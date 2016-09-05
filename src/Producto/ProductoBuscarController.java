
package Producto;

import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ProductoBuscarController implements Initializable {

    @FXML
    private Button btBuscarProducto;

    @FXML
    private Button btEditarProducto;

    @FXML
    private JFXTextField tfNombreProductoEncontrar;

    @FXML
    private Button btEliminarProducto;

    @FXML
    private JFXTextField tfNombreProductoBuscar;

    @FXML
    private JFXTextField tfCodigoProductoEncontrar;

    @FXML
    private Button btMenuPrincipal;

    @FXML
    private JFXTextField tfCodigoProductoBuscar;
    
    @FXML
    private Label lblActual;
    List<Producto> listaProductos;
    
    @FXML
    private Label lbltotal;
    String codigoOriginal;
    private int index;

    @FXML
    void NombrePorductoBuscar(ActionEvent event) {

    }

    @FXML
    void CodigoProductoBuscar(ActionEvent event) {

    }

    @FXML
    void NombrePorductoEncontrar(ActionEvent event) {

    }

    @FXML
    void CodigoProductoEncontrar(ActionEvent event) {

    }
     
    @FXML
    void BuscarProducto(ActionEvent event) {
        listaProductos = Producto.buscarProducto(tfNombreProductoBuscar.getText(), tfCodigoProductoBuscar.getText());
        index = 0;
        if(!listaProductos.isEmpty()){      //si devolvio una coincidencia en la BD
            Producto p = listaProductos.get(0);
            tfNombreProductoEncontrar.setText(p.getNombre_Producto());
            tfCodigoProductoEncontrar.setText(p.getId_Producto());
            int i = index + 1;
            lblActual.setText(""+i);
            lbltotal.setText(""+listaProductos.size()); 
           // enableTextFields();
        }else{
            AlertBox.alertBox.crearAlertBox("Information Dialog", null, "No se encontraron coincidencias");
        }
        codigoOriginal = tfCodigoProductoEncontrar.getText();
    }
    
    @FXML
    void EditarProducto(ActionEvent event) {
        if(revisarEdicion()){
            if(Producto.editarProducto(codigoOriginal,tfNombreProductoEncontrar.getText(),tfCodigoProductoEncontrar.getText()))
            {
                AlertBox.alertBox.crearAlertBox("Confirmation Dialog", null, "Producto actualizado");
            }
        }
    }
    
    @FXML
    void EliminarProducto(ActionEvent event) {
        if(Producto.eliminarProducto(tfCodigoProductoEncontrar.getText())){
            AlertBox.alertBox.crearAlertBox("Information Dialog", null, "Producto eliminado");
        }else{
            AlertBox.alertBox.crearAlertBox("Warning Dialog", null, "No se pudo eliminar");
        }
        
        if(listaProductos.size()==1){        //SI SOLO HABIA UN PRODUCTO ENCONTRADO, AL ELIMINARLO YA NO SE DEBE MOSTRAR NADA EN LA VISTA PREVIA
            //Encero todos los valores
            tfCodigoProductoEncontrar.setText(null);
            tfNombreProductoEncontrar.setText(null);
        }else{                              //SI HABIAN MAS PRODUCTOS QUE COINCIDIAN CON EL CRITERIO DE BUSQUEDA, AL ELIMINAR EL PRODUCTO SELECCIONADO SE DEBEN MOSTRAR LOS QUE QUEDAN
            listaProductos.remove(index);        //SE ELIMINA EL PRODUCTO ELIMINADO DE LA LISTA
            //SE VUELVE A PRESENTAR TODO COMO ANTES SIN EL CLIENTE ELIMINADO
            index = 0;
            Producto p = listaProductos.get(0);
            tfNombreProductoEncontrar.setText(p.getNombre_Producto());
            tfCodigoProductoEncontrar.setText(p.getId_Producto());
            int i = index + 1;
            lblActual.setText(""+i);
            lbltotal.setText(""+listaProductos.size()); 
            
            codigoOriginal = p.getId_Producto();
        }
    }
    
    @FXML
    void MenuPrincipal(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/MenuPrincipal/menuPrincipal.fxml"));
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
    
    private void enableTextFields() {
       tfNombreProductoEncontrar.setDisable(false);
       tfCodigoProductoEncontrar.setDisable(false);
    }
    
    private boolean revisarEdicion() {
        //System.out.println(Producto.buscarProducto("",tfCodigoProductoEncontrar.getText()).size());
        if(Producto.buscarProducto("",tfCodigoProductoEncontrar.getText()).size()>1){      //Busca en la BD si existe otro registro con el mismo codigo ingresada en el TextField
            //Tiene que ser >1 porque al buscar en la BD, si va a encontrar una coincidencia.
            AlertBox.alertBox.crearAlertBox("Warning Dialog", null, "Ya existe otro producto con ese codigo!");
            return false;
        }
        return true;
    }
    
}
