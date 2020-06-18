/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.LigneCommande;
import entite.LignePanier;
import entite.Livreur;
import entite.Produit;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;
import service.Fosuserservice;
import service.ServiceLivreur;
import service.ServicePanier;
import service.UserSession;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class PanierController implements Initializable {

    @FXML
    private TableColumn<LignePanier, String> nom;
    @FXML
    private TableColumn<LignePanier, Float> prix;
    @FXML
    private TableColumn<LignePanier, Integer> qte;
    @FXML
    private TableView<LignePanier> table;
    
    private ObservableList<LignePanier> recdata = FXCollections.observableArrayList();
    @FXML
    private Button checkoutb;
    @FXML
    private Text total;
    @FXML
    private Button home;
    @FXML
    private Button checkout;
    @FXML
    private Button profile;
    @FXML
    private Button search;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView pro;
    @FXML
    private ImageView insta;
    @FXML
    private ImageView fb;
    @FXML
    private ImageView chariot;
    @FXML
    private Button updateAnnonce;
    @FXML
    private TextField quantite;
    private Object quantiteinput;
    @FXML
    private Button Supprimer;
    @FXML
    private Button shop;
    @FXML
    private Button cart;
    @FXML
    private Button login;
    @FXML
    private Button profile1;
   
   
   
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        Produit p = new Produit();
//        p.setNom("test");
//        p.setPrix(10);
//        ServicePanier.panier.put(p, 5);
 logo.setImage(new Image("/images/logo.png"));
//home.setImage(new Image("/images/acceuil.png"));
chariot.setImage(new Image("/images/chariot.png"));
fb.setImage(new Image("/images/fb.png"));
pro.setImage(new Image("/images/product.png"));
insta.setImage(new Image("/images/insta.jpg"));

 
                
        List<LignePanier> listRec= new ArrayList<LignePanier>();
        ServicePanier rs =  new ServicePanier();
        
        
        listRec = rs.getPanier();
        recdata.clear();
        recdata.addAll(listRec);
        table.setItems(recdata);
        
       
        
        qte.setCellValueFactory(
            new PropertyValueFactory<>("qte")
        );
        nom.setCellValueFactory(
            new PropertyValueFactory<>("nom")
        );
        prix.setCellValueFactory(
            new PropertyValueFactory<>("prix")
        );
        
        total.setText(rs.total());
       
    }    

    @FXML
    private void checkout(ActionEvent event) throws AWTException, IOException, Exception {
        
        ServicePanier sp = new ServicePanier();
         Fosuserservice  sc = new Fosuserservice ();
        
        sp.checkout(0, false, sc.getIdbymail(UserSession.getInstance().getEmail()));
      
        
        
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/commande.fxml"));
            Parent root= loader.load();
            
            
            
            
            table.getScene().setRoot(root);
        
        //this.initialize(this.url, null);
        
    }

  

    @FXML
  private void profile(ActionEvent event) throws IOException {
                
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/gui/profile_1.fxml"));
        
        Parent root=fxml.load();
        table.getScene().setRoot(root);
      
    }


    @FXML
    private void updateAnnonce(ActionEvent event) {
    
            int qte = Integer.parseInt(quantite.getText());
            ServicePanier sp = new ServicePanier();
            Produit p = new Produit();
            sp.setProduit(table.getSelectionModel().getSelectedItem().getP(), qte);
            
            
            List<LignePanier> listRec= new ArrayList<LignePanier>();
        ServicePanier rs =  new ServicePanier();
        
        
        listRec = rs.getPanier();
        recdata.clear();
        recdata.addAll(listRec);
        table.setItems(recdata);
         total.setText(rs.total());
    }

    private void Home(MouseEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/javafxapplication4/FXMLDocument.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
    }

  

    private void checkout(MouseEvent event) throws IOException {
                  FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/commande.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
    }

    private void logout(MouseEvent event) throws IOException {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/login.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
    }

    @FXML
    private void Supprimer(ActionEvent event) {
             LignePanier p= table.getSelectionModel().getSelectedItem();
       ServicePanier sp = new ServicePanier();
       sp.supprimerProduit(p.getP(),p.getQte());
       List<LignePanier> listRec= new ArrayList<LignePanier>();
       listRec = sp.getPanier();
        recdata.clear();
        recdata.addAll(listRec);
        table.setItems(recdata);
    }

    @FXML
    private void product(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/produit.fxml"));
            Parent root= loader.load();
            
            
            
            
            table.getScene().setRoot(root);
    }

    @FXML
     private void tocart(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/panier.fxml"));
            Parent root= loader.load();
  
           table.getScene().setRoot(root);
    }

    @FXML
    private void onmouseexit(MouseEvent event) {
    }

    @FXML
    private void Onmouseenter(MouseEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
      
       
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/gui/login.fxml"));
        
        Parent root=fxml.load();
        table.getScene().setRoot(root);
    }

    @FXML
    private void annonce(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/listFXML.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
    }


    
    
}
