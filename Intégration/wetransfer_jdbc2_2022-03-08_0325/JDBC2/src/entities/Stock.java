/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author nader
 */
public class Stock {
    
    
    
    
    
    
    private int id_article; 
    private String nom_article;
    private int quantite_article;
    private float prix_article;
    private int id_pole;
    
    
        

    public Stock(String nom_article, int quantite_article, float prix_article, int id_pole) {
        this.nom_article = nom_article;
        this.quantite_article = quantite_article;
        this.prix_article = prix_article;
        this.id_pole = id_pole;
    }

     

    public Stock(int id_article, String nom_article, int quantite_article, float prix_article, int id_pole) {
        this.id_article = id_article;
        this.nom_article = nom_article;
        this.quantite_article = quantite_article;
        this.prix_article = prix_article;
        this.id_pole = id_pole;
    }

    public Stock() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_article() {
        return id_article;
    }

    public String getNom_article() {
        return nom_article;
    }

    public int getQuantite_article() {
        return quantite_article;
    }

    public float getPrix_article() {
        return prix_article;
    }

    public int getId_pole() {
        
        
        return id_pole;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public void setNom_article(String nom_article) {
        this.nom_article = nom_article;
    }

    public void setQuantite_article(int quantite_article) {
        this.quantite_article = quantite_article;
    }

    public void setPrix_article(float prix_article) {
        this.prix_article = prix_article;
    }

    public void setId_pole(int id_pole) {
        this.id_pole = id_pole;
    }

    @Override
    public String toString() {
        return "Stock{" + "id_article=" + id_article + ", nom_article=" + nom_article + ", quantite_article=" + quantite_article + ", prix_article=" + prix_article + ", id_pole=" + id_pole + "}";
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stock other = (Stock) obj;
        if (this.id_article != other.id_article) {
            return false;
        }
        return true;
    }
    
    

    
    
    
    
    
    
    
}
