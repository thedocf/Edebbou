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

import com.mycompany.myapp.entities.depot;
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
public class DepotService {

    public ArrayList<depot> depots;
    public static DepotService instance;
    private ConnectionRequest req;

    public DepotService() {
        req = new ConnectionRequest();
    }

    public static DepotService getInstance() {
        if (instance == null) {
            instance = new DepotService();
        }
        return instance;
    }
    


    public ArrayList<depot> parseDepot(String jsonText) {
        try {

            depots = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> depotListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) depotListJson.get("root");
              System.out.println(list);

            for (Map<String, Object> obj : list) {
                depot p;
                p = new depot();
                                float id = Float.parseFloat(obj.get("id").toString());
                                System.out.print(id);
                p.setIdRef((int) id);
                
       
                                                p.setEntreprise(obj.get("entreprise").toString());
                
                
                float surface = Float.parseFloat(obj.get("surface").toString());
                p.setSurface((int) surface);

                                p.setVille(obj.get("ville").toString());   

               float capacite = Float.parseFloat(obj.get("capacite").toString());
                p.setCapacite((int) capacite);

                p.setDescription(obj.get("description").toString());      

p.setPhoto(obj.get("image").toString());
                depots.add(p);

            }
   
            }

               catch (IOException ex) {
        }
        return depots;
    }

    
    
    
    
    public ArrayList<depot> getAllDepot() {
        String url = "http://localhost/Edebbou-fyras/web/app_dev.php/mobile/fournisseurs";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                depots = parseDepot(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
                        System.out.println(depots);
        return depots;
    }
}




