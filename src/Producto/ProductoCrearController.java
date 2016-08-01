/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Producto;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ediso
 */
public class ProductoCrearController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private JFXTextField tfStockProducto;

    @FXML
    private JFXTextField tfPrecioVentaProducto;

    @FXML
    private ImageView ivImagenProdcuto;

    @FXML
    private JFXTextField tfNombreProducto;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
