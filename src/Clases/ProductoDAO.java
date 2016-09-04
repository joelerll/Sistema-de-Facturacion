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
        // No ingreso ningun dato
        if (id.equals("") && nombre.equals("") && marca.equals("")){
            return null;
        }
        String sql = "{call buscarProductoFormatoUnoJ(?,?,?)}";
        try{
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.setString(1, id);
            cs.setString(2, nombre);
            cs.setString(3, marca);
            cs.executeQuery();
            try(ResultSet rset = cs.getResultSet()){
                while(rset.next())
                {
                    if(rset.getInt(5) == 0){
                        continue;
                    }
                    ProductoVO producto  = new ProductoVO();
                    producto.setId(rset.getString(1));
                    producto.setNombre(rset.getString(2));
                    producto.setMarca(rset.getString(3));
                    producto.setImagen(rset.getString(4));
                    producto.setStock(rset.getInt(5));
                    producto.setPrecio_inicial(rset.getBigDecimal(6));
                    producto.setPrecio_venta(rset.getBigDecimal(7));
                    productos.add(producto);
                }
            }
            con.desconetar();
            cs.close();
        }catch(SQLException e){
            System.out.println("Clases.ProductoDAO.buscarProductoFacturaFormato ERROR");
            return null;
        }
        return productos;
    }
    
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
