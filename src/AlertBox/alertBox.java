
package AlertBox;

import javafx.scene.control.Alert;

public class alertBox {
    public static void crearAlertBox(String title, String header, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
        
    }
}
