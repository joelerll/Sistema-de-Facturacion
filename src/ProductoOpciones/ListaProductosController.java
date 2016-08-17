/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductoOpciones;

import AlertBox.alertBox;
import Clases.ProductoVO;
import Utils.Colores;
import com.jfoenix.controls.JFXButton;
import factura.IngresarController;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author joelerll
 */
public class ListaProductosController implements Initializable {
    @FXML
    private TableView<ProductoVO> productos;
 
    @FXML
    private ImageView imagen;
    
    @FXML
    private JFXButton btnAgregar;
  
    private ProductoVO productoEscogido;
    
    public static List<ProductoVO> productosOB;

    private ObservableList<ProductoVO> productosOBB = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       // Setear todos los productos en la tablewiew
       for (ProductoVO p : productosOB){   
            productosOBB.add(p);
       }
       
       // Verifica que celda esta escogiendo
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
       
       // Anade celdad al tableview, no se hace directamente del fxml por error de parser del mismo
       TableColumn codigo = new TableColumn("Codigo"); 
       codigo.setCellValueFactory(new PropertyValueFactory<>("id"));
       codigo.setCellFactory(stringCellFactory);
       
       TableColumn nombre = new TableColumn("Nombre");
       nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
       nombre.setCellFactory(stringCellFactory);
       
       TableColumn marca = new TableColumn("Marca");
       marca.setCellValueFactory(new PropertyValueFactory<>("marca"));
       marca.setCellFactory(stringCellFactory);
       
       TableColumn precio = new TableColumn("Precio");
       precio.setCellValueFactory(new PropertyValueFactory<>("precio_venta"));
       precio.setCellFactory(bigDecimalCellFactory);
       
       TableColumn stock = new TableColumn("Stock");
       stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
       stock.setCellFactory(integerCellFactory);
       
       // elimina la ultima celda por defecto
       productos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       
       // Ingresa los datos en cada celda determinada
       productos.setItems(productosOBB);
       
        // Anade todas las columnas en orden
       productos.getColumns().addAll(codigo,nombre,marca,stock,precio);
    }
    
    class MyIntegerTableCell extends TableCell<ProductoVO,Integer>{
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
    
    class MyStringTableCell extends TableCell<ProductoVO,String>{
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
    
    class MyBigDecimalTableCell extends TableCell<ProductoVO,BigDecimal>{
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
            
            System.out.println("\n" + Colores.ANSI_GREEN + "Producto escogido");
            System.out.println("productosOB.get(index).toString()");

            // Agregar un producto a carrito
            btnAgregar.setOnAction(new EventHandlerImpl(index));
            
            // Ingresar imagen y ver
            try{
                imagen.setVisible(true);
                Image im = new Image(productosOB.get(index).getImagen());
                imagen.setImage(im);
            }catch(Exception e){
                imagen.setVisible(false);
                System.out.println(Colores.ANSI_RED + "No se encontro imagen");
            }    
        }

        private class EventHandlerImpl implements EventHandler<ActionEvent> {
            
            private final int index;
            
            public EventHandlerImpl(int index) {
                this.index = index;
            }

            @Override
            public void handle(ActionEvent e) {
                ProductoVO p = new ProductoVO();
                p.setId(productosOB.get(index).getId());
                IngresarController.productosCanasta.add(p);
                alertBox.crearAlertBox(" ", "", "Producto Agregado a Carrito");
            }
        }
    }
    
}
