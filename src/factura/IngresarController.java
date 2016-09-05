
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
import Clases.EmpleadoVO;
import Clases.FacturaDAO;
import Clases.ProductoDAO;
import Clases.ProductoVO;
import ProductoOpciones.ListaProductosController;
import Utils.Colores;
import Utils.PDF;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 *
 * @author joelerll
 */
public class IngresarController implements Initializable {
    
    @FXML
    private JFXComboBox<EmpleadoVO> comboBoxEmpleados;
    
    @FXML
    private JFXCheckBox consumidorFinal;
    
    @FXML
    private JFXTextField clienteCedula,clienteApellido,clienteNombre,codigoProducto,nombreProducto,marcaProducto;
    
    @FXML
    private Label codigoFactura,fecha;
    
    @FXML
    private JFXButton btnCantidad;

    @FXML
    private TableView<ProductosCanasta> tablaProductos;
    
    @FXML
    private Text textSubtotal,textIva, textTotal;
    
    @FXML
    private JFXButton btnBorrarProducto;
    
    private Date date = new Date();
    private ClienteVO cliente;
    private BigDecimal subtotal;
    private int siguienteIdFactura;
    private List <ProductoVO> productos = null;
    public static ProductoVO productoEscogido ;
    public static List<ProductoVO> productosCanasta = new ArrayList<>();
    public static List<ProductosCanasta> productosCanastaFactura = new ArrayList<>();
    private final ObservableList<ProductosCanasta> productosCanastaObservable = FXCollections.observableArrayList();
    
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
        
        // Setea los nombres de las celdad
        setCeldasSinDatos();
        
        productosCanastaFactura.clear();
    }
    
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
            cliente = null;
            cliente = new ClienteVO();
            clienteNombre.setText("Consumidor");
            clienteApellido.setText("Final");
            clienteCedula.setText("XXXXXXX");
            clienteNombre.setDisable(true);
            clienteCedula.setDisable(true);
            clienteApellido.setDisable(true);
            cliente.setNombre_C("Consumidor");
            cliente.setApellido_C("Final");
            cliente.setCedula_C("XXXXXXX");
            cliente.setDireccion_C("");
            cliente.setConvencional_C("");
        }else{
            clienteNombre.setText("");
            clienteCedula.setText("");
            clienteApellido.setText("");
            clienteCedula.setDisable(false);
            cliente = null;
        }        
    }

    @FXML
    private void handleButtonBuscarCliente(ActionEvent event){
       ClienteVO cliente = null;
       try{
            cliente = ClienteDAO.buscarCliente(new ClienteVO(clienteCedula.getText()),"Cedula_C");
       }catch(Exception e){
           alertBox.crearErrorBox("","","No fue encontrado el cliente");
       }
       if (cliente != null){
           clienteNombre.setText(cliente.getNombre_C());
           clienteApellido.setText(cliente.getApellido_C());
           clienteCedula.setText(cliente.getCedula_C());
           this.cliente = cliente;
       }else{
          // alertBox.crearErrorBox("","","No fue encontrado el cliente");
       }
    }

    @FXML
    private void handleButtonFacturar(ActionEvent event) throws ParseException{
        boolean bandera = true;
        for (ProductosCanasta proo : productosCanastaFactura){
            if(proo.getCantidad() == 0){
                alertBox.crearErrorBox(" ", "", "Debe ingresar las cantidades de todos los productos");
                bandera = false;
                break;
            }
        }
        if(bandera){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            String parseDate = dateFormat.format(date);
            Timestamp ts = Timestamp.valueOf(parseDate);
            if(clienteCedula.getText().isEmpty()){
                alertBox.crearErrorBox(" ", "", "Ingrese cliente primero");
            }else{
                FacturaDAO.setFactura(new BigDecimal(textTotal.getText()),ts,  clienteCedula.getText(),comboBoxEmpleados.getValue().getCedula());
                alertBox.crearAlertBox(" ", "", "La factura ha sido registrada");
            }
            for (ProductosCanasta pro : productosCanastaFactura){ 
                FacturaDAO.setProducto_Factura(pro.getId(), siguienteIdFactura);
                ProductoDAO.actualizarProducto(pro.getId(), pro.getCantidad());
            }
            
            try{
                PDF.print(""+siguienteIdFactura,date,comboBoxEmpleados.getValue().getApellido(),cliente,productosCanastaFactura,subtotal );
                limpiarCarritoObservable();
                productosCanastaFactura.clear();
                clienteCedula.clear();clienteApellido.clear();clienteNombre.clear();codigoProducto.clear();nombreProducto.clear();marcaProducto.clear();
                siguienteIdFactura = FacturaDAO.getIdLast()+1;
                codigoFactura.setText(""+siguienteIdFactura);
            }catch(Exception e){
                System.out.println("ERROR no se pudo imprimir");
            }
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
            List <ProductoVO> productosTmp = new ArrayList<>();

            for (ProductoVO pp : productos){
                productosTmp.add(pp);
            }

            for (ProductoVO  p1 : productos){
                for (ProductoVO p : productosCanastaFactura){
                   if (p.getId().equals(p1.getId()))
                       productosTmp.remove(p1);
                }  
            }

            if (productosTmp.isEmpty()){
                alertBox.crearAlertBox(" ", "", "No hay productos que mostrar");
            }else{
                ListaProductosController.productosOB = productosTmp;
                cargarFxmlProductos();
            }
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

    private void setCeldasSinDatos(){
        TableColumn tableColumnNro = new TableColumn("Nro");
        TableColumn tableColumnCodigo = new TableColumn("CODIGO");
        TableColumn tableColumNombre = new TableColumn("NOMBRE");
        TableColumn tableColumnStock = new TableColumn("STOCK");
        TableColumn tableColumnCantidad = new TableColumn("CANTIDAD");
        TableColumn tableColumnPUnitario = new TableColumn("P. UNITARIO");
        TableColumn tableColumnPTotal = new TableColumn("TOTAL");
        tablaProductos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablaProductos.getColumns().addAll(tableColumnNro,tableColumnCodigo,tableColumNombre,tableColumnStock,tableColumnCantidad,tableColumnPUnitario,tableColumnPTotal);
    }
    
    public void setProductosTable(){
        int cont = 1;
        subtotal = new BigDecimal (0.00);
        for (ProductosCanasta p : productosCanastaFactura){
            p.setNmr(cont); // Coloca el numero de venta del producto
            p.setTotal(p.getPrecio_venta().multiply(new BigDecimal(p.getCantidad()))); // Setea el total multiplicando
            if (p.getTotal() != null || !p.getTotal().equals(new BigDecimal (0.00))){
                subtotal = subtotal.add(p.getTotal());
            }
            productosCanastaObservable.add(p);
            cont ++;  
        }
        
        textSubtotal.setText(subtotal.toString());
        textIva.setText("14 %");
        textTotal.setText(subtotal.multiply(new BigDecimal(0.14)).add(subtotal).setScale(2,3).toString());
        
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
        tableColumnNro.setMaxWidth( 1f * Integer.MAX_VALUE * 3 );
        tableColumnCodigo.setMaxWidth( 1f * Integer.MAX_VALUE * 15 );
        tableColumNombre.setMaxWidth( 1f * Integer.MAX_VALUE * 35 );
        tableColumnStock.setMaxWidth( 1f * Integer.MAX_VALUE * 5 );
        tableColumnCantidad.setMaxWidth( 1f * Integer.MAX_VALUE * 5 );
        tableColumnPUnitario.setMaxWidth( 1f * Integer.MAX_VALUE * 8 );
        tableColumnPTotal.setMaxWidth( 1f * Integer.MAX_VALUE * 5 );
        tablaProductos.setItems(productosCanastaObservable);
        tablaProductos.getColumns().addAll(tableColumnNro,tableColumnCodigo,tableColumNombre,tableColumnStock,tableColumnCantidad,tableColumnPUnitario,tableColumnPTotal);
    }
      
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
            System.out.println(index);
            System.out.println("\n" + Colores.ANSI_GREEN + "Producto escogido Para Borrar o Ingresar Cantidad"+ Colores.ANSI_RESET);
            try {
                System.out.println(productosCanastaFactura.get(index).getId());
            } catch (Exception e) {
                System.out.println("ERROR celda escogida sin productos");
            }
            // Agregar un producto a carrito
            try{
                btnCantidad.setOnAction(new EventHandlerImpl(index));
            }
            catch(Exception ex){
                System.out.println("ERRO por celda escogida sin productos");
            }
            
            try{
               btnBorrarProducto.setOnAction(new EventHandlerBorrar(index));
            }
            catch(Exception ex){
                System.out.println("ERRO por celda escogida sin productos");
            }    
        }
        
        private class EventHandlerBorrar implements EventHandler<ActionEvent> {
            private final int index;
            public EventHandlerBorrar(int index) {
                 this.index = index;
            }

                @Override
                public void handle(ActionEvent event) {
                    System.out.println("\n" + Colores.ANSI_GREEN + "Escogido para borrar" + Colores.ANSI_RESET);
                    alertBox.crearAlertBox(" ", "", "El productos se borro");
                    int cont = 0;
                    for (ProductosCanasta p : productosCanastaFactura){
                        if(p.getId() == productosCanastaFactura.get(index).getId()){
                            System.out.println(p.getId() + " Listo para borrar");
                            productosCanastaFactura.remove(cont);
                            break;
                        }
                        cont ++;
                    }
                    limpiarTablasYNoAcumulen();
                    limpiarCarritoObservable();
                    setProductosTable();
                }
        }

        private class EventHandlerImpl implements EventHandler<ActionEvent> {
            
            private final int index;
            
            public EventHandlerImpl(int index) {
                this.index = index;
            }

            @Override
            public void handle(ActionEvent e) {
                System.out.println("\n" + Colores.ANSI_CYAN + "Editar Cantidad");
                int cantidad = alertBox.textInputCantidadBox("CANTIDAD", "", "Ingresa la cantidad del producto");
                for (ProductosCanasta po : productosCanastaFactura){
                    if (productosCanastaFactura.get(index).getId() == po.getId() && productosCanastaFactura!= null && !productosCanastaFactura.isEmpty()){
                        if(productosCanastaFactura.get(index).getStock() < cantidad || cantidad < 0){
                            alertBox.crearErrorBox(" ", "", "El numero no es valido");
                        }else{
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
}

