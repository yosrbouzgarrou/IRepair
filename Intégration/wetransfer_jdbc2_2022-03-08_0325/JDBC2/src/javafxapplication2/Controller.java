/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author aadhayma
 */
public class Controller implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfType;
    @FXML
    private ComboBox<?> id_categorie;
    @FXML
    private TextField tfprix;
    @FXML
    private TableView<?> tvser;
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colNom;
    @FXML
    private TableColumn<?, ?> colType;
    @FXML
    private TableColumn<?, ?> colC;
    @FXML
    private TableColumn<?, ?> colPrix;
    @FXML
    private Button btninsert;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private Button PDF1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void tocategorie(ActionEvent event) {
    }

    @FXML
    private void toservice(ActionEvent event) {
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @FXML
    private void Categoriefxml(ActionEvent event) {
    }

    @FXML
    private void PDF1(ActionEvent event) {
    }
    
}
