/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author joelerll
 */
public class DBconexion {
    public Connection conexion = null;
    private final String url = "jdbc:mysql://localhost:3306/"; //por ser el puerto por defecto
    private final String root= "root";
    private final String password = "260695";//<----Ingresar la clase de root
    String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
    private final String bd = "facturacion";
    public DBconexion () throws SQLException {
         try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("----------------Intentando conectar la base de datos----------------");
            conexion=DriverManager.getConnection(url+bd,root,password);
            if (conexion!=null){
                System.out.println("Conexion a la base de datos " +bd + "  OK\n");
            }
        }catch(ClassNotFoundException ex){
            System.out.println("-------------No se ha logado la conexion------------");
        }
    }
    public Connection getConnection(){
        return conexion;
    }
    
    public void desconetar(){
        System.out.println("Base de datos desconectada");
        conexion = null;
    }
}
