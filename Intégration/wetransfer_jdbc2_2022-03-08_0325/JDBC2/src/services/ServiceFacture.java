/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Facture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.MyDB;

/**
 *
 * @author PLANETAMIR
 */
public class ServiceFacture {

    Connection cnx;

    public ServiceFacture() {
        cnx = MyDB.getInstance().getConnection();

    }

    public void ajout(Facture f) {
        try {
            String req = "insert into facture (id_fact, nom_prenom, prix_total, id_piece) values"
                    + " ( '" + f.getId_fact() + "','" + f.getNom_prenom() + "', '" + f.getPrix_total() + "', " + f.getId_piece() + ")";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFacture.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifier(Facture f) {
        try {
            String req = "update facture set nom_prenom= ? , prix_total= ?  where id_fact = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, f.getNom_prenom());
            ps.setFloat(2, f.getPrix_total());
            ps.setInt(3, f.getId_fact());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceFacture.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(int id_fact) {
        try {
            String req = "delete from facture where id_fact = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_fact);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFacture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Facture> afficher() {
        return afficher(null);
    }
    
    public List<Facture> afficher(String criteria) {
        List<Facture> list = new ArrayList<>();
        try {
            String req = "select * from facture";
            if (criteria != null && !"".equals(criteria.trim())) {
                String criteriaReq = " WHERE nom_prenom like '%"+ criteria +"%'";
                try {
                    criteriaReq += String.format(" OR id_fact = %d", Integer.parseInt(criteria));
                    criteriaReq += String.format(" OR prix_total = %d", Integer.parseInt(criteria));
                }catch (Exception e){}
                req+= criteriaReq;
            }
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Facture f = new Facture();
                f.setId_fact(rs.getInt(1));
                f.setNom_prenom(rs.getString("nom_prenom"));
                f.setPrix_total(rs.getFloat("prix_total"));
                f.setId_piece(rs.getInt("id_piece"));

                list.add(f);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceFacture.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
