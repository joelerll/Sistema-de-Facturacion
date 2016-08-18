
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
import static ProductoOpciones.ListaProductosController.productosOB;
import Utils.Colores;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 *
 * @author joelerll
 */
public class IngresarController implements Initializable {
    
    /* @FXML
    private TableColumn<ProductoVO, Integer> tableColumnNro;
  */
    /*@FXML
    private TableColumn<ProductoVO, String> tableColumnCodigo;
     */
   /* @FXML
    private TableColumn<ProductoVO, String> tableColumNombre;
    */
   /* @FXML
    private TableColumn<ProductoVO, Integer> tableColumnStock;
    
    @FXML
    private TableColumn<ProductoVO, Integer> tableColumnCantidad;
    
   @FXML
    private TableColumn<ProductoVO, BigDecimal> tableColumnPUnitario;
    
    @FXML
    private TableColumn<ProductoVO, BigDecimal> tableColumnTotal;*/
    
    @FXML
    private JFXCheckBox consumidorFinal;
    
    @FXML
    private JFXTextField clienteCedula,clienteApellido,clienteNombre,codigoProducto,nombreProducto,marcaProducto;
    
    @FXML
    private Label codigoFactura,hora,fecha;

    @FXML
    private JFXComboBox comboBoxEmpleados;
    
    @FXML
    private JFXButton btnCantidad;
    
    @FXML
    private ImageView imagen;
    
    /*@FXML
    private TableView<ProductoVO> tablaProductos;
    */
    @FXML
    private TableView<ProductosCanasta> tablaProductos;
    
    private Date date = new Date();
    private ClienteVO cliente;
    private int siguienteIdFactura;
    private List <ProductoVO> productos;
      
    public static ProductoVO productoEscogido ;
    public static List<ProductoVO> productosCanasta = new ArrayList<>();
    public static List<ProductosCanasta> productosCanastaFactura = new ArrayList<>();
    //private final ObservableList<ProductoVO> productosCanastaObservable = FXCollections.observableArrayList();
    private final ObservableList<ProductosCanasta> productosCanastaObservable = FXCollections.observableArrayList();
    //private static final ObservableList<ProductoVO> productosCanasataObservable = FXCollections.observableArrayList(productosCanasta);
    
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
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Coincidencia Productos");
            stage.setScene(new Scene(root1));
            limpiarCarritoObservable(); //<-------------------------------------------------- Limpia el obseblable
            stage.showAndWait(); //Para que espere y podramos coger los datos que escoge el empleado
            
            // Limpiar las columnas y que no se acumulen 
            limpiarTablasYNoAcumulen();
             
            // Imprime los productos escogidos
            imprimirCarrito();
            setProductosTable();
            
        }catch(Exception e){
            System.out.println(Colores.ANSI_RED + "factura.IngresarController.cargarFxmlProductos ERROR");
        }  
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // elimina la ultima celda por defecto
        tablaProductos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
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
        /*ProductoVO vo = new ProductoVO();
        vo.setId("sadsa");
        productosCanasataObservable.add(vo);
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablaProductos.setItems(productosCanasataObservable);*/
        
        // Setea los nombres de las celdad
        setCeldasSinDatos();
    }
    
    private void setCeldasSinDatos(){
        TableColumn tableColumnNro = new TableColumn("Nro");
        TableColumn tableColumnCodigo = new TableColumn("CODIGO");
        TableColumn tableColumNombre = new TableColumn("NOMBRE");
        TableColumn tableColumnStock = new TableColumn("STOCK");
        TableColumn tableColumnCantidad = new TableColumn("CANTIDAD");
        TableColumn tableColumnPUnitario = new TableColumn("P. UNITARIO");
        TableColumn tableColumnPTotal = new TableColumn("TOTAL");
        tablaProductos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablaProductos.getColumns().addAll(tableColumnNro,tableColumnCodigo,tableColumNombre,tableColumnStock,tableColumnCantidad,tableColumnPUnitario);
    }
    
    public void setProductosTable(){
        int cont = 1; // Coloca el numero de venta del producto
        for (ProductosCanasta p : productosCanastaFactura){
            p.setNmr(cont);
            productosCanastaObservable.add(p);
            cont ++;
        }
        //TableColumn codigo = new TableColumn("Codigo"); 
       // ProductoVO vo = new ProductoVO();
        //vo.setId("sadsa");
        //productosCanastaObservable.add(vo);
       Callback<TableColumn, TableCell> integerCellFactory = new Callback<TableColumn, TableCell>(){
           @Override
           public TableCell call(TableColumn p){
               MyIntegerTableCell cell = new MyIntegerTableCell();
               cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
               return cell;
               
           }
        };
        Callback<TableColumn, TableCell> stringCellFactory = new Callback<TableColumn, TableCell>(){
           @Override
           public TableCell call(TableColumn p){
               MyStringTableCell cell = new MyStringTableCell();
               cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
               return cell;
               
           }
        };
        Callback<TableColumn, TableCell> bigDecimalCellFactory = new Callback<TableColumn, TableCell>(){
           @Override
           public TableCell call(TableColumn p){
               MyBigDecimalTableCell cell = new MyBigDecimalTableCell();
               cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
               return cell;
               
           }
        };
        //tablaProductos.setEditable(true);
        TableColumn tableColumnNro = new TableColumn("Nro");
        tableColumnNro.setCellValueFactory(new PropertyValueFactory<>("nmr"));
        tableColumnNro.setCellFactory(integerCellFactory);
        
        TableColumn tableColumnCodigo = new TableColumn("CODIGO"); 
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnCodigo.setCellFactory(stringCellFactory);
        tableColumnCodigo.onEditCancelProperty();
        
        TableColumn tableColumNombre = new TableColumn("NOMBRE");   
        tableColumNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tableColumNombre.setCellFactory(stringCellFactory);
        
        
        TableColumn tableColumnStock = new TableColumn("STOCK");
        tableColumnStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tableColumnStock.setCellFactory(integerCellFactory);

        TableColumn tableColumnCantidad = new TableColumn("CANTIDAD");
        tableColumnCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tableColumnCantidad.setCellFactory(integerCellFactory);
        
        TableColumn tableColumnPUnitario = new TableColumn("P. UNITARIO");
        tableColumnPUnitario.setCellValueFactory(new PropertyValueFactory<>("precio_venta"));
        tableColumnPUnitario.setCellFactory(bigDecimalCellFactory);
        
        TableColumn tableColumnPTotal = new TableColumn("TOTAL");
        tableColumnPTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tableColumnPTotal.setCellFactory(bigDecimalCellFactory);
        
        tablaProductos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //tableColumNombre.prefWidthProperty().bind(tablaProductos.widthProperty().multiply(0.50));
        tableColumnNro.setMaxWidth( 1f * Integer.MAX_VALUE * 3 );
        tableColumnCodigo.setMaxWidth( 1f * Integer.MAX_VALUE * 15 );
        tableColumNombre.setMaxWidth( 1f * Integer.MAX_VALUE * 35 );
        tableColumnStock.setMaxWidth( 1f * Integer.MAX_VALUE * 5 );
        tableColumnCantidad.setMaxWidth( 1f * Integer.MAX_VALUE * 5 );
        tableColumnPUnitario.setMaxWidth( 1f * Integer.MAX_VALUE * 8 );
        tableColumnPTotal.setMaxWidth( 1f * Integer.MAX_VALUE * 5 );
                
        tablaProductos.setItems(productosCanastaObservable);
        //tablaProductos.getColumns().addAll(tableColumnNro,tableColumnCodigo,tableColumNombre,tableColumnStock,tableColumnCantidad,tableColumnPUnitario,tableColumnPTotal);
        tablaProductos.getColumns().addAll(tableColumnNro,tableColumnCodigo,tableColumNombre,tableColumnStock,tableColumnCantidad,tableColumnPUnitario,tableColumnPTotal);

    }
    
    @FXML
    public void handleButtonBorrar(ActionEvent event){
        limpiarCarritoObservable();
        ProductoVO producto = new ProductoVO();
        producto.setNombre("sadsads");
        //productosCanastaObservable.add(producto);
        //tableColumNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tablaProductos.setItems(productosCanastaObservable);
        System.out.println(productosCanastaFactura);//  <-----------------------------------------
    }
    
   /* @FXML
    public void handleButtonCantidad(ActionEvent event){
        int cantidad = -1;
        System.out.println("\n" + Colores.ANSI_CYAN + "Editar Cantidad");
        cantidad = alertBox.textInputCantidadBox("CANTIDAD", "", "Ingresa la cantidad del producto");
        
        pCanastaEditarBorrar.setCantidad(cantidad);
        System.out.println(pCanastaEditarBorrar);
        limpiarTablasYNoAcumulen();
        limpiarCarritoObservable();
        ///setProductoEditadoCanasta();
        setProductosTable();
    }*/
    
   /* public void setProductoEditadoCanasta(){
        int index = 0;
        if (productosCanastaFactura.isEmpty()){
        }else{
            for (ProductosCanasta p : productosCanastaFactura){
            if (pCanastaEditarBorrar.getId() == p.getId()){
               System.out.println(productosCanastaFactura.indexOf(p));                
               System.out.println(productosCanastaFactura.size());
               productosCanastaFactura.remove(p);
                System.out.println(productosCanastaFactura.size());
               //imprimirCarrito();
                //productosCanastaFactura.add(p);
            }
            index ++;
            }
        } 
    }*/
        
    public void limpiarTablasYNoAcumulen(){
        tablaProductos.getColumns().clear();
    }
    
    private void imprimirCarrito(){
        System.out.println("\n"+ Colores.ANSI_BLUE + "Productos agregados a carrito");
        for (ProductosCanasta p : productosCanastaFactura){
            System.out.println(p);
        }
        System.out.println("\n");
    }
    
    private void limpiarCarritoObservable(){
        productosCanastaObservable.clear();
    }

    class MyStringTableCell extends TableCell<ProductosCanasta,String>{
        @Override
        public void updateItem(String item, boolean empty){
            super.updateItem(item,empty);
            setText(empty ? null : getString());
            setGraphic(null);
        }
        
        private String getString(){
            return getItem() == null ? "" : getItem().toString();
        }
    }
    
    class MyIntegerTableCell extends TableCell<ProductosCanasta,Integer>{
        @Override
        public void updateItem(Integer item, boolean empty){
            super.updateItem(item,empty);
            setText(empty ? null : getString());
            setGraphic(null);
        }
        
        private String getString(){
            return getItem() == null ? "" : getItem().toString();
        }
    }
    
    class MyBigDecimalTableCell extends TableCell<ProductosCanasta,BigDecimal>{
        @Override
        public void updateItem(BigDecimal item, boolean empty){
            super.updateItem(item,empty);
            setText(empty ? null : getString());
            setGraphic(null);
        }
        
        private String getString(){
            return getItem() == null ? "" : getItem().toString();
        }
    }
    
    class MyEventHandler implements EventHandler<MouseEvent >{
        @Override
        public void handle(MouseEvent t){
            TableCell c = (TableCell) t.getSource();
            int index = c.getIndex();
            
            System.out.println("\n" + Colores.ANSI_GREEN + "Producto escogido Para Borrar o Ingresar Cantidad");
            System.out.println(productosCanastaFactura.get(index).getId());
            //System.out.println(productosOB.get(index).toString());

            // Agregar un producto a carrito
            btnCantidad.setOnAction(new EventHandlerImpl(index));
            
            // Ingresar imagen y ver 
          /* try{
                imagen.setVisible(true);
                Image im = new Image(productosOB.get(index).getImagen());
                imagen.setImage(im);
            }catch(Exception e){
                imagen.setVisible(false);
                System.out.println(Colores.ANSI_RED + "No se encontro imagen");
            } */   
        }

        private class EventHandlerImpl implements EventHandler<ActionEvent> {
            
            private final int index;
            
            public EventHandlerImpl(int index) {
                this.index = index;
            }

            @Override
            public void handle(ActionEvent e) {
                int cantidad = -1;
                System.out.println("\n" + Colores.ANSI_CYAN + "Editar Cantidad");
                cantidad = alertBox.textInputCantidadBox("CANTIDAD", "", "Ingresa la cantidad del producto");
                for (ProductosCanasta po : productosCanastaFactura){
                    if (productosCanastaFactura.get(index).getId() == po.getId() && productosCanastaFactura!= null && !productosCanastaFactura.isEmpty()){
                       productosCanastaFactura.get(productosCanastaFactura.indexOf(po)).setCantidad(cantidad);
                       limpiarTablasYNoAcumulen();
                       limpiarCarritoObservable();
                       setProductosTable();
                    }
                    
                }
                
            }
        }
    }
}

