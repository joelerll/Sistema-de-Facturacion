/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proveedor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import database.DBconnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fernando
 */
public class IngresarProveedorController implements Initializable {
    
    //ATRIBUTOS
    private static final DBconnection database=new DBconnection();
    private static Connection con;
    
    //JFXTextField
    @FXML
    private JFXTextField FXPid;
    @FXML
    private JFXTextField FXPnombre;
    @FXML
    private JFXTextField FXPdir;
    //JFXButton
    @FXML
    private JFXButton menuButton;
    @FXML
    private JFXButton ingresarButton;
    @FXML
    private JFXButton atrasButton;
    //METODOS
    @FXML
    void ingresarProveedor(ActionEvent event) {
        String id = FXPid.getText();
        String nombre = FXPnombre.getText();
        String dir = FXPdir.getText();
        
        if(id.equals("")||nombre.equals("")||dir.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Debes completar los campos obligatorios");
            alert.showAndWait();
        }else{
            Proveedor.ingresarProveedor(Integer.parseInt(FXPid.getText()), FXPnombre.getText().toUpperCase(), FXPdir.getText().toUpperCase());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Proveedor ingresado!");
            alert.showAndWait();
        }
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
    
    @FXML
    void atras(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/proveedor/ProveedorOpciones.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RequiredFieldValidator validatorId = new RequiredFieldValidator();
        RequiredFieldValidator validatorNombre = new RequiredFieldValidator();
        RequiredFieldValidator validatorDir = new RequiredFieldValidator();
        
        FXPid.getValidators().add(validatorId);
        validatorId.setMessage("Campo Obligatorio");
        FXPid.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                FXPid.validate();
            }
        });
        
        FXPnombre.getValidators().add(validatorNombre);
        validatorNombre.setMessage("Campo Obligatorio");
        FXPnombre.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                FXPnombre.validate();
            }
        });
        
        FXPdir.getValidators().add(validatorDir);
        validatorDir.setMessage("Campo Obligatorio");
        FXPdir.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                FXPdir.validate();
            }
        });  
    }    
   
}
