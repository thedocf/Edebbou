/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import static pidev.sandy.controller.DetailExperienceController.id;
import static pidev.sandy.controller.DetailExperienceController.image;
import static pidev.sandy.controller.DetailExperienceController.titre;
import static pidev.sandy.controller.ModifDemandeController.oe;
import pidev.sandy.entites.Repondre;
import pidev.sandy.entites.User;
import pidev.sandy.entites.avis;
import pidev.sandy.entites.demande_experience;
import pidev.sandy.services.DemandeRecommandation;
import pidev.sandy.services.PartageExperience;
import pidev.sandy.controller.LoginController;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class DetailDemandeController implements Initializable {

    @FXML
    private AnchorPane UserPane;
    @FXML
    private VBox vbox;
    @FXML
    private Label backexp;
    @FXML
    private JFXButton btnclose;
    @FXML
    private Label nomPExp;
    @FXML
    private FontAwesomeIcon notif;
    @FXML
    private AnchorPane listpane;
    @FXML
    private ImageView imgdetail;
    @FXML
    private Label Description;
    @FXML
    private Label Adresse;
    @FXML
    private Label adddetail;
    @FXML
    private Label Region;
    @FXML
    private TextArea descavis;
    @FXML
    private JFXButton addavis;
    @FXML
    private Label Description1;
    DemandeRecommandation drec = new DemandeRecommandation();
    demande_experience d = new demande_experience();
    @FXML
    private Label titledetail;
    @FXML
    private Label descdetail;
    public static int id;
    @FXML
    private JFXButton adddmd;
    @FXML
    private JFXButton modifierdmd;
    @FXML
    private JFXButton suppdmd;
    @FXML
    private VBox vboxdemande;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
        System.out.println("user id "+UserInterfaceController.userid);
    }

    @FXML
    private void closeApp(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void getnotif(MouseEvent event) {
    }

    @FXML
    private void showAddPartageExp(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/AjoutDemande.fxml"));
        Parent root = loader.load();
        descavis.getScene().setRoot(root); 
    
    }

    @FXML
    private void BackListsdemd(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/DemandeRecommandation.fxml"));
        Parent root = loader.load();
        nomPExp.getScene().setRoot(root);
    }

    public void initData(demande_experience o) {
        d=o;
        d.setIduser(o.getIduser());
        imgdetail.setImage(new Image("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + o.getImage()));
        descdetail.setText(o.getDescripion());
        adddetail.setText(o.getAddresse());
        titledetail.setText(o.getNom());
        nomPExp.setText(o.getNom());
        id = o.getId();
  
        Repondre rp=new Repondre();
        List<Repondre> list = drec.ListComm(id);
        HBox hbox[] = new HBox[list.size()];
        for (int i = 1; i < list.size(); i++) {

            hbox[i] = new HBox();
            ImageView img = new ImageView(new Image("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + drec.GetUserAvatar(list.get(i).getUserid())));
            img.setFitWidth(50);
            img.setFitHeight(50);
            img.setPreserveRatio(true);
            Label lb = new Label((list.get(i).getMessage()));
            lb.setTextFill(Color.web("#fff"));
          
            //Label rt=new Label(""+);
            hbox[i].getChildren().addAll(img, lb );
            hbox[i].setAlignment(Pos.CENTER_LEFT);
            hbox[i].setPadding(new Insets(10, 0, 10, 0));

            vboxdemande.getChildren().addAll(hbox[i]);
        }
        
    }

    @FXML
    private void addDemand(ActionEvent event) {
        Repondre rp = new Repondre();
        rp.setMessage(descavis.getText());
        System.out.println("user"+UserInterfaceController.userid);
        rp.setUserid(UserInterfaceController.userid);
        System.out.println("id"+d.getId());
        rp.setDemandeid(d.getId());
        drec.AddComm(rp);
        Notifications.create().title("Commentaire").text("Votre commaintaire est ajouter avec succée").showConfirm();
        
        drec.ajoutnotif(UserInterfaceController.userid, d.getId(),d.getNom());
        System.out.println("notification ajouter apres commentaire");
    }

    @FXML
    private void ModifDemande(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/ModifDemande.fxml"));
        Parent root;

        try {
            root = loader.load();
             ModifDemandeController detail=loader.getController();
                detail.initData(d);    
        //    System.out.println("id =" + id);
            descdetail.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(PartageExperienceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void suppDemande(ActionEvent event) {
        DemandeRecommandation pexp = new DemandeRecommandation();
        //System.out.println(id);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/DemandeRecommandation.fxml"));
        Parent root;

        try {
            root = loader.load();
            pexp.SupprimerDemande(id);
           // System.out.println("id =" + id);
            descdetail.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(PartageExperienceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Notifications.create()
                .title("Supprission d'un partage d'expérience")
                .darkStyle()
                .text("Votre expérience partager a été supprimer avec succès")
                .showConfirm();
    }

}
