/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.xml.bind.ParseConversionEvent;
import org.controlsfx.control.Rating;
import pidev.sandy.entites.User;
import pidev.sandy.entites.avis;
import pidev.sandy.entites.offre_experience;
import pidev.sandy.services.AvisExperience;
import pidev.sandy.services.PartageExperience;
import javafx.stage.Stage;
import org.apache.commons.codec.binary.Hex;
import org.controlsfx.control.Notifications;
import pidev.sandy.services.PartageFb;

/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class DetailExperienceController implements Initializable {

    @FXML
    private AnchorPane UserPane;
    @FXML
    private VBox vbox;
    @FXML
    private Label backexp;
    @FXML
    private JFXButton btnclose;
    @FXML
    private FontAwesomeIcon notif;
    @FXML
    private AnchorPane listpane;
    @FXML
    private JFXButton addexp;
    @FXML
    private Label nomPExp;
    public static int id = 0;
    @FXML
    private ImageView imgdetail;
    private offre_experience oo;
    @FXML
    private Label Description;
    @FXML
    private Label decdetail;
    @FXML
    private Label Adresse;
    @FXML
    private Label adddetail;
    @FXML
    private Label Region;
    @FXML
    private Label regdetail;
    @FXML
    private Rating ratdetail;
    @FXML
    private VBox vboxavis;
    PartageExperience exp = new PartageExperience();
    private static final double WIDTH = 450, HEIGHT = 480;
    private Timeline animation;
    Image[] images;
    @FXML
    private Button imgshow;
    FileChooser fileChooser;
    @FXML
    private Rating ratingavis;
    @FXML
    private TextArea descavis;
    @FXML
    private JFXButton addavis;
    @FXML
    private Label Description1;
    @FXML
    private JFXButton loadimgavis;
    @FXML
    private ImageView imageaviss;
    avis av = new avis();
    @FXML
    private JFXButton modifierexp;
    @FXML
    private JFXButton suppexp;
    @FXML
    private JFXButton addavis1;
    public  static String titre;
    public  static String image;
    public static offre_experience of=new offre_experience();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        oo = new offre_experience();
      // User loggedUser =LoginController.getInstance().getLoggedUser();
        //  imgdetail.setImage(new Image( "file:C:\\xampp\\htdocs\\PhpstormProjects\\Pidev3\\web\\uploads\\images\\"+ oo.getUrl_image()));
     //     System.out.println(""+loggedUser);

        //  vboxavis.visibleProperty();
    }

    @FXML
    private void BackListExp(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/PartageExperience.fxml"));
        Parent root = loader.load();
        backexp.getScene().setRoot(root);
    }

    @FXML
    private void closeApp(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) btnclose.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void getnotif(MouseEvent event) {
    }

    @FXML
    private void showAddPartageExp(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/AjoutPartageExperience.fxml"));
        Parent root = loader.load();
        backexp.getScene().setRoot(root); 
    }

    public void setNomPExp(String nomPExp) {
        this.nomPExp.setText(nomPExp);
    }

    public void initData(offre_experience o) {
      
        of=o;
        imgdetail.setImage(new Image("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + o.getUrl_image()));
        decdetail.setText(o.getDescription());
        adddetail.setText(o.getAddrese());
        regdetail.setText(exp.GetRegion(o.getRegion_id()));
        ratdetail.setRating((int) o.getRating());
        id = o.getId();
        AvisExperience avis = new AvisExperience();
        image="C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + o.getUrl_image();
        titre=o.getNom();
        List<avis> list = avis.listerAvisParId(id);
        HBox hbox[] = new HBox[list.size()];
        for (int i = 1; i < list.size(); i++) {

            hbox[i] = new HBox();
            ImageView img = new ImageView(new Image("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + (list.get(i).getImageuser())));
            img.setFitWidth(50);
            img.setFitHeight(50);
            img.setPreserveRatio(true);
            Label lb = new Label((list.get(i).getNom()));
            lb.setTextFill(Color.web("#fff"));
            Rating r = new Rating((list.get(i).getRating()));
            r.setOrientation(Orientation.HORIZONTAL);
            //Label rt=new Label(""+);
            hbox[i].getChildren().addAll(img, lb, r);
            hbox[i].setAlignment(Pos.CENTER_LEFT);
            hbox[i].setPadding(new Insets(10, 0, 10, 0));

            vboxavis.getChildren().addAll(hbox[i]);
        }
        if (list.size() > 0) {
            images = new Image[list.size()];
        }
        List listimg = new ArrayList();
        int i = 0;
        for (avis aa : list) {
            listimg.add(aa.getUrl_image());
            images[i] = new Image("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + aa.getUrl_image());
            i++;

        }
        if (list.size() > 0) {
            // create display shelf
            imgshow.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    DisplayShelf displayShelf = new DisplayShelf(images);
                    displayShelf.setPrefSize(WIDTH, HEIGHT);
                    Scene scene = new Scene(displayShelf);
                    Stage dialogStage = new Stage();
                    dialogStage.setScene(scene);
                    dialogStage.show();
                }
            });
        } else {
            imgshow.disableProperty();
        }

    }

    public String upload(File file, String fileName) throws IOException {
        BufferedOutputStream stream = null;
        String globalPath = "C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\";
        String localPath = "localhost:80/";
        /*fileName = file.getName();
        fileName=fileName.replace(" ", "_");*/
        try {
            Path p = file.toPath();

            byte[] bytes = Files.readAllBytes(p);

            File dir = new File(globalPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return localPath + "/" + fileName;
        } catch (IOException ex) {
            Logger.getLogger(AjoutDemandeController.class.getName()).log(Level.SEVERE, null, ex);
            return "error2";

        }
    }

    @FXML
    public void addimage(ActionEvent event) throws MalformedURLException, NoSuchAlgorithmException, IOException {

        System.out.println("Load Image Button Pressed");
        fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(listpane.getScene().getWindow());
        if (file != null) {

            System.out.println("File Was Selected");
            URL url = file.toURI().toURL();
            String urlimg = url.getFile().replaceFirst("/", "");
            //  System.out.println("url = "+urlimg);

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] urlmd5 = md.digest(urlimg.getBytes());
            final String result = new String(Hex.encodeHex(urlmd5)) + ".jpg";
            upload(file, result);
            //    System.out.println("url md5 = "+result);
            av.setUrl_image(result);
            imageaviss.setImage(new Image(url.toExternalForm()));

        }

    }

    @FXML
    public void addAvis() {
        AvisExperience avexp = new AvisExperience();
        av.setNom(descavis.getText());
        av.setRating((int) ratingavis.getRating());
        av.setRef_experiencce(id);
        avexp.ajouterAvis(av);
        Notifications.create()
                .title("L'ajout d'avis")
                .darkStyle()
                .text("Votre avis a été ajouter avec succès")
                .showConfirm();
    }
    
    @FXML
      public void suppPartageExp() {
        PartageExperience pexp = new PartageExperience();
          System.out.println(id);
        pexp.SupprimerExperience(id);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/PartageExperience.fxml"));
            Parent root;
           
            try {
                root = loader.load();
               
                        suppexp.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(PartageExperienceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Notifications.create()
                .title("Supprission d'un partage d'expérience")
                .darkStyle()
                .text("Votre expérience partager a été supprimer avec succès")
                .showConfirm();
    }
    @FXML
     public void ModifPartageExp() {
         PartageExperience pexp = new PartageExperience();
        
      //  avexp.SupprimerAvis(id);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/ModiffPartageExperience.fxml"));
            Parent root;
           
            try {
                root = loader.load();
                   ModiffPartageExperienceController detail=loader.getController();
                             
                 detail.initData(of);
              
                 ModiffPartageExperienceController.id=of.getId();
                        suppexp.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(PartageExperienceController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }   

    @FXML
    private void sharefb(ActionEvent event) {
    PartageFb fb=new PartageFb();
        try {
            System.out.println("titre "+titre);
            System.out.println("/n image "+image);
            fb.partager(titre, image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailExperienceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
