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

/**
 *
 * @author joelerll
 */
public class Sistema_Facturacion extends Application {
    @FXML
    Pane panelDatos;
    DBconnection db;
    SQL insert=new SQL();
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Sistema de Facturacion");
        Parent root = FXMLLoader.load(getClass().getResource("/facturacion/Facturacion.fxml"));
        Scene scene = new Scene(root,(dimensionesPantalla())[0],(dimensionesPantalla())[1]-40);
        stage.setScene(scene);
        //db=new DBconnection();
        //db.conectar();
        insert.setEmpleado("0965565489","Joel Eduardo Rodriguez LLamuca","23:89","24:29",1,15);
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

