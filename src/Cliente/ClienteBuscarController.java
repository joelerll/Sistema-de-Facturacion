package Cliente;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class ClienteBuscarController implements Initializable {
    //ATRIBUTOS BUSCAR
    @FXML
    private JFXTextField tfCedula;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXTextField tfNombres;
    @FXML
    private JFXTextField tfApellido;
    @FXML
    private JFXTextField tfDireccion;
    @FXML
    private JFXTextField tfFechaRegistro;
    @FXML
    private JFXTextField tfConvencional;
    @FXML
    private JFXTextField tfCelular;
    @FXML
    private JFXButton BtnRegresar;
    @FXML
    private JFXButton btnBuscar;
    //ATRIBUTOS VISTA PREVIA
    @FXML
    private JFXTextField tfVPNombre;
    @FXML
    private JFXTextField tfVPCedula;
    @FXML
    private JFXTextField tfVPDireccion;
    @FXML
    private JFXTextField tfVPFechaRegistro;
    @FXML
    private JFXTextField tfVPEmail;
    @FXML
    private JFXTextField tfVPCelular;
    @FXML
    private JFXTextField tfVPApellido;
    @FXML
    private JFXTextField tfVPConvencional;
    //LABELS
    @FXML
    private Label lblCTotal;    //muestra el total de Clientes encontrados.
    @FXML
    private Label lblCActual;   //muestra el Cliente actual que se muestra en la Vista Previa
    @FXML
    private Label lblDe;
    //BOTONES
    @FXML
    private JFXButton btnPrev;
    @FXML
    private JFXButton btnNext;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnEliminar;
    //ATRIBUTOS DE LA CLASE
    private int index;
    private List <Cliente> listaClientes;
    String cedulaOriginal;
    
    @FXML
    private JFXButton btnConfirmar;
    
    @FXML
    private GridPane GridVistaPrevia;
    //METODOS
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
        listaClientes = Cliente.buscarCliente(tfCedula.getText(), tfFechaRegistro.getText(), tfNombres.getText(), tfApellido.getText(), tfDireccion.getText(), tfCelular.getText(), tfConvencional.getText(), tfEmail.getText());
        index = 0;
        if(!listaClientes.isEmpty()){
            Cliente c = listaClientes.get(0);
            LocalDate date = LocalDate.parse(c.getFecha_C(), DateTimeFormatter.ISO_DATE);
            tfVPNombre.setText(c.getNombre_C());
            tfVPCedula.setText(c.getCedula_C());
            tfVPFechaRegistro.setText(c.getFecha_C());
            tfVPDireccion.setText(c.getDireccion_C());
            tfVPApellido.setText(c.getApellido_C());
            tfVPCelular.setText(c.getCelular_C());
            tfVPConvencional.setText(c.getConvencional_C());
            tfVPEmail.setText(c.getEmail_C());
            int i = index + 1;
            lblCActual.setText(""+i);
            lblCTotal.setText(""+listaClientes.size()); 
            enableTextFields();
        }else{
            AlertBox.alertBox.crearAlertBox("Information Dialog", null, "No se encontraron coincidencias");
        }
        cedulaOriginal = tfVPCedula.getText();
    }
    
    @FXML
    void editar(ActionEvent event) throws IOException {
        if(revisarEdicion()){
            if (Cliente.editarCliente(cedulaOriginal, tfVPCedula.getText(), tfVPFechaRegistro.getText(), tfVPNombre.getText(), tfVPApellido.getText(), tfVPDireccion.getText(), tfVPCelular.getText(), tfVPConvencional.getText(), tfVPEmail.getText())) 
            {
                AlertBox.alertBox.crearAlertBox("Confirmation Dialog", null, "Cliente actualizado");
            }
        }
    }
    
    public void enableTextFields(){
        tfVPCedula.setDisable(false);
        tfVPNombre.setDisable(false);
        tfVPApellido.setDisable(false);
        tfVPDireccion.setDisable(false);
        tfVPEmail.setDisable(false);
        tfVPCelular.setDisable(false);
        tfVPConvencional.setDisable(false);
        tfVPFechaRegistro.setDisable(false);
    }
    
    public boolean revisarEdicion(){
        System.out.println(Cliente.buscarCliente(tfVPCedula.getText(), "", "", "", "", "", "", "").size());
         if(Cliente.buscarCliente(tfVPCedula.getText(),"", "", "", "", "", "", "").size()>1){      //Busca en la BD si existe otro registro con la misma cedula ingresada en el TextField
               //Tiene que ser >1 porque al buscar en la BD, si va a encontrar una coincidencia.
             AlertBox.alertBox.crearAlertBox("Warning Dialog", null, "Ya existe otro cliente con esa cedula!");
             return false;
         }
         if(tfVPCedula.getText().length()!=10){ //La cedula debe tener 10 digitos
             AlertBox.alertBox.crearAlertBox("Warning Dialog", null, "Cedula incorrecta. Debe tener 10 digitos");
             return false;
         }
        return true;
    }

    @FXML
    void eliminar(ActionEvent event) {
        if(Cliente.eliminarCliente(tfVPCedula.getText())){
            AlertBox.alertBox.crearAlertBox("Information Dialog", null, "Cliente eliminado");
        }else{
            AlertBox.alertBox.crearAlertBox("Warning Dialog", null, "No se pudo eliminar");
        }
        
        if(listaClientes.size()==1){        //SI SOLO HABIA UN CLIENTE ENCONTRADO, AL ELIMINARLO YA NO SE DEBE MOSTRAR NADA EN LA VISTA PREVIA
            //Encero todos los valores
            tfVPCedula.setText(null);
            tfVPNombre.setText(null);
            tfVPApellido.setText(null);
            tfVPDireccion.setText(null);
            tfVPCelular.setText(null);
            tfVPConvencional.setText(null);
            tfVPEmail.setText(null);
            tfVPFechaRegistro.setText(null);
            lblCActual.setText(null);
            lblCTotal.setText(null);
        }else{                              //SI HABIAN MAS CLIENTES QUE COINCIDIAN CON EL CRITERIO DE BUSQUEDA, AL ELIMINAR EL CLIENTE SELECCIONADO SE DEBEN MOSTRAR LOS QUE QUEDAN
            listaClientes.remove(index);        //SE ELIMINA EL CLIENTE ELIMINADO DE LA LISTA
            //SE VUELVE A PRESENTAR TODO COMO ANTES SIN EL CLIENTE ELIMINADO
            index = 0;
            Cliente c = listaClientes.get(0);
            LocalDate date = LocalDate.parse(c.getFecha_C(), DateTimeFormatter.ISO_DATE);
            tfVPFechaRegistro.setText(c.getFecha_C());
            tfVPNombre.setText(c.getNombre_C());
            tfVPCedula.setText(c.getCedula_C());
            tfVPDireccion.setText(c.getDireccion_C());
            tfVPApellido.setText(c.getApellido_C());
            tfVPCelular.setText(c.getCelular_C());
            tfVPConvencional.setText(c.getConvencional_C());
            tfVPEmail.setText(c.getEmail_C());
            int i = index + 1;
            lblCActual.setText(""+i);
            lblCTotal.setText(""+listaClientes.size()); 
            
            cedulaOriginal = c.getCedula_C();
        }
        
    }
    
     @FXML
    void previous(ActionEvent event) {
        //Para hacer prvious debe haber un cliente anterior, por lo que el label lblCActual debe indicar un valor >1
        if(Integer.parseInt(lblCActual.getText())>1){
            index--;
            Cliente c = listaClientes.get(index);
            LocalDate date = LocalDate.parse(c.getFecha_C(), DateTimeFormatter.ISO_DATE);
            tfVPFechaRegistro.setText(c.getFecha_C());
            tfVPNombre.setText(c.getNombre_C());
            tfVPCedula.setText(c.getCedula_C());
            tfVPDireccion.setText(c.getDireccion_C());
            tfVPApellido.setText(c.getApellido_C());
            tfVPCelular.setText(c.getCelular_C());
            tfVPConvencional.setText(c.getConvencional_C());
            tfVPEmail.setText(c.getEmail_C());
            int i = index+1;
            lblCActual.setText(""+i);  
            cedulaOriginal = c.getCedula_C();
        }
    }

    @FXML
    void next(ActionEvent event) { 
        if(!lblCActual.getText().equals(lblCTotal.getText())){
            index++;
            Cliente c = listaClientes.get(index);
            LocalDate date = LocalDate.parse(c.getFecha_C(), DateTimeFormatter.ISO_DATE);
            tfVPFechaRegistro.setText(c.getFecha_C());
            tfVPNombre.setText(c.getNombre_C());
            tfVPCedula.setText(c.getCedula_C());
            tfVPDireccion.setText(c.getDireccion_C());
            tfVPApellido.setText(c.getApellido_C());
            tfVPCelular.setText(c.getCelular_C());
            tfVPConvencional.setText(c.getConvencional_C());
            tfVPEmail.setText(c.getEmail_C());
            
            int i = index+1;
            lblCActual.setText(""+i);
            cedulaOriginal = c.getCedula_C();
        }
    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
