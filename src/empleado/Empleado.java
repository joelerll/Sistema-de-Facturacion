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
import java.util.ArrayList;
import java.util.List;

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
    
    public void juan(){
        
    }
    
    public static void ingresarEmpleado(String cedula, String nombre, String apellido, String dir, String fecha_ing, String horario_ent, String horario_sal, BigDecimal sueldo, int es_admin, String telefono, String user){
        try{
            con=database.conectar();
            String query = "INSERT INTO empleado VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,cedula);
            preparedStatement.setString(2,nombre);
            preparedStatement.setString(3,apellido);
            preparedStatement.setString(4,dir);
            preparedStatement.setString(5, fecha_ing);
            preparedStatement.setString(6,horario_ent);
            preparedStatement.setString(7,horario_sal);
            preparedStatement.setBigDecimal(8, sueldo);
            preparedStatement.setInt(9, es_admin);
            preparedStatement.setString(10,telefono);
            preparedStatement.setString(11,user);
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            System.out.println("No se ingreso el Empleado");
        }finally{
            System.out.println("Se ingreso el Empleado");
        }
    }
    
    public static List <Empleado> searchEmpleado(String campo,String nombreBuscar){
        rs = null;
        List <Empleado>  empleados = new ArrayList<> ();
        try{
            con = database.conectar();
            String q ="SELECT * FROM empleado WHERE "+ campo +" LIKE ('%"+nombreBuscar+"%')";
            System.out.println(q);
            ps = con.prepareCall(q);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setEcedula(rs.getString(1));
                empleado.setEnombre(rs.getString(2));
                empleado.setEapellido(rs.getString(3));
                empleado.setEdireccion(rs.getString(4));
                empleado.setEfecha_ing(rs.getString(5));
                empleado.setEhorario_ent(rs.getString(6));
                empleado.setEhorario_sal(rs.getString(7));
                empleado.setEsueldo(new BigDecimal(rs.getString(8)));
                empleado.setEes_admin(rs.getInt(9));
                empleado.setEtelefono(rs.getString(10));
                empleado.setEuser(rs.getString(11));
                empleados.add(empleado);
            }
            ps.close();
            con.close();
            rs.close();
        }catch(SQLException sql){
            System.out.println("Error en buscar empleado para eliminar");
        }
        return empleados;
    }
    
    public static List<Empleado> buscarPorUsuario(String usuario){
        List <Empleado>  empleados = new ArrayList<> ();
        try{
            con = database.conectar();
            String q ="SELECT * FROM empleado WHERE usuario = '"+usuario+"'";
            ps = con.prepareCall(q);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setEcedula(rs.getString(1));
                empleado.setEnombre(rs.getString(2));
                empleado.setEapellido(rs.getString(3));
                empleado.setEdireccion(rs.getString(4));
                empleado.setEfecha_ing(rs.getString(5));
                empleado.setEhorario_ent(rs.getString(6));
                empleado.setEhorario_sal(rs.getString(7));
                empleado.setEsueldo(new BigDecimal(rs.getString(8)));
                empleado.setEes_admin(rs.getInt(9));
                empleado.setEtelefono(rs.getString(10));
                empleado.setEuser(rs.getString(11));
                empleados.add(empleado);
            }
            ps.close();
            con.close();
            rs.close();
        }catch(SQLException sql){
            System.out.println("Error de busqueda");
        }
        return empleados;
    }
    
    public static void eliminarEmpleadoSQL(Empleado empleado){
        try{
            con = database.conectar();
            String q ="DELETE FROM empleado WHERE cedula = " + empleado.getEcedula(); 
            System.out.println(q);
            ps = con.prepareStatement(q);
            ps.execute();
            con.close();
            System.out.println("Borrado el empleado seleccionado");
        }catch(SQLException sql){
            System.out.println("Error al tratar de eliminar empleado");
        }
    }
    
    public static void editarEmpleadoSQL(Empleado empleado){
        String q = "UPDATE empleado SET nombre = '"+ empleado.getEnombre() + "', apellido = '" + empleado.getEapellido() + "', direccion = '" + empleado.getEdireccion() + "', fecha_ing = '" + empleado.getEfecha_ing() + "', horario_ent = '" + empleado.getEhorario_ent() + "', horario_sal = '" + empleado.getEhorario_sal() + "', sueldo = '" + empleado.getEsueldo() + "', es_admin = '" + empleado.getEes_admin().toString() + "', telefono = '" + empleado.getEtelefono() + "', usuario = '" + empleado.getEuser() +
                "' WHERE cedula = '"+ empleado.getEcedula() + "'";
        try{
            con = database.conectar();
            System.out.println(q);
            ps = con.prepareStatement(q);
            ps.execute();
            con.close();
            System.out.println("Editado el empleado seleccionado");
        }catch(SQLException sql){
            System.out.println("Error al tratar de editar el empleado");
        }
    }
    
    //Modificado para el ListBox
    @Override
    public String toString() {
        return " NOMBRE "+ Enombre + " CEDULA " +  Ecedula;
    }
    
    
    
    
    
 }
   
  
   
    
   
    

