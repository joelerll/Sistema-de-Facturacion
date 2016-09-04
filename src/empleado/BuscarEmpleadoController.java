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
    
    public Empleado buscarEmpleado(String campo,String nombreBuscar){
        try{
            con = database.conectar();
            String q ="SELECT * FROM empleado WHERE "+ campo +" LIKE ('%"+nombreBuscar+"%')"+"LIMIT 1";
            System.out.println(q);
            ps = con.prepareCall(q);
            rs = ps.executeQuery();
            rs.next();
            this.empleado.setEcedula(rs.getString(1));
            this.empleado.setEnombre(rs.getString(2));
            this.empleado.setEapellido(rs.getString(3));
            this.empleado.setEdireccion(rs.getString(4));
            this.empleado.setEfecha_ing(rs.getString(5));
            this.empleado.setEhorario_ent(rs.getString(6));
            this.empleado.setEhorario_sal(rs.getString(7));
            this.empleado.setEsueldo(new BigDecimal(rs.getString(8)));
            this.empleado.setEes_admin(rs.getInt(9));
            this.empleado.setEtelefono(rs.getString(10));
            this.empleado.setEuser(rs.getString(11));
            ps.close();
            con.close();
            rs.close();
            System.out.println("Encontrado empleado\n"+empleado.toString());
            // Limpiar ComboBox por cada busqueda
            if (CBnombre != null){
                CBnombre.getSelectionModel().clearSelection();
                CBnombre.getItems().clear();
            }
        }catch(SQLException sql){
            System.out.println("Error en buscar empleado");
            Lerror.setText("Ninguna coincidencia encontrada");
            return null;
        }
        return this.empleado;
    }
    
    public void buscarProveedorNombre(ActionEvent event){
        String campo = "nombre";
        String nombreBuscar= TFbuscar_nombre.getCharacters().toString().toUpperCase();
        if (nombreBuscar.equals("")){
            errorWindow("Error","No ingreso nombre");
        }else{
            Empleado e = buscarEmpleado(campo,nombreBuscar);
            if (e == null){
                System.out.println("No encontro ninguna coincidencia");
            }else{
            setCamposEnTextField(e);
                this.empleados =Empleado.searchEmpleado("nombre", nombreBuscar);
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
    public void eliminarEmpleado(ActionEvent event){
        if(this.empleado.getEcedula().equals(""))
        {
            alertWindow("Ingrese campos","No ha ingresado ningun campo");
        }else
        {
            Alert alert = confirmationWindow("Eliminar","Esta de Acuerdo con eliminar empleado");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                List <String>  vacio = new ArrayList <>(); 
                Empleado.eliminarEmpleadoSQL(this.empleado);
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
        em.setEcedula(this.empleado.getEcedula());
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
                Empleado.editarEmpleadoSQL(em);
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
