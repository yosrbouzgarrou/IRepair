/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.Facture;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceFacture;

/**
 * FXML Controller class
 *
 * @author PLANETAMIR
 */
public class Modifier_FactController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id_fact;

    @FXML
    private TextField id_piece;

    @FXML
    private Button modifier_Facture;

    @FXML
    private TextField nom_prenom;

    @FXML
    private TextField prix_totale;
    
    public static Facture facture;
    
    private ServiceFacture serviceFacture = new ServiceFacture();

    @FXML
    void ModifierFact(ActionEvent event) {
        facture.setNom_prenom(nom_prenom.getText());
        facture.setPrix_total(Float.parseFloat(prix_totale.getText()));
        
        serviceFacture.modifier(facture);
        Stage s = (Stage) modifier_Facture.getScene().getWindow();
        s.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prix_totale.setText(facture.getPrix_total() + "");
        nom_prenom.setText(facture.getNom_prenom());
        id_piece.setText(facture.getId_piece() + "");
        id_fact.setText(facture.getId_fact() + "");
    }    
    
}
