
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion;

import Cliente.Cliente;
import Factura.Factura;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author joelerll
 */
public class FacturacionController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Label fecha;
    
    @FXML
    private Label codigoFactura;

    @FXML
    private JFXCheckBox consumidorFinal;
    
    @FXML
    private JFXTextField clienteNombre;

    @FXML
    private JFXTextField clienteCedula;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void consumidorFinalCheck(ActionEvent event) {
        boolean selected = consumidorFinal.isSelected();
        if(selected){
            clienteNombre.setText("Consumidor Final");
            clienteCedula.setText("XXXXXXXXXXXXXXXX");
        }else{
            clienteNombre.setText("");
            clienteCedula.setText("");
        }        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Factura factura =new Factura();
       codigoFactura.setText(""+Integer.parseInt(factura.Last().getId_Orden()));
       
       Date date= new Date();
       SimpleDateFormat formateador = new SimpleDateFormat("MMMM dd 'del' yyyy", new Locale("es"));
       String fe=formateador.format(date);
       
       fecha.setText(fe);
       clienteNombre.focusedProperty().addListener(new ChangeListener<Boolean>() {
           @Override
           public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println("Focussssssssssss");
                clienteNombre.getText();
                System.out.println("Text;"+clienteNombre.getText());
           }
       });
      
      
       
       //autocompletarClientes();
       
    }    
    
    public void autocompletarClientes()
    {
        List list=new LinkedList();
        list.add("Hey");
        list.add ("Hello");
        list.add("Hello World");
        TextFields.bindAutoCompletion(clienteNombre,list);
        TextFields.createClearableTextField();
        
    }
    
    public void nombresAutocompletado()
    {
        clienteNombre.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER)
                {
                    List<Cliente> searchClientesByName = Cliente.searchClientesByName(clienteNombre.getText());
                    System.out.println( searchClientesByName.size());
                    System.out.println("Presiono enter");
                    TextFields.bindAutoCompletion(clienteNombre,Cliente.nombresClientes(searchClientesByName)).setPrefWidth(700);
                }
            }
        });
    }
    
    

}
