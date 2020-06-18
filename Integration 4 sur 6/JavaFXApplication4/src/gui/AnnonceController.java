/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import entite.Anonnce;
import service.Fosuserservice;
import service.UserSession;
import service.Annoncesservice;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import entite.Fos_user;

import entite.Anonnce;

/**
 * FXML Controller class
 *
 * @author Fida
 */
public class AnnonceController implements Initializable {

    @FXML
    private TextField tfNomAnnonce;
    @FXML
    private TextField tfIdAnnonce;
    @FXML
    private TextField tfUserId;
    @FXML
    private Button photo;
    @FXML
    private Button btnAjouterAnnonce1;
    @FXML
    private ImageView imageview;
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
    private Button login;
    @FXML
    private Button search;
    @FXML
    private TextArea tfDescriptionAnnonce;
 UserSession n = UserSession.getInstance(); 
     String img="";
      List<String>type;
    @FXML
    private AnchorPane parent1;
    private Label testnom;
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
        
    }    

    @FXML
    private void import_image(ActionEvent event) {
          FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
            img=fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img);
            imageview.setImage(i);
        }
    }

    @FXML
    private void AjouterAnnonce(ActionEvent event) throws SQLException, IOException {
          String mail=n.getEmail();
         
           
        Fosuserservice  cs = new Fosuserservice ();
        Fos_user uu = new Fos_user(); 
        uu.setEmail(mail);
        Fos_user u =cs.getuser(uu); 
        String title = tfNomAnnonce.getText(); 
        String anoncee = tfDescriptionAnnonce.getText(); 
         String pphoto = img; 
         Annoncesservice an = new Annoncesservice();
         Anonnce a = new Anonnce(u,title,anoncee,pphoto);
         an.ajouter(a);
                  

                 AnchorPane pane = FXMLLoader.load(getClass().getResource("listFXML.fxml"));
         parent1.getChildren().setAll(pane);
    }

    @FXML
    private void onmouseexit(MouseEvent event) {
    }

    @FXML
    private void Onmouseenter(MouseEvent event) {
    }

    @FXML
      private void logout(ActionEvent event) throws IOException {
          n.cleanUserSession(); 
       
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/gui/login.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
    }

     private void home(ActionEvent event)  throws IOException{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/javafxapplication4/FXMLDocument.fxml"));
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
   private void profile(ActionEvent event) throws IOException {
                
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/gui/profile_1.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
      
    }

  
       
    @FXML
     private void annonce(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/listFXML.fxml"));
            Parent root= loader.load();
  
            parent1.getScene().setRoot(root);
    }

    @FXML
    private void product(ActionEvent event) throws IOException {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/ShoppingP.fxml"));
            Parent root= loader.load();
            
            parent1.getScene().setRoot(root);
    }

    @FXML
    private void checkout(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/commande.fxml"));
            Parent root= loader.load();
            parent1.getScene().setRoot(root);
    }
}
