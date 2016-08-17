
package Estadisticas;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXListView;
import item.Item;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
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
    private JFXListView<?> lvYearList;
    @FXML
    private JFXListView<?> lvItemList;
    @FXML
    private JFXListView<Label> lvMonthList;
    @FXML
    private JFXButton btnRegresar;
    
    public static String mes;
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
    
    public void crearPieChart(String mesPC){
        double x;
        List<Item> resultado = item.Item.buscarPorFecha("2016", mesPC, "01");
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for(Item i:resultado){
            valor = i.getPrecio();
            x = valor.doubleValue();
            pieChartData.add(new PieChart.Data(i.getNombre(), x));
        }
        pieChartMes.setData(pieChartData);
    }
    
    final EventHandler<MouseEvent> myHandler = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
            Label lbl = (Label) event.getSource();
            if(event.getSource()== enero){
                crearPieChart("01");
            }
            if(event.getSource()== febrero){
                crearPieChart("02");
            }
            if(event.getSource()== marzo){
                crearPieChart("03");
            }
            if(event.getSource()== abril){
                crearPieChart("04");
            }
            if(event.getSource()== mayo){
                crearPieChart("05");
            }
            if(event.getSource()== junio){
                crearPieChart("06");
            }
            if(event.getSource()== julio){
                crearPieChart("07");
            }
            if(event.getSource()== agosto){
                crearPieChart("08");
            }
            if(event.getSource()== septiembre){
                crearPieChart("09");
            }
            if(event.getSource()== octubre){
                crearPieChart("10");
            }
            if(event.getSource()== noviembre){
                crearPieChart("11");
            }
            if(event.getSource()== diciembre){
                crearPieChart("12");
            }
        }
    };
}
