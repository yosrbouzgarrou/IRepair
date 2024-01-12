/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import entities.Compte;
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
public class CompteService {

    private Connection con = MyDB.getInstance().getConnection();
    private Statement ste;

    public CompteService() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    /*  ajout Compte */
    public void ajouterCompte(Compte compte) throws SQLException {
        String req1 = "INSERT INTO `compte` (`Nom`,`Prenom`,`Date_naissance`,`Tel`,`Email`) "
                + "VALUES ('" + compte.getNom() + "', '" + compte.getPrenom() + "','" + compte.getDate_naissance() + "','" + compte.getTel() + "','" + compte.getEmail() + "');";
        ste.executeUpdate(req1);
        System.out.println("Compte ajouté");
    }

    /*modifier compte */
    public void modifierCompte(Compte compte) {
        try {
            String req = "update compte set Nom = ? ,Prenom = ?, Date_naissance = ?, Tel = ?, Email = ? where Id_Client = ?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, compte.getNom());
            ps.setString(2, compte.getPrenom());
            ps.setString(3, compte.getDate_naissance());
            ps.setInt(4, compte.getTel());
            ps.setString(5, compte.getEmail());
            ps.setInt(6, compte.getId_client());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CompteService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerCompte(Compte compte) throws SQLException {
        String req1 = "DELETE FROM compte where Id_Client=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setInt(1, compte.getId_client());

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("compte supprime");
        } else {
            System.out.println("compte non supprime");
        }
    }

    public Vector<Compte> afficherCompteParId(int id_client) throws SQLException {

        Vector<Compte> compteList = new Vector<Compte>();

        String req1 = "SELECT * FROM compte where id_client=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setInt(1, id_client);

        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            Compte cpt = new Compte();
            cpt.setId_client(result.getInt("id_client"));
            cpt.setNom(result.getString("Nom"));
            cpt.setPrenom(result.getString("Prenom"));
            cpt.setDate_naissance(result.getString("Date_naissance"));
            cpt.setTel(result.getInt("Tel"));
            cpt.setEmail(result.getString("Email"));

            compteList.add(cpt);

        }
        System.out.println(compteList);
        return compteList;
    }

    public List<Compte> afficherComptes() throws SQLException {
        List<Compte> cpts = new ArrayList<>();

        String req1 = "SELECT * FROM compte";
        PreparedStatement preparedStatement = con.prepareStatement(req1);

        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            Compte cpt = new Compte();
            cpt.setId_client(result.getInt("Id_client"));
            cpt.setNom(result.getString("Nom"));
            cpt.setPrenom(result.getString("Prenom"));
            cpt.setDate_naissance(result.getString("Date_naissance"));
            cpt.setTel(result.getInt("Tel"));
            cpt.setEmail(result.getString("Email"));

            cpts.add(cpt);

        }

        return cpts;
    }

    public List<Integer> idClientsList() throws SQLException {
        List<Integer> idClientsList = new ArrayList<>();

        String req1 = "SELECT Id_Client FROM compte";
        PreparedStatement preparedStatement = con.prepareStatement(req1);

        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {

            idClientsList.add(result.getInt("Id_Clients"));

        }

        return idClientsList;
    }

}
