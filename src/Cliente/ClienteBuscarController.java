package Cliente;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ClienteBuscarController implements Initializable {
    @FXML
    private JFXTextField tfNombres;
    @FXML
    private JFXTextField tfApellido;
    @FXML
    private JFXTextField tfDireccion;
    @FXML
    private JFXTextField tfCedula;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXTextField tfConvencional;
    @FXML
    private JFXTextField tfCelular;
    @FXML
    private JFXButton BtnRegresar;
    @FXML
    private JFXButton btnBuscar;
    
    @FXML
    private JFXTextField tfVPNombre;
    @FXML
    private JFXTextField tfVPCedula;
    @FXML
    private JFXTextField tfVPDireccion;
    @FXML
    private DatePicker tfVPFechaRegistro;
    
    
    @FXML
    private Label lblCTotal;    //muestra el total de Clientes encontrados.
    @FXML
    private Label lblCActual;   //muestra el Cliente actual que se muestra en la Vista Previa
    
     @FXML
    private JFXButton btnPrev;
    @FXML
    private JFXButton btnNext;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnEliminar;
    
    private int index;
    private List <Cliente> listaClientes;
    

    @FXML
    void regresarMenuPrincipal(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/MenuPrincipal/menuPrincipal.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }

    @FXML
    void buscar(ActionEvent event) {
        listaClientes = Cliente.buscarCliente(tfCedula.getText(), tfNombres.getText(), tfApellido.getText(), tfDireccion.getText(), tfCelular.getText(), tfConvencional.getText(), tfEmail.getText());
        index = 0;
        if(!listaClientes.isEmpty()){
            Cliente c = listaClientes.get(0);
            tfVPNombre.setText(c.getNombre_C() + " " + c.getApellido_C());
            tfVPCedula.setText(c.getCedula_C());
            tfVPDireccion.setText(c.getDireccion_C());
            
            int i = index + 1;
            lblCActual.setText(""+i);
            lblCTotal.setText(""+listaClientes.size()); 
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No se encontraron coincidencias");
            alert.showAndWait();
        }
        
        
        
        /*
        int i=1;
        for(Cliente c : listaClientes){
            System.out.println("Cliente #"+i);
            System.out.println("Cedula: " + c.getCedula_C());
            System.out.println("Nombre: " + c.getNombre_C());
            System.out.println("Apellido: " + c.getApellido_C());
            System.out.println("Direccion: " + c.getDireccion_C());
            System.out.println("Celular: " + c.getCelular_C());
            System.out.println("Email: " + c.getEmail_C());
            i++;
        }*/
        
    }
    
    @FXML
    void editar(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Cliente/clienteEditar.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }

    @FXML
    void eliminar(ActionEvent event) {
        if(Cliente.eliminarCliente(tfVPCedula.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Cliente eliminado");
            alert.showAndWait();
        }
        //Encero todos los valores
        tfVPCedula.setText(null);
        tfVPNombre.setText(null);
        tfVPDireccion.setText(null);
        lblCActual.setText(null);
        lblCTotal.setText(null);
    }
    
     @FXML
    void previous(ActionEvent event) {
        if(Integer.parseInt(lblCActual.getText())>1){
            index--;
            Cliente c = listaClientes.get(index);
            tfVPNombre.setText(c.getNombre_C() + " " + c.getApellido_C());
            tfVPCedula.setText(c.getCedula_C());
            tfVPDireccion.setText(c.getDireccion_C());
            int i = index+1;
            lblCActual.setText(""+i);  
            
        }
    }

    @FXML
    void next(ActionEvent event) { 
        if(!lblCActual.getText().equals(lblCTotal.getText())){
            index++;
            Cliente c = listaClientes.get(index);
            tfVPNombre.setText(c.getNombre_C() + " " + c.getApellido_C());
            tfVPCedula.setText(c.getCedula_C());
            tfVPDireccion.setText(c.getDireccion_C());
            
            int i = index+1;
            lblCActual.setText(""+i);
        }
    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            }    
    
}
