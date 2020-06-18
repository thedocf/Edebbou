/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.mycompany.myapp.entities.Category;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.services.ProductService;
import java.util.ArrayList;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class productsCForm extends BaseForm {

    public productsCForm(Category c, Form prev) {
        ArrayList<Product> products = ProductService.getInstance().getAllProductC(c.getId());
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        this.setTitle(c.getLabel());
        this.setLayout(BoxLayout.y());
        int x=0;
        if (products != null) {
            for (Product p : products) {
                x=1;
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
        
     
       this.add(imgC);
        this.add(mb1);
       
            }
        }
       
       
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
     Label c1 = new Label("Aucun produit pour "+c.getLabel());
 if (x==0) {
     this.add(c1);
 
 }
        
        this.show();
    }

}