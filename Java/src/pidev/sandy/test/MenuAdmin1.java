/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
//mail import

/**
 *
 * @author Alaa
 */
public class MenuAdmin1 extends Application {
 
    
    public static int Id_user_connecte;
    
    private Parent gotoLogin() {
        try {
           Parent root = FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/Login.fxml"));
           return root;
        } catch (Exception ex) {
            Logger.getLogger(MenuAdmin1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
 
    
    public void start(Stage primaryStage) throws IOException {

        
        
        
        Parent root =  gotoLogin();

        Scene scene = new Scene(root);

        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML.fxml")); 
        //FXMLController controller = fxmlLoader.<FXMLController>getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          MyServices myServices=new MyServices();
           List<User> list=new ArrayList<>();
  
          
         /* Categoris cmere=new Categoris();
          cmere.setId(1);
         
          cmere=myServices.chercherCategorieById(cmere);
          
          Categoris categoris=new Categoris();
          categoris.setIdcategoriemere(cmere);
  
          categoris=myServices.chercherCategorieByCateMere(categoris);*/
           
          
          /*String ROLE="a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}";*/
//         String ROLE="a:1:{i:0;s:11:\"ROLE_MEMBRE\";}";
//         User U=myServices.chercherUtilisateurByid(21);
//          System.out.println(U.toString());
//         list=myServices.afficherlisteMembreAPointMerciSuffisant(ROLE,2);
////       
      //  System.out.println(list.toString()); 
      
      
      

        launch(args);

    }

}
