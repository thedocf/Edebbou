/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.services.FournisseurService;
import com.mycompany.myapp.services.UploadServices;
import java.io.IOException;





/**
 *
 * @author M-YAHYAOUI
 */
public class AddDepotForm extends BaseForm {
                                FournisseurService ad = new FournisseurService ( );
                                
                                

    String path;
    String filePath;
    Container imgCtn;
    ImageViewer l = new ImageViewer();
    String FilenameInserver = "";
    UploadServices uploadservices = new UploadServices();

    public AddDepotForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public AddDepotForm(com.codename1.ui.util.Resources resourceObjectInstance) {

        imgCtn = new Container();

        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Ajout Depot ", "Title")
                )
        );

        installSidemenu(resourceObjectInstance);

        //getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("contact-b.png"), e -> {
        // });

        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setUIID("SignUpCenter");

        Container row1 = new Container(new GridLayout(1, 2));

        TextField entreprise = new TextField();
       
        entreprise.setUIID("SignUpField");
        entreprise.setHint("entreprise");
        entreprise.getHintLabel().setUIID("SignupFieldHint");

        row1.addComponent(new Label("entreprise :"));
        row1.addComponent(entreprise);
        center.addComponent(row1);
        
        
        
        TextField surface = new TextField();
        surface.setUIID("SignUpField");
        surface.setHint("surface");
        surface.getHintLabel().setUIID("SignupFieldHint");
        
        Container row3 = new Container(new GridLayout(1, 2));
          
        row3.add(new Label("surface :")).add(surface);
        center.addComponent(row3);
        center.setScrollableY(true);
        

        Container row2 = new Container(new GridLayout(1, 2));
        /*  
        TextField ville = new TextField();
       
        ville.setUIID("SignUpField");
        ville.setHint("ville");
        ville.getHintLabel().setUIID("SignupFieldHint");

        row1.addComponent(new Label("ville :"));
        row1.addComponent(ville);
        center.addComponent(row2);
       */ 

        TextField ville = new TextField();
        ville.setUIID("SignUpField");
        ville.setHint("ville");
        ville.getHintLabel().setUIID("SignupFieldHint");
        row2.add(new Label("ville :")).addComponent(ville);
          center.addComponent(row2);
        
        
        
        
        TextField capacite = new TextField();
        capacite.setUIID("SignUpField");
        
        Container row4 = new Container(new GridLayout(1, 2));
        capacite.setHint("capacite");
        capacite.getHintLabel().setUIID("SignupFieldHint");
        row4.add(new Label("capacite :")).addComponent(capacite);
        center.addComponent(row4);

        TextField description = new TextField();
        description.setUIID("SignUpField");

        Container row5 = new Container(new GridLayout(1, 2));
        description.setHint("description");
        description.getHintLabel().setUIID("SignupFieldHint");
         row5.add(new Label("description")).add(description);
        center.addComponent(row5);

        Button imageev = new Button("Browse Images");

        

        center.add(imageev);

        this.addComponent(center);
        //  this.addComponent(getimage);
        Button Ajouter = new Button("Ajouter Depot");

        Container btnC = new Container();
        btnC.add(Ajouter);
        this.add(btnC);
        imageev.addActionListener(e -> {
            Display.getInstance().openGallery(new ActionListener() {

                public void actionPerformed(final ActionEvent evt) {
                    if (evt == null) {
                        ToastBar.Status s = ToastBar.getInstance().createStatus();
                        s.setMessage("User Cancelled Gallery");
                        s.setMessageUIID("Title");
                        Image i = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"));
                        s.setIcon(i);
                        System.out.println(i);
                        s.setExpires(2000);
                        s.show();
                        return;
                    }
                    String file = (String) evt.getSource();
                    System.out.println("pathhhh:" + file);
                    String path = file.substring(7);
                    System.out.println(path);
                   //get the top Logger
                    try {
                        FilenameInserver = uploadservices.uploadImage(path);
                    } catch (IOException ex) {
                        
                    }
                   
                           System.out.println("nom image" + FilenameInserver);

                }
            }, Display.GALLERY_IMAGE);
            
                                            Media m = null;
                                
            
           
            try {
                m = MediaManager.createMedia("browse.mp3", false);
            } catch (IOException ex) {
                
            }
           
                                    m.play();
                                

        });
        Ajouter.addActionListener(new ActionListener (){
                            @Override
                            public void actionPerformed ( ActionEvent evt ){
                                      //  System.out.println(FilenameInserver);
                                      
                                Media m;
                                try {
                                    m = MediaManager.createMedia("audio/done.mp3", false);
                                    m.play();
                                } catch (IOException ex) {
                                   
                                }
                                String photo = "localhost/test/uploads/hadhra.png";
            ad.addDepot(entreprise.getText(),Integer.parseInt(surface.getText()),ville.getText(),Integer.parseInt(capacite.getText()),description.getText(),photo);
            ToastBar.showMessage( entreprise.getText()+" ajouté", FontImage.MATERIAL_THUMB_UP);

//Adresse a = new Adresse (adresse.getText(),Pays.getText(),Comp.getText(),cp.getText(),ville.getText());
                            }
});  

    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setInlineStylesTheme(resourceObjectInstance);
        setInlineStylesTheme(resourceObjectInstance);

    }
}
