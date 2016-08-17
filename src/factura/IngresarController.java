
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factura;


import AlertBox.alertBox;
import Clases.ClienteDAO;
import Clases.ClienteVO;
import Clases.EmpleadoDAO;
import Clases.FacturaDAO;
import Clases.ProductoDAO;
import Clases.ProductoVO;
import Cliente.Cliente;
import ProductoOpciones.ListProductos;
import ProductoOpciones.ListaProductosController;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author joelerll
 */
public class IngresarController implements Initializable {
    
    @FXML
    private JFXCheckBox consumidorFinal;
    
    @FXML
    private JFXTextField clienteCedula,clienteApellido,clienteNombre,codigoProducto,nombreProducto,marcaProducto;
    
    @FXML
    private Label codigoFactura,hora,fecha;
    
    @FXML
    private JFXComboBox comboBoxEmpleados;
    
    @FXML
    private ImageView imagen;
    
    private Date date = new Date();
    private ClienteVO cliente;
    private int siguienteIdFactura;
    
    @FXML
    void regresarMenuFacturacion(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/factura/facturacionMenu.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }
    
    /*
    Check Box a consumidor final, 
    */
    @FXML
    private void consumidorFinalCheck(ActionEvent event) {
        boolean selected = consumidorFinal.isSelected();
        if(selected){
            clienteNombre.setText("Consumidor Final");
            clienteApellido.setText("");
            clienteCedula.setText("XXXXXXXXXXXXXXXX");
            clienteNombre.setDisable(true);
            clienteCedula.setDisable(true);
            clienteApellido.setDisable(true);
        }else{
            clienteNombre.setText("");
            clienteCedula.setText("");
            clienteApellido.setText("");
            clienteCedula.setDisable(false);
        }        
    }
    
    /*
    Buscar cliente
    Verificar que los datos ingresados de cliente existan
    */
    @FXML
    private void handleButtonBuscarCliente(ActionEvent event){
       ClienteVO cliente;
       cliente = ClienteDAO.buscarCliente(new ClienteVO(clienteCedula.getText()),"Cedula_C");
       if (cliente != null){
           clienteNombre.setText(cliente.getNombre_C());
           clienteApellido.setText(cliente.getApellido_C());
           clienteCedula.setText(cliente.getCedula_C());
           this.cliente = cliente;
       }else{
           alertBox.crearErrorBox("","","No fue encontrado el cliente");
       }
    }
    
    
    /*
    Buscar cliente, si no llevarlo a ingresar cliente
    */
    
    /*
    Buscar vendedor
    */
    
    /*
    Setear los datos de la factura
    */
    
    /*
    Imprimir factura
    */
    
    /*
    Crear una nueva ventana al darle el boton buscar y seleccinar el producto que se desea
    */
    List <ProductoVO> productos;
    @FXML
    public void handleButtonBuscarProducto(ActionEvent event)
    {
        ProductoVO producto = new ProductoVO();
        producto.setId(codigoProducto.getText().toUpperCase());
        producto.setNombre(nombreProducto.getText().toUpperCase());
        producto.setMarca(marcaProducto.getText().toUpperCase());
        
        productos = ProductoDAO.buscarProductoFacturaFormato(producto.getId(),producto.getNombre(),producto.getMarca());
        if (productos == null){
            alertBox.crearErrorBox("","", "No ingreso ningun dato para buscar");
        }else{
            
            //ListaProductosController productospass = new ListaProductosController(productos);
            //productospass.setProductos(productos);
            //ListaProductosController productospass = new ListaProductosController(productos);
            ListaProductosController.productosOB = productos;
            cargarFxmlProductos();
            
            //productospass.setProductos();
            //ListProductos.Productos(productos);
        }
    }
    
    public static List<ProductoVO> Productos(List <ProductoVO> productos)
    {
        return productos;
    }
    
    public void cargarFxmlProductos()
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ProductoOpciones/ListaProductos.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Coincidencia Productos");
            stage.setScene(new Scene(root1));  
            stage.show();    
            }catch(Exception e){
                System.out.println("factura.IngresarController.cargarFxmlProductos ERROR");
            }  
    }
    
    /*
    Eliminar producto de la tabla
    */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Desabilitar los textField nombre y apellido de cliente, por solo busqueda por cedula
        clienteNombre.setDisable(true);
        clienteApellido.setDisable(true);
        
        //Set fecha label fecha
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	fecha.setText(dateFormat.format(date));
        
        // Combo box todos los empleados
        comboBoxEmpleados.setValue(EmpleadoDAO.empleados().get(0));
        comboBoxEmpleados.getItems().addAll(EmpleadoDAO.empleados());
        
        //Set factura ID siguiente del ultimo ingresado
        siguienteIdFactura = FacturaDAO.getIdLast()+1;
        codigoFactura.setText(""+siguienteIdFactura);
        /*
        ProductoVO producto;
        producto = ProductoDAO.buscarProducto();
        System.out.println(producto);
        System.out.println(producto.getImagen());
        Image im = new Image(producto.getImagen());
        
        imagen.setImage(im);
        */
        
        
    }    
 
}
