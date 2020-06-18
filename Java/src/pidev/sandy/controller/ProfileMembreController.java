/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import jdk.nashorn.internal.parser.TokenType;
import org.apache.commons.io.FileUtils;
import pidev.sandy.entites.Cadeaux;
import pidev.sandy.entites.Compte;
import pidev.sandy.entites.Mailing;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
import pidev.sandy.services.Password;
import pidev.sandy.services.ServiceNotification;
import pidev.sandy.services.ServiceRandomMailConfirmation;
import pidev.sandy.services.serviceCryptage;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class ProfileMembreController implements Initializable {

    @FXML
    private AnchorPane Anchprincipal;
    @FXML
    private Pane PaneinfoAndResearch;
    @FXML
    private Pane PaneInfoMembre;
    @FXML
    private JFXButton ModifierProfil;
    @FXML
    private Label NomPrenom;
    @FXML
    private JFXTextField ChercherCadeaux;
  
    @FXML
    private JFXButton showInformationPersonnelle;
    @FXML
    private JFXButton showInformationCompte;
    
    @FXML
    private AnchorPane AnchoInfoUser;
    @FXML
    private Label labelNom;
    @FXML
    private Label labelPrenom;
    @FXML
    private Label labelUsername;
    @FXML
    private Label labelEmailcontrole;
    @FXML
    private Label labelPhone;
    @FXML
    private Label labelRole;
    @FXML
    private Label labelPointMerci;
    @FXML
    private Label labelCadeau;

    /**
     * Initializes the controller class.
     */
    //liste des roles qui ont va le remplir dans ini
    List<String> listeroles = new ArrayList<String>();
    ObservableList<String> observableListroles;

    //liste des genres qui ont va le remplir dans ini
    List<String> listegenre = new ArrayList<String>();
    ObservableList<String> observableListgenre;

    private static MyServices myServices = new MyServices();
    @FXML
    private AnchorPane anchorInformationPersonnelle;
    @FXML
    private JFXButton enregistrerInformationPersonnelle;
    @FXML
    private JFXButton annulerInformationPersonnelle;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXComboBox<String> genre;
    @FXML
    private JFXComboBox<String> roles;
    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField phone;

    //les verification qui va faire pour confirmer la registration
    private boolean verificationUserName = false;
    private boolean verificationUserEmail = false;
    private boolean verificationUserConfirmEmail = false;
    private boolean verificationUserPhone = false;
    private boolean verificationUserpasword = false;
    private boolean verificationUserConfirmpasword = false;

    private boolean verificationUserNom = false;
    private boolean verificationUserPrenom = false;
    private boolean verificationUserSexe = false;
    private boolean verificationUserRole = false;
    private boolean verificationUserDateNaissance = false;
    private boolean verificationUserDateInscrit = false;

    //les verfication de la mot de passe
    boolean containsDigit = false;
    boolean containsLowerCaseLetter = false;
    boolean containsUpperCaseLetter = false;
    boolean containsSpecialCharacter = false;
    boolean length = false;
    @FXML
    private Label labelusername;
    @FXML
    private JFXTextField IdUser;
    @FXML
    private AnchorPane anchorInformationLieAuCompte;
    @FXML
    private JFXButton btnChangerEmail;
    @FXML
    private JFXButton ChangerMailButton;
    @FXML
    private JFXButton btnChangerMotDePasse;
    @FXML
    private JFXPasswordField newPassword;
    @FXML
    private JFXButton annulerInformationCompte;
    @FXML
    private JFXButton buttonSaveNewPassword;
    @FXML
    private JFXPasswordField passwordActuelle;
    @FXML
    private JFXPasswordField Confirmation_password;
    @FXML
    private JFXTextField email;

    @FXML
    private Label labelconfEmail;

    ServiceRandomMailConfirmation serviceMail = new ServiceRandomMailConfirmation();
    @FXML
    private JFXTextField confirmation_email;
    @FXML
    private Label labelpassword;
    @FXML
    private Label labelConfirmationMdp;
    @FXML
    private Label labelcontainsDigit;
    @FXML
    private Label labelcontainsLowerCaseLetter;
    @FXML
    private Label labelpasswordcontainsUpperCaseLetter;
    @FXML
    private Label labelpasswordcontainsSpecialCharacter;
    @FXML
    private Label labelpasswordlength;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelPhonecontrole;
    @FXML
    private JFXButton ImporterImagePath;
    @FXML
    private ImageView ImporterImage;
    @FXML
    private Label labelImage;
    @FXML
    private JFXButton BtnSauvegarderImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        labelImage.setVisible(false);
        labelpassword.setVisible(false);

        labelConfirmationMdp.setVisible(false);

        labelcontainsDigit.setVisible(false);

        labelcontainsLowerCaseLetter.setVisible(false);

        labelpasswordcontainsUpperCaseLetter.setVisible(false);

        labelpasswordcontainsSpecialCharacter.setVisible(false);

        labelpasswordlength.setVisible(false);
        IdUser.setVisible(false);

        User loggedUser = pidev.sandy.controller.LoginController.getInstance().getLoggedUser();

        User UserConneter = myServices.chercherUtilisateurByid(loggedUser.getId());
        IdUser.setText(String.valueOf(loggedUser.getId()));
        IdUser.setVisible(true);
        System.out.println(UserConneter.toString());

        listegenre.add("male");
        listegenre.add("femelle");
        observableListgenre = FXCollections.observableList(listegenre);//convertir la liste des genre
        genre.setItems(observableListgenre);

        listeroles.add("Admin ");
        listeroles.add("Partenaire");
        listeroles.add("Membre");
        observableListroles = FXCollections.observableList(listeroles);//convertir la liste des genre
        roles.setItems(observableListroles);
        AnchoInfoUser.setVisible(true);
        anchorInformationLieAuCompte.setVisible(false);
        anchorInformationPersonnelle.setVisible(false);
        showInformationCompte.setVisible(false);
        showInformationPersonnelle.setVisible(false);
        ImporterImage.setVisible(false);

        Compte CompteUserConnecter = myServices.chercherUtilisateurByUsernameDansLecompte(UserConneter.getUsername());
        NomPrenom.setText(UserConneter.getNom() + "+" + UserConneter.getPrenom());
        System.out.println("CompteUserConnecter" + CompteUserConnecter.toString());
        //   user.setText(String.valueOf(UserConneter.getId()));
        if (UserConneter.getNom() != null) {
            labelNom.setText(UserConneter.getNom());
        }
        if (UserConneter.getPrenom() != null) {
            labelPrenom.setText(UserConneter.getPrenom());
        }
        if (UserConneter.getUsername() != null) {
            labelUsername.setText(UserConneter.getUsername());
        }
        if (UserConneter.getEmail() != null) {
     labelEmail.setText(UserConneter.getEmail());
            
         
        }
        if (UserConneter.getPhone() != null) {
            labelPhone.setText(UserConneter.getPhone());
            
        }
        if (UserConneter.getRoles() != null) {

            String role = UserConneter.getRoles();

            if (role.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {

                labelRole.setText("Admin");

            } else if (role.equals("a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}")) {

                labelRole.setText("Partenaire");

            } else {
                labelRole.setText("Membre");

            }

        }
        if (CompteUserConnecter.getPoint_merci() != 0) {
            labelPointMerci.setText(String.valueOf(CompteUserConnecter.getPoint_merci()));
            System.out.println((String.valueOf(CompteUserConnecter.getPoint_merci())));
            
            
        }
         else {
            labelPointMerci.setText("Tu ne n'a pas des point");
        }
        

        if (CompteUserConnecter.getId_cadeau().getId() != 0) {
            System.out.println(CompteUserConnecter.getId_cadeau());
            Cadeaux cadeauxUserConnecter = new Cadeaux();
            cadeauxUserConnecter = myServices.chercherCadeauxById(CompteUserConnecter.getId_cadeau().getId());
            labelCadeau.setText("Cadeaux" + cadeauxUserConnecter.getLibelle());
        } else {
            labelCadeau.setText("Tu ne n'a pas des cadeaux");
        }

    }

    @FXML
    private void modifierProfilClick(MouseEvent event) {
        showInformationPersonnelle.setVisible(true);
        showInformationCompte.setVisible(true);
        anchorInformationLieAuCompte.setVisible(false);
        anchorInformationPersonnelle.setVisible(false);
        ImporterImage.setVisible(true);
        AnchoInfoUser.setVisible(false);
    }

    @FXML
    private void CreerCommandeWithSearchName(KeyEvent event) {
    }

    @FXML
    private void informationPersonnelleClick(MouseEvent event) {

        User loggedUser = pidev.sandy.controller.LoginController.getInstance().getLoggedUser();

        User UserConneter = myServices.chercherUtilisateurByid(loggedUser.getId());
        anchorInformationLieAuCompte.setVisible(false);
        anchorInformationPersonnelle.setVisible(true);
        username.setText(UserConneter.getUsername());
        nom.setText(UserConneter.getNom());
        prenom.setText(UserConneter.getPrenom());

        String ROLEBASE = "";
        if (UserConneter.getRoles().equals("a:1:{i:0;s:11:\"ROLE_MEMBRE\";}")) {
            ROLEBASE = "Membre";
        } else if (UserConneter.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
            ROLEBASE = "Admin";
        } else {
            ROLEBASE = "Partenaire";
        }

        listeroles.add(ROLEBASE);
        observableListroles = FXCollections.observableList(listeroles);//convertir la liste des genre
        roles.setItems(observableListroles);

        roles.getSelectionModel().selectFirst();
        phone.setText(UserConneter.getPhone());
        // date_naissance.setValue(UserConneter.getDate_naissance().toString());

        String sexe = "";
        if (UserConneter.getGenre().equals("male")) {
            sexe = "male";
        } else {
            sexe = "femelle";
        }

        listegenre.add(sexe);
        observableListgenre = FXCollections.observableList(listegenre);//convertir la liste des genre
        genre.setItems(observableListgenre);
        IdUser.setVisible(false);

        genre.getSelectionModel().selectFirst();

        showInformationCompte.setVisible(true);
        ImporterImage.setVisible(true);
    }

    @FXML
    private void informationCompteClick(MouseEvent event) {

        anchorInformationPersonnelle.setVisible(false);
        anchorInformationLieAuCompte.setVisible(true);
        email.setVisible(false);
        labelEmailcontrole.setVisible(false);
        confirmation_email.setVisible(false);
        labelconfEmail.setVisible(false);
        ChangerMailButton.setVisible(false);
        passwordActuelle.setVisible(false);
        btnChangerMotDePasse.setVisible(true);
        showInformationPersonnelle.setVisible(true);
        newPassword.setVisible(false);
        Confirmation_password.setVisible(false);
        annulerInformationCompte.setVisible(false);
        buttonSaveNewPassword.setVisible(false);
        ImporterImage.setVisible(true);

    }


    @FXML
    private void controphone(KeyEvent event) {
        if (phone.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < phone.getText().trim().length(); i++) {
                char ch = phone.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                System.out.println(nbChar);
            }

            if (nbChar == 0) {
                labelPhonecontrole.setText("number valide");
                verificationUserPhone = true;
            } else {
                labelPhonecontrole.setText("invalide number \n"
                        + " Il exist des char");
                verificationUserPhone = false;

            }

        } else {
            labelPhonecontrole.setText("8 chiffres");
            verificationUserPhone = false;
        }
    }

    @FXML
    private void verifusername(KeyEvent event) {

        User loggedUser = pidev.sandy.controller.LoginController.getInstance().getLoggedUser();

        User UserConneter = myServices.chercherUtilisateurByid(loggedUser.getId());

        boolean p = username.getText().equals(UserConneter.getUsername());

        if (myServices.chercherUtilisateurBylogin(username.getText()) == true && p == false) {
            labelusername.setText("Nom d'utilisateur existe déja");
            verificationUserName = false;
        }
        if (myServices.chercherUtilisateurBylogin(username.getText()) == false) {
            labelusername.setText("Nom d'utilisateur n'existe pas");
            verificationUserName = true;
        }

    }

    @FXML
    private void EnregistrerInfoPersonnelle(MouseEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        if ("".equals(nom.getText())) {
            alert.setTitle("Attention");
            alert.setHeaderText("Echec");
            alert.setContentText("Veillez remplir votre nom");
            alert.show();
            nom.requestFocus();

        } else if ("".equals(prenom.getText())) {
            alert.setTitle("Attention");
            alert.setHeaderText("Echec");
            alert.setContentText("Veillez remplir votre prenom");
            alert.show();
            prenom.requestFocus();

        } else if ("".equals(roles.getItems())) {
            alert.setTitle("Attention");
            alert.setHeaderText("Echec");
            alert.setContentText("Veillez remplir votre role");
            alert.show();
            roles.requestFocus();

        } else if ("".equals(genre.getItems())) {
            alert.setTitle("Attention");
            alert.setHeaderText("Echec");
            alert.setContentText("Veillez remplir votre genre");
            alert.show();
            roles.requestFocus();

        } else if (verificationUserPhone == false) {
            alert.setTitle("Attention");
            alert.setHeaderText("Echec");
            alert.setContentText("Veillez remplir votre phone");
            alert.show();
            roles.requestFocus();

        } else if (verificationUserName == false) {
            alert.setTitle("Attention");
            alert.setHeaderText("Echec");
            alert.setContentText("Veillez remplir votre Username");
            alert.show();
            roles.requestFocus();

        } else {

            String ROLEBASE = "";
            if ((String.valueOf(roles.getValue())).equals("Membre")) {
                ROLEBASE = "a:1:{i:0;s:11:\"ROLE_MEMBRE\";}";
            } else if ((String.valueOf(roles.getValue())).equals("Admin")) {
                ROLEBASE = "a:1:{i:0;s:10:\"ROLE_ADMIN\";}";
            } else {
                ROLEBASE = "a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}";
            }

            String ROLEGENRE = genre.getValue();

            User user = new User(username.getText().trim(), username.getText().trim(),
                    nom.getText().trim(), prenom.getText().trim(),
                    ROLEBASE, phone.getText().trim(),
                     ROLEGENRE);
            System.out.println(user.toString());
            myServices.modifierUtilisateursInformationPersonnel(user, Integer.parseInt(IdUser.getText().trim()));

            nom.setText(myServices.chercherUtilisateurByid(Integer.parseInt(IdUser.getText().trim())).getNom() + "  " + myServices.chercherUtilisateurByid(Integer.parseInt(IdUser.getText().trim())).getPrenom());
            //anchorModif.setVisible(false);
            //anchroProfil.setFirstLastName(servClient.getInfoClient(User.uName).getFirstname()+" "+servClient.getInfoClient(User.uName).getLastname());
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("AnchroProfilClient.fxml"));
//            try {
//                Parent root = loader.load();
//                AnchroProfilClientController r =loader.getController();
//                r.setFirstLastName(servClient.getInfoClient(User.uName).getFirstname()+" "+servClient.getInfoClient(User.uName).getLastname());
//            } catch (Exception e) {
//            }
            ServiceNotification.showNotif("Operation effectué", "Vos informations personnelles sont à jour");
            anchorInformationLieAuCompte.setVisible(false);
            anchorInformationPersonnelle.setVisible(false);
            AnchoInfoUser.setVisible(true);
            showInformationCompte.setVisible(false);
            showInformationPersonnelle.setVisible(false);
            ImporterImage.setVisible(false);
            setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ProfileMembre.fxml")));

        }
    }

    private void setNode(Node node) {
        Anchprincipal.getChildren().clear();
        Anchprincipal.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

      @FXML
    private void Annuler(MouseEvent event) {
        anchorInformationPersonnelle.setVisible(false);
    }

    @FXML
    private void typeuser(MouseEvent event) {
    }

    @FXML
    private void buttonToChangeMail(MouseEvent event) {
        User loggedUser = pidev.sandy.controller.LoginController.getInstance().getLoggedUser();

        User UserConneter = myServices.chercherUtilisateurByid(loggedUser.getId());
        email.setVisible(true);
        labelEmailcontrole.setVisible(true);
        confirmation_email.setVisible(true);
        labelconfEmail.setVisible(true);
        ChangerMailButton.setVisible(true);
        email.setText(myServices.chercherUtilisateurByUsername(UserConneter.getUsername()).getEmail());
        btnChangerMotDePasse.setVisible(false);
         btnChangerMotDePasse.setVisible(false);
        passwordActuelle.setVisible(false);
        
         
       
     
        
    }

    @FXML
    private void ChangerMailButtonSave(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        if ("".equals(email.getText().trim())) {
            alert.setTitle("Attention");
            alert.setHeaderText("Echec");
            alert.setContentText("Veillez remplir votre email");
            alert.show();
            nom.requestFocus();

        } else if ("".equals(confirmation_email.getText().trim())) {
            alert.setTitle("Attention");
            alert.setHeaderText("Echec");
            alert.setContentText("Veillez remplir votre confirmation email");
            alert.show();
            confirmation_email.requestFocus();

        } else if (verificationUserEmail == false && verificationUserEmail == false) {

            alert.setTitle("Attention");
            alert.setHeaderText("Echec");
            alert.setContentText("Verifier votre email");
            alert.show();
            email.requestFocus();

        } else {

            String code = serviceMail.generateRandomString();
            System.out.println(code);

            String to = email.getText().trim();
            String subject = "Confirmation de modification Email";
            String message = "Bienvenu " + prenom.getText() + " " + nom.getText() + " dans notre application voici votre code de confirmation " + code + "  Veillez saisir votre code pour confirmer votre modofication";
            String usermail = "alaa.guissouma@esprit.tn";
            String passmail = "Skotinka00_";
            Mailing.send(to, subject, message, usermail, passmail);

            if (verifconfirMail(code) == true) {

                User user = new User();
                user.setEmail(email.getText().trim());
               // myServices.modifierUtilisateursInformationPersonnel(user, Integer.parseInt(IdUser.getText().trim()));
                myServices.modifierEMAILUtilisateurs(user, Integer.parseInt(IdUser.getText().trim()));
                //anchorModif.setVisible(false);
                ServiceNotification.showNotif("Operation effectué", "Votre email est bien à jour");

            }

        }

    }

    public boolean verifconfirMail(String code) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Confirmez votre modification email");
        dialog.setHeaderText("Un mail vous a été envoyer où vous trouvez le code");
        dialog.setContentText("Entrez votre code de confirmation:");
        Optional<String> result = dialog.showAndWait();
        if (result.get().equals(code)) {

            if (result.get().equals(code)) {
                return true;
            }
        } else {
            return verifconfirMail(code);
        }
        return false;
    }

    @FXML
    private void buttonToChangePassword(MouseEvent event) {
 ChangerMailButton.setVisible(false);
        email.setVisible(false);
        labelEmailcontrole.setVisible(false);
        confirmation_email.setVisible(false);
        labelconfEmail.setVisible(false);
        ChangerMailButton.setVisible(false);
        passwordActuelle.setVisible(true);
btnChangerEmail.setVisible(false);
    }

    @FXML
    private void AnnulerInforCompte(MouseEvent event) {
        
        anchorInformationLieAuCompte.setVisible(false);
    }

    @FXML
    private void saveNewPassword(MouseEvent event) {
        
 Alert alert = new Alert(Alert.AlertType.ERROR);
        if ("".equals(newPassword.getText().trim())) {
            alert.setTitle("Attention");
            alert.setHeaderText("Echec");
            alert.setContentText("Veillez remplir votre mot de passe");
            alert.show();
            newPassword.requestFocus();

        } else if ("".equals(Confirmation_password.getText().trim())) {
            alert.setTitle("Attention");
            alert.setHeaderText("Echec");
            alert.setContentText("Veillez remplir votre Confirmation password ");
            alert.show();
            Confirmation_password.requestFocus();

        } else if (verificationUserConfirmpasword == false && verificationUserpasword == false) {

            alert.setTitle("Attention");
            alert.setHeaderText("Echec");
            alert.setContentText("Verifier votre mot de passe");
            alert.show();
            newPassword.requestFocus();

        } else {
            String passCrypt = Password.hashPassword(newPassword.getText());
       User user = new User();
                user.setPassword(passCrypt);
          
                
   

            myServices.modifierPasswordUtilisateurs(user, Integer.parseInt(IdUser.getText().trim()));
                    //anchorModif.setVisible(false);
                    newPassword.setText("");
                    Confirmation_password.setText("");
                ServiceNotification.showNotif("Operation effectué", "Votre mot de passe est bien à jour");
                anchorInformationLieAuCompte.setVisible(false);
                anchorInformationPersonnelle.setVisible(false);
                showInformationCompte.setVisible(false);
        showInformationPersonnelle.setVisible(false);
        ImporterImage.setVisible(false);
        AnchoInfoUser.setVisible(true);

        }

    }

    @FXML
    private void controlMdp(KeyEvent event) {

        String PAS = newPassword.getText().trim();
        System.out.println(PAS);
        if (PAS.length() >= 6) {// Check for Digits in password
//•	Contains at least 1 numeric digit
            labelpasswordlength.setText("longeur just");
            verificationUserConfirmpasword = true;

            for (int i = 0; i < PAS.length(); i++) {
                char ch = PAS.charAt(i);

                if (Character.isDigit(ch)) {// Check for Digits in password
//•	Contains at least 1 numeric digit
                    labelcontainsDigit.setText("Contient un nombre");
                    containsDigit = true;
                }

                if (Character.isLetter(ch) && Character.isLowerCase(ch)) {// Check for Letters in password
//•	Contains at least 1 lower letter character
                    labelcontainsLowerCaseLetter.setText("Contient une lettre minus");
                    containsLowerCaseLetter = true;

                }

                if (Character.isLetter(ch) && Character.isUpperCase(ch)) {// Check for Letters in password
//•	Contains at least 1 upper letter character
                    labelpasswordcontainsUpperCaseLetter.setText("Contient une lettre majus");
                    containsUpperCaseLetter = true;

                }
                if (ch == '!' || ch == '@' || ch == '#' || ch == '$' || ch == '%' || ch == '^' || ch == '&' || ch == '*') {
//•	Contains at least 1 special character from the set: !@#$%^&*
                    labelpasswordcontainsSpecialCharacter.setText("Contient un lettre sepcial");
                    containsSpecialCharacter = true;

                }
                System.out.println(containsUpperCaseLetter + "containsUpperCaseLetter\n" + containsLowerCaseLetter + "containsLowerCaseLetter\n"
                        + containsDigit + "containsDigit\n" + containsSpecialCharacter + "containsSpecialCharacter\n\n\n");

                if (containsUpperCaseLetter && containsLowerCaseLetter && containsDigit && containsSpecialCharacter) {
                    labelpassword.setText("Mot de passe valide!");

                    verificationUserpasword = true;
                }

            }
        } else {
            labelpasswordlength.setText("Il faut 6 caractere");
            labelpassword.setText("Mot de passe  invalide!");
            length = false;
            verificationUserpasword = false;
            labelpasswordcontainsSpecialCharacter.setText("");
            containsSpecialCharacter = false;
            labelpasswordcontainsUpperCaseLetter.setText("");
            containsUpperCaseLetter = false;
            labelcontainsLowerCaseLetter.setText("");
            containsLowerCaseLetter = false;
            labelcontainsDigit.setText("");
            containsDigit = false;
        }

    }

    @FXML
    private void ConfirmMDP(KeyEvent event) {
        if (newPassword.getText().equals(Confirmation_password.getText())) {
            labelConfirmationMdp.setText("Mot de passe valide!");
            verificationUserConfirmpasword = true;
        } else {
            labelConfirmationMdp.setText("Verifier votre mot de passe");
            verificationUserConfirmpasword = false;
        }

    }

    @FXML
    private void verifEmail(KeyEvent event) {

        User loggedUser = pidev.sandy.controller.LoginController.getInstance().getLoggedUser();
        User UserConneter = myServices.chercherUtilisateurByid(loggedUser.getId());

        boolean pemail = email.getText().equals(UserConneter.getEmail());

        if (myServices.chercherUtilisateurByEmail(email.getText()) == true && pemail == false) {
            labelEmailcontrole.setText("Email Existe déja");
            verificationUserEmail = false;
        }
        if (myServices.chercherUtilisateurByEmail(email.getText()) == false) {//alphanumerique@alphanumerique.com
            //{ici longeur  }
            //debut ^
            //fin $
            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(email.getText().trim());

            if (matcher.matches()) {       //if   matcher ne contient pas la format   
                labelEmailcontrole.setVisible(true);
                labelEmailcontrole.setText("Email valide !");
                verificationUserEmail = true;

            } else {
                labelEmailcontrole.setVisible(true);
                labelEmailcontrole.setText("Email Format invalide !");
                // JOptionPane.showMessageDialog(null, "Email Format invalide");
                verificationUserEmail = false;

            }
        }

    }

    @FXML
    private void ConfirmEmail(KeyEvent event) {

        if (confirmation_email.getText().equals(email.getText())) {
            labelconfEmail.setText("les deux email sont confondu");
            verificationUserConfirmEmail = true;
        } else {
            labelconfEmail.setText("Verifier votre confirmation email");
            verificationUserConfirmEmail = false;
        }

    }

    @FXML
    private void VerifPasswordActuelle(KeyEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (event.getCode() == KeyCode.ENTER) {
            
              User loggedUser = LoginController.getInstance().getLoggedUser();
              User UserConneter = myServices.chercherUtilisateurByid(loggedUser.getId());
            
              System.out.println("sername"+UserConneter.getUsername());
              System.out.println("pass"+UserConneter.getPassword());
         
                Boolean pas=myServices.verifierpassword(UserConneter.getPassword(), UserConneter.getUsername());
              
                
                
                
//                
//                
//            serviceCryptage cryptage = new serviceCryptage();
//            String passCrypt = Password.hashPassword(passwordActuelle.getText());
//            System.out.println(passCrypt);
//            System.out.println(Integer.parseInt(IdUser.getText().trim()));
            if (Password.checkPassword(passwordActuelle.getText(),UserConneter.getPassword())) {
                passwordActuelle.setVisible(false);
                passwordActuelle.setText("");
                buttonSaveNewPassword.setVisible(true);
                newPassword.setVisible(true);
                Confirmation_password.setVisible(true);
                annulerInformationCompte.setVisible(true);
                labelpassword.setVisible(true);

                labelConfirmationMdp.setVisible(true);

                labelcontainsDigit.setVisible(true);

                labelcontainsLowerCaseLetter.setVisible(true);

                labelpasswordcontainsUpperCaseLetter.setVisible(true);

                labelpasswordcontainsSpecialCharacter.setVisible(true);

                labelpasswordlength.setVisible(true);
            } else {
                alert.setTitle("Attention");
                alert.setHeaderText("Echec");
                alert.setContentText("Mot de passe Actuel incorrecte");
                alert.show();
                passwordActuelle.requestFocus();
            }
        }
    }
       private boolean verificationImage;
  String path;
    File selectedFile;
     @FXML
     private void image(ActionEvent event) throws MalformedURLException 
    {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home")));//importation de l'image ou
        fc.setTitle("Veuillez choisir l'image"); //titre de la
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
//                path = selectedFile.toURI().toURL().toExternalForm();
          Image image = new Image(selectedFile.toURI().toString());
          
          
          ImporterImage.setVisible(true);
          
           ImporterImage.setImage(image) ;
           labelImage.setVisible(true);
            ImporterImagePath.setText(path);
            labelImage.setText("Cliquez sur le button pour la changer!");
              if(path.equals(""))
            {
            verificationImage=false;
            }
            else 
                  
              { verificationImage=true;}  
               
//             ImporterImage.

        }

    }

 
    @FXML
    private void SauvegarderImage(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
        if (verificationImage) {
            
 User user = new User();
           user.setImage(path);
          
                
      
                if (selectedFile != null)
            {
                try {
                    //System.out.println(selectedFile.toString());
                    File source = new File(selectedFile.toString());
                    File dest = new File("C:\\wamp64\\www\\TunisiaBonPlan2\\web\\uploads\\images");

                    FileUtils.copyFileToDirectory(source, dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                

            myServices.modifierImageUtilisateurs(user, Integer.parseInt(IdUser.getText().trim()));
                    //anchorModif.setVisible(false);
                   
                ServiceNotification.showNotif("Operation effectué", "Votre image est bien à jour");
                anchorInformationLieAuCompte.setVisible(false);
                anchorInformationPersonnelle.setVisible(false);
                showInformationCompte.setVisible(false);
        showInformationPersonnelle.setVisible(false);
        ImporterImage.setVisible(true);
        labelImage.setVisible(false);
        BtnSauvegarderImage.setVisible(false);
        AnchoInfoUser.setVisible(true);            
        } else {
                alert.setTitle("Attention");
            alert.setHeaderText("Echec");
            alert.setContentText("Selectioner un image ");
            alert.show();
            newPassword.requestFocus();
        }
    }


  

     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}
