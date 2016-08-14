/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proveedor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DBconnection;
import java.io.IOException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author fernando
 */
public class BuscarProveedorController implements Initializable {
    //ATRIBUTOS
    private static final DBconnection database=new DBconnection();
    private static Connection con;
    PreparedStatement ps;
    ResultSet rs;
    List <Proveedor>  proveedores = null;
    Proveedor proveedor = new Proveedor();
    //JFXTextField
    @FXML
    private JFXTextField TFbuscar_nombre;
    @FXML
    private JFXTextField TFnombre;
    @FXML
    private JFXTextField TFdir;
    //ComboBox
    @FXML
    private ComboBox CBnombre;
    //JFXButton
    @FXML 
    private JFXButton XBbuscar;
    @FXML
    private JFXButton XBeliminar;
    @FXML
    private JFXButton XBeditar;
    @FXML
    private JFXButton XBvista;
    @FXML
    private JFXButton XBatras;
    //Label
    @FXML
    private Label Lerror;
    @FXML
    private Label Lmsg;
    
    //METODOS
    @FXML
    void atras(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/proveedor/ProveedorOpciones.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }
    
    public Proveedor buscarProveedor(String campo,String nombreBuscar){
        try{
            con = database.conectar();
            String q ="SELECT * FROM proveedor WHERE "+ campo +" LIKE ('%"+nombreBuscar+"%')"+"LIMIT 1";
            System.out.println(q);
            ps = con.prepareCall(q);
            rs = ps.executeQuery();
            rs.next();
            this.proveedor.setPId(rs.getInt(1));
            this.proveedor.setPNombre(rs.getString(2));
            this.proveedor.setPDireccion(rs.getString(3));
            ps.close();
            con.close();
            rs.close();
            System.out.println("Encontrado proveedor\n"+proveedor.toString());
            // Limpiar ComboBox por cada busqueda
            if (CBnombre != null){
                CBnombre.getSelectionModel().clearSelection();
                CBnombre.getItems().clear();
            }
        }catch(SQLException sql){
            System.out.println("Error en buscar proveedor");
            Lerror.setText("Ninguna coincidencia encontrada");
            return null;
        }
        return this.proveedor;
    }
    
    public void buscarProveedorNombre(ActionEvent event){
        String campo = "nombre";
        String nombreBuscar= TFbuscar_nombre.getCharacters().toString().toUpperCase();
        if (nombreBuscar.equals("")){
            errorWindow("Error","No ingreso nombre");
        }else{
            Proveedor p= buscarProveedor(campo,nombreBuscar);
            if (p == null){
                System.out.println("No encontro ninguna coincidencia");
            }else{
            setCamposEnTextField(p);
                this.proveedores =Proveedor.searchProveedor("nombre", nombreBuscar);
                CBnombre.setValue(this.proveedores.get(0));
                CBnombre.getItems().addAll(this.proveedores);
            }
        }
    }
    
    @FXML
    public void getComboBoxOpcion(ActionEvent event){
        this.proveedor = null;
        this.proveedor =(Proveedor) CBnombre.getValue();
        System.out.println(this.proveedor);
        setCamposEnTextField(this.proveedor);     
    }
    
    @FXML
    public void eliminarProveedor(ActionEvent event){
        if(this.proveedor.getPId() == 0)
        {
            alertWindow("Ingrese campos","No ha ingresado ningun campo");
        }else
        {
            Alert alert = confirmationWindow("Eliminar","Esta de Acuerdo con eliminar proveedor");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                List <String>  vacio = new ArrayList <>(); 
                Proveedor.eliminarProveedorSQL(this.proveedor);
                TFnombre.setText("");
                TFdir.setText("");
                CBnombre.setValue("");
                CBnombre.getItems().addAll(vacio);
            }
        }
    }
    
    @FXML
    public void editarProveedor(ActionEvent event){
        Proveedor pv = new Proveedor();
        pv.setPId(this.proveedor.getPId());
        Alert alert = confirmationWindow("Editar","Esta de Acuerdo con editar proveedor");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try{
            pv.setPNombre(TFnombre.getText().toUpperCase());
            pv.setPDireccion(TFdir.getText().toUpperCase());
            Proveedor.editarProveedorSQL(pv);
            alertWindow("Editado","Se ha editado el proveedor correctamente");
            }catch(Exception e){
                errorWindow("Error","Ingrese correctamente los campos");
            }
        }
    }
    
    private void setCamposEnTextField(Proveedor proveedor)
    {
        TFnombre.setText(proveedor.getPNombre());
        TFdir.setText(proveedor.getPDireccion());
    }
    
    private Alert confirmationWindow(String title, String mensaje)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        return alert;
    }
    
    public void alertWindow(String title, String mensaje)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }
    
    public void errorWindow(String title, String mensaje)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        
    }    
    
}
