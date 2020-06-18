/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import static com.itextpdf.text.pdf.security.LtvTimestamp.timestamp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.*;//c a d regular expression

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
 
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import pidev.sandy.entites.Badge;
import pidev.sandy.entites.Cadeaux;
import pidev.sandy.entites.Compte;
import pidev.sandy.entites.Mailing;
import pidev.sandy.entites.User;
import pidev.sandy.services.BCrypt;
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
public class RegistrationController implements Initializable {

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
    private Label inscrirLabel;
    @FXML
    private JFXButton login;
    @FXML
    private JFXButton signup;
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
    String path;
    File selectedFile;

    //liste des roles qui ont va le remplir dans ini
    List<String> listeroles = new ArrayList<String>();
    ObservableList<String> observableListroles;

    //liste des genres qui ont va le remplir dans ini
    List<String> listegenre = new ArrayList<String>();
    ObservableList<String> observableListgenre;

    //les verification qui va faire pour confirmer la registration
    private boolean verificationUserName;
    private boolean verificationUserEmail;
    private boolean verificationUserConfirmEmail;
    private boolean verificationUserPhone;
    private boolean verificationUserpasword;
    private boolean verificationUserConfirmpasword;
    private boolean verificationImage;

    private boolean verificationUserNom;
    private boolean verificationUserPrenom;
    private boolean verificationUserSexe;
    private boolean verificationUserRole;
    private boolean verificationUserDateNaissance;
    private boolean verificationUserDateInscrit;

    //les verfication de la mot de passe
    boolean containsDigit = false;
    boolean containsLowerCaseLetter = false;
    boolean containsUpperCaseLetter = false;
    boolean containsSpecialCharacter = false;
    boolean length = false;
    

    private MyServices myServices = new MyServices();
    ServiceRandomMailConfirmation serviceMail = new ServiceRandomMailConfirmation();
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField phone;
    @FXML
    private ImageView ImporterImage;
    @FXML
    private JFXButton ImporterImagePath;
    @FXML
    private Label labelImage;
    @FXML
    private ImageView usernameCheck;
    @FXML
    private ImageView emailCheck;
    @FXML
    private ImageView ccnfirmation_emailCheck;
    @FXML
    private ImageView genreCheck;
    @FXML
    private ImageView passwordCheck;
  
    @FXML
    private ImageView prenomCheck;
    @FXML
    private ImageView rolesCheck;
    @FXML
    private ImageView Confirmation_passwordCheck;
    @FXML
    private ImageView date_naissanceCheck;
    @FXML
    private ImageView date_inscritCheck;
    @FXML
    private ImageView ImporterImageCheck;
    @FXML
    private ImageView phoneCheck;
    @FXML
    private ImageView nomCheck;
    @FXML
    private Label labelnom;
    @FXML
    private Label labelprenom;
    @FXML
    private Label labeldate_naissance;
    @FXML
    private Label labeldate_inscrit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        listegenre.add("male");
        listegenre.add("femelle");
        observableListgenre = FXCollections.observableList(listegenre);//convertir la liste des genre
        genre.setItems(observableListgenre);

        listeroles.add("Admin ");
        listeroles.add("Partenaire");
        listeroles.add("Membre");
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

    @FXML
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
    private void verifEmail(KeyEvent event) {

        if (myServices.chercherUtilisateurByEmail(email.getText().trim()) == true) {
            labelEmail.setText("Email Existe déja");
            verificationUserEmail = false;
        }
        if (myServices.chercherUtilisateurByEmail(email.getText().trim()) == false) {//alphanumerique@alphanumerique.com
            //{ici longeur  }
            //debut ^
            //fin $
            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(email.getText().trim());

            if (matcher.matches()) {       //if   matcher ne contient pas la format   
                labelEmail.setVisible(false);
                labelEmail.setText("Email valide !");
                 emailCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
                verificationUserEmail = true;

            } else {
                labelEmail.setVisible(true);
                 emailCheck.setImage(new Image("/pidev/sandy/ressources/img/ "));
                labelEmail.setText("Email Format invalide !");
                // JOptionPane.showMessageDialog(null, "Email Format invalide");
                verificationUserEmail = false;

            }
        }

    }

    @FXML
    private void ConfirmEmail(KeyEvent event) {

        if (email.getText().trim().equals(ccnfirmation_email.getText().trim())) {
            labelconfEmail.setText("les deux email sont confondu");
             ccnfirmation_emailCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
            verificationUserConfirmEmail = true;
        } else {
                     ccnfirmation_emailCheck.setImage(new Image("/pidev/sandy/ressources/img/ "));
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
            labelpasswordlength.setText("Longeur juste");
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
                    labelcontainsLowerCaseLetter.setText("lettre minuscule");
                    containsLowerCaseLetter = true;

                }

                if (Character.isLetter(ch) && Character.isUpperCase(ch)) {// Check for Letters in password
//•	Contains at least 1 upper letter character
                    labelpasswordcontainsUpperCaseLetter.setText("Lettre majuscule");
                    containsUpperCaseLetter = true;

                }
                if (ch == '{' || ch == '(' || ch == '|' || ch == ')' || ch == '+' || ch == '-' || ch == '*' || ch == '-' ||
                        ch == '!' || ch == '@' || ch == '#' || ch == '$' || ch == '%' || ch == '^' || ch == '&' || ch == '*') {
//•	Contains at least 1 special character from the set: !@#$%^&*
                    labelpasswordcontainsSpecialCharacter.setText("Lettre sepcial");
                    containsSpecialCharacter = true;

                }
                System.out.println(containsUpperCaseLetter + "containsUpperCaseLetter\n" + containsLowerCaseLetter + "containsLowerCaseLetter\n"
                        + containsDigit + "containsDigit\n" + containsSpecialCharacter + "containsSpecialCharacter\n\n\n");

                if (containsUpperCaseLetter && containsLowerCaseLetter && containsDigit && containsSpecialCharacter) {
                    labelpassword.setText("Mot de passe valide!");
 passwordCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
                    verificationUserpasword = true;
                }

            }
        } else {
             passwordCheck.setImage(new Image("/pidev/sandy/ressources/img/ "));
            labelpasswordlength.setText("Utilisez au moins huit caractères\n"
                    + " avec des lettres, des chiffres et des symboles");
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
        if (password.getText().trim().equals(Confirmation_password.getText().trim())) {
            labelConfirmationMdp.setText("Mot de passe Conforme!");
             Confirmation_passwordCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
            verificationUserConfirmpasword = true;
        } else {  Confirmation_passwordCheck.setImage(new Image("/pidev/sandy/ressources/img/ "));
            labelConfirmationMdp.setText("Verifier votre\n"
                    + " mot de passe");
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
                 phoneCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
                verificationUserPhone = true;
            } else {             phoneCheck.setImage(new Image("/pidev/sandy/ressources/img/ "));
                labelPhone.setText("invalide number \n"
                        + " Il exist des char");
                verificationUserPhone = false;

            }

        } else {phoneCheck.setImage(new Image("/pidev/sandy/ressources/img/ "));
            labelPhone.setText("Il faut 8 chiffres");
            verificationUserPhone = false;
        }
    }


    @FXML
    private void Pagelogin(ActionEvent event) {

        labelusername.getScene().getWindow().hide();
        loadWindow(getClass().getResource("/pidev/sandy/GUI/Login.fxml"), "Dashboard", null);

    }
//verification 3
    public boolean verifconfirMail(String code) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Confirmez votre inscription");
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

    
 
    
    //verification 1
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
 
        } else {  
            
            
 
            verificationImage = true;
         
        }

        if (verificationImage && verificationUserName && verificationUserEmail && verificationUserConfirmEmail && verificationUserPhone
                && verificationUserpasword && verificationUserConfirmpasword && verificationUserNom
                && verificationUserPrenom && verificationUserSexe && verificationUserRole
                && verificationUserDateNaissance && verificationUserDateInscrit) {

            return true;
        } else {

            return false;
        }

    }
 //verification 2===>appelle a  //verification 3
    @FXML
    private void handleButtonConfirmar(ActionEvent event) {
        User u = new User();
        if (Signup()) {

            String code = serviceMail.generateRandomString();
            System.out.println(code);

//             String to = email.getText().trim();
//            String subject = "Confirmation d'inscription";
//          String message = "Bienvenu " + prenom.getText().trim() + " " + nom.getText().trim() + " dans notre application voici votre code de confirmation " + code + "  Veillez saisir votre code pour confirmer votre inscription";
//            String usermail = "alaa.guissouma@esprit.tn";
//         String passmail = "Skotinka00_";
//           Mailing.send(to, subject, message, usermail, passmail);

            if (verifconfirMail(code) == true) {
                
         
                serviceCryptage cryptage = new serviceCryptage();
                String passCrypt = Password.hashPassword(password.getText().trim());

                u.setUsername(username.getText().trim());
                u.setUsername_canonical(username.getText().trim());
                u.setEmail(email.getText().trim());
                u.setEmail_canonical(ccnfirmation_email.getText().trim());
                u.setEnabled(1);
                u.setPassword(passCrypt);
                
                
                
                if ((String.valueOf(roles.getValue())).equals("Membre")) {
                       u.setRoles("a:1:{i:0;s:11:\"ROLE_MEMBRE\";}");
                } else if ((String.valueOf(roles.getValue())).equals("Admin")) {
                    u.setRoles("a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
                }
                else
                {
                u.setRoles("a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}");
                }
   
      
             ServiceSysdate serviceSysdate = new ServiceSysdate();
                 
                u.setDate_inscription(String.valueOf( serviceSysdate.selectDate() ));
                u.setDate_naissance(date_naissance.getValue().toString());

                u.setNom(nom.getText().trim());
                u.setPrenom(prenom.getText().trim());
                u.setPhone(phone.getText());
               
                //user.setDate_naissance(date_naissance.getValue().format(DateTimeFormatter.BASIC_ISO_DATE));

                // user.setDate_inscription(date_inscrit.getValue().format(DateTimeFormatter.BASIC_ISO_DATE));
                u.setGenre(genre.getValue());
                u.setImage(path);
                
                if (selectedFile != null)
            {
                try { ImporterImageCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
                    //System.out.println(selectedFile.toString());
                    File source = new File(selectedFile.toString());
                    File dest = new File("C:\\wamp64\\www\\bon_paln\\web\\uploads\\images");

                    FileUtils.copyFileToDirectory(source, dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                
                myServices.ajouterUtilisateurs(u);
                
                User user=new User();
                user.setId(1);
                        
                       user= myServices.chercherUtilisateurByUsername(u.getUsername());
                //Badge badge=new Badge();
                //badge.setIdBadge(1);
                //Cadeaux cadeaux=new Cadeaux();
                //cadeaux.setId(82);
                
                //badge=myServices.chercherBadgeById(badge.getIdBadge());
                 //cadeaux=myServices.chercherCadeauxByLibelle( cadeaux.getLibelle());
               
            
                // System.out.println(badge.getLevel()+"===>"+cadeaux.getLibelle());
               // Compte compte=new Compte(0, badge, cadeaux, user, 0);
                System.out.println(user.toString());
                Compte compte=new Compte();
                compte.setId_user(user);
                compte.setPoint_merci(0);
                myServices.ajouterCompteUtilisateurs(compte);
                System.out.println("sssssssss");

                labelusername.getScene().getWindow().hide();

                loadWindow(getClass().getResource("/pidev/sandy/GUI/Login.fxml"), "Login", null);
                ServiceNotification.showNotif("Bienvenu", "Bienvenu dans Bon plan Vous Pouvez Inscrir maintenant");

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
    private void Pageregister(ActionEvent event) {

        labelusername.getScene().getWindow().hide();
        loadWindow(getClass().getResource("/pidev/sandy/GUI/Registration.fxml"), "Registration", null);
        
        

    }


    @FXML
    private void handleButtonCancelar(ActionEvent event) {

        labelusername.getScene().getWindow().hide();
        loadWindow(getClass().getResource("/pidev/sandy/GUI/Login.fxml"), "Registration", null);

    }
    
    
    
    
    
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
           ImporterImage.setImage(image) ;
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
            
            verificationUserNom = true;
            } else {
              nomCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                 verificationUserNom = false;

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
            prenomCheck.setImage(new Image("/pidev/sandy/ressources/img/checkMark.jpg"));
            
            verificationUserPrenom = true;
            } else {
              prenomCheck.setImage(new Image("/pidev/sandy/ressources/img/"));
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