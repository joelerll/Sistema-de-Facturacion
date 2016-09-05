/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleado;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
public class BuscarEmpleadoController implements Initializable {

    //ATRIBUTOS
    private static final DBconnection database=new DBconnection();
    private static Connection con;
    PreparedStatement ps;
    ResultSet rs;
    List <Empleado>  empleados = null;
    Empleado empleado = new Empleado();
    //JFXTextField
    @FXML
    private JFXTextField TFbuscar_nombre;
    @FXML
    private JFXTextField TFcedula;
    @FXML
    private JFXTextField TFnombre;
    @FXML
    private JFXTextField TFapellido;
    @FXML
    private JFXTextField TFdir;
    @FXML
    private JFXTextField TFfecha_ing;
    @FXML
    private JFXTextField TFhorario_ent;
    @FXML
    private JFXTextField TFhorario_sal;
    @FXML
    private JFXTextField TFsueldo;
    @FXML
    private JFXTextField TFes_admin;
    @FXML
    private JFXTextField TFuser;
    @FXML
    private JFXTextField TFtelefono;
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
    
    
    public Empleado buscarEmpleado(String nombreBuscar){
        Empleado e = new Empleado();
        try{
            con = database.conectar();
            CallableStatement procedure = con.prepareCall("{call buscar_empleado_nombre(?)}");
            procedure.setString(1,nombreBuscar);
            procedure.execute();
            
            try (ResultSet resultSet = procedure.getResultSet()) {
                while (resultSet.next()){
                    e.setEcedula(resultSet.getString(1));
                    e.setEnombre(resultSet.getString(2));
                    e.setEapellido(resultSet.getString(3));
                    e.setEdireccion(resultSet.getString(4));
                    e.setEfecha_ing(resultSet.getString(5));
                    e.setEhorario_ent(resultSet.getString(6));
                    e.setEhorario_sal(resultSet.getString(7));
                    e.setEsueldo(resultSet.getBigDecimal(8));
                    e.setEes_admin(resultSet.getInt(9));
                    e.setEtelefono(resultSet.getString(10));
                    e.setEuser(resultSet.getString(11));
                    }
            }
                
            
            
            System.out.println("Encontrado empleado\n"+ e.toString());
            // Limpiar ComboBox por cada busqueda
            if (CBnombre != null){
                CBnombre.getSelectionModel().clearSelection();
                CBnombre.getItems().clear();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return e;
    }
    
    public void buscarEmpleadoNombre(ActionEvent event){
        String nombreBuscar= TFbuscar_nombre.getCharacters().toString().toUpperCase();
        if (nombreBuscar.equals("")){
            errorWindow("Error","No ingreso nombre");
        }else{
            Empleado e = buscarEmpleado(nombreBuscar);
            if (e.getEcedula() == null){
                System.out.println("No encontro ninguna coincidencia");
                TFcedula.setText("");
                TFnombre.setText("");
                TFapellido.setText("");
                TFdir.setText("");
                TFfecha_ing.setText("");
                TFhorario_ent.setText("");
                TFhorario_sal.setText("");
                TFsueldo.setText("");
                TFes_admin.setText("");
                TFuser.setText("");
                TFtelefono.setText("");
                CBnombre.setValue("");
                // Limpiar ComboBox por cada busqueda
                if (CBnombre != null){
                    CBnombre.setValue("No se encontró ninguna coincidencia");
                    CBnombre.getSelectionModel().clearSelection();
                    CBnombre.getItems().clear();
                }
            }else{
            setCamposEnTextField(e);
                this.empleados =Empleado.buscarEmpleado2(e);
                CBnombre.setValue(this.empleados.get(0));
                CBnombre.getItems().addAll(this.empleados);
            }
        }
    }
    
    @FXML
    public void getComboBoxOpcion(ActionEvent event){
        this.empleado = null;
        this.empleado =(Empleado) CBnombre.getValue();
        System.out.println(this.empleado);
        setCamposEnTextField(this.empleado);     
    }
    
    @FXML
    public void eliminarEmpleado(ActionEvent event){ // TFcedula.getText().equals("")
        if(TFcedula.getText().equals("")) //this.empleado.getEcedula().equals("")
        {
            alertWindow("Ingrese campos","No ha ingresado ningun campo");
        }else{
            String key = TFcedula.getText();
            Alert alert = confirmationWindow("Eliminar","Esta de Acuerdo con eliminar empleado");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                List <String>  vacio = new ArrayList <>();
                Empleado.eliminarEmpleado2(key); //OJO
                TFcedula.setText("");
                TFnombre.setText("");
                TFapellido.setText("");
                TFdir.setText("");
                TFfecha_ing.setText("");
                TFhorario_ent.setText("");
                TFhorario_sal.setText("");
                TFsueldo.setText("");
                TFes_admin.setText("");
                TFuser.setText("");
                TFtelefono.setText("");
                CBnombre.setValue("");
                CBnombre.getItems().addAll(vacio);
            }
        }
    }
    
    @FXML
    public void editarEmpleado(ActionEvent event){
        Empleado em = new Empleado();
        String  key = TFcedula.getText();
        em.setEcedula(key);
        Alert alert = confirmationWindow("Editar","Esta de Acuerdo con editar proveedor");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try{
                em.setEnombre(TFnombre.getText().toUpperCase());
                em.setEapellido(TFapellido.getText().toUpperCase());
                em.setEdireccion(TFdir.getText().toUpperCase());
                em.setEfecha_ing(TFfecha_ing.getText().toUpperCase());
                em.setEhorario_ent(TFhorario_ent.getText().toUpperCase());
                em.setEhorario_sal(TFhorario_sal.getText().toUpperCase());
                em.setEsueldo(new BigDecimal(TFsueldo.getText().toUpperCase()));
                em.setEes_admin(Integer.parseInt(TFes_admin.getText()));
                em.setEtelefono(TFtelefono.getText().toUpperCase());
                em.setEuser(TFuser.getText().toUpperCase());
                Empleado.editarEmpleado2(em, key); //OJO
                alertWindow("Editado","Se ha editado el empleado correctamente");
            }catch(Exception e){
                errorWindow("Error","Ingrese correctamente los campos");
            }
        }
    }
    
    private void setCamposEnTextField(Empleado empleado)
    {   TFcedula.setText(empleado.getEcedula());
        TFnombre.setText(empleado.getEnombre());
        TFapellido.setText(empleado.getEapellido());
        TFdir.setText(empleado.getEdireccion());
        TFfecha_ing.setText(empleado.getEfecha_ing());
        TFhorario_ent.setText(empleado.getEhorario_ent());
        TFhorario_sal.setText(empleado.getEhorario_sal());
        TFsueldo.setText(empleado.getEsueldo().toString());
        TFes_admin.setText(empleado.getEes_admin().toString());
        TFuser.setText(empleado.getEuser());
        TFtelefono.setText(empleado.getEtelefono());
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
        // TODO
    }    
    
}
