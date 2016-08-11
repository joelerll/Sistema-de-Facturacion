/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proveedor;

import database.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fernando
 */
public class Proveedor {
    //ATRIBUTOS
    private static final DBconnection database=new DBconnection();
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    
    private int PId;
    private String PNombre;
    private String PDireccion;
    
    //METODOS
    public Proveedor() {
    }

    public Proveedor(int PId, String PNombre, String PDireccion) {
        this.PId = PId;
        this.PNombre = PNombre;
        this.PDireccion = PDireccion;
    }

    public Proveedor(String PNombre, String PDireccion) {
        this.PNombre = PNombre;
        this.PDireccion = PDireccion;
    }
    
    public int getPId() {
        return PId;
    }

    public void setPId(int PId) {
        this.PId = PId;
    }

    public String getPNombre() {
        return PNombre;
    }

    public void setPNombre(String PNombre) {
        this.PNombre = PNombre;
    }

    public String getPDireccion() {
        return PDireccion;
    }

    public void setPDireccion(String PDireccion) {
        this.PDireccion = PDireccion;
    }
    
    public static void ingresarProveedor(int id, String nombre, String dir){
        try{
            con=database.conectar();
            String query = "INSERT INTO proveedor VALUES(?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,nombre);
            preparedStatement.setString(3,dir);
            preparedStatement.executeUpdate();
        }catch (SQLException ex)
        {
            System.out.println("No se ingreso el Proveedor");
        }finally{
            System.out.println("Se ingreso el Proveedor");
        }
    }
    
    public static List <Proveedor> searchProveedor(String campo,String nombreBuscar){
        rs = null;
        List <Proveedor>  proveedores = new ArrayList<> ();
        try{
            con = database.conectar();
            String q ="SELECT * FROM proveedor WHERE "+ campo +" LIKE ('%"+nombreBuscar+"%')";
            System.out.println(q);
            ps = con.prepareCall(q);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setPId(rs.getInt(1));
                proveedor.setPNombre(rs.getString(2).toUpperCase());
                proveedor.setPDireccion(rs.getString(3).toUpperCase());
                proveedores.add(proveedor);
            }ps.close();
            con.close();
            rs.close();
        }catch(SQLException sql){
            System.out.println("Error en buscar item para eliminar");
        }
        return proveedores;
    }
    
    public static void eliminarProveedorSQL(Proveedor proveedor){
        try{
            con = database.conectar();
            String q ="DELETE FROM proveedor WHERE id = " + proveedor.getPId(); 
            System.out.println(q);
            ps = con.prepareStatement(q);
            ps.execute();
            con.close();
            System.out.println("Borrado el proveedor seleccionado");
        }catch(SQLException sql){
            System.out.println("Error al tratar de eliminar proveedor");
        }
    }
    
    public static void editarProveedorSQL(Proveedor proveedor){
        String q = "UPDATE proveedor SET nombre = '"+ proveedor.getPNombre() + "', direccion = '" + proveedor.getPDireccion() +
                "' WHERE id = "+ proveedor.getPId();
        try{
            con = database.conectar();
            System.out.println(q);
            ps = con.prepareStatement(q);
            ps.execute();
            con.close();
            System.out.println("Editado el proveedor seleccionado");
        }catch(SQLException sql){
            System.out.println("Error al tratar de editar el proveedor");
        }
    }
    
    //Modificado para el ListBox
    @Override
    public String toString() {
        return " NOMBRE "+ PNombre + " ID " +  PId;
    }
    
}
