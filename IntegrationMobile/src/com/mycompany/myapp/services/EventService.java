/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

/**
 *
 * @author M-YAHYAOUI
 */

import com.mycompany.myapp.entities.fournisseur;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import com.mycompany.myapp.entities.Evenement;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class EventService {
    
    public ArrayList<Evenement> events;
    public static EventService instance = null;
    private ConnectionRequest req;

    public EventService() {
        req = new ConnectionRequest();
    }

    public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }
    


    public ArrayList<Evenement> parseEvents(String jsonText) {
        try {

            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) eventListJson.get("root");
             

            for (Map<String, Object> obj : list) {
                Evenement p;   
                p = new Evenement();
                                float id = Float.parseFloat(obj.get("id").toString());
                                System.out.print(id);
                                p.setId((int) id);
                                
                             //  float depot_id = Float.parseFloat(obj.get("depot_id").toString());
                             //  p.setDepot_id((int) depot_id); 
                                
       
                                p.setNomEvent(obj.get("nomEvent").toString());
                                //p.setAdresse(obj.get("adresse").toString()); 
                                //p.setType(obj.get("type").toString()); 
                                p.setDescription(obj.get("description").toString()); 
                               //float prix = Float.parseFloat(obj.get("prix").toString());
                               //p.setPrix(prix);  
                                float nbPlaces = Float.parseFloat(obj.get("nbPlaces").toString());
                               p.setNbPlaces((int)nbPlaces);  
                                p.setImage(obj.get("image").toString());
                                

                                
                             

                              
                events.add(p);
            }
        } 
        catch (IOException ex) {
        }
        return events;
    }

    
    
    
    
    
    public ArrayList<Evenement> getAllEvent() {
        String url = "http://localhost/back/web/app_dev.php/ListEventMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
                        System.out.println(events);
        return events;
    }
    
    
    
    
     /* public void addFournisseur(String nom, String prenom, int numTel, int disponible,int depot_id) {
        String url = "http://localhost/debou/web/app_dev.php/mobile/AjoutFournisseurMobile?nom="
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
        
     
        
        ConnectionRequest con = new ConnectionRequest(url, true);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }*/
}
