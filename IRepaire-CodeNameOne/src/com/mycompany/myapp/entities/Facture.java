/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author KattaX
 */
public class Facture {
    int id;
    Materiel mat_id;
    double prix;

    public Facture() {
    }

    public Facture(int id, Materiel mat_id, double prix) {
        this.id = id;
        this.mat_id = mat_id;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Materiel getMat_id() {
        return mat_id;
    }

    public void setMat_id(Materiel mat_id) {
        this.mat_id = mat_id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Facture{" + "id=" + id + ", mat_id=" + mat_id + ", prix=" + prix + '}';
    }
    
    
}
