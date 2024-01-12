/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author PLANETAMIR
 */
public class Facture {

    int id_fact;
    String nom_prenom;
    float prix_total;
    int id_piece;

    public Facture(int id_fact, String nom_prenom, float prix_total, int id_piece) {
        this.id_fact = id_fact;
        this.nom_prenom = nom_prenom;
        this.prix_total = prix_total;
        this.id_piece = id_piece;
    }

    public Facture(String nom_prenom, float prix_total, int id_piece) {
        this.nom_prenom = nom_prenom;
        this.prix_total = prix_total;
        this.id_piece = id_piece;
    }

    public Facture() {
    }

    public int getId_fact() {
        return id_fact;
    }

    public void setId_fact(int id_fact) {
        this.id_fact = id_fact;
    }

    public String getNom_prenom() {
        return nom_prenom;
    }

    public void setNom_prenom(String nom_prenom) {
        this.nom_prenom = nom_prenom;
    }

    public float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }

    public int getId_piece() {
        return id_piece;
    }

    public void setId_piece(int id_piece) {
        this.id_piece = id_piece;
    }

    @Override
    public String toString() {
        return "Facture{" + "id_fact=" + id_fact + ", nom_prenom=" + nom_prenom + ", prix_total=" + prix_total + ", id_piece=" + id_piece + '}';
    }

}
