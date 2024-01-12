/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Compte;
import entities.Materiel;
import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.MyDB;

/**
 *
 * @author PLANETAMIR
 */
public class ServiceMateriel {

    Connection cnx;
    private Statement ste;
    private CompteService compteService = new CompteService();

    public ServiceMateriel() throws SQLException {

        cnx = MyDB.getInstance().getConnection();
        ste = cnx.createStatement();

    }

    public void ajout(Materiel m) throws SQLException {

        String req1 = "INSERT INTO `materiel` (`type_piece`,`probleme`,`date_distribution`,`Id_Client`) "
                + "VALUES ('" + m.getType_piece() + "', '" + m.getProbleme() + "','" + m.getDate_dist() + "','" + m.getId_client() + "');";
        ste.executeUpdate(req1);
        System.out.println("Materiel ajouté");
    }

    public void modifier(Materiel m) {
        try {
            String req = "update materiel set type_piece= ? , probleme= ? , date_distribution=?, Id_Client=?  where id_piece = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, m.getType_piece());
            ps.setString(2, m.getProbleme());
            ps.setString(3, m.getDate_dist());
            ps.setInt(4, m.getId_client());
            ps.setInt(5, m.getId_piece());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceMateriel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(int id_piece) {
        try {
            String req = "delete from materiel where id_piece = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_piece);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFacture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List< Materiel> afficherMats() throws SQLException {
        return afficherMats(null);
    }

    public List< Materiel> afficherMats(String criteria) throws SQLException {
        List< Materiel> mats = new ArrayList<>();

        String req1 = "SELECT * FROM  materiel";
        if (criteria != null && !"".equals(criteria.trim())) {
            String criteriaReq = " WHERE type_piece like '%" + criteria + "%' OR probleme like '%"+ criteria + "%'";
            try {
                criteriaReq += String.format(" OR Id_Client = %d", Integer.parseInt(criteria));
                criteriaReq += String.format(" OR id_piece = %d", Integer.parseInt(criteria));
            } catch (Exception e) {
            }
            req1 += criteriaReq;
        }
        PreparedStatement preparedStatement = cnx.prepareStatement(req1);

        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            Compte cpt = compteService.afficherCompteParId(result.getInt("Id_Client")).firstElement();
            Timestamp timeStamp = result.getTimestamp("date_distribution");
            String distributionDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(timeStamp);
            Materiel mat = new Materiel(result.getInt("id_piece"), result.getString("type_piece"), result.getString("probleme"), distributionDate, cpt);

            mats.add(mat);

        }
        System.out.println(mats);
        return mats;
    }

}
