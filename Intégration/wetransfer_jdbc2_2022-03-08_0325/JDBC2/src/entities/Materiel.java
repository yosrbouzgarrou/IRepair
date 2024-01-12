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
public class Materiel {

    int id_piece;
    String type_piece;
    String probleme;
    String date_dist;
    Compte cpt;
    int id_client;

    public Materiel() {

    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public Materiel(String type_piece, String probleme, String date_dist, int id_client) {
        this.type_piece = type_piece;
        this.probleme = probleme;
        this.date_dist = date_dist;
        this.id_client = id_client;
    }

    public Materiel(int id_piece, String type_piece, String probleme, String date_dist, Compte cpt) {
        this.id_piece = id_piece;
        this.type_piece = type_piece;
        this.probleme = probleme;
        this.date_dist = date_dist;
        this.cpt = cpt;
        this.id_client = cpt.getId_client();

    }

    public Materiel(int id_piece, String type_piece, String probleme, String date_dist, int idClient) {
        this.id_piece = id_piece;
        this.type_piece = type_piece;
        this.probleme = probleme;
        this.date_dist = date_dist;
        this.id_client = idClient;

    }

    public Materiel(String type_piece, String probleme, String date_dist, Compte cpt) {
        this.type_piece = type_piece;
        this.probleme = probleme;
        this.date_dist = date_dist;
        this.cpt = cpt;
    }

    public int getId_piece() {
        return id_piece;
    }

    public void setId_piece(int id_piece) {
        this.id_piece = id_piece;
    }

    public String getType_piece() {
        return type_piece;
    }

    public void setType_piece(String type_piece) {
        this.type_piece = type_piece;
    }

    public String getProbleme() {
        return probleme;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
    }

    public String getDate_dist() {
        return date_dist;
    }

    public void setDate_dist(String date_dist) {
        this.date_dist = date_dist;
    }

    public Compte getCpt() {
        return cpt;
    }

    public void setCpt(Compte cpt) {
        this.cpt = cpt;
    }

    @Override
    public String toString() {
        return "Materiel{" + "id_piece=" + id_piece + ", type_piece=" + type_piece + ", probleme=" + probleme + ", date_dist=" + date_dist + ", cpt=" + cpt + '}';
    }

}
