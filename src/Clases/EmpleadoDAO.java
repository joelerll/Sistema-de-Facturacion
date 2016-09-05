/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import database.DBconexion;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joelerll
 */
public class EmpleadoDAO {
    
    public static List<EmpleadoVO> empleados(){
        CallableStatement cs = null;
        CallableStatement cs2 = null;
        List <EmpleadoVO> empleados = new ArrayList<>();
        String sql = "{call getAllEmpleadorJ(?,?,?,?,?,?,?,?,?,?,?)}";
        String sqlmax = "{call maxEmpleadosJ(?)}";
        int max = 0;
        int count = 0;
        try{
            DBconexion con = new DBconexion();
            cs2 = con.getConnection().prepareCall(sqlmax);
            cs2.executeQuery();
            max = cs2.getInt(1);
            cs2.close();
            cs = con.getConnection().prepareCall(sql);
            while(count < max){
                cs.setInt(1, count);
                cs.executeQuery();
                EmpleadoVO empleado = new EmpleadoVO();
                empleado.setCedula(cs.getString(2));
                empleado.setNombre(cs.getString(3));
                empleado.setApellido(cs.getString(4));
                empleado.setDireccion(cs.getString(5));
                empleado.setFecha_ing(cs.getDate(6));
                empleado.setHorario_ent(cs.getString(7));
                empleado.setHorario_sal(cs.getString(8));
                empleado.setSueldo(cs.getBigDecimal(9));
                empleado.setEs_admin(cs.getInt(10));
                empleado.setTelefono(cs.getString(11));
                empleados.add(empleado);
                count ++;
            }
            cs.close();
            con.desconetar();
        }catch(SQLException e){
            System.out.println("Clases.EmpleadoDAO ERROR");
            return null;
        }
        return empleados;
    }
}
