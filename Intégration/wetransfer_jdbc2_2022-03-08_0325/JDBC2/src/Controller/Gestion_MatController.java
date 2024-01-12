/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceMateriel;

/**
 * FXML Controller class
 *
 * @author PLANETAMIR
 */
public class Gestion_MatController implements Initializable {

    private final ServiceMateriel serviceMateriel;
    private List<Materiel> mats;

    public Gestion_MatController() throws SQLException {
        serviceMateriel = new ServiceMateriel();
    }

    @FXML
    private AnchorPane dn;
    @FXML
    private TableColumn ID_PIECE;
    @FXML
    private TableColumn TYPE_PIECE;
    @FXML
    private TableColumn PROBLEME;
    @FXML
    private TableColumn DATE_DISTRIBUTION;
    @FXML
    private TableColumn CLIENT;
    @FXML
    private Button btnMdf;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAjout;
    @FXML
    private TableView materiel;
    
    @FXML
    private TextField criteria;
    
    public void afficherTableViewAll() throws SQLException {

        mats = serviceMateriel.afficherMats();

        materiel.setItems(FXCollections.observableArrayList(mats));

        ID_PIECE.setCellValueFactory(new PropertyValueFactory<Materiel, Integer>("id_piece") {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Materiel, Integer> param) {
                Materiel r = (Materiel) param.getValue();
                return new ReadOnlyObjectWrapper<Integer>(r.getId_piece());
            }
        });

        TYPE_PIECE.setCellValueFactory(new PropertyValueFactory<Materiel, String>("type_piece") {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Materiel, String> param) {
                Materiel mat = (Materiel) param.getValue();
                return new ReadOnlyObjectWrapper<String>(mat.getType_piece());
            }
        });
        PROBLEME.setCellValueFactory(new PropertyValueFactory<Materiel, String>("probleme") {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Materiel, String> param) {
                Materiel mat = (Materiel) param.getValue();
                return new ReadOnlyObjectWrapper<String>(mat.getProbleme());
            }
        });
        DATE_DISTRIBUTION.setCellValueFactory(new PropertyValueFactory<Materiel, String>("date_distribution") {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Materiel, String> param) {
                Materiel mat = (Materiel) param.getValue();
                String str1 = String.valueOf(mat.getDate_dist());

                return new ReadOnlyObjectWrapper<String>(str1);

            }
        });

        CLIENT.setCellValueFactory(new PropertyValueFactory<Materiel, String>("date_distribution") {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Materiel, String> param) {
                Materiel mat = (Materiel) param.getValue();
                String str1 = String.valueOf(mat.getCpt().getNom());

                return new ReadOnlyObjectWrapper<String>(str1);

            }
        });

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initData();
    }

    private void initData() {
        initData(null);
    }
    
    private void initData(String criteria) {
        try {
            afficherTableViewAll();
            materiel.getItems().clear();
            materiel.getItems().setAll(serviceMateriel.afficherMats(criteria));

        } catch (SQLException ex) {
            Logger.getLogger(Gestion_MatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Modifier_Mat(ActionEvent event) throws IOException {
        Modifier_MatController.materiel = (Materiel) materiel.getSelectionModel().getSelectedItem();
        if(Modifier_MatController.materiel != null){
            
        Modifier_MatController.gestionController = this;
        
        AnchorPane newLoadedPane;
        newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/Modifier_Mat.fxml"));
        Scene scene = new Scene(newLoadedPane);
        Stage stage = new Stage();
        stage.setTitle("Modifier Materiel");
        stage.setScene(scene);
        stage.show();
        }
    }

    @FXML
    private void Supprimer_Mat(ActionEvent event) {
        Materiel selected = (Materiel) materiel.getSelectionModel().getSelectedItem();
        materiel.getItems().remove(selected);
        serviceMateriel.supprimer(selected.getId_piece());
    }

    @FXML
    private void Ajouter_Mat(ActionEvent event) throws IOException {
        AnchorPane newLoadedPane;
        newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/Ajout_Mat.fxml"));
        Scene scene = new Scene(newLoadedPane);
        Stage stage = new Stage();
        stage.setTitle("Ajouter Materiel");
        stage.setScene(scene);
        stage.show();
        scene.addEventHandler(EventType.ROOT, (_event) -> {
            if (_event instanceof ActionEvent) {
                initData();
            }
        });
    }
    
    @FXML
    void textChanged(KeyEvent event) {
        String criteriaStr = criteria.getText();
        initData(criteriaStr);
    }
   @FXML
    void goback(ActionEvent event) throws IOException {
          AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/interfaceadmin.fxml"));
        dn.getChildren().clear();
        dn.getChildren().add(newLoadedPane);

    }
}
