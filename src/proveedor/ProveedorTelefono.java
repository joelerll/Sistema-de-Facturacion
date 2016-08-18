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

/**
 *
 * @author fernando
 */
public class ProveedorTelefono {
    //ATRIBUTOS
    private static final DBconnection database=new DBconnection();
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    
    private String id_proveedor;
    private String telefono;
           
    //METODOS
    public ProveedorTelefono(String id_proveedor, String telefono) {
        this.id_proveedor = id_proveedor;
        this.telefono = telefono;
    }

    public ProveedorTelefono() {
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public static void ingresarTelefonoProveedor(String id_proveedor, String telefono){
        try{
            con=database.conectar();
            String query = "INSERT INTO telefono_proveedor VALUES(?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,id_proveedor);
            preparedStatement.setString(2,telefono);
            preparedStatement.executeUpdate();
        }catch (SQLException ex)
        {
            System.out.println("No se ingreso el Telefono del Proveedor");
        }finally{
            System.out.println("Se ingreso el Telefono del Proveedor");
        }
    }
    
}
