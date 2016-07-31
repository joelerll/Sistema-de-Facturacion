
package Cliente;

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


public class ClienteCrearController implements Initializable {

    @FXML
    private JFXTextField tfNombres;

    @FXML
    private JFXTextField tfDireccion;

    @FXML
    private JFXButton btnMenuPrincipal;

    @FXML
    private JFXTextField tfApellidos;

    @FXML
    private JFXTextField tfConvencional;

    @FXML
    private JFXTextField tfEmail;

    @FXML
    private JFXButton btnIngresar;

    @FXML
    private JFXTextField tfCedula;

    @FXML
    private JFXTextField tfCelular;

    @FXML
    private JFXTextField tfFechaRegistro;

    @FXML
    void ingresarCliente(ActionEvent event) {

    }

    @FXML
    void regresarMenuPrincipal(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/MenuPrincipal/menuPrincipal.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
