package item;

import database.DBconexion;
import database.DBconnection;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author joelerll
 */
public class Item {
    private int id;
    private BigDecimal precio;
    private String nombre;
    private String descripcion;
    private Date fecha;
    
    public Item() {
    }

    public Item(BigDecimal precio, String nombre, String descripcion, Date date) {
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = date;
    }

    public Item(int id, BigDecimal precio, String nombre, String descripcion, Date date) {
        this.id = id;
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = date;
    }
    
    public static List <Item> searchItem(String campo,String nombreBuscar){
        CallableStatement cs = null;
        List <Item>  items=new ArrayList<> ();
        String sql = "{call searchItem(?)}";
        try{
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.setString(1, nombreBuscar);
            cs.executeQuery();
            try(ResultSet rset = cs.getResultSet()){
                 while(rset.next())
                 {
                    Item item = new Item();
                    item.setId(rset.getInt(1));
                    item.setPrecio(rset.getBigDecimal(2));
                    item.setNombre(rset.getString(3));
                    item.setDescripcion(rset.getString(4));
                    item.setFecha(rset.getDate(5));
                    items.add(item);
                 }
            }
            cs.close();
            con.desconetar();
        }catch(SQLException s){
            System.out.println("Error en buscar item para eliminar");
        }
        return items;
    }
    
    public static int eliminarItemSQL(Item item){
        CallableStatement cs = null;
        String sql = "{call  eliminarItemSQL(?)}";
        try{
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.setInt(1, item.getId());
            cs.executeQuery();
            con.desconetar();
            cs.close();
            System.out.println("Borrado el item seleccionado");
        }catch(SQLException s){
            System.out.println("Error al tratar de eliminar item");
            return s.getErrorCode();
        }
        return 0;
    }
    
    public static void editarItemSQL(Item item){
        CallableStatement cs = null;
        String sql = "{call editarItemSQL(?,?,?,?,?)}";
        try{
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.setInt(1, item.getId());
            cs.setBigDecimal(2, item.getPrecio());
            cs.setString(3, item.getNombre());
            cs.setString(4, item.getDescripcion());
            cs.setDate(5, item.getFecha());
            cs.executeQuery();
            cs.close();
            con.desconetar();
            System.out.println("Editado el item seleccionado");
        }catch(SQLException s){
            System.out.println("Error al tratar de editar item");
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    //Modificado para el list box
    @Override
    public String toString() {
        return " NOMBRE "+ nombre + " ID " +  id + "  FECHA " + fecha;
    }
    
    public static List<Item> buscarPorFecha(String year, String month, String day){
        List<Item> resultados = new ArrayList<>();
        CallableStatement cs = null;
        String sql = "{call buscarPorFecha(?)}";
        String fechaB = year + "-" + month;
        try{
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.setString(1, fechaB);
            cs.executeQuery();
            try(ResultSet rset = cs.getResultSet()){
                while (rset.next()) {                    
                    Item item = new Item();
                    item.setId(rset.getInt(1));
                    item.setPrecio(rset.getBigDecimal(2));
                    item.setNombre(rset.getString(3));
                    item.setDescripcion(rset.getString(4));
                    item.setFecha(rset.getDate(5));
                    resultados.add(item);
                }
            }
            con.desconetar();
            cs.close();
        }catch(SQLException s){
            System.out.println("Error en buscar item para eliminar");
        }
        return resultados; 
    }
    
    
    
}
