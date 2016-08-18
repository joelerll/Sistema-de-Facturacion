/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proveedor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DBconnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
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
public class ProveedorTelefonoController implements Initializable {

    //ATRIBUTOS
    private static final DBconnection database=new DBconnection();
    private static Connection con;
    
    //JFXTextField
    @FXML
    private JFXTextField FXTid;
    @FXML
    private JFXTextField FXTtel;
    //JFXButton
    @FXML
    private JFXButton menuButton;
    @FXML
    private JFXButton guardarButton;
    @FXML
    private JFXButton atrasButton;
    
    //METODOS
    @FXML
    void ingresarTelefonoProveedor(ActionEvent event) {
        String id = FXTid.getText();
        String tel = FXTtel.getText();
        
        if(id.equals("")||tel.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Debes completar los campos obligatorios");
            alert.showAndWait();
        }else{
            ProveedorTelefono.ingresarTelefonoProveedor(FXTid.getText(), FXTtel.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Telefono del Proveedor ingresado!");
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
        // TODO
    }
    
    
}
