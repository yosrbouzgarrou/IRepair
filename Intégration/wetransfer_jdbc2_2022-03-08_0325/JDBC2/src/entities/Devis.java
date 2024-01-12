/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Beyram
 */
public class Devis {

    private int id_devis;
    private double prix;
    private int id_client;
    private String date_devis;

    public Devis() {
    }

    public Devis(int id_devis, double prix, int id_client, String date_devis) {
        this.id_devis = id_devis;
        this.prix = prix;
        this.id_client = id_client;
        this.date_devis = date_devis;
        
    }
     public Devis(int id_client) {
        
        this.id_client = id_client;
       
        
    }
    public Devis( double prix, int id_client, String date_devis) {
        
        this.prix = prix;
        this.id_client = id_client;
        this.date_devis = date_devis;
        
    }

   /* public Personne(String prenom, String nom, String email) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
    }
*/
    public int getId_devis() {
        return id_devis;
    }

    public void setId_devis(int id_devis) {
        this.id_devis = id_devis;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    public int getId_Client() {
        return id_client;
    }

    public void setId_Client(int id_client) {
        this.id_client = id_client;
    }
    
     public String getDate_devis() {
        return date_devis;
    }

    public void setDate_devis(String date_devis) {
        this.date_devis = date_devis;
    }

    @Override
    public String toString() {
        return "Devis{" + "Id_devis=" + id_devis + ", prix=" + prix + ",id_client=" + id_client + ", date_devis=" + date_devis + '}';
    }

}
