/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author PLANETAMIR
 */
public class InterfaceReclamationController implements Initializable {

    private final ReclamationService reclamationService;
    private List<Reclamation> recs;

    public InterfaceReclamationController() {
        reclamationService = new ReclamationService();
    }
    @FXML
    private TableView tvRec;
    @FXML
    private TableColumn client;
    @FXML
    private TableColumn date_rec;
    @FXML
    private TableColumn messageRec;
    @FXML
    private TableColumn etat;
    @FXML
    private Button btnTraiter;
    @FXML
    private Button btn_retirer;
    @FXML
    private Button btn_cpt;

    @FXML
    private Button btn_mat;
    @FXML
    private AnchorPane anch;

    @FXML
    void BtnCpt(ActionEvent event) throws IOException {
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/gestionCompteAdmin.fxml"));
        anch.getChildren().clear();
        anch.getChildren().add(newLoadedPane);

    }

    @FXML
    void BtnMat(ActionEvent event) throws IOException {
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/interfacemateriel.fxml"));
        anch.getChildren().clear();
        anch.getChildren().add(newLoadedPane);

    }

    /**
     * Initializes the controller class.
     */
    @FXML
    private void Traiter_Rec(ActionEvent event) throws SQLException {
        Reclamation r = (Reclamation) tvRec.getSelectionModel().getSelectedItem();
        if (r != null) {
            r.setEtatRec(1);
            reclamationService.modifierReclamation(r);
            refreshData();
        }
    }

    // metier
    @FXML
    private void Retirer_Rec(ActionEvent event) throws SQLException {
        Reclamation r = (Reclamation) tvRec.getSelectionModel().getSelectedItem();
        if (r != null) {
            reclamationService.supprimerReclamation(r);
            refreshData();
        }
    }
    
    private void refreshData() throws SQLException {
        recs = reclamationService.afficherReclamations();
        tvRec.getItems().clear();
        tvRec.getItems().setAll(recs);
    }

    private void afficherTableViewAll() throws SQLException {

        recs = reclamationService.afficherReclamations();

        tvRec.setItems(FXCollections.observableArrayList(recs));

        client.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id_Client") {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Reclamation, Integer> param) {
                Reclamation r = (Reclamation) param.getValue();
                return new ReadOnlyObjectWrapper<Integer>(r.getCpt().getId_client());
            }
        });

        date_rec.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Date_rec") {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reclamation, String> param) {
                Reclamation cpt = (Reclamation) param.getValue();
                return new ReadOnlyObjectWrapper<String>(cpt.getDate_rec());
            }
        });
        messageRec.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("ReclamationMsg") {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reclamation, String> param) {
                Reclamation rec = (Reclamation) param.getValue();
                return new ReadOnlyObjectWrapper<String>(rec.getReclamationMsg());
            }
        });
        etat.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Etat") {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reclamation, String> param) {
                Reclamation rec = (Reclamation) param.getValue();
                String status = "Non traitée";
                if (rec.getEtatRec() == 1) {
                    status = "Traitée";
                }
                return new ReadOnlyObjectWrapper<String>(status);

            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            afficherTableViewAll();
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
