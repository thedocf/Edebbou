/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.StringUtil;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.services.ServicePost;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 *
 * @author hp
 */
public class Detail  extends Form{
          EncodedImage encoded;
Form f ;
 Post P;
String url = "http://localhost/ffosuser/web/public/uploads/post/";

    public  Detail(Post l){
                     f= new Form(" Post", new BoxLayout(BoxLayout.Y_AXIS));

 f.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_BACKSPACE, e -> { new TrendingForm1().show();});
        Container ctn1 = new Container(BoxLayout.y());
        Label nom = new Label("Title          : "+l.getDescription());
        Label Description = new Label("Description            :"+l.getTitre());
         Style s = UIManager.getInstance().getComponentStyle("MultiLine1");

            String urll = "http://localhost/ffosuser/web/public/uploads/post/" + l.getPhoto();
               FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);

              EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() *3, p.getHeight()*3 ), false);

            URLImage urlimg = URLImage.createToStorage(placeholder, l.getPhoto(), urll);
     ImageViewer image = new ImageViewer(urlimg);
            Image im = image.getImage();
       System.out.println(SessionManager.getId());
        System.out.println(l.getCreateur());
            Button tfdelete = new Button(FontImage.MATERIAL_DELETE);
                Button btnValider = new Button("send mail");
         btnValider.addActionListener((e) -> {

             
Message m = new Message(l.getTitre());
m.getAttachments().put(urll, "image/png");
Display.getInstance().sendMessage(new String[] {"someone@gmail.com"}, l.getDescription(), m);
               

            });
         
              tfdelete.addActionListener((e) -> {
                 
                if (Dialog.show("Alert", "Voulez vous supprimer  " + l.getTitre()+ " !!", "OK", "Cancel")) {
                    if (ServicePost.getInstance().deletePost(l)) {
                        ToastBar.showMessage("La publication a été supprimé", FontImage.MATERIAL_WARNING);
                         new TrendingForm1().show();
                    } else {
                        new TrendingForm1().show();
                    }
                }
                 
            });
            
        ctn1.add(im);
        ctn1.add(nom);
        ctn1.add(Description);
        ctn1.add(tfdelete);
        ctn1.add(btnValider);
        f.add(ctn1);       



    }
    public Form getForm(){
    return f;
    }
    
    
}

