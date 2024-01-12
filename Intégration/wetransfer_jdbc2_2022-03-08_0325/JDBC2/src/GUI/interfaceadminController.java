package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class interfaceadminController {

 private Stage stage;
 private Scene scene;
 private Parent root;

 
    @FXML
 public void tomenurdv(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("menurdv.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
    @FXML
  public void tomenudevis(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("menudevis.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
    @FXML
   public void tostats(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("statistique.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }

    @FXML
    private void toservice(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void tocategorie(ActionEvent event) throws IOException {
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
    @FXML
    void toaccount(ActionEvent event) throws IOException {
          Parent modifierCours = FXMLLoader.load(getClass().getResource("gestionCompteAdmin.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();

    }
    @FXML
    void toamaterial (ActionEvent event) throws IOException {
          Parent modifierCours = FXMLLoader.load(getClass().getResource("interfacemateriel.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();

    }
}
