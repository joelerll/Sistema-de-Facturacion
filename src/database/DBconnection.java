/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
/**
 *
 * @author joelerll
 */
public class DBconnection {
    public Connection conexion;
    private final String url = "jdbc:mysql://localhost:3306/"; //por ser el puerto por defecto
    private final String root= "root";
    private final String password = "199596979899";//<----Ingresar la clase de root
    String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
    
    public Connection conectar() throws SQLException{
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Conectando a la base de datos .............");
            conexion=DriverManager.getConnection(url+"facturacion",root,password);
            System.out.println("--------------------Conectada a la base de datos............");
        }catch(ClassNotFoundException ex){
            System.out.println("-------------No se ha logado la conexion------------");
        }
        return conexion;
    }
}
