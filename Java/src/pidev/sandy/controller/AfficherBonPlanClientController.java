/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import pidev.sandy.entites.User;
import pidev.sandy.entites.aimer;
import pidev.sandy.entites.avisbonplan;
import pidev.sandy.services.MyServices;
import pidev.sandy.services.ServiceBonplan;
import pidev.sandy.test.MenuAdmin1;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class AfficherBonPlanClientController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

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
    @FXML
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
    private AnchorPane client;

    int nombre_like;
    @FXML
    private Label nbjaime;
    @FXML
    private Label moy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        double moyennea = 0;
        double somme = 0;
        int n = 1;
        ServiceBonplan bon = new ServiceBonplan();
        ArrayList arf = (ArrayList) bon.ListeAvis(TousLesBonPlanController.bonplanclient.getId());
        Iterator<avisbonplan> it = arf.iterator();

        while (it.hasNext()) {
            avisbonplan avis = it.next();
            n = n + 1;
            somme = (somme + avis.getRating());
        }
        moyennea = (somme / n);

           String Rate = String.valueOf(moyennea );
        System.out.println(moyennea);
                  moy.setText(Rate);
                  rate.setRating(moyennea);
                  
                      
        ServiceBonplan nb = new ServiceBonplan();

        nbjaime.setText(Integer.toString(nb.countJaime(TousLesBonPlanController.bonplanclient.getId())));
        System.out.println("nombre jaime" + nb.countJaime(TousLesBonPlanController.bonplanclient.getId()));

        map.addMapInializedListener(this);
        description.setText(TousLesBonPlanController.bonplanclient.getDescription());
        libelle.setText(TousLesBonPlanController.bonplanclient.getLibelle());
        profil.setImage(new Image(TousLesBonPlanController.bonplanclient.getImage()));
        profil.setFitWidth(200);
        profil.setFitHeight(150);
        email.setText(TousLesBonPlanController.bonplanclient.getEmail());
        tel.setText(Integer.toString(TousLesBonPlanController.bonplanclient.getNum_tel_local()));
        adresse.setText(TousLesBonPlanController.bonplanclient.getAddresse());

        if (TousLesBonPlanController.bonplanclient.getFermeture() != null && TousLesBonPlanController.bonplanclient.getOverture() != null) {
            fermeture.setValue(TousLesBonPlanController.bonplanclient.getFermeture().toLocalTime());
            ouverture.setValue(TousLesBonPlanController.bonplanclient.getOverture().toLocalTime());
        }
        profil.setFitWidth(280);
        profil.setFitHeight(200);
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();
        Double lal = TousLesBonPlanController.bonplanclient.getLatitude();
        Double lon = TousLesBonPlanController.bonplanclient.getLongitude();
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
        client.getChildren().clear();
        client.getChildren().add((Node) node);
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
        setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/TousLesBonsPlans.fxml")));
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
        String to = TousLesBonPlanController.bonplanclient.getAddresse();
        DirectionsRequest request = new DirectionsRequest(from.getText(), to, TravelModes.WALKING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, map.getMap(), directionsPane));
        request.setOpt(true);

    }


    @FXML
    private void evaluer(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/rating.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {

        }
    }

    public void moyenne() {
        float moyennea = 0;
        int somme = 0;
        int n = 1;
        ServiceBonplan bon = new ServiceBonplan();
        ArrayList arf = (ArrayList) bon.ListeAvis(TousLesBonPlanController.bonplanclient.getId());
        Iterator<avisbonplan> it = arf.iterator();

        while (it.hasNext()) {
            avisbonplan avis = it.next();
            n = n + 1;
            somme = (int) (somme + avis.getRating());
        }
        moyennea = somme / n;

        String Rate = String.valueOf(moyennea);

    }


    @FXML
    private void like(ActionEvent event) throws IOException {
        
        ServiceBonplan sbn = new ServiceBonplan();
        MyServices a = new MyServices();
        
        User bbo = a.chercherUtilisateurByid(MenuAdmin1.Id_user_connecte);
        aimer am = new aimer(TousLesBonPlanController.bonplanclient.getId(), bbo);
        
        ArrayList listaime = (ArrayList) sbn.listejaime(TousLesBonPlanController.bonplanclient.getId());
        Iterator<aimer> aimeraIterator = listaime.iterator();
        
        int nombrejaimedememebre = 0;
        while (aimeraIterator.hasNext()) {
            aimer mm = aimeraIterator.next();
            nombrejaimedememebre = nombrejaimedememebre + 1;
            if (listaime.contains(bbo.getUsername())) {
                nombrejaimedememebre = nombrejaimedememebre + 1;
            }
        }
        if (nombrejaimedememebre == 0) {
            sbn.jaime(am);
            Notifications n = Notifications.create()
                    .title("J'aime")
                    .text("Vous avez aimer ce bon plan")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showInformation();
        } else {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Vous avez déjà aimez ce bon plan")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();

        }

        setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AfficherBonPlanClient.fxml")));

    }

}
