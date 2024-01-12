/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.Materiel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.CompteService;
import services.ServiceMateriel;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author PLANETAMIR
 */
public class Ajout_MatController implements Initializable {

    Connection cnx;

    public Ajout_MatController() throws SQLException {
        this.cs = new ServiceMateriel();
        cnx = MyDB.getInstance().getConnection();

    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_confirmer_Ajout;

    @FXML
    private TextField client;

    @FXML
    private DatePicker date_dist;

    @FXML
    private TextField probleme;

    @FXML
    private TextField type_piece;
    @FXML
    private ComboBox cb;
    @FXML
    private AnchorPane anch;

    ServiceMateriel cs;
    CompteService cptService = new CompteService();

    @FXML
    void Confirmer_Ajout(ActionEvent event) throws SQLException {

        LocalDate localDate = date_dist.getValue();

        Materiel mat;

        mat = new Materiel(type_piece.getText(), probleme.getText(), localDate.toString(), (int) cb.getSelectionModel().getSelectedItem());

        cs.ajout(mat);
        Stage s = (Stage) anch.getScene().getWindow();
        s.close();
    }
    ObservableList ol = FXCollections.observableArrayList();

    public void fillcombo() throws SQLException {
        PreparedStatement pst;
        String query = "select Id_Client from compte";
        pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            ol.add(rs.getInt("Id_Client"));
            cb.setItems(ol);

        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fillcombo();
        } catch (SQLException ex) {
            Logger.getLogger(Ajout_MatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
  

}
