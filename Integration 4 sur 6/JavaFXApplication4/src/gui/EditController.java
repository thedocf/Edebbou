/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import entite.Anonnce;
import entite.Fos_user;
import service.UserSession;
import service.Fosuserservice;
/**
 * FXML Controller class
 *
 * @author Fida
 */
public class EditController implements Initializable {
File file;
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
    private Pane container;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField numtel;
    @FXML
    private JFXDatePicker datedenaissance;
    @FXML
    private JFXButton inscrire;

            UserSession n = UserSession.getInstance();
    @FXML
    private AnchorPane parent1;
    @FXML
    private JFXButton choisirimg;
    @FXML
    private ImageView img;
    @FXML
    private Button login;
    @FXML
    private Button profile1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Fosuserservice su = new Fosuserservice();
        nom.setText(su.getNombyId(su.getIdbymail(n.getEmail())));
        prenom.setText(su.getPrenombyId(su.getIdbymail(n.getEmail())));
        datedenaissance.setValue(su.getDateDbyId(su.getIdbymail(n.getEmail())).toLocalDate());
        mail.setText(su.getMailbyId(su.getIdbymail(n.getEmail())));
        File file = new File(su.getPhotobyId(su.getIdbymail(n.getEmail())));
        Image image = new Image(file.toString());
        System.out.println(file.toURI().toString());
            img.setImage(image);
         numtel.setText(su.getTelbyId(su.getIdbymail(n.getEmail())));
    }    

    @FXML
    private void profile(ActionEvent event) throws IOException {      
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("profile_1.fxml"));
        
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
    private void checkNom(MouseEvent event) {
    }

    @FXML
    private void checkPrenom(MouseEvent event) {
    }

    @FXML
    private void verifMail(MouseEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        Fos_user u = new Fos_user();
            u.setUsername(nom.getText());
            u.setUsernameCanonical(nom.getText());
            u.setEmail(mail.getText());
            u.setEmailCanonical(mail.getText());
            u.setFirstname(nom.getText());
            u.setLastname(prenom.getText());
            u.setPhone(numtel.getText());
            u.setDatenaiss(datedenaissance.getValue().toString());
            
        u.setPhoto(file.toURI().toString());
        Fosuserservice sc = new Fosuserservice();
        
        int id =sc.getIdbymail(n.getEmail());
         sc.modifierClient(u, id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("!!! Modification avec success !!!");
        alert.showAndWait();
  AnchorPane pane = FXMLLoader.load(getClass().getResource("profile_1.fxml"));
         parent1.getChildren().setAll(pane);
    }

    private void home(ActionEvent event)  throws IOException{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/javafxapplication4/FXMLDocument.fxml"));
            Parent root= loader.load();
  
            parent1.getScene().setRoot(root);
    }

    void initData(Anonnce kkk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void choisirimage(ActionEvent event) {
             FileChooser fileChooserr = new FileChooser();
        fileChooserr.setTitle("Select PDF files");
        fileChooserr.setInitialDirectory(new File("C:"));
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooserr.getExtensionFilters().add(imageFilter);
        file = fileChooserr.showOpenDialog(img.getScene().getWindow());
        Image image = new Image(file.toURI().toString());
        img.setImage(image);
    }

@FXML
    private void tocart(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/panier.fxml"));
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
