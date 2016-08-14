/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import database.DBconnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joelerll
 */
public class Item {
    private int id;
    private BigDecimal precio;
    private String nombre;
    private String descripcion;
    private Date fecha;
    public Item() {
    }

    public Item(BigDecimal precio, String nombre, String descripcion, Date date) {
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = date;
    }

    public Item(int id, BigDecimal precio, String nombre, String descripcion, Date date) {
        this.id = id;
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = date;
    }
    
    public static List <Item> searchItem(String campo,String nombreBuscar){
        DBconnection database=new DBconnection();
        Connection conexion;
        PreparedStatement ps;
        ResultSet rs = null;
        List <Item>  items=new ArrayList<> ();
        try{
            conexion = database.conectar();
            String q ="SELECT * FROM item WHERE "+ campo +" LIKE ('%"+nombreBuscar+"%')";
            System.out.println(q);
            ps = conexion.prepareCall(q);
            rs = ps.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt(1));
                item.setPrecio(rs.getBigDecimal(2));
                item.setNombre(rs.getString(3));
                item.setDescripcion(rs.getString(4));
                item.setFecha(rs.getDate(5));
                items.add(item);
            }ps.close();
            conexion.close();
            rs.close();
        }catch(SQLException sql){
            System.out.println("Error en buscar item para eliminar");
        }
        return items;
    }
    
    public static int eliminarItemSQL(Item item){
        DBconnection database=new DBconnection();
        Connection conexion;
        PreparedStatement ps;
        try{
            conexion = database.conectar();
            String q ="DELETE FROM item WHERE id = " +item.getId(); 
            System.out.println(q);
            ps = conexion.prepareStatement(q);
            ps.execute();
            conexion.close();
            System.out.println("Borrado el item seleccionado");
        }catch(SQLException sql){
            System.out.println("Error al tratar de eliminar item");
            return sql.getErrorCode();
        }
        return 0;
    }
    
    public static void editarItemSQL(Item item){
        DBconnection database=new DBconnection();
        Connection conexion;
        String q = "UPDATE item SET precio = '"+ item.getPrecio() + "', nombre = '" + item.getNombre()
                + "', descripcion = '" + item.getDescripcion() + "', fecha = '" + item.getFecha() +
                "' WHERE id = "+item.getId();
        PreparedStatement ps;
        try{
            conexion = database.conectar();
            //String q ="DELETE FROM item WHERE id = " +item.getId(); 
            System.out.println(q);
            ps = conexion.prepareStatement(q);
            ps.execute();
            conexion.close();
            System.out.println("Editado el item seleccionado");
        }catch(SQLException sql){
            System.out.println("Error al tratar de editar item");
        }
    }
     /* public static List <Cliente> searchClientesByName(String name)
    {
        List <Cliente>  clientes=new ArrayList<> ();
        
        String patron=String.format("");
        String query=String.format("\"SELECT Nombre_C FROM Cliente WHERE Nombre_C REGEXP "+"'"+"(?i)"+name+"'"+"\"");
        String query2=String.format("\"SELECT * FROM Cliente\"");
        try {
            con=database.conectar();
            ps = con.prepareCall(" SELECT * FROM Cliente WHERE Nombre_C REGEXP'"+name+"'");
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente=new Cliente();
                cliente.setCedula_C(rs.getString(1));
                cliente.setNombre_C(rs.getString(2));
                cliente.setDireccion_C (rs.getString(3));
                clientes.add(cliente);
            }ps.close();
            con.close();
            rs.close();
            return clientes;
        } catch (SQLException ex) {
            System.out.println("----No cargo Cliente-----------");
        }
        return clientes;
    }*/
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    //Modificado para el list box
    @Override
    public String toString() {
        return " NOMBRE "+ nombre + " ID " +  id + "  FECHA " + fecha;
    }
}
