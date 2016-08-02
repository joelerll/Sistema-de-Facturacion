package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import database.DBconnection;
import database.SQL;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.math.BigDecimal;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author joelerll
 */
public class Sistema_Facturacion extends Application {
    @FXML
    Pane panelDatos;
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Sistema de Facturacion");
        //stage.initStyle(StageStyle.TRANSPARENT);
        try{
            Parent root = FXMLLoader.load(getClass().getResource("VentanaLogin.fxml"));
            Scene scene = new Scene(root,600,400);
            stage.setScene(scene);
        }catch (Exception e){
            System.out.println("No cargo VentanaLogin.xml");
        }
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * @autor Joel Rodriguez
     * @return Redimenciona la ventana
     * 
     */
    public double [] dimensionesPantalla()
    {
        double dim[]=new double[2];
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //height -40
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        dim[0]=width;
        dim[1]=height;
        return dim;
    }
}

