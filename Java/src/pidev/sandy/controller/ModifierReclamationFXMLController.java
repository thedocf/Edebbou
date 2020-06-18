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
import java.util.Calendar;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
import pidev.sandy.test.MenuAdmin1;

/**
 * FXML Controller class
 *
 * @author X
 */
public class ModifierReclamationFXMLController implements Initializable {

    ObservableList<String> listRoles = FXCollections.observableArrayList("Bon Plan", "Compte", "Deal", "Autre");
    @FXML
    private ComboBox<String> typeReclamationCBX;
   
    File selectedFile;
    int selectedBonplanID;
    String erreur;
    Notifications n;
    int selectedCompteID;
    java.sql.Timestamp timestamp = null;
    private String path;
    @FXML
    private ImageView nomCheckMark;
    @FXML
    private ImageView telCheckMark;
    @FXML
    private ImageView prenomCheckMark;
    @FXML
    private ImageView emailCheckMark;
    @FXML
    private ImageView dateCheckMark;
    @FXML
    private JFXButton image;
    @FXML
    private TextField NomTXFLD;
    @FXML
    private TextField PrenomTXFLD;
    @FXML
    private TextField TelTXFLD;
    @FXML
    private TextField EmailTXFLD;
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
    private JFXButton modifier;
    @FXML
    private ImageView checkMarkImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         MyServices myServices = new MyServices();
        User UserConneter = myServices.chercherUtilisateurByid(MenuAdmin1.Id_user_connecte);
        System.out.println("ID du user connecté interface ajouter reclamation =" + MenuAdmin1.Id_user_connecte);

        if (MenuAdmin1.Id_user_connecte != 0) {
            NomTXFLD.setText(UserConneter.getNom());
            PrenomTXFLD.setText(UserConneter.getPrenom());
            EmailTXFLD.setText(UserConneter.getEmail());
            TelTXFLD.setText(UserConneter.getPhone());
            
            NomTXFLD.setDisable(true);
            PrenomTXFLD.setDisable(true);
            EmailTXFLD.setDisable(true);
            TelTXFLD.setDisable(true);
        }
        // TODO
        NomTXFLD.setText(AfficherReclamationController.selectionedReclamation.getNom());
        PrenomTXFLD.setText(AfficherReclamationController.selectionedReclamation.getPrenom());
        EmailTXFLD.setText(AfficherReclamationController.selectionedReclamation.getEmail());
        TelTXFLD.setText(Integer.toString(AfficherReclamationController.selectionedReclamation.getNumero_mobile()));
        SujetTXFLD.setText(AfficherReclamationController.selectionedReclamation.getObjet());
        DescriptionTXFLD.setText(AfficherReclamationController.selectionedReclamation.getDescription());
        screenshotView.setImage(new Image(AfficherReclamationController.selectionedReclamation.getScreenshot()));
        screenshotView.setFitHeight(150);
        screenshotView.setFitWidth(250);
        path=new File(AfficherReclamationController.selectionedReclamation.getScreenshot()).getName();
        image.setText(path);
//         Time temps = java.sql.Time.valueOf(AfficherReclamationController.selectionedReclamation.getDate_disponibilite());
//        Date date = java.sql.Date.valueOf(DateDispoTimePicker.getValue());
//System.out.println(AfficherReclamationController.selectionedReclamation.getDate_disponibilite());
if (AfficherReclamationController.selectionedReclamation.getDate_disponibilite()!=null)
{DateDispoTimePicker.setValue(AfficherReclamationController.selectionedReclamation.getDate_disponibilite().toLocalDate());
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        String t= localDateFormat.format(AfficherReclamationController.selectionedReclamation.getDate_disponibilite());
        System.out.println(AfficherReclamationController.selectionedReclamation.getDate_disponibilite());
//        TempsDispoTimePicker.setValue(localDateFormat.format(AfficherReclamationController.selectionedReclamation.getDate_disponibilite().toString()));
//        TempsDispoTimePicker.setValue(java.sql.Time.valueOf(AfficherReclamationController.selectionedReclamation.getDate_disponibilite().toString()).toLocalTime());

        String time = localDateFormat.format(AfficherReclamationController.selectionedReclamation.getDate_disponibilite());
    System.out.println(time);
            TempsDispoTimePicker.setValue(java.sql.Time.valueOf(time).toLocalTime());

//        DateDispoTimePicker.setValue(LocalDate.MAX);   
}
        
        System.out.println(AfficherReclamationController.selectionedReclamation.getScreenshot());

        typeReclamationCBX.setPromptText("Veuillez selectionner un type");
        typeReclamationCBX.setItems(listRoles);

        DescriptionTXFLD.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        // Dropping over surface
        DescriptionTXFLD.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    path = null;
                    for (File file : db.getFiles()) {
                        path = file.getName();
                        selectedFile = new File(file.getAbsolutePath());
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());//file.getAbsolutePath()="C:\Users\X\Desktop\ScreenShot.6.png"
                        screenshotView.setImage(new Image("file:" + file.getAbsolutePath()));
                        screenshotView.setFitHeight(150);
                        screenshotView.setFitWidth(250);
                        image.setText(path);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

    }

    @FXML
    private void selectionReclamation(ActionEvent event) {
        if (typeReclamationCBX.getValue().toString() == "Bon Plan") {
            Stage window = new Stage();

            //Block events to other windows
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Veuillez choisir un bon plan");
            window.setMinWidth(250);

            TableColumn<BonplanAdnene, Integer> libelle = new TableColumn<>("ID");
            libelle.setMinWidth(100);
            libelle.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<BonplanAdnene, String> prix = new TableColumn<>("Libelle");
            prix.setMinWidth(100);
            prix.setCellValueFactory(new PropertyValueFactory<>("libelle"));

            ServiceBonplanAdnene produitService = new ServiceBonplanAdnene();
            ArrayList arrayList = (ArrayList) produitService.listerBonplan();
            ObservableList observableList = FXCollections.observableArrayList(arrayList);

            TableView<BonplanAdnene> listeBonplan = new TableView<>();
            listeBonplan.setEditable(true);
            listeBonplan.setItems(observableList);
            listeBonplan.getColumns().addAll(libelle, prix);

            listeBonplan.setOnMouseClicked((MouseEvent event2)
                    -> {
                if (event2.getClickCount() >= 2) {
                    if (listeBonplan.getSelectionModel().getSelectedItem() != null) {
                        selectedBonplanID = listeBonplan.getSelectionModel().getSelectedItem().getId();
//        nameTextField.setText(selectedPerson.getName());
//        addressTextField.setText(selectedPerson.getAddress());
                        System.out.println("SeledtecBonPlan ID="+selectedBonplanID);
                        window.close();

                        Notifications n = Notifications.create()
                                .title("Succès")
                                .text("Bon Plan selectionné avec succès")
                                .graphic(null)
                                .position(Pos.TOP_CENTER)
                                .hideAfter(Duration.seconds(3));
                        n.showInformation();
                        checkMarkImage.setImage(new Image("Images/checkMark.png"));
                        //typeReclamationCBX.setValue(listeBonplan.getSelectionModel().getSelectedItem().getLibelle().toString());

                    }
                }
            });

//            Label label = new Label();
//            label.setText("aaaaa");
            Button closeButton = new Button("Fermer");
            closeButton.setOnAction(e -> window.close());

            VBox layout = new VBox(10);
            layout.getChildren().addAll(listeBonplan, closeButton);
            layout.setAlignment(Pos.CENTER);

            //Display window and wait for it to be closed before returning
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();

        }
        //*****************************************************
        if (typeReclamationCBX.getValue().toString() == "Compte") {
            Stage window = new Stage();

            //Block events to other windows
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Veuillez choisir un compte");
            window.setMinWidth(250);

            TableColumn<User, Integer> id = new TableColumn<>("ID");
            id.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<User, String> username = new TableColumn<>("UserName");
            username.setCellValueFactory(new PropertyValueFactory<>("username"));

            TableColumn<User, String> nom = new TableColumn<>("Nom");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));

            TableColumn<User, String> prenom = new TableColumn<>("Nom");
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

            MyServices produitService = new MyServices();
            ArrayList arrayList = (ArrayList) produitService.afficherlisteUtilisateurs();
            ObservableList observableList = FXCollections.observableArrayList(arrayList);

            TableView<User> listeCompte = new TableView<>();
            listeCompte.setEditable(true);
            listeCompte.setItems(observableList);
            listeCompte.getColumns().addAll(id, username, nom, prenom);

            listeCompte.setOnMouseClicked((MouseEvent event3)
                    -> {
                if (event3.getClickCount() >= 2) {
                    if (listeCompte.getSelectionModel().getSelectedItem() != null) {
                        selectedCompteID = listeCompte.getSelectionModel().getSelectedItem().getId();
//        nameTextField.setText(selectedPerson.getName());
//        addressTextField.setText(selectedPerson.getAddress());
                        System.out.println("id du compte selectioné=" + selectedCompteID);
                        window.close();

                        Notifications n = Notifications.create()
                                .title("Succès")
                                .text("Compte selectionné avec succès")
                                .graphic(null)
                                .position(Pos.TOP_CENTER)
                                .hideAfter(Duration.seconds(3));
                        n.showInformation();
                        checkMarkImage.setImage(new Image("Images/checkMark.png"));
                        //typeReclamationCBX.setValue(listeBonplan.getSelectionModel().getSelectedItem().getLibelle().toString());

                    }
                }
            });

//            Label label = new Label();
//            label.setText("aaaaa");
            Button closeButton = new Button("Fermer");
            closeButton.setOnAction(e -> window.close());

            VBox layout = new VBox(10);
            layout.getChildren().addAll(listeCompte, closeButton);
            layout.setAlignment(Pos.CENTER);

            //Display window and wait for it to be closed before returning
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();

        }

    }

    @FXML
    private void modififerReclamationGUI(ActionEvent event) {
        //   java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0");
               

        java.sql.Timestamp timestamp = null;
//        System.out.println(DateDispoTimePicker.getValue());
//        System.out.println(TempsDispoTimePicker.getValue());

      Reclamation r;

        if (testSaisieMembre()&&MenuAdmin1.Id_user_connecte != 0  || MenuAdmin1.Id_user_connecte ==0 &&testSaisie()) //si un memebre ne test pas les donnes , sinon fait un test de siaisie
        {
        if ((DateDispoTimePicker.getValue() != null) && (TempsDispoTimePicker.getValue() != null)) {
            timestamp = java.sql.Timestamp.valueOf(DateDispoTimePicker.getValue() + " " + TempsDispoTimePicker.getValue() + ":00");
            System.out.println(timestamp);
            // Date date_creation, Date date_traitement, Date date_disponibilite
            r = new Reclamation(0, selectedBonplanID, 0, 0, Integer.parseInt(TelTXFLD.getText()),0, new Date(2010, 1, 1),null,new Date(2010, 1, 1), DescriptionTXFLD.getText(), SujetTXFLD.getText(), "En attente", NomTXFLD.getText(), PrenomTXFLD.getText(), EmailTXFLD.getText(), path);

        }

//        TempsDispoTimePicker
//        DateDispoTimePicker
//        Reclamation r = new Reclamation(0,selectedBonplanID,0,0,Integer.parseInt(TelTXFLD.getText()),new Date(2010,1,1),DescriptionTXFLD.getText(),SujetTXFLD.getText(),"En attente",NomTXFLD.getText(),PrenomTXFLD.getText(),EmailTXFLD.getText(),path);
            r = new Reclamation(0, selectedBonplanID, 0, 0, Integer.parseInt(TelTXFLD.getText()),0, new Date(2010, 1, 1),null,new Date(2010, 1, 1), DescriptionTXFLD.getText(), SujetTXFLD.getText(), "En attente", NomTXFLD.getText(), PrenomTXFLD.getText(), EmailTXFLD.getText(), path);
        ServicesReclamation ms = new ServicesReclamation();

        
            ms.modifierReclamation2(r, AfficherReclamationController.selectionedReclamation.getId(),timestamp);
            System.out.println("Midifié avec succes");
            Notifications n = Notifications.create()
                    .title("Succes")
                    .text("Reclamation Midifié avec succes")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(3));
            n.showInformation();
            
            
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

    }

   

    @FXML
    private void image(ActionEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
//                path = selectedFile.toURI().toURL().toExternalForm();
            screenshotView.setImage(new Image(selectedFile.toURI().toURL().toString()));
            screenshotView.setFitHeight(150);
            screenshotView.setFitWidth(250);
            image.setText(path);

        }

    }

    
    
    
    
    
     private Boolean testSaisie() {
        erreur = "";
        if (!testMail()) {
            erreur = erreur + ("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");
        }
        if (!testTel()) {
            erreur = erreur + ("Telephone doit avoir 8 chiffres et ne doit pas contenir des caracteres \n");
        }
        if (!testDate()) {
            erreur = erreur + ("Veuillez verifier que vous avez choisi une date superieur a la date courante \n");
        }
        
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testPrenom()) {
            erreur = erreur + ("Veuillez verifier votre Prenom: seulement des caractères et de nombre >= 3");
        }
        

        
if (!testMail() || !testTel() || !testDate()  ||!testNom() || !testPrenom())
{
        n = Notifications.create()
                .title("Erreur de format")
                .text(erreur)
                .graphic(null)
                .position(Pos.TOP_CENTER)
                .hideAfter(Duration.seconds(6));
        n.showInformation();}

        return testMail() && testTel() && testDate() &&testNom() && testPrenom();
    }
    
    
    
    
    
    private Boolean testSaisieMembre() {
        erreur = "";
    
        if (!testDate()) {
            erreur = erreur + ("Veuillez verifier que vous avez choisi une date superieur a la date courante \n");
        }
       

if ( !testDate() )
{
        n = Notifications.create()
                .title("Erreur de format")
                .text(erreur)
                .graphic(null)
                .position(Pos.TOP_CENTER)
                .hideAfter(Duration.seconds(6));
        n.showInformation();}

        return testDate();
    }

    
    
    
    
    @FXML
    private Boolean testMail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (EmailTXFLD.getText() == null) {
            return false;
        }

        if (pat.matcher(EmailTXFLD.getText()).matches() == false) {
            emailCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            emailCheckMark.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }

//   

    @FXML
    private Boolean testTel() {
        if (TelTXFLD.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < TelTXFLD.getText().trim().length(); i++) {
                char ch = TelTXFLD.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    nbChar++;
                }
            }

            if (nbChar == 0) {
                telCheckMark.setImage(new Image("Images/checkMark.png"));
                return true;
            } else {
                telCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                return false;

            }
        } else if (TelTXFLD.getText().trim().length() != 8) {
//            erreur = erreur + ("Il faut saisir 8 chiffres dans le numéro de telephone\n");
            telCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
            return false;
        }

        return true;

    }


    @FXML
    private Boolean testDate() {
        java.sql.Timestamp today_date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        if (TempsDispoTimePicker.getValue() != null && DateDispoTimePicker.getValue() != null) {
            timestamp = java.sql.Timestamp.valueOf(DateDispoTimePicker.getValue() + " " + TempsDispoTimePicker.getValue() + ":00");
            if (timestamp.compareTo(today_date) > 0) {
                dateCheckMark.setImage(new Image("Images/checkMark.png"));
                return true;
            } else {
                dateCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
            }
            return false;
        } else if (TempsDispoTimePicker.getValue() == null && DateDispoTimePicker.getValue() == null) {
            return true;
        } else if (TempsDispoTimePicker.getValue() != null && DateDispoTimePicker.getValue() == null) {
            dateCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
        } else if (TempsDispoTimePicker.getValue() == null && DateDispoTimePicker.getValue() != null) {
            dateCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
        }
        return false;

    }
    
    
    @FXML
    private Boolean testNom() {
        int nbNonChar = 0;
            for (int i = 1; i < NomTXFLD.getText().trim().length(); i++) {
                char ch = NomTXFLD.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && NomTXFLD.getText().trim().length() >=3) {
                nomCheckMark.setImage(new Image("Images/checkMark.png"));
                return true;
            } else {
                nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                return false;

            }

    }
    
    
    @FXML
    private Boolean testPrenom() {
               int nbNonChar = 0;
            for (int i = 1; i < PrenomTXFLD.getText().trim().length(); i++) {
                char ch = PrenomTXFLD.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && PrenomTXFLD.getText().trim().length() >=3) {
                prenomCheckMark.setImage(new Image("Images/checkMark.png"));
                return true;
            } else {
                prenomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                return false;

            }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
