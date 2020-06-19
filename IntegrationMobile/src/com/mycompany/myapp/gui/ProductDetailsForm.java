/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.mycompany.myapp.entities.Product;
import com.codename1.ui.plaf.Style;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.mycompany.myapp.entities.Product2;
import com.mycompany.myapp.services.PanierService;
import com.mycompany.myapp.services.ProductService;
import java.util.HashMap;

/**
 *
 * @author M-YAHYAOUI
 */
public class ProductDetailsForm extends BaseForm {

    public ProductDetailsForm(Product2 p, Form prev) {
        PanierService.panier =  new HashMap<Product2, Integer>();
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        this.setTitle("Details");
        this.setLayout(BoxLayout.y());
        MultiButton mb1 = new MultiButton();
        mb1.setTextLine1(p.getNom());
        mb1.setTextLine3("Prix: "+Float.toString(p.getPrix())+" T.N.D");
        
        Label l=new Label("");
        if (p.getQte()> 0 )
        {
            l.setText("Oui");
        }
        else {
            l.setText("Non");
        } 
         EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/ 2 , this.getHeight() / 8, 0xFFFFFFFF), true);
                Image image = URLImage.createToStorage(placeholder, p.getImage(), "http://localhost/backt/productimage/" + p.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
                Container imgC = new Container();
          imgC.add(image);
            
        l.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        mb1.setTextLine4("Disponible :"+l.getText()+" ( "+p.getQte()+" En Stock )");
        mb1.setTextLine2(p.getDescription());
        
        mb1.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
                strokeOpacity(120));
        mb1.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        mb1.getAllStyles().setBgTransparency(255);
        mb1.getAllStyles().setBgColor(0x2d283e);
        mb1.getAllStyles().setFgColor(0xd1d7e0);
        mb1.getAllStyles().setMargin(20, 20, 20, 20);
        
     /*  MultiButton mb2 = new MultiButton();
        mb2.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
                strokeOpacity(120));
        mb2.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        mb2.getAllStyles().setBgTransparency(255);
        mb2.getAllStyles().setBgColor(0xff4d4d); 
        mb2.getAllStyles().setFgColor(0xd1d7e0);
        mb2.getAllStyles().setMargin(20, 20, 20, 20);
        mb2.setTextLine1("J'aime : "+p.getLike());
        mb2.setTextLine2("J'aime pas : "+p.getUnlike());*/
     Button bb = new Button("Acheter");
                bb.addActionListener((e) -> {
       PanierService pss = new PanierService();
            
       System.out.println(p.getId());
       System.out.println(p.getNom());
       System.out.println(p.getPrix());
       
               pss.ajouterProduit(p,1);
               ToastBar.showMessage( p.getNom()+" ajouté", FontImage.MATERIAL_THUMB_UP);
        
       });
               
      Button b1 = new Button("Evaluer");
      ProductService ps = new ProductService();
      Slider slide =ps.createStarRankSlider();
       System.out.println(p.getId());
      b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if( Dialog.show("Evaluation", Integer.toString(slide.getProgress())+" Etoiles pour ce produit ?", "Oui" ,"Annuler"))
               {
                   p.setStars(slide.getProgress());
                
                  Product p2 = new Product();
                        p2.setId(p.getId());
                        p2.setNom(p.getNom());
                        p2.setPrix(p.getPrix());
                        p2.setImage(p.getImage());
                        p2.setDescription(p.getDescription());
                        p2.setQte(p.getQte());
                        p2.setStars(p.getStars());
                   ps.evastar(p2);
                   ToastBar.showMessage( p.getNom()+" Evalué", FontImage.MATERIAL_THUMB_UP);
               }
                     
            }
        });
       Container b= new Container();
      
       slide.setProgress(p.getStars());
       
     b.add(FlowLayout.encloseCenter(slide));
     
       this.add(imgC);
        this.add(mb1
        );
         this.add(bb);
        this.add(b);
       this.add(b1);
        
        this.show();
    }

}