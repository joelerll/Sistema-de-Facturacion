package Producto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ProductoCrearController implements Initializable {

     @FXML
    private JFXTextField tfStockProducto;

    @FXML
    private JFXTextField tfPrecioVentaProducto;

    @FXML
    private ImageView ivImagenProducto;

    @FXML
    private JFXTextField tfNombreProducto;
    
    @FXML
    private JFXTextField tfMarcaProducto;
    
    @FXML
    private JFXButton btIngresarProducto;

    @FXML
    private JFXButton btMenuPrincipal;

    @FXML
    private JFXTextField tfCodigoProducto;

    @FXML
    private JFXTextField tfPrecioProducto;

    @FXML
    void IngresarNombreProducto(ActionEvent event) {

    }

    @FXML
    void IngresarCodigoProducto(ActionEvent event) {

    }

    @FXML
    void IngresarPrecioProducto(ActionEvent event) {

    }

    @FXML
    void IngresarPrecioVentaProducto(ActionEvent event) {

    }

    @FXML
    void IngresarStockProducto(ActionEvent event) {

    }
    
    
    @FXML
    void RegresarMenuPrincipal(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/MenuPrincipal/menuPrincipal.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }
    
    @FXML
    void IngresarProducto(ActionEvent event) {
        String codigo = tfCodigoProducto.getText();
        String nombreProducto = tfNombreProducto.getText();
        String Marca = tfMarcaProducto.getText();
        
        if(codigo.equals("")||nombreProducto.equals("")||Marca.equals("")){
            AlertBox.alertBox.crearAlertBox("Alert", null, "Debes completar los campos obligatorios");
        }else{
            if(Producto.buscarProducto(nombreProducto,codigo).isEmpty()){    //Si no existe ese producto con ese codigo en la base de datos
                Producto.ingresarProducto(codigo, nombreProducto, Marca, null, Integer.parseInt(tfStockProducto.getText()), Float.parseFloat(tfPrecioVentaProducto.getText()), Float.parseFloat(tfPrecioProducto.getText()));
                AlertBox.alertBox.crearAlertBox("Confirmation Dialog", null, "Producto ingresado!");
            }
    }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void IngresarMarcaProducto(ActionEvent event) {
    }
    
}
