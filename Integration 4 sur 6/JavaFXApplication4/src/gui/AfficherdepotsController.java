/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import gui.StatistiqueController;
import entite.depots;
import service.Servicedepots;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert.AlertType;
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
import javafx.util.Callback;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficherdepotsController implements Initializable {

    static int i;
    public int t;
    public int iduser;
    public double xx;
    @FXML 
    private ImageView aj;
    private Button btAjArticl;
 
  @FXML
    private TableColumn<depots, String> entreprise;
  @FXML
    private TableColumn<depots, Integer> surface;
  @FXML
    private TableColumn<depots, String> ville;
  @FXML
    private TableColumn<depots, Integer> capacite;
  @FXML
    private TableColumn<depots, String> description;
  @FXML
    private TableView table;
  ObservableList<depots> depotslist ;
  static depots Recup ;
  @FXML
  private Button ajouter;
  @FXML
  private Button stat;
    @FXML
    private Button triPro;
    @FXML
    private Button triPro1;
    private Button Home;
    @FXML
    private Button logout;
    @FXML
    private Button Gdepotsss;
    @FXML
    private Button GProduit;
    private Button GFournisseur;
    private Button commandes;
    @FXML
    private Button Glivreur;
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
      
       
        entreprise.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
        surface.setCellValueFactory(new PropertyValueFactory<>("surface"));
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

          delete();
         addButtonUpdateToTable();
        
                depots a=new depots();

        Servicedepots ac =new Servicedepots();
        depotslist=FXCollections.observableArrayList(ac.afficherdepots());
      
        
        table.setItems(depotslist);
        
       
       
    }   
    
    
    private void addButtonUpdateToTable() {
        TableColumn<depots, Void> colBtn = new TableColumn("Modifier Depots");

        Callback<TableColumn<depots, Void>, TableCell<depots, Void>> cellFactory = new Callback<TableColumn<depots, Void>, TableCell<depots, Void>>() {
            @Override
            public TableCell<depots, Void> call(final TableColumn<depots, Void> param) {
                final TableCell<depots, Void> cell = new TableCell<depots, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Recup=getTableView().getItems().get(getIndex());
                                 try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierdepots.fxml"));
                                Parent root = loader.load();
                              ModifierdepotsController rc = loader.getController();
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
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ajouterDepots.fxml"));
            Parent root= loader.load();
            AjouterDepotsController rc= loader.getController();
            
            ajouter.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
        
    }
 
    private void delete() {
        TableColumn<depots, Void> colBtn = new TableColumn("Supprimer Depots");

        Callback<TableColumn<depots, Void>, TableCell<depots, Void>> cellFactory = new Callback<TableColumn<depots, Void>, TableCell<depots, Void>>() {
            @Override
            public TableCell<depots, Void> call(final TableColumn<depots, Void> param) {
                final TableCell<depots, Void> cell = new TableCell<depots, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {

                            depots art = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Suppression");
                            alert.setHeaderText(null);
                            alert.setContentText("Voulez vraiment supprimer le depot " + art.getEntreprise() + " ?  ");
                            Optional<ButtonType> action = alert.showAndWait();

                            if (action.get() == ButtonType.OK) {
                                Servicedepots ac = new Servicedepots();
                                ac.supprimerdepots(art.getId()); //supprimer T3amlet

                            }

                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherdepots.fxml"));
                                Parent root = loader.load();
                                AfficherdepotsController rc = loader.getController();
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
        
        entreprise.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
        surface.setCellValueFactory(new PropertyValueFactory<>("surface"));
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
       
        
        
        
        
        
       
        depots a=new depots();
        Servicedepots ac =new Servicedepots();
        
  //   depotslist=FXCollections.observableArrayList(ac.(tfAuteurRechercher.getText()));

    depotslist=FXCollections.observableArrayList(ac.afficherdepots());
        
        table.setItems(depotslist);
       
        
        
         
        

    

   
}
    private void redirectionAjArticle(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ajouterdepots.fxml"));
            Parent root= loader.load();
            AjouterDepotsController rc= loader.getController();
            
            btAjArticl.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    
    
    /*
    public void recher(ActionEvent event) {
         
 
        entreprise.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
        surface.setCellValueFactory(new PropertyValueFactory<>("surface"));
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
      
           Servicedepots ac =new Servicedepots();       
        
      //  dataEvent=FXCollections.observableArrayList(srv.rechercherEvent(recherche.getText()));
         depotslist=FXCollections.observableArrayList(ac.rechercherEvent(recherche.getText()));
 table.setItems(depotslist);  

   
}
*/


    
    
    
private ObservableList<depots> getSortedDepot() throws SQLException {
        Servicedepots su = new Servicedepots();
        return su.triProBySurface();
    }
private ObservableList<depots> getSortedDepot2() throws SQLException {
        Servicedepots su = new Servicedepots();
        return su.triProByCapacite();
    }
    
        @FXML    
    private void triPro(ActionEvent event) throws SQLException {
       entreprise.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
        surface.setCellValueFactory(new PropertyValueFactory<>("surface"));
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
       
     
      
            table.setItems(getSortedDepot());
                                                                        TrayNotification tray = new TrayNotification("succès", " Tri par Surface effectué", SUCCESS);
                                    tray.showAndWait();

    }

    @FXML
    private void triProoo(ActionEvent event) throws SQLException {
        
       entreprise.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
        surface.setCellValueFactory(new PropertyValueFactory<>("surface"));
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
       
     
      
            table.setItems(getSortedDepot2()); 
                                                            TrayNotification tray = new TrayNotification("succès", " Tri par Capacaite effectué", SUCCESS);
                                    tray.showAndWait();
                               

    }

    @FXML
    private void retourstatssss(ActionEvent event) {
        
               
               Alert alert = new Alert(AlertType.WARNING, 
                        "erreur", 
                        ButtonType.YES, ButtonType.NO);
              try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Statistique.fxml"));
            Parent root= loader.load();
          
            
            stat.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
        
        
        
        
        
        
        
    }

    private void Home(ActionEvent event) throws IOException {
                
                    FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/homeback.fxml"));
            Parent root= loader.load();
  
            Home.getScene().setRoot(root);
        
        
        
        
    }

    @FXML
    private void onmouseexit(MouseEvent event) {
    }

    @FXML
    private void Onmouseenter(MouseEvent event) {
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/login.fxml"));
            Parent root= loader.load();  
            logout.getScene().setRoot(root);
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

    private void GFournisseur(ActionEvent event) throws IOException {

            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherfournisseurs.fxml"));
            Parent root= loader.load();  
            GFournisseur.getScene().setRoot(root);
        
    }

    private void commandes(ActionEvent event) throws IOException {
        
                         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Commandeback.fxml"));
            Parent root= loader.load();  
            commandes.getScene().setRoot(root);
        
        
        
        
    }

    @FXML
    private void Glivreur(ActionEvent event) throws IOException {
                
                         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/livreurDash.fxml"));
            Parent root= loader.load();  
            commandes.getScene().setRoot(root);
      
        
    }

    @FXML
    private void GCategorie(ActionEvent event) throws IOException {
        
        
                         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/CategorieBack.fxml"));
            Parent root= loader.load();  
            commandes.getScene().setRoot(root);
      
    }

    private void home(ActionEvent event) throws IOException {
        
                         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/homeback.fxml"));
            Parent root= loader.load();  
            commandes.getScene().setRoot(root);
        
        
        
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
