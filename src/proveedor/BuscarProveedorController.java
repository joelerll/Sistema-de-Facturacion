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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/MenuPrincipal/menuPrincipal.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }
    
    public Proveedor buscarProveedor(String nombreBuscar){
        Proveedor p = new Proveedor();
        try{
           con = database.conectar();
            CallableStatement procedure = con.prepareCall("{call buscar_proveedor_nombre(?)}");
            procedure.setString(1,nombreBuscar);
            procedure.execute();
            
            try (ResultSet resultSet = procedure.getResultSet()) {
                while (resultSet.next()){
                    p.setPId(resultSet.getInt(1));
                    p.setPNombre(resultSet.getString(2));
                    p.setPDireccion(resultSet.getString(3));
                    }
            }
            
            System.out.println("Encontrado proveedor\n"+ p.toString());
            // Limpiar ComboBox por cada busqueda
            if (CBnombre != null){
                CBnombre.getSelectionModel().clearSelection();
                CBnombre.getItems().clear();
            }
         } catch (SQLException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return p;
    }
     
    public void buscarProveedorNombre(ActionEvent event){
        List <Proveedor>  listaproveedores = null;
        String nombreBuscar= TFbuscar_nombre.getCharacters().toString().toUpperCase();
        if (nombreBuscar.equals("")){
            errorWindow("Error","No ingreso nombre");
        }else{
            Proveedor p = buscarProveedor(nombreBuscar);
            if (p.getPId() == 0){
                System.out.println("No encontro ninguna coincidencia");
                TFnombre.setText("");
                TFdir.setText("");
                // Limpiar ComboBox por cada busqueda
                if (CBnombre != null){
                    CBnombre.setValue("No se encontró ninguna coincidencia");
                    CBnombre.getSelectionModel().clearSelection();
                    CBnombre.getItems().clear();
                }
            }else{
            setCamposEnTextField(p);
                listaproveedores = Proveedor.buscarProveedor2(p); //AQUI ES
                CBnombre.setValue(listaproveedores.get(0));
                CBnombre.getItems().addAll(listaproveedores);
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
        Proveedor p = buscarProveedor(TFnombre.getText());
        if(TFnombre.getText().equals(""))
        {
            alertWindow("Ingrese campos","No ha ingresado ningun campo");
        }else
        {
            Alert alert = confirmationWindow("Eliminar","Esta de Acuerdo con eliminar proveedor");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                List <String>  vacio = new ArrayList <>(); 
                Proveedor.eliminarProveedor2(p.getPId());
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
            Proveedor.editarProveedor2(pv, pv.getPId());
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
