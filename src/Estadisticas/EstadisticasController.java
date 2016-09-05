
package Estadisticas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXListView;
import item.Item;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class EstadisticasController implements Initializable {
    //LABELS
        Label enero = new Label("Enero");
        Label febrero = new Label("Febrero");
        Label marzo = new Label("Marzo");
        Label abril = new Label("Abril");
        Label mayo = new Label("Mayo");
        Label junio = new Label("Junio");
        Label julio = new Label("Julio");
        Label agosto = new Label("Agosto");
        Label septiembre = new Label("Septiembre");
        Label octubre = new Label("Octubre");
        Label noviembre = new Label("Noviembre");
        Label diciembre = new Label("Diciembre");
    @FXML
    private AnchorPane chartPane;
    @FXML
    private PieChart pieChartMes;
    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXListView<Label> lvYearList;
    
    @FXML
    private JFXListView<Label> lvMonthList;
    
    public static String year;
    public static String mes;       //en numero
    public static String month = "";
    public static BigDecimal valor;
    /*
    METODOS
    */
    @FXML
    void regresarMenuPrincipal(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/MenuPrincipal/menuPrincipal.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Label> months = FXCollections.observableArrayList(enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre);
        lvMonthList.setItems(months);
        
        lvMonthList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Label>(){
            @Override
            public void changed(ObservableValue<? extends Label> observable, Label oldValue, Label newValue) {
                month = newValue.getText();
                crearPieChart(year, month);
            }
        });
        for(int i =2010; i<2019; i++){
            lvYearList.getItems().add(new Label (""+ i));
        }
        ObservableList<Label> yList = lvYearList.getItems();
        for(Label lbl : yList){
            lbl.setOnMouseClicked(yearHandler);
        }
       
    } 
    
    public void crearPieChart(String yearPC, String mesPC){
        double x;
        String mesesNumeros[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        //buscar el numero del mes
        int numMes = 0;
        for(int i = 0; i<mesesNumeros.length; i++){
            if(mesesNumeros[i].equals(mesPC)){
                numMes = i+1;
            }
        }
        String mes1 = ""+numMes;
        if(numMes<10){
            mes1 = "0"+ numMes;
        }
        
       // System.out.println(year + "-" + mes1);
        List<Item> resultado = item.Item.buscarPorFecha(yearPC, mes1, "01");
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for(Item i:resultado){
            valor = i.getPrecio();
            x = valor.doubleValue();
            pieChartData.add(new PieChart.Data(i.getNombre(), x));
        }
        pieChartMes.setData(pieChartData);
        pieChartMes.setTitle("Gastos del mes de " + month + " del " + year);
        
        pieChartData.forEach(data ->
            data.nameProperty().bind(
                Bindings.concat(data.getName(), " $", data.pieValueProperty())
            )
        );
    }

    
    final EventHandler<MouseEvent> yearHandler = new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Label lbl = (Label) event.getSource();
                year=lbl.getText();
                System.out.println(year);
            }
        
    };
    
}
