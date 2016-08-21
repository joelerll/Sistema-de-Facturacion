/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Utils.Colores;
import database.DBconexion;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author joelerll
 */
public class FacturaDAO {
    
    public static int getIdLast(){
        int last=0;
        String sql = "SELECT max(id) FROM factura";
        try{
            DBconexion con = new DBconexion();
            PreparedStatement ps = con.getConnection().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            last = rs.getInt(1);
            con.desconetar();
        }catch(SQLException e){
            System.out.println("Clases.FacturaDAO.getIdLast ERROR");
            return 0;
        }
        return last;
    }
    
    public static void setFactura(BigDecimal valor, Date fecha, String cedula_c, String cedula_empl) throws ParseException{
        
        Timestamp time = new Timestamp(fecha.getTime());
        //String sql = "INSERT INTO factura (valor,fecha,cedula_c,cedula_empl) VALUES ("+valor+","+ time+","+cedula_c+","+cedula_empl+")";
        String sql = "INSERT INTO factura(valor,fecha,cedula_c,cedula_empl) VALUES (?,?,?,?)";
        System.out.println(sql);
        try{
            DBconexion con = new DBconexion();
            PreparedStatement ps = con.getConnection().prepareStatement(sql);
            ps.setBigDecimal(1, valor);
            ps.setTimestamp(2,time);
            ps.setString(3, cedula_c);
            ps.setString(4, cedula_empl);
            ps.executeUpdate();
            //con.desconetar();
        }catch(SQLException e){
            System.out.println(Colores.ANSI_RED + "ERROR Clases.FacturaDAO setFactura" + Colores.ANSI_RESET);
        }
    }
}
