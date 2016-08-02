/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factura;

import Producto.Producto;
import database.DBconnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joelerll
 */
public class Factura {
    private final DBconnection database=new DBconnection();
    private Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    private String Id_Orden;
    private String Tipo;
    private float Valor;
    private Date Fecha;
    private String Cedula_C;
    private String Cedula_Empl;

    public Factura(String Id_Orden, String Tipo, float Valor, Date Fecha, String Cedula_C, String Cedula_Empl) {
        this.Id_Orden = Id_Orden;
        this.Tipo = Tipo;
        this.Valor = Valor;
        this.Fecha = Fecha;
        this.Cedula_C = Cedula_C;
        this.Cedula_Empl = Cedula_Empl;
    }
    
    public Factura()
    {
        
    }
    
     public void save(Factura f){
        try{
            con=database.conectar();
            String query = "INSERT INTO Factura VALUES(?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1,f.getId_Orden());
            ps.setString(2,f.Tipo);
            ps.setFloat(3,f.Valor);
            ps.setDate(4,f.Fecha);
            ps.setString(5,f.Cedula_C);
            ps.setString(6,f.Cedula_Empl);
            ps.executeUpdate();
        }catch (SQLException ex)
        {
            System.out.println("-------Error al ingresar Producto--------");
        }
    }
    
   /* public void view(Factura f)
    {
        try {
            con=database.conectar();
            ps = con.prepareCall("SELECT * FROM Factura");
            rs = ps.executeQuery();
            while (rs.next()) {
                f.Id_Orden = rs.getString(1);
                f.Tipo = rs.getString(2);
                f.Valor = rs.getFloat(3);
                f.Fecha= rs.getDate(4);
                f.Cedula_C= rs.getString(5);
                f.Cedula_Empl= rs.getString(6);
            }ps.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("----No cargo-----------");
        }
    }*/
    
    public Factura Idview(String id)
    {
        Factura f=new Factura();
        try {
            con=database.conectar();
            ps = con.prepareCall("SELECT * FROM Factura");
            rs = ps.executeQuery();
            while (rs.next()) {
                f.Id_Orden = rs.getString(1);
                f.Tipo = rs.getString(2);
                f.Valor = rs.getFloat(3);
                f.Fecha= rs.getDate(4);
                f.Cedula_C= rs.getString(5);
                f.Cedula_Empl= rs.getString(6);
                if (id.equals(f.Id_Orden))
                {
                    return f;
                }
            }ps.close();
            con.close();
            rs.close();
            
        } catch (SQLException ex) {
            System.out.println("----No cargo-----------");
        }
        return null;
    }
    
 /*   public Factura Last()
    {
        Factura f=new Factura();
        try {
            con=database.conectar();
            ps = con.prepareCall("SELECT * FROM Factura ORDER BY Id_Orden DESC LIMIT 1");
            rs = ps.executeQuery();
            rs.next();
            f.Id_Orden = rs.getString(1);
            f.Tipo = rs.getString(2);
            f.Valor = rs.getFloat(3);
            f.Fecha= rs.getDate(4);
            f.Cedula_C= rs.getString(5);
            f.Cedula_Empl= rs.getString(6);
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("----No cargo Last-----------");
        }
        return f;
    }*/
     
     

    public String getId_Orden() {
        return Id_Orden;
    }

    public void setId_Orden(String Id_Orden) {
        this.Id_Orden = Id_Orden;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public float getValor() {
        return Valor;
    }

    public void setValor(float Valor) {
        this.Valor = Valor;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getCedula_C() {
        return Cedula_C;
    }

    public void setCedula_C(String Cedula_C) {
        this.Cedula_C = Cedula_C;
    }

    public String getCedula_Empl() {
        return Cedula_Empl;
    }

    public void setCedula_Empl(String Cedula_Empl) {
        this.Cedula_Empl = Cedula_Empl;
    }
    
    
    
}
