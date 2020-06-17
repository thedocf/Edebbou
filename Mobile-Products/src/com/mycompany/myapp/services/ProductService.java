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

import com.mycompany.myapp.entities.Product;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Th3Doc
 */
public class ProductService {

    public ArrayList<Product> products;
    boolean resultOK;
    public static ProductService instance;
    private ConnectionRequest req;

    public ProductService() {
        req = new ConnectionRequest();
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }
    


    public ArrayList<Product> parseProduct(String jsonText) {
        try {

            products = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> productListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) productListJson.get("root");
              System.out.println("liste:"+list);

            for (Map<String, Object> obj : list) {
                Product p;
                p = new Product();
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

    
    
    
    
    public ArrayList<Product> getAllProduct() {
        String url = "http://localhost/debou/web/app_dev.php/Mobile/Products";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseProduct(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
                      
        return products;
    }
   public ArrayList<Product> SearchProduct(String nom){
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
public ArrayList<Product> getAllProductC(int id) {
        String url = "http://localhost/debou/web/app_dev.php/Mobile/CategP/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseProduct(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
                       
        return products;
    }

public boolean evastar(Product e) {
   
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
public ArrayList<Product> getTriProduct() {
        String url = "http://localhost/debou/web/app_dev.php/Mobile/Tri";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseProduct(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
                       
        return products;
    }
}




