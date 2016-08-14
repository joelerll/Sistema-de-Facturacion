/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author joelerll
 */
public class EmpleadoVO {
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private Date fecha_ing;
    private String horario_ent;
    private String horario_sal;
    private BigDecimal sueldo;
    private int es_admin;
    private String telefono;

    public EmpleadoVO() {
    }
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha_ing() {
        return fecha_ing;
    }

    public void setFecha_ing(Date fecha_ing) {
        this.fecha_ing = fecha_ing;
    }

    public String getHorario_ent() {
        return horario_ent;
    }

    public void setHorario_ent(String horario_ent) {
        this.horario_ent = horario_ent;
    }

    public String getHorario_sal() {
        return horario_sal;
    }

    public void setHorario_sal(String horario_sal) {
        this.horario_sal = horario_sal;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public int getEs_admin() {
        return es_admin;
    }

    public void setEs_admin(int es_admin) {
        this.es_admin = es_admin;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return  nombre + " " + apellido;
    }
    
    
}
