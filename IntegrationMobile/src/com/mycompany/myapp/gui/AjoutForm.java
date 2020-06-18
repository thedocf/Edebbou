/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.components.WebBrowser;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.sun.javafx.tk.FileChooserType;
import java.io.IOException;
import java.util.Hashtable;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.list.DefaultListModel;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.services.ServicePost;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Fida
 */
public class AjoutForm {
String path;
String  filePath;
Container imgCtn;
static Post b=new Post();
    Form hi;
ImageViewer l = new ImageViewer();
MultipartRequest cr = new MultipartRequest();
    public AjoutForm() {
       hi = new Form("Add Post", new BoxLayout(BoxLayout.Y_AXIS));

        hi.setScrollVisible(true);
        hi.setSmoothScrolling(true);

        
        
        
          
    
        
        TableLayout tl;
int spanButton = 2;
if(Display.getInstance().isTablet()) {
    tl = new TableLayout(7, 2);
} else {
    tl = new TableLayout(14, 1);
    spanButton = 1;
}
tl.setGrowHorizontally(true);
hi.setLayout(tl);

TextField Name = new TextField("", "Nom", 20, TextArea.ANY);
TextField Desc = new TextField("", "Description", 20, TextArea.ANY);


Button getimage = new Button("get image");
  getimage.addActionListener(e->{
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if( evt!=null && evt.getSource()!=null){
                        path = (String)evt.getSource();
                        
                        Image image = null;
                        try {
                            image = Image.createImage(FileSystemStorage.getInstance()
                                    .openInputStream(path)).fill(125, 175);
      
                        } catch (IOException ex) {
                            Dialog.show("Error", ex.getMessage(), "OK", null);
                        }
                        l = new ImageViewer(image);
                        l.getStyle().setMarginLeft(40);
                        imgCtn.add(l);
                    }
                }
            }, Display.GALLERY_IMAGE);
        });


/******************* autocomplete *******/

 final DefaultListModel<String> options = new DefaultListModel<>();
 

         
 


        
/*********************Map**************************/


/********************/
   imgCtn = new Container();
Button submit = new Button("Submit");


if(b!=null)
{
    Name.setText(b.getTitre());
    

}


TableLayout.Constraint cn = tl.createConstraint();
cn.setHorizontalSpan(spanButton);
cn.setHorizontalAlign(Component.RIGHT);
hi.add("Name").add(Name).
        add("Description").add(Desc).
                add(getimage).
        add(cn, submit).add(imgCtn);



        
        
        /*******************/
        
        
        
        
      
           //fbButton =new Button("Partager sur Facebook");
      
        submit.addActionListener((e) -> {
        
            ServicePost ser = new ServicePost();
          Post t = new Post(SessionManager.getId(), Desc.getText(),"308682a2bcafbc272bed9b948a4b02bd.jpeg",Name.getText());
            ser.ajoutPost(t,cr);
              
/*

     ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Add post");
  status.setExpires(3000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();    */
 LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound

  
      Display.getInstance().scheduleLocalNotification(
        n,
        System.currentTimeMillis() + 10 * 1000, // fire date/time
        LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
);
 
new InboxForm().show();
        });
        
     

        

    }

    public Form getF() {
        return hi;
    }

    public void setF(Form f) {
        this.hi = f;
    }



}
