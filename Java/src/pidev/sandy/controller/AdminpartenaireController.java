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
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class AdminpartenaireController implements Initializable {

    @FXML
    private AnchorPane gestioncategorie;
    @FXML
    private ImageView gestionbonplan;
    @FXML
    private AnchorPane espacepartenaraieadminpane;
    @FXML
    private JFXButton logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gestionbonplan(ActionEvent event) throws IOException {
         setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/Adminpartenaire.fxml")));
    }


    @FXML
    private void categ(MouseEvent event) throws IOException {
         setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AfficherCategorie.fxml")));
    }

    @FXML
    private void bn(MouseEvent event) throws IOException {
         setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AccepterRefuser.fxml")));
        
    }
    


    @FXML
    private void logout(ActionEvent event) {
           Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
    }
         private void setNode(Node node) {
        espacepartenaraieadminpane.getChildren().clear();
        espacepartenaraieadminpane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
         }
    
}
