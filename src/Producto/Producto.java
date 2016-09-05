
package Producto;

import database.DBconnection;
import java.sql.Blob;
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
public class Producto {
    //ATRIBUTOS PARA LA CONEXION
    private static final DBconnection database=new DBconnection();
    private static Connection con;
    PreparedStatement preparedStatement;
    public static ResultSet rs;
    private static PreparedStatement ps;
    //ATRIBUTOS DE LA CLASE
    private String id_Producto;
    private String nombre_Producto;
    private String marca;
    private Blob imagen;
    private int stock;
    private float precio_Venta; 
    private float precio_Inicial;
    //CONSTRUCTORES
    public Producto(String Id_Producto, String Nombre_Producto, Blob Imagen, int Stock, float Precio_Venta, float Precio_Inicial) {
        this.id_Producto = Id_Producto;
        this.nombre_Producto = Nombre_Producto;
        this.imagen = Imagen;
        this.stock = Stock;
        this.precio_Venta = Precio_Venta;
        this.precio_Inicial = Precio_Inicial;
    }
    
    private Producto() {
        this.id_Producto = null;
        this.nombre_Producto = null;
        this.marca=null;
        this.imagen = null;
        this.stock = 0;
        this.precio_Venta = 0;
        this.precio_Inicial = 0;
    }
    
    public void save(Producto p){
        try{
            con=database.conectar();
            String query = "INSERT INTO producto VALUES(?, ?, ?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,p.getId_Producto());
            preparedStatement.setString(2,p.getNombre_Producto());
            preparedStatement.setBlob(3,p.getImagen());
            preparedStatement.setInt(4,p.getStock());
            preparedStatement.setFloat(5,p.getPrecio_Venta());
            preparedStatement.setFloat(6,p.getPrecio_Inicial());
            preparedStatement.executeUpdate();
        }catch (SQLException ex)
        {
            System.out.println("-------Error al ingresar Producto--------");
        }
    }
    
    public void view(Producto p)
    {
        try {
            con=database.conectar();
            preparedStatement = con.prepareCall("SELECT * FROM Producto");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                p.id_Producto = rs.getString(1);
                p.nombre_Producto = rs.getString(2);
                p.imagen = rs.getBlob(3);
                p.stock= rs.getInt(4);
                p.precio_Venta= rs.getFloat(5);
                p.precio_Inicial= rs.getFloat(6);
            }preparedStatement.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("----No cargo-----------");
        }
    }
    //GETTERS Y SETTERS
    public String getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(String Id_Producto) {
        this.id_Producto = Id_Producto;
    }

    public String getNombre_Producto() {
        return nombre_Producto;
    }

    public void setNombre_Producto(String Nombre_Producto) {
        this.nombre_Producto = Nombre_Producto;
    }

    public Blob getImagen() {
        return imagen;
    }

    public void setImagen(Blob Imagen) {
        this.imagen = Imagen;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int Stock) {
        this.stock = Stock;
    }

    public float getPrecio_Venta() {
        return precio_Venta;
    }

    public void setPrecio_Venta(float Precio_Venta) {
        this.precio_Venta = Precio_Venta;
    }

    public float getPrecio_Inicial() {
        return precio_Inicial;
    }

    public void setPrecio_Inicial(float Precio_Inicial) {
        this.precio_Inicial = Precio_Inicial;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    //METODOS DE QUERIES
    
    public static void ingresarProducto(String id,String nombre,String marca, Blob Imagen, int Stock, float Precio_Venta, float Precio_Inicial){
        try{
            con=database.conectar();
            String query = "INSERT INTO producto VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,null);
            
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,nombre);
            preparedStatement.setString(3,marca);
            preparedStatement.setBlob(4,Imagen);
            preparedStatement.setInt(5,Stock);
            preparedStatement.setFloat(6,Precio_Venta);
            preparedStatement.setFloat(7,Precio_Inicial);
            preparedStatement.executeUpdate();
        }catch (SQLException ex)
        {
            System.out.println("-------Error al ingresar Producto--------");
        }
    }
    
    public static List <Producto> buscarProducto(String nombre, String id) {
        String query = "SELECT * FROM producto WHERE ";
        String query2 = "";
        List <Producto>  listaProductos=new ArrayList<> ();
        
        if(!id.equals("")){     //SI EL ID NO ESTA VACIO QUE BUSQUE POR ID  
            query+="id = '"+id+"' AND ";
        }
        if(!nombre.equals("")){ //SI EL NOMBRE NO ESTA VACIO QUE BUSQUE POR EL NOMBRE
            query+="nombre = '"+nombre+"' AND ";
        }
        query2 = query.substring(0, query.length()-4);  //QUITAR LOS AND
        try {
            con=database.conectar();
            ps = con.prepareCall(query2);
            rs = ps.executeQuery();
            while (rs.next()){
                Producto producto=new Producto();
                producto.setId_Producto(rs.getString(1));
                producto.setNombre_Producto(rs.getString(2));
                listaProductos.add(producto);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProductos;
    }
    
    public static boolean eliminarProducto(String Id){
        String query = "DELETE FROM producto WHERE id = '" + Id + "'";
        try {
            con = database.conectar();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("Se elimino el producto!");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se elimino el Producto!");
            return false;
        }
    }
    
    public static boolean editarProducto(String IdOriginal, String Nombre, String Id){
        String query = "UPDATE producto SET "+"id = '"+Id+"', "+"nombre = '"+Nombre+"', " +"WHERE id = '"+IdOriginal+"'";
        try {
            con = database.conectar();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
