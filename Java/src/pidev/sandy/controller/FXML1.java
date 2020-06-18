/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdk.nashorn.internal.parser.TokenType;
import pidev.sandy.entites.Badge;
import pidev.sandy.entites.Cadeaux;
import pidev.sandy.entites.Compte;
import pidev.sandy.entites.Mailing;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
import pidev.sandy.services.ServiceNotification;
import pidev.sandy.services.ServiceRandomMailConfirmation;
import pidev.sandy.services.serviceCryptage;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class FXML1 implements Initializable {

 
 
   
 
 
    
     
 
 
    
 
    
 
  
    @FXML
    private ProgressBar progressPersonal;
    @FXML
    private Label lblComplete;
   
 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
 
    }

    @FXML
    private void typeuser(MouseEvent event) {
    }

    @FXML
    private void controphone(KeyEvent event) {
    }

    @FXML
    private void verifusername(KeyEvent event) {
    }

    @FXML
    private void verifEmail(KeyEvent event) {
    }

    @FXML
    private void ConfirmEmail(KeyEvent event) {
    }

    @FXML
    private void controlMdp(KeyEvent event) {
    }

    @FXML
    private void ConfirmMDP(KeyEvent event) {
    }

    @FXML
    private void handleButtonCancelar(ActionEvent event) {
    }

    @FXML
    private void handleButtonConfirmar(ActionEvent event) {
    }
 

    
}
