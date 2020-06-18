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
import com.codename1.l10n.ParseException;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.entities.Product2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jha
 */
public class ProduitService {
     boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Product2> products;
     public ArrayList<Product2> parseListCotisationJson(String json) throws ParseException, IOException  {
          
        ArrayList<Product2> listProducts = new ArrayList<>();

      try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> product = j.parseJSON(new CharArrayReader(json.toCharArray()));
       
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) product.get("root");
            

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Product2 c = new Product2();

                float id = Float.parseFloat(obj.get("idp").toString());
                c.setId((int) id) ;
                c.setNom((String) obj.get("nom"));
               
                float prix = Float.parseFloat(obj.get("prix").toString());
                c.setPrix(prix);


               // System.out.println(c);
                
                listProducts.add(c);

            }
              } catch (IOException ex) {
            
         } 

     
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
       // System.out.println(listCotisation);
        return listProducts;
    }
     
     ArrayList<Product2> listProducts= new ArrayList<>();
    
    public ArrayList<Product2> getListProducts() throws ParseException {       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ffosuser/web/app_dev.php/wsprod");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
                try {
                    listProducts = parseListCotisationJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    //Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    //Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            }
        });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProducts;
    }
    
public boolean evastar(Product2 e) {
   
        String Url = "http://localhost/debou/web/app_dev.php/Mobile/Evaluation?id="
                + e.getId()
                + "&nom=" + e.getNom()
                + "&prix=" + e.getPrix()
                + "&qte=" + e.getQte()
                
                + "&image=" + e.getImage()
                + "&description=" + e.getDescription()
                + "&stars=" + e.getStars();
       
        req.setUrl(Url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
 public void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}

public Slider createStarRankSlider() {
    Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(5);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    return starRank;
}
public ArrayList<Product2> parseProduct(String jsonText) {
        try {

            products = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> productListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) productListJson.get("root");
              System.out.println("liste:"+list);

            for (Map<String, Object> obj : list) {
                Product2 p;
                p = new Product2();
                               float id = Float.parseFloat(obj.get("idp").toString());
                            
                p.setId((int) id);
                
       
                                                p.setNom(obj.get("nom").toString());
                float stars = Float.parseFloat(obj.get("stars").toString());
                p.setStars((int) stars);
                
                float prix = Float.parseFloat(obj.get("prix").toString());
                p.setPrix(prix);

                                p.setDescription(obj.get("description").toString());   

               float qte = Float.parseFloat(obj.get("qte").toString());
                p.setQte((int)qte);
               
               
               
                //p.setDate_expiration((LocalDate) obj.get("date_expiration"));      

p.setImage(obj.get("image").toString());
                products.add(p);

            }
   
            }

               catch (IOException ex) {
        }
        return products;
    }
 public ArrayList<Product2> SearchProduct(String nom){
        System.out.println("lesm:"+nom);
        String url = "http://localhost/debou/web/app_dev.php/Mobile/Find/"+nom;
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseProduct(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return products;
    }
}
