/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import pidev.sandy.entites.BonPlan;
import pidev.sandy.entites.Categoris;
import pidev.sandy.services.ServiceBonplan;
import pidev.sandy.services.ServiceCategorie;
import pidev.sandy.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import com.lynden.gmapsfx.*;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class AjouterBonPlanController implements Initializable, MapComponentInitializedListener {

    @FXML
    private JFXTextField libelle;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton image;

    @FXML
    private JFXButton couverture;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTimePicker ouverture;
    @FXML
    private JFXTimePicker fermeture;
    @FXML
    private JFXTextField addresse;

    private String path;
    private String path2;
    File selectedFile;
    File selectedFile2;
    @FXML
    private GoogleMapView map;
    GoogleMap g;
    @FXML
    private JFXButton valid;
    @FXML
    private JFXTextField longtitude;
    @FXML
    private JFXTextField latitude;
    @FXML
    private VBox vbox;
    @FXML
    private Label partenaire;
    @FXML
    private JFXButton btnclose;
    @FXML
    private JFXButton back;

    @FXML
    private JFXComboBox<String> test;
    @FXML
    private Label categorie;
    @FXML
    private AnchorPane ajoutbonplan;
    @FXML
    private ImageView afficheprofilll;
    @FXML
    private ImageView affichecouverturee;
    @FXML
    private Label libelleerreur;
    @FXML
    private Label descriptionerreur;
    @FXML
    private Label emailerreur;
    @FXML
    private Label numtelereur;

    
    
    static String getAddressByGpsCoordinates(String lng, String lat)
            throws MalformedURLException, IOException, org.json.simple.parser.ParseException {
         
        URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng="
                + lat + "," + lng + "&sensor=true");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String formattedAddress = "";
 
        try {
            InputStream in = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String result, line = reader.readLine();
            result = line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
 
            JSONParser parser = new JSONParser();
            JSONObject rsp = (JSONObject) parser.parse(result);
 
            if (rsp.containsKey("results")) {
                JSONArray matches = (JSONArray) rsp.get("results");
                JSONObject data = (JSONObject) matches.get(0); //TODO: check if idx=0 exists
                formattedAddress = (String) data.get("formatted_address");
            }
 
            return "";
        } finally {
            urlConnection.disconnect();
            return formattedAddress;
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        map.addMapInializedListener(this);
        //warning.setVisible(false);
        libelleerreur.setVisible(false);
        descriptionerreur.setVisible(false);
        emailerreur.setVisible(false);
        numtelereur.setVisible(false);
        List<String> myList = new ArrayList<String>();
        String sql = "SELECT * FROM categorie WHERE categorie.idcategoriemere is not NULL";
        try {
            Statement result = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = result.executeQuery(sql);
            while (rs.next()) {
                myList.add(rs.getString(3));

            }
            ObservableList<String> olist = FXCollections.observableArrayList(myList);
            test.setItems(olist);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    
    
    
    
    @FXML
    private void ajouterBonPlan(ActionEvent event) throws IOException {

 BonPlan p = new BonPlan();
  if (tel.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < tel.getText().trim().length(); i++) {
                char ch = tel.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                System.out.println(nbChar);
            }

            if (nbChar == 0) {
                numtelereur.setText("Numéro valide");
                numtelereur.setVisible(false);
                 p.setNum_tel_local(Integer.parseInt(tel.getText()));
                
                System.out.println("hhhhhhhh");

            } else {
                numtelereur.setText("Numéro de telephone invalide ,Il exist des char");
                numtelereur.setVisible(true);
                
                System.out.println("nnnn");

            }
        }
  else
      
{
  numtelereur.setText("Numéro de telephone invalide nbr char <8");
                numtelereur.setVisible(true);
  }
        
        p.setLibelle(libelle.getText());
        p.setDescription(description.getText());
        Categoris categorie2 = new Categoris();
        ServiceCategorie sc = new ServiceCategorie();
        categorie2 = sc.ChercherCategorie(test.getValue());
        System.out.println(test.getValue());
        System.out.println(categorie2);
        p.setRefcategorie(categorie2);
        p.setImage(path);
        p.setCouverture(path2);
        p.setEmail(email.getText());
       
        p.setOverture(java.sql.Time.valueOf(ouverture.getValue()));
        p.setFermeture(java.sql.Time.valueOf(fermeture.getValue()));
        p.setAddresse(addresse.getText());

        p.setLongitude(Double.parseDouble(longtitude.getText()));
        p.setLatitude(Double.parseDouble(latitude.getText()));

        

        if (selectedFile != null) {
            try {
                //System.out.println(selectedFile.toString());
                File source = new File(selectedFile.toString());
                File dest = new File("C:\\wamp64\\www\\TunisiaBonPlan2\\web\\uploads\\images");
 
                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (selectedFile2 != null) {
            try {
                //System.out.println(selectedFile.toString());
                File source = new File(selectedFile2.toString());
                File dest = new File("C:\\wamp64\\www\\TunisiaBonPlan2\\web\\uploads\\images");

                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!(libelle.getText().isEmpty())) {
            String masque = "^[a-zA-Z]+$";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(libelle.getText());
            if (!(controler.matches())) {
                libelleerreur.setText("Nom bon plan invalide");
                libelleerreur.setVisible(true);
                //  warning.setVisible(true);
                return;
            }
        } else {
            libelleerreur.setVisible(false);
        }

        if ((description.getText().length() < 4)) {
            descriptionerreur.setText("Description invalide");
            descriptionerreur.setVisible(true);
            // warning.setVisible(true);
            return;
        } else {
            descriptionerreur.setVisible(false);
        }

        if (tel.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < tel.getText().trim().length(); i++) {
                char ch = tel.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                System.out.println(nbChar);
            }

            if (nbChar == 0) {
                numtelereur.setText("Numéro valide");
                numtelereur.setVisible(false);
                
                System.out.println("hhhhhhhh");

            } else {
                numtelereur.setText("Numéro de telephone invalide \n"
                        + " Il exist des char");
                numtelereur.setVisible(true);
                
                System.out.println("nnnn");

            }

        } else {
            numtelereur.setText("invalide number \n"
                    + " Ilfaut 8 chiffres");
        }
        
        
        
        if (!(email.getText().isEmpty())) {
            String email_pattern = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(email.getText());

            if (matcher.matches()) {       //if   matcher ne contient pas la format   
                emailerreur.setVisible(false);
                emailerreur.setText("Email valide !");

            } else {
                emailerreur.setVisible(true);
                emailerreur.setText("Email Format invalide !");

            }
        }
//bizar testi trah
// ok chouuu eel teel mee bekkri meeyet3aadch el ajout ella manhot 8 chiffre mais matjiinich ereur maaneha tokhrejjch lel user mais maytt3chc lajout merde
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCategorie.fxml"));
if (!emailerreur.isVisible() &&!numtelereur.isVisible() && !descriptionerreur.isVisible()&& !libelleerreur.isVisible())
{ServiceBonplan ms = new ServiceBonplan();
        ms.ajouter2(p);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/Espacepartenairee.fxml"));
        Parent root;
        root = loader.load();
        ajouter.getScene().setRoot(root);
        new Alert(Alert.AlertType.INFORMATION, "Bon Plan ajoutée").show();}

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCategorie.fxml"));
//        Parent root = loader.load();
//        AfficherCategorieController afficheController = loader.getController();
//        afficheController.setLibelle(libelle.getText());
//        afficheController.setDiscription(description.getText());
//        afficheController.setImage(image.getText());
//
//        libelle.getScene().setRoot(root);
    }
// }
    @FXML
    private void image(ActionEvent event) {

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("Image", ".jpg", ".png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
//    
            image.setText(path);
            Image imagea = new Image(selectedFile.toURI().toString());
            afficheprofilll.setImage(imagea);

        }
    }

    @FXML
    private void couverture(ActionEvent event) {

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("Image", ".jpg", ".png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path2 = selectedFile.getName();
//    
            image.setText(path2);
            Image imagea = new Image(selectedFile.toURI().toString());
            affichecouverturee.setImage(imagea);

        }
    }

    @FXML
    private void valid(ActionEvent event) throws IOException, MalformedURLException {

        
            longtitude.setText(String.valueOf(g.getCenter().getLongitude()));//lesem bch nbaa3iou zone de
            //text b l long lesem nrodouha string khater zone tde texte tee9bel ken string
            latitude.setText(String.valueOf(g.getCenter().getLatitude()));
            System.out.println(g.getCenter().getLongitude());
            System.out.println(g.getCenter().getLatitude());
            try {
            addresse.setText(getAddressByGpsCoordinates(longtitude.getText(), latitude.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(AjouterBonPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mapInitialized() {
        //3maltha abstrait khater nheb nbadel kima nheb ok bb :) bye 
        MapOptions options = new MapOptions(); //bech n7adedou chniyaa fehaa ptption

        options.center(new LatLong(36.8189700, 10.1657900))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(true)
                .streetViewControl(false)
                .zoomControl(true)
                .mapMaker(true)
                .zoom(5);
        g = map.createMap(options);

        LatLong cord = new LatLong(36.8189700, 10.1657900);
        MarkerOptions Options = new MarkerOptions();
        Options.position(cord)
                .visible(Boolean.TRUE)
                .title("My Marker")
                .animation(Animation.BOUNCE);
        Marker marker = new Marker(Options);
        g.addMarker(marker);
        g.setCenter(new LatLong(36.8189700, 10.1657900));
        g.setZoom(15);
        g.addMarker(marker);

    }

    private void setNode(Node node) {
        ajoutbonplan.getChildren().clear();
        ajoutbonplan.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(javafx.util.Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void categorie(MouseEvent event) throws IOException {
        setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/List.fxml")));
    }

    @FXML
    private void partenaire(MouseEvent event) throws IOException {
        setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/Espacepartenairee.fxml")));
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/Espacepartenairee.fxml")));
    }

    @FXML
    public void closeApp() {

        Stage stage = (Stage) btnclose.getScene().getWindow();
        stage.close();
    }

}
