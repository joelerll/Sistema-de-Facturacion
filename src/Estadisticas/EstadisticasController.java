
package Estadisticas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXListView;
import item.Item;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
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
import javafx.scene.paint.Color;
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
        
        for(int i =2010; i<2019; i++){
            lvYearList.getItems().add(new Label (""+ i));
        }
        
        ObservableList<Label> yList = lvYearList.getItems();
        for(Label lbl : yList){
            lbl.setOnMouseClicked(yearHandler);
        }
        
        lvMonthList.getItems().add(enero);
        lvMonthList.getItems().add(febrero);
        lvMonthList.getItems().add(marzo);
        lvMonthList.getItems().add(abril);
        lvMonthList.getItems().add(mayo);
        lvMonthList.getItems().add(junio);
        lvMonthList.getItems().add(julio);
        lvMonthList.getItems().add(agosto);
        lvMonthList.getItems().add(septiembre);
        lvMonthList.getItems().add(octubre);
        lvMonthList.getItems().add(noviembre);
        lvMonthList.getItems().add(diciembre);
        
        enero.setOnMouseClicked(myHandler);
        febrero.setOnMouseClicked(myHandler);
        marzo.setOnMouseClicked(myHandler);
        abril.setOnMouseClicked(myHandler);
        mayo.setOnMouseClicked(myHandler);
        junio.setOnMouseClicked(myHandler);
        julio.setOnMouseClicked(myHandler);
        agosto.setOnMouseClicked(myHandler);
        septiembre.setOnMouseClicked(myHandler);
        octubre.setOnMouseClicked(myHandler);
        noviembre.setOnMouseClicked(myHandler);
        diciembre.setOnMouseClicked(myHandler);
        
    } 
    
    public void crearPieChart(String yearPC, String mesPC){
        double x;
        List<Item> resultado = item.Item.buscarPorFecha(yearPC, mesPC, "01");
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for(Item i:resultado){
            valor = i.getPrecio();
            x = valor.doubleValue();
            pieChartData.add(new PieChart.Data(i.getNombre(), x));
        }
        pieChartMes.setData(pieChartData);
        pieChartMes.setTitle("Gastos del mes de " + month + " del " + year);
        
        //DoubleBinding total = Bindings.createDoubleBinding(() -> pieChartData.stream().collect(Collectors.summingDouble(PieChart.Data::getPieValue)), pieChartData);
               
        pieChartData.forEach(data ->
            data.nameProperty().bind(
                Bindings.concat(data.getName(), " $", data.pieValueProperty())
            )
        );
    }
    
    final EventHandler<MouseEvent> myHandler = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
            Label lbl = (Label) event.getSource();
            if(event.getSource()== enero){
                month = "enero";
                crearPieChart(year, "01");
            }
            if(event.getSource()== febrero){
                month = "febrero";
                crearPieChart(year, "02");
            }
            if(event.getSource()== marzo){
                month = "marzo";
                crearPieChart(year, "03");
            }
            if(event.getSource()== abril){
                month = "abril";
                crearPieChart(year, "04");
            }
            if(event.getSource()== mayo){
                month = "mayo";
                crearPieChart(year, "05");
            }
            if(event.getSource()== junio){
                month = "junio";
                crearPieChart(year, "06");
            }
            if(event.getSource()== julio){
                month = "julio";
                crearPieChart(year, "07");
            }
            if(event.getSource()== agosto){
                month = "agosto";
                crearPieChart(year, "08");
            }
            if(event.getSource()== septiembre){
                month = "septiembre";
                crearPieChart(year, "09");
            }
            if(event.getSource()== octubre){
                month = "octubre";
                crearPieChart(year, "10");
            }
            if(event.getSource()== noviembre){
                month = "noviembre";
                crearPieChart(year, "11");
            }
            if(event.getSource()== diciembre){
                month = "diciembre";
                crearPieChart(year, "12");
            }
        }
    };
    
    final EventHandler<MouseEvent> yearHandler = new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Label lbl = (Label) event.getSource();
                year=lbl.getText();
                System.out.println(year);
            }
        
    };
    
}
