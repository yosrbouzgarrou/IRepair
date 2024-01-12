/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author KattaX
 */
public class Materiel {

    int id;
    int cpt_id;
    String type_piece;
    String probleme;
    String date_dist;
    String nom;

    public Materiel() {
    }

    public Materiel(String type_piece, String probleme, String date_dist, String nom) {
        this.type_piece = type_piece;
        this.probleme = probleme;
        this.date_dist = date_dist;
        this.nom = nom;
    }

    public Materiel(int cpt_id, String type_piece, String probleme, String date_dist, String nom) {
        this.cpt_id = cpt_id;
        this.type_piece = type_piece;
        this.probleme = probleme;
        this.date_dist = date_dist;
        this.nom = nom;

    }

    public Materiel(int id,int cpt_id, String type_piece, String probleme, String date_dist, String nom) {
        this.id = id;
        this.cpt_id = cpt_id;
        this.type_piece = type_piece;
        this.probleme = probleme;
        this.date_dist = date_dist;
        this.nom = nom;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCpt_id() {
        return cpt_id;
    }

    public void setCpt_id(int cpt_id) {
        this.cpt_id = cpt_id;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Materiel{" + "id=" + id + ", type_piece=" + type_piece + ", probleme=" + probleme + ", date_dist=" + date_dist + ", nom=" + nom + '}';
    }

}
