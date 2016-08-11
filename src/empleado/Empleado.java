/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleado;

import database.DBconnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author fernando
 */
public class Empleado {
    //ATRIBUTOS
    /*private static final DBconnection database=new DBconnection();
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    
    private String Ecedula;
    private String Enombre;
    private String Eapellido;
    private String Edireccion;
    private Date Efecha_ing;
    private String Ehorario_ent;
    private String Ehorario_sal;
    private BigDecimal Esueldo;
    private Integer Ees_admin;
    private String ETelefono;
    
    //METODOS
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

    public Date getEfecha_ing() {
        return Efecha_ing;
    }

    public void setEfecha_ing(Date Efecha_ing) {
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

    public String getETelefono() {
        return ETelefono;
    }

    public void setETelefono(String ETelefono) {
        this.ETelefono = ETelefono;
    }
    
    public static void ingresarEmpleado(String cedula, String nombre, String apellido, String dir, Date fecha_ing, String horario_ent, String horario_sal, BigDecimal sueldo, Integer es_admin, String telefono){
        try{
            con=database.conectar();
            String query = "INSERT INTO empleado VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,cedula);
            preparedStatement.setString(2,nombre);
            preparedStatement.setString(3,apellido);
            preparedStatement.setString(4,dir);
            preparedStatement.setDate(5, fecha_ing);
            preparedStatement.setString(6,horario_ent);
            preparedStatement.setString(7,horario_sal);
            preparedStatement.setBigDecimal(8, sueldo);
            preparedStatement.setInt(9, es_admin);
            preparedStatement.setString(10,telefono);
            preparedStatement.executeUpdate();
        }catch (SQLException ex)
        {
            System.out.println("No se ingreso el Empleado");
        }finally{
            System.out.println("Se ingreso el Empleado");
        }
    }*/
    
}
