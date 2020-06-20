/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.fournisseurs;
import service.Servicefournisseurs;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficherFournisseursController implements Initializable {

    @FXML
    private ImageView aj;
    @FXML
    private TextField recherche;
    private Button retour;
    @FXML
    private Button btAjArticl;
    @FXML
    private TableColumn<fournisseurs, String> depot_id;
    @FXML
    private TableColumn<fournisseurs, String> nom;
    @FXML
    private TableColumn<fournisseurs, String> prenom;
    @FXML
    private TableColumn<fournisseurs, String> numTel;
    @FXML
    private TableColumn<fournisseurs, String> disponible;
    @FXML
    private TableView table;
    ObservableList<fournisseurs> fournisseurslist;
    static fournisseurs Recup;
    @FXML
    private Button ajouter;
    @FXML
    private Button printpdf;
    @FXML
    private Button ktiba;
    @FXML
    private Button logout;
    @FXML
    private Button Gdepotsss;
    @FXML
    private Button GProduit;
    @FXML
    private Button GFournisseur;
    private Button commandes;
    private Button Home;
    @FXML
    private Button Glivreur;
    @FXML
    private Button GCategorie;
    @FXML
    private Button Gcommande;
    @FXML
    private Button Ghome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        depot_id.setCellValueFactory(new PropertyValueFactory<>("depot_id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        numTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        disponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));
        delete();
        addButtonUpdateToTable();

        fournisseurs a = new fournisseurs();

        Servicefournisseurs ac = new Servicefournisseurs();
        fournisseurslist = FXCollections.observableArrayList(ac.afficherfournisseurs());

        table.setItems(fournisseurslist);

    }

    private void addButtonUpdateToTable() {
        TableColumn<fournisseurs, Void> colBtn = new TableColumn("Modifier fournisseurs");

        Callback<TableColumn<fournisseurs, Void>, TableCell<fournisseurs, Void>> cellFactory = new Callback<TableColumn<fournisseurs, Void>, TableCell<fournisseurs, Void>>() {
            @Override
            public TableCell<fournisseurs, Void> call(final TableColumn<fournisseurs, Void> param) {
                final TableCell<fournisseurs, Void> cell = new TableCell<fournisseurs, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Recup = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierfournisseurs.fxml"));
                                Parent root = loader.load();
                                ModifierFournisseursController rc = loader.getController();
                                btn.getScene().setRoot(root);

                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        table.getColumns().add(colBtn);

    }

    @FXML
    private void retourajouter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterfournisseurs.fxml"));
            Parent root = loader.load();
            AjouterFournisseursController rc = loader.getController();

            ajouter.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }

    }

    private void delete() {
        TableColumn<fournisseurs, Void> colBtn = new TableColumn("Supprimer fournisseurs");

        Callback<TableColumn<fournisseurs, Void>, TableCell<fournisseurs, Void>> cellFactory = new Callback<TableColumn<fournisseurs, Void>, TableCell<fournisseurs, Void>>() {
            @Override
            public TableCell<fournisseurs, Void> call(final TableColumn<fournisseurs, Void> param) {
                final TableCell<fournisseurs, Void> cell = new TableCell<fournisseurs, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {

                            fournisseurs art = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Suppression");
                            alert.setHeaderText(null);
                            alert.setContentText("Voulez vraiment supprimer le refugié  " + art.getNom() + "   " + art.getPrenom() + "   ?");
                            Optional<ButtonType> action = alert.showAndWait();

                            if (action.get() == ButtonType.OK) {
                                Servicefournisseurs ac = new Servicefournisseurs();
                                ac.supprimerfournisseurs(art.getIdFour()); //supprimer T3amlet

                            }

                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherfournisseurs.fxml"));
                                Parent root = loader.load();
                                AfficherFournisseursController rc = loader.getController();
                                btAjArticl.getScene().setRoot(root);

                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());

                            }

                        });

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        table.getColumns().add(colBtn);

    }

    @FXML
    private void testAff(KeyEvent event) {
        depot_id.setCellValueFactory(new PropertyValueFactory<>("depot_id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        numTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        disponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));

        fournisseurs a = new fournisseurs();
        Servicefournisseurs ac = new Servicefournisseurs();

        fournisseurslist = FXCollections.observableArrayList(ac.afficherfournisseurs());

        table.setItems(fournisseurslist);

    }

    @FXML
    private void redirectionAjArticle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterfournisseurs.fxml"));
            Parent root = loader.load();
            AjouterFournisseursController rc = loader.getController();

            btAjArticl.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }


    @FXML
        public void pdf (ActionEvent event) throws FileNotFoundException
    {   Servicefournisseurs srv = new Servicefournisseurs();
        srv.pdf();
    }

    @FXML
    private void recher(ActionEvent event) {
        
        depot_id.setCellValueFactory(new PropertyValueFactory<>("depot_id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        numTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        disponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));
      
           Servicefournisseurs ac =new Servicefournisseurs();       
        
      //  dataEvent=FXCollections.observableArrayList(srv.rechercherEvent(recherche.getText()));
         fournisseurslist=FXCollections.observableArrayList(ac.rechercherEvent(recherche.getText()));
 table.setItems(fournisseurslist);  
        

        
        
    }

    
    
    
    
    
    
    
    
    @FXML
    private void onmouseexit(MouseEvent event) {
    }

    @FXML
    private void Onmouseenter(MouseEvent event) {
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
    private void GProduit(ActionEvent event) throws IOException {
        
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/ProduitBack.fxml"));
            Parent root= loader.load();  
            GProduit.getScene().setRoot(root);

        
    }

    @FXML
    private void GFournisseur(ActionEvent event) throws IOException {

            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherfournisseurs.fxml"));
            Parent root= loader.load();  
            GFournisseur.getScene().setRoot(root);
        
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
    private void Glivreur(ActionEvent event) throws IOException {
                                    FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/livreurDash.fxml"));
            Parent root= loader.load();
  
            Glivreur.getScene().setRoot(root);
    }

    @FXML
    private void GCategorie(ActionEvent event) throws IOException {
                                            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/CategorieBack.fxml"));
            Parent root= loader.load();
  
            GCategorie.getScene().setRoot(root);
    }

    @FXML
    private void livreurs(MouseEvent event) {
    }

    @FXML
    private void products(MouseEvent event) {
    }

    @FXML
    private void categoriess(MouseEvent event) {
    }


}
