/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author M-YAHYAOUI
 */
public class HomeBackController {

    @FXML
    private Button logout;
    private Button commandes;
    private Button Gdepot;
    private Button GFournisseur;
    @FXML
    private Button GProduit;
    @FXML
    private Button Gdepotsss;
    @FXML
    private Button GCategorie;
    @FXML
    private Button Glivreur;
    @FXML
    private Button Gcommande;
    @FXML
    private Button Ghome;
    @FXML
    private Button GF;
    @FXML
    private Button GCategorie1;
    @FXML
    private Button GCategorie11;


    @FXML
    private void onmouseexit(MouseEvent event) {
    }

    @FXML
    private void Onmouseenter(MouseEvent event) {
    }



    @FXML
    private void livreurs(MouseEvent event) {
    }

    @FXML
    private void products(MouseEvent event) {
    }


    @FXML
    private void categoriess(MouseEvent event) {
    }


    @FXML
    private void Trier(MouseEvent event) {
    }

  
    @FXML
    private void logout(ActionEvent event) throws IOException {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/login.fxml"));
            Parent root= loader.load();  
            logout.getScene().setRoot(root);
    }
    @FXML
    private void Gdepotsss(ActionEvent event) throws IOException {
                                    FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherdepots.fxml"));
            Parent root= loader.load();  
            Gdepotsss.getScene().setRoot(root);
        
        
    }



    @FXML
    private void GProduit(ActionEvent event) throws IOException {
        
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/ProduitBack.fxml"));
            Parent root= loader.load();  
            GProduit.getScene().setRoot(root);

        
    }

    private void GFournisseur(ActionEvent event) throws IOException {

            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherfournisseurs.fxml"));
            Parent root= loader.load();  
            GFournisseur.getScene().setRoot(root);
        
    }

    @FXML
    private void Gcommande(ActionEvent event) throws IOException {
        
                         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Commandeback.fxml"));
            Parent root= loader.load();  
            Gcommande.getScene().setRoot(root);
        
        
        
        
    }

    @FXML
    private void Ghome(ActionEvent event) throws IOException {
                            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/homeback.fxml"));
            Parent root= loader.load();
  
            Ghome.getScene().setRoot(root);
        
        
    }

    @FXML
    private void Glivreur(ActionEvent event) throws IOException {
                                    FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/livreurDash.fxml"));
            Parent root= loader.load();
  
            Glivreur.getScene().setRoot(root);
    }

    @FXML
    private void GCategorie(ActionEvent event) throws IOException {
                                            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/CategorieBack.fxml"));
            Parent root= loader.load();
  
            GCategorie.getScene().setRoot(root);
    }

    @FXML
    private void GF(ActionEvent event) throws IOException {
        
        
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherfournisseurs.fxml"));
            Parent root= loader.load();  
            GF.getScene().setRoot(root);
        
        
    }

    @FXML
    private void logout(MouseEvent event) {
    }

    @FXML
    private void buser(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/dash.fxml"));
            Parent root= loader.load();  
            GF.getScene().setRoot(root);
        
    }

    @FXML
    private void bannonce(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/backan.fxml"));
            Parent root= loader.load();  
            GF.getScene().setRoot(root);
        
    }


  
    }
    


    
    
    
    
    
    
    
