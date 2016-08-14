
package Cliente;

import com.jfoenix.controls.*;
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

import javafx.stage.Stage;


public class ClienteCrearController implements Initializable {
    //ATRIBUTOS PARA LA BASE DE DATOS
    private static final DBconnection database=new DBconnection();
    private static Connection con;
    //ATRIBUTOS DE LA VENTANA
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
    //METODOS
    @FXML
    void ingresarCliente(ActionEvent event) {
        String cedula = tfCedula.getText();
        String nombre = tfNombres.getText();
        String apellido = tfApellidos.getText();
        
        if(cedula.equals("")||nombre.equals("")||apellido.equals("")||cedula.equals(null)||nombre.equals(null)||apellido.equals(null)){              //VALIDA QUE LOS CAMPOS OBLIGATORIOS ESTEN LLENOS
            AlertBox.alertBox.crearAlertBox("Information Dialog", null, "Debes ingresar los campos obligatorios");
        }
        else{
            System.out.println("Su email es: " + tfEmail.getText());
            if(!Cliente.revisarEmail(tfEmail.getText())){                           //VALIDA QUE EL EMAIL SEA VALIDO
                
                AlertBox.alertBox.crearAlertBox("Warning Dialog", null,"No es un email valido");
            }
            else{
                if(Cliente.buscarCliente(cedula, "", "", "", "", "", "").isEmpty()){    //Si no existe ese cliente con esa cedula en la base de datos
                    Cliente.ingresarCliente(tfCedula.getText(), tfNombres.getText(), tfApellidos.getText(), tfDireccion.getText(), tfCelular.getText(), tfConvencional.getText(), tfEmail.getText());
                    AlertBox.alertBox.crearAlertBox("Information Dialog", null, "Cliente creado");
                    encerarTF();
                }
                else{
                    AlertBox.alertBox.crearAlertBox("Information Dialog", null, "Ya existe un cliente con esa cedula");
                }
            }
        }
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
    
    
    public void encerarTF(){
        tfCedula.setText("");
        tfNombres.setText("");
        tfApellidos.setText("");
        tfDireccion.setText("");
        tfCelular.setText("");
        tfConvencional.setText("");
        tfEmail.setText("");
        tfFechaRegistro.setText("");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnIngresar.setDefaultButton(true);
        RequiredFieldValidator validatorCedula = new RequiredFieldValidator();
        RequiredFieldValidator validatorNombre = new RequiredFieldValidator();
        RequiredFieldValidator validatorApellido = new RequiredFieldValidator();
        
        tfCedula.getValidators().add(validatorCedula);
        validatorCedula.setMessage("Campo Obligatorio");
        tfCedula.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                tfCedula.validate();
            }
        });
        
        tfNombres.getValidators().add(validatorNombre);
        validatorNombre.setMessage("Campo Obligatorio");
        tfNombres.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                tfNombres.validate();
            }
        });
        
        tfApellidos.getValidators().add(validatorApellido);
        validatorApellido.setMessage("Campo Obligatorio");
        tfApellidos.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                tfApellidos.validate();
            }
        });
    }    
    
}
