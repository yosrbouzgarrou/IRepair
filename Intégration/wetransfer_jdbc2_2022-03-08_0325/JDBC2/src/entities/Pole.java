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
public class Pole {
    
    
        
    private int id_pole;
    private String nom_pole;
    private String lieu_pole;
    
    

    public Pole(int id_pole, String nom_pole, String lieu_pole) {
        this.id_pole = id_pole;
        this.nom_pole = nom_pole;
        this.lieu_pole = lieu_pole;
    }

    public Pole(String nom_pole, String lieu_pole) {
        this.nom_pole = nom_pole;
        this.lieu_pole = lieu_pole;
    }


    public int getId_pole() {
        return id_pole;
    }

    public String getNom_pole() {
        return nom_pole;
    }

    public String getLieu_pole() {
        return lieu_pole;
    }

    public void setId_pole(int id_pole) {
        this.id_pole = id_pole;
    }

    public void setNom_pole(String nom_pole) {
        this.nom_pole = nom_pole;
    }

    public void setLieu_pole(String lieu_pole) {
        this.lieu_pole = lieu_pole;
    }

    @Override
    public String toString() {
        return "pole{" + "id_pole=" + id_pole + ", nom_pole=" + nom_pole + ", lieu_pole=" + lieu_pole + '}';
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
        final Pole other = (Pole) obj;
        if (this.id_pole != other.id_pole) {
            return false;
        }
        return true;
    }
    

    
    
    
    
    
}
