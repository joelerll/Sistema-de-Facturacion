/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DBconnection;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author joelerll
 */
public class EliminarItemController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private JFXButton btnAtras;

    @FXML
    private JFXTextField TexfFielNombre;

    @FXML
    private JFXTextField textFieldPrecio;

    @FXML
    private DatePicker datePickerFecha;

    @FXML
    private JFXTextField textFieldBuscarNombre;

    @FXML
    private JFXTextField textFieldDescripcion;

    @FXML
    private DatePicker datePickerBuscarFecha;

    @FXML
    private JFXButton btnBuscar;
    
    @FXML
    private JFXTextField textFieldFecha;
    
    @FXML
    private Label labelMensaje;
    
    @FXML
    private ComboBox comboBoxNombre;
    
    @FXML
    private Label labelError;
    
    @FXML
    private Label labelNombre_empl;
    
    @FXML
    private Label labelCedula_empl;
     
     
   
    public DBconnection database=new DBconnection();
    public Connection conexion;
    PreparedStatement ps;
    ResultSet rs;
    
    @FXML
    //atras de menu
    void atras(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/MenuPrincipal/menuPrincipal.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }
    
    List <Item>  items=null;
    Item item = new Item();
    //busca item por nombre y campo
    public Item buscarItem(String campo,String nombreBuscar){
        try{
            comboBoxNombre.setVisible(true);
            conexion = database.conectar();
            String q ="SELECT * FROM item WHERE "+ campo +" LIKE ('%"+nombreBuscar+"%')"+"LIMIT 1";
            System.out.println(q);
            ps = conexion.prepareCall(q);
            rs = ps.executeQuery();
            rs.next();
            this.item.setId(rs.getInt(1));
            this.item.setPrecio(rs.getBigDecimal(2));
            this.item.setNombre(rs.getString(3));
            this.item.setDescripcion(rs.getString(4));
            this.item.setFecha(rs.getDate(5));
            ps.close();
            conexion.close();
            rs.close();
            System.out.println("Encontrado item\n"+item.toString());
            // limpiar combo box por cada busqueda
            if (comboBoxNombre != null){
                comboBoxNombre.getSelectionModel().clearSelection();
                comboBoxNombre.getItems().clear();
            }
        }catch(SQLException sql){
            System.out.println("Error en buscar item");
            errorWindow("No encontrado","Ninguna coincidencia encontrada");
            return null;
        }
        return this.item;
    }
    
    private String buscarNombreEmpleado(String cedula){
        String c= null;
        DBconnection database=new DBconnection();
        Connection conexion;
        PreparedStatement ps;
        ResultSet rs = null;
        try{
            conexion = database.conectar();
            String q ="SELECT nombre FROM empleado WHERE "+ "cedula" +" LIKE ('%"+cedula+"%') limit 1";
            System.out.println(q);
            ps = conexion.prepareCall(q);
            rs = ps.executeQuery();
            while (rs.next()) {
                c = rs.getString(1);
                System.out.println(c);
            }
            ps.close();
            conexion.close();
            rs.close();
        }catch(SQLException sql){
            System.out.println("Error en buscar nombre empleado");
            return null;
        }
        return c;
    }
    
    private void eliminarItem_emplado(int id_item,String cedula_empl)
    {
        DBconnection database=new DBconnection();
        Connection conexion;
        PreparedStatement ps;
        try{
            conexion = database.conectar();
            String q ="DELETE FROM item_empleado WHERE id_item = " + id_item + " AND cedula_empl = " +cedula_empl; 
            System.out.println(q);
            ps = conexion.prepareStatement(q);
            ps.execute();
            conexion.close();
            System.out.println("Borrado el item empleado seleccionado");
        }catch(SQLException sql){
            System.out.println("Error al tratar de eliminar item_emplado");
        }
    }
    
    private Item_empleado buscarItem_Empleado(int id_item)
    {
        Item_empleado item_empleado = new Item_empleado();
        DBconnection database=new DBconnection();
        Connection conexion;
        PreparedStatement ps;
        ResultSet rs = null;
        try{
            conexion = database.conectar();
            String q ="SELECT * FROM item_empleado WHERE "+ "id_item" +" LIKE ('%"+id_item+"%') limit 1";
            System.out.println(q);
            ps = conexion.prepareCall(q);
            rs = ps.executeQuery();
            while (rs.next()) {
                item_empleado.setId(rs.getInt(1));
                item_empleado.setId_item(rs.getInt(2));
                item_empleado.setCedula_empl(rs.getString(3));
            }
            ps.close();
            conexion.close();
            rs.close();
        }catch(SQLException sql){
            System.out.println("Error en buscar item empleado, cedula");
            return null;
        }
        return item_empleado;
    }
    
    @FXML
    public void buscarItemNombre(ActionEvent event){
        String campo = "nombre";
        String nombreBuscar= textFieldBuscarNombre.getCharacters().toString().toUpperCase();
        if ("".equals(nombreBuscar)){
            errorWindow("Error","No ingreso nombre");
        }else{
            Item item= buscarItem(campo,nombreBuscar);
            if (item == null){
                System.out.println("No encontro ninguna coincidencia");
            }else{
            setCamposEnTextField(item);
                this.items=Item.searchItem("nombre", nombreBuscar);
                comboBoxNombre.setValue(this.items.get(0));
                comboBoxNombre.getItems().addAll(this.items);
            }
        }
    }

    @FXML
    public void getComboBoxOpcion(ActionEvent event){
        Item_empleado item_empleado = new Item_empleado();
        this.item = null;
        this.item =(Item) comboBoxNombre.getValue();
        System.out.println(this.item);
        labelNombre_empl.setText(buscarNombreEmpleado(buscarItem_Empleado(this.item.getId()).getCedula_empl()));
        labelCedula_empl.setText(buscarItem_Empleado(this.item.getId()).getCedula_empl());
        setCamposEnTextField(this.item);     
    }
    
    @FXML
    public void eliminarItem(ActionEvent event){
        int error;
        if(this.item.getId() == 0)
        {
            alertWindow("Ingrese campos","No ha ingresado ningun campo");
        }else
        {
             Alert alert = confirmationWindow("Eliminar","Esta de Acuerdo con eliminar item");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                List <String>  vacio = new ArrayList <>();
                eliminarItem_emplado(this.item.getId(),buscarItem_Empleado(this.item.getId()).getCedula_empl());
                error = Item.eliminarItemSQL(this.item);
                if (error != 1451){
                    
                    System.out.println(error);
                    TexfFielNombre.setText("");
                    textFieldPrecio.setText("");
                    textFieldDescripcion.setText("");
                    textFieldFecha.setText("");
                    labelCedula_empl.setText("");
                    labelNombre_empl.setText("");
                    comboBoxNombre.getSelectionModel().clearSelection();
                    comboBoxNombre.setVisible(false);
                    //comboBoxNombre.getItems().addAll(vacio);
                }else{
                    System.out.println("Item no eliminado sueldo");
                    errorWindow("Sueldo no eliminado","No eliminado sueldo");
                }
                
            }
        }
    }
    
    @FXML
    public void editarItem(ActionEvent event){
        Item item = new Item();
        item.setId(this.item.getId());
        Alert alert = confirmationWindow("Editar","Esta de Acuerdo con editar item");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try{
            item.setPrecio(new BigDecimal(textFieldPrecio.getText())); 
            item.setNombre(TexfFielNombre.getText());
            item.setDescripcion(textFieldDescripcion.getText());
            item.setFecha(java.sql.Date.valueOf(textFieldFecha.getText()));    
            Item.editarItemSQL(item);
            alertWindow("Editado","Se ha editado el item correctamente");
            }catch(Exception e){
                System.out.println("Ingrese correctamente la fecha");
                errorWindow("Error","Ingrese correctamente los campos");
            }
        }
    }

    private void setCamposEnTextField(Item item)
    {
        TexfFielNombre.setText(item.getNombre());
        textFieldDescripcion.setText(item.getDescripcion());
        textFieldPrecio.setText(item.getPrecio().toString());
        textFieldFecha.setText(item.getFecha().toString());
    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private Alert confirmationWindow(String title, String mensaje)
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        return alert;
    }
    
    public void alertWindow(String title, String mensaje)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }
    
    public void errorWindow(String title, String mensaje)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }
    
}
