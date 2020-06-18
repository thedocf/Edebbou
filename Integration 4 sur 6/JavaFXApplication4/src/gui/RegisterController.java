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
import entite.Fos_user;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.sql.Date;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import service.Fosuserservice;
import static util.Sms.send;
/**
 * FXML Controller class
 *
 * @author Fida
 */
public class RegisterController implements Initializable {

    @FXML
    private Pane container;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXPasswordField motdepasse;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField numtel;
    @FXML
    private JFXDatePicker datedenaissance;
    @FXML
    private JFXButton inscrire;
    @FXML
    private Label testmail;
    @FXML
    private Label testprenom;
    @FXML
    private Label testnom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event)throws IOException {
          String recepient=numtel.getText();
        if(nom.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("!!!  Vous devez entrer votre nom !!!");
        alert.showAndWait();}
        else if (prenom.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("!!!  Vous devez entrer votre prennom !!!");
        alert.showAndWait();}
        else if (motdepasse.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("!!!  Vous devez entrer votre mot de passe !!!");
        alert.showAndWait();}
        else if (mail.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("!!!  Vous devez entrer votre e-mail !!!");
        alert.showAndWait();}
        else if (numtel.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText("!!!  Vous devez entrer votre numéro telephonique !!!");
        alert.showAndWait();}
        else
        {  
            if ((testmail.getText().equals("Email valide"))&& (testprenom.getText().equals("Prénom valide")) && (testnom.getText().equals("Nom valide"))){
                    Fos_user u = new Fos_user();
            u.setUsername(nom.getText());
            u.setUsernameCanonical(nom.getText());
            u.setEmail(mail.getText());
            u.setEmailCanonical(mail.getText());
            u.setPassword(motdepasse.getText());
            u.setFirstname(nom.getText());
            u.setLastname(prenom.getText());
            u.setPhone(numtel.getText());
            u.setDatenaiss(datedenaissance.getValue().toString());

        Fosuserservice sc = new Fosuserservice();

        sc.ajouterClient(u);
        send(recepient,nom.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("!!!  Inscription Avec Success !!!");
        alert.showAndWait();
        FXMLLoader loader = new FXMLLoader();
        nom.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();}}
    }
public boolean checkMail(String a) {

        Boolean valide = false;
        int i, j, k;
        for (j = 1; j < a.length(); j++) {
            if (a.charAt(j) == '@') {
                if (j < a.length() - 4) {
                    for (k = j; k < a.length() - 2; k++) {
                        if (a.charAt(k) == '.') {
                            valide = true;
                        }
                    }
                }
            }
        }

        return valide;
    }
    @FXML
    private void verifMail(MouseEvent event) {
        Fosuserservice fs = new Fosuserservice();
        if (checkMail(mail.getText())) {
            if (fs.CheckIfUserExist(mail.getText())) {
               testmail.setTextFill(Paint.valueOf("RED"));
                testmail.setText("Utilisateur existe déjà ");
            } else {
                testmail.setTextFill(Paint.valueOf("#fbb710"));
                testmail.setText("Email valide");
            }
        } else {
         testmail.setTextFill(Paint.valueOf("RED"));
            testmail.setText("Vérifiez le format de votre adresse mail ");
        }

    
    }

    @FXML
    private void checkPrenom(MouseEvent event) {
        if (!prenom.getText().matches("[a-z A-Z]+")) {
            testprenom.setTextFill(Paint.valueOf("RED"));
            testprenom.setText("Un prénom ne doit contenir que des lettres ");
        } else {
            testprenom.setTextFill(Paint.valueOf("#fbb710"));
            testprenom.setText("Prénom valide");
        }
    }

    @FXML
    private void checkNom(MouseEvent event) {
         if (!nom.getText().matches("[a-z A-Z]+")) {
            testnom.setTextFill(Paint.valueOf("RED"));
            testnom.setText("Un nom ne doit contenir que des lettres ");

        } else {
            testnom.setTextFill(Paint.valueOf("#fbb710"));
            testnom.setText("Nom valide");
        }
    }
    
}
