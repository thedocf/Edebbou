/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import java.io.File;
import java.lang.reflect.Member;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import pidev.sandy.entites.BonPlan;
import pidev.sandy.entites.Boncadeaux;
import pidev.sandy.entites.Cadeaux;
import pidev.sandy.entites.Compte;
import pidev.sandy.entites.Mailing;
import pidev.sandy.entites.Personne;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
import pidev.sandy.services.ServiceNotification;
import pidev.sandy.services.ServiceSysdate;
import pidev.sandy.services.serviceCryptage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class AffecterCadeauxController implements Initializable {

     
    
    
    
    
    
    private List<String> listMembre =new ArrayList<String>();
    private List<User> listMembreChoisie =new ArrayList<User>();
    private ObservableList<String> ObservablelistMembre;
    private ObservableList<User> ObservablelistMembreChoisie;
      private ObservableList<String> ObservableCadeauxChoisie;
    @FXML
    private DatePicker DateAffectationCadeaux;
    @FXML
    private TableView<User> tableViewMembre;
    @FXML
    private TableColumn<User, String> tableColumnUsername;
    @FXML
    private TableColumn<User, String> tableColumnEmail;
    @FXML
    private ComboBox<String> comboBoxSelectionneMembre;
    @FXML
    private TextField TFTypeBon;
    @FXML
    private ComboBox<String > comboBoxSelectionneCadeau;
    @FXML
    private Button buttonConfirmer;
    @FXML
    private Button BtAnuller;

     

  
   

   
    
    
     
    
          private Stage stage;//controle de classe(annuler ou fermer) stage(serie)
    private boolean buttonConfimClicked = false;//nous informer est ce que on clicker sur confimer ou annuler
    private Cadeaux cadeaux;
    private Boncadeaux boncadeaux;
    MyServices myServices=new MyServices();
    @FXML
    private ImageView imageCadeau;
    @FXML
    private ImageView imageUser;
    
 
 
    
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
   public Cadeaux getCadeaux() {
        return cadeaux;
    }
   public Boncadeaux getBoncadeaux() {
        return boncadeaux;
    }
   
   public void setBoncadeaux(Boncadeaux boncadeaux) {
        
        this.boncadeaux = boncadeaux;
        
        
             
   }
    
    public void setCadeaux(Cadeaux cadeaux) {
        
        this.cadeaux = cadeaux;
     
        
              ObservableCadeauxChoisie=FXCollections.observableArrayList(cadeaux.getLibelle());
                          this.comboBoxSelectionneCadeau.setItems(ObservableCadeauxChoisie);
        


        //je suis interesser quand j'ai un new client
        //accepter les champs technique avec cette champs
      /*  this.TXValeurPointCadeaux.setText(cadeaux.getPrix_reel());
        this.email.setText(user.getEmail());
        this.ccnfirmation_email.setText(user.getEmail_canonical());
        this.Confirmation_password.setText(user.getPassword());
        this.password.setText(user.getPassword());
        
        password.setDisable(true);
        Confirmation_password.setDisable(true);
        date_inscrit.setDisable(true);
        //this.password.setText(user.getPassword());

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

        this.phone.setText(user.getPhone());*/


        // this.date_inscrit.setValue(String.valueOf()  );
        // this.date_naissance.setValue(user.getDate_naissance());
        //  this.date.setText(String.valueOf(personne.getDate_inscrit().format(DateTimeFormatter.ofPattern(pattern), args)));
//tawan bech nifhmou 3lech 3malna les 2 phrases ili just fou9i
//imchi ListeUtilisateurFXMLController tawa tifhim
    }
    
      MyServices services = new MyServices();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
              TFTypeBon.setEditable(false);
             tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("username"));//reference pour la colonne
        tableColumnUsername.setCellValueFactory(new PropertyValueFactory<>("email"));//reference pour la colonne
         
    TFTypeBon.setText("Affecter");
        
         
    }    
  @FXML
      public void ChargerComboBoxListeDesMembreConcerner() throws MalformedURLException
            {
                     String ROLE="a:1:{i:0;s:11:\"ROLE_MEMBRE\";}";
                
                     Cadeaux cadeaux=services.chercherCadeauxByLibelle(comboBoxSelectionneCadeau.getSelectionModel().getSelectedItem());
                     File file=new File("C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\"+cadeaux.getImage());
                     javafx.scene.image.Image img = new javafx.scene.image.Image(file.toURI().toURL().toString());
                     imageCadeau.setImage(img);
                     
            listMembre=services.afficherlisteMembreAPointMerciSuffisant(ROLE,cadeaux.getValeur_point());
                                System.out.println(listMembre.toString() );
                     if (listMembre.size()==0) {
                           ServiceNotification.showNotif("Stop", "Aucun Membre à le nombre "
                                 + "de point supperieure ou egale au point du cadeaux" );
                         
 
                } else {
              
                        ServiceNotification.showNotif("Stop", "Choisir un   " );
                         
                     
                     ObservablelistMembre=FXCollections.observableArrayList(listMembre);
                      comboBoxSelectionneMembre.setItems(ObservablelistMembre);
                     }  
        
        
 
            }
      
      
      
                   
    
      
      
   
     ServiceSysdate sys = new ServiceSysdate();
    @FXML
    private void handleButtonConfirmer(ActionEvent event) throws MalformedURLException {
//            TrayNotification tray = new TrayNotification("Stop", "Aucun Membre à le nombre "
//                                 + "de point supperieure ou egale au point du cadeaux " , NotificationType.ERROR);
//            
//                tray.setAnimationType(AnimationType.POPUP);
//            tray.showAndDismiss(Duration.ONE);
        
          Alert alert = new Alert(Alert.AlertType.ERROR);
        
        
        
        if (comboBoxSelectionneCadeau.getValue()==null)
        {
            alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez choisir le cadeaux à affecter");
                            alert.show();
                            comboBoxSelectionneCadeau.requestFocus();
        }
        
            if (comboBoxSelectionneMembre.getValue()==null)
        {
            alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez choisir le membre");
                            alert.show();
                            comboBoxSelectionneCadeau.requestFocus();
        }
     
            
     
        else if (DateAffectationCadeaux.getEditor().getText().equals(""))
        {
         alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez enter la date de votre commande");
                            alert.show();
                            DateAffectationCadeaux.requestFocus();   
        }
        
        else
        {
            Date dateo = Date.valueOf(DateAffectationCadeaux.getValue());
            if (dateo.before(sys.selectDate()))
            {
                
              System.out.println(DateAffectationCadeaux.getEditor().getText());
            System.out.println(sys.selectDate());
             alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez verifier que  la date de votre commande est superieur à la date actuelle");
                            alert.show();
                            DateAffectationCadeaux.requestFocus();
                            
        }
            else
            {
                String username = comboBoxSelectionneMembre.getSelectionModel().getSelectedItem();
         
           Cadeaux cadeaux=services.chercherCadeauxByLibelle(comboBoxSelectionneCadeau.getSelectionModel().getSelectedItem());
       
                User user = myServices.chercherUtilisateurByUsername(username);
                Compte compte = myServices.chercherUtilisateurByUsernameDansLecompte(username);
        
                              cadeaux.setQuantite_actuel(cadeaux.getQuantite_actuel() - 1);
                compte.setPoint_merci((int) (compte.getPoint_merci()- cadeaux.getValeur_point()));  
                 
                 boncadeaux.setCadeaux(cadeaux);
                 boncadeaux.setMembreConcerne(compte);
                 boncadeaux.setDescriptionBoncadeaux("Affectation du cadeaux");
                 boncadeaux.setType_bonBoncadeaux(TFTypeBon.getText());
                 boncadeaux.setDate_cadeaux(DateAffectationCadeaux.getValue().toString());
                 
                 System.out.println("Bon ====>"+boncadeaux.toString());
                 
            
          
                buttonConfimClicked = true;
 
            stage.close();

 
               
          
                
                  
                     
                
            }}
        }

    

    @FXML
    private void handleButtonAnnuler(ActionEvent event) {
    }

    @FXML
    private void ChargerTableauxDesMembreConcerner(ActionEvent event) throws MalformedURLException {
      
     
                      
            
      
                      String username = comboBoxSelectionneMembre.getSelectionModel().getSelectedItem();
                User user = myServices.chercherUtilisateurByUsername(username);
 
           
          listMembreChoisie.add(user);
  
           
                     ObservablelistMembreChoisie=FXCollections.observableArrayList(listMembreChoisie);
                 
                      
                     tableViewMembre.setItems(ObservablelistMembreChoisie);     listMembreChoisie.clear();
    }

    @FXML
    private void keyNom(KeyEvent event) {
    }
      
       
 
 
    
}
