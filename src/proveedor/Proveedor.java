/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proveedor;

import database.DBconnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public static void ingresarProveedor2(Proveedor proveedor, String ptelefono){
        try{
            con=database.conectar();
            CallableStatement procedure = con.prepareCall("{ call ingresar_proveedor(?, ?, ?) }");          
            procedure.setString(1,proveedor.getPNombre());
            procedure.setString(2,proveedor.getPDireccion());
            procedure.setString(3,ptelefono);
            
            procedure.execute();
            System.out.println("Proveedor ingresado con éxito");
        } catch (SQLException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public static List <Proveedor> buscarProveedor2(Proveedor proveedor){
        List <Proveedor>  proveedores = new ArrayList<> ();
        try{
            con=database.conectar();
            CallableStatement procedure = con.prepareCall("{ call buscar_proveedor(?, ?, ?) }");
            procedure.setInt(1,proveedor.getPId());
            procedure.setString(2,proveedor.getPNombre());
            procedure.setString(3,proveedor.getPDireccion());
            procedure.execute();
                        
            try (ResultSet resultSet = procedure.getResultSet()) {
                while (resultSet.next()){
                    Proveedor p = new Proveedor();
                    p.setPId(resultSet.getInt(1));
                    p.setPNombre(resultSet.getString(2));
                    p.setPDireccion(resultSet.getString(3));
                    proveedores.add(p);
                }
            }
            return proveedores;
            } catch (SQLException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
   
    public static void eliminarProveedor2(Integer id){
        try {
            con=database.conectar();
            CallableStatement procedure = con.prepareCall("{ call eliminar_proveedor(?) }");
            procedure.setInt(1, id);
            procedure.execute();
            System.out.println("Proveedor eliminado exitosamente");
           
        } catch (SQLException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public static void editarProveedor2(Proveedor proveedor, Integer idOriginal){
        try {
            con=database.conectar();
            CallableStatement procedure = con.prepareCall("{ call editar_proveedor(?, ?, ?, ?) }");
            procedure.setInt(1,proveedor.getPId());
            procedure.setString(2,proveedor.getPNombre());
            procedure.setString(3,proveedor.getPDireccion());
            procedure.setInt(4, idOriginal);
            procedure.execute();
            System.out.println("Proveedor editado con éxito");
        } catch (SQLException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    //Modificado para el ListBox
    @Override
    public String toString() {
        return " NOMBRE "+ PNombre + " ID " +  PId;
    }
    
}

