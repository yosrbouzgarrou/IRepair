/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import utils.Etat;



/**
 *
 * @author PLANETAMIR
 */
public class Reclamation {

    private int id_rec;
    private String ReclamationMsg;
    private String Date_rec;
    Compte cpt;
    int etatRec;

    public Reclamation() {
       
    }

    public Reclamation(int id_rec, String ReclamationMsg, String Date_rec, Compte cpt) {
        this.id_rec = id_rec;
        this.ReclamationMsg = ReclamationMsg;
        this.Date_rec = Date_rec;
        this.cpt = cpt;
    }
    

      public Reclamation(int id_rec, String ReclamationMsg, String Date_rec, Compte cpt,int etatRec) {
        this.id_rec = id_rec;
        this.ReclamationMsg = ReclamationMsg;
        this.Date_rec = Date_rec;
        this.cpt = cpt;
        this.etatRec = etatRec;
    }
    public Reclamation(String ReclamationMsg, String Date_rec, Compte cpt,int etatRec) {
        this.ReclamationMsg = ReclamationMsg;
        this.Date_rec = Date_rec;
        this.cpt = cpt;
        this.etatRec = etatRec;
    }

    public Reclamation(int id_rec, String ReclamationMsg, String Date_rec, int etatRec) {
        this.id_rec = id_rec;
        this.ReclamationMsg = ReclamationMsg;
        this.Date_rec = Date_rec;
        this.etatRec = etatRec;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public String getReclamationMsg() {
        return ReclamationMsg;
    }

    public void setReclamationMsg(String ReclamationMsg) {
        this.ReclamationMsg = ReclamationMsg;
    }

    public String getDate_rec() {
        return Date_rec;
    }

    public void setDate_rec(String Date_rec) {
        this.Date_rec = Date_rec;
    }

    public Compte getCpt() {
        return cpt;
    }

    public void setCpt(Compte cpt) {
        this.cpt = cpt;
    }

    public int getEtatRec() {
        return etatRec;
    }

    public void setEtatRec(int etatRec) {
        this.etatRec = etatRec;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_rec=" + id_rec + ", ReclamationMsg=" + ReclamationMsg + ", Date_rec=" + Date_rec + ", cpt=" + cpt + ", etatRec=" + etatRec + '}';
    }

   
    

  




}


