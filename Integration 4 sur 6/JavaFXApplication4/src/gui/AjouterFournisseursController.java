/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import entite.fournisseurs;
import service.Servicefournisseurs;
import util.ControlleSaisie;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouterFournisseursController implements Initializable {

    @FXML
    private ImageView re;
    @FXML
    private ImageView co;
    @FXML
    private TextField depot_id;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField numtel;
    @FXML
    private TextField disponible;
    @FXML
    private Button ajouter;
    @FXML
    private Button retour;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void insertFournisseurs(ActionEvent event) {

        if (
            !(ControlleSaisie.estVide(nom, "nom "))   
         && !(ControlleSaisie.estVide(prenom, "prenom "))
         && !(ControlleSaisie.estVide(numtel, "numtel "))
         && !(ControlleSaisie.estVide(disponible, "disponible "))
         && !(ControlleSaisie.estVide(depot_id, "depot_id "))    
            ) 
        {
            fournisseurs r = new fournisseurs();
            r.setDepot_id(Integer.parseInt(depot_id.getText()));
            r.setNom(nom.getText());
            r.setPrenom(prenom.getText());
            r.setNumTel(Integer.parseInt(numtel.getText()));
            r.setDisponible(disponible.getText());
            
            Servicefournisseurs sr = new Servicefournisseurs();
            sr.ajouterfournisseurs(r);
            
            TrayNotification tray = new TrayNotification("succès", " ajouté", SUCCESS);
            tray.showAndWait();

        }
    }



    @FXML
    private void retourafficher(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherfournisseurs.fxml"));
            Parent root = loader.load();
            AfficherFournisseursController rc = loader.getController();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }

    }

}
