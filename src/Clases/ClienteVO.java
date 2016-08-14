/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Date;

/**
 *
 * @author joelerll
 */
public class ClienteVO {
    private String Cedula_C;
    private Date Fecha_C;
    private String Nombre_C;
    private String Apellido_C;
    private String Direccion_C;
    private String Celular_C;
    private String Convencional_C;
    private String Email_C;

    public ClienteVO() {
    }

    public ClienteVO(String Cedula_C, String Nombre_C, String Apellido_C) {
        this.Cedula_C = Cedula_C;
        this.Nombre_C = Nombre_C;
        this.Apellido_C = Apellido_C;
    }

    public ClienteVO(String Cedula_C) {
        this.Cedula_C = Cedula_C;
    }
 
    
    public String getCedula_C() {
        return Cedula_C;
    }

    public void setCedula_C(String Cedula_C) {
        this.Cedula_C = Cedula_C;
    }

    public Date getFecha_C() {
        return Fecha_C;
    }

    public void setFecha_C(Date Fecha_C) {
        this.Fecha_C = Fecha_C;
    }

    public String getNombre_C() {
        return Nombre_C;
    }

    public void setNombre_C(String Nombre_C) {
        this.Nombre_C = Nombre_C;
    }

    public String getApellido_C() {
        return Apellido_C;
    }

    public void setApellido_C(String Apellido_C) {
        this.Apellido_C = Apellido_C;
    }

    public String getDireccion_C() {
        return Direccion_C;
    }

    public void setDireccion_C(String Direccion_C) {
        this.Direccion_C = Direccion_C;
    }

    public String getCelular_C() {
        return Celular_C;
    }

    public void setCelular_C(String Celular_C) {
        this.Celular_C = Celular_C;
    }

    public String getConvencional_C() {
        return Convencional_C;
    }

    public void setConvencional_C(String Convencional_C) {
        this.Convencional_C = Convencional_C;
    }

    public String getEmail_C() {
        return Email_C;
    }

    public void setEmail_C(String Email_C) {
        this.Email_C = Email_C;
    }

    @Override
    public String toString() {
        return "ClienteVO{" + "Cedula_C=" + Cedula_C + ", Fecha_C=" + Fecha_C + ", Nombre_C=" + Nombre_C + ", Apellido_C=" + Apellido_C + ", Direccion_C=" + Direccion_C + ", Celular_C=" + Celular_C + ", Convencional_C=" + Convencional_C + ", Email_C=" + Email_C + '}';
    }
    
    
   
}
