
package AlertBox;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class alertBox {
    public static void crearAlertBox(String title, String header, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /*  Modo de uso crearConfiramcionBox
    Optional<ButtonType> result = alertBox.crearConfirmationBox(String title,String header, String mensaje);
        if (result.get() == ButtonType.OK){
           
        }else{
        }
     */ 
    public static Alert crearConfirmationBox(String title,String header, String message)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.initStyle(StageStyle.UTILITY);
        return alert;
    }
    
    public static void crearErrorBox(String title, String header,String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }

    
}
