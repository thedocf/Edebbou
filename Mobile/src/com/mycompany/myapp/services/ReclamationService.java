/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

/**
 *
 * @author Sandy
 */

import com.mycompany.myapp.entities.reclamation;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class ReclamationService {
    
    public ArrayList<reclamation> fournisseurs;
    public static ReclamationService instance = null;
    private ConnectionRequest req;

    public ReclamationService() {
        req = new ConnectionRequest();
    }

    public static ReclamationService getInstance() {
        if (instance == null) {
            instance = new ReclamationService();
        }
        return instance;
    }
    


    public ArrayList<reclamation> parseFournisseur(String jsonText) {
        try {

            fournisseurs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> fournisseurListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) fournisseurListJson.get("root");
         

            for (Map<String, Object> obj : list) {
                reclamation p;   
                p = new reclamation();
                                float id = Float.parseFloat(obj.get("id").toString());
                                System.out.print(id);
                                p.setId((int) id);
                                
                             //  float depot_id = Float.parseFloat(obj.get("depot_id").toString());
                             //  p.setDepot_id((int) depot_id); 
                                
       
                                p.setNom(obj.get("nom").toString());
                                p.setPrenom(obj.get("prenom").toString()); 

                                
                               float numTel = Float.parseFloat(obj.get("numTel").toString());
                               p.setNumTel((int) numTel);  
                                
                                p.setDisponible(obj.get("disponible").toString());
                                

                                
                             

                              
                fournisseurs.add(p);
            }
        } 
        catch (IOException ex) {
        }
        return fournisseurs;
    }

    
    
    
    
    
    public ArrayList<reclamation> getAllFournisseur() {
        String url = "http://localhost/Edebbou-fyras/web/app_dev.php/mobile/fournisseursfour";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                fournisseurs = parseFournisseur(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
                    
        return fournisseurs;
    }
    
    
    public void addDepot(String entreprise, int surface, String ville, int capacite, String description, String FilenameInserver) {
        String url = "http://localhost/Edebbou-fyras/web/app_dev.php/mobile/AjoutDepotMobile?entreprise="
                + entreprise
                +"&surface="
                +surface
                +"&ville="
                +ville
                +"&capacite="
                +capacite
                +"&description="
                +description
                +"&photo="
                +FilenameInserver;        
        
        System.out.print(url);
        
        ConnectionRequest con = new ConnectionRequest(url, true);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
      public void addFournisseur(String nom, String prenom, int numTel, int disponible,int depot_id) {
        String url = "http://localhost/Edebbou-fyras/web/app_dev.php/mobile/AjoutFournisseurMobile?nom="
                +nom
                +"&prenom="
                +prenom
                +"&numTel="
                +numTel
                +"&disponible="
                +disponible
                 +"&depot_id="
                +depot_id
                            ;     
        
        System.out.print(url);
        
        ConnectionRequest con = new ConnectionRequest(url, true);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
