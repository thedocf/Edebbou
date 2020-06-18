/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import entite.Anonnce;
import javafx.scene.input.MouseEvent;
import service.Annoncesservice;
import service.UserSession;

/**
 * FXML Controller class
 *
 * @author Fida
 */
public class AnnonceShowFXMLController implements Initializable {

     private Anonnce selectedAnnonce; 
    @FXML
    private Button home;
    @FXML
    private Button shop;
    @FXML
    private Button cart;
    @FXML
    private Button checkout;
    @FXML
    private Button profile;
    @FXML
    private Button search;
    @FXML
    private Button p;
    @FXML
    private ImageView im;
    @FXML
    private TextField creator;
    @FXML
    private TextField date;
    @FXML
    private TextField desc;
    @FXML
    private TextField title;
    @FXML
    private Button p1;
         UserSession n = UserSession.getInstance();
 private Anonnce cc=null;
          private Anonnce c1=null;    
           String imggg="";
      List<String>type;
    @FXML
    private Text kop;
    @FXML
    private AnchorPane parent1;
    @FXML
    private Button login;
    @FXML
    private Button profile1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         type=new ArrayList<>();
            type.add("*.jpg");
            type.add("*.png");   
       String imgg =n.getEmail();
        // TODO
    }    
  @FXML
   private void profile(ActionEvent event) throws IOException {
                
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/gui/profile_1.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
      
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
          n.cleanUserSession(); 
       
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/gui/login.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
    }

    @FXML
    private void import_image(ActionEvent event) {
        FileChooser f=new FileChooser();
         f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
            imggg=fc.getAbsoluteFile().toURI().toString();
            if(imggg!=null){
            Image i = new Image(imggg);
            im.setImage(i); }
            
    }else { im.setImage(new Image(cc.getImage())); }
    }
  public void initData(Anonnce aa) { 
    selectedAnnonce = aa; 
    title.setText(selectedAnnonce.getDescription());
    
    desc.setText(selectedAnnonce.getTitle());
    Image i = new Image(selectedAnnonce.getImage());
    
                date.setText(selectedAnnonce.getDate().toString()); 
    im.setImage(i);  
    String n= selectedAnnonce.getId_user().getUsername();
    creator.setText(n);  
    int od=selectedAnnonce.getId();
                String odd=String.valueOf(od);
                kop.setText(odd);   
                
    
    }
    @FXML
    private void update(ActionEvent event) throws SQLException  {
         Annoncesservice cs = new Annoncesservice();
        String pphoto="";  
       int i= Integer.parseInt(kop.getText());
        if(creator.getText().equals(n.getUserName())) {
        String annonc = desc.getText();  
        if(imggg.length()!=0)
       { pphoto = imggg;
       }
       else 
       {  pphoto=cc.getImage();  }  
       String titl=title.getText();
            cs.update(new Anonnce(titl,annonc,pphoto),i);
          
        JOptionPane.showMessageDialog(null, "annonce modifier");
      
        
        } else { JOptionPane.showMessageDialog(null, "You don't have the permission"); 
         
            }
        }

    @FXML
    private void annonce(ActionEvent event) throws IOException {
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("/gui/listFXML.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
    }

    @FXML
    private void product(ActionEvent event) throws IOException {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/ShoppingP.fxml"));
            Parent root= loader.load();
            
            parent1.getScene().setRoot(root);
    }

    @FXML
    private void tocart(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/panier.fxml"));
            Parent root= loader.load();
  
            parent1.getScene().setRoot(root);
    }
      @FXML
    private void checkout(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/commande.fxml"));
            Parent root= loader.load();
            parent1.getScene().setRoot(root);
    }

    @FXML
    private void onmouseexit(MouseEvent event) {
    }

    @FXML
    private void Onmouseenter(MouseEvent event) {
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
              FXMLLoader loader= new FXMLLoader(getClass().getResource("/javafxapplication4/FXMLDocument.fxml"));
            Parent root= loader.load();
  
            parent1.getScene().setRoot(root);
    }

    }
    
    
    
  

