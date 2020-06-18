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

import com.mycompany.myapp.entities.Category;
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



public class CategoryService {
    
    public ArrayList<Category> categs;
    public static CategoryService instance = null;
    private ConnectionRequest req;

    public CategoryService() {
        req = new ConnectionRequest();
    }

    public static CategoryService getInstance() {
        if (instance == null) {
            instance = new CategoryService();
        }
        return instance;
    }
    


    public ArrayList<Category> parseCategory(String jsonText) {
        try {

            categs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> categListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) categListJson.get("root");
              System.out.println(list);

            for (Map<String, Object> obj : list) {
                Category p;   
                p = new Category();
                                float id = Float.parseFloat(obj.get("id").toString());
                                System.out.print(id);
                                p.setId((int) id);
                                
                      
                                p.setLabel(obj.get("label").toString());
                               


                                
                             

                              
                categs.add(p);
            }
        } 
        catch (IOException ex) {
        }
        return categs;
    }

    
    
    
    
    
    public ArrayList<Category> getAllCateg() {
        String url = "http://localhost/debou/web/app_dev.php/Mobile/Categ";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categs = parseCategory(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
                        System.out.println(categs);
        return categs;
    }
    
    
    
}
