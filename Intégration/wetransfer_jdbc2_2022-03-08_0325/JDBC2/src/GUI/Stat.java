/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Stock;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import services.serviceStock;

 

/**
 * FXML Controller class
 *
 * @author nader
 */
public class Stat implements Initializable {

    @FXML
    private Button pole;
    @FXML
    private NumberAxis squantite;
    @FXML
    private CategoryAxis nom;
    @FXML
    private BarChart<String, Integer> barchart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    serviceStock sp = new serviceStock();
    List<Stock> stock =new ArrayList();
    List listeQte=new ArrayList();    
    List listeNom=new ArrayList();

     
        stock.addAll(sp.afficher());
           System.out.println(stock);
           
        for (int i =0 ; i< stock.size();i++){
        listeNom.add(    stock.get(i).getNom_article());  
       listeQte.add(stock.get(i).getQuantite_article());

        }
        System.out.println(listeQte + ","+ listeNom);
    ObservableList lista = FXCollections.observableArrayList(stock);
      squantite.setLabel("quantite");
        nom.setLabel("article");
           for(int v=0;v<lista.size();v++){
       XYChart.Series set1 = new XYChart.Series<>();
    //   deviss.get(v).toString
  
       set1.getData().add(new XYChart.Data(""+listeNom.get(v),listeQte.get(v)));
       
       barchart.getData().addAll(set1);           
           }   
    }   
    @FXML
    private void gogestion(ActionEvent event) throws IOException {
               System.out.println("You clicked me!");
           Parent modifierCours = FXMLLoader.load(getClass().getResource("interfaceadmin.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }
    @FXML
  private void interfaceadmin2(ActionEvent event) throws IOException {
               System.out.println("You clicked me!");
           Parent modifierCours = FXMLLoader.load(getClass().getResource("interfaceadmin2.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }
   @FXML
    private void tordv(ActionEvent event) throws IOException {
               System.out.println("You clicked me!");
           Parent modifierCours = FXMLLoader.load(getClass().getResource("interfaceadmin.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }
      @FXML
    private void tocat(ActionEvent event) throws IOException {
               System.out.println("You clicked me!");
           Parent modifierCours = FXMLLoader.load(getClass().getResource("cat.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }
    }
    

