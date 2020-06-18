/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;
import com.lynden.gmapsfx.GoogleMapView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.lynden.gmapsfx.*;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.directions.*;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class AfficherBonPlanController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {
//    @FXML
//    private ImageView couverture;
    @FXML
    private ImageView profil;
    @FXML
    private GoogleMapView map;
    GoogleMap g;
    @FXML
    private Label description;
    @FXML
    private Label adresse;
    @FXML
    private JFXTimePicker ouverture;
    @FXML
    private JFXTimePicker fermeture;
    @FXML
    private Label libelle;
    int id;
    private Rating rate;
    @FXML
    private TextField from;
    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    Marker marker;
    @FXML
    private Label tel;
    @FXML
    private Label email;
    @FXML
    private VBox vbox;
    @FXML
    private Label categorie;
    @FXML
    private Label partenaire;
    @FXML
    private JFXButton btnclose;
    @FXML
    private JFXButton back;
    @FXML
    private AnchorPane profilepane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rate.setRating((int) EspacepartenaireeController.d.getRating());

        map.addMapInializedListener(this);
        description.setText(EspacepartenaireeController.d.getDescription());
        libelle.setText(EspacepartenaireeController.d.getLibelle());
        profil.setImage(new Image(EspacepartenaireeController.d.getImage()));
//        couverture.setImage(new Image(EspacepartenaireeController.d.getCouverture()));
      profil.setFitWidth(280);
       profil.setFitHeight(200);
//System.out.println(EspacepartenaireeController.d.getImage());
//System.out.println(EspacepartenaireeController.d.getCouverture());
        email.setText(EspacepartenaireeController.d.getEmail());
        tel.setText(Integer.toString(EspacepartenaireeController.d.getNum_tel_local()));
        adresse.setText(EspacepartenaireeController.d.getAddresse());
        //ouverture.setText((EspacepartenaireeController.d.getOverture()));
if(EspacepartenaireeController.d.getFermeture()!=null&&EspacepartenaireeController.d.getOverture()!=null)
{fermeture.setValue(EspacepartenaireeController.d.getFermeture().toLocalTime());
        ouverture.setValue(EspacepartenaireeController.d.getOverture().toLocalTime());}

        // latitude.setText(Integer.toString(EspacepartenaireeController.d.getLatitude()));
        // longitude.setText(Integer.toString(EspacepartenaireeController.d.getLatitude()));
        id = EspacepartenaireeController.d.getId();

    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();
        Double lal = EspacepartenaireeController.d.getLatitude();
        Double lon = EspacepartenaireeController.d.getLongitude();
        options.center(new LatLong(lal, lon))
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
        marker = new Marker(Options);
        g.addMarker(marker);
        g.setCenter(new LatLong(36.8189700, 10.1657900));
        g.setZoom(15);
        g.addMarker(marker);

        directionsService = new DirectionsService();
        directionsPane = map.getDirec();
    }


    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setNode(Node node) {
        profilepane.getChildren().clear();
        profilepane.getChildren().add((Node) node);
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

    @FXML
    private void trajet(ActionEvent event) {
        //        MarkerOptions Options = new MarkerOptions();
//        Options.visible(Boolean.FALSE);
//        marker.setVisible(false);
        g.removeMarker(marker);
//                = new Marker(Options);
      String to =EspacepartenaireeController.d.getAddresse();
      DirectionsRequest request = new DirectionsRequest(from.getText(), to, TravelModes.WALKING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, map.getMap(), directionsPane));
        request.setOpt(true);
       
    }

    

}
