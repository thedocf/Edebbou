/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.Servicedepots;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author hp
 */
public class ModifierdepotsController implements Initializable {

    @FXML
    private ImageView re;
    @FXML
    private ImageView va;
    @FXML
    private TextField modifentreprise;
    @FXML
    private TextField modifsurface;
    @FXML
    private TextField modifville;
    @FXML
    private TextField modifcapacite;
    @FXML
    private TextField modifdescription;
    @FXML
    private Button retour; 
        @FXML
    private Button valider;

    public TextField getModifentreprise() {
        return modifentreprise;
    }

    public void setModifentreprise(TextField modifentreprise) {
        this.modifentreprise = modifentreprise;
    }

    public TextField getModifsurface() {
        return modifsurface;
    }

    public void setModifsurface(TextField modifsurface) {
        this.modifsurface = modifsurface;
    }

    public TextField getModifville() {
        return modifville;
    }

    public void setModifville(TextField modifville) {
        this.modifville = modifville;
    }

    public TextField getModifcapacite() {
        return modifcapacite;
    }

    public void setModifcapacite(TextField modifcapacite) {
        this.modifcapacite = modifcapacite;
    }
    
    public TextField getModifdescription() {
        return modifdescription;
    }

    public void setModifdescription(TextField modifdescription) {
        this.modifdescription = modifdescription;
    }

    public Button getRetour() {
        return retour;
    }

    public void setRetour(Button retour) {
        this.retour = retour;
    }

    public Button getValider() {
        return valider;
    }

    public void setValider(Button valider) {
        this.valider = valider;
    }
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        modifentreprise.setText(AfficherdepotsController.Recup.getEntreprise());
        modifsurface.setText(String.valueOf(AfficherdepotsController.Recup.getSurface()));
        modifville.setText(AfficherdepotsController.Recup.getVille());
        modifcapacite.setText(String.valueOf(AfficherdepotsController.Recup.getCapacite()));
        modifdescription.setText(AfficherdepotsController.Recup.getDescription());

        
    }   
    @FXML
    private void validerModifdepots(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Modification Depot ");
                            alert.setHeaderText(null);
                            alert.setContentText("voulez vous vraiment modifier cet depot ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            if (action.get() == ButtonType.OK)
                            {Servicedepots ac=new Servicedepots();
        
      
        ac.modifierdepots(AfficherdepotsController.Recup.getId(),modifentreprise.getText(), Integer.parseInt(modifsurface.getText()), modifville.getText(), Integer.parseInt(modifcapacite.getText()), modifdescription.getText()); // insertion dans la base de données
          try {
                                                 TrayNotification tray = new TrayNotification("succès", " ajouté", SUCCESS);
                                    tray.showAndWait();
                               
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherdepotsController.fxml"));
                                Parent root = loader.load();
                               AfficherdepotsController rc = loader.getController();
                               modifentreprise.getScene().setRoot(root);

                               
             
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());

                            }

                            }
                            

       
    }

    @FXML
    private void retourModif(ActionEvent event) {
          try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("afficherdepots.fxml"));
            Parent root= loader.load();
            AfficherdepotsController rc= loader.getController();
            
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
        
    }

    
   }    

    
    

