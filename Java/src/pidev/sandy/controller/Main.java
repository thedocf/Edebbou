/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author Alaa
 */
public class Main extends Application{
      @Override
    public void start(Stage stage) throws IOException {
         Parent root;
  //   root = FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/Adminpartenaire.fxml"));
root = FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/List.fxml"));
     
   // root = FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/UserInterface.fxml"));
 //root = FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AjouterBonPlan.fxml"));
       
      // root = FXMLLoader.load(getClass().getResource("AccueilClient.fxml"));
 // root = FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/Espacepartenairee.fxml"));
       
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
      public static void main(String[] args) {
//          System.out.println("hello"+new ServiceBonplan().listerBonPlan());
          
//           double moyennea = 0;
//        double somme = 0;
//        int n = 1;
//        ServiceBonplan bon = new ServiceBonplan();
//        ArrayList arf = (ArrayList) bon.ListeAvis(12);
//        Iterator<avisbonplan> it = arf.iterator();
//
//        while (it.hasNext()) {
////            avisbonplan avis = it.next();
//////            n = n + 1;
////            somme = (somme + avis.getRating());*
//System.out.println(it.next().getRating());
//        }
//          
       
          
        launch(args);
        
    }
      

      
}
