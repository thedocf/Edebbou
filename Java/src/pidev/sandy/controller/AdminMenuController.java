/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import pidev.sandy.controller.LoginController;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
import pidev.sandy.test.MenuAdmin1;
 
 

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class AdminMenuController implements Initializable {

    @FXML
    private VBox btnListeGestion;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnprofilAvance;
    @FXML
    private JFXButton btnReclamations;
    @FXML
    private JFXButton btnBonPlans;
  
 
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private AnchorPane btnGestionAffichage;
 
    @FXML
    private Label loggeduser;

    
    private  static MyServices myServices=new MyServices();
    
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                 User loggedUser = LoginController.getInstance().getLoggedUser();
                 
         MenuAdmin1.Id_user_connecte=loggedUser.getId();
  
 User UserConneter=myServices.chercherUtilisateurByid(loggedUser.getId());
        loggeduser.setText("                "+String.valueOf(UserConneter.getUsername()));
       
    
          System.out.println("scscscsc"+loggeduser.getText());
        try {
            setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ProfileAvanceeAdmin.fxml")));
            
            // TODO
        } catch (IOException ex) {
            Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    private void setNode(Node node) {
        btnGestionAffichage.getChildren().clear();
        btnGestionAffichage.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void Home(ActionEvent event) throws IOException {
        
              setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ListeDesDemandeCadeauxMembre.fxml")));
        
        
    }

    @FXML
    private void GestionProfileAvance(ActionEvent event) throws IOException {
        
        
        setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ProfileAvanceeAdmin.fxml")));
    }

    @FXML
    private void GestionReclamations(ActionEvent event) throws IOException {
        
        
                setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AccueilReclamation.fxml")));

        
    }

    @FXML
    private void GestionBonPlans(ActionEvent event) throws IOException {
        
            setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/Adminpartenaire.fxml")));
    }

   

   

    @FXML
    private void MyProfile(ActionEvent event) throws IOException {
       
           setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ProfileMembre.fxml")));
    }


    @FXML
    private void ListeGestion(MouseEvent event) {
    }

    @FXML
    private void GestionAffichage(MouseEvent event) {
    }
    
       @FXML 
       protected void processLogout() {
    
        LoginController.getInstance().userLogout();
    }
    
}
