/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author joelerll
 */
public class SQL {
    public DBconnection database=new DBconnection();
    public Connection conexion;
    Statement st;
    public void setEmpleado(String Cedula_Empl, String Nombre_E, String Horario_Ent,String Horario_Sal,int Es_Admin, float Sueldo){
        try{
            conexion=database.conectar();
            String query = "insert into Empleado values (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,Cedula_Empl);
            preparedStatement.setString(2,Nombre_E);
            preparedStatement.setString(3,Horario_Ent);
            preparedStatement.setString(4,Horario_Sal);
            preparedStatement.setInt(5,Es_Admin);
            preparedStatement.setFloat(6,Sueldo);
            preparedStatement.executeUpdate();
        }catch (SQLException ex)
        {
         
            System.out.println("-------No se ha ingresado el Empleado--------");
        }
       
    }
    
}
