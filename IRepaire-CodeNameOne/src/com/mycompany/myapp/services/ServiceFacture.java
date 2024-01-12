/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Facture;
import com.mycompany.myapp.entities.Materiel;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author User
 */
public class ServiceFacture {

    public ArrayList<Facture> factures;

    public static ServiceFacture instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceFacture() {
        req = new ConnectionRequest();
    }

    public static ServiceFacture getInstance() {
        if (instance == null) {
            instance = new ServiceFacture();
        }
        return instance;
    }

    public boolean ajouterFacture(Facture t) {
        System.out.println(t);
        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "factures/mobile/newFact";

        req.setUrl(url);
        req.setPost(true);
//        req.addArgument("nom", t.getNom());
//        req.addArgument("description", t.getCategorie() + "");
//        req.addArgument("delai", t.getDelai());
       
            req.addArgument("materiel_id", t.getMat_id().getId()+"");
            req.addArgument("prix_totale", t.getPrix()+"");
        

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("Ajout respons " + req.getResponseCode());
                System.out.println("Ajout respons " + req.getResponseErrorMessage());
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean modifierFacture(Facture facture) {
        System.out.println(facture.getId());
        System.out.println("********");
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "factures/mobile/updateFact/"+facture.getId();

        req.setUrl(url);
        req.setPost(true);
   
        if (facture.getMat_id()!= null) {
            req.addArgument("materiel_id", String.valueOf(facture.getMat_id().getId()));
        }

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("Modif respons " + req.getResponseCode());
                System.out.println("Modif respons " + req.getResponseErrorMessage());
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Facture> parseFacture(String jsonText) {

        try {
            factures = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> exercicesListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) exercicesListJson.get("root");
            for (Map<String, Object> obj : list) {
                Facture parsedFacture = new Facture();
                float id = Float.parseFloat(obj.get("id").toString());
                parsedFacture.setId((int) id);

              
                 if (obj.get("materiel_id") == null) {
                    parsedFacture.setMat_id(new Materiel());
                } else {
                     int mat_id =(int) Math.round((Double)obj.get("materiel_id"));
                     ServiceMateriel service = new ServiceMateriel();
                     
                    parsedFacture.setMat_id(service.getMatById(mat_id));
                }
                 if (obj.get("prix_totale") == null) {
                    parsedFacture.setPrix(0);
                } else {
                     int prix_Totale =(int) Math.round((Double)obj.get("prix_totale"));
                     
                    parsedFacture.setPrix(prix_Totale);
                }

                factures.add(parsedFacture);

            }

        } catch (IOException ex) {

        }
        return factures;
    }

    public ArrayList<Facture> getAllFacture() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "factures/mobile/facts";
        System.out.println("===>" + url);
        req.setUrl(url);

        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                factures = parseFacture(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return factures;
    }

    public boolean deleteFacture(int id) {
        String url = Statics.BASE_URL + "factures/mobile/delete/"+ id;
        System.out.println("Delete ===> " + url);
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     public int countFacture(){
        return getAllFacture().size();
    }
}
