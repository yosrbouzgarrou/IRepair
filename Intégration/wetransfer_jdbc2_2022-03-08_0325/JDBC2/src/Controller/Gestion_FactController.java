/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.Facture;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceFacture;

/**
 * FXML Controller class
 *
 * @author PLANETAMIR
 */
public class Gestion_FactController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initData();
        
        nompre.setCellValueFactory(new PropertyValueFactory<Facture, String>("nom_prenom") {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Facture, String> param) {
                Facture r = (Facture) param.getValue();
                return new ReadOnlyObjectWrapper<String>(r.getNom_prenom());
            }
        });
        
        prix.setCellValueFactory(new PropertyValueFactory<Facture, String>("prix_total") {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Facture, String> param) {
                Facture r = (Facture) param.getValue();
                return new ReadOnlyObjectWrapper<String>(r.getPrix_total() + "");
            }
        });
        
        ref.setCellValueFactory(new PropertyValueFactory<Facture, String>("id_piece") {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Facture, String> param) {
                Facture r = (Facture) param.getValue();
                return new ReadOnlyObjectWrapper<String>(r.getId_piece()+ "");
            }
        });
    }
    /**
     * Initializes the controller class.
     */

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSupp;

    @FXML
    private Button btn_ajout;

    @FXML
    private Button btn_modif;

    @FXML
    private TableColumn nompre;

    @FXML
    private TableColumn prix;

    @FXML
    private TableColumn ref;
    
    @FXML
    private TableView tv;
    
    @FXML
    private TextField criteria;

    private final ServiceFacture serviceFacture = new ServiceFacture();

    @FXML
    void Ajouter_Facture(ActionEvent event) throws IOException {

        AnchorPane newLoadedPane;
        newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/Ajout_Fact.fxml"));
        Scene scene = new Scene(newLoadedPane);
        Stage stage = new Stage();
        stage.setTitle("Ajouter facture");
        stage.setScene(scene);
        stage.show();
        scene.addEventHandler(EventType.ROOT, (_event) -> {
            if (_event instanceof ActionEvent) {
                initData();
            }
        });
    }

    @FXML
    void Modifier_Fact(ActionEvent event) throws IOException {
        Facture f = (Facture) tv.getSelectionModel().getSelectedItem();
        if (f == null) {
            return;
        }
        
        Modifier_FactController.facture = f;
        
        AnchorPane newLoadedPane;
        newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/Modifier_Fact.fxml"));
        Scene scene = new Scene(newLoadedPane);
        Stage stage = new Stage();
        stage.setTitle("Modifier Materiel");
        stage.setScene(scene);
        stage.show();
        scene.addEventHandler(EventType.ROOT, (_event) -> {
            if (_event instanceof ActionEvent) {
                initData();
            }
        });
        

    }

    @FXML
    void Supp_Fact(ActionEvent event) {
        Facture f = (Facture) tv.getSelectionModel().getSelectedItem();
        if (f == null) {
            return;
        }
        
        serviceFacture.supprimer(f.getId_fact());
        initData();
    }
    
    void initData() {
        initData(null);
    }
    
    void initData(String criteria) {
        List<Facture> facts = serviceFacture.afficher(criteria);
        tv.getItems().clear();
        tv.getItems().addAll(facts);
    }

    @FXML
    void textChanged(KeyEvent event) {
        String criteriaStr = criteria.getText();
        initData(criteriaStr);
    }
}
