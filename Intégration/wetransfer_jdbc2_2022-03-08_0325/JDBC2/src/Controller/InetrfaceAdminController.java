/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GUI.ClassLoaderFXML;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class InetrfaceAdminController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnGestionCpt;
    @FXML
    private Button btnMat;
   
 



    @FXML
    void GestionCptInterface(ActionEvent event) throws IOException {
        
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/gestionCompteAdmin.fxml"));
        AnchorPane.getChildren().clear();
        AnchorPane.getChildren().add(newLoadedPane);

    }

    @FXML
    void GestionMat(ActionEvent event) throws IOException {
        
           AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/interfacemateriel.fxml"));
        AnchorPane.getChildren().clear();
        AnchorPane.getChildren().add(newLoadedPane);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


}
