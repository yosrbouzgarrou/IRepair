/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

//import entities.Personne;
import entities.Devis;
import entities.Rendezvous;
//import services.ServicePersonne;
import services.ServiceDevis;
import services.ServiceRendezvous;
import utils.MyDB;

/**
 *
 * @author Skander
 */
public class Main {
    
    public static void main(String[] args) {
       // ServicePersonne sp = new ServicePersonne();
       ServiceRendezvous sd = new ServiceRendezvous();
        Rendezvous s = new Rendezvous(5, "2020-01-01", 25, 1);
       // sd.modifier(s);
        System.out.println(sd.afficher());
         //  System.out.println(sd.afficher());
    }
    
}
