/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Devis;
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

public class ServiceDevis implements IService<Devis> {

    Connection cnx;

    public ServiceDevis() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajout(Devis t) {
        try {
            String req = "insert into devis (prix,Id_Client,date_devis) values"
                    + " ( '" + t.getPrix() + "', '" + t.getId_Client() + "','" + t.getDate_devis() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDevis.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void modifier(Devis t) {
        try {
            String req = "update devis set prix = ?, Id_Client = ? , date_devis = ? where id_devis = ?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDouble(1, t.getPrix());
            ps.setInt(2, t.getId_Client());
            ps.setString(3, t.getDate_devis());
            ps.setInt(4, t.getId_devis());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDevis.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   // @Override
    public void supprimer(Devis t) {
        try {
            String req = "delete from devis where id_devis = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getId_devis());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDevis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Devis> afficher() {
        List<Devis> list = new ArrayList<>();
        try {
            String req ="select * from devis";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Devis d = new Devis();
                d.setId_devis(rs.getInt(1));
                d.setPrix(rs.getDouble(2));
         
                d.setId_Client(rs.getInt(3));
                d.setDate_devis(rs.getString("date_devis"));
                list.add(d);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDevis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Integer> afficherid() {
        List<Integer> list = new ArrayList<>();
        try {
            String req ="select Id_Client from compte";
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
     public List<Integer> statiddevis() {
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
      public List<Integer> statprixdevis() {
        List<Integer> list = new ArrayList<>();
        try {
            String req ="select prix from devis";
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
