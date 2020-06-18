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
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import jdk.nashorn.internal.parser.TokenType;
import org.apache.commons.io.FileUtils;
import pidev.sandy.entites.Badge;
import pidev.sandy.entites.Cadeaux;
import pidev.sandy.entites.Compte;
import pidev.sandy.entites.Mailing;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
import pidev.sandy.services.Password;
import pidev.sandy.services.ServiceNotification;
import pidev.sandy.services.ServiceRandomMailConfirmation;
import pidev.sandy.services.ServiceSysdate;
import pidev.sandy.services.serviceCryptage;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class AjouterUilisateurController implements Initializable {

    //les composants
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField ccnfirmation_email;
    @FXML
    private JFXComboBox<String> genre;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField Confirmation_password;

    @FXML
    private JFXComboBox<String> roles;
   
    @FXML
    private ImageView view1;
    @FXML
    private Label inscrirLabel;
  
    @FXML
    private JFXButton addBtn;
    @FXML
    private JFXTextField nom;
   
    @FXML
    private JFXDatePicker date_naissance;
    @FXML
    private JFXButton annulerBtn;

    @FXML
    private Label labelusername;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelconfEmail;
    @FXML
    private Label labelpassword;
    @FXML
    private Label labelConfirmationMdp;
    @FXML
    private JFXDatePicker date_inscrit;
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
    private Label labelPhone;

    //liste des roles qui ont va le remplir dans ini
    List<String> listeroles = new ArrayList<String>();
    ObservableList<String> observableListroles;

    //liste des genres qui ont va le remplir dans ini
    List<String> listegenre = new ArrayList<String>();
    ObservableList<String> observableListgenre;

    //les verification qui va faire pour confirmer la registration
    private boolean verificationUserName=true;
    private boolean verificationUserEmail=true;
    private boolean verificationUserConfirmEmail=true;
    private boolean verificationUserPhone=true;
    private boolean verificationUserpasword=true;
    private boolean verificationUserConfirmpasword=true;

 
    private boolean verificationUserNom=true;
    private boolean verificationUserPrenom=true;
    private boolean verificationUserSexe=true;
    private boolean verificationUserRole=true;
    private boolean verificationUserDateNaissance=true;
    private boolean verificationUserDateInscrit=true;

    //les verfication de la mot de passe
    boolean containsDigit = false;
    boolean containsLowerCaseLetter = false;
    boolean containsUpperCaseLetter = false;
    boolean containsSpecialCharacter = false;
    boolean length = false;
    

    private MyServices myServices = new MyServices();
 
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField phone;
    
    
     private Stage stage;//controle de classe(annuler ou fermer) stage(serie)
    private boolean buttonConfimClicked = false;//nous informer est ce que on clicker sur confimer ou annuler
    private User user;
    @FXML
    private ProgressBar progressPersonal;
    @FXML
    private Label lblComplete;
    @FXML
    private JFXButton ImporterImagePath;
    @FXML
    private ImageView date_inscritCheck;
    @FXML
    private ImageView date_naissanceCheck;
    @FXML
    private ImageView rolesCheck;
    @FXML
    private ImageView prenomCheck;
    @FXML
    private ImageView phoneCheck;
    @FXML
    private ImageView genreCheck;
    @FXML
    private ImageView nomCheck;
    @FXML
    private ImageView Confirmation_passwordCheck;
    @FXML
    private ImageView passwordCheck;
    @FXML
    private ImageView ccnfirmation_emailCheck;
    @FXML
    private ImageView emailCheck;
    @FXML
    private ImageView usernameCheck;
    @FXML
    private ImageView ImporterImageCheck;
    @FXML
    private Label labeldate_naissance;
    @FXML
    private Label labeldate_inscrit;
    
      public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isButtonConfimClicked() {
        return buttonConfimClicked;
    }

    public void setButtonConfimClicked(boolean buttonConfimClicked) {
        this.buttonConfimClicked = buttonConfimClicked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {

        this.user = user;

        //je suis interesser quand j'ai un new client
        //accepter les champs technique avec cette champs
        this.username.setText(user.getUsername());
        this.email.setText(user.getEmail());
        this.ccnfirmation_email.setText(user.getEmail_canonical());
        this.Confirmation_password.setText(user.getPassword());
        this.password.setText(user.getPassword());
        
     
        //this.password.setText(user.getPassword());

 
        
   
          DecimalFormat decimalFormat = new DecimalFormat("###.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
          progressPersonal.setProgress(0.00);  
         lblComplete.setText(decimalFormat.format(0 * 100) + "% complete");
              if (user.getId()!=0) {
            
 
// 
//                  progressPersonal.setProgress(1);
//                lblComplete.setText(decimalFormat.format(1 * 100) + "% complete");
// 
             date_inscritCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
             rolesCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
             prenomCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
             phoneCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
             genreCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
             nomCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
             //Confirmation_passwordCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
             //passwordCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
             ccnfirmation_emailCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
             usernameCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
             emailCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
             usernameCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
             ImporterImageCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
            
        }
                   
              String pattern = "dd/MM/yyyy";

date_inscrit.setPromptText(user.getDate_inscription());

date_inscrit.setConverter(new StringConverter<LocalDate>() {
     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

     @Override 
     public String toString(LocalDate date) {
         if (date != null) {
             return dateFormatter.format(date);
         } else {
             return "";
         }
     }

     @Override 
     public LocalDate fromString(String string) {
         if (string != null && !string.isEmpty()) {
             return LocalDate.parse(string, dateFormatter);
         } else {
             return null;
         }
     }
 });
date_naissance.setPromptText(user.getDate_naissance());

date_naissance.setConverter(new StringConverter<LocalDate>() {
     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

     @Override 
     public String toString(LocalDate date) {
         if (date != null) {
             return dateFormatter.format(date);
         } else {
             return "";
         }
     }

     @Override 
     public LocalDate fromString(String string) {
         if (string != null && !string.isEmpty()) {
             return LocalDate.parse(string, dateFormatter);
         } else {
             return null;
         }
     }
 });


     
        try {
               File file = new File("C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + user.getImage());
        javafx.scene.image.Image img;
            img = new javafx.scene.image.Image(file.toURI().toURL().toString());
                  view1.setImage(img);
        } catch (MalformedURLException ex) {
            System.out.println(ex.toString());
        }

  
        
        listegenre.add(user.getGenre());
        observableListgenre = FXCollections.observableList(listegenre);//convertir la liste des genre
        genre.setItems(observableListgenre);

        listeroles.add(user.getRoles());
        observableListroles = FXCollections.observableList(listeroles);//convertir la liste des genre
        this.roles.setItems(observableListroles);

        this.nom.setText(user.getNom());
   genre.setValue(user.getGenre());
    roles.setValue(user.getRoles());
        this.prenom.setText(user.getPrenom());

        this.phone.setText(user.getPhone());


        // this.date_inscrit.setValue(String.valueOf()  );
        // this.date_naissance.setValue(user.getDate_naissance());
        //  this.date.setText(String.valueOf(personne.getDate_inscrit().format(DateTimeFormatter.ofPattern(pattern), args)));
//tawan bech nifhmou 3lech 3malna les 2 phrases ili just fou9i
//imchi ListeUtilisateurFXMLController tawa tifhim
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       updateProgress();
        listegenre.add("male");
        listegenre.add("femelle");
        observableListgenre = FXCollections.observableList(listegenre);//convertir la liste des genre
        genre.setItems(observableListgenre);

        //listeroles.add("a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
       // listeroles.add("a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}");
        listeroles.add("MEMBRE");
        listeroles.add("ADMIN");
        listeroles.add("PROPRIETAIRE");
        
        
        
        
        
        observableListroles = FXCollections.observableList(listeroles);//convertir la liste des genre
        roles.setItems(observableListroles);

    }

    public static void loadWindow(URL loc, String title, Stage parentStage) {
        try {
            Parent parent = FXMLLoader.load(loc);
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            Scene scene = new Scene(parent);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void closeApplication(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Vous allez quitter l'application");
        alert.setHeaderText("Vous allez quitter l'application");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } else {
            alert.close();
        }
    }

    @FXML
    private void verifusername(KeyEvent event) {
        if (myServices.chercherUtilisateurBylogin(username.getText()) == true) {
            labelusername.setText("Nom d'utilisateur existe déja");
            verificationUserName = false;
        }
        if (myServices.chercherUtilisateurBylogin(username.getText()) == false) {
            labelusername.setText("Nom d'utilisateur n'existe pas");
            verificationUserName = true;
        }

    }

    @FXML
    private void verifEmail(KeyEvent event) {

        if (myServices.chercherUtilisateurByEmail(email.getText()) == true) {
            labelEmail.setText("Email Existe déja");
            verificationUserEmail = false;
        }
        if (myServices.chercherUtilisateurByEmail(email.getText()) == false) {//alphanumerique@alphanumerique.com
            //{ici longeur  }
            //debut ^
            //fin $
            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(email.getText());

            if (matcher.matches()) {       //if   matcher ne contient pas la format   
                labelEmail.setVisible(false);
                labelEmail.setText("Email valide !");
                verificationUserEmail = true;

            } else {
                labelEmail.setVisible(true);
                labelEmail.setText("Email Format invalide !");
                // JOptionPane.showMessageDialog(null, "Email Format invalide");
                verificationUserEmail = false;

            }
        }

    }

    @FXML
    private void ConfirmEmail(KeyEvent event) {

        if (email.getText().equals(ccnfirmation_email.getText())) {
            labelconfEmail.setText("les deux email sont confondu");
            verificationUserConfirmEmail = true;
        } else {
            labelconfEmail.setText("Verifier votre email");
            verificationUserConfirmEmail = false;
        }

    }

    @FXML
    private void typeuser(MouseEvent event) {
    }

    @FXML
    private void controlMdp(KeyEvent event) {

        String PAS = password.getText().trim();
 
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
        
      
        if (password.getText().equals(Confirmation_password.getText())) {
            labelConfirmationMdp.setText("Mot de passe vide!");
            verificationUserConfirmpasword = true;
        } else {
            labelConfirmationMdp.setText("Verifier votre mot de passe");
            verificationUserConfirmpasword = false;
        }

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
                labelPhone.setText("number valide");
                verificationUserPhone = true;
            } else {
                labelPhone.setText("invalide number \n"
                        + " Il exist des char");
                verificationUserPhone = false;

            }

        } else {
            labelPhone.setText("8 chiffres");
            verificationUserPhone = false;
        }
    }
    
    
    


    
//verification 3
    public boolean verifconfirMail() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Confirmez l'ajout de l'utilisateur");
        dialog.setHeaderText("Un mail a été envoyer au l'utlisateur avec l'username et mot de passe");
        dialog.setContentText("Entre oui pour la confirmation:");
        Optional<String> result = dialog.showAndWait();
        if (result.get().equals("oui")) {

            if (result.get().equals("oui")) {
                return true;
            }
        } else {
            return verifconfirMail();
        }
        return false;
    }

    
    
    
private boolean Signup() {
 
 

        if (genre.getValue() != null) {
             genreCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
            verificationUserSexe = true;
        } else {    genreCheck.setImage(new Image("/pidev/sandy/ressources/img/ "));
            verificationUserSexe = false;
        }

        if (roles.getValue() != null) {
             rolesCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
            verificationUserRole = true;
        } else {    rolesCheck.setImage(new Image("/pidev/sandy/ressources/img/ "));
            verificationUserRole = false;
        }
      
        if (ImporterImagePath.getText().equals("Ajouter Photo")) {
         verificationImage = false;
         ImporterImageCheck.setImage(new Image("/pidev/sandy/ressources/img/ "));
        } else {  
            
            
            ImporterImageCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
            verificationImage = true;
         
        }

        if (verificationImage && verificationUserName && verificationUserEmail && verificationUserConfirmEmail && verificationUserPhone
                && verificationUserpasword && verificationUserConfirmpasword && verificationUserNom
                && verificationUserPrenom && verificationUserSexe && verificationUserRole
                && verificationUserDateNaissance && verificationUserDateInscrit) {
            
            System.out.println(verificationImage);
            System.out.println(verificationUserName);
            System.out.println(verificationUserEmail);
            System.out.println(verificationUserConfirmEmail);
            System.out.println(verificationUserpasword);
            System.out.println(verificationUserConfirmpasword);
            System.out.println(verificationUserNom);
            System.out.println(verificationUserPrenom);
            System.out.println(verificationUserSexe);
            System.out.println(verificationUserRole);
            System.out.println(verificationUserDateNaissance);
            System.out.println(verificationUserDateInscrit);
           
            
            

            return true;
        } else {
      System.out.println(verificationImage);
            System.out.println(verificationUserName);
            System.out.println(verificationUserEmail);
            System.out.println(verificationUserConfirmEmail);
            System.out.println(verificationUserpasword);
            System.out.println(verificationUserConfirmpasword);
            System.out.println(verificationUserNom);
            System.out.println(verificationUserPrenom);
            System.out.println(verificationUserSexe);
            System.out.println(verificationUserRole);
            System.out.println(verificationUserDateNaissance);
            System.out.println(verificationUserDateInscrit);
            return false;
        }

    }
 //verification 2===>appelle a  //verification 3
    @FXML
    private void handleButtonConfirmar(ActionEvent event) {
 
        if (Signup()) {

            
         

            String to = "alaa.guissouma@esprit.tn";
            String subject = "Confirmation d'inscription";
            String message = "Bienvenu " + prenom.getText() + " " + nom.getText() + " dans notre application voici votre mot de passe  " + password.getText() + " et votre Username ==>"+username.getText();
            String usermail = "alaa.guissouma@esprit.tn";
            String passmail = "Skotinka00_";
            Mailing.send(to, subject, message, usermail, passmail);

            if (verifconfirMail() == true) {
           
                String passCrypt = Password.hashPassword(password.getText().trim());

                user.setUsername(username.getText());
                user.setUsername_canonical(username.getText());
                user.setEmail(email.getText());
                user.setEmail_canonical(ccnfirmation_email.getText());
               user.setEnabled(1);
                user.setPassword(passCrypt);
                
                
                
                String role=((String.valueOf(roles.getValue())));
                
                if (role.equals("ADMIN")) {
                    user.setRoles("a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
                } else if (role.equals("PROPRIETAIRE")) {
                      user.setRoles("a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}");
                    
                }
                else
                {
                 user.setRoles("a:1:{i:0;s:11:\"ROLE_MEMBRE\";}");
                }
                
                
                
                
 
                
                
                
                
                user.setDate_inscription(date_inscrit.getValue().toString());
                user.setDate_naissance(date_naissance.getValue().toString());

               user.setNom(nom.getText());
                user.setPrenom(prenom.getText());
                user.setPhone(phone.getText());
               
                //user.setDate_naissance(date_naissance.getValue().format(DateTimeFormatter.BASIC_ISO_DATE));

                // user.setDate_inscription(date_inscrit.getValue().format(DateTimeFormatter.BASIC_ISO_DATE));
                user.setGenre(genre.getValue());
                
                
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
                
                
                buttonConfimClicked = true;
 
            stage.close();//boite de dialogue
               

            //    loadWindow(getClass().getResource("/pidev/sandy/GUI/Login.fxml"), "Login", null);
               
//
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir tous les champs");
            alert.show();

        }

    }
    
    
    
    
  

    @FXML
    private void handleButtonCancelar(ActionEvent event) {

      stage.close();

    }
    
    String path;
    File selectedFile;
     private boolean verificationImage;
    @FXML
     private void image(ActionEvent event) throws MalformedURLException 
    {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));//importation de l'image ou
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
           view1.setImage(image) ;
            ImporterImagePath.setText(path);
         
              if(path.equals(""))
            {
            verificationImage=false;
              ImporterImageCheck.setImage(new Image("/pidev/sandy/ressources/img/ "));
            }
            else 
                  
              { verificationImage=true;
                ImporterImageCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
              }  
               
//             ImporterImage.

        }

    }
     
     private static double progress1 = 0;
    private static double progress2 = 0;
    private static double progress3 = 0;
    private static double progress4 = 0;
    private static double progress5 = 0;
    private static double progress6 = 0;
    private static double progress7 = 0;
    private static double progress8 = 0;
    private static double progress9 = 0;
    private static double progress10 = 0;
    private static double progress11 = 0;
    private static double progress12 = 0;
    
    private void updateProgress() {
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
 
    progressPersonal.setProgress(0.00);       
        double sum_progress = progress11+progress12+progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9;

        nom.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress1 = 0.1;

                } else {
                    progress1 = 0.0;

                }

                double sum = (progress11+progress12+progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

        prenom.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress2 = 0.1;

                } else {
                    progress2 = 0.0;

                }
                double sum = (progress11+progress12+progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

        roles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress3 = 0.1;

                } else {
                    progress3 = 0.0;

                }
                double sum = (progress11+progress12+progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
 
        date_naissance.valueProperty().addListener(new ChangeListener<LocalDate>() {
        

            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                  if (newValue.getYear()> 1) {
                    progress4 = 0.1;

                } else {
                    progress4 = 0.0;

                }
                double sum = (progress11+progress12+progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

        phone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress5 = 0.1;

                } else {
                    progress5 = 0.0;

                }
                double sum = (progress11+progress12+progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
        //Course name
 date_inscrit.valueProperty().addListener(new ChangeListener<LocalDate>() {
        

            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                  if (newValue.getYear()> 1) {
                    progress4 = 0.1;

                } else {
                    progress4 = 0.0;

                }
                double sum = (progress11+progress12+progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
        // Amount paid
        genre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress7 = 0.1;

                } else {
                    progress7 = 0.0;

                }
                double sum = (progress11+progress12+progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
        //Gender Radio buttons
        username.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress8 = 0.1;

                } else {
                    progress8 = 0.0;

                }
                double sum = (progress11+progress12+progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
             email.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress9 = 0.1;

                } else {
                    progress9 = 0.0;

                }
                double sum = (progress11+progress12+progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

             ccnfirmation_email.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress10 = 0.1;

                } else {
                    progress10 = 0.0;

                }
                double sum = (progress11+progress12+progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
               password.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress11 = 0.1;

                } else {
                    progress11 = 0.0;

                }
                double sum = (progress11+progress12+progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
               
               //ObservableValue:la possibilite ajouter retire des ecouteur de type invalidationListner
               
              Confirmation_password.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress12 = 0.1;

                } else {
                    progress12 = 0.0;

                }
                double sum = (progress11+progress12+progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

              

    }
    
     @FXML
    private void verifNom(KeyEvent event) {
        
        
        int nbNonChar = 0;
            for (int i = 1; i < nom.getText().trim().length(); i++) {
                char ch = nom.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && nom.getText().trim().length() >=3) {
            nomCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
            
            verificationUserName = true;
            } else {
              usernameCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                 verificationUserName = false;

            }
    }

    @FXML
    private void verifPrenom(KeyEvent event) {
        
        
   int nbNonChar = 0;
            for (int i = 1; i < prenom.getText().trim().length(); i++) {
                char ch = prenom.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && prenom.getText().trim().length() >=3) {
            nomCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
            
            verificationUserPrenom = true;
            } else {
              usernameCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                 verificationUserPrenom = false;

            }
    }
 ServiceSysdate sys = new ServiceSysdate();
    
    @FXML
    private void verifiedate_naissance() {
      
        if (date_naissance.getValue() != null) {
           
                       Date dateo = Date.valueOf(date_naissance.getValue());
            if (dateo.before(sys.selectDate()))
            {labeldate_naissance.setVisible(false);
                 
                     date_naissanceCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
     verificationUserDateNaissance= true; 
                            
        }
            else{ labeldate_naissance.setVisible(true);  
             labeldate_naissance.setText("Date naissance doit etre inferieur a la date actuel");
                           date_naissanceCheck.setImage(new Image("/pidev/sandy/ressources/img/ "));
     verificationUserDateNaissance= false; 
            }
        }
             
        }
            
            
    @FXML
    private void verifiedate_inscrit() {
    
        if (date_inscrit.getValue() != null ) {
           
                       Date dateo = Date.valueOf(date_inscrit.getValue());
            if (dateo.equals(sys.selectDate()))
            {    
                labeldate_inscrit.setVisible(false);
                date_inscritCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));

                verificationUserDateInscrit= true;
                            
        } 
               else{ 

                           
                          labeldate_inscrit.setVisible(true);  
             labeldate_inscrit.setText("Date inscrit doit etre egale date actuel");
         date_inscritCheck.setImage(new Image("/pidev/sandy/ressources/img/ "));
                           
            }
   

    }

 
 
    }
    
}
