/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import com.mysql.jdbc.Blob;
import database.DBconexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joelerll
 */
public class ProductoDAO {
    
    public static List <ProductoVO> buscarProductoFacturaFormato(String id,String nombre, String marca){
        List <ProductoVO> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE ";
        boolean idBandera = false,nombreBandera = false;
        if (!"".equals(id)){
            idBandera = true;
            sql = sql + "id LIKE '%"+ id + "%'";
        }
        if (!"".equals(nombre)){
            nombreBandera = true;
            if (idBandera){
                sql = sql + " AND ";
            }
            sql = sql + "nombre LIKE '%" + nombre + "%'";
        }
        if (!"".equals(marca)){
            if (nombreBandera || idBandera){
                sql = sql + " AND ";
            }
            sql = sql + "marca LIKE '%" + marca + "%'";
        }
        
        // No ingreso ningun dato
        if ("SELECT * FROM producto WHERE ".equals(sql)){
            return null;
        }
        System.out.println(sql);
        try{
            DBconexion con = new DBconexion();
            PreparedStatement ps = con.getConnection().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                ProductoVO producto  = new ProductoVO();
                producto.setId(rs.getString(1));
                producto.setNombre(rs.getString(2));
                producto.setMarca(rs.getString(3));
                producto.setImagen(rs.getString(4));
                producto.setStock(rs.getInt(5));
                producto.setPrecio_inicial(rs.getBigDecimal(6));
                producto.setPrecio_venta(rs.getBigDecimal(7));
                productos.add(producto);
            }
            con.desconetar();
        }catch(SQLException e){
            System.out.println("Clases.ProductoDAO.buscarProductoFacturaFormato ERROR");
            return null;
        }
        return productos;
    }
}
