/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Commande;
import entite.Livreur;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;
import service.ServiceCommande;
import service.ServiceLivreur;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class LivreurDashController implements Initializable {

    @FXML
    private TableView<Livreur> table;
    @FXML
    private TableColumn<Livreur, String> nom;
    @FXML
    private TableColumn<Livreur, Button> action;
    
    private ObservableList<Livreur> recdata = FXCollections.observableArrayList();
     ServiceLivreur rs =  new ServiceLivreur();
       
    public ObservableList<Livreur> list = FXCollections.observableArrayList(rs.afficherLivreur());
    @FXML
    private TextField nominput;
    @FXML
    private TextField search;
    @FXML
    private Button stat;
    @FXML
    private AnchorPane parent1;
    @FXML
    private Button ADL;
    @FXML
    private Button logout;
    @FXML
    private Button Gdepotsss;
    @FXML
    private Button Glivreur;
    @FXML
    private Button GProduit;
    @FXML
    private Button GCategorie;
    @FXML
    private Button Gcommande;
    @FXML
    private Button Ghome;
    @FXML
    private Button GF;
    @FXML
    private Button GCategorie1;
    @FXML
    private Button GCategorie11;
 


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // logo.setImage(new Image("/images/logo.png"));
        List<Livreur> listRec= new ArrayList<Livreur>();
        ServiceLivreur rs =  new ServiceLivreur();
        listRec = rs.afficherLivreur();
        recdata.clear();
        recdata.addAll(listRec);
        table.setItems(recdata);
        
        
        nom.setCellValueFactory(
            new PropertyValueFactory<>("nom")
        );
        action.setCellValueFactory(
            new PropertyValueFactory<>("b")
        );
        FilteredList<Livreur> filteredData = new FilteredList<>(list, e -> true);
        search.setOnKeyReleased(e -> {
            search.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Livreur>) livreur -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lower = newValue.toLowerCase();
                    if (livreur.getNom().toLowerCase().contains(lower)) {
                        return true;
                    }

                    return false;
                });
            });
            SortedList<Livreur> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        });
    }    

    private void com(MouseEvent event) throws IOException {
                FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Commandeback.fxml"));
            Parent root= loader.load();
            table.getScene().setRoot(root);
    }

    private void addliv(MouseEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/new livreur.fxml"));
            Parent root= loader.load();
            
            
            
            
            table.getScene().setRoot(root);
    }

    @FXML
    private void update(MouseEvent event) {
        
       Livreur l= table.getSelectionModel().getSelectedItem();
       ServiceLivreur sl = new ServiceLivreur();
       sl.modifierLivreur(l.getId(), nominput.getText());
       List<Livreur> listRec= new ArrayList<Livreur>();
       listRec = sl.afficherLivreur();
        recdata.clear();
        recdata.addAll(listRec);
        table.setItems(recdata);
    }

    @FXML
    private void click(MouseEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
               Livreur l= table.getSelectionModel().getSelectedItem();
       ServiceLivreur sl = new ServiceLivreur();
       sl.supprimerLivreur(l.getId());
       List<Livreur> listRec= new ArrayList<Livreur>();
       listRec = sl.afficherLivreur();
        recdata.clear();
        recdata.addAll(listRec);
        table.setItems(recdata);
    }

    @FXML
    private void btnstatAction(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Statistiques.fxml"));
            Parent root= loader.load();
            table.getScene().setRoot(root);
    }


    @FXML
    private void onmouseexit(MouseEvent event) {
    }

    @FXML
    private void Onmouseenter(MouseEvent event) {
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
       FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
    }

    private void Commandes(MouseEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Commandeback.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
    }

    @FXML
    private void newlivreur(MouseEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/new livreur.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
    }

    private void AddLiv(ActionEvent event) throws IOException {
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/new livreur.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
        
    }

    @FXML
    private void ADL(ActionEvent event) throws IOException {
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/livaddd.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
        
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/login.fxml"));
            Parent root= loader.load();
  
            logout.getScene().setRoot(root);
    }

    @FXML
    private void Gdepotsss(ActionEvent event) throws IOException {
        
                                    FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherdepots.fxml"));
            Parent root= loader.load();  
            Gdepotsss.getScene().setRoot(root);
    }

    @FXML
    private void livreurs(MouseEvent event) {
    }

    @FXML
    private void Glivreur(ActionEvent event) throws IOException {
        
                                    FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/livreurDash.fxml"));
            Parent root= loader.load();
  
            Glivreur.getScene().setRoot(root);
    }

    @FXML
    private void products(MouseEvent event) {
    }

    @FXML
    private void GProduit(ActionEvent event) throws IOException {
        
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/ProduitBack.fxml"));
            Parent root= loader.load();  
            GProduit.getScene().setRoot(root);
    }

    @FXML
    private void categoriess(MouseEvent event) {
    }

    @FXML
    private void GCategorie(ActionEvent event) throws IOException {
        
                                            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/CategorieBack.fxml"));
            Parent root= loader.load();
  
            GCategorie.getScene().setRoot(root);
    }

    @FXML
    private void Gcommande(ActionEvent event) throws IOException {
        
                         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Commandeback.fxml"));
            Parent root= loader.load();  
            Gcommande.getScene().setRoot(root);
    }

    @FXML
    private void Ghome(ActionEvent event) throws IOException {
        
                            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/homeback.fxml"));
            Parent root= loader.load();
  
            Ghome.getScene().setRoot(root);
    }

    @FXML
    private void GF(ActionEvent event) throws IOException {
           
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherfournisseurs.fxml"));
            Parent root= loader.load();  
            GF.getScene().setRoot(root);
    }

       @FXML
    private void buser(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/dash.fxml"));
            Parent root= loader.load();  
            GF.getScene().setRoot(root);
        
    }

    @FXML
    private void bannonce(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/backan.fxml"));
            Parent root= loader.load();  
            GF.getScene().setRoot(root);
        
    }

    
}
