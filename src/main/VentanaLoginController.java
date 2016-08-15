
package main;

import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class VentanaLoginController implements Initializable {

    public static String usuario;
    String pass;
    
    @FXML
    private JFXPasswordField tfPassword;

    @FXML
    private JFXButton btnCrearUsuario;

    @FXML
    private JFXTextField tfUsuario;

    @FXML
    private JFXButton btnIngresar;

    @FXML
    void ingresarMenuPrincipal(ActionEvent event) throws IOException {
        usuario = tfUsuario.getText();
        pass = tfPassword.getText();
        
        
        
        
        
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/MenuPrincipal/menuPrincipal.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }

    @FXML
    void crearUsuario(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
