package Clases;


import java.math.BigDecimal;
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joelerll
 */
public class FacturaVO {
    private int id;
    private  BigDecimal valor;
    private Timestamp fecha;
    private String Cedula_C;
    private String Cedula_empl;
    private boolean anulada;
    private Timestamp actualizada;

    public FacturaVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getCedula_C() {
        return Cedula_C;
    }

    public void setCedula_C(String Cedula_C) {
        this.Cedula_C = Cedula_C;
    }

    public String getCedula_empl() {
        return Cedula_empl;
    }

    public void setCedula_empl(String Cedula_empl) {
        this.Cedula_empl = Cedula_empl;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public Timestamp getActualizada() {
        return actualizada;
    }

    public void setActualizada(Timestamp actualizada) {
        this.actualizada = actualizada;
    }
    
    
}
