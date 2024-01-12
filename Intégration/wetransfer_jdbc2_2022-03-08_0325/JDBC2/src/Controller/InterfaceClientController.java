/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author user
 */
import entities.Compte;
import entities.Materiel;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.CompteService;

public class InterfaceClientController implements Initializable {

    private final CompteService compteService;
    private List<Compte> cpts;

    public InterfaceClientController() {
        compteService = new CompteService();
    }
    @FXML
    private AnchorPane anch;

    @FXML
    private Button btnAjout;

    @FXML
    private Button btnGestionCpt;

    @FXML
    private Button btnMat;

    @FXML
    private Button btnMdf;

    @FXML
    private Button btnSupprimer;

    @FXML
    private TableColumn dn;

    @FXML
    private TableColumn email;

    @FXML
    private TableColumn nom;

    @FXML
    private TableColumn prenom;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn tel;

    public void afficherTableViewAll() throws SQLException {

        cpts = compteService.afficherComptes();

        tableView.setItems(FXCollections.observableArrayList(cpts));

        nom.setCellValueFactory(new PropertyValueFactory<Compte, String>("Nom") {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Compte, String> param) {
                Compte cpt = (Compte) param.getValue();
                return new ReadOnlyObjectWrapper<String>(cpt.getNom());
            }
        });

        prenom.setCellValueFactory(new PropertyValueFactory<Compte, String>("Prenom") {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Compte, String> param) {
                Compte cpt = (Compte) param.getValue();
                return new ReadOnlyObjectWrapper<String>(cpt.getPrenom());
            }
        });
        dn.setCellValueFactory(new PropertyValueFactory<Compte, String>("Date_naissance") {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Compte, String> param) {
                Compte cpt = (Compte) param.getValue();

                return new ReadOnlyObjectWrapper<String>(cpt.getDate_naissance().split(" ")[0]);
            }
        });
        tel.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("Tel") {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Compte, Integer> param) {
                Compte cpt = (Compte) param.getValue();
                return new ReadOnlyObjectWrapper<Integer>(cpt.getTel());
            }
        });
        email.setCellValueFactory(new PropertyValueFactory<Compte, String>("Email") {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Compte, String> param) {
                Compte cpt = (Compte) param.getValue();
                return new ReadOnlyObjectWrapper<String>(cpt.getEmail());
            }

        });

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            initData();
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initData() throws SQLException {

        afficherTableViewAll();
        tableView.getItems().clear();
        tableView.getItems().setAll(compteService.afficherComptes());

    }

    @FXML
    void GestionCptInterface(ActionEvent event) throws IOException {
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/gestionCompteAdmin.fxml"));
        anch.getChildren().clear();
        anch.getChildren().add(newLoadedPane);
    }

    @FXML
    void GestionMat(ActionEvent event) throws IOException {
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/interfacemateriel.fxml"));
        anch.getChildren().clear();
        anch.getChildren().add(newLoadedPane);

    }

    @FXML
    void Supprimer_Client(ActionEvent event) throws SQLException {
        Compte selected = (Compte) tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(selected);
        compteService.supprimerCompte(selected);
    }

    @FXML
    void Ajouter_Client(ActionEvent event) throws IOException {
        AnchorPane newLoadedPane;
        newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/Ajout_Compte.fxml"));

        Scene scene = new Scene(newLoadedPane);
        Stage stage = new Stage();
        stage.setTitle("Ajouter client");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Modifier_Client(ActionEvent event) throws IOException {

        Modifier_CompteController.compte = (Compte) tableView.getSelectionModel().getSelectedItem();
        if (Modifier_CompteController.compte != null) {

            Modifier_CompteController.interfaceClientController = this;

            AnchorPane newLoadedPane;
            newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/Modifier_Compte.fxml"));
            Scene scene = new Scene(newLoadedPane);
            Stage stage = new Stage();
            stage.setTitle("Modifier Compte");
            stage.setScene(scene);
            stage.show();
        }

    }
      @FXML
    void goback (ActionEvent event) throws IOException {
          AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/interfaceadmin.fxml"));
        anch.getChildren().clear();
        anch.getChildren().add(newLoadedPane);

    }
}
