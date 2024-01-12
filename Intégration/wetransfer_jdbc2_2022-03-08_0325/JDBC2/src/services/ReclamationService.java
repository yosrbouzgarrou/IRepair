/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Compte;
import utils.MyDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import entities.Reclamation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PLANETAMIR
 */

/*
    @Override
    public void ajout(CompteService t) {
        try {
            String req = "insert into CompteService(Id_client,Nom ,Prenom,Date_naissance,Tel,Email) values"
                    + " ( '" + t.getId_client() + "', '" + t.getNom() + "','" + t.getPrenom() + t.getDate_naissance() "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRendezvous.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }/*
   
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 */
public class ReclamationService {

    private Connection con = MyDB.getInstance().getConnection();
    private Statement ste;

    public ReclamationService() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    /*modifier compte */
    public void modifierReclamation(Reclamation reclamation) {
        try {
            String req = "update reclamation set Etat = ? where id_rec = ?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, reclamation.getEtatRec());

            ps.setInt(2, reclamation.getId_rec());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerReclamation(Reclamation reclamation) throws SQLException {
        String req1 = "UPDATE reclamation SET archive = true where id_rec=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setInt(1, reclamation.getId_rec());

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("reclamation supprime");
        } else {
            System.out.println("reclamation non supprime");
        }
    }

    public Vector<Reclamation> getRecByIdClient(int id_Client) throws SQLException {

        Vector<Reclamation> reclamations = new Vector<Reclamation>();

        String req1 = "SELECT * FROM `Reclamation` where id_Client=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setInt(1, id_Client);

        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            Compte cpt = new Compte();
            cpt.setId_client(result.getInt("id_Client"));
            Reclamation r = new Reclamation(result.getInt(1), result.getString(2), result.getString(3), cpt, result.getInt("Etat"));

            reclamations.add(r);

        }
        System.out.println(reclamations);
        return reclamations;
    }

    public List< Reclamation> afficherReclamations() throws SQLException {
        List< Reclamation> recs = new ArrayList<>();

        String req1 = "SELECT * FROM  reclamation WHERE archive = false";
        PreparedStatement preparedStatement = con.prepareStatement(req1);

        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            Compte cpt = new Compte();
            cpt.setId_client(result.getInt("id_Client"));

            Reclamation rec = new Reclamation(result.getInt("id_rec"), result.getString("ReclamationMsg"), result.getString("Date_rec"), cpt, result.getInt("Etat"));

            recs.add(rec);

        }
        System.out.println(recs);
        return recs;
    }

}
