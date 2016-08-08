/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DBconnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joelerll
 */
public class EliminarItemController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private JFXButton btnAtras;

    @FXML
    private JFXTextField TexfFielNombre;

    @FXML
    private JFXTextField textFieldPrecio;

    @FXML
    private DatePicker datePickerFecha;

    @FXML
    private JFXTextField textFieldBuscarNombre;

    @FXML
    private JFXTextField textFieldDescripcion;

    @FXML
    private DatePicker datePickerBuscarFecha;

    @FXML
    private JFXButton btnBuscar;
    
    @FXML
    private JFXTextField textFieldFecha;
    
    @FXML
    private Label labelMensaje;
    
    public DBconnection database=new DBconnection();
    public Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    
    @FXML
    //atras de menu
    void atras(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/item/itemOpciones.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }
    
    //busca item por nomreb y campo
    public Item buscarItem(String campo,String nombreBuscar){
        Item item = new Item();
        if ("".equals(nombreBuscar)){
            item.setDescripcion("NN");
            return item;
        }
        try{
            conexion = database.conectar();
            String q ="SELECT * FROM item WHERE "+ campo +" LIKE ('%"+nombreBuscar+"%')"+"LIMIT 1";
            System.out.println(q);
            ps = conexion.prepareCall(q);
            rs = ps.executeQuery();
            rs.next();
            item.setId(rs.getInt(1));
            item.setPrecio(rs.getBigDecimal(2));
            item.setNombre(rs.getString(3));
            item.setDescripcion(rs.getString(4));
            item.setFecha(rs.getDate(5));
            System.out.println("Encontrado item\n"+item.toString());
        }catch(SQLException sql){
            System.out.println("Error en buscar item para eliminar");
        }
        return item;
    }
     
    public void buscarItemNombre(ActionEvent event){
        String campo = "nombre";
        String nombreBuscar= textFieldBuscarNombre.getCharacters().toString().toUpperCase();
        if ("".equals(nombreBuscar)){
            labelMensaje.setText("No ingreso nombre");
        }else{
            Item item= buscarItem(campo,nombreBuscar);
            TexfFielNombre.setText(item.getNombre());
            textFieldDescripcion.setText(item.getDescripcion());
            textFieldPrecio.setText(item.getPrecio().toString());
            textFieldFecha.setText(item.getFecha().toString());
        }
        
    }
    
    public void eliminarItem(ActionEvent event){
        
    }
     
          /*   public Factura Last()
    {
        Factura f=new Factura();
        try {
            con=database.conectar();
            ps = con.prepareCall("SELECT * FROM Factura ORDER BY Id_Orden DESC LIMIT 1");
            rs = ps.executeQuery();
            rs.next();
            f.Id_Orden = rs.getString(1);
            f.Tipo = rs.getString(2);
            f.Valor = rs.getFloat(3);
            f.Fecha= rs.getDate(4);
            f.Cedula_C= rs.getString(5);
            f.Cedula_Empl= rs.getString(6);
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("----No cargo Last-----------");
        }
        return f;
    }*/
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
