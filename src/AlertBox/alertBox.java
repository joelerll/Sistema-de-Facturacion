
package AlertBox;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
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

    public static int textInputCantidadBox(String title, String header, String message){
        int cantidad = -1;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(message);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            cantidad = Integer.parseInt(result.get());
            return cantidad;
        }
        return -1;
    }
}
