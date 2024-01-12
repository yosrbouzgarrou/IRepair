/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Pole;
import entities.Stock;
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
 * @author nader
 */
public class serviceStock implements IService<Stock>{
    
    
    
    
      private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
    
    
    
        public serviceStock() {
        cnx = MyDB.getInstance().getConnection();
    }
        
        servicePole sp =new servicePole();
        


    @Override
    public void ajout(Stock stock) {
        
          String req = "insert into stock (nom_article,quantite_article,prix_article,id_pole) values ('" + stock.getNom_article() + "','" + stock.getQuantite_article() + "','" + stock.getPrix_article() + "','" + stock.getId_pole() + "')";
            if (rechercherPole(stock.getId_pole())){
                  try {
            ste = cnx.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(serviceStock.class.getName()).log(Level.SEVERE, null, ex);
        }
                
            }
            else  {System.out.println("id pole n'existe pas ");
      
            }
        
    }

    @Override
    public void modifier(Stock stock) {
        
           try {
            String req = "update stock set nom_article = ?, quantite_article = ?, prix_article = ?, id_pole = ? where id_article = ?" ;
            pst = cnx.prepareStatement(req);
            if (rechercherPole(stock.getId_pole())){
            pst.setString(1, stock.getNom_article());
            pst.setInt(2, stock.getQuantite_article()) ;
             pst.setFloat(3, stock.getPrix_article());
            pst.setInt(4, stock.getId_pole());
            pst.setInt(5, stock.getId_article());}
             else System.out.println(" id pole n'existe pas ");
           
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(servicePole.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        
        
    }

    @Override
    public void supprimer(int id_article) {
         try {
            String req = "delete from stock where id_article = ?";
            pst = cnx.prepareStatement(req);
            pst.setInt(1, id_article);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceStock.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public List<Stock> afficher() {
        
         String req = "select * from stock";
        List<Stock> listStock=new ArrayList<>();
        try {
                ste=cnx.createStatement();
                rs= ste.executeQuery(req);
                
           while(rs.next()){
              listStock.add(new Stock(rs.getInt("id_article"), rs.getString("nom_article"), rs.getInt("quantite_article"), rs.getFloat("prix_article"), rs.getInt("id_pole")));
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(servicePole.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listStock;
    }
    
    
     public boolean rechercherPole(int idPole) {
         List<Pole> list=new ArrayList<>();
         
         list=sp.afficher();

        return list.stream().anyMatch(e->e.getId_pole()==idPole);
                
    }

    public void supprimer(Stock p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
