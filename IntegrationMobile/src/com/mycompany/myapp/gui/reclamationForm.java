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

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.reclamation;
import com.mycompany.myapp.services.ReclamationService;

import java.util.ArrayList;
import java.util.List;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class reclamationForm extends BaseForm {

    public reclamationForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public reclamationForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label(" Reclamations ", "Title")
                )
        );

        installSidemenu(resourceObjectInstance);

        // getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("contact-b.png"), e -> {
        //});

        int t = reclamationForm.this.getTintColor();
        reclamationForm.this.setTintColor(0);
        Container Reclamation = new Container(BoxLayout.y());
        // gui_Container_3_3.setUIID("List");
        //  gui_Container_3_3.setScrollableY(true);
        ArrayList<reclamation> depot = ReclamationService.getInstance().getAllReclamation();
        if (depot != null) {
            for (reclamation p : depot) {
                
                EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/ 2 , this.getHeight() / 8, 0xFFFFFFFF), true);
                Image image = URLImage.createToStorage(placeholder, p.getPhoto(), "http://localhost/test/uploads/" + p.getPhoto(), URLImage.RESIZE_SCALE_TO_FILL);
                Container imgC = new Container();
                imgC.add(image);
                
                MultiButton mb = new MultiButton(p.getTitle());
                mb.setUIID("ListItem");
              mb.setTextLine2(p.getDescription());
                
             
      //mb.add(RIGHT, image);
                mb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       // ProduitDetailsForm.produitS=p;

                        
                    }
                });

                
                
                /*      Reclamation.add(BorderLayout.CENTER, new Label("Center")).
    add(BorderLayout.SOUTH, new Label("South")).
    add(BorderLayout.NORTH, new Label("North")).
    add(BorderLayout.EAST, new Label("East")).
    add(BorderLayout.WEST, new Label("West"));
                
                */
                Reclamation.add(FlowLayout.encloseCenter(mb));

            }

            this.add(Reclamation);
        }

    }

////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setInlineStylesTheme(resourceObjectInstance);
        setInlineStylesTheme(resourceObjectInstance);
        setTitle("depotForm");
        setName("depotForm");

        

    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
