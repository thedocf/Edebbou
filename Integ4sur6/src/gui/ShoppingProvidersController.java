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
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import service.UserSession;

import entite.depots;

import java.io.IOException;
import service.serviceProduct;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import service.Servicedepots;
import service.UserSession;
import entite.depots;



/**
 * FXML Controller class
 *
 * @author M-YAHYAOUI
 */
public class ShoppingProvidersController implements Initializable {

    @FXML
    private AnchorPane parent1;
    @FXML
    private VBox vbox1;
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
    private Button login;
    @FXML
    private Button search;
    UserSession n = UserSession.getInstance();
    @FXML
    private ChoiceBox<String> catcho;
    @FXML
    private Button filter;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   back.setVisible(false);
        List<depots> le = new ArrayList<>();
        Servicedepots es = new Servicedepots();
       
  
        ObservableList<String> l = null;
    
          le = es.afficherdepots();

        catcho.setItems(l);
        for (depots evv : le) {
            
            int surface = evv.getSurface();
            Label entreprise = new Label();
            Label capacite = new Label();
            Label description = new Label();
            //ImageView photo = new ImageView(new Image(evv.getPhoto()));
            //photo.setFitHeight(150);
            //photo.setFitWidth(150);
            Text ty = new Text("Surface: ");
            Text dd = new Text(" | ");
            ty.setFill(Color.DARKGREY);
            dd.setFill(Color.DARKGREY);
            entreprise.setText(evv.getEntreprise());
            description.setText(evv.getDescription());

            HBox h2 = new HBox();
            HBox h1 = new HBox();
            VBox rv = new VBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
           
            HBox hv1 = new HBox();
           
            final Separator sep = new Separator();
            vbox1.setSpacing(10);
            vbox1.setStyle("-fx-background-color: DARKORANGE; -fx-text-fill: DARKGREY;");
            sep.setMaxWidth(Double.MAX_EXPONENT);
            sep.setStyle(" -fx-text-fill: DARKGREY;");
              h1.setAlignment(Pos.CENTER);
                h2.setAlignment(Pos.CENTER);
            h2.setSpacing(10);
            
            hv1.getChildren().add(entreprise);
            //h1.getChildren().add(photo);
            v1.getChildren().add(description);
            h2.getChildren().add(capacite);



            
            vbox1.getChildren().add(hv1);
            vbox1.getChildren().add(v1);
            vbox1.getChildren().add(v2);
            vbox1.getChildren().add(h1);
            vbox1.getChildren().add(h2);
            vbox1.getChildren().add(sep);

            entreprise.setFont(javafx.scene.text.Font.font("Arial", 20));
            entreprise.setStyle("-fx-text-fill: #fbb710");
            description.setFont(javafx.scene.text.Font.font("Courrier", 16));
            description.setStyle("-fx-text-fill: #333333");

            vbox1.setStyle("-fx-padding: 10;" 
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                    + "-fx-border-radius: 5;"  + "-fx-border-height:50");
    }    
    
} 

   @FXML
    private void logout(ActionEvent event) throws IOException {
       FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/login.fxml"));
            Parent root= loader.load();
  
            parent1.getScene().setRoot(root);
    }

    
    


    @FXML
    private void tocart(ActionEvent event) throws IOException {
        
                    FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/panier.fxml"));
            Parent root= loader.load();
  
            catcho.getScene().setRoot(root);
    }

    @FXML
    private void onmouseexit(MouseEvent event) {
    }

    @FXML
    private void Onmouseenter(MouseEvent event) {
    }

    @FXML
    private void filter(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
    }

 


}