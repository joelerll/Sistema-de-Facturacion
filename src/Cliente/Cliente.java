
package Cliente;

import database.DBconnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    //CONSTRUCTOR

    public Cliente(){
        this.Cedula_C = null;
        this.Fecha_C = null;
        this.Nombre_C = null;
        this.Apellido_C = null;
        this.Direccion_C = null;
        this.Celular_C = null;
        this.Convencional_C = null;
        this.Email_C = null;
    }
    
    public Cliente(String Cedula_C, String Fecha_C, String Nombre_C, String Apellido_C, String Direccion_C, String Celular_C, String Convencional_C, String Email_C) {
        this.Cedula_C = Cedula_C;
        this.Fecha_C = Fecha_C;
        this.Nombre_C = Nombre_C;
        this.Apellido_C = Apellido_C;
        this.Direccion_C = Direccion_C;
        this.Celular_C = Celular_C;
        this.Convencional_C = Convencional_C;
        this.Email_C = Email_C;
    }
    
    
    //METODOS
    
    public static List <Cliente> searchClientesByName(String name){
        List <Cliente>  clientes=new ArrayList<> ();
        
        String patron=String.format("");
        String query=String.format("\"SELECT Nombre_C FROM cliente WHERE Nombre_C REGEXP "+"'"+"(?i)"+name+"'"+"\"");
        String query2=String.format("\"SELECT * FROM cliente\"");
        try {
            con=database.conectar();
            ps = con.prepareCall(" SELECT * FROM cliente WHERE Nombre_C REGEXP'"+name+"'");
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente= new Cliente();
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
    public static List <Cliente> buscarCliente(String cedula, String fecha, String nombre, String apellido, String dir, String cel, String telf, String email){
        String query = "SELECT * FROM cliente WHERE ";
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
    
    public static void ingresarCliente2(Cliente cliente){
        try {
            con=database.conectar();
            CallableStatement proc = con.prepareCall("{ call insertar_cliente(?, ?, ?, ?, ?, ?, ?, ?) }");
            proc.setString(1, cliente.getCedula_C());
            proc.setString(2, cliente.getFecha_C());
            proc.setString(3, cliente.getNombre_C());
            proc.setString(4, cliente.getApellido_C());
            proc.setString(5, cliente.getDireccion_C());
            proc.setString(6, cliente.getCelular_C());
            proc.setString(7, cliente.getConvencional_C());
            proc.setString(8, cliente.getEmail_C());
            proc.execute();
            System.out.println("Cliente ingresado a la base de datos");
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean eliminarCliente2(String cedula){
        try {
            con=database.conectar();
            CallableStatement procedure = null;
            procedure = con.prepareCall("{ call eliminar_cliente(?) }");
            procedure.setString(1, cedula);
            procedure.execute();
            System.out.println("Cliente eliminado de la base de datos");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean editarCliente2(String cedulaOriginal, Cliente cliente){
        try {
            con=database.conectar();
            CallableStatement procedure = null;
            procedure = con.prepareCall("{ call editar_cliente(?, ?, ?, ?, ?, ?, ?, ?, ?) }");
            procedure.setString(1, cliente.getCedula_C());
            procedure.setString(2, cliente.getFecha_C());
            procedure.setString(3, cliente.getNombre_C());
            procedure.setString(4, cliente.getApellido_C());
            procedure.setString(5, cliente.getDireccion_C());
            procedure.setString(6, cliente.getCelular_C());
            procedure.setString(7, cliente.getConvencional_C());
            procedure.setString(8, cliente.getEmail_C());
            procedure.setString(9, cedulaOriginal);
            procedure.execute();
            System.out.println("Cliente editado en la base de datos");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
