/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;

import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.entities.Product2;
import com.mycompany.myapp.services.ProductService;
import com.mycompany.myapp.services.ProduitService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Th3Doc
 */
public class searchProduct extends BaseForm{
   
   Form hi;
     
     ProduitService bS = new ProduitService();
     Container listeContainer = new Container(BoxLayout.y());
     public searchProduct(Form prev){}
     
      public searchProduct(String d){
          System.out.println("d:"+d);
              hi=this;
     Button back = $(new Button("Retour")).addActionListener(e2 -> {

            //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            new productsForm().showBack();
        }).asComponent(Button.class);
   // Form F2 = new Form(BoxLayout.y());
         
 
 ArrayList<Product2> liche = bS.SearchProduct(d);
 int x=0;
 if (liche!=null)
 {
 for (Product2 guide: liche) {
     x=1;
 Label titre = new Label(guide.getNom());
   Label date = new Label(guide.getDescription());
            //titre.getAllStyles().setFgColor(0x0c42c0);
            //String m=guide.getDate_creation();
            //SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
            //String date = DATE_FORMAT.format(m);
            //Label date = new Label(guide.getDate_creation());
         //Label cate = new Label(guide.getCategorie());
   
     //   searchContainer.add(srchText);
      //  searchContainer.add(searchFiled);
        // wholeContainer.add(searchContainer);
         //    add(wholeContainer);
        //wholeContainer.add(listeContainer);
      
        //wholeContainer.add(listeContainer);
//             add(wholeContainer);
        //Service testing
       

           Button ev = new Button("Details");
            
        ev.addActionListener((evt) -> {
           //new GuideDetailsForm(prev ).show();
          // bS.AfficherDetails(b);
           // new GuideDetailsForm(prev, b, CENTER).show();
         new ProductDetailsForm(guide,hi).show();
            });
      
     
  EncodedImage img = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(),350), true);
                        URLImage imgg= URLImage.createToStorage(img,guide.getImage(), "http://localhost/backt/productimage/"+guide.getImage());
                        imgg.fetch();
                        ScaleImageLabel sl = new ScaleImageLabel(imgg);
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

                        ImageViewer imgv = new ImageViewer(imgg);
                     
         
            System.out.println(guide);
          
            listeContainer.add(titre);
            listeContainer.add(date);
            
              listeContainer.add(imgv);
           
            listeContainer.addComponent(ev);
          // listeContainer.add(searchContainer);
      
        listeContainer.add(back);
        
        }}
 Label c = new Label("Produit "+d+" introuvable"); 
 
     listeContainer.add(c);
     listeContainer.add(back);
 
     hi.add(listeContainer);
        hi.show();
     
     
     
        }
}
