/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

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

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", precio=" + precio + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha + '}';
    }
    
}
