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
import database.DBconexion;
import database.DBconnection;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
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
                CallableStatement cs = null;
                try{
                    String sql = "{call guardarItem(?,?,?,?)}";
                    DBconexion con = new DBconexion();
                    cs = con.getConnection().prepareCall(sql);
                    cs.setBigDecimal(1, precio);
                    cs.setString(2,nombre.toUpperCase());
                    cs.setString(3,descripcion.toUpperCase());
                    cs.setDate(4,java.sql.Date.valueOf(date));
                    cs.executeQuery();
                    con.desconetar();
                    System.out.println("Item exitosamente ingresado");
                    alertWindow("Item","Item exitosamente ingresado");
                    textFieldPrecio.setText("");
                    textFieldDescripcion.setText("");
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
        CallableStatement cs = null;
        String sql = "{call buscarCedulaEmpleado(?,?)}";
        try{
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.setString(1, cedula);
            cs.executeQuery();
            ced = cs.getString(2);
            cs.close();
            con.desconetar();
        }catch(SQLException s){
            System.out.println("Error en buscar cedula emplpeado");
            return null;
        }
        return ced;
    }
    
    //set e la tabla item_empleado id_item y cedula empl
    private int buscarUltimoItem()
    {
        int itemx=0;
        CallableStatement cs = null;
        String sql = "{call buscarUltimoItem(?)}";
        try{
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.executeQuery();
            itemx = cs.getInt(1);
            System.out.println(itemx);
            cs.close();
            con.desconetar();
        }catch(SQLException s){
            System.out.println("Error en buscar item_empleado");
            return itemx = 0;
        }
        return itemx;
    }
    
    private void ingresarItem_empleado(int id_item,String cedula){
        CallableStatement cs = null;
        String sql = "{call ingresarItem_empleado(?,?)}";
        try{
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.setInt(1, id_item);
            cs.setString(2, cedula);
            cs.executeQuery();
            System.out.println("ingresado el item_empleado");
            alertWindow("Emplado","Ingresado el item empleado");
        }catch(SQLException s){
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
