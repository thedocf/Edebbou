/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import entite.depots;
import service.Servicedepots;
import Utils.ControlleSaisie;
import Utils.copyImages;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouterDepotsController implements Initializable {
    @FXML
    private ImageView re;
    @FXML
    private ImageView co;
    @FXML
    private TextField entreprise;
    @FXML
    private TextField surface;
    @FXML
    private TextField ville;
    @FXML
    private TextField capacite;
    @FXML
    private TextField description;
    @FXML
    private Button ajouter;
     @FXML
    private Button retour;
    @FXML
    private ImageView imgv;
    @FXML
    private Button btnPhotoUser;
    @FXML
    private Text txtPhotoUser;
    private String absolutePathPhotoUser;
    @FXML
    private ImageView imageView;
    private Image image ;
    
    
        @FXML
    private void photoUserChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        btnPhotoUser.setOnAction(e -> {
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
                System.out.println(choix.getAbsolutePath());
                absolutePathPhotoUser = choix.getAbsolutePath();
                txtPhotoUser.setText(choix.getName());
                image = new Image(choix.getAbsoluteFile().toURI().toString());
                imageView.setImage(image);
            } else {
                System.out.println("Image introuvable");
            }
        });
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       
    @FXML
    public void insertDepots(ActionEvent event)
    {
       
    if (!(ControlleSaisie.estVide(entreprise, "entreprise "))
            && !(ControlleSaisie.estVide(surface, "surface "))
            && !(ControlleSaisie.estVide(ville, "ville "))
            && !(ControlleSaisie.estVide(capacite, "capacite "))
            && !(ControlleSaisie.estVide(description, "description "))
            && !(txtPhotoUser.getText().equals(""))
            )
        
            
        {  
        depots r = new depots();
        r.setEntreprise(entreprise.getText());
        r.setSurface(Integer.parseInt(surface.getText()));
        r.setVille(ville.getText());
        r.setCapacite(Integer.parseInt(capacite.getText()));
        r.setDescription(description.getText());
        r.setPhoto(txtPhotoUser.getText());
        
        
        copyImages.deplacerVers(txtPhotoUser, absolutePathPhotoUser, "C:\\wamp64\\www\\Edebbou-fyras\\web\\uploads\\images\\depots");
        
        
        Servicedepots sr=new Servicedepots();
        sr.ajouterdepots(r);

        TrayNotification tray = new TrayNotification("succès", " ajouté", SUCCESS);
        tray.showAndWait();
        
        
        
    }
    
    
    
    }

    public TextField getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(TextField entreprise) {
        this.entreprise = entreprise;
    }

    public TextField getSurface() {
        return surface;
    }

    public void setSurface(TextField surface) {
        this.surface = surface;
    }

    public TextField getVille() {
        return ville;
    }

    public void setVille(TextField ville) {
        this.ville = ville;
    }

    public TextField getCapacite() {
        return capacite;
    }

    public void setCapacite(TextField capacite) {
        this.capacite = capacite;
    }
    
    public TextField getDescription() {
        return description;
    }

    public void setDescription(TextField description) {
        this.description = description;
    }

    public Button getAjouter() {
        return ajouter;
    }

    public void setAjouter(Button ajouter) {
        this.ajouter = ajouter;
    }
        @FXML
    private void retourafficher(ActionEvent event) {
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
