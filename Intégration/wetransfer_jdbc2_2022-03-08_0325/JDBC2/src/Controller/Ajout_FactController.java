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
public class Ajout_FactController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button Ajout_Fact;

    @FXML
    private TextField id_piece;

    @FXML
    private TextField nom_prenom;

    @FXML
    private TextField prix_totale;
    
    private ServiceFacture serviceFacture = new ServiceFacture();

    @FXML
    void saveFact(ActionEvent event) {
        Facture f = new Facture();
        f.setNom_prenom(nom_prenom.getText());
        f.setPrix_total(Float.parseFloat(prix_totale.getText()));
        f.setId_piece(Integer.parseInt(id_piece.getText()));
        serviceFacture.ajout(f);
        
        Stage s = (Stage) Ajout_Fact.getScene().getWindow();
        s.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
