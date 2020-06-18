/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import pidev.sandy.entites.BonPlan;
import pidev.sandy.entites.avisbonplan;
import pidev.sandy.entites.User;
import pidev.sandy.services.ServiceBonplan;
/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class RatingController implements Initializable {

    @FXML
    private TextField commentaire;
    @FXML
    private TextField titre;
    @FXML
    private Rating rating;
    @FXML
    private Button ajouterButton;
    @FXML
    private TableColumn<avisbonplan, String> membre;
    @FXML
    private Button supprimer;
    @FXML
    private TableColumn<avisbonplan, String> titret;
    @FXML
    private TableColumn<avisbonplan, String> commentairet;
     BonPlan b= TousLesBonPlanController.bonplanclient;
    @FXML
    private TableView<avisbonplan> table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           ServiceBonplan bon = new ServiceBonplan();
        ArrayList arrayList = (ArrayList) bon.ListeAvis(b.getId());
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
  membre.setCellValueFactory(new PropertyValueFactory<>("refavis"));
       titret.setCellValueFactory(new PropertyValueFactory<>("titre"));
      commentairet.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        System.out.println("BonPlan");
       
               

        avisbonplan a=new avisbonplan(rating.getRating(),new User(1), titre.getText(),commentaire.getText(), b);
//        avisbonplan a=new avisbonplan(5.0,new User(1), titre.getText(),commentaire.getText(), b);
       
        ServiceBonplan sbn =new ServiceBonplan();
           
         if (!titre.getText().equals("")&& !commentaire.getText().equals("") )
         {
                   sbn.ajoutrating(a);
                     new Alert(Alert.AlertType.INFORMATION, "Merci pour votre commentaire").show();
     
         }
         else
         {
                  new Alert(Alert.AlertType.ERROR, "Ecrivez votre commentaire s'il vous plait avant de valider").show();
         }
             
  
        
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
               if (table.getSelectionModel().isEmpty()) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Choix invalide")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
        } else {
            ObservableList<avisbonplan> bon = table.getSelectionModel().getSelectedItems();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment supprimer ce commentaire");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new ServiceBonplan().supprimeravis(bon.get(0).getId());
                System.out.println(bon.get(0).getId());
            }
        }
        list();
        
        
    }
    
       public void list(){
        ServiceBonplan bon = new ServiceBonplan();
        ArrayList arrayList = (ArrayList) bon.ListeAvis(b.getId());
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        

}
   
    
    
    
}
