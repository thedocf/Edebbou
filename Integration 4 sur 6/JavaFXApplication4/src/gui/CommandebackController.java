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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.ServiceCommande;
import service.ServiceLivreur;
import java.net.*;
import java.util.Base64;
import java.io.*;
import java.sql.SQLException;
import javafx.scene.control.RadioButton;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CommandebackController implements Initializable {
     

    @FXML
    private TableView<Commande> table;
    @FXML
    private TableColumn<Commande, String> date;
    @FXML
    private TableColumn<Commande, String> etat;
    @FXML
    private TableColumn<Commande, String> action;
    
    
     ServiceCommande rs =  new ServiceCommande();
       
    public ObservableList<Commande> list = FXCollections.observableArrayList(rs.afficherAllCommande());
    
    private ObservableList<Commande> recdata = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> etatbox;
    @FXML
    private ComboBox<Livreur> livreurbox;
    @FXML
    private TextField search;
    @FXML
    private RadioButton tri;
    @FXML
    private RadioButton triDate;
    @FXML
    private Button valider;
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
        
        // TODO
     //   logo.setImage(new Image("/images/logo.png"));
        List<Commande> listRec= new ArrayList<Commande>();
        ServiceCommande rs =  new ServiceCommande();
        listRec = rs.afficherCommande(1);
        recdata.clear();
        recdata.addAll(listRec);
        table.setItems(recdata);
        
        
        date.setCellValueFactory(
            new PropertyValueFactory<>("date")
        );
        etat.setCellValueFactory(
            new PropertyValueFactory<>("etat")
        );
        action.setCellValueFactory(
            new PropertyValueFactory<>("nom")
        );
        
        ObservableList<String> options = 
    FXCollections.observableArrayList(
        "En attente",
        "En cours",
        "Livrée"
    );
        etatbox.setItems(options);
        ServiceLivreur sl = new ServiceLivreur();
        ObservableList<Livreur> lo = FXCollections.observableArrayList(sl.afficherLivreur());
        
        livreurbox.setItems(lo);
        FilteredList<Commande> filteredData = new FilteredList<>(list, e -> true);
        search.setOnKeyReleased(e -> {
            search.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Commande>) cmd -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lower = newValue.toLowerCase();
                    if (cmd.getEtat().toLowerCase().contains(lower)) {
                        return true;
                    }

                    return false;
                });
            });
            SortedList<Commande> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        });
        
    }    

    private void addliv(MouseEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/new livreur.fxml"));
            Parent root= loader.load();
            
            
            
            
            table.getScene().setRoot(root);
    }

    private void liv(MouseEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/livreurDash.fxml"));
            Parent root= loader.load();
            
            
            
            
            table.getScene().setRoot(root);
    }

    private void valider(ActionEvent event) {
        
    }


    @FXML
    private void onmouseexit(MouseEvent event) {
    }

    @FXML
    private void Onmouseenter(MouseEvent event) {
    }


    private void newLivreur(MouseEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/new livreur.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
    }

    @FXML
    private void livreurs(MouseEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/livreurDash.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
        
    }

    @FXML
    private void Trier(MouseEvent event) {
    }
    public void sms(String username , String password , String to , String messagee) {
        try {
             String myURI = "https://api.bulksms.com/v1/messages";

    // change these values to match your own account
    String myUsername = ""+username+"";
    String myPassword = ""+password+"";

    // the details of the message we want to send
    String myData = "{to: \""+to+"\", encoding: \"UNICODE\", body: \""+messagee+"\"}";

    // if your message does not contain unicode, the "encoding" is not required:
    // String myData = "{to: \"1111111\", body: \"Hello Mr. Smith!\"}";

    // build the request based on the supplied settings
    URL url = new URL(myURI);
    HttpURLConnection request = (HttpURLConnection) url.openConnection();
    request.setDoOutput(true);

    // supply the credentials
    String authStr = myUsername + ":" + myPassword;
    String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
    request.setRequestProperty("Authorization", "Basic " + authEncoded);

    // we want to use HTTP POST
    request.setRequestMethod("POST");
    request.setRequestProperty( "Content-Type", "application/json");

    // write the data to the request
    OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
    out.write(myData);
    out.close();

    // try ... catch to handle errors nicely
    try {
      // make the call to the API
      InputStream response = request.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(response));
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
      }
      in.close();
    } catch (IOException ex) {
      System.out.println("An error occurred:" + ex.getMessage());
      BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
      // print the detail that comes with the error
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
      }
      in.close();
    }
    request.disconnect();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void triAction(ActionEvent event) throws SQLException {
       
            
            ServiceCommande rs =  new ServiceCommande();
            ArrayList<Commande> a = (ArrayList<Commande>) rs.getTrierEtat(); 
            ObservableList<Commande> obs = FXCollections.observableArrayList(a);   
            table.setItems(obs);
            triDate.setSelected(false);

    }

    @FXML
    private void triDateAction(ActionEvent event) throws SQLException {
         ServiceCommande rs =  new ServiceCommande();
            ArrayList<Commande> a = (ArrayList<Commande>) rs.getTrierDate(); 
            ObservableList<Commande> obs = FXCollections.observableArrayList(a);   
            table.setItems(obs);
            tri.setSelected(false);
    }






    @FXML
    private void products(MouseEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/ProduitBack.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
    }

    @FXML
    private void categoriess(MouseEvent event) throws IOException {
        
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/CategorieBack.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
        
    }

    @FXML
    private void validerr(ActionEvent event) {
        ServiceCommande sc = new ServiceCommande();
        Commande c = table.getSelectionModel().getSelectedItem();
        sc.modifierCommande(c.getId(), livreurbox.getSelectionModel().getSelectedItem().getId(), etatbox.getSelectionModel().getSelectedItem());
        List<Commande> listRec= new ArrayList<Commande>();
        ServiceCommande rs =  new ServiceCommande();
        listRec = rs.afficherCommande(1);
                
   sms("ikk10","16111998", "+21623043475", "Votre commande est en cours");
        recdata.clear();
        recdata.addAll(listRec);
        table.setItems(recdata);
    }


    private void home(ActionEvent event) throws IOException {
        
                    FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/homeback.fxml"));
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
    private void Glivreur(ActionEvent event) throws IOException {
        
                                    FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/livreurDash.fxml"));
            Parent root= loader.load();
  
            Glivreur.getScene().setRoot(root);
    }

    @FXML
    private void GProduit(ActionEvent event) throws IOException {
        
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/ProduitBack.fxml"));
            Parent root= loader.load();  
            GProduit.getScene().setRoot(root);
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
    private void logout(MouseEvent event) {
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
