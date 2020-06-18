/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import pidev.sandy.entites.Reclamation;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.sound.midi.MidiDevice.Info;
import pidev.sandy.entites.Compte;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
import pidev.sandy.services.ServiceNotification;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class ListeUtlisateurFXMLController implements Initializable {

    MyServices services = new MyServices();
    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<User> tableviewusers;
    @FXML
    private TableColumn<User, String> tablecolonneusernom;
    @FXML
    private TableColumn<User, String> tablecolonneuserid;
    @FXML
    private TableColumn<User, String> tablecolonneuserprenom;
    @FXML
    private TableColumn<User, String> tablecolonneuserUsername;
    @FXML
    private TableColumn<User, String> tablecolonneuserEmail;

    @FXML
    private TableColumn<User, String> tablecolonneuserphone;
    @FXML
    private TableColumn<User, String> tablecolonneuserRole;

    @FXML
    private Label labelusernom;
    @FXML
    private Label labeluserprenom;
    @FXML
    private Button buttonSupprimeruser;
    @FXML
    private Button buttonAjouteruser;
    @FXML
    private Button buttonModifieruser;

    @FXML
    private Button buttonAfficheruser;

    //on va crre un liste et observer liste
    private List<User> listeuser;//retourner liste de la select
    private ObservableList<User> Observablelisteuser;//pour la table view
    @FXML
    private ImageView imageCli;
    @FXML

    private Label labeluserPhone;
    @FXML
    private Label labeluserEmail;
    @FXML
    private Label labeluserRole;
    @FXML
    private Label labelhome;
    @FXML
    private Separator sep;
    @FXML
    private TextField searchfield;
    @FXML
    private MenuItem block;
    @FXML
    private MenuItem deleteUsers;
    @FXML
    private Label labeluserId;
    @FXML
    private ImageView NoteMembre;
    @FXML
    private Label labeluserNote;
    @FXML
    private BorderPane BorederPaneUser;
    public static User useraffecter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //Écouter les changements dans la sélection des éléments TableView
        //ajouter une liste
        //cela ce produit chaque changement chaque selection d'un element
        tableviewusers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        selectionnerunuser(newValue);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(ListeUtlisateurFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

        //selectionner sur le user et passer un new user
    }

    @FXML
    public void createtableviewuser() {

        MyServices services = new MyServices();
        tablecolonneuserid.setCellValueFactory(new PropertyValueFactory<>("Id"));//nom reference pour la colonne
        tablecolonneusernom.setCellValueFactory(new PropertyValueFactory<>("nom"));//nom reference pour la colonne
        //reference pour la colonne
        tablecolonneuserprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));//reference pour la colonne
        tablecolonneuserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));//reference pour la colonne
        tablecolonneuserUsername.setCellValueFactory(new PropertyValueFactory<>("username"));//reference pour la colonne
        tablecolonneuserphone.setCellValueFactory(new PropertyValueFactory<>("phone"));//reference pour la colonne

        tablecolonneuserRole.setCellValueFactory(new PropertyValueFactory<>("roles"));//reference pour la colonne

        /**
         * *********************************************************
         */
        /**
         * *********************************************************
         */
        Callback<TableColumn<User, String>, TableCell<User, String>> cellFactoryRoles
                = //
                new Callback<TableColumn<User, String>, TableCell<User, String>>() {
            @Override
            public TableCell call(final TableColumn<User, String> param) {
                final TableCell<User, String> cell = new TableCell<User, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {

                            ImageView imagev;
                            if (item.equals("a:1:{i:0;s:11:\"ROLE_MEMBRE\";}")) {
                                setText("Membre");

                            }
                            if (item.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
                                setText("Admin");
                            } else if (item.equals("a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}")) {
                                setText("Partenaire");
                            }

                        }
                    }
                };
                return cell;
            }
        };

        tablecolonneuserRole.setCellFactory(cellFactoryRoles);
        /**
         * *********************************************************
         */

        listeuser = services.afficherlisteUtilisateurs();

        Observablelisteuser = FXCollections.observableArrayList(listeuser);//convertir la liste des client en observable liste
        tableviewusers.setItems(Observablelisteuser);

    }

    public void selectionnerunuser(User user) throws MalformedURLException//quand on selectionner un user il declanche
    {

        Compte compte = new Compte();
        compte = services.chercherUtilisateurByUsernameDansLecompte(user.getUsername());

        if (compte == null) {
            labeluserNote.setText(String.valueOf(0));

        } else {
            labeluserNote.setText(String.valueOf(compte.getPoint_merci()));
        }

        labelusernom.setText(user.getNom());
        labeluserprenom.setText(user.getPrenom());
        labeluserEmail.setText(user.getEmail());
        labeluserPhone.setText(user.getPhone());
        labeluserId.setText(String.valueOf(user.getId()));
        String role = user.getRoles();
        if (role.equals("a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}")) {
            labeluserRole.setText("Partenaire");
        } else if (role.equals("a:1:{i:0;s:11:\"ROLE_MEMBRE\";}")) {
            labeluserRole.setText("Membre");
        } else {
            labeluserRole.setText("Admin");
        }

        File file = new File("C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + user.getImage());
        javafx.scene.image.Image img = new javafx.scene.image.Image(file.toURI().toURL().toString());

        imageCli.setImage(img);
    }

    @FXML
    public void ajouterUtilisateur() throws IOException {

        MyServices services = new MyServices();
        User user = new User();
        System.out.println(user.toString());
        //connecter au fb bech nimchi lil set client fil controlleur AjouterPersonne2Controller
        boolean buttonConfimClicked = showAjouterPersonneFXML(user);//cette qui va ouvrir cette page (dmender cete methode) 

        if (buttonConfimClicked) {

            /* myServices.ajouterUtilisateurs(u);
                
                User user=new User();
                user.setId(1);
                        
                       user= myServices.chercherUtilisateurByUsername(u.getUsername());
                Badge badge=new Badge();
                badge.setIdBadge(1);
                Cadeaux cadeaux=new Cadeaux();
                cadeaux.setId(8);
                
                badge=myServices.chercherBadgeById(badge.getIdBadge());
               
            
                 System.out.println(badge.getLevel()+"===>"+cadeaux.getLibelle());
                Compte compte=new Compte(0, badge, cadeaux, user, 0);
                
                myServices.ajouterCompteUtilisateurs(compte);
                System.out.println("sssssssss");

                labelusername.getScene().getWindow().hide();*/
            services.ajouterUtilisateurs(user);
            ServiceNotification.showNotif("Felicitaion ", "Vous Avez ajouter ce membre" + "" + user.getUsername() + " avec sucées");
            createtableviewuser();

        }

    }

    //jina ba3d mabdilna fil setPersonne
    //quand je cliquer sur le button il appale cette methode(showAjouterPersonneFXML) pour capable de montre la clase
    //(showAjouterPersonneFXML) ili feha dialoge pour tout configure
    //quand je fait setPeronne je recoit comme base les gens qui venait les chamngement du button qui on deja les donne
    //le controller va frapper le persone et informer les 2eme autre champs ili fil setPersonne
    @FXML
    public void modifierUtilisateur() throws IOException {

        User user = tableviewusers.getSelectionModel().getSelectedItem();

        if (user != null) {

            boolean buttonConfimClicked = showAjouterPersonneFXML(user);//cette qui va ouvrir cette page (dmender cete methode) 
            if (buttonConfimClicked) {

                services.modifierUtilisateurs(user);
                ServiceNotification.showNotif("Felicitaion ", "Vous Avez modifier ce membre" + "" + user.getUsername() + " avec sucées");
                createtableviewuser();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner un personne");
            alert.show();
        }

    }

    @FXML
    public void supprimerUtilisateur() throws IOException {
        MyServices services = new MyServices();
        User user = tableviewusers.getSelectionModel().getSelectedItem();

        if (user != null) {

            services.supprimerUtilisateur(user);
            createtableviewuser();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner un personne");
            alert.show();
        }

    }

    //je ne met pas l'anotation car cette methode ne sera pas appele a partir d'un composant viseul
    //ce sera appele en intterne par l'event mettre en ouvre le click de la buuton de changement et
    //par l'event qui implement un clic sur le buuton qui  va (insere,modifier,delet)==>
    //il faut ouvrir un fenetre et le methode
    private boolean showAjouterPersonneFXML(User user) throws IOException {
        /**
         * ***********************1ere etape*******************************
         */

        FXMLLoader loader = new FXMLLoader();//qui va charger la fichier de type fxml
        loader.setLocation(AjouterUilisateurController.class.getResource("/pidev/sandy/GUI/AjouterUilisateur.fxml"));//ouvrire ce fenetre
        AnchorPane page = (AnchorPane) loader.load();//d'ou et cree un attribut page qui va enregister mon loder deja telechagere
        //==> mon fichier et deje tawa en memoire
        /**
         * ***********************2eme etape(configuration pour new fenter*******************************
         */

        //TAWA POUR en voie cette page il faut cree un stage
        Stage dialogeStage = new Stage();
        //a partir de ce stage j'ai cree un titre pour le client
        dialogeStage.setTitle("Gestion d'utilisateur");

        //craetion de la scene et passe l'attrbut page qui le petite ecran de la l'affichage qui on a telechager
        Scene scene = new Scene(page);

        //charger la scene
        dialogeStage.setScene(scene);
        /**
         * ***********************3eme etape*******************************
         */
//cree un attribut unique et nous avons  utliser cete endroit pour appele le controller de cette (AjouterPersonneFXML)
//on va tawa lil AjouterPersonne2Controller donner lui 2 clics(pour voire les attribut stage,personne,confiramation bbuton)
        AjouterUilisateurController controller = loader.getController();

//tawa on va vers les methode ili 3amlou applele lil methode hethi lekbira
        controller.setStage(dialogeStage);
        controller.setUser(user);//ce client passer en parametre passer paR le controlleur

        /**
         * ***********************4eme etape enfin montre l'ecran a l'user
         * attend l'insertion des donne*******************************
         */
//je ne encore envoyoer cet ecran il va aparaitre a partir
        dialogeStage.showAndWait();//je veut lui montre et attender que l'user fermer cette petite ecran plus tard

        /**
         * **********************5eme etape attendre l'user fermer le fentre***************************
         */
//on va modifier setClient pour //on va modifier setClient pour  
        return controller.isButtonConfimClicked();
    }

    @FXML
    private void block(ActionEvent event) {
    }

    @FXML
    private void deleteUsers(ActionEvent event) {
    }

    @FXML
    private void NoteMembre(MouseEvent event) throws IOException {
        if (tableviewusers.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez choisir le membre a noter");
            alert.show();
        } else {
            useraffecter = tableviewusers.getSelectionModel().getSelectedItem();

            setNode((FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/Noter_Membre.fxml"))));
        }

    }

    private void setNode(Node node) {
        BorederPaneUser.getChildren().clear();
        BorederPaneUser.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

  

}
