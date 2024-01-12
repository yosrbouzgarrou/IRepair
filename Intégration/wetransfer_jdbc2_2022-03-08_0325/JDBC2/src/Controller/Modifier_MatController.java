/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.Materiel;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceMateriel;

/**
 * FXML Controller class
 *
 * @author PLANETAMIR
 */
public class Modifier_MatController implements Initializable {

    @FXML
    private TextField mdate_dist;
    @FXML
    private TextField mprobleme;
    @FXML
    private TextField mtype_piece;
    @FXML
    private Button modifier_Materiel;
    @FXML
    private DatePicker date_dist;
    private ServiceMateriel serviceMateriel;
    
    public static Materiel materiel;
    public static Gestion_MatController gestionController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            serviceMateriel = new ServiceMateriel();
            if (materiel != null) {
                mtype_piece.setText(materiel.getType_piece());
                mprobleme.setText(materiel.getProbleme());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
                LocalDate date = LocalDate.parse(materiel.getDate_dist(), formatter);
                date_dist.setValue(date);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modifier_MatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ModifierMat(ActionEvent event) {
        Materiel udpatedMateriel = new Materiel(
                materiel.getId_piece(),
                mtype_piece.getText(),
                mprobleme.getText(),
                date_dist.getValue().toString(),
                materiel.getId_client()
        );
        serviceMateriel.modifier(udpatedMateriel);
        if(gestionController != null){
            try {
                gestionController.afficherTableViewAll();
            } catch (SQLException ex) {
                Logger.getLogger(Modifier_MatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       Stage stage = (Stage) mtype_piece.getScene().getWindow();
       stage.close();
    }
    
}
