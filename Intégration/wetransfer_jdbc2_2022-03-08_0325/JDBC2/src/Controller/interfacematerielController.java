/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author PLANETAMIR
 */
public class interfacematerielController implements Initializable {

       @FXML
    private AnchorPane anch;

    @FXML
    private Button btn_GestionCompte;

    @FXML
    private Button btn_GestionMateriel;

    @FXML
    private Button btn_Gestion_de_Facture;

    @FXML
    private Button btn_Gestion_de_Materiel;

    @FXML
    void Btn_GestionCompte(ActionEvent event) throws IOException {

          AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/gestionCompteAdmin.fxml"));
        anch.getChildren().clear();
        anch.getChildren().add(newLoadedPane);
    }

    @FXML
    void Btn_GestionMAT(ActionEvent event) {

    }

    @FXML
    void Btn_GestionMateriel(ActionEvent event) {

    }

   

    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
      /**
     * Initializes the controller class.
     */
    
 @FXML
    void GestionDeFact(ActionEvent event) throws IOException {
        
          AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/Gestion_Fact.fxml"));
        anch.getChildren().clear();
        anch.getChildren().add(newLoadedPane);
    }

    @FXML
    void GestionDeMat(ActionEvent event) throws IOException {
          AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/Gestion-Mat.fxml"));
        anch.getChildren().clear();
        anch.getChildren().add(newLoadedPane);

    }
   @FXML
    void goback (ActionEvent event) throws IOException {
          AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/interfaceadmin.fxml"));
        anch.getChildren().clear();
        anch.getChildren().add(newLoadedPane);

    }

}
