/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import database.DBconexion;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joelerll
 */
public class ClienteDAO {
    
    /**
     *
     * @param clienteVO
     * @param arg
     */
    public static ClienteVO buscarCliente(ClienteVO clienteVO,String... arg){
        ClienteVO cliente = new ClienteVO();
        String q = "SELECT * from "+ "cliente" + " WHERE "+ arg[0] +  " = '"+clienteVO.getCedula_C()+"'";
        try{
            DBconexion con= new DBconexion();
            System.out.println(q);
            PreparedStatement ps = con.getConnection().prepareCall(q);
            ResultSet rs = ps.executeQuery();
            rs.next();
            cliente.setCedula_C(rs.getString(1));
            cliente.setFecha_C(rs.getDate(2));
            cliente.setNombre_C(rs.getString(3));
            cliente.setApellido_C(rs.getString(4));
            cliente.setDireccion_C(rs.getString(5));
            cliente.setCelular_C(rs.getString(6));
            cliente.setConvencional_C(rs.getString(7));
            cliente.setEmail_C(rs.getString(8));
            con.desconetar();
        }catch(SQLException e){
            System.out.println("ClienteDAO.buscarPesona ERROR");
            return null;  
        }
        return cliente;
    }
    
    public static String buscarId(String id){
        String nombre = null;
        CallableStatement cs = null;
        String sql = "{call getCliente_nombre_completo(?,?)}";
        try {
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.setString(1, id);
            cs.executeQuery();
            nombre = cs.getString(2);
            cs.close();
            con.desconetar();
        } catch (SQLException e) {
            System.out.println("ERROR ClienteDAO.buscarID");
        }
        return nombre;
    }
}
