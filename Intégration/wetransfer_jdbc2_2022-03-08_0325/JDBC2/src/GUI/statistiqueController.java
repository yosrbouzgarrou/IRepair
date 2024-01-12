/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.NumberAxisBuilder;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceRendezvous;
import entities.Rendezvous;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import services.ServiceDevis;
/**
 *
 * @author MSI
 */
public class statistiqueController implements Initializable {
     private Stage stage;
 private Scene scene;
 private Parent root;

 @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
 
      @FXML
    private BarChart<String, Number> barchart;
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
             //liste des id devis
          ServiceDevis sp = new ServiceDevis();
         List deviss = sp.statiddevis();
           ObservableList lista = FXCollections.observableArrayList(deviss);
           
           //liste des prix
             ServiceDevis sd = new ServiceDevis();
         List devisss = sd.statprixdevis();
           ObservableList listaa = FXCollections.observableArrayList(devisss);
            x.setLabel("Les devis");
        y.setLabel("Les Prix");
           for(int v=0;v<lista.size();v++){
       XYChart.Series set1 = new XYChart.Series<>();
    //   deviss.get(v).toString
       set1.getData().add(new XYChart.Data(""+deviss.get(v),devisss.get(v)));
       barchart.getData().addAll(set1);
           }
       
    //    XYChart.Series<String, Number> series = new XYChart.Series<String,Number>();
   //     series.getData().add(new XYChart.Data<String, Number>(Rendezvous.getId_devis() ,Rendezvous.getId_Client()));
        
      //  linechart.getData().add(series);
        
    }
    
    
     public void tointerfaceadmin(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("interfaceadmin.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }

      @FXML
    private void tocat(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("cat.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }
     @FXML
    private void tostock(ActionEvent event) throws IOException {
               System.out.println("You clicked me!");
           Parent modifierCours = FXMLLoader.load(getClass().getResource("interfaceadmin2.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }
   
    
}
