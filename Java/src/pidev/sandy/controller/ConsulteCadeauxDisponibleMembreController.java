/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev.sandy.entites.Cadeaux;
import pidev.sandy.entites.Compte;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class ConsulteCadeauxDisponibleMembreController implements Initializable {

 
    @FXML
    private GridPane gridpane;

    /**
     * Initializes the controller class.
     */
   
    
    
    
    MyServices myServices=new MyServices();
        Image img = null;
    @FXML
    private AnchorPane AnchorPaneCadeauxDisponible;
    @FXML
    private JFXButton btnAnnuler;
 
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                MyServices services = new MyServices();
                     User loggedUser = pidev.sandy.controller.LoginController.getInstance().getLoggedUser();
                 
        
  
 User UserConneter=myServices.chercherUtilisateurByid(loggedUser.getId());
  
        Compte compte = services.chercherUtilisateurByUsernameDansLecompte(UserConneter.getUsername());
        
        List<Cadeaux> cadeaux = new ArrayList<>();

         
           cadeaux=myServices.afficherlisteCadeaux();
       
        for (int i = 0; i < cadeaux.size(); i++) {
            if (!"".equals(cadeaux.get(i).getImage())) {
                    
                    File file = new File("C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\"+cadeaux.get(i).getImage());
//                
                try {     System.out.println(cadeaux.toString());
                    img = new Image(file.toURI().toURL().toString());
                    
                    ImageView photo = new ImageView(img);
                    photo.setFitHeight(150);
                    photo.setFitWidth(150);
                    photo.setImage(img);
                    gridpane.add(photo, 0, i + 1);
                } catch (MalformedURLException ex) {
                    System.out.println(ex);
                }  
                Label Libelle  = new Label(cadeaux.get(i).getLibelle());
                Label Point = new Label(String.valueOf(cadeaux.get(i).getValeur_point()));
                Label Categoris  = new Label(String.valueOf(cadeaux.get(i).getCategorisCadeaux().getLibelle()));
        
                Label QuantitDisponible  = new Label(String.valueOf(cadeaux.get(i).getQuantite_actuel()));
      
    
             
                     final  String LibelleCadeaux;
                     final  float point;
                     final  float quantiteActuel;
                     LibelleCadeaux=  cadeaux.get(i).getLibelle();
                   point=cadeaux.get(i).getValeur_point();
                   quantiteActuel=cadeaux.get(i).getQuantite_actuel();
                ImageView delete = new ImageView(new Image("File:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\ButtonAdd.png"));
 
                
                      delete.setFitHeight(50);
                    delete.setFitWidth(50);
                  
                    gridpane.add(delete, 5, i + 1);
                
    
                delete.setOnMouseClicked((MouseEvent event) -> {
            
                    
                    
                    if (compte.getPoint_merci() < point) {
                        System.out.println(compte.getPoint_merci());
                          Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Tu ne peut pas demander ce cadeaux car tes point est inssiffusant est egale : "+compte.getPoint_merci());
                    alert.setContentText(null);
                   alert.show();
                          
                    }
                    
                    
                       else  if (quantiteActuel < 0) {
                        System.out.println(compte.getPoint_merci());
                          Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Tu ne peut pas demander ce cadeaux car la quantite est indisponible dans la stock==> "+quantiteActuel);
                    alert.setContentText(null);
                   alert.show();
                          
                    }
                    
                     else
                        { System.out.println(compte.getPoint_merci());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Tu peut demander ce cadeaux car tes point est suffisant ");
                    alert.setContentText(null);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                         
                        Notifications n = Notifications.create().title("Information").graphic(null).position(Pos.TOP_CENTER).text("Vous avez selectionner Ce Cadeaux pour choisir");
                        n.showInformation();
                        try {  
                            
                             
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/AjouterDemandeCadeauxMembre.fxml"));
        Parent root=loader.load();
        AjouterDemandeCadeauxMembreController adcmc=loader.getController();//charger le controleur
        adcmc.setTXMessage("");
        adcmc.setTXDescription("");
        adcmc.setTXStatus("Attent");
        adcmc.setTXCadeaux(LibelleCadeaux);
        adcmc.setTXMembredemande(UserConneter.getUsername());
           Categoris.getScene().setRoot(root);
                            
  
                        }catch (IOException ex) {
                            Logger.getLogger(ConsulteCadeauxDisponibleMembreController.class.getName()).log(Level.SEVERE, null, ex);
                        } } else {
                        // ... user chose CANCEL or closed the dialog
                    }
                }});

 
      

                Libelle.setFont(Font.font("system", FontWeight.BOLD, FontPosture.REGULAR, 16));
                Point.setFont(Font.font("system", FontWeight.BOLD, FontPosture.ITALIC, 16));
                Categoris.setFont(Font.font("system", FontWeight.BOLD, FontPosture.ITALIC, 16));
                 
                QuantitDisponible.setFont(Font.font("system", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 16));
             
//                offername.setOnAction((ActionEvent event) -> {
//                    idOffre = 0;
//                    idOffre = Integer.parseInt(idLab.getText());
//                });
                gridpane.add(Libelle, 1, i + 1);
                gridpane.add(Point, 2, i + 1);
                gridpane.add(Categoris, 3, i + 1);
                gridpane.add(QuantitDisponible, 4, i + 1);
          
 
      
            }
        }

        // TODO
    }
    
    

    @FXML
    private void handleButtonCancelar(ActionEvent event) throws IOException {
        
                 setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ListeDesDemandeCadeauxMembre.fxml")));

        
        
    }
    
    private void setNode(Node node) {
        AnchorPaneCadeauxDisponible.getChildren().clear();
        AnchorPaneCadeauxDisponible.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }
    
}
