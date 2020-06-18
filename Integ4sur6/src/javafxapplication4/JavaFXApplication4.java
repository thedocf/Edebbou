/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;
//bilehi tra runi w 5adem crud enti
import entite.Produit;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import service.ServicePanier;
import util.SMTP;


public class JavaFXApplication4 extends Application {
    
    
     public static Boolean isSplashLoaded = false; 
    @Override
    public void start(Stage stage) throws Exception {
       
        ServicePanier.panier =  new HashMap<Produit, Integer>();
        Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication4/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
       stage.getIcons().add(new Image("/images/E.png"));
       stage.setTitle("Eddebou");
        
        stage.setScene(scene);
        stage.show();
    }
    
    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
