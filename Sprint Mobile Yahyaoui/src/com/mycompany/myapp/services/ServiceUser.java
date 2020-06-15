/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.InteractionDialog;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.myapp.entities.Fos_user;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Fida
 */
public class ServiceUser {

    public ArrayList<Fos_user> users;

    private static int workload = 5;
    public static ServiceUser instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceUser() {
        req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
  public void ajoutTask(Fos_user ta) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/Edebbou-fyras/web/app_dev.php/sign/" + ta.getEmail() + "/" + ta.getPassword() + "/" + ta.getUsername();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        
        
        System.out.println(ta.toString());

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    public ArrayList<Fos_user> parseUsers(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) usersListJson.get("root");

            for (Map<String, Object> obj : list) {

                Fos_user u = new Fos_user();
                u.setId(((int) Float.parseFloat(obj.get("id").toString())));
                   float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int)id);
                u.setUsername(obj.get("username").toString());
                u.setPassword(obj.get("password").toString());
                u.setEmail(obj.get("email").toString());
                u.setRoles(obj.get("roles").toString());
                // System.out.println(obj.get("classedeseleves").toString());
                users.add(u);
            }

        } catch (IOException ex) {

        }
        return users;
    }

    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        stored_hash = "$2a" + stored_hash.substring(3);
        if (null == stored_hash || !stored_hash.startsWith("$2a$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        //password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return (password_verified);
    }

   

    public Fos_user getConnectedUser(String u, String p) {
        String url = Statics.BASE_URL + "/loginMobile/" + u;
        Fos_user ss = new Fos_user();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
                boolean test = false;
                if (users.isEmpty()) {
                    InteractionDialog d = new InteractionDialog("Login");
                   
                        Dialog.show("Echec d'authenfication", "Verifiez votre username", "Ok", null);
                } else {
                    for (Fos_user obj : users) {
                        if (u.equals(obj.getUsername()) && p.contentEquals(obj.getPassword())) {
                            ss.setUsername(obj.getUsername());
                            ss.setId(obj.getId());
                            ss.setEmail(obj.getEmail());
                            ss.setRoles(obj.getRoles());
                            test = true;
                        }
                    }
                    if (test == false) {
                       
                        Dialog.show("Echec d'authenfication", "mot de passe éronné", "Ok", null);
                    }
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ss;
    }

}
