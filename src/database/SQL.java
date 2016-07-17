/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author joelerll
 */
public class SQL {
    public DBconnection database=new DBconnection();
    public Connection conexion;
    public void setEmpleado(String Cedula_Empl, String Nombre_E, String Horario_Ent,String Horario_Sal,int Es_Admin, float Sueldo){
        try{
            conexion=database.conectar();
            String query = "INSERT INTO Empleado VALUES(?, ?, ?, ?, ?, ?)";
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
    public void setCliente(String Cedula, String Nombre, String Direccion){
        try{
            conexion=database.conectar();
            String query = "INSERT INTO Cliente VALUES(?, ?, ?)";

            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,Cedula);
            preparedStatement.setString(2,Nombre);
            preparedStatement.setString(3,Direccion);
            preparedStatement.executeUpdate();
        }catch (SQLException ex)
        {
            System.out.println("-------Error al ingresar cliente--------");
        }
    }
    public void setFactura(String Id_Orden, String Tipo, float Valor,Date Fecha,String Cedula_C, String Cedula_Emp){
        try{
            conexion=database.conectar();
            String query = "INSERT INTO Factura VALUES(?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,Id_Orden);
            preparedStatement.setString(2,Tipo);
            preparedStatement.setFloat(3,Valor);
            preparedStatement.setDate(4,Fecha);
            preparedStatement.setString(5,Cedula_C);
            preparedStatement.setString(6,Cedula_Emp);
            preparedStatement.executeUpdate();
        }catch (SQLException ex)
        {
            System.out.println("-------Error al ingresar Factura--------");
        }
    }
    public void setGastos(String ID_Gasto, Date Fecha,float Total,String Cedula_Empleado){
        try{
            conexion=database.conectar();
            String query = "INSERT INTO Gastos VALUES(?, ?, ?, ?)";

            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,ID_Gasto);
            preparedStatement.setDate(2,Fecha);
            preparedStatement.setFloat(3,Total);
            preparedStatement.setString(4,Cedula_Empleado);
            preparedStatement.executeUpdate();
        }catch (SQLException ex)
        {
            System.out.println("-------No se ha ingresado el Empleado--------");
        }
    }
    public void setGrupos(String Id_Grupo, String Nombre_G, String Descripcion){
        try{
            conexion=database.conectar();
            String query = "INSERT INTO Grupos VALUES(?, ?, ?)";

            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,Id_Grupo);
            preparedStatement.setString(2,Nombre_G);
            preparedStatement.setString(3,Descripcion);
            preparedStatement.executeUpdate();
        }catch (SQLException ex)
        {
            System.out.println("-------error al ingresar grupos--------");
        }
    }
    public void setItem(String Id_Item, float Precio, String Nombre,String Descripcion){
        try{
            conexion=database.conectar();
            String query = "INSERT INTO Item VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,Id_Item);
            preparedStatement.setFloat(2,Precio);
            preparedStatement.setString(3,Nombre);
            preparedStatement.setString(4,Descripcion);
            preparedStatement.executeUpdate();
        }catch (SQLException ex)
        {
            System.out.println("-------Error al Ingresar Item--------");
        }
    }
    public void setItem_gastos(String Id_ITG, String Item, String Id_Gasto){
        try{
            conexion=database.conectar();
            String query = "INSERT INTO Item_Gastos VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1,Id_ITG);
            preparedStatement.setString(2,Item);
            preparedStatement.setString(3,Id_Gasto);
            preparedStatement.executeUpdate();
        }catch (SQLException ex)
        {
            System.out.println("-------Error al ingresar Item_Gastos--------");
        }
    }
   
}
