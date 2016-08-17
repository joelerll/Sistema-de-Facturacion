
package main;

import com.jfoenix.controls.*;
import empleado.Empleado;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    public static String pass;
    public static Boolean es_Admin;
    
    @FXML
    private JFXPasswordField tfPassword;
    @FXML
    private JFXTextField tfUsuario;
    @FXML
    private JFXButton btnIngresar;

    @FXML
    void ingresarMenuPrincipal(ActionEvent event) throws IOException {
        usuario = tfUsuario.getText();
        pass = tfPassword.getText();
        if(verificarIngreso()){
            verificarAdmin();
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/MenuPrincipal/menuPrincipal.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();
        }
    }

    public void verificarAdmin(){
        List<Empleado> empleados = empleado.Empleado.buscarPorUsuario(usuario);
        if(!empleados.isEmpty()){
            Empleado e = empleados.get(0);
            es_Admin = e.getEes_admin()==1;
        }else{
            es_Admin = false;
        }
    }
    
    public boolean verificarIngreso(){
        if(!usuario.equals("")){
            List<Empleado> empleados = empleado.Empleado.buscarPorUsuario(usuario);
            if(!empleados.isEmpty()){
                Empleado e = empleados.get(0);
                if(e.getEcedula().equals(pass)){
                    System.out.println("Bienvenido al sistema");
                    return true;
                }else{
                    AlertBox.alertBox.crearErrorBox("Error", null, "Usuario y contrasena no coinciden");
                    return false;
                }
            }else{
                AlertBox.alertBox.crearErrorBox("Error", null, "No existe ese usuario en la base de datos");
                return false;
            }
        }else{
            AlertBox.alertBox.crearErrorBox("Error", null, "Ingrese un usuario y contrasena");
            return false;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    
}
