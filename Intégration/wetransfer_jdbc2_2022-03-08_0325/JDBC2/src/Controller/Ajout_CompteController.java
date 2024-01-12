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
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import services.CompteService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Ajout_CompteController implements Initializable {
    @FXML
    private Text errornumber;
    @FXML
    private Text errormail;
    @FXML
    private Button btnAjouter;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;



    @FXML
    private DatePicker tfDn;

    @FXML
    private TextField tfemail;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfprenom;

    @FXML
    private TextField tftel;

   CompteService cs = new CompteService();
    /**
     * Initializes the controller class.
     */
      
    private void Ajouter(ActionEvent event) {
      
//        Compte c;
//        Integer number = Integer.valueOf(tfTel.getText());
//        c = new Compte(tfNom.getText(), tfPrenom.getText(), tfDn.getTypeSelector(),number, tfEmail.getText());
//     
//        cs.ajout(c);
    }


    @FXML
    private void saveCompte(ActionEvent event) throws SQLException {
//        int id_client=Integer.parseInt(tfId.getText());
//        String nom =tfNom.getText();
//          String prenom =tfPrenom.getText();
//          Compte c = new Compte(id_client,nom,prenom);
//         CompteService pc = new CompteService();
//         pc.ajouterCompte(pc);

LocalDate localDate = tfDn.getValue();
            Compte cpt;
           
       int number=Integer.parseInt(tftel.getText());
    
     
       if (tftel.getText().length()!=8){
            errornumber.setVisible(true);
       }
       else if  ( tfemail.getText().contains("@")){
        cpt = new Compte(tfnom.getText(), tfprenom.getText(), localDate.toString(),number, tfemail.getText());
     
        cs.ajouterCompte(cpt);
       }
       else {
                   errormail.setVisible(true);
                   errornumber.setVisible(false);

           System.out.println("mail needs @");
       }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errormail.setVisible(false);
          errornumber.setVisible(false);
        // TODO
    }  
}
