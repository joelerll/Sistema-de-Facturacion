/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductoOpciones;

import Clases.ProductoVO;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author joelerll
 */
public class ListaProductosController implements Initializable {
    @FXML
    private TableView<ProductoVO> productos;
    
   /* @FXML
    private TableColumn <ProductoVO, String>marca;*/
    
    /*@FXML
    private TableColumn   <ProductoVO, String> codigo;*/
    
    /*@FXML
    private TableColumn<ProductoVO, BigDecimal> precio;

   /* @FXML
    private TableColumn<ProductoVO, Integer> stock;*/

    /*@FXML
    private TableColumn<ProductoVO, String> nombre;*/
    
    public static List<ProductoVO> productosOB;
    
    @FXML
    private Label label;
    
    @FXML
    private AnchorPane anchorPane;

    public ListaProductosController() {
        
    }
  
    public List<ProductoVO> getProductosOB() {
        return productosOB;
    }
    ObservableList<ProductoVO> productosOBB = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       // Setear todos los productos en la tablewiew
       
       for (ProductoVO p : productosOB){   
            productosOBB.add(p);
       }
       
       Callback<TableColumn, TableCell> integerCellFactory =
               new Callback<TableColumn, TableCell>(){
           @Override
           public TableCell call(TableColumn p){
               MyIntegerTableCell cell = new MyIntegerTableCell();
               cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
               return cell;
               
           }
        };
       
       Callback<TableColumn, TableCell> stringCellFactory =
               new Callback<TableColumn, TableCell>(){
           @Override
           public TableCell call(TableColumn p){
               MyStringTableCell cell = new MyStringTableCell();
               cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
               return cell;
               
           }
        };
       
       Callback<TableColumn, TableCell> bigDecimalCellFactory =
               new Callback<TableColumn, TableCell>(){
           @Override
           public TableCell call(TableColumn p){
               MyBigDecimalTableCell cell = new MyBigDecimalTableCell();
               cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
               return cell;
               
           }
        };
       
       TableColumn codigo = new TableColumn("Codigo"); 
       codigo.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>("id"));
       codigo.setCellFactory(stringCellFactory);
       
       TableColumn nombre = new TableColumn("Nombre");
       nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
       nombre.setCellFactory(stringCellFactory);
       
       TableColumn marca = new TableColumn("Marca");
       marca.setCellValueFactory(new PropertyValueFactory<ProductoVO, String>("marca"));
       marca.setCellFactory(stringCellFactory);
       
       TableColumn precio = new TableColumn("Precio");
       precio.setCellValueFactory(new PropertyValueFactory<>("precio_venta"));
       precio.setCellFactory(bigDecimalCellFactory);
       
       TableColumn stock = new TableColumn("Stock");
       stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
       stock.setCellFactory(integerCellFactory);
       productos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       productos.setItems(productosOBB);
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
            System.out.println(productosOB.get(index).getId());
            System.out.println(productosOB.get(index).getNombre());
            System.out.println(productosOB.get(index).getMarca());
            System.out.println(productosOB.get(index).getStock());
            System.out.println(productosOB.get(index).getPrecio_venta());
        }

      
    }

    
 /*   public void cargarFxmlProductos()
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ProductoOpciones/ListaProductos.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Coincidencia Productos");
            stage.setScene(new Scene(root1));  
            stage.show();
            }catch(Exception e){
                System.out.println("factura.IngresarController.cargarFxmlProductos ERROR");
            }
        
    }*/
}
