/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author PLANETAMIR
 */
public class Compte {

 

    private int id_client;
    private String nom;
    private String prenom;
    private String Date_naissance;
    private int Tel;
    private String Email;

    public Compte() {
        this.id_client = id_client;
    }
  public Compte(int id_client) {
        this.id_client = id_client;
    }
  
    public Compte(int id_client, String nom, String prenom, String Date_naissance, int Tel, String Email) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.Date_naissance = Date_naissance;
        this.Tel = Tel;
        this.Email = Email;
    }

    public Compte(String nom, String prenom, String Date_naissance, int Tel, String Email) {
        this.nom = nom;
        this.prenom = prenom;
        this.Date_naissance = Date_naissance;
        this.Tel = Tel;
        this.Email = Email;
    }

 

  

 

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate_naissance() {
        return Date_naissance;
    }

    public void setDate_naissance(String Date_naissance) {
        this.Date_naissance = Date_naissance;
    }

    public int getTel() {
        return Tel;
    }

    public void setTel(int Tel) {
        this.Tel = Tel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        return "compte{" + "id_client=" + id_client + ", nom=" + nom + ", prenom=" + prenom + ", Date_naissance=" + Date_naissance + ", Tel=" + Tel + ", Email=" + Email + '}';
    }

    public Object getSelectionModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
