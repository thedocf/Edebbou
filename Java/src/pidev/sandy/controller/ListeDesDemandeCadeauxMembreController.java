/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import pidev.sandy.entites.Cadeaux;
import pidev.sandy.entites.Compte;
import pidev.sandy.entites.DemandeCadeau;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class ListeDesDemandeCadeauxMembreController implements Initializable {

    @FXML
    private BorderPane ListeDemandeCadeauxBorderPane;
    @FXML
    private ImageView CadeauxDemande;
    @FXML
    private Button buttonSupprimerDemandeCadeaux;
    @FXML
    private Button buttonModifierDemandeCadeaux;
    @FXML
    private Button buttonConsulteCadeauxDisponible;
    @FXML
    private Button buttonAjouterDemandeCadeaux;
    @FXML
    private Button buttonAfficherListeDemandeCadeaux;
    @FXML
    private TextField searchfield;
    @FXML
    private TableView<DemandeCadeau> tableviewListeDemandeCadeauxMembre;
    @FXML
    private TableColumn<DemandeCadeau, DemandeCadeau> tablecolonneNumero;
    @FXML
    private TableColumn<DemandeCadeau, DemandeCadeau> tablecolonneDemandeStatus;
    @FXML
    private TableColumn<DemandeCadeau, DemandeCadeau> tablecolonneDemandeDescription;
    @FXML
    private TableColumn<DemandeCadeau, DemandeCadeau> tablecolonneDatedemandeCadeau;
    @FXML
    private TableColumn<DemandeCadeau, DemandeCadeau> tablecolonneDemandeMessage;
    @FXML
    private TableColumn<DemandeCadeau, DemandeCadeau> tablecolonneDatedexpiration;

    private List<DemandeCadeau> listDemandeCadeau;//retourner liste de la select
    private ObservableList<DemandeCadeau> ObservablelisteDemandeCadeau;//pour la table view
    @FXML
    private Label LibelleCadeauxDemande;
    @FXML
    private Label StatusCadeauxDemande;
    @FXML
    private Label LibelleDateExperationDemande;
    @FXML
    private Label LibelleDateDemandeDemande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           tableviewListeDemandeCadeauxMembre.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
               try {
                   selectionnerUneDemandeCadeaux(newValue);
               } catch (MalformedURLException ex) {
                   Logger.getLogger(ListeDesDemandeCadeauxMembreController.class.getName()).log(Level.SEVERE, null, ex);
               }
           });
    }

   
    @FXML
    private void createtableviewDemandeCadeaux(ActionEvent event) {

      User loggedUser = pidev.sandy.controller.LoginController.getInstance().getLoggedUser();
        MyServices services = new MyServices();
      User UserConneter=services.chercherUtilisateurByid(loggedUser.getId());
       
        Compte compte = services.chercherUtilisateurByUsernameDansLecompte(UserConneter.getUsername());

        tablecolonneNumero.setCellValueFactory(new PropertyValueFactory<>("idDemandeCadeaux"));//nom reference pour la colonne
        tablecolonneDemandeStatus.setCellValueFactory(new PropertyValueFactory<>("StatusDemandeCadeaux"));//nom reference pour la colonne

        tablecolonneDemandeDescription.setCellValueFactory(new PropertyValueFactory<>("descriptionDemandeCadeaux"));//reference pour la colonne
        tablecolonneDatedemandeCadeau.setCellValueFactory(new PropertyValueFactory<>("datedemandeDemandeCadeaux"));//reference pour la colonne

        tablecolonneDemandeMessage.setCellValueFactory(new PropertyValueFactory<>("messageDemandeCadeaux"));//nom reference pour la colonne
        //reference pour la colonne
        tablecolonneDatedexpiration.setCellValueFactory(new PropertyValueFactory<>("datedexpirationDemandeCadeaux"));//reference pour la colonne

        listDemandeCadeau = services.afficherlisteDemandecadeauxParMembre(compte.getIdCompte());

        ObservablelisteDemandeCadeau = FXCollections.observableArrayList(listDemandeCadeau);//convertir la liste des client en observable liste
        tableviewListeDemandeCadeauxMembre.setItems(ObservablelisteDemandeCadeau);
    }

    private void setNode(Node node) {
        ListeDemandeCadeauxBorderPane.getChildren().clear();
        ListeDemandeCadeauxBorderPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

 
 

    @FXML
    private void modifierDemandeCadeauMembre(ActionEvent event) throws IOException {
            
               MyServices services = new MyServices();
        DemandeCadeau  demandeCadeau = tableviewListeDemandeCadeauxMembre.getSelectionModel().getSelectedItem();

        if (demandeCadeau != null) {
 
                      FXMLLoader loader=new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/modifierDemandeCadeauMembre.fxml"));
        Parent root=loader.load();
        ModifierDemandeCadeauMembreController adcmc=loader.getController();//charger le controleur
        adcmc.setTXMessage(demandeCadeau.getMessageDemandeCadeaux());
        adcmc.setTXDescription(demandeCadeau.getDescriptionDemandeCadeaux());
        adcmc.setTXStatus(demandeCadeau.getStatusDemandeCadeaux());
        Cadeaux cadeaux=services.chercherCadeauxById(demandeCadeau.getCadeaux().getId());
        Compte compte=services.chercherCompteById(demandeCadeau.getMembreDemande().getIdCompte());
        User user=services.chercherUtilisateurByid(compte.getId_user().getId());
        adcmc.setTXMembredemande(user.getUsername());
        adcmc.setTXCadeaux(cadeaux.getLibelle());
        adcmc.setTXidCadeaux(String.valueOf(demandeCadeau.getIdDemandeCadeaux()));
           CadeauxDemande.getScene().setRoot(root);            
           
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner une demande Cadeaux");
            alert.show();
        }
    }

    @FXML
    private void ConsulteCadeauxDisponibleMembre(ActionEvent event) throws IOException {
        
        
         setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ConsulteCadeauxDisponibleMembre.fxml")));
        
    }

    @FXML
    private void AjouterDemandeCadeauxMembre(ActionEvent event) throws IOException {
setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ConsulteCadeauxDisponibleMembre.fxml")));

    }

    
    
    
    
    
    
    
   @FXML
    private void supprimerDemandeCadeau(ActionEvent event) throws MalformedURLException {
        
               MyServices services = new MyServices();
        DemandeCadeau  demandeCadeau = tableviewListeDemandeCadeauxMembre.getSelectionModel().getSelectedItem();

        if (demandeCadeau != null) {
 
                    services.supprimerDemandeCadeau(demandeCadeau);
                 createtableviewDemandeCadeau();                
           
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner une demande Cadeaux");
            alert.show();
        }
        
    }
            MyServices services = new MyServices();
     Image img = null;
    private void createtableviewDemandeCadeau() throws MalformedURLException {

        // User loggedUser = pidev.sandy.Controller.LoginController.getInstance().getLoggedUser();

        //User UserConneter=myServices.chercherUtilisateurByid(loggedUser.getId());
        User UserConneter = services.chercherUtilisateurByid(22);
        Compte compte = services.chercherUtilisateurByUsernameDansLecompte(UserConneter.getUsername());

        tablecolonneNumero.setCellValueFactory(new PropertyValueFactory<>("idDemandeCadeaux"));//nom reference pour la colonne
        tablecolonneDemandeStatus.setCellValueFactory(new PropertyValueFactory<>("StatusDemandeCadeaux"));//nom reference pour la colonne

        tablecolonneDemandeDescription.setCellValueFactory(new PropertyValueFactory<>("descriptionDemandeCadeaux"));//reference pour la colonne
        tablecolonneDatedemandeCadeau.setCellValueFactory(new PropertyValueFactory<>("datedemandeDemandeCadeaux"));//reference pour la colonne

        tablecolonneDemandeMessage.setCellValueFactory(new PropertyValueFactory<>("messageDemandeCadeaux"));//nom reference pour la colonne
        //reference pour la colonne
        tablecolonneDatedexpiration.setCellValueFactory(new PropertyValueFactory<>("datedexpirationDemandeCadeaux"));//reference pour la colonne

        listDemandeCadeau = services.afficherlisteDemandecadeauxParMembre(compte.getIdCompte());

        ObservablelisteDemandeCadeau = FXCollections.observableArrayList(listDemandeCadeau);//convertir la liste des client en observable liste
        tableviewListeDemandeCadeauxMembre.setItems(ObservablelisteDemandeCadeau);
                    
        
 
    }
    
    
      public void selectionnerUneDemandeCadeaux(DemandeCadeau demandeCadeau) throws MalformedURLException//quand on selectionner un user il declanche  
    {
//User UserConneter = services.chercherUtilisateurByid(22);
//        Compte compte = services.chercherUtilisateurByUsernameDansLecompte(UserConneter.getUsername());
        System.out.println("***************************" );
           System.out.println(demandeCadeau.getCadeaux().getId());
         Cadeaux cadeaux=services.chercherCadeauxById(demandeCadeau.getCadeaux().getId());
      
        File file = new File("C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\"+cadeaux.getImage());
        
        System.out.println(file);
        
        LibelleCadeauxDemande.setText(cadeaux.getLibelle());
        StatusCadeauxDemande.setText(demandeCadeau.getStatusDemandeCadeaux());
        LibelleDateExperationDemande.setText(demandeCadeau.getDatedexpirationDemandeCadeaux());
        LibelleDateDemandeDemande.setText(demandeCadeau.getDatedemandeDemandeCadeaux());
         
                     img = new Image(file.toURI().toURL().toString());
                       
                    ImageView photo = new ImageView(img);
                    CadeauxDemande.setFitHeight(200);
                    CadeauxDemande.setFitWidth(200);
                    CadeauxDemande.setImage(img);
         
       

    }

}
