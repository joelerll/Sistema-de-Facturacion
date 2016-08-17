/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import database.DBconexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
