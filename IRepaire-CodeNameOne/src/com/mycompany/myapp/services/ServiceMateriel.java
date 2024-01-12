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
import com.mycompany.myapp.utils.Statics;
import com.mycompany.myapp.entities.Materiel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ServiceMateriel {

    public ArrayList<Materiel> mats;

    public static ServiceMateriel instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceMateriel() {
        req = new ConnectionRequest();
    }

    public static ServiceMateriel getInstance() {
        if (instance == null) {
            instance = new ServiceMateriel();
        }
        return instance;
    }

    public boolean ajouterMateriel(Materiel t) {
        System.out.println(t);
        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "materiel/mobile/newMat";

        req.setUrl(url);
        req.setPost(true);
        req.addArgument("compte_id", 1+"");
        req.addArgument("type_piece", t.getType_piece());
        req.addArgument("probleme", t.getProbleme());
        req.addArgument("date_distribution", t.getDate_dist());
        req.addArgument("nom", t.getNom());

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("Ajout respons "+req.getResponseCode());
                System.out.println("Ajout respons "+req.getResponseErrorMessage());
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    /*public boolean uploadImage( String base64, String fileName) {
        System.out.println("********");
        String url = Statics.BASE_URL + "abonnement/mobile/upload";
        System.out.println(url);
        System.out.println(base64);
        System.out.println(fileName);
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("image", base64);
        req.addArgument("fileName", fileName);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(evt);
                System.out.println("Upload respons "+req.getResponseCode());
                if(req.getResponseCode() != 200)
                    System.out.println("Upload respons "+req.getResponseErrorMessage());
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }*/
    public boolean modifierMateriel(Materiel mat) {
        String url = Statics.BASE_URL + "materiel/mobile/updateMat/"+mat.getId();

        req.setUrl(url);
        req.setPost(true);
        req.addArgument("cpt_id","1");
        req.addArgument("type_piece", mat.getType_piece());
        req.addArgument("probleme", mat.getProbleme());
        req.addArgument("date_dist", mat.getDate_dist());
        req.addArgument("nom", mat.getNom());


        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Materiel> parseMats(String jsonText) throws IOException {

      
            mats = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> exercicesListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) exercicesListJson.get("root");
            for (Map<String, Object> obj : list) {
                Materiel t = new Materiel();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
//               float cpt_id = Float.parseFloat(obj.get("compte_id").toString());
               t.setCpt_id(1);
                if (obj.get("type_piece") == null) {
                    t.setType_piece("null");
                } else {
                    t.setType_piece(obj.get("type_piece").toString());
                }

                if (obj.get("probleme") == null) {
                    t.setProbleme("null");
                } else {
                    t.setProbleme(obj.get("probleme").toString());
                }
            
                   if(obj.get("date_distribution").toString() == null){
                       t.setDate_dist("null");
                } else {
                       
                        t.setDate_dist(obj.get("date_distribution").toString());
                }
                   
                
                t.setNom(obj.get("nom").toString());
                mats.add(t);

            }

       
        return mats;
    }

    public ArrayList<Materiel> getAllMateriel() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "materiel/mobile/mats";
        System.out.println("===>" + url);
        req.setUrl(url);

        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    mats = parseMats(new String(req.getResponseData()));
                } catch (IOException ex) {
                    Logger.getLogger(ServiceMateriel.class.getName()).log(Level.SEVERE, null, ex);
                }
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return mats;
    }
    
    public Materiel getMatById(int id){
        ArrayList<Materiel> list = getAllMateriel();
        Materiel materiel = new Materiel();
        for(Materiel mat: list){
            if(id == mat.getId())
                materiel = mat;
        }
        
        return materiel;
    }



    public boolean deleteMateriel(int id) {
        String url = Statics.BASE_URL + "materiel/mobile/delete/" +id;
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
    
    public int countMat(){
        return getAllMateriel().size();
    }
}
