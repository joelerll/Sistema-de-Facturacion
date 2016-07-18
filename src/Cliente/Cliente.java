/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import database.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joelerll
 */
public class Cliente {
    private static final DBconnection database=new DBconnection();
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    
    private String Cedula_C;
    private String Nombre_C;
    private String Direccion_C;

    public Cliente(String Cedula_C, String Nombre_C, String Direccion_C) {
        this.Cedula_C = Cedula_C;
        this.Nombre_C = Nombre_C;
        this.Direccion_C = Direccion_C;
    }
    
    public static List <Cliente> searchClientesByName(String name)
    {
        List <Cliente>  clientes=new ArrayList<> ();
        
        String patron=String.format("");
        String query=String.format("\"SELECT Nombre_C FROM Cliente WHERE Nombre_C REGEXP "+"'"+"(?i)"+name+"'"+"\"");
        String query2=String.format("\"SELECT * FROM Cliente\"");
        try {
            con=database.conectar();
            ps = con.prepareCall(" SELECT * FROM Cliente WHERE Nombre_C REGEXP'"+name+"'");
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente=new Cliente();
                cliente.setCedula_C(rs.getString(1));
                cliente.setNombre_C(rs.getString(2));
                cliente.setDireccion_C (rs.getString(3));
                clientes.add(cliente);
            }ps.close();
            con.close();
            rs.close();
            return clientes;
        } catch (SQLException ex) {
            System.out.println("----No cargo Cliente-----------");
        }
        return clientes;
    }
    
    public static List<String> nombresClientes(List <Cliente> clientes)
    {
        List<String> nombres= new ArrayList<String>();
        String nombre;
        for(Cliente cliente: clientes)
        {
            nombres.add(cliente.getNombre_C());
        }
        return nombres;
    }

    public Cliente() {
    }

    public String getCedula_C() {
        return Cedula_C;
    }

    public void setCedula_C(String Cedula_C) {
        this.Cedula_C = Cedula_C;
    }

    public String getNombre_C() {
        return Nombre_C;
    }

    public void setNombre_C(String Nombre_C) {
        this.Nombre_C = Nombre_C;
    }

    public String getDireccion_C() {
        return Direccion_C;
    }

    public void setDireccion_C(String Direccion_C) {
        this.Direccion_C = Direccion_C;
    }
    
    
}
