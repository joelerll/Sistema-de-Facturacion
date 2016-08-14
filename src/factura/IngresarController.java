
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
import Cliente.Cliente;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author joelerll
 */
public class IngresarController implements Initializable {
    
    @FXML
    private JFXCheckBox consumidorFinal;
    
    @FXML
    private JFXTextField clienteCedula,clienteApellido,clienteNombre;
    
    @FXML
    private Label codigoFactura,hora,fecha;
    
    @FXML
    private JFXComboBox comboBoxEmpleados;
    
    private Date date = new Date();
    private ClienteVO cliente;
    
    @FXML
    void regresarMenuFacturacion(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/factura/facturacionMenu.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }
    
    /*
    Check Box a consumidor final, 
    */
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
    
    /*
    Buscar cliente
    Verificar que los datos ingresados de cliente existan
    */
    @FXML
    private void handleButtonBuscarCliente(ActionEvent event){
       ClienteVO cliente;
       cliente = ClienteDAO.buscarCliente(new ClienteVO(clienteCedula.getText()));
       if (cliente != null){
           clienteNombre.setText(cliente.getNombre_C());
           clienteApellido.setText(cliente.getApellido_C());
           clienteCedula.setText(cliente.getCedula_C());
           this.cliente = cliente;
       }else{
           alertBox.crearErrorBox("","","No fue encontrado el cliente");
       }
    }
    
    /*
    Buscar cliente, si no llevarlo a ingresar cliente
    */
    
    /*
    Buscar vendedor
    */
    
    /*
    Setear los datos de la factura
    */
    
    /*
    Imprimir factura
    */
    
    /*
    Buscar producto y setear imagen
    */
    
    /*
    Eliminar producto de la tabla
    */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Desabilitar los textField nombre y apellido de cliente, por solo busqueda por cedula
        clienteNombre.setDisable(true);
        clienteApellido.setDisable(true);
        
        //Set fecha label fecha
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	fecha.setText(dateFormat.format(date));
        
        // Combo box todos los empleados
        comboBoxEmpleados.setValue(EmpleadoDAO.empleados().get(0));
        comboBoxEmpleados.getItems().addAll(EmpleadoDAO.empleados());
    }    
 
}
