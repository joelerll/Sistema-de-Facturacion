package Cliente;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;


public class ClienteBuscarController implements Initializable {
    @FXML
    private JFXTextField tfNombres;

    @FXML
    private JFXTextField tfDireccion;

    @FXML
    private DatePicker tfFecha;

    @FXML
    private JFXTextField tfCedula;

    @FXML
    private JFXTextField tfEmail;

    @FXML
    private JFXTextField tfConvencional;

    @FXML
    private JFXTextField tfCelular;

    @FXML
    private JFXButton BtnRegresar;

    @FXML
    private JFXButton btnBuscar;

    @FXML
    private JFXTextField tfApellido;

    @FXML
    void regresarMenuPrincipal(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/MenuPrincipal/menuPrincipal.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }

    @FXML
    void buscar(ActionEvent event) {
        Cliente.buscarCliente(tfCedula.getText(), tfNombres.getText(), tfApellido.getText(), tfDireccion.getText(), tfCelular.getText(), tfConvencional.getText(), tfEmail.getText());

    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            }    
    
}
