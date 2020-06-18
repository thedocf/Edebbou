/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entite.LignePanier;
import entite.Produit;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.ServicePanier;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ProduitController implements Initializable {


    /**
     * Initializes the controller class.
     */
    
    private ObservableList<Produit> recdata = FXCollections.observableArrayList();
    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<Produit, String> nom;
    @FXML
    private TableColumn<Produit, Float> prix;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView fb;
    @FXML
    private ImageView insta;
    @FXML
    private ImageView pro;
    @FXML
    private Button add;
    @FXML
    private Button checkout;
    @FXML
    private Button profile;
    @FXML
    private Button logout;
    @FXML
    private Button search;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       logo.setImage(new Image("/images/logo.png"));
       fb.setImage(new Image("/images/fb.png"));
       insta.setImage(new Image("/images/insta.jpg"));
       pro.setImage(new Image("/images/product.png"));
     

        Produit p1 = new Produit();
        p1.setId(1);  
        p1.setNom("p1");
        p1.setPrix(1);
        Produit p2 = new Produit();
        p2.setId(2);  
        p2.setNom("p2");
        p2.setPrix(10);
        Produit p3 = new Produit();
        p3.setId(3);  
        p3.setNom("p3");
        p3.setPrix(100);
        
        recdata.add(p1);
        recdata.add(p2);
        recdata.add(p3);
        
        table.setItems(recdata);
        
        
        nom.setCellValueFactory(
            new PropertyValueFactory<>("nom")
        );
        prix.setCellValueFactory(
            new PropertyValueFactory<>("prix")
        );
    }    

    @FXML
    private void ajouterPanier(ActionEvent event) {
        
        Produit p = table.getSelectionModel().getSelectedItem();
        ServicePanier sp = new ServicePanier();
        sp.ajouterProduit(p, 1);
    }

    @FXML
    private void cart(MouseEvent event) throws IOException {
       
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/panier.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
    }

    @FXML
    private void profile(ActionEvent event) {
    }

    

    @FXML
    private void Home(MouseEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/javafxapplication4/FXMLDocument.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
    }

    @FXML
    private void checkout(MouseEvent event) throws IOException {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/commande.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
        
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/login.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
        
    }


  
    
}
