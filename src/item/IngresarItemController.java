/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.prism.impl.Disposer.Record;
import database.DBconnection;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
public class IngresarItemController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton btnAtras;

    @FXML
    private JFXTextField textFieldNombre;

    @FXML
    private JFXButton btnGuardar;

    @FXML
    private JFXTextField textFieldPrecio;

    @FXML
    private DatePicker datePickerFecha;

    @FXML
    private JFXTextField textFieldDescripcion;
    
    @FXML
    private Label labelError;
    @FXML
    void atras(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/item/itemOpciones.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }
    
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
    
    @FXML
    private void guardar(ActionEvent event) {
        
        String nombre = textFieldNombre.getCharacters().toString();
        String descripcion = textFieldDescripcion.getCharacters().toString();
        LocalDate date = datePickerFecha.getValue();
        BigDecimal precio = null;
        try{
            precio = new BigDecimal(textFieldPrecio.getCharacters().toString());
            System.out.println("Guardando items"
                + "Nombre : " + nombre +"\n"
                + "Descricion: " + descripcion + "\n"
                + "Precio: " + precio + "\n"
                + "Fecha: " + date );
        }catch (Exception excepcion){
            System.out.println("No ingreso todos los datos");
            labelError.setText("No ingreso precio");
        }
        if ("".equals(nombre) || "".equals(descripcion) || date == null){
            labelError.setText("Ingrese todos los datos");
        }else
        {
            try{
                conexion = database.conectar();
                String query = "INSERT INTO item (precio,nombre,descripcion,fecha) VALUES(?, ?, ?, ?)";
                PreparedStatement preparedStatement = conexion.prepareStatement(query);
                preparedStatement.setBigDecimal(1, precio);
                preparedStatement.setString(2,nombre.toUpperCase());
                preparedStatement.setString(3,descripcion.toUpperCase());
                preparedStatement.setDate(4,java.sql.Date.valueOf(date));
                preparedStatement.executeUpdate();
                System.out.println("Item exitosamente ingresado");
            }catch (SQLException ex)
            {
                System.out.println("-------No se ha ingresado el Item--------");
            }
        }
        
                
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
