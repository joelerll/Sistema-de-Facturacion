/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_facturacion;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author joelerll
 */
public class Sistema_Facturacion extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Facturacion.fxml"));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //height -40
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Scene scene = new Scene(root,width,height-40);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}


//metodo ajustar ventana
