
package Producto;

import database.DBconnection;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joelerll
 */
public class Producto {
    private final DBconnection database=new DBconnection();
    private Connection conexion;
    PreparedStatement preparedStatement;
    ResultSet rs;
   
    private String id_Producto;
    private String nombre_Producto;
    private Blob imagen;
    private int stock;
    private float precio_Venta; 
    private float precio_Inicial;

    public Producto(String Id_Producto, String Nombre_Producto, Blob Imagen, int Stock, float Precio_Venta, float Precio_Inicial) {
        this.id_Producto = Id_Producto;
        this.nombre_Producto = Nombre_Producto;
        this.imagen = Imagen;
        this.stock = Stock;
        this.precio_Venta = Precio_Venta;
        this.precio_Inicial = Precio_Inicial;
    }
    
    public void save(Producto p){
        try{
            conexion=database.conectar();
            String query = "INSERT INTO Producto VALUES(?, ?, ?, ?, ?, ?)";
            preparedStatement = conexion.prepareStatement(query);
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
            conexion=database.conectar();
            preparedStatement = conexion.prepareCall("SELECT * FROM Producto");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                p.id_Producto = rs.getString(1);
                p.nombre_Producto = rs.getString(2);
                p.imagen = rs.getBlob(3);
                p.stock= rs.getInt(4);
                p.precio_Venta= rs.getFloat(5);
                p.precio_Inicial= rs.getFloat(6);
            }preparedStatement.close();
            conexion.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("----No cargo-----------");
        }
    }
    
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
    
    
}
