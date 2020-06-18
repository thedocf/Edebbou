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
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.services.ServicePost;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * GUI builder created Form
 *
 * @author shai
 */
public class TopForm extends BaseForm {

    public TopForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public TopForm(com.codename1.ui.util.Resources resourceObjectInstance) {
          gui_separator1.setShowEvenIfBlank(true);
        gui_Label_1_1_1.setShowEvenIfBlank(true);
       
        
        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {});
        
        FontImage.setMaterialIcon(gui_LA, FontImage.MATERIAL_LOCATION_ON);
        gui_LA.setIconPosition(BorderLayout.EAST);

        FontImage.setMaterialIcon(gui_newYork, FontImage.MATERIAL_LOCATION_ON);
        gui_newYork.setIconPosition(BorderLayout.EAST);
        
        gui_Text_Area_2.setRows(2);
        gui_Text_Area_2.setColumns(100);
        gui_Text_Area_2.setGrowByContent(false);
        gui_Text_Area_2.setEditable(false);
        gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setGrowByContent(false);
        gui_Text_Area_1.setEditable(false);
        initGuiBuilderComponents(resourceObjectInstance);
        
        Label Labfiltrer = new Label("Filter par :");
        ComboBox list = new ComboBox();
        list.addItem("All posts");
        list.addItem("Latest");
        addAll(Labfiltrer, list);

        list.addActionListener((e) -> {
            if (list.getSelectedIndex() == 0) {

                new TrendingForm1(resourceObjectInstance).show();
            }

            if (list.getSelectedIndex() == 1) {


                new TopForm(resourceObjectInstance).show();
            }
           

        }); 
         for (Post p : ServicePost.getInstance().getTop()) {
                   
            Label tfname = new Label();
            tfname.setText("Titre : " + p.getDescription());
             Label tfdesc = new Label();
            tfdesc.setText("Description : " + p.getTitre());
            Label tfprice = new Label();
            Container cnt1 = new Container(BoxLayout.x());
            Container cnt2 = new Container(BoxLayout.y());   
            Container cnt3 = new Container(BoxLayout.x());
            cnt2.addAll(tfname,tfdesc );
            

            String urll = "http://localhost/debou/web/post/" + p.getPhoto();
            
            EncodedImage enc = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme_1").getImage("search.png").scaled(250, 250), false);

            URLImage urlimg = URLImage.createToStorage(enc, p.getPhoto(), urll);
            ImageViewer image = new ImageViewer(urlimg);
            Image im = image.getImage();

            cnt1.add(im);
            cnt1.add(cnt2);
            add(cnt1);
           
            
            
          
            
         }
            
            FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
         fab.addActionListener(e -> {
         
        });
            
         }

//////////////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    protected com.codename1.components.MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
    protected com.codename1.components.MultiButton gui_LA = new com.codename1.components.MultiButton();
    protected com.codename1.ui.Container gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    protected com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    protected com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
    protected com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    protected com.codename1.ui.Label gui_separator1 = new com.codename1.ui.Label();
    protected com.codename1.ui.Container gui_null_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    protected com.codename1.components.MultiButton gui_null_1_1_1 = new com.codename1.components.MultiButton();
    protected com.codename1.components.MultiButton gui_newYork = new com.codename1.components.MultiButton();
    protected com.codename1.ui.Container gui_imageContainer2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    protected com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    protected com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
    protected com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    protected com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
 setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("Blog");
        setName("TrendingForm");
    }
//-- DON'T EDIT ABOVE THIS LINE!!!

    @Override
    protected boolean isCurrentTrending() {
        return true;
    }
}
