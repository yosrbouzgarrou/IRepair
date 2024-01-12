/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nader
 */
public class Interfaceadmin2Controller implements Initializable {

    @FXML
    private Button poles;
    @FXML
    private Button stock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherpole(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent afficherpole = FXMLLoader.load(getClass().getResource("ajouterpole.fxml"));
        Scene afficherpole_secne = new Scene(afficherpole);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(afficherpole_secne);
        app_stage.show();
    }

    @FXML
    private void aficherstock(ActionEvent event) throws IOException {
               System.out.println("You clicked me!");
           Parent modifierCours = FXMLLoader.load(getClass().getResource("Stock.fxml"));
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
