/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import GUI.StatistiqueController;
import Entities.depots;
import Services.Servicedepots;
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
    @FXML 
    private ImageView re;
    @FXML
    private Button retour;
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
      @FXML
    private void retour(ActionEvent event) {
          try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("mainadminscreen.fxml"));
            Parent root= loader.load();
            MainAdminScreenController rc= loader.getController();            
            retour.getScene().setRoot(root);
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

    @FXML
    private void retourstat(ActionEvent event) {
        
        
               Alert alert = new Alert(AlertType.WARNING, 
                        "3asba", 
                        ButtonType.YES, ButtonType.NO);
              try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Statistique.fxml"));
            Parent root= loader.load();
          
            
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
        
       
    }
    
    
    
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
    




}
