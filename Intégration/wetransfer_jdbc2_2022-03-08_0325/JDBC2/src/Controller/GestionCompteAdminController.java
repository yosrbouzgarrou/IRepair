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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GestionCompteAdminController implements Initializable {

    @FXML
    private Button btn_gestion_client;
    @FXML
    private AnchorPane anch;

    @FXML
    private Button btnAjouter;

    @FXML
    private DatePicker tfDn;

    @FXML
    private TextField tfemail;

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfprenom;

    @FXML
    private TextField tftel;
    @FXML
    private Button btn_Grec;
    @FXML
    private Button btn_cpt;

    @FXML
    private Button btn_mat;
    

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnGestionCpt;

    @FXML
    private Button btnMat;



    @FXML
    void Gerer_Rec(ActionEvent event) throws IOException {
            AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/interfaceReclamation.fxml"));
        anch.getChildren().clear();
        anch.getChildren().add(newLoadedPane);

    }

    @FXML
    void GestionCptInterface(ActionEvent event) {

    }

    @FXML
    void GestionMat(ActionEvent event) throws IOException {
               AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/interfacemateriel.fxml"));
        anch.getChildren().clear();
        anch.getChildren().add(newLoadedPane);

    }

    @FXML
    void gererClients(ActionEvent event) throws IOException {
                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/interfaceClient.fxml"));
        anch.getChildren().clear();
        anch.getChildren().add(newLoadedPane);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
       @FXML
    void goback (ActionEvent event) throws IOException {
          AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/interfaceadmin.fxml"));
        anch.getChildren().clear();
        anch.getChildren().add(newLoadedPane);

    }

   
}
