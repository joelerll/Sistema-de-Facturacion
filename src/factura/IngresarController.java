
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
import ProductoOpciones.ListaProductosController;
import Utils.Colores;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.paint.Color;
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
    private List <ProductoVO> productos;
        
    public static ProductoVO productoEscogido;
    public static List<ProductoVO> productosCanasta = new ArrayList<>();
    
    
    @FXML
    void regresarMenuFacturacion(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/factura/facturacionMenu.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }

    // CheckBox para setear si es cosumidor final
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

    @FXML
    public void handleButtonBuscarProducto(ActionEvent event)
    {
        ProductoVO producto = new ProductoVO();
        producto.setId(codigoProducto.getText().toUpperCase());
        producto.setNombre(nombreProducto.getText().toUpperCase());
        producto.setMarca(marcaProducto.getText().toUpperCase());
        
        productos = ProductoDAO.buscarProductoFacturaFormato(producto.getId(),producto.getNombre(),producto.getMarca());
        if (productos == null){
            alertBox.crearErrorBox(" ","", "No ingreso ningun dato para buscar");
        }else if(productos.isEmpty()){
            alertBox.crearAlertBox(" ", "" , "Ningun Producto Encontrado" );
        }else{
            // Para la lista de concidencias a ListaProductosController
            ListaProductosController.productosOB = productos;
            cargarFxmlProductos();
        }
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
            stage.showAndWait(); //Para que espere y podramos coger los datos que escoge el empleado
            
            // Imprime los productos escogidos
            imprimirCarrito();
            
        }catch(Exception e){
            System.out.println(Colores.ANSI_RED + "factura.IngresarController.cargarFxmlProductos ERROR");
        }  
    }
    
    private void imprimirCarrito(){
        System.out.println("\n"+ Colores.ANSI_BLUE + "Productos agregados a carrito");
        for (ProductoVO p : productosCanasta){
            System.out.println(p.getId());
        }
        System.out.println("\n");
    }
    
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
