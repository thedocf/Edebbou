/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;
import pidev.sandy.entites.BonplanAdnene;
import pidev.sandy.entites.Reclamation;
import pidev.sandy.services.ServicesReclamation;
import pidev.sandy.services.ServiceBonplanAdnene;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import pidev.sandy.entites.Mailing;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
import pidev.sandy.test.MenuAdmin1;

/**
 * FXML Controller class
 *
 * @author X
 */
public class AfficherReclamationUniqueController implements Initializable {

    ObservableList<String> listRoles = FXCollections.observableArrayList("Bon Plan", "Compte", "Deal", "Autre");
    private ComboBox<String> typeReclamationCBX;
    private JFXButton image;
    @FXML
    private TextField NomTXFLD;
    @FXML
    private TextField PrenomTXFLD;
    @FXML
    private TextField EmailTXFLD;
    @FXML
    private TextField TelTXFLD;
    @FXML
    private JFXTimePicker TempsDispoTimePicker;
    @FXML
    private TextField SujetTXFLD;
    @FXML
    private JFXTextArea DescriptionTXFLD;
    @FXML
    private JFXDatePicker DateDispoTimePicker;
    @FXML
    private ImageView screenshotView;
    @FXML
    private ImageView checkMarkImage;
    File selectedFile;
    int selectedBonplanID;
    private String path;
    @FXML
    private TextArea bonPlanText;
    @FXML
    private Label nomImageLabel;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton repondreBTN;
    @FXML
    private ComboBox<String> statusComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
                MyServices myServices = new MyServices();
        User UserConneter = myServices.chercherUtilisateurByid(MenuAdmin1.Id_user_connecte);
        System.out.println("ID du user connecté interface ajouter reclamation =" + MenuAdmin1.Id_user_connecte);

        if (UserConneter!=null&&!UserConneter.getRoles().contains("ROLE_ADMIN")) {
           
            
            statusComboBox.setDisable(true);
            repondreBTN.setDisable(true);
            repondreBTN.setVisible(false);
            ajouter.setDisable(true);
            ajouter.setVisible(false);
            
        }
        
       
        
        if(AfficherReclamationController.selectionedReclamation.getRef_bonplan()!=0) 
        { 
           ServiceBonplanAdnene sbn= new ServiceBonplanAdnene();
        BonplanAdnene b =sbn.rechercheBonPlanById(AfficherReclamationController.selectionedReclamation.getRef_bonplan());
        if (UserConneter==null||UserConneter.getRoles().contains("ROLE_ADMIN"))
            bonPlanText.setText("BonPlan:"+"id="+b.getId()+" Nom="+b.getLibelle()+"\nDescription="+b.getDesciption()+"\nAddresse="+b.getAddresse());
        else
            bonPlanText.setText("BonPlan:"+" Nom="+b.getLibelle()+" Description="+b.getDesciption()+" Addresse="+b.getAddresse());
        }
        
        
        
        else  if(AfficherReclamationController.selectionedReclamation.getRef_compte()!=0)
             {
                  MyServices sbn= new MyServices();
        User b =sbn.chercherUtilisateurByid(AfficherReclamationController.selectionedReclamation.getRef_compte());
        if (UserConneter==null||UserConneter.getRoles().contains("ROLE_ADMIN"))
            bonPlanText.setText("Compte:"+"id="+b.getId()+" Nom="+b.getNom()+"\nPrenom="+b.getPrenom()+"\nUsername="+b.getUsername());
        else
            bonPlanText.setText("BonPlan:"+" Nom="+b.getNom()+"\nPrenom="+b.getPrenom()+"\nUsername="+b.getUsername());
        }
        
        
        else  if(AfficherReclamationController.selectionedReclamation.getRef_deal()!=0)
             {}
        else  if(AfficherReclamationController.selectionedReclamation.getRef_mise()!=0)
             {}
        
        
        
        NomTXFLD.setText(AfficherReclamationController.selectionedReclamation.getNom());
        PrenomTXFLD.setText(AfficherReclamationController.selectionedReclamation.getPrenom());
        EmailTXFLD.setText(AfficherReclamationController.selectionedReclamation.getEmail());
        TelTXFLD.setText(Integer.toString(AfficherReclamationController.selectionedReclamation.getNumero_mobile()));
        SujetTXFLD.setText(AfficherReclamationController.selectionedReclamation.getObjet());
        DescriptionTXFLD.setText(AfficherReclamationController.selectionedReclamation.getDescription());
        screenshotView.setImage(new Image(AfficherReclamationController.selectionedReclamation.getScreenshot()));
        screenshotView.setFitHeight(175);
        screenshotView.setFitWidth(320);
        path = new File(AfficherReclamationController.selectionedReclamation.getScreenshot()).getName();
        nomImageLabel.setText(path);

        statusComboBox.getItems().setAll("En attente", "En Traitement", "Traité");
//        statusComboBox.setValue(AfficherReclamationController.selectionedReclamation.getStatus());
        if ((UserConneter==null&&AfficherReclamationController.selectionedReclamation.getStatus().equals("En attente"))||(UserConneter!=null&&AfficherReclamationController.selectionedReclamation.getStatus().equals("En attente")&&UserConneter.getRoles().contains("ROLE_ADMIN"))) {
            statusComboBox.setValue("En Traitement");
            modififerReclamationGUI();
        } else {
            statusComboBox.setValue(AfficherReclamationController.selectionedReclamation.getStatus());
        }

        if (AfficherReclamationController.selectionedReclamation.getDate_disponibilite() != null) {
            DateDispoTimePicker.setValue(AfficherReclamationController.selectionedReclamation.getDate_disponibilite().toLocalDate());
            SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
            String t = localDateFormat.format(AfficherReclamationController.selectionedReclamation.getDate_disponibilite());
            System.out.println(AfficherReclamationController.selectionedReclamation.getDate_disponibilite());

            String time = localDateFormat.format(AfficherReclamationController.selectionedReclamation.getDate_disponibilite());
            System.out.println(time);
            TempsDispoTimePicker.setValue(java.sql.Time.valueOf(time).toLocalTime());

        }

        screenshotView.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 1) {
                //  if (AfficherReclamationController.selectionedReclamation.getScreenshot() != null) 
                {
                    Stage window = new Stage();
                    window.setMinWidth(250);
                    ImageView imagevPOPUP = new ImageView(new Image(AfficherReclamationController.selectionedReclamation.getScreenshot()));
                    imagevPOPUP.setFitHeight(576);
                    imagevPOPUP.setFitWidth(1024);

                    VBox layout = new VBox(10);
                    layout.getChildren().addAll(imagevPOPUP);
                    layout.setAlignment(Pos.CENTER);

                    Scene scene = new Scene(layout);
                    window.setScene(scene);
                    window.show();

                }
            }

        });
//AfficherReclamationController a=new AfficherReclamationController();
//        a.TableViewReclamation.refresh();
    }

    @FXML
    private void modififerReclamationGUI(ActionEvent event) {
        java.sql.Timestamp timestamp = null;

        if ((DateDispoTimePicker.getValue() != null) && (TempsDispoTimePicker.getValue() != null) && statusComboBox.getValue().equals("Traité")) {
            timestamp = java.sql.Timestamp.valueOf(DateDispoTimePicker.getValue() + " " + TempsDispoTimePicker.getValue() + ":00");
            Reclamation r = new Reclamation(0, selectedBonplanID, 0, 0, 2222, 0, new Date(2010, 1, 1), null, new Date(2010, 1, 1), DescriptionTXFLD.getText(), SujetTXFLD.getText(), statusComboBox.getValue(), NomTXFLD.getText(), PrenomTXFLD.getText(), EmailTXFLD.getText(), path);

        }
        Reclamation r = new Reclamation(0, selectedBonplanID, 0, 0, 2222, 0, new Date(2010, 1, 1), null, new Date(2010, 1, 1), DescriptionTXFLD.getText(), SujetTXFLD.getText(), statusComboBox.getValue(), NomTXFLD.getText(), PrenomTXFLD.getText(), EmailTXFLD.getText(), path);
        ServicesReclamation ms = new ServicesReclamation();
        
     
            
    

        ms.modifierReclamationStatus(r, AfficherReclamationController.selectionedReclamation.getId(), timestamp);
        
            Notifications n = Notifications.create()
                    .title("Succes")
                    .text("Reclamation traité avec succes")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(3));
            n.showInformation();
            
            
                  if (statusComboBox.getValue().equals("Traité")) {
            String to = AfficherReclamationController.selectionedReclamation.getEmail().trim();
            String subject = "Reponse Reclamation";
            String message = "Bonjour Mr/Mme " + AfficherReclamationController.selectionedReclamation.getNom() + " " + AfficherReclamationController.selectionedReclamation.getPrenom() + " Merci de nous avoir contactés, votre reclamation a été traitée.";
            String usermail = "alaa.guissouma@esprit.tn";
            String passmail = "Skotinka00_";
            Mailing.send(to, subject, message, usermail, passmail);
        }
        
                 Notifications b = Notifications.create()
                    .title("Succes")
                    .text("Client notifié avec succes")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(3));
            b.showInformation();
        System.out.println("Midifié avec succes");
        
        
        
        if (selectedFile != null) {
            try {
                //System.out.println(selectedFile.toString());
                File source = new File(selectedFile.toString());
                File dest = new File("C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images");

                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void modififerReclamationGUI() {
        java.sql.Timestamp timestamp = null;
        System.out.println(DateDispoTimePicker.getValue());
        System.out.println(TempsDispoTimePicker.getValue());
        if ((DateDispoTimePicker.getValue() != null) && (TempsDispoTimePicker.getValue() != null)) {
            timestamp = java.sql.Timestamp.valueOf(DateDispoTimePicker.getValue() + " " + TempsDispoTimePicker.getValue() + ":00");
            System.out.println(timestamp);
            // Date date_creation, Date date_traitement, Date date_disponibilite
            Reclamation r = new Reclamation(0, selectedBonplanID, 0, 0, 2222, 0, new Date(2010, 1, 1), null, new Date(2010, 1, 1), DescriptionTXFLD.getText(), SujetTXFLD.getText(), statusComboBox.getValue(), NomTXFLD.getText(), PrenomTXFLD.getText(), EmailTXFLD.getText(), path);

        }
        Reclamation r = new Reclamation(0, selectedBonplanID, 0, 0, 2222, 0, new Date(2010, 1, 1), null, new Date(2010, 1, 1), DescriptionTXFLD.getText(), SujetTXFLD.getText(), statusComboBox.getValue(), NomTXFLD.getText(), PrenomTXFLD.getText(), EmailTXFLD.getText(), path);
        ServicesReclamation ms = new ServicesReclamation();

        ms.modifierReclamation2(r, AfficherReclamationController.selectionedReclamation.getId(), timestamp);
       
            
        if (statusComboBox.getValue().equals("Traité")) {
            String to = AfficherReclamationController.selectionedReclamation.getEmail().trim();
            String subject = "Reponse Reclamation";
            String message = "Bonjour Mr/Mme " + AfficherReclamationController.selectionedReclamation.getNom() + " " + AfficherReclamationController.selectionedReclamation.getPrenom() + " Merci de nous avoir contactés, votre reclamation a été traitée.";
            String usermail = "alaa.guissouma@esprit.tn";
            String passmail = "Skotinka00_";
            Mailing.send(to, subject, message, usermail, passmail);
        }

        System.out.println("Midifié avec succes");
        if (selectedFile != null) {
            try {
                //System.out.println(selectedFile.toString());
                File source = new File(selectedFile.toString());
                File dest = new File("C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images");

                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void RepondreReclamation(ActionEvent event) {
        Stage window = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/RepondreReclamation.fxml"));
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherReclamationUniqueController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
