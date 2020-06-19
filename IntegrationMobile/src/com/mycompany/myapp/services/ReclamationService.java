/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author Ghassen
 */
public class ReclamationService {

    public ArrayList<reclamation> depots;
    public static ReclamationService instance;
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
    


    public ArrayList<reclamation> parseReclamation(String jsonText) {
        try {

            depots = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> depotListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) depotListJson.get("root");
              System.out.println(list);

            for (Map<String, Object> obj : list) {
                reclamation p;
                p = new reclamation();
                        
      
                p.setTitle(obj.get("title").toString());   
                p.setDescription(obj.get("description").toString());      
 
p.setPhoto(obj.get("photo").toString());
                depots.add(p);

            }
   
            }

               catch (IOException ex) {
        }
        return depots;
    }

    
    
    
    
    public ArrayList<reclamation> getAllReclamation() {
        String url = "http://localhost/debou/web/app_dev.php/mobile/allrec";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                depots = parseReclamation(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
                        System.out.println(depots);
        return depots;
    }
}




