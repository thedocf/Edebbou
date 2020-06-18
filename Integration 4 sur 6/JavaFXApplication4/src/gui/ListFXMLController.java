/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;
import org.controlsfx.control.textfield.TextFields;
import entite.Anonnce;
import service.UserSession;
import service.Annoncesservice;
/**
 * FXML Controller class
 *
 * @author Fida
 */
public class ListFXMLController implements Initializable {

   
    
            UserSession n = UserSession.getInstance();

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
    private AnchorPane parent1;
    @FXML
    private ScrollPane s1;
    @FXML
    private VBox vbox1;
      List<String> li = new ArrayList<>();
    Annoncesservice es = new Annoncesservice();
    @FXML
    private Button adn;
    private Rating rat;
    @FXML
    private Button login;
    @FXML
    private Button profile1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 // TODO
        List<Anonnce> le = new ArrayList<>();

        try {
            le = es.readAll2();
        } catch (SQLException ex) {
            Logger.getLogger(ListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
     

        for (Anonnce evv : le) {
            
            Rating rat = new Rating();
            Rating rat2 = new Rating();
            String sd = evv.getDate().toString().substring(0, 10);
            Label nom = new Label();
            Label Desc = new Label();
            Label dated = new Label();
            
            Label fos = new Label();
            ImageView photo = new ImageView(new Image(evv.getImage()));
            photo.setFitHeight(150);
            photo.setFitWidth(150);
            Text ty = new Text("Creator: ");
            Text dd = new Text("Date: ");
            ty.setFill(Color.DARKGREY);
            dd.setFill(Color.DARKGREY);
            nom.setText(evv.getTitle());
            Desc.setText(evv.getDescription());
            fos.setText(evv.getA());
            System.out.println(sd);
            dated.setText(sd);
           rat2.setRating(evv.getRating());
            HBox h2 = new HBox();
            HBox h1 = new HBox();
            
            VBox rv = new VBox();
            HBox btn = new HBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
            HBox hv1 = new HBox();
            JFXButton bt = new JFXButton("Details");
            Button bt3 = new Button("Delete");
            Button bt2 = new Button("Comment");
            Button btfb = new Button("Share Fb");
            final Separator sep = new Separator();
            vbox1.setSpacing(10);
            vbox1.setStyle("-fx-background-color: DARKORANGE; -fx-text-fill: DARKGREY;");
            
            sep.setMaxWidth(Double.MAX_EXPONENT);
            sep.setStyle(" -fx-text-fill: DARKGREY;");
              h1.setAlignment(Pos.CENTER);
                h2.setAlignment(Pos.CENTER);
            h2.setSpacing(10);
            rat2.ratingProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                     rat.setRating(evv.getRating());
                    evv.setRating((int) rat2.getRating());

                    System.out.println("before" + rat.getRating());
                    System.out.println("Clicked" + rat2.getRating());
                    System.out.println("    After Clicked" + (((int) rat2.getRating() + (int) rat.getRating())/2));
                    evv.setRating((((int) rat2.getRating() + (int) rat.getRating())/2));
                    
                    es.addRarting(evv);
                    rat.setRating(es.getRateAnnonces(evv));
                    
                }

            });
            hv1.getChildren().add(nom);
            
            h1.getChildren().add(photo);
            v1.getChildren().add(Desc);
            
            h2.getChildren().add(ty);
            
            v1.getChildren().add(rat2);
            h2.getChildren().add(fos);
            h2.getChildren().add(dd);
            h2.getChildren().add(dated);
            btn.getChildren().add(bt);
            btn.setSpacing(10);
            btn.getChildren().add(bt3);
            btn.getChildren().add(bt2);
            btn.getChildren().add(btfb); 
            vbox1.getChildren().add(hv1);
            vbox1.getChildren().add(v1);
            vbox1.getChildren().add(v2);
            vbox1.getChildren().add(h1);
            
            vbox1.getChildren().add(h2);
            vbox1.getChildren().add(btn);
            vbox1.getChildren().add(sep);
            btn.setAlignment(Pos.CENTER);
            nom.setFont(javafx.scene.text.Font.font("Arial", 20));
            nom.setStyle("-fx-text-fill: #fbb710");
            Desc.setFont(javafx.scene.text.Font.font("Courrier", 16));
            Desc.setStyle("-fx-text-fill: #333333");
            bt.setStyle("-fx-background-color: #fbb710; -fx-text-fill: black;");
            btfb.setStyle("-fx-background-color: #4267B2; -fx-text-fill: black;");
            bt3.setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
            bt2.setStyle("-fx-background-color: #fbb710; -fx-text-fill: black;");
            vbox1.setStyle("-fx-padding: 10;" 
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                    + "-fx-border-radius: 5;"  + "-fx-border-height:50" +"-fx-");
           
 btfb.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    Parent homePage;
                     Anonnce annonce = new Anonnce();
              String accessToken = "EAACP2IMdZCsoBALulx9ZAM8988YsQOp7jnIoADOVwL7IkcXE0OMx328e5cjcj18nBgOM2OcsA3UjC8ZAd9y77scEaKuTW6DotnFcvNZCZCKfzDU4DPg7lXqkCULv93Tpwx0G6YRVLAkZARfukXHJdkO7egIC42VbJq40dxjEAuc6pfP8f6XvChYBND09wCV7r1JeRtMqKIJ3pllbyrt6aiSsS5grhlbVoZD";
           Annoncesservice ls = new Annoncesservice();
          //  Annonce loc = ls.afficherLocalUn(Annonce.getId());
            Scanner s = new Scanner(System.in);
            FacebookClient fbClient = new DefaultFacebookClient(accessToken);
            FacebookType response = fbClient.publish("10216170886244629/feed", FacebookType.class,
                    Parameter.with("message", "Annonce " + evv.getTitle()+ "Description " + evv.getDescription() + " à " + evv.getTitle()),
                    Parameter.with("link", "https://www.google.com/"));
            System.out.println("fb.com/" + response.getId());
            System.out.println(accessToken);
                    }
                  });
    bt3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                         if(evv== null){
            JOptionPane.showMessageDialog(null, "choisir annonce");
                   
        }else{ 

                        if (evv.getA().equals(n.getUserName())) {

                            try {
                                es.delete(evv.getId());
                            } catch (SQLException ex) {
                                Logger.getLogger(ListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            vbox1.getChildren().remove(hv1);
                            vbox1.getChildren().remove(v1);
                            vbox1.getChildren().remove(h1);                            
                            vbox1.getChildren().remove(h2);
                            vbox1.getChildren().remove(btn);
                            vbox1.getChildren().remove(v2);
                            vbox1.getChildren().remove(sep);
                            Alert alertSec = new Alert(Alert.AlertType.INFORMATION);
                            alertSec.setHeaderText("Annonce suprrimée avec succées");
                            alertSec.showAndWait();

                        } else {   Alert alertSec = new Alert(Alert.AlertType.INFORMATION);
                            alertSec.setHeaderText("You dont have the permission ");
                            alertSec.showAndWait();    }
                         }
                    }

                });
 
 bt2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("comment.fxml"));
                        AnchorPane parent = loader.load();
                        Scene tableviewscene = new Scene(parent);
                        
                        CommentController controller = loader.getController();
                        int od=evv.getId();
                        Anonnce aaa = new Anonnce();
                        aaa.setId(od);
                        Annoncesservice saa = new Annoncesservice();
                        Anonnce kkk= new Anonnce();
                        kkk =saa.getannonce_id(aaa.getId());
                        controller.initData(kkk);
                        
                        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        window.setScene(tableviewscene);
                        window.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
           
bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("AnnonceShowFXML.fxml"));
                        AnchorPane parent = loader.load();
                        Scene tableviewscene = new Scene(parent);
                        
                        AnnonceShowFXMLController controller = loader.getController();
                        int od=evv.getId();
                        Anonnce aaa = new Anonnce();
                        aaa.setId(od);
                        Annoncesservice saa = new Annoncesservice();
                        Anonnce kkk= new Anonnce();
                        kkk =saa.getannonce_id(aaa.getId());
                        controller.initData(kkk);
                        
                        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        window.setScene(tableviewscene);
                        window.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        
        }

    }
    

    @FXML
    private void profile(ActionEvent event) throws IOException {
                
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/gui/profile_1.fxml"));
        
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
    private void ajouterann(ActionEvent event) throws IOException {
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/gui/annonce.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
    }

       private void home(ActionEvent event)  throws IOException{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/javafxapplication4/FXMLDocument.fxml"));
            Parent root= loader.load();
  
            parent1.getScene().setRoot(root);
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
