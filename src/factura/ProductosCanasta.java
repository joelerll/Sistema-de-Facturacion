/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factura;

import Clases.ProductoVO;
import java.math.BigDecimal;

/**
 *
 * @author joelerll
 */
public class ProductosCanasta extends ProductoVO{
    private int nmr;
    private int cantidad;
    private BigDecimal total;

    public ProductosCanasta(int nmr, int cantidad, BigDecimal total, String id, String nombre, String marca, String imagen, int stock, BigDecimal precio_venta) {
        super(id, nombre, marca, imagen, stock, precio_venta);
        this.nmr = nmr;
        this.cantidad = cantidad;
        this.total = total;
    }

    public ProductosCanasta() {
    }
    
    

    public ProductosCanasta(ProductoVO p, int nmr, int cantidad, BigDecimal total){
        super(p.getId(),p.getNombre(),p.getMarca(),p.getImagen(),p.getStock(),p.getPrecio_venta());
        this.nmr = nmr;
        this.cantidad = cantidad;
        this.total = total;
    }
    
    public ProductosCanasta(ProductoVO p){
        super(p.getId(),p.getNombre(),p.getMarca(),p.getImagen(),p.getStock(),p.getPrecio_venta());
    }
    
    public int getNmr() {
        return nmr;
    }

    public void setNmr(int nmr) {
        this.nmr = nmr;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ProductosCanasta{" + "nmr=" + nmr + ", cantidad=" + cantidad + ", total=" + total + '}' + super.toString();
    }

    
    
}
