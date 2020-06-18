/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
import pidev.sandy.test.MenuAdmin1;

/**
 * FXML Controller class
 *
 * @author X
 */
public class AccueilReclamationController  implements Initializable {

    @FXML
    private AnchorPane mainAnchorPane;
    private Stage stage=new Stage();
    @FXML
    private ImageView mapStatGif;
    @FXML
    private ImageView hommeStatgif;
    @FXML
    private Button statBtn;

    
    


    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MyServices myServices = new MyServices();
        User UserConneter = myServices.chercherUtilisateurByid(MenuAdmin1.Id_user_connecte);
        System.out.println("ID du user connecté interface ajouter reclamation =" + MenuAdmin1.Id_user_connecte);

        if (!UserConneter.getRoles().contains("ROLE_ADMIN")) {
            mapStatGif.setVisible(false);
            hommeStatgif.setVisible(false);
            
            statBtn.setDisable(true);
            statBtn.setVisible(false);
            
        }
    }    

    
//     private void setNode(Node node) {
//        mainAnchorPane.getChildren().clear();
//        mainAnchorPane.getChildren().add((Node) node);
//        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
//        ft.setNode(node);
//        ft.setFromValue(0.10);//dispartion 
//        ft.setToValue(1);
//        ft.setCycleCount(1);
//        ft.setAutoReverse(true);
//        ft.play();
//    }
    
    
//        private Parent replaceSceneContent(String fxml) throws Exception {
//        Parent page = (Parent) FXMLLoader.load(getClass().getResource(fxml), null, new JavaFXBuilderFactory());
//        Scene scene = stage.getScene();
//        if (scene == null) {
//            scene = new Scene(page, 700, 450);
//            stage.setScene(scene);
//        } else {
//            stage.getScene().setRoot(page);
//        }
//        stage.sizeToScene();
//        return page;
//    }
    
            private void setNode(Node node) {
        mainAnchorPane.getChildren().clear();
        mainAnchorPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }
    
    @FXML
    private void listerReclamation(ActionEvent event) throws IOException {

        setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AfficherReclamation.fxml")));
//        try {
//            Node root = (Node)FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AfficherReclamation.fxml"));
//            mainAnchorPane.getChildren().setAll(root);
//        } catch (IOException ex) {
//            Logger.getLogger(AccueilReclamationController.class.getName()).log(Level.SEVERE, null, ex);
//        }
            
    }

    @FXML
    private void afficherStatistique(ActionEvent event) {
//        try {
//            setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/Chart.fxml")));
//        } catch (IOException ex) {
//            Logger.getLogger(AccueilReclamationController.class.getName()).log(Level.SEVERE, null, ex);
//        }
   try {
            Node root = (Node)FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/Chart.fxml"));
            mainAnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(AccueilReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    @FXML
    private void envoyerReclamation(ActionEvent event) {
        try {
            Node root = (Node)FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AjouterReclamation.fxml"));
            mainAnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(AccueilReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
