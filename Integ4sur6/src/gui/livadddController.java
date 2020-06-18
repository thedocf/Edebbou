/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import entite.Livreur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;
import service.ServiceLivreur;

/**
 * FXML Controller class
 *
 * @author M-YAHYAOUI
 */
public class livadddController implements Initializable {

    @FXML
    private AnchorPane parent1;
    @FXML
    private TextArea nom;
    @FXML
    private Button button;
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
    private void add(ActionEvent event) {
        
        Livreur l = new Livreur();
        l.setNom(nom.getText());
        ServiceLivreur sl = new ServiceLivreur();
        sl.ajouterLivreur(l);
          Notifications.create()
                .title("Affectation")
                .text("Livreur ajoutée avec succés").darkStyle()
                .showWarning();
    }

    @FXML
    private void retourafficher(ActionEvent event) throws IOException {
        
                
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/livreurDash.fxml"));
            Parent root= loader.load();  
            retour.getScene().setRoot(root);

        
        
    }

    
}
