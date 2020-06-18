/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Commande;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.controlsfx.control.Rating;
import service.Fosuserservice;
import service.ServiceCommande;
import service.UserSession;

/**
 * FXML Controller class
 *
 * @author jha
 */
public class CommandeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ObservableList<Commande> recdata = FXCollections.observableArrayList();
    @FXML
    private TableView<Commande> table;
    @FXML
    private TableColumn<Commande, String> etat;
    @FXML
    private Button home;
    @FXML
    private Button shop;
    @FXML
    private Button cart;
    @FXML
    private Button checkout;
    @FXML
    private Button profile;
    @FXML
    private Button logout;
    @FXML
    private Button search;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView fb;
    @FXML
    private ImageView insta;
    @FXML
    private ImageView pro;
    @FXML
    private Button Pdf;
    @FXML
    private ImageView imgCommande;
    @FXML
    private Label idC;
    @FXML
    private Label date;
    @FXML
    private Label etatC;
    @FXML
    private Pane paneAffichage;
    @FXML
    private Button Supprimer;
    @FXML
    private Rating rating;
    @FXML
    private Label msg;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      logo.setImage(new Image("/images/logo.png"));
    //    home.setImage(new Image("/images/acceuil.png"));
fb.setImage(new Image("/images/fb.png"));
pro.setImage(new Image("/images/product.png"));
insta.setImage(new Image("/images/insta.jpg"));
paneAffichage.setVisible(false);
                List<Commande> listRec= new ArrayList<Commande>();
        ServiceCommande rs =  new ServiceCommande();
        
         Fosuserservice  sc = new Fosuserservice ();
        
         
        
        listRec = rs.afficherCommande(sc.getIdbymail(UserSession.getInstance().getEmail()));
        recdata.clear();
        recdata.addAll(listRec);
        table.setItems(recdata);
        etat.setCellValueFactory(
            new PropertyValueFactory<>("etat")
        );
        
        table.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) {
                paneAffichage.setVisible(true);
                Commande rowData = table.getSelectionModel().getSelectedItem();
                imgCommande.setImage(new Image("/images/motour.jpg"));
                idC.setText("Commande numero : "+rowData.getId());
                etatC.setText("Etat : "+rowData.getEtat());
                date.setText("Date Commande : "+rowData.getDate());
          
            }
        });
            rating.ratingProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
                msg.setText("Rating : "+ t1.toString());
      });
        
    }    
    
    private void prod(MouseEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/ShoppingP.fxml"));
            Parent root= loader.load();
            
            
            
            
            table.getScene().setRoot(root);
    }
    
    @FXML
    private void cart(MouseEvent event) throws IOException {
       
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/panier.fxml"));
            Parent root= loader.load();
            table.getScene().setRoot(root);
    }

    @FXML
    private void profile(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/login.fxml"));
            Parent root= loader.load();
            table.getScene().setRoot(root);
    }

    @FXML
    private void creepdf(ActionEvent event) {
        
//        PdfGenerator pg=new PdfGenerator();
       ServiceCommande cmd=new ServiceCommande();
//       
//     
//        LocalDateTime currentTime = LocalDateTime.now();
//        String date =currentTime.toLocalDate().toString();
//        String path=pg.BasicPath+"Fournisseurs"+date+".pdf";
//  
//        Document doc=pg.createPdf(path);
//     doc.add(cmd.generateFournisseurPdfTable());
//     doc.close();
Commande c= table.getSelectionModel().getSelectedItem();
cmd.getFacture(c.getId());
        
     //   pg.createPdf(dest)
       // createPdf
        
    }

    @FXML
    private void Home(MouseEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/javafxapplication4/FXMLDocument.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
    }

    @FXML
    private void Shop(MouseEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/produit.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
        
    }

    @FXML
    private void Supprimer(ActionEvent event) {
            Commande cm= table.getSelectionModel().getSelectedItem();
       ServiceCommande sc = new ServiceCommande();
       sc.supprimerCommande(cm);
       List<Commande> listRec= new ArrayList<Commande>();
       listRec = sc.afficherCommande(cm.getUser());
        recdata.clear();
        recdata.addAll(listRec);
        table.setItems(recdata);
    }
    }
    
