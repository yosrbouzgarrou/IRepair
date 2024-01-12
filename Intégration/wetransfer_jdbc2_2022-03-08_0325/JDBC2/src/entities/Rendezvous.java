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
public class Rendezvous {

    private int id_rdv;
    private String date_rdv;
    private int id_devis;
    private int id_client;

    public Rendezvous() {
    }

    public Rendezvous(int id_rdv, String date_rdv, int id_devis, int id_client) {
        this.id_rdv = id_rdv;
        this.date_rdv = date_rdv;
        this.id_devis = id_devis;
        this.id_client = id_client;
        
    }
    public Rendezvous( String date_rdv, int id_devis, int id_client) {
        
        this.date_rdv = date_rdv;
        this.id_devis = id_devis;
        this.id_client = id_client;
        
    }

   /* public Personne(String prenom, String nom, String email) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
    }
*/
    public int getId_rdv() {
        return id_rdv;
    }

    public void setId_rdv(int id_rdv) {
        this.id_rdv = id_rdv;
    }

    public String getDate_rdv() {
        return date_rdv;
    }

    public void setDate_rdv(String date_rdv) {
        this.date_rdv = date_rdv;
    }
    public int getId_devis() {
        return id_devis;
    }

    public void setId_devis(int id_devis) {
        this.id_devis = id_devis;
    }
    
     public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Rendezvous{" + "id_rdv=" + id_rdv + ", date_rdv=" + date_rdv + ",id_devis=" + id_devis + ", id_client=" + id_client + '}';
    }

}
