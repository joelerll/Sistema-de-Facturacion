/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import database.DBconexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joelerll
 */
public class EmpleadoDAO {
    
    public static List<EmpleadoVO> empleados(){
        List <EmpleadoVO> empleados = new ArrayList<>();
        String sql = "SELECT cedula, nombre,apellido,direccion,fecha_ing,horario_ent,horario_sal, sueldo, es_admin, telefono FROM empleado";
        try{
            DBconexion con = new DBconexion();
            PreparedStatement pd = con.getConnection().prepareCall(sql);
            ResultSet rs = pd.executeQuery();
            while(rs.next()){
                EmpleadoVO empleado = new EmpleadoVO();
                empleado.setCedula(rs.getString(1));
                empleado.setNombre(rs.getString(2));
                empleado.setApellido(rs.getString(3));
                empleado.setDireccion(rs.getString(4));
                empleado.setFecha_ing(rs.getDate(5));
                empleado.setHorario_ent(rs.getString(6));
                empleado.setHorario_sal(rs.getString(7));
                empleado.setSueldo(rs.getBigDecimal(8));
                empleado.setEs_admin(rs.getInt(9));
                empleado.setTelefono(rs.getString(10));
                empleados.add(empleado);
            }
            con.desconetar();
        }catch(SQLException e){
            System.out.println("Clases.EmpleadoDAO ERROR");
            return null;
        }
        return empleados;
    }
}
