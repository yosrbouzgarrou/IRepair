/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.Compte;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.CompteService;

/**
 * import FXML Controller class
 *
 * @author user
 */
public class Modifier_CompteController implements Initializable {

    @FXML
    private Button btn_modifier;

    @FXML
    private DatePicker tfDn;

    @FXML
    private Pane tfEmail;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfPrenom;

    @FXML
    private TextField tfTel;

    @FXML
    private TextField tfemail;
    /**
     * Initializes the controller class.
     */
    private CompteService compteService;
    public static Compte compte;

    public static InterfaceClientController interfaceClientController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        compteService = new CompteService();
        if (compte != null) {
            tfNom.setText(compte.getNom());
            tfPrenom.setText(compte.getPrenom());

            String tel = String.valueOf(compte.getTel());
            tfTel.setText(tel);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if (compte.getDate_naissance() != null) {
                LocalDate date = LocalDate.parse(compte.getDate_naissance().split(" ")[0], formatter);
                tfDn.setValue(date);
            }
        }
        tfemail.setText(compte.getEmail());
    }

    @FXML
    void modifCpt(ActionEvent event) {
        Compte updateCompte = new Compte(
                compte.getId_client(),
                tfNom.getText(),
                tfPrenom.getText(),
                tfDn.getValue().toString(),
                compte.getTel(),
                tfemail.getText()
        );
        compteService.modifierCompte(updateCompte);
        if (interfaceClientController != null) {

            try {
                interfaceClientController.afficherTableViewAll();
            } catch (SQLException ex) {
                Logger.getLogger(Modifier_CompteController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        Stage stage = (Stage) tfNom.getScene().getWindow();
        stage.close();

    }
}
