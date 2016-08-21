
package Cliente;

import database.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author joelerll
 */
public class Cliente {
    //ATRIBUTOS
    private static final DBconnection database=new DBconnection();
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    
    private String Cedula_C;
    private String Fecha_C;
    private String Nombre_C;
    private String Apellido_C;
    private String Direccion_C;
    private String Celular_C;
    private String Convencional_C;
    private String Email_C;

    //METODOS
    
    public static List <Cliente> searchClientesByName(String name){
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
    
    public static List<String> nombresClientes(List <Cliente> clientes){
        List<String> nombres= new ArrayList<String>();
        String nombre;
        for(Cliente cliente: clientes)
        {
            nombres.add(cliente.getNombre_C());
        }
        return nombres;
    }
    //GETTERS AND SETTERS
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

    public String getApellido_C() {
        return Apellido_C;
    }

    public void setApellido_C(String Apellido_C) {
        this.Apellido_C = Apellido_C;
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

    public String getFecha_C() {
        return Fecha_C;
    }

    public void setFecha_C(String Fecha_C) {
        this.Fecha_C = Fecha_C;
    }
    //METODOS DE LA BASE DE DATOS
    public static boolean revisarEmail(String email){
        if(email.equals("")||email.equals(null)){
            System.out.println("Email vacio");
            return true;
        }
        ArrayList<String> listaEmail = new ArrayList<String>();
        listaEmail.add("hotmail.com");
        listaEmail.add("gmail.com");
        listaEmail.add("espol.edu.ec");
        listaEmail.add("live.com");
        if(email.contains("@")){
           String [] parts = email.split("@");
            for(String s:listaEmail){
                if(s.equals(parts[1])){
                    return true;
                }
            } 
        }
        return false;
    }
    
    
    public static void ingresarCliente(String cedula, String fecha, String nombre, String apellido, String dir, String cel, String telf, String email){
        try{
            con=database.conectar();
            //INSERT INTO cliente VALUES(cedula, fecha, nombre, apellido, direccion, celular, convencional, email)
            String query = "INSERT INTO Cliente VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,cedula);
            preparedStatement.setString(2,fecha);
            preparedStatement.setString(3,nombre);
            preparedStatement.setString(4, apellido);
            preparedStatement.setString(5, dir);
            preparedStatement.setString(6, cel);
            preparedStatement.setString(7, telf);
            preparedStatement.setString(8, email);
            preparedStatement.executeUpdate();
            System.out.println("Se ingreso el cliente");
        }catch (SQLException ex)
        {
            System.out.println("No se ingreso el cliente");
        }
    }
    
    public static List <Cliente> buscarCliente(String cedula, String fecha, String nombre, String apellido, String dir, String cel, String telf, String email){
        String query = "SELECT * FROM Cliente WHERE ";
        String query2 = "";
        List <Cliente>  listaClientes=new ArrayList<> ();
        
        if(!cedula.equals("")){
            query+="Cedula_C = '"+cedula+"' AND ";
        }
        if(!fecha.equals("")){
            query+="Fecha_C = '"+fecha+"' AND ";
        }
        if(!nombre.equals("")){
            query+="Nombre_C = '"+nombre+"' AND ";
        }
        if(!apellido.equals("")){
            query+="Apellido_C = '"+apellido+"' AND ";
        }
        if(!dir.equals("")){
            query+="Direccion_C = '"+dir+"' AND ";
        }
        if(!cel.equals("")){
            query+="Celular_C = '"+cel+"' AND ";
        }
        if(!telf.equals("")){
            query+="Convencional_C = '"+telf+"' AND ";
        }
        if(!email.equals("")){
            query+="Email_C = '"+email+"' AND ";
        }
        query2 = query.substring(0, query.length()-4);
        try {
            con=database.conectar();
            ps = con.prepareCall(query2);
            rs = ps.executeQuery();
            while (rs.next()){
                Cliente cliente=new Cliente();
                cliente.setCedula_C(rs.getString(1));
                cliente.setFecha_C(rs.getString(2));
                cliente.setNombre_C(rs.getString(3));
                cliente.setApellido_C(rs.getString(4));
                cliente.setDireccion_C (rs.getString(5));
                cliente.setCelular_C(rs.getString(6));
                cliente.setConvencional_C(rs.getString(7));
                cliente.setEmail_C(rs.getString(8));
                listaClientes.add(cliente);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClientes;
    }
    
    public static boolean eliminarCliente(String cedula){
        String query = "DELETE FROM Cliente WHERE Cedula_C = '" + cedula + "'";
        try {
            con = database.conectar();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("Se elimino el cliente!");
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se elimino el cliente!");
            return false;
        }finally{
            return true;
        }
    }
    public static boolean editarCliente(String cedulaOriginal, String cedula, String fecha, String nombre, String apellido, String dir, String cel, String telf, String email){
        String query = "UPDATE cliente SET ";
        query+="Cedula_C = '"+cedula+"', ";
        query+="Fecha_C = '"+fecha+"', ";
        query+="Nombre_C = '"+nombre+"', ";
        query+="Apellido_C = '"+apellido+"', ";
        query+="Direccion_C = '"+dir+"', ";
        query+="Celular_C = '"+cel+"', ";
        query+="Convencional_C = '"+telf+"', ";
        query+="Email_C = '"+email+"' ";
        query+= "WHERE Cedula_C = '"+cedulaOriginal+"'";
        
        try {
            con = database.conectar();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            return true;
        }
    }
    
    
}
