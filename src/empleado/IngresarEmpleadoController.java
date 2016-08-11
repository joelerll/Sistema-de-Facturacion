/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleado;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import database.DBconnection;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fernando
 */
public class IngresarEmpleadoController implements Initializable {

    //ATRIBUTOS
    private static final DBconnection database=new DBconnection();
    private static Connection con;
    
    //JFXTextField
    @FXML
    private JFXTextField FXEcedula;
    @FXML
    private JFXTextField FXEnombre;
    @FXML
    private JFXTextField FXEapellido;
    @FXML
    private JFXTextField FXEdir;
    @FXML
    private JFXTextField FXEsueldo;
    @FXML
    private JFXTextField FXEhora_ent;
    @FXML
    private JFXTextField FXEhora_sal;
    @FXML
    private DatePicker FXEfecha_ing;
    @FXML
    private JFXTextField FXEes_admin;
    @FXML
    private JFXTextField FXEtelefono;
    
    //JFXButton
    @FXML
    private JFXButton menuButton;
    @FXML
    private JFXButton ingresarButton;
    
    //METODOS
    @FXML
    void ingresarEmpleado(ActionEvent event) {
       /* String cedula = FXEcedula.getText();
        String nombre = FXEnombre.getText();
        String apellido = FXEapellido.getText();
        String dir = FXEdir.getText();
        Date fecha_ing = (Date) FXEfecha_ing.getDayCellFactory();
        String hora_ent = FXEhora_ent.getText();
        String hora_sal = FXEhora_sal.getText();
        BigDecimal sueldo = new BigDecimal(FXEsueldo.getText());
        Integer es_admin = Integer.parseInt(FXEes_admin.getText());
        String telefono = FXEtelefono.getText();
        
        
        if(cedula.equals("")||nombre.equals("")||apellido.equals("")||dir.equals("")||hora_ent.equals("")||hora_sal.equals("")||telefono.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Debes completar los campos obligatorios");
            alert.showAndWait();
        }else{
            empleado.Empleado.ingresarEmpleado(cedula, nombre, apellido, dir, fecha_ing, hora_ent, hora_sal, sueldo, es_admin, telefono);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Empleado ingresado!");
            alert.showAndWait();
        }*/
    }
    
    @FXML
    void menuPrincipal(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/MenuPrincipal/menuPrincipal.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        RequiredFieldValidator validatorCedula = new RequiredFieldValidator();
        RequiredFieldValidator validatorNombre = new RequiredFieldValidator();
        RequiredFieldValidator validatorApellido = new RequiredFieldValidator();
        RequiredFieldValidator validatorDir = new RequiredFieldValidator();
      //RequiredFieldValidator validatorFecha_Ing = new RequiredFieldValidator();
        RequiredFieldValidator validatorHora_Ent = new RequiredFieldValidator();
        RequiredFieldValidator validatorHora_Sal = new RequiredFieldValidator();
        RequiredFieldValidator validatorSueldo = new RequiredFieldValidator();
        RequiredFieldValidator validatorEs_Admin = new RequiredFieldValidator();
        RequiredFieldValidator validatorTelefono = new RequiredFieldValidator();
        
        
        FXEcedula.getValidators().add(validatorCedula);
        validatorCedula.setMessage("Campo Obligatorio");
        FXEcedula.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                FXEcedula.validate();
            }
        });
        
        FXEnombre.getValidators().add(validatorNombre);
        validatorNombre.setMessage("Campo Obligatorio");
        FXEnombre.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                FXEnombre.validate();
            }
        });
        
        FXEapellido.getValidators().add(validatorApellido);
        validatorApellido.setMessage("Campo Obligatorio");
        FXEapellido.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                FXEapellido.validate();
            }
        });
        
        FXEdir.getValidators().add(validatorDir);
        validatorDir.setMessage("Campo Obligatorio");
        FXEdir.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                FXEdir.validate();
            }
        });
        
        FXEhora_ent.getValidators().add(validatorHora_Ent);
        validatorHora_Ent.setMessage("Campo Obligatorio");
        FXEhora_ent.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                FXEhora_ent.validate();
            }
        });
        
        FXEhora_sal.getValidators().add(validatorHora_Sal);
        validatorHora_Sal.setMessage("Campo Obligatorio");
        FXEhora_sal.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                FXEhora_sal.validate();
            }
        });
        
        FXEsueldo.getValidators().add(validatorSueldo);
        validatorSueldo.setMessage("Campo Obligatorio");
        FXEsueldo.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                FXEsueldo.validate();
            }
        });
        
        FXEes_admin.getValidators().add(validatorEs_Admin);
        validatorEs_Admin.setMessage("Campo Obligatorio");
        FXEes_admin.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                FXEes_admin.validate();
            }
        });
        
        FXEtelefono.getValidators().add(validatorTelefono);
        validatorTelefono.setMessage("Campo Obligatorio");
        FXEtelefono.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                FXEtelefono.validate();
            }
        });*/
    }    
    
}
