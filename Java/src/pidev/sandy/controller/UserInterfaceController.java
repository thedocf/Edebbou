/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import impl.org.controlsfx.skin.NotificationBar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
import pidev.sandy.controller.LoginController;
import pidev.sandy.test.MenuAdmin1;

/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class UserInterfaceController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btnclose;
    private Label btndemanderec;
    private static MyServices myServices = new MyServices();
    private VBox btnGestionAffichage;
    @FXML
    private AnchorPane parent;
    @FXML
    private Label categorie;
    @FXML
    private Label partenaire;
    @FXML
    private Label loggeduser;

    public static int userid;
    @FXML
    private Label profileBtn;
    @FXML
    private Label cadeauxBtn;
    @FXML
    private AnchorPane AdneneGadourAnchorPane;
    public static String username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        User loggedUser = pidev.sandy.controller.LoginController.getInstance().getLoggedUser();
        User UserConneter = myServices.chercherUtilisateurByid(loggedUser.getId());
        userid = loggedUser.getId();
        System.out.println("deeerfr");
        
   MenuAdmin1.Id_user_connecte=loggedUser.getId();

 username=UserConneter.getUsername();
        loggeduser.setText("                " + String.valueOf(UserConneter.getUsername()));
//       
//    
//          System.out.println("scscscsc"+loggeduser.getText());
//    try {
//  
//            
//    setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ProfileMembre.fxml")));
//            
//            // TODO
//        } catch (IOException ex) {
//            Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE, null, ex);
//        } 
    }

    @FXML
    public void closeApp() {

        // get a handle to the stage
        Stage stage = (Stage) btnclose.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void DemandeRecomanadation(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/DemandeRecommandation.fxml"));
        Parent root = loader.load();
        categorie.getScene().setRoot(root);
        //    Notifications.create().darkStyle().title("xxx").text("fff").showConfirm();

    }

    @FXML
    private void AjouterParataeExperience(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/PartageExperience.fxml"));
        Parent root = loader.load();
        btnclose.getScene().setRoot(root);

    }

//    private void MyProfile(MouseEvent event) throws IOException  {
//   
//             setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ProfileMembre.fxml")));
//         
//         
//          
// 
//    }
    private void setNode(Node node) {
        AdneneGadourAnchorPane.getChildren().clear();
        AdneneGadourAnchorPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

//    private void processLogout(MouseEvent event) {
//        
//        
//         LoginController.getInstance().userLogout();
//    }
//    
    @FXML
    private void categorie(MouseEvent event) {
    }

    @FXML
    private void partenaire(MouseEvent event) {
    }

    @FXML
    private void profile(MouseEvent event) throws IOException {
        setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ProfileMembre.fxml")));
    }

    @FXML
    private void demandeCadeaux(MouseEvent event) throws IOException {
        setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ListeDesDemandeCadeauxMembre.fxml")));
    }

    @FXML
    private void reclamation(MouseEvent event) throws IOException {
        setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AccueilReclamation.fxml")));
    }

}
