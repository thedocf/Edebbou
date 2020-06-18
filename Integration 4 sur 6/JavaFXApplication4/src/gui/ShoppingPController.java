/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Product;
import entite.Produit;

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
import service.ServicePanier;
import service.UserSession;
import service.serviceCategory;

/**
 * FXML Controller class
 *
 * @author Th3Doc
 */
public class ShoppingPController implements Initializable {

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
    private AnchorPane parent1;
    @FXML
    private ChoiceBox<String> catcho;
    @FXML
    private Button filter;
    @FXML
    private Button back;
    @FXML
    private Button profile1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        back.setVisible(false);
        List<Product> le = new ArrayList<>();
        serviceProduct es = new serviceProduct();
        serviceCategory sp = new serviceCategory ();
  
        ObservableList<String> l = null;
        try {
          le = es.readAll();
            l = sp.readAll2();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingPController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catcho.setItems(l);
        for (Product evv : le) {
            
            Float sd = evv.getPrix();
            Label nom = new Label();
            Label Desc = new Label();
            Label Price = new Label();
            Label qte = new Label();
            ImageView photo = new ImageView(new Image(evv.getImage()));
            photo.setFitHeight(150);
            photo.setFitWidth(150);
            Text ty = new Text("Price: ");
            Text dd = new Text(" | ");
            ty.setFill(Color.DARKGREY);
            dd.setFill(Color.DARKGREY);
            nom.setText(evv.getNom());
            Desc.setText(evv.getDescription());
            Price.setText(sd.toString());
            if (evv.getQte()>0)
            {
                qte.setText("Available");
                qte.setStyle("-fx-text-fill: #00ff00");
                
            }
            else {
                qte.setText("Not Available");
                qte.setStyle("-fx-text-fill: #ff0000");
            }
            HBox h2 = new HBox();
            HBox h1 = new HBox();
            VBox rv = new VBox();
            HBox btn = new HBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
           
            HBox hv1 = new HBox();
            Button bt = new Button("Buy");
            Button bt3 = new Button("Add to Cart");
            bt3.setOnAction(e->{
                Produit p = new Produit();
                p.setNom(evv.getNom());
                p.setPrix(evv.getPrix());
        ServicePanier spp = new ServicePanier();
        spp.ajouterProduit(p, 1);
            });
            final Separator sep = new Separator();
            vbox1.setSpacing(10);
            vbox1.setStyle("-fx-background-color: DARKORANGE; -fx-text-fill: DARKGREY;");
            sep.setMaxWidth(Double.MAX_EXPONENT);
            sep.setStyle(" -fx-text-fill: DARKGREY;");
              h1.setAlignment(Pos.CENTER);
                h2.setAlignment(Pos.CENTER);
            h2.setSpacing(10);
            
            hv1.getChildren().add(nom);
            h1.getChildren().add(photo);
            v1.getChildren().add(Desc);
            h2.getChildren().add(ty);
            
            h2.getChildren().add(Price);
            h2.getChildren().add(dd);
            h2.getChildren().add(qte);
            btn.getChildren().add(bt);
            btn.setSpacing(10);
            btn.getChildren().add(bt3);
            
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
            bt3.setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
            vbox1.setStyle("-fx-padding: 10;" 
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                    + "-fx-border-radius: 5;"  + "-fx-border-height:50");
    }    
    
}

    @FXML
    private void onmouseexit(MouseEvent event) {
    }

    @FXML
    private void Onmouseenter(MouseEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
       FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/login.fxml"));
            Parent root= loader.load();
  
            parent1.getScene().setRoot(root);
    }

    
    

    private void tt2() throws SQLException {
      
   
        List<Product> le = new ArrayList<>();
        serviceProduct es=new serviceProduct();
        le=es.readAllBC(catcho.getValue());
        vbox1.getChildren().clear();
        for (Product evv : le) {
            
            Float sd = evv.getPrix();
            Label nom = new Label();
            Label Desc = new Label();
            Label Price = new Label();
            Label qte = new Label();
            
            ImageView photo = new ImageView(new Image(evv.getImage()));
            photo.setFitHeight(150);
            photo.setFitWidth(150);
            Text ty = new Text("Price: ");
            Text dd = new Text(" | ");
            ty.setFill(Color.DARKGREY);
            dd.setFill(Color.DARKGREY);
            nom.setText(evv.getNom());
            Desc.setText(evv.getDescription());
            Price.setText(sd.toString());
            if (evv.getQte()>0)
            {
                qte.setText("Available");
                qte.setStyle("-fx-text-fill: #00ff00");
                
            }
            else {
                qte.setText("Not Available");
                qte.setStyle("-fx-text-fill: #ff0000");
            }
            HBox h2 = new HBox();
            HBox h1 = new HBox();
            VBox rv = new VBox();
            HBox btn = new HBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
           
            HBox hv1 = new HBox();
            Button bt = new Button("Buy");
            Button bt3 = new Button("Add to Cart");
            
            final Separator sep = new Separator();
            vbox1.setSpacing(10);
            vbox1.setStyle("-fx-background-color: DARKORANGE; -fx-text-fill: DARKGREY;");
            sep.setMaxWidth(Double.MAX_EXPONENT);
            sep.setStyle(" -fx-text-fill: DARKGREY;");
              h1.setAlignment(Pos.CENTER);
                h2.setAlignment(Pos.CENTER);
            h2.setSpacing(10);
            
            hv1.getChildren().add(nom);
            h1.getChildren().add(photo);
            v1.getChildren().add(Desc);
            h2.getChildren().add(ty);
            
            h2.getChildren().add(Price);
            h2.getChildren().add(dd);
            h2.getChildren().add(qte);
            btn.getChildren().add(bt);
            btn.setSpacing(10);
            btn.getChildren().add(bt3);
            
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
            bt3.setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
            vbox1.setStyle("-fx-padding: 10;" 
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                    + "-fx-border-radius: 5;"  + "-fx-border-height:50");
    }    
    }

    @FXML
    /* private void tt(MouseEvent event) throws SQLException {
       
                List<Product> le = new ArrayList<>();
        serviceProduct es=new serviceProduct();
        le=es.readAllBC(catcho.getValue());
        for (Product evv : le) {
            
            Float sd = evv.getPrix();
            Label nom = new Label();
            Label Desc = new Label();
            Label Price = new Label();
            Label qte = new Label();
            
            ImageView photo = new ImageView(new Image(evv.getImage()));
            photo.setFitHeight(150);
            photo.setFitWidth(150);
            Text ty = new Text("Price: ");
            Text dd = new Text(" | ");
            ty.setFill(Color.DARKGREY);
            dd.setFill(Color.DARKGREY);
            nom.setText(evv.getNom());
            Desc.setText(evv.getDescription());
            Price.setText(sd.toString());
            if (evv.getQte()>0)
            {
                qte.setText("Available");
                qte.setStyle("-fx-text-fill: #00ff00");
                
            }
            else {
                qte.setText("Not Available");
                qte.setStyle("-fx-text-fill: #ff0000");
            }
            HBox h2 = new HBox();
            HBox h1 = new HBox();
            VBox rv = new VBox();
            HBox btn = new HBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
           
            HBox hv1 = new HBox();
            Button bt = new Button("Buy");
            Button bt3 = new Button("Add to Cart");
            
            final Separator sep = new Separator();
            vbox1.setSpacing(10);
            vbox1.setStyle("-fx-background-color: DARKORANGE; -fx-text-fill: DARKGREY;");
            sep.setMaxWidth(Double.MAX_EXPONENT);
            sep.setStyle(" -fx-text-fill: DARKGREY;");
              h1.setAlignment(Pos.CENTER);
                h2.setAlignment(Pos.CENTER);
            h2.setSpacing(10);
            
            hv1.getChildren().add(nom);
            h1.getChildren().add(photo);
            v1.getChildren().add(Desc);
            h2.getChildren().add(ty);
            
            h2.getChildren().add(Price);
            h2.getChildren().add(dd);
            h2.getChildren().add(qte);
            btn.getChildren().add(bt);
            btn.setSpacing(10);
            btn.getChildren().add(bt3);
            
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
            bt3.setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
            vbox1.setStyle("-fx-padding: 10;" 
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                    + "-fx-border-radius: 5;"  + "-fx-border-height:50");
    }    

    }

    @FXML
    private void tt3(MouseEvent event) throws SQLException {
        
    }
*/
    private void tocart(ActionEvent event) throws IOException {
        
                    FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/panier.fxml"));
            Parent root= loader.load();
  
            catcho.getScene().setRoot(root);
    }

    @FXML
    private void filter(ActionEvent event) throws SQLException {
        back.setVisible(true);
        vbox1.getChildren().clear();
         List<Product> le = new ArrayList<>();
        serviceProduct es=new serviceProduct();
       le= es.readAllBC(catcho.getValue());
        for (Product evv : le) {
            
            Float sd = evv.getPrix();
            Label nom = new Label();
            Label Desc = new Label();
            Label Price = new Label();
            Label qte = new Label();
            
            ImageView photo = new ImageView(new Image(evv.getImage()));
            photo.setFitHeight(150);
            photo.setFitWidth(150);
            Text ty = new Text("Price: ");
            Text dd = new Text(" | ");
            ty.setFill(Color.DARKGREY);
            dd.setFill(Color.DARKGREY);
            nom.setText(evv.getNom());
            Desc.setText(evv.getDescription());
            Price.setText(sd.toString());
            if (evv.getQte()>0)
            {
                qte.setText("Available");
                qte.setStyle("-fx-text-fill: #00ff00");
                
            }
            else {
                qte.setText("Not Available");
                qte.setStyle("-fx-text-fill: #ff0000");
            }
            HBox h2 = new HBox();
            HBox h1 = new HBox();
            VBox rv = new VBox();
            HBox btn = new HBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
           
            HBox hv1 = new HBox();
            Button bt = new Button("Buy");
            Button bt3 = new Button("Add to Cart");
            
            final Separator sep = new Separator();
            vbox1.setSpacing(10);
            vbox1.setStyle("-fx-background-color: DARKORANGE; -fx-text-fill: DARKGREY;");
            sep.setMaxWidth(Double.MAX_EXPONENT);
            sep.setStyle(" -fx-text-fill: DARKGREY;");
              h1.setAlignment(Pos.CENTER);
                h2.setAlignment(Pos.CENTER);
            h2.setSpacing(10);
            
            hv1.getChildren().add(nom);
            h1.getChildren().add(photo);
            v1.getChildren().add(Desc);
            h2.getChildren().add(ty);
            
            h2.getChildren().add(Price);
            h2.getChildren().add(dd);
            h2.getChildren().add(qte);
            btn.getChildren().add(bt);
            btn.setSpacing(10);
            btn.getChildren().add(bt3);
            
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
            bt3.setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
            vbox1.setStyle("-fx-padding: 10;" 
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                    + "-fx-border-radius: 5;"  + "-fx-border-height:50");
    }    
    }

    @FXML
    private void back(ActionEvent event) {
        back.setVisible(false);
        List<Product> le = new ArrayList<>();
        serviceProduct es = new serviceProduct();
        serviceCategory sp = new serviceCategory ();
  
        ObservableList<String> l = null;
        try {
          le = es.readAll();
            l = sp.readAll2();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingPController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catcho.setItems(l);
        for (Product evv : le) {
            
            Float sd = evv.getPrix();
            Label nom = new Label();
            Label Desc = new Label();
            Label Price = new Label();
            Label qte = new Label();
            ImageView photo = new ImageView(new Image(evv.getImage()));
            photo.setFitHeight(150);
            photo.setFitWidth(150);
            Text ty = new Text("Price: ");
            Text dd = new Text(" | ");
            ty.setFill(Color.DARKGREY);
            dd.setFill(Color.DARKGREY);
            nom.setText(evv.getNom());
            Desc.setText(evv.getDescription());
            Price.setText(sd.toString());
            if (evv.getQte()>0)
            {
                qte.setText("Available");
                qte.setStyle("-fx-text-fill: #00ff00");
                
            }
            else {
                qte.setText("Not Available");
                qte.setStyle("-fx-text-fill: #ff0000");
            }
            HBox h2 = new HBox();
            HBox h1 = new HBox();
            VBox rv = new VBox();
            HBox btn = new HBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
           
            HBox hv1 = new HBox();
            Button bt = new Button("Buy");
            Button bt3 = new Button("Add to Cart");
            bt3.setOnAction(e->{
                Produit p = new Produit();
                p.setNom(evv.getNom());
                p.setPrix(evv.getPrix());
        ServicePanier spp = new ServicePanier();
        spp.ajouterProduit(p, 1);
            });
            final Separator sep = new Separator();
            vbox1.setSpacing(10);
            vbox1.setStyle("-fx-background-color: DARKORANGE; -fx-text-fill: DARKGREY;");
            sep.setMaxWidth(Double.MAX_EXPONENT);
            sep.setStyle(" -fx-text-fill: DARKGREY;");
              h1.setAlignment(Pos.CENTER);
                h2.setAlignment(Pos.CENTER);
            h2.setSpacing(10);
            
            hv1.getChildren().add(nom);
            h1.getChildren().add(photo);
            v1.getChildren().add(Desc);
            h2.getChildren().add(ty);
            
            h2.getChildren().add(Price);
            h2.getChildren().add(dd);
            h2.getChildren().add(qte);
            btn.getChildren().add(bt);
            btn.setSpacing(10);
            btn.getChildren().add(bt3);
            
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
            bt3.setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
            vbox1.setStyle("-fx-padding: 10;" 
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                    + "-fx-border-radius: 5;"  + "-fx-border-height:50");
    }   
    }

    @FXML
     private void annonce(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/listFXML.fxml"));
            Parent root= loader.load();
  
            parent1.getScene().setRoot(root);
    }

    @FXML
   private void profile(ActionEvent event) throws IOException {
                
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/gui/profile_1.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
      
    }

}
