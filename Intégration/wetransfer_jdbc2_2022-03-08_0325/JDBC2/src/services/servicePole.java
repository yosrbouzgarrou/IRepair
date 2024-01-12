/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Pole;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author nader
 */
public class servicePole implements IService<Pole> {
 
    
    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public servicePole() {
        cnx = MyDB.getInstance().getConnection();
    }

    
    
    
    
    @Override
    public void ajout(Pole pole) {
        
        
         String req = "insert into pole (nom_pole, lieu_pole) values ('" + pole.getNom_pole() + "','" + pole.getLieu_pole() + "')";

        try {
            ste = cnx.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(servicePole.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }

    @Override
    public void modifier(Pole pole) {
        
         try {
            String req = "update pole set nom_pole = ?, lieu_pole = ?  where id_pole = ?" ;
            pst = cnx.prepareStatement(req);
            pst.setString(1, pole.getNom_pole());
            pst.setString(2, pole.getLieu_pole()) ;  
            pst.setInt(3, pole.getId_pole());
           
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(servicePole.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        

    }

    @Override
    public void supprimer(int id_pole) {
       try {
            String req = "delete from pole where id_pole = ?";
            pst = cnx.prepareStatement(req);
            pst.setInt(1, id_pole);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicePole.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
    
    
    }

    @Override
    public List<Pole> afficher() {
        
        String req = "select * from pole";
        List<Pole> list=new ArrayList<>();
        try {
                ste=cnx.createStatement();
                rs= ste.executeQuery(req);
                
           while(rs.next()){
              list.add(new Pole(rs.getInt("id_pole"), rs.getString("nom_pole"), rs.getString("lieu_pole")));
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(servicePole.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }

    public void supprimer(Pole p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
