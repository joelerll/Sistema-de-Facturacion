/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleado;

import database.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class Empleado {
    //ATRIBUTOS
    private static final DBconnection database=new DBconnection();
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    
    private String Ecedula;
    private String Enombre;
    private String Eapellido;
    private String Edireccion;
    private String Efecha_ing;
    private String Ehorario_ent;
    private String Ehorario_sal;
    private BigDecimal Esueldo;
    private Integer Ees_admin;      //1 si lo es, 0 si no lo es
    private String Etelefono;
    private String Euser;
    
    //METODOS
    public Empleado() {
    }

    public Empleado(String Ecedula, String Enombre, String Eapellido, String Edireccion, String Efecha_ing, String Ehorario_ent, String Ehorario_sal, BigDecimal Esueldo, Integer Ees_admin, String Etelefono, String Euser) {
        this.Ecedula = Ecedula;
        this.Enombre = Enombre;
        this.Eapellido = Eapellido;
        this.Edireccion = Edireccion;
        this.Efecha_ing = Efecha_ing;
        this.Ehorario_ent = Ehorario_ent;
        this.Ehorario_sal = Ehorario_sal;
        this.Esueldo = Esueldo;
        this.Ees_admin = Ees_admin;
        this.Etelefono = Etelefono;
        this.Euser = Euser;
    }

    public String getEcedula() {
        return Ecedula;
    }

    public void setEcedula(String Ecedula) {
        this.Ecedula = Ecedula;
    }

    public String getEnombre() {
        return Enombre;
    }

    public void setEnombre(String Enombre) {
        this.Enombre = Enombre;
    }

    public String getEapellido() {
        return Eapellido;
    }

    public void setEapellido(String Eapellido) {
        this.Eapellido = Eapellido;
    }

    public String getEdireccion() {
        return Edireccion;
    }

    public void setEdireccion(String Edireccion) {
        this.Edireccion = Edireccion;
    }

    public String getEfecha_ing() {
        return Efecha_ing;
    }

    public void setEfecha_ing(String Efecha_ing) {
        this.Efecha_ing = Efecha_ing;
    }

    public String getEhorario_ent() {
        return Ehorario_ent;
    }

    public void setEhorario_ent(String Ehorario_ent) {
        this.Ehorario_ent = Ehorario_ent;
    }

    public String getEhorario_sal() {
        return Ehorario_sal;
    }

    public void setEhorario_sal(String Ehorario_sal) {
        this.Ehorario_sal = Ehorario_sal;
    }

    public BigDecimal getEsueldo() {
        return Esueldo;
    }

    public void setEsueldo(BigDecimal Esueldo) {
        this.Esueldo = Esueldo;
    }

    public Integer getEes_admin() {
        return Ees_admin;
    }

    public void setEes_admin(Integer Ees_admin) {
        this.Ees_admin = Ees_admin;
    }

    public String getEtelefono() {
        return Etelefono;
    }

    public void setEtelefono(String ETelefono) {
        this.Etelefono = ETelefono;
    }

    public String getEuser() {
        return Euser;
    }

    public void setEuser(String Euser) {
        this.Euser = Euser;
    }
    
    public static void ingresarEmpleado2(Empleado empleado){
        try {
            con=database.conectar();
            CallableStatement procedure = con.prepareCall("{ call ingresar_empleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
            procedure.setString(1,empleado.getEcedula());
            procedure.setString(2,empleado.getEnombre());
            procedure.setString(3,empleado.getEapellido());
            procedure.setString(4,empleado.getEdireccion());
            procedure.setString(5,empleado.getEfecha_ing());
            procedure.setString(6,empleado.getEhorario_ent());
            procedure.setString(7,empleado.getEhorario_sal());
            procedure.setBigDecimal(8,empleado.getEsueldo());
            procedure.setInt(9,empleado.getEes_admin());
            procedure.setString(10,empleado.getEtelefono());
            procedure.setString(11,empleado.getEuser());
            procedure.execute();
            System.out.println("Empleado ingresado con éxito");
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List <Empleado> buscarEmpleado2(Empleado empleado){
        List <Empleado>  listaEmpleados = new ArrayList<> ();
        try {
            con = database.conectar();
            CallableStatement procedure = con.prepareCall("{call buscar_empleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            procedure.setString(1,empleado.getEcedula());
            procedure.setString(2,empleado.getEnombre());
            procedure.setString(3,empleado.getEapellido());
            procedure.setString(4,empleado.getEdireccion());
            procedure.setString(5,empleado.getEfecha_ing());
            procedure.setString(6,empleado.getEhorario_ent());
            procedure.setString(7,empleado.getEhorario_sal());
            procedure.setBigDecimal(8,empleado.getEsueldo());
            procedure.setInt(9,empleado.getEes_admin());
            procedure.setString(10,empleado.getEtelefono());
            procedure.setString(11,empleado.getEuser());
            procedure.execute();
            
            try (ResultSet resultSet = procedure.getResultSet()) {
                while (resultSet.next()){
                    Empleado e = new Empleado();
                    e.setEcedula(resultSet.getString(1));
                    e.setEnombre(resultSet.getString(2));
                    e.setEapellido(resultSet.getString(3));
                    e.setEdireccion(resultSet.getString(4));
                    e.setEfecha_ing(resultSet.getString(5));
                    e.setEhorario_ent(resultSet.getString(6));
                    e.setEhorario_sal(resultSet.getString(7));
                    e.setEsueldo(resultSet.getBigDecimal(8));
                    e.setEes_admin(resultSet.getInt(9));
                    e.setEtelefono(resultSet.getString(10));
                    e.setEuser(resultSet.getString(11));
                    listaEmpleados.add(e);
                }
            }
            return listaEmpleados;
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Empleado> buscarPorUsuario(String usuario){
        List <Empleado>  listaEmpleados = new ArrayList<> ();
        try {
            con = database.conectar();
            CallableStatement procedure = con.prepareCall("{call buscar_empleado_user(?)}");
            procedure.setString(1,usuario);
            procedure.execute();
            
            try (ResultSet resultSet = procedure.getResultSet()) {
                while (resultSet.next()){
                    Empleado e = new Empleado();
                    e.setEcedula(resultSet.getString(1));
                    e.setEnombre(resultSet.getString(2));
                    e.setEapellido(resultSet.getString(3));
                    e.setEdireccion(resultSet.getString(4));
                    e.setEfecha_ing(resultSet.getString(5));
                    e.setEhorario_ent(resultSet.getString(6));
                    e.setEhorario_sal(resultSet.getString(7));
                    e.setEsueldo(resultSet.getBigDecimal(8));
                    e.setEes_admin(resultSet.getInt(9));
                    e.setEtelefono(resultSet.getString(10));
                    e.setEuser(resultSet.getString(11));
                    listaEmpleados.add(e);
                }
            }
            return listaEmpleados;
            
        }catch(SQLException sql){
            System.out.println("Error de busqueda");
        }
        return listaEmpleados;
    }
    
    public static void eliminarEmpleado2(String cedula){
        try {
            con=database.conectar();
            CallableStatement procedure = con.prepareCall("{ call eliminar_empleado(?) }");
            procedure.setString(1, cedula);
            procedure.execute();
            System.out.println("Empleado eliminado exitosamente");
           
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public static boolean editarEmpleado2(Empleado empleado, String cedulaOriginal){
        try {
            con=database.conectar();
            CallableStatement procedure = null;
            procedure = con.prepareCall("{ call editar_empleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
            procedure.setString(1,empleado.getEcedula());
            procedure.setString(2,empleado.getEnombre());
            procedure.setString(3,empleado.getEapellido());
            procedure.setString(4,empleado.getEdireccion());
            procedure.setString(5,empleado.getEfecha_ing());
            procedure.setString(6,empleado.getEhorario_ent());
            procedure.setString(7,empleado.getEhorario_sal());
            procedure.setBigDecimal(8,empleado.getEsueldo());
            procedure.setInt(9,empleado.getEes_admin());
            procedure.setString(10,empleado.getEtelefono());
            procedure.setString(11,empleado.getEuser());
            procedure.setString(12, cedulaOriginal);
            procedure.execute();
            System.out.println("Empleado Editado con éxito");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //Modificado para el ListBox
    @Override
    public String toString() {
        return " NOMBRE "+ Enombre + " CEDULA " +  Ecedula;
    }
   
    
 }
   
  
   
    
   
    

