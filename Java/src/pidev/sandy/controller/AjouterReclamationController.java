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
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.controlsfx.control.Notifications;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
import pidev.sandy.test.MenuAdmin1;

/**
 * FXML Controller class
 *
 * @author X
 */
public class AjouterReclamationController implements Initializable {

    private String path;

    @FXML
    private ComboBox<String> typeReclamationCBX;
    ObservableList<String> listRoles = FXCollections.observableArrayList("Produits", "Compte", "Assitance", "Autre");
    @FXML
    private JFXButton ajouter;
    @FXML
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
    private TextField SujetTXFLD;
    @FXML
    private JFXTextArea DescriptionTXFLD;
    int selectedBonplanID;
    int selectedCompteID;
    @FXML
    private JFXTimePicker TempsDispoTimePicker;
    @FXML
    private JFXDatePicker DateDispoTimePicker;
    @FXML
    private ImageView screenshotView;
    File selectedFile;
    @FXML
    private ImageView checkMarkImage;
    Notifications n;
    String erreur;
    @FXML
    private ImageView nomCheckMark;
    @FXML
    private ImageView telCheckMark;
    @FXML
    private ImageView prenomCheckMark;
    @FXML
    private ImageView emailCheckMark;
    @FXML
//    private WebView webView;
//    WebEngine webEngine;
    java.sql.Timestamp timestamp = null;
    @FXML
    private ImageView dateCheckMark;
    @FXML
    private ImageView recaptchaCheckMark;
    int etatrecaptcha=0;
     Stage window; 
     WebView webView2;
     WebEngine webEngine ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         window = new Stage();
         webView2 = new WebView();
         webEngine = webView2.getEngine();
          window.setOnCloseRequest(e -> {
             etatrecaptcha=1;
            recaptchaCheckMark.setImage(new Image("Images/checkMark.png"));
                
            System.out.println("etat recaptcha="+etatrecaptcha);
        });
          window.initModality(Modality.APPLICATION_MODAL);
          window.setMinWidth(250);
          
        // TODO
//        //API RECAPTCHA V2 GOOGLE   
//        webEngine = webView.getEngine();
//        webEngine.setUserAgent("use required / intended UA string");
////         File f = new File("C:\\Users\\X\\Desktop\\test.html");
////    try {
////        webView.getEngine().load(f.toURI().toURL().toString());
////    } catch (MalformedURLException ex) {
////    }
//        webEngine.load("http://localhost/TunisiaBonPlan2-integration/web/app_dev.php/clientelle/recaptchaJAVA.html");

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

        typeReclamationCBX.setPromptText("Veuillez selectionner un type");
        typeReclamationCBX.setItems(listRoles);

//        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0");
//        System.out.println(DateDispoTimePicker.getValue() + " " + TempsDispoTimePicker.getValue());
        screenshotView.setOnDragOver(new EventHandler<DragEvent>() {
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
        screenshotView.setOnDragDropped(new EventHandler<DragEvent>() {
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
//                        screenshotView.setFitHeight(150);
//                        screenshotView.setFitWidth(250);
                        image.setText(path);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        screenshotView.setImage(new Image("file:C:\\Users\\X\\Documents\\NetBeansProjects\\Bon_paln\\src\\Images\\drag-drop.gif"));
    }

    @FXML
    private void selectionReclamation(ActionEvent event) {
        if (typeReclamationCBX.getValue().toString() == "Produits") {
            Stage window = new Stage();

            //Block events to other windows
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Type de reclamation");
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
                        System.out.println("id du Produit selectioné=" + selectedBonplanID);
                        window.close();

                        Notifications n = Notifications.create()
                                .title("Succès")
                                .text("Produit selectionné avec succès")
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
        //****************************************************************************************************
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

    @FXML
    private void ajouterReclamationGUI(ActionEvent event) {
//        System.out.println("page title=" + webEngine.getTitle());
//   java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0");
        System.out.println(DateDispoTimePicker.getValue());
        System.out.println(TempsDispoTimePicker.getValue());
        //        Time a = java.sql.Time.valueOf(TempsDispoTimePicker.getValue());
        Reclamation r;

        if (testSaisieMembre()&&MenuAdmin1.Id_user_connecte != 0  || MenuAdmin1.Id_user_connecte ==0 &&testSaisie()) //si un memebre ne test pas les donnes , sinon fait un test de siaisie
        {

            if ((DateDispoTimePicker.getValue() != null) && (TempsDispoTimePicker.getValue() != null)) {
                timestamp = java.sql.Timestamp.valueOf(DateDispoTimePicker.getValue() + " " + TempsDispoTimePicker.getValue() + ":00");
                System.out.println(timestamp);
//            Reclamation r = new Reclamation(0, selectedBonplanID, 0, 0, 2222, new Date(2010, 1, 1), DescriptionTXFLD.getText(), SujetTXFLD.getText(), "En attente", NomTXFLD.getText(), PrenomTXFLD.getText(), EmailTXFLD.getText(), path);
                if (MenuAdmin1.Id_user_connecte != 0) {
                    r = new Reclamation(MenuAdmin1.Id_user_connecte, 0, selectedBonplanID, 0, 0, Integer.parseInt(TelTXFLD.getText()), DescriptionTXFLD.getText(), SujetTXFLD.getText(), "En attente", NomTXFLD.getText(), PrenomTXFLD.getText(), EmailTXFLD.getText(), path);
                } else {
                    r = new Reclamation(0, selectedBonplanID, 0, 0, Integer.parseInt(TelTXFLD.getText()), DescriptionTXFLD.getText(), SujetTXFLD.getText(), "En attente", NomTXFLD.getText(), PrenomTXFLD.getText(), EmailTXFLD.getText(), path);
                }

            }
//        DateDispoTimePicker
//        Reclamation r = new Reclamation(0,selectedBonplanID,0,0,Integer.parseInt(TelTXFLD.getText()),new Date(2010,1,1),DescriptionTXFLD.getText(),SujetTXFLD.getText(),"En attente",NomTXFLD.getText(),PrenomTXFLD.getText(),EmailTXFLD.getText(),path);
            if (MenuAdmin1.Id_user_connecte != 0) {
                r = new Reclamation(MenuAdmin1.Id_user_connecte, 0, selectedBonplanID, 0, 0, Integer.parseInt(TelTXFLD.getText()), DescriptionTXFLD.getText(), SujetTXFLD.getText(), "En attente", NomTXFLD.getText(), PrenomTXFLD.getText(), EmailTXFLD.getText(), path);
            } else {
                r = new Reclamation(0, selectedBonplanID, 0, 0, Integer.parseInt(TelTXFLD.getText()), DescriptionTXFLD.getText(), SujetTXFLD.getText(), "En attente", NomTXFLD.getText(), PrenomTXFLD.getText(), EmailTXFLD.getText(), path);
            }
            ServicesReclamation ms = new ServicesReclamation();

            ms.ajouterReclamation3(r, timestamp);
            System.out.println("ajouté avec succes");
            n = Notifications.create()
                    .title("Succes")
                    .text("Reclamation envoyé avec succes")
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
        if (etatrecaptcha==0) {
            erreur = erreur + ("Veuillez valider la recaptcha\n");
            recaptchaCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
        }
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testPrenom()) {
            erreur = erreur + ("Veuillez verifier votre Prenom: seulement des caractères et de nombre >= 3");
        }
        

        
if (!testMail() || !testTel() || !testDate() || etatrecaptcha==0 ||!testNom() || !testPrenom())
{
        n = Notifications.create()
                .title("Erreur de format")
                .text(erreur)
                .graphic(null)
                .position(Pos.TOP_CENTER)
                .hideAfter(Duration.seconds(6));
        n.showInformation();}

        return testMail() && testTel() && testDate() && etatrecaptcha==1 &&testNom() && testPrenom();
    }
    
    
    
    
    
    private Boolean testSaisieMembre() {
        erreur = "";
    
        if (!testDate()) {
            erreur = erreur + ("Veuillez verifier que vous avez choisi une date superieur a la date courante \n");
        }
        if (etatrecaptcha==0) {
            erreur = erreur + ("Veuillez valider la recaptcha\n");
            recaptchaCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
        }

if ( !testDate() || etatrecaptcha==0 )
{
        n = Notifications.create()
                .title("Erreur de format")
                .text(erreur)
                .graphic(null)
                .position(Pos.TOP_CENTER)
                .hideAfter(Duration.seconds(6));
        n.showInformation();}

        return testDate() && etatrecaptcha==1;
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

    @FXML
    private void recaptcha(MouseEvent event) {
                 

            //Block events to other windows
            
//            window.setTitle("Veuillez choisir un compte");
        webView2.setPrefSize(400,500);
        webEngine.setUserAgent("use required / intended UA string");
        webEngine.load("https://patrickhlauke.github.io/recaptcha/");

            
        Button closeButton = new Button("Fermer");
            closeButton.setOnAction(e -> window.close());

            VBox layout = new VBox(10);
            layout.getChildren().addAll(webView2);
            layout.setAlignment(Pos.CENTER);

            //Display window and wait for it to be closed before returning
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
            
            
       
            
            
    }

}
