/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.mycompany.myapp.entities.Product;
import com.codename1.ui.plaf.Style;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.mycompany.myapp.services.ProductService;

/**
 *
 * @author M-YAHYAOUI
 */
public class ProductDetailsForm extends BaseForm {

    public ProductDetailsForm(Product p, Form prev) {
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
      Button b1 = new Button("Evaluer");
      ProductService ps = new ProductService();
      b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ps.showStarPickingForm();

                
            }
        });
       
       this.add(imgC);
        this.add(mb1);
        
       this.add(b1);
        
        this.show();
    }

}