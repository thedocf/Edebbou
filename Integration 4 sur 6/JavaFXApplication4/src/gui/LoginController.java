/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.github.sarxos.webcam.Webcam;
import com.gluonhq.charm.glisten.control.TextField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import service.Fosuserservice;
import service.UserSession;
import static util.SendMail.sendEmailWithAttachments;

;
/**
 * FXML Controller class
 *
 * @author Fida
 */
public class LoginController implements Initializable {
    @FXML
    private AnchorPane login;
    @FXML
    private AnchorPane login1;
    @FXML
    private JFXTextField t1;
    @FXML
    private JFXPasswordField t2;
    @FXML
    private JFXButton b1;
    @FXML
    private ImageView image;
    
        public static int  codem;
    public static String username;
    public static String motpass;
    private Label label;
    @FXML
    private ImageView image1;
    @FXML
    private JFXButton register;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SeConnecter(ActionEvent event) throws IOException {
        
        username = t1.getText();
        motpass = t2.getText();
        Fosuserservice  sc = new Fosuserservice ();
        if (sc.login(username, motpass)) {
            String role = sc.getRolebyId(sc.getIdbymail(username));
 String usr = sc.getUsenamebyId(sc.getIdbymail(username));
            if (role.equals("a:0:{}")) { 
               UserSession.getInstace(usr,username);

                // AnchorPane pane = FXMLLoader.load(getClass().getResource("panier.fxml"));
                 
                 AnchorPane pane = FXMLLoader.load(getClass().getResource("ShoppingProviders.fxml"));      
                 login.getChildren().setAll(pane);
            }
            else {
                 UserSession.getInstace(usr,username);
                   AnchorPane pane = FXMLLoader.load(getClass().getResource("homeback.fxml"));
          //       AnchorPane pane = FXMLLoader.load(getClass().getResource("livreurDash.fxml"));
                 login.getChildren().setAll(pane);
            }
        }  else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Verifiez Vos Coordonnees !!!");
            alert.showAndWait();
        }
    }

    @FXML
    private void MotpassOubliee(ActionEvent event) throws MessagingException, IOException, InterruptedException {
         username = t1.getText();
        Fosuserservice  sc = new Fosuserservice ();
   		
        Random r = new Random ();
      codem =r.nextInt(9999-1000+1);
                System.out.println(codem);
        if(isValidEmailAddress(t1.getText())){
           Webcam webcam = Webcam.getDefault();
          
                    webcam.open();
                    ImageIO.write(webcam.getImage(), "JPG", new File("recovery.jpg"));
                    Thread.sleep(3);
                    webcam.close();
                    String[] attachFiles = new String[1];
                    attachFiles[0] = "C:\\Users\\Fida\\Desktop\\Integration 3 sur 6\\JavaFXApplication4\\recovery.jpg";
                    String token=Integer.toString(codem);
                   sendEmailWithAttachments("smtp.gmail.com", "465", "devcrewforum@gmail.com", "forum 1234",
                            t1.getText(), "Eddebou | Recovery mail", "Use this token to recover your Password : "+token, attachFiles);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Email de récuperation envoyé !");
                    alert.setHeaderText("Email de récuperation envoyé ! ");
                    alert.setContentText("Nous avons envoyé un mail de récuperation , veuillez consulter votre boit Mail ! ");

                    alert.showAndWait();
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("Motpasseoublie.fxml"));
          //       AnchorPane pane = FXMLLoader.load(getClass().getResource("livreurDash.fxml"));
                 login.getChildren().setAll(pane);
         }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Adresse Email Non Valide !!!");
            alert.showAndWait();
        }
       
    }
      public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }


    @FXML
    private void register(ActionEvent event) throws IOException  {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/register.fxml"));
            Parent root = loader.load();
            RegisterController rc = loader.getController();
            register.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }

    }
        
        
        
        
        
        
        
        
        
     //           AnchorPane pane   = FXMLLoader.load(getClass().getResource("/gui/login.fxml"));
      // rootpane.getChildren().setAll(pane);
    
    }  
    



    

