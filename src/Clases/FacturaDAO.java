/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Utils.Colores;
import database.DBconexion;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author joelerll
 */
public class FacturaDAO {
    
    public static int getIdLast(){
        int last=0;
        CallableStatement cs = null;
        String sql = "{call getIdLastFacturaJ(?)}";
        try{
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.executeQuery();
            last = cs.getInt(1);
            cs.close();
            con.desconetar();
        }catch(SQLException e){
            System.out.println("Clases.FacturaDAO.getIdLast ERROR");
            return 0;
        }
        return last;
    }
    
    public static void setFactura(BigDecimal valor, Timestamp fecha, String cedula_c, String cedula_empl){
        CallableStatement cs = null;
        String sql = "{call setFacturaJ(?,?,?,?)}";
        try{
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.setBigDecimal(1, valor);
            cs.setTimestamp(2,fecha);
            cs.setString(3, cedula_c);
            cs.setString(4, cedula_empl);
            cs.executeQuery();
            cs.close();
            con.desconetar();
            System.out.println("Factura ha sido guardada");
        }catch(SQLException e){
            System.out.println(Colores.ANSI_RED + "ERROR Clases.FacturaDAO setFactura" + Colores.ANSI_RESET);
        }
    }
    
    public static void setProducto_Factura(String producto_id, int factura_id){
        CallableStatement cs = null;
        String sql = "{call setProducto_FacturaJ(?,?)}";
        try{
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.setString(1, producto_id);
            cs.setInt(2, factura_id);
            cs.executeQuery();
            cs.close();
            con.desconetar();
            System.out.println("Producto Factura ha sido guardado");
        }catch(SQLException e){
            System.out.println(Colores.ANSI_RED + "ERROR Clases.FacturaDAO setProducto_Factura" + Colores.ANSI_RESET);
        }
    }
    
    public static FacturaVO getFactura(int id) throws SQLException{
        FacturaVO factura= new FacturaVO();
        CallableStatement cs = null;
        int idf;
        String sql = "{call getFactura (? , ?, ?, ?, ?, ?, ?,?)}";
        try {
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.setInt(1, id);
            cs.executeQuery();
            factura.setId(cs.getInt(2));
            factura.setValor(cs.getBigDecimal(3));
            factura.setFecha(cs.getTimestamp(4));
            factura.setCedula_C(cs.getString(5));
            factura.setCedula_empl(cs.getString(6));
            factura.setAnulada((cs.getInt(7) != 0));
            factura.setActualizada(cs.getTimestamp(8));
            cs.close();
            con.desconetar();
        } catch (SQLException e) {
            System.out.println(Colores.ANSI_RED+"ERROR FacturaDAO.getFactura"+Colores.ANSI_RESET);
        }
        return factura;
    }
    
    public static void anularFactura(String id){
        CallableStatement cs = null;
        String sql = "{call anularFactura(?)}";
        try {
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.setString(1, id);
            cs.executeQuery();
        } catch (SQLException e) {
            System.out.println("ERROR FacturaDAO.anularFactura");
        }
    }
}
