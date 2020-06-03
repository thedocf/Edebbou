/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;


import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Product;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jha
 */
public class PanierService {
        public static Map<Product,Integer> panier;
    public void ajouterProduit(Product idproduit, int qte) {
        if (panier.containsKey(idproduit)) {
            panier.put(idproduit, panier.get(idproduit) + qte);
        } else {
            panier.put(idproduit, qte);
        }
    }

    public void modifProduit(Product produit, int qte) {
        if (qte>0) {
                panier.put(produit, qte);
        } else {
            panier.remove(produit);
        }
        
    }
    
    boolean resultOK = false;
    public boolean Checkout(){
        
        String p = "";
        for (Map.Entry<Product, Integer> entry : PanierService.panier.entrySet()) {
            //Label l = new Label("'"+entry.getKey().getNom()+"'  Qte: '"+entry.getValue().toString()+"' P : '"+entry.getKey().getPrix()+"' Tot : "+(entry.getKey().getPrix()*entry.getValue()));
            p+= Integer.toString(entry.getKey().getId()) + "-" + entry.getValue().toString()+"_";
        }
        
        ConnectionRequest req = new ConnectionRequest();
        String url = "http://127.0.0.1:80/ffosuser/web/app_dev.php/wsCheckOut/"+p+"?id="+ServiceUser.userid; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = (req.getResponseCode() == 200); //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    

    
    public String total(){
    float total = 0;
    
    
        for (Map.Entry<Product, Integer> entry : panier.entrySet()) {
            total += entry.getValue() * entry.getKey().getPrix();
        }
    
    return String.valueOf(total);
    }
}
