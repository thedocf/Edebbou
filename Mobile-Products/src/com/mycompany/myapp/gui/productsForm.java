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
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.services.ProductService;
import com.sun.javafx.text.TextLine;

import java.util.ArrayList;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class productsForm extends BaseForm {
Form current;
    public productsForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public productsForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        current=this;
        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label(" Produits ", "Title")
                )
        );

        installSidemenu(resourceObjectInstance);
           TextComponent id = new TextComponent().label("Recherche par ID");
        // getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("contact-b.png"), e -> {
        //});

        int t = productsForm.this.getTintColor();
        productsForm.this.setTintColor(0);
        Container Product = new Container(BoxLayout.y());
        // gui_Container_3_3.setUIID("List");
        //  gui_Container_3_3.setScrollableY(true);
        ArrayList<Product> products = ProductService.getInstance().getAllProduct();
        if (products != null) {
            for (Product p : products) {
                
                EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/ 2 , this.getHeight() / 8, 0xFFFFFFFF), true);
                Image image = URLImage.createToStorage(placeholder, p.getImage(), "http://localhost/backt/productimage/" + p.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
                Container imgC = new Container();
                imgC.add(image);
                
                MultiButton mb = new MultiButton(p.getNom());
                mb.setUIID("ListItem");
                mb.setTextLine2(p.getDescription());
                mb.setTextLine3("Prix :" + Float.toString(p.getPrix()) + " T.N.D");
                
       
      mb.add(RIGHT, image);
                mb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                     
                        
                        new ProductDetailsForm(p,current).show();
                    
                    }
                });

                
                
                /*      Depot.add(BorderLayout.CENTER, new Label("Center")).
    add(BorderLayout.SOUTH, new Label("South")).
    add(BorderLayout.NORTH, new Label("North")).
    add(BorderLayout.EAST, new Label("East")).
    add(BorderLayout.WEST, new Label("West"));
                
                */
                Product.add(FlowLayout.encloseCenter(mb));

            }

            this.add(Product);
        }

    }

////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setInlineStylesTheme(resourceObjectInstance);
        setInlineStylesTheme(resourceObjectInstance);
        setTitle("productsForm");
        setName("productsForm");

        

    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
