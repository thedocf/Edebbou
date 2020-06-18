/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import java.io.File;
import pidev.sandy.entites.DemandeCadeau;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev.sandy.entites.Boncadeaux;
import pidev.sandy.entites.Cadeaux;
import pidev.sandy.entites.Compte;
import pidev.sandy.entites.Mailing;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
import pidev.sandy.services.ServiceSysdate;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class AfficherDemandeCadeauxController implements Initializable {
ServiceSysdate sys = new ServiceSysdate();
    @FXML
    private AnchorPane ListeDesDemandeCadeaAnchorPane;
    @FXML
    private AnchorPane anchorListePatissier;
    @FXML
    private TableView<DemandeCadeau> tableviewListeDemandeAffectation;
    @FXML
    private TableColumn<DemandeCadeau, String> tablecolonneNumero;
    @FXML
    private TableColumn<DemandeCadeau, String> tablecolonneDateDemande;
    @FXML
    private TableColumn<DemandeCadeau, Cadeaux > tablecolonnePhotoMembreDemande;
    @FXML
    private TableColumn<DemandeCadeau, String> tablecolonneMembreDemande;
    @FXML
    private TableColumn<DemandeCadeau, String> tablecolonnetablecolonneMembrPoint;
    @FXML
    private TableColumn<DemandeCadeau, Integer> tablecolonnetablecolonneMembreId;
    @FXML
    private Button buttonAccepteDemande;
   
    @FXML
    private Button buttonAttenteDemande;
    @FXML
    private Button buttonRetourBonCadeaux;

        
      private List<DemandeCadeau> listDemandeCadeau;//retourner liste de la select
    private ObservableList<DemandeCadeau> ObservablelisteDemandeCadeau;//pour la table view
    @FXML
    private TableColumn<DemandeCadeau, DemandeCadeau> tablecolonnetablecolonneDemandeCadeau;
    @FXML
    private TableColumn<DemandeCadeau, String> tablecolonnetablecolonneDemandeStatus1;
    @FXML
    private Button buttonRefuserDemande;
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createtableviewDemandeCadeau();
    }    
    private void createtableviewDemandeCadeau() {
        
          MyServices services = new MyServices();
        
          tablecolonneNumero.setCellValueFactory(new PropertyValueFactory<>("idDemandeCadeaux"));//nom reference pour la colonne
        tablecolonneDateDemande.setCellValueFactory(new PropertyValueFactory<>("datedemandeDemandeCadeaux"));//nom reference pour la colonne
 
                tablecolonnePhotoMembreDemande.setCellValueFactory(new PropertyValueFactory<>("Cadeaux"));//reference pour la colonne
      
                                 /************************************************************/
                
                   Callback<TableColumn<DemandeCadeau, Cadeaux>, TableCell<DemandeCadeau, Cadeaux>> cellFactoryMembreImage
                = //
                new Callback<TableColumn<DemandeCadeau, Cadeaux>, TableCell<DemandeCadeau, Cadeaux>>() {
            @Override
            public TableCell call(final TableColumn<DemandeCadeau, Cadeaux> param) {
                final TableCell<DemandeCadeau, Cadeaux> cell = new TableCell<DemandeCadeau, Cadeaux>() {

                    @Override
                    public void updateItem(Cadeaux item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            
                            Cadeaux cadeaux=services.chercherCadeauxById((item.getId()));
                            System.out.println(item+"user.toString()"+cadeaux.toString());
                       
                   
                                    
      
        File file = new File("C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + cadeaux.getImage());
        javafx.scene.image.Image img;
        try {
            img = new javafx.scene.image.Image(file.toURI().toURL().toString());
           ImageView imagev = new ImageView();
           imagev.setImage(img);
           
               imagev.setFitHeight(50);
                                imagev.setFitWidth(150);
            setGraphic(imagev);
        } catch (MalformedURLException ex) {
            System.out.println(ex.toString());
        }
                            
                            

                        }
                    }
                };
                return cell;
            }
        };
        
       tablecolonnePhotoMembreDemande.setCellFactory(cellFactoryMembreImage);
                
                
 
                
                
                
                
                
                /*********************************************************************/
                
       tablecolonnetablecolonneMembreId.setCellValueFactory(new PropertyValueFactory<>("IdMembreDemande"));//reference pour la colonne
       
       
                 /************************************************************/
                
                   Callback<TableColumn<DemandeCadeau, Integer>, TableCell<DemandeCadeau, Integer>> cellFactoryCadeauImage
                = //
                new Callback<TableColumn<DemandeCadeau, Integer>, TableCell<DemandeCadeau, Integer>>() {
            @Override
            public TableCell call(final TableColumn<DemandeCadeau, Integer> param) {
                final TableCell<DemandeCadeau, Integer> cell = new TableCell<DemandeCadeau, Integer>() {

                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            
                            User user=services.chercherUtilisateurByid( (item));
                            System.out.println(item+"user.toString()"+user.toString());
                       
                   
                                    
      
        File file = new File("C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + user.getImage());
        javafx.scene.image.Image img;
        try {
            img = new javafx.scene.image.Image(file.toURI().toURL().toString());
           ImageView imagev = new ImageView();
           imagev.setImage(img);
           
               imagev.setFitHeight(50);
                                imagev.setFitWidth(150);
            setGraphic(imagev);
        } catch (MalformedURLException ex) {
            System.out.println(ex.toString());
        }
                            
                            

                        }
                    }
                };
                return cell;
            }
        };
        
       tablecolonnetablecolonneMembreId.setCellFactory(cellFactoryCadeauImage);
                
                
 
                
                
                
                
                
                /*********************************************************************/
                
                

          tablecolonnetablecolonneMembrPoint.setCellValueFactory(new PropertyValueFactory<>("PointMembreDemande"));//nom reference pour la colonne
        //reference pour la colonne
         tablecolonnetablecolonneDemandeCadeau.setCellValueFactory(new PropertyValueFactory<>("LibelleCadeaux"));//reference pour la colonne
         tablecolonnetablecolonneDemandeStatus1.setCellValueFactory(new PropertyValueFactory<>("StatusDemandeCadeaux"));//reference pour la colonne
       
                  /************************************************************/
           Callback<TableColumn<DemandeCadeau, String>, TableCell<DemandeCadeau, String>> cellFactoryStatus
                = //
                new Callback<TableColumn<DemandeCadeau, String>, TableCell<DemandeCadeau, String>>() {
            @Override
            public TableCell call(final TableColumn<DemandeCadeau, String> param) {
                final TableCell<DemandeCadeau, String> cell = new TableCell<DemandeCadeau, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            
                               Button Accepte = new Button("Accepte");
                                   Accepte.setStyle("-fx-background-color: #74DF00");
                               Button Reffuse = new Button("Reffuse");
                                  Reffuse.setStyle("-fx-background-color: #FE2E2E");
                               Button Attente = new Button("Attente");
                                  Attente.setStyle("-fx-background-color: #58ACFA");

                  
                            if (item.equals("Accepte")) {
                         
                                setGraphic(Accepte);

                            }
                            if (item.equals("Attente")) {
                       
                            setGraphic(Attente);
                            } else if (item.equals("Reffuse")) {
                                 setGraphic(Reffuse);
                            }

                        }
                    }
                };
                    cell.setOnMousePressed((MouseEvent event2)
                        -> {System.out.println(""
                                + "sdsdsdsdd");
                    if (event2.getClickCount() > 1) {
                        if (tableviewListeDemandeAffectation.getSelectionModel().getSelectedItem().getCadeaux().getImage()!= null) {
                            Stage window = new Stage();
//
                            window.setMinWidth(250);
                            ImageView imagevPOPUP = new ImageView(new Image(tableviewListeDemandeAffectation.getSelectionModel().getSelectedItem().getCadeaux().getImage()));
                            imagevPOPUP.setFitHeight(576);
                            imagevPOPUP.setFitWidth(1024);

                            VBox layout = new VBox(10);
                            layout.getChildren().addAll(imagevPOPUP);
                            layout.setAlignment(Pos.CENTER);

                            //Display window and wait for it to be closed before returning
                            Scene scene = new Scene(layout);
                            window.setScene(scene);
                            window.show();

                        }
                    }
 
                });
                return cell;
            }
        };
        
        tablecolonnetablecolonneDemandeStatus1.setCellFactory(cellFactoryStatus);
         /************************************************************/
         
         listDemandeCadeau = services.afficherlisteDemandecadeaux();

        ObservablelisteDemandeCadeau = FXCollections.observableArrayList(listDemandeCadeau);//convertir la liste des client en observable liste
        tableviewListeDemandeAffectation.setItems(ObservablelisteDemandeCadeau);
    }
    @FXML
    private void AccepteDemande(ActionEvent event) throws ParseException {
        
        
               MyServices services = new MyServices();
        DemandeCadeau  demandeCadeau = tableviewListeDemandeAffectation.getSelectionModel().getSelectedItem();

        if (demandeCadeau != null) {
            
            Cadeaux cadeaux=new Cadeaux();
            Compte compte=new  Compte();
            
             cadeaux=services.chercherCadeauxById(demandeCadeau.getCadeaux().getId());
            compte=services.chercherCompteById(demandeCadeau.getMembreDemande().getIdCompte());
            User user=services.chercherUtilisateurByid(demandeCadeau.getMembreDemande().getId_user());
            System.out.println("user.toString()==>"+user.toString());
//            System.out.println("Compte"+compte.toString());
//           System.out.println(compte.getId_user().getEmail());
//               System.out.println(compte.getId_user().getNom());
//               System.out.println(compte.getId_user().getPrenom());
//            
             Alert alert = new Alert(Alert.AlertType.ERROR);
             
             
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = demandeCadeau.getDatedexpirationDemandeCadeaux();

 

            Date dateo = formatter.parse(dateInString);
 
             
              
            if (dateo.after(sys.selectDate()))
            {
                
              System.out.println(dateo);
            System.out.println(sys.selectDate());
            
            
                System.out.println("Date experation apres la date d'aujourd'hui ");
            
          
                       
                if (cadeaux.getValeur_point() < compte.getPoint_merci()) {
                    
                    System.out.println("cadeaux.getValeur_point() < compte.getPoint_merci()");
                       demandeCadeau.setStatusDemandeCadeaux("Accepte");
                       demandeCadeau.setMessageDemandeCadeaux("Ton demande  est Accepter");
                 
                        
                          String to = user.getEmail();
            String subject = "Confirmation de recevoire email";
            String message = "Bienvenu " + user.getNom()+ " " + user.getPrenom()+ " dans notre application ton demande du cadeaux est accepter vous pouver confirmer ton recevoire du email et de imprimer la demande en papier";
            String usermail = "alaa.guissouma@esprit.tn";
            String passmail = "Skotinka00_";
             Mailing.send(to, subject, message, usermail, passmail);
                         Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Tu as accepter la demande et tu as envoyer une email de confirmation a!"+compte.getId_user().getUsername())
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));  
                    
                } else  if ((cadeaux.getValeur_point() - compte.getPoint_merci()) > 5) {
                    
                       demandeCadeau.setStatusDemandeCadeaux("Attente");
                        demandeCadeau.setMessageDemandeCadeaux("Ton demande  est en Attente");
                        
                          String to = user.getEmail();
            String subject = "Confirmation de recevoire email";
            String message = "Bienvenu " + user.getNom()+ " " + user.getPrenom()+ " dans notre application Tu ne peut pas avoire ce cadeaux car le nombre de point est insuffisant ton demande est en cours de traitement";
            String usermail = "alaa.guissouma@esprit.tn";
            String passmail = "Skotinka00_";
             Mailing.send(to, subject, message, usermail, passmail);
                         Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Tu ne peut pas donner ce cadeaux pour ce membre ca les points est insuffisant le demande est en cours de traitement!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));  
                    
                 
                }
                else {
                    
                    
                     
                       demandeCadeau.setStatusDemandeCadeaux("Reffuse");
                        demandeCadeau.setMessageDemandeCadeaux("Ton demande  est  Reffuser");
                        
                          String to = user.getEmail();
            String subject = "Confirmation de recevoire email";
            String message = "Bienvenu " + user.getNom()+ " " + user.getPrenom()+ " dans notre application Tu ne peut pas avoire ce cadeaux car le nombre de point est insuffisant ton demande est en cours de traitement";
            String usermail = "alaa.guissouma@esprit.tn";
            String passmail = "Skotinka00_";
        Mailing.send(to, subject, message, usermail, passmail);
                         Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Tu ne peut pas donner ce cadeaux pour ce membre ca les points est insuffisant le demande est Reffuser!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));  
                }
                
                
                            
        } else {
                
                
                
                
                   alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez verifier que  la date d'expiration de votre Demande est superieur à la date actuelle");
                            alert.show();
                
            }
            
            
            
            
            
            
            
            
            
            
            
 
                     
          services.modifierDemandecadeaux(demandeCadeau);
                 createtableviewDemandeCadeau();    
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner une demande");
            alert.show();
        }
                    
        
    }
  

    

    @FXML
    private void AttenteDemande(ActionEvent event) throws ParseException {
        
        
          
               MyServices services = new MyServices();
        DemandeCadeau  demandeCadeau = tableviewListeDemandeAffectation.getSelectionModel().getSelectedItem();

        if (demandeCadeau != null) {
            
            Cadeaux cadeaux=new Cadeaux();
            Compte compte=new  Compte();
            
            
            
            
            
            cadeaux=services.chercherCadeauxById(demandeCadeau.getCadeaux().getId());
            compte=services.chercherCompteById(demandeCadeau.getMembreDemande().getIdCompte());
             User user=services.chercherUtilisateurByid(demandeCadeau.getMembreDemande().getId_user());
             Alert alert = new Alert(Alert.AlertType.ERROR);
              
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = demandeCadeau.getDatedexpirationDemandeCadeaux();

 

            Date dateo = formatter.parse(dateInString);
 
             
              
            if (dateo.after(sys.selectDate()))
            {
                
              System.out.println(dateo);
            System.out.println(sys.selectDate());
            
            
                System.out.println("Date experation apres la date d'aujourd'hui ");
            
          
 
                    
                       demandeCadeau.setStatusDemandeCadeaux("Attente");
                        demandeCadeau.setMessageDemandeCadeaux("Ton demande  est en Attente");
                        
                          String to = user.getEmail();
            String subject = "Confirmation de recevoire email";
            String message = "Bienvenu " + user.getNom()+ " " + user.getPrenom()+ " dans notre application Tu ne peut pas avoire ce cadeaux car le nombre de point est insuffisant ton demande est en cours de traitement";
            String usermail = "alaa.guissouma@esprit.tn";
            String passmail = "Skotinka00_";
            //Mailing.send(to, subject, message, usermail, passmail);
                         Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Tu ne peut pas donner ce cadeaux pour ce membre ca les points est insuffisant le demande est en cours de traitement!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));  
                    
                 
              
                
                
                
                            
        } else {
                
                
                
                
                   alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez verifier que  la date d'expiration de votre Demande est superieur à la date actuelle");
                            alert.show();
                
            }
            
            
            
  
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner une demande");
            alert.show();
        }
                  services.modifierDemandecadeaux(demandeCadeau);
                 createtableviewDemandeCadeau();     
        
        
        
        
        
        
        
        
        
    }

    @FXML
    private void RetourBonCadeaux(ActionEvent event) throws IOException {
        
         setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ListAffectationDemandeCadeaux.fxml")));
    }
    
    
     
                   private void setNode(Node node) {
        ListeDesDemandeCadeaAnchorPane.getChildren().clear();
        ListeDesDemandeCadeaAnchorPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void RefuserDemande(ActionEvent event) throws ParseException {
        
            
          
               MyServices services = new MyServices();
        DemandeCadeau  demandeCadeau = tableviewListeDemandeAffectation.getSelectionModel().getSelectedItem();

        if (demandeCadeau != null) {
            
            Cadeaux cadeaux=new Cadeaux();
            Compte compte=new  Compte();
            
            
            cadeaux=services.chercherCadeauxById(demandeCadeau.getCadeaux().getId());
            compte=services.chercherCompteById(demandeCadeau.getMembreDemande().getIdCompte());
             User user=services.chercherUtilisateurByid(demandeCadeau.getMembreDemande().getId_user());
             Alert alert = new Alert(Alert.AlertType.ERROR);
              
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = demandeCadeau.getDatedexpirationDemandeCadeaux();

 

            Date dateo = formatter.parse(dateInString);
 
             
              
            if (dateo.after(sys.selectDate()))
            {
                
              System.out.println(dateo);
            System.out.println(sys.selectDate());
            
            
                System.out.println("Date experation apres la date d'aujourd'hui ");
            
          
 
                     
                       demandeCadeau.setStatusDemandeCadeaux("Reffuse");
                        demandeCadeau.setMessageDemandeCadeaux("Ton demande  est  Reffuser");
                        
                          String to = user.getEmail();
            String subject = "Confirmation de recevoire email";
            String message = "Bienvenu " + user.getNom()+ " " + user.getPrenom()+ " dans notre application Tu ne peut pas avoire ce cadeaux car le nombre de point est insuffisant ton demande est en cours de traitement";
            String usermail = "alaa.guissouma@esprit.tn";
            String passmail = "Skotinka00_";
             Mailing.send(to, subject, message, usermail, passmail);
                         Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Tu ne peut pas donner ce cadeaux pour ce membre ca les points est insuffisant le demande est Reffuser!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));  
                    
                 
              
                
                
                
                            
        } else {
                
                
                
                
                   alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez verifier que  la date d'expiration de votre Demande est superieur à la date actuelle");
                            alert.show();
                
            }
            
            
            
  
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner une demande");
            alert.show();
        }
                  services.modifierDemandecadeaux(demandeCadeau);
                 createtableviewDemandeCadeau();    
        
    }
                   
   
}
