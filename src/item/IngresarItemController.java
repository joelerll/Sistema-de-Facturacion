/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import database.DBconnection;
import java.io.IOException;
import java.math.BigDecimal;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private JFXDatePicker datePickerFecha;

    @FXML
    private JFXTextField textFieldDescripcion;
    
    @FXML
    private Label labelError;
    
    @FXML
    private JFXCheckBox checkBoxEmpleado;
    
    @FXML
    private JFXTextField textFieldCedula;
    
    public DBconnection database=new DBconnection();
    public Connection conexion;
    
    @FXML
    void atras(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/MenuPrincipal/menuPrincipal.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }

    public void setEmpleado(String Cedula_Empl, String Nombre_E, String Horario_Ent,String Horario_Sal,int Es_Admin, float Sueldo){
        try{
            conexion=database.conectar();
            String query = "INSERT INTO empleado VALUES(?, ?, ?, ?, ?, ?)";
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
        String cedula = textFieldCedula.getCharacters().toString();
        String nombre = textFieldNombre.getCharacters().toString();
        String descripcion = textFieldDescripcion.getCharacters().toString();
        LocalDate date = datePickerFecha.getValue();
        BigDecimal precio = null;
        boolean bandera = true;
        int b = 0;
        if (checkBoxEmpleado.isSelected()){
            System.out.println("Ingreso de item empleado");
            cedula = buscarCedulaEmpleado(textFieldCedula.getText());
            if (cedula == null){
                errorWindow("Empleado","No se encontro el empleado");
                bandera = false;
            }
        }
        //del ingreo de empleado
        if (bandera){
             try{
            precio = new BigDecimal(textFieldPrecio.getCharacters().toString());
            System.out.println("Guardando items"
                + "Nombre : " + nombre +"\n"
                + "Descricion: " + descripcion + "\n"
                + "Precio: " + precio + "\n"
                + "Fecha: " + date );
            }catch (Exception excepcion){
                System.out.println("No ingreso todos los datos");
                alertWindow("Item","No ingreso precio");
                b = 1;
            }
            if ("".equals(nombre) || "".equals(descripcion) || date == null){
                errorWindow("Item","Ingrese todos los datos");
                b = 1;
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
                    alertWindow("Item","Item exitosamente ingresado");
                    textFieldPrecio.setText("");
                    textFieldDescripcion.setText("");
                    //datePickerFecha.setValue('2');
                }catch (SQLException ex)
                {
                    System.out.println("-------No se ha ingresado el Item--------");
                    System.out.println("Error database:" + ex.getErrorCode());
                    b = 1;
                }
            }
        }
        if (checkBoxEmpleado.isSelected() && cedula!=null && b ==0){
            ingresarItem_empleado(buscarUltimoItem(),cedula);
            textFieldCedula.setText("");
            cedula = null;
        }
       
    }
    
    private String buscarCedulaEmpleado(String cedula){
        String ced = null;
        DBconnection database=new DBconnection();
        Connection conexion;
        PreparedStatement ps;
        ResultSet rs = null;
        String c = null;
        try{
            conexion = database.conectar();
            String q ="SELECT cedula FROM empleado WHERE "+ "cedula" +" LIKE ('%"+cedula+"%')";
            System.out.println(q);
            ps = conexion.prepareCall(q);
            rs = ps.executeQuery();
            while (rs.next()) {
                c = rs.getString(1);
                System.out.println(c);
            }
            ps.close();
            conexion.close();
            rs.close();
            ced = c;
        }catch(SQLException sql){
            System.out.println("Error en buscar cedula emplpeado");
            return null;
        }
        return ced;
    }
    
    //set e la tabla item_empleado id_item y cedula empl
    private int buscarUltimoItem()
    {
        //select max(id) from item;
        DBconnection database=new DBconnection();
        Connection conexion;
        PreparedStatement ps;
        ResultSet rs = null;
        int itemx=0;
        try{
            conexion = database.conectar();
            String q ="SELECT max(id) FROM item";
            System.out.println(q);
            ps = conexion.prepareCall(q);
            rs = ps.executeQuery();
            while (rs.next()) {
                itemx = rs.getInt(1);
                System.out.println(itemx);
            }
            ps.close();
            conexion.close();
            rs.close();
        }catch(SQLException sql){
            System.out.println("Error en buscar item_empleado");
            return itemx = 0;
        }
        return itemx;
    }
    
    private void ingresarItem_empleado(int id_item,String cedula){
        DBconnection database=new DBconnection();
        Connection conexion;
        PreparedStatement ps;
        String q ="INSERT INTO  item_empleado (id_item,cedula_empl) VALUES (" + id_item  
                    + ",'"+cedula+"')";
       System.out.println(q);
        try{
            conexion = database.conectar();
            ps = conexion.prepareStatement(q);
            ps.execute();
            conexion.close();
            System.out.println("ingresado el item_empleado");
            alertWindow("Emplado","Ingresado el item empleado");
        }catch(SQLException sql){
            System.out.println("Item_empleado no ingresado");
            errorWindow("Emplado","NO Ingresado el item empleado");
        }
    }
    
    @FXML
    public void checkBoxEmpleado(ActionEvent event){
        if (checkBoxEmpleado.isSelected()){
            textFieldCedula.setDisable(false);
            textFieldNombre.setText("SUELDO");
            textFieldNombre.setDisable(true);
        }else{
            textFieldCedula.setDisable(true);
            textFieldNombre.setText("");
            textFieldNombre.setDisable(false);
        }
        
    }
    
    private Alert confirmationWindow(String title, String mensaje)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        return alert;
    }
    
    public void alertWindow(String title, String mensaje)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }
    
    public void errorWindow(String title, String mensaje)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }            
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldCedula.setDisable(true);
    }    
    
    
}
