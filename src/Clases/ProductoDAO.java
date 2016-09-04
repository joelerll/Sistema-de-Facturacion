/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import database.DBconexion;
import factura.ProductosCanasta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;

/**
 *
 * @author joelerll
 */
public class ProductoDAO {
    
    public static List <ProductoVO> buscarProductoFacturaFormato(String id,String nombre, String marca){
        CallableStatement cs = null;
        List <ProductoVO> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE ";
        boolean idBandera = false,nombreBandera = false;
        if (!"".equals(id)){
            idBandera = true;
            sql = "{call buscarProductoFormatoUnoJ(?,?,?,?,?,?)}";
            //sql = sql + "id LIKE '%"+ id + "%'";
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
                if(rs.getInt(5) == 0){
                    continue;
                }
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
    
   /* public static void ActualizarProducto (List<ProductosCanasta> productos){
        int set;
        CallableStatement cs = null;
        String productoId= "";
        String sql = "{call actualizarProductoListJ(?,?)}";
        try{
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            for(ProductosCanasta po : productos){
                set = po.getStock();
                //String sql = "UPDATE producto SET stock =" + (set+1)+ "WHERE id = " + productoId;
                cs.setInt(1, set+1);
                cs.setString(2, productoId);
                cs.executeUpdate();
            }
            con.desconetar();
        }catch(SQLException e){
            System.out.println("Clases.ProductoDAO.ActualizarProducto ERROR");
        }
    }*/
    
     public static void actualizarProducto(String id_producto, int cantidad){
        CallableStatement cs = null;
        String sql = "{call actualizarProducto (?,?)}";
        try {
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.setString(1, id_producto);
            cs.setInt(2, cantidad);
            cs.executeQuery();
        } catch (SQLException e) {
        }
    }
}
