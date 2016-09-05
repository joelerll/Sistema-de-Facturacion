/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factura;

import AlertBox.alertBox;
import Clases.ClienteDAO;
import Clases.FacturaDAO;
import Clases.FacturaVO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joelerll
 */
public class EliminarFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton btnAtras;

    @FXML
    private JFXTextField fecha;

    @FXML
    private JFXTextField cliente;

    @FXML
    private JFXTextField codigo;

    @FXML
    private JFXButton btnEliminar;

    @FXML
    private JFXTextField codigoBuscar,cedulaBuscar;

    @FXML
    private JFXButton btnBuscar,btnPDF;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        codigo.setDisable(true);
        fecha.setDisable(true);
        cliente.setDisable(true);
        btnEliminar.setDisable(true);
        btnPDF.setDisable(true);
    }
    
    @FXML
    public void handleButtonBuscarFactura() throws SQLException{
        FacturaVO factura;
        factura = FacturaDAO.getFactura(Integer.parseInt(codigoBuscar.getText()));
        if (factura.getId() == 0){
            alertBox.crearErrorBox(" ", "", "No existe esa factura");
        }else {
            codigo.setText(factura.getId()+"");
            fecha.setText(factura.getFecha().toString());
            cliente.setText(ClienteDAO.buscarId(factura.getCedula_C()));
        }
        if (factura.isAnulada()) {
            alertBox.crearAlertBox(" ", "", "La factura ya esta anulada");
        }else{
            btnEliminar.setDisable(false);
        }
        btnPDF.setDisable(false);
    }
    
    @FXML
    public void handleButtonEliminar(){
        FacturaDAO.anularFactura(codigo.getText());
        alertBox.crearAlertBox(" ", "", "La factura fue anulada");
        btnEliminar.setDisable(true);
        btnPDF.setDisable(true);
    }
    
    @FXML
    public void handleButtonRegresar(ActionEvent event) throws IOException
    {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("facturacionMenu.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }
    
    @FXML
    public void handeButtonBuscarPDF() throws IOException, ParseException{
        DateFormat dateAnio = new SimpleDateFormat("yyyy");
        DateFormat dateMes = new SimpleDateFormat("MM");
        DateFormat dateDia = new SimpleDateFormat("dd");
        System.out.println(fecha.getText());
        DateFormat datefort = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.S");
        Date ts = datefort.parse(fecha.getText());
        System.out.println(ts);
        String direccion = "facturas"+"/"+dateAnio.format(ts)+ "/"+ dateMes.format(ts)+"/"+dateDia.format(ts);
        System.out.println(direccion);
        File dir = new File(direccion);
        DateFormat dateF = new SimpleDateFormat("kk-mm-ss_dd-MM-yyyy");
        String fileName = direccion + "/"+ dateF.format(ts) + ".pdf";
        System.out.println(fileName);
        Process p = Runtime.getRuntime().exec(new String[]{"xpdf",fileName});
    }
    
}
