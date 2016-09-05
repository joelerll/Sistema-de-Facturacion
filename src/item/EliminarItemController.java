/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DBconexion;
import database.DBconnection;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.CallableStatement;
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
        CallableStatement cs = null;
        String sql = "{call buscarItem(?)}";
        try{
            comboBoxNombre.setVisible(true);
            conexion = database.conectar();
            cs = conexion.prepareCall(sql);
            cs.setString(1, nombreBuscar);
            cs.executeQuery();
            try(ResultSet rset = cs.getResultSet()){
                rset.next();
                this.item.setId(rset.getInt(1));
                this.item.setPrecio(rset.getBigDecimal(2));
                this.item.setNombre(rset.getString(3));
                this.item.setDescripcion(rset.getString(4));
                this.item.setFecha(rset.getDate(5));
            }
            cs.close();
            conexion.close();
            System.out.println("Encontrado item\n"+item.toString());
            // limpiar combo box por cada busqueda
            if (comboBoxNombre != null){
                comboBoxNombre.getSelectionModel().clearSelection();
                comboBoxNombre.getItems().clear();
            }
        }catch(SQLException s){
            System.out.println("Error en buscar item");
            errorWindow("No encontrado","Ninguna coincidencia encontrada");
            return null;
        }
        return this.item;
    }
    
    private String buscarNombreEmpleado(String cedula){
        CallableStatement cs  = null;
        String c= null;
        String sql = "{call buscarNombreEmplado(?,?)}";
        try{
            conexion = database.conectar();
            cs = conexion.prepareCall(sql);
            cs.setString(1, cedula);
            cs.executeQuery();
            c = cs.getString(2);
            cs.close();
            conexion.close();
        }catch(SQLException s){
            System.out.println("Error en buscar nombre empleado");
            return null;
        }
        return c;
    }
    
    private void eliminarItem_emplado(int id_item,String cedula_empl)
    {
        CallableStatement cs = null;
        System.out.println("sdsdsds"+id_item + "  " + cedula_empl);
        String sql = "{call eliminarItem_emplado(?,?)}";
        try{
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.setInt(1, id_item);
            cs.setString(2, cedula_empl);
            cs.executeQuery();
            cs.close();
            con.desconetar();
            System.out.println("Borrado el item empleado seleccionado");
        }catch(SQLException s){
            System.out.println("Error al tratar de eliminar item_emplado");
        }
    }
    
    private Item_empleado buscarItem_Empleado(int id_item)
    {
        CallableStatement cs = null;
        Item_empleado item_empleado = new Item_empleado();
        String sql = "{call  buscarItem_Empleado(?)}";
        try{
            DBconexion con = new DBconexion();
            cs = con.getConnection().prepareCall(sql);
            cs.setInt(1, id_item);
            cs.executeQuery();
            try(ResultSet rset = cs.getResultSet())
            {
                rset.next();
                item_empleado.setId(rset.getInt(1));
                item_empleado.setId_item(rset.getInt(2));
                item_empleado.setCedula_empl(rset.getString(3));
            }
            con.desconetar();
            cs.close();
        }catch(SQLException s){
            System.out.println("Error en buscar item empleado, cedula");
            return null;
        }
        return item_empleado;
    }
    
    @FXML
    public void buscarItemNombre(ActionEvent event){
        String campo = "nombre";
        String nombreBuscar= textFieldBuscarNombre.getCharacters().toString().toUpperCase();
        System.out.println("dsfd"+ nombreBuscar);
        if ("".equals(nombreBuscar)){
            errorWindow("Error","No ingreso nombre");
        }else{
            Item item= buscarItem(campo,nombreBuscar);
            System.out.println(item);
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
    public void getComboBoxOpcion(){
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
            getComboBoxOpcion();
             Alert alert = confirmationWindow("Eliminar","Esta de Acuerdo con eliminar item");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                List <String>  vacio = new ArrayList <>();
                System.out.println("");
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
