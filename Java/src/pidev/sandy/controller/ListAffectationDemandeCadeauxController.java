/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import pidev.sandy.entites.Boncadeaux;
import pidev.sandy.entites.Cadeaux;
import pidev.sandy.entites.Compte;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class ListAffectationDemandeCadeauxController implements Initializable {

 
    @FXML
    private TableView<Boncadeaux> tableviewListeDemandeAffectation;
    @FXML
    private TableColumn<Boncadeaux, Integer> tablecolonneNumero;
    @FXML
    private TableColumn<Boncadeaux, Integer> tablecolonnePersonne;
    @FXML
   
    private TableColumn<Boncadeaux, String > tablecolonneType;
    @FXML
    private TableColumn<Boncadeaux, String> tablecolonneDate;
    @FXML
    private TableColumn<Boncadeaux, Cadeaux> tablecolonneCadeau;
    @FXML
    private TableColumn<Boncadeaux, Float> tablecolonneCadeauValeur;
    @FXML
    private Button buttonAfficherListeDemandeCadeaxu;
    @FXML
    private Button buttonSupprimerDemandeAffectation;
    @FXML
    private Button buttonAfficgherListeCadeaux;

    /**
     * Initializes the controller class.
     */
    

 
  
    @FXML
    private AnchorPane ListeDesDemandeAffectationCadeaAnchorPane;
  
    @FXML
    private Button buttonAfficherListeDemandeAffectationCadeaxu;
        
      private List<Boncadeaux> listBoncadeaux;//retourner liste de la select
    private ObservableList<Boncadeaux> ObservablelisteBoncadeaux;//pour la table view
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         
    }    


    @FXML
    private void SupprimerDemandeAffectation(ActionEvent event) {
        
               MyServices services = new MyServices();
        Boncadeaux  boncadeaux = tableviewListeDemandeAffectation.getSelectionModel().getSelectedItem();

        if (boncadeaux != null) {
 
                    services.supprimerBonCadeaux(boncadeaux);
                 createtableviewBonCadeaux();                
           
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner un Cadeaux");
            alert.show();
        }
        
    }

    @FXML
    private void createtableviewBonCadeaux() {
        
          MyServices services = new MyServices();
        
          tablecolonneNumero.setCellValueFactory(new PropertyValueFactory<>("idBoncadeaux"));//nom reference pour la colonne
        tablecolonnePersonne.setCellValueFactory(new PropertyValueFactory<>("IdMembreConcerne"));//nom reference pour la colonne
 
                tablecolonneType.setCellValueFactory(new PropertyValueFactory<>("type_bonBoncadeaux"));//reference pour la colonne
        tablecolonneDate.setCellValueFactory(new PropertyValueFactory<>("date_cadeaux"));//reference pour la colonne

          tablecolonneCadeau.setCellValueFactory(new PropertyValueFactory<>("LibelleCadeaux"));//nom reference pour la colonne
        //reference pour la colonne
         tablecolonneCadeauValeur.setCellValueFactory(new PropertyValueFactory<>("ValeurCadeaux"));//reference pour la colonne
        listBoncadeaux = services.afficherlisteBoncadeaux();

        ObservablelisteBoncadeaux = FXCollections.observableArrayList(listBoncadeaux);//convertir la liste des client en observable liste
        tableviewListeDemandeAffectation.setItems(ObservablelisteBoncadeaux);
    }
    
    
    
 
            
        
                   private void setNode(Node node) {
        ListeDesDemandeAffectationCadeaAnchorPane.getChildren().clear();
        ListeDesDemandeAffectationCadeaAnchorPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }
                   
                   

    @FXML
    private void AfficherListeDemandeCadeaxu(ActionEvent event) throws IOException {
        
         setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AfficherDemandeCadeaux.fxml")));
    }

    @FXML
    private void AfficherListedesCadeaux(ActionEvent event) throws IOException {
        
         setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ListeCadeauxAdmin.fxml")));
    }
    
}
