
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Sistema_Facturacion;
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
    private MenuItem mnuClienteIngresar;
    
    @FXML
    private MenuItem mnuClienteBuscar;
    
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
    
    public void crearCliente(){
        Stage windowC = new Stage();
        windowC.setTitle("Crear Cliente");
        
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);
        
        Label lblCodigo = new Label("Codigo");
        Label lblFecha = new Label("Fecha de Registro");
        Label lblId = new Label("Id");
        Label lblTipo = new Label("Tipo");  //cedula
        Label lblRazonSocial = new Label("Razon Social");
        Label lblNombres = new Label("Nombres");
        Label lblApellidos = new Label("Apellidos");
        Label lblDireccion = new Label("Direccion");
        Label lblCelular = new Label("Telefono Celular");
        Label lblTelefono = new Label("Telefono Convencional");
        Label lblEmail = new Label("Email");
        
        TextField tfCodigo = new TextField();
        TextField tfFecha = new TextField();
        TextField tfId = new TextField();
        TextField tfRazonSocial = new TextField();
        TextField tfNombres = new TextField();
        TextField tfApellidos = new TextField();
        TextField tfDireccion = new TextField();
        TextField tfCelular = new TextField();
        TextField tfTelefono = new TextField();
        TextField tfEmail = new TextField();
        
        layout.add(lblCodigo, 0, 0, 1, 1);
        layout.add(tfCodigo, 1, 0, 1, 1);
        layout.add(lblFecha, 2, 0, 1, 1);
        layout.add(tfFecha, 3, 0, 1, 1);
        layout.add(lblId, 0, 1, 1, 1);
        layout.add(tfId, 1, 1, 1, 1);
        layout.add(lblTipo, 2, 1, 1, 1);
        
        layout.add(lblRazonSocial, 0, 2, 1, 1);
        layout.add(tfRazonSocial, 1, 2, 3, 1);
        layout.add(lblNombres, 0, 3, 1, 1);
        layout.add(tfNombres, 1, 3, 3, 1);
        layout.add(lblApellidos, 0, 4, 1, 1);
        layout.add(tfApellidos, 1, 4, 3, 1);
        layout.add(lblDireccion, 0, 5, 1, 1);
        layout.add(tfDireccion, 1, 5, 3, 1);
        layout.add(lblCelular, 0, 6, 1, 1);
        layout.add(tfCelular, 1, 6, 3, 1);
        layout.add(lblTelefono, 0, 7, 1, 1);
        layout.add(tfTelefono, 1, 7, 3, 1);
        layout.add(lblEmail, 0, 8, 1, 1);
        layout.add(tfEmail, 1, 8, 3, 1);
        
        Button confirmar = new Button("Ingresar");
        Button regresar = new Button("Menu Principal");
       
        regresar.setOnAction(e -> regresarMenuPrincipal());
        
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().add("Cedula");
        choiceBox.getItems().add("Otra cosa que no sea cedula...");
        choiceBox.setValue("Cedula");
        
        layout.add(choiceBox, 3, 1, 1, 1);
        
        layout.add(regresar, 1, 9);
        layout.add(confirmar, 3, 9);
        
        Scene scene = new Scene(layout, 700, 400);
        windowC.setScene(scene);
        windowC.show();        
    }

    public void buscarCliente(){
        Stage windowC = new Stage();
        windowC.setTitle("Buscar Cliente");
        //Definicion del layout
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);
        
        Label lblCodigo = new Label("Codigo");
        Label lblFecha = new Label("Fecha de Registro");
        Label lblId = new Label("Id");
        Label lblTipo = new Label("Tipo");  //cedula
        Label lblRazonSocial = new Label("Razon Social");
        Label lblNombres = new Label("Nombres");
        Label lblApellidos = new Label("Apellidos");
        Label lblDireccion = new Label("Direccion");
        Label lblCelular = new Label("Telefono Celular");
        Label lblTelefono = new Label("Telefono Convencional");
        Label lblEmail = new Label("Email");
        
        TextField tfCodigo = new TextField();
        TextField tfFecha = new TextField();
        TextField tfId = new TextField();
        TextField tfRazonSocial = new TextField();
        TextField tfNombres = new TextField();
        TextField tfApellidos = new TextField();
        TextField tfDireccion = new TextField();
        TextField tfCelular = new TextField();
        TextField tfTelefono = new TextField();
        TextField tfEmail = new TextField();
        
        layout.add(lblCodigo, 0, 0, 1, 1);
        layout.add(tfCodigo, 1, 0, 1, 1);
        layout.add(lblFecha, 2, 0, 1, 1);
        layout.add(tfFecha, 3, 0, 1, 1);
        layout.add(lblId, 0, 1, 1, 1);
        layout.add(tfId, 1, 1, 1, 1);
        layout.add(lblTipo, 2, 1, 1, 1);
        
        layout.add(lblRazonSocial, 0, 2, 1, 1);
        layout.add(tfRazonSocial, 1, 2, 3, 1);
        layout.add(lblNombres, 0, 3, 1, 1);
        layout.add(tfNombres, 1, 3, 3, 1);
        layout.add(lblApellidos, 0, 4, 1, 1);
        layout.add(tfApellidos, 1, 4, 3, 1);
        layout.add(lblDireccion, 0, 5, 1, 1);
        layout.add(tfDireccion, 1, 5, 3, 1);
        layout.add(lblCelular, 0, 6, 1, 1);
        layout.add(tfCelular, 1, 6, 3, 1);
        layout.add(lblTelefono, 0, 7, 1, 1);
        layout.add(tfTelefono, 1, 7, 3, 1);
        layout.add(lblEmail, 0, 8, 1, 1);
        layout.add(tfEmail, 1, 8, 3, 1);
        
        Button btnBuscar = new Button("Buscar");
        Button regresar = new Button("Menu Principal");
        
        layout.add(regresar, 1, 9);
        layout.add(btnBuscar, 3, 9);
        
        Scene scene = new Scene(layout, 700, 400);
        windowC.setScene(scene);
        windowC.show();        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Llene los datos necesarios para realizar la busqueda");
        
        btnBuscar.setOnAction(e-> {
            System.out.println("hola");
            BorderPane bpLayout = new BorderPane();
            VBox vbox = new VBox();
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(8);
            
            bpLayout.setCenter(vbox);
            
            Scene scene2 = new Scene(bpLayout, 700, 500);
            windowC.setScene(scene2);
            presentarCliente();
        });
        
        regresar.setOnAction(e -> regresarMenuPrincipal());
        
        alert.showAndWait();
    }
    
    public static void regresarMenuPrincipal(){
        Stage confirmWindow = new Stage();
        confirmWindow.initModality(Modality.APPLICATION_MODAL);
        confirmWindow.setTitle("Regresar");
        confirmWindow.setMinWidth(350);
        Label label = new Label();
        label.setText("Desea regresar al menu principal?\nPerdera los datos regristrados");

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
            /*
        yesButton.setOnAction(e -> {
            Sistema_Facturacion.main(args);
            confirmWindow.close();
            windowC.close();
        });*/
        noButton.setOnAction(e -> {
            confirmWindow.close();
        });
        VBox layout = new VBox(10);

        //Add buttons
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        confirmWindow.setScene(scene);
        confirmWindow.showAndWait();
    }
    
    public static void presentarCliente(){
        Stage windowC = new Stage();
        windowC.setTitle("Cliente");
        
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);
        
        Label lblCodigo = new Label("Codigo");
        Label lblFecha = new Label("Fecha de Registro");
        Label lblId = new Label("Id");
        Label lblTipo = new Label("Tipo");  //cedula
        Label lblRazonSocial = new Label("Razon Social");
        Label lblNombres = new Label("Nombres");
        Label lblApellidos = new Label("Apellidos");
        Label lblDireccion = new Label("Direccion");
        Label lblCelular = new Label("Telefono Celular");
        Label lblTelefono = new Label("Telefono Convencional");
        Label lblEmail = new Label("Email");
        
        TextField tfCodigo = new TextField();
        TextField tfFecha = new TextField();
        TextField tfId = new TextField();
        TextField tfTipo = new TextField();
        TextField tfRazonSocial = new TextField();
        TextField tfNombres = new TextField();
        TextField tfApellidos = new TextField();
        TextField tfDireccion = new TextField();
        TextField tfCelular = new TextField();
        TextField tfTelefono = new TextField();
        TextField tfEmail = new TextField();
        
        tfCodigo.setDisable(true);
        tfFecha.setDisable(true);
        tfId.setDisable(true);
        tfTipo.setDisable(true);
        tfRazonSocial.setDisable(true);
        tfNombres.setDisable(true);
        tfApellidos.setDisable(true);
        tfDireccion.setDisable(true);
        tfCelular.setDisable(true);
        tfTelefono.setDisable(true);
        tfEmail.setDisable(true);
        
        layout.add(lblCodigo, 0, 0, 1, 1);
        layout.add(tfCodigo, 1, 0, 1, 1);
        layout.add(lblFecha, 2, 0, 1, 1);
        layout.add(tfFecha, 3, 0, 1, 1);
        layout.add(lblId, 0, 1, 1, 1);
        layout.add(tfId, 1, 1, 1, 1);
        layout.add(lblTipo, 2, 1, 1, 1);
        layout.add(lblRazonSocial, 0, 2, 1, 1);
        layout.add(tfRazonSocial, 1, 2, 3, 1);
        layout.add(lblNombres, 0, 3, 1, 1);
        layout.add(tfNombres, 1, 3, 3, 1);
        layout.add(lblApellidos, 0, 4, 1, 1);
        layout.add(tfApellidos, 1, 4, 3, 1);
        layout.add(lblDireccion, 0, 5, 1, 1);
        layout.add(tfDireccion, 1, 5, 3, 1);
        layout.add(lblCelular, 0, 6, 1, 1);
        layout.add(tfCelular, 1, 6, 3, 1);
        layout.add(lblTelefono, 0, 7, 1, 1);
        layout.add(tfTelefono, 1, 7, 3, 1);
        layout.add(lblEmail, 0, 8, 1, 1);
        layout.add(tfEmail, 1, 8, 3, 1);
        
        Button btnEditar = new Button("Editar");
        Button regresar = new Button("Menu Principal");
        Button btnEliminar = new Button("Eliminar");
        
        btnEditar.setOnAction(e->{
            tfCodigo.setDisable(false);
            tfFecha.setDisable(false);
            tfId.setDisable(false);
            tfTipo.setDisable(false);
            tfRazonSocial.setDisable(false);
            tfNombres.setDisable(false);
            tfApellidos.setDisable(false);
            tfDireccion.setDisable(false);
            tfCelular.setDisable(false);
            tfTelefono.setDisable(false);
            tfEmail.setDisable(false);
            
            Button btnConfirmar = new Button("Confirmar");
            
            btnConfirmar.setOnAction(f->{
                if(revisarIngreso()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Cliente actualizado");
                    alert.showAndWait();
                    
                    layout.getChildren().remove(btnConfirmar);
                    layout.add(btnEditar, 3, 9);
                    layout.add(btnEliminar, 2, 9);
                    
                }
            });
            
            layout.getChildren().remove(btnEditar);
            layout.getChildren().remove(btnEliminar);
            layout.add(btnConfirmar, 3, 9);
        });
        
        btnEliminar.setOnAction(g-> eliminarCliente());
        
        regresar.setOnAction(e -> regresarMenuPrincipal());
        
        layout.add(tfTipo, 3, 1, 1, 1);
        
        layout.add(regresar, 1, 9);
        layout.add(btnEliminar, 2, 9);
        layout.add(btnEditar, 3, 9);
        
        Scene scene = new Scene(layout, 700, 400);
        windowC.setScene(scene);
        windowC.show();
    }
    
    public static Boolean revisarIngreso(){
        return true;
    }
 
    public static void eliminarCliente(){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Cliente eliminado de la base de datos");
            alert.showAndWait(); 
            regresarMenuPrincipal();
    }
}
