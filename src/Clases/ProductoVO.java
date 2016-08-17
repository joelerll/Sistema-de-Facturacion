/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import com.mysql.jdbc.Blob;
import java.math.BigDecimal;

/**
 *
 * @author joelerll
 */
public class ProductoVO {
    private String id;
    private String nombre;
    private String marca;
    private String imagen;
    private int stock;
    private BigDecimal precio_venta;
    private BigDecimal precio_inicial;

    public ProductoVO() {
    }

    public ProductoVO(String id) {
        this.id = id;
    }
    
    public ProductoVO(String id, String nombre, String marca, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.stock = stock;
    }

    public ProductoVO(String id, String nombre, String marca, String imagen, int stock, BigDecimal precio_venta) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.imagen = imagen;
        this.stock = stock;
        this.precio_venta = precio_venta;
    }
 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(BigDecimal precio_venta) {
        this.precio_venta = precio_venta;
    }

    public BigDecimal getPrecio_inicial() {
        return precio_inicial;
    }

    public void setPrecio_inicial(BigDecimal precio_inicial) {
        this.precio_inicial = precio_inicial;
    }

    @Override
    public String toString() {
        return "ProductoVO{" + "id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", imagen=" + imagen + ", stock=" + stock + ", precio_venta=" + precio_venta + ", precio_inicial=" + precio_inicial + '}';
    }
}
