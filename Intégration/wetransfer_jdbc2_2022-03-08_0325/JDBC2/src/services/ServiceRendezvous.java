/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Rendezvous;
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
 * @author Beyram
 */

public class ServiceRendezvous implements IService<Rendezvous> {

    Connection cnx;

    public ServiceRendezvous() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajout(Rendezvous t) {
        try {
            String req = "insert into rendezvous (date_rdv,id_devis,Id_Client) values"
                    + " ( '" + t.getDate_rdv() + "', '" + t.getId_devis() + "','" + t.getId_client() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRendezvous.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void modifier(Rendezvous t) {
        try {
            String req = "update rendezvous set date_rdv = ?, Id_devis = ? , id_client = ? where id_rdv = ?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getDate_rdv());
            ps.setInt(2, t.getId_devis());
            ps.setInt(3, t.getId_client());
            ps.setInt(4, t.getId_rdv());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRendezvous.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

 
    public void supprimer(Rendezvous t) {
        try {
            String req = "delete from rendezvous where id_rdv = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getId_rdv());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRendezvous.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Rendezvous> afficher() {
        List<Rendezvous> list = new ArrayList<>();
        try {
            String req ="select * from rendezvous";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Rendezvous p = new Rendezvous();
                p.setId_rdv(rs.getInt(1));
                p.setDate_rdv(rs.getString("date_rdv"));
                p.setId_devis(rs.getInt(3));
                p.setId_client(rs.getInt(4));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRendezvous.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<Integer> afficheridd() {
        List<Integer> list = new ArrayList<>();
        try {
            String req ="select id_devis from devis";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                
                list.add(rs.getInt(1));
               
         
               
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDevis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
