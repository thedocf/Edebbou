/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Fida
 */
public class ServicePost {

    public ArrayList<Post> tasks;
    
    public static ServicePost instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServicePost() {
         req = new ConnectionRequest();
    }

    public static ServicePost getInstance() {
        if (instance == null) {
            instance = new ServicePost();
        }
        return instance;
    }

   

    public ArrayList<Post> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson =j.parseJSON (new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Post t = new Post();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setTitre(obj.get("title").toString());
                t.setDescription(obj.get("description").toString());
                t.setPhoto(obj.get("photo").toString());
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Post> getAllTasks(){
        String url = Statics.BASE_URL+"/api/post";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    
    }
     public ArrayList<Post> getTop(){
        String url = Statics.BASE_URL+"/api/top";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    
    }
        public ArrayList<Post> getPostSingle(Post p){
       
         
         String url = Statics.BASE_URL+"/api/get/"+ p.getId();      
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    public Container getList2(Resources  theme) {
                           
        
        Container container1All = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        ArrayList<Post> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/blog2/web/app_dev.php/api/post");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                ArrayList<Post> listTasks = new ArrayList<>();
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   // System.out.println("roooooot:" +tasks.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {
                        Post task = new Post();
                         task.setDescription(obj.get("description").toString());
                         
                           task.setPhoto(obj.get("photo").toString());
                        
                                 task.setTitre(obj.get("title").toString());
                        
                        Label titre=new Label("Titre :"+obj.get("title").toString());
         EncodedImage encImg  = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme_1").getImage("search.png").fill(125, 175),false);
        URLImage imgUrl = URLImage.createToStorage(encImg, task.getPhoto(), "http://localhost/ffosuser/web/uploads/post/"+task.getPhoto()
                ,URLImage.RESIZE_SCALE_TO_FILL);
        
        ImageViewer image = new ImageViewer(imgUrl);
                        System.out.println(task.getPhoto());
        

                       
                   
                        
    
                            Container container = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                            Container container1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                            Container container2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                                               
container1.add(titre);
container1.add(image);






        container.add(BorderLayout.CENTER, container1);

    container.add(BorderLayout.SOUTH, container2);
    
                        
//                        Container c=new Container();
//                        c.addAll(titre,lauteur,adresse,nbr);
                  container1All.add(container);
                        
                        
                        

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return container1All;
    }
 public void ajoutPost(Post ta,MultipartRequest con) {
      // final User me = new User();
           
              
String Url = "http://localhost/blog2/web/app_dev.php/api/addpost/"+ta.getCreateur()+"/"+ta.getDescription()+"/"+ ta.getPhoto()+"/"+ ta.getTitre();
 //  String Url = "http://127.0.0.1/Ecosmartweb/web/app_dev.php/api/annonces/add?userid="+ me.getId()+"&photo="+ ta.getPhoto()+"&description="+ ta.getDescription()+"&adresse="+ ta.getAdresse();
  
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        


    }
}
