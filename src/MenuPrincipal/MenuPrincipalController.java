
package MenuPrincipal;

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
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


public class MenuPrincipalController implements Initializable {
        @FXML
    private MenuBar mnuBar;    
    @FXML
    private MenuItem mItmCrearCliente;
    @FXML
    private MenuItem mItmCrearEmpleado;
    @FXML
    private JFXButton btnLogin;
    
    @FXML
    void accederFacturacion(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/factura/facturacionMenu.fxml"));
        Scene scene = new Scene(home_page_parent);
        Stage stage = (Stage) mnuBar.getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void regresarMenuLogin(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/main/VentanaLogin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }
    
    @FXML
    void accederItem(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/item/itemOpciones.fxml"));
        Scene scene = new Scene(home_page_parent);
        Stage stage = (Stage) mnuBar.getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show(); 
    }
    
    @FXML
    void accederEstadisticas(ActionEvent event) throws IOException {
        //System.out.println("holaaa");
        if(main.VentanaLoginController.es_Admin){
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Estadisticas/estadisticas.fxml"));
            Scene scene = new Scene(home_page_parent);
            Stage stage = (Stage) mnuBar.getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();  
        }else{
            AlertBox.alertBox.crearErrorBox("Error", null, "No es administrador. No tiene permisos para acceder a esta informacion!");
        } 
    }
    
        @FXML
    void crearCliente(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Cliente/clienteCrear.fxml"));
        Scene scene = new Scene(home_page_parent);
        Stage stage = (Stage) mnuBar.getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void crearEmpleado(ActionEvent event) throws IOException {
        if(main.VentanaLoginController.es_Admin){
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/empleado/IngresarEmpleado.fxml"));
            Scene scene = new Scene(home_page_parent);
            Stage stage = (Stage) mnuBar.getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();  
        }else{
            AlertBox.alertBox.crearErrorBox("Error", null, "No es administrador. No tiene permisos para acceder a esta informacion!");
        } 
    }

    @FXML
    void ingresarGasto(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/item/IngresarItem.fxml"));
        Scene scene = new Scene(home_page_parent);
        Stage stage = (Stage) mnuBar.getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    void crearProveedor(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/proveedor/IngresarProveedor.fxml"));
        Scene scene = new Scene(home_page_parent);
        Stage stage = (Stage) mnuBar.getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show(); 
    }
    
        @FXML
    void buscarCliente(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Cliente/clienteBuscar.fxml"));
        Scene scene = new Scene(home_page_parent);
        Stage stage = (Stage) mnuBar.getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void buscarEmpleado(ActionEvent event) throws IOException {
        if(main.VentanaLoginController.es_Admin){
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/empleado/BuscarEmpleado.fxml"));
            Scene scene = new Scene(home_page_parent);
            Stage stage = (Stage) mnuBar.getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show(); 
        }else{
            AlertBox.alertBox.crearErrorBox("Error", null, "No es administrador. No tiene permisos para acceder a esta informacion!");
        }
    }

    @FXML
    void buscarGasto(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/item/EliminarItem.fxml"));
        Scene scene = new Scene(home_page_parent);
        Stage stage = (Stage) mnuBar.getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void buscarProveedor(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/proveedor/BuscarProveedor.fxml"));
        Scene scene = new Scene(home_page_parent);
        Stage stage = (Stage) mnuBar.getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show(); 
    }
    
    @FXML
    void buscarProducto(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Producto/ProductoBuscar.fxml"));
        Scene scene = new Scene(home_page_parent);
        Stage stage = (Stage) mnuBar.getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }
        
    @FXML
    void crearProducto(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Producto/ProductoCrear.fxml"));
        Scene scene = new Scene(home_page_parent);
        Stage stage = (Stage) mnuBar.getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
