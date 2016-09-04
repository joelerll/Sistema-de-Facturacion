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
        CallableStatement cs = null;
        String q = "{call buscarClienteJ(?,?,?,?,?,?,?,?,?)}";
        try{
            DBconexion con= new DBconexion();
            //System.out.println(q);
            cs = con.getConnection().prepareCall(q);
            //PreparedStatement ps = con.getConnection().prepareCall(q);
            //ResultSet rs = ps.executeQuery();
            //rs.next();
            cs.setString(1, clienteVO.getCedula_C());
            cs.executeQuery();
            cliente.setCedula_C(cs.getString(2));
            cliente.setFecha_C(cs.getDate(3));
            cliente.setNombre_C(cs.getString(4));
            cliente.setApellido_C(cs.getString(5));
            cliente.setDireccion_C(cs.getString(6));
            cliente.setCelular_C(cs.getString(7));
            cliente.setConvencional_C(cs.getString(8));
            cliente.setEmail_C(cs.getString(8));
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
