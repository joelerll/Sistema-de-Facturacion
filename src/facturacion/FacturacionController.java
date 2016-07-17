
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion;

import Factura.Factura;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

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
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Factura f =new Factura();
        codigoFactura.setText(f.Last().getId_Orden());
        
      
       Date date= new Date();
       SimpleDateFormat formateador = new SimpleDateFormat("MMMM dd 'del' yyyy", new Locale("es"));
       String fe=formateador.format(date);
       //String str = String.format("%1$s %2$tB %2$td, %2$tY", "",date);
       fecha.setText(fe);
    }    
    
}
