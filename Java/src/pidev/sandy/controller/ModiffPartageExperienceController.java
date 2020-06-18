/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.apache.commons.codec.binary.Hex;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import pidev.sandy.entites.offre_experience;
import pidev.sandy.services.PartageExperience;

/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class ModiffPartageExperienceController implements Initializable {

    @FXML
    private AnchorPane UserPane;
    @FXML
    private VBox vbox;
    @FXML
    private Label preclabel;
    @FXML
    private JFXButton btnclose;
    @FXML
    private Pane partageexpbtn;
    @FXML
    private AnchorPane partageexppanes;
    @FXML
    private JFXTextField nompx;
    @FXML
    private JFXTextField descripex;
    @FXML
    private JFXTextField adresseexp;
    @FXML
    private JFXComboBox<?> regionexp;
    @FXML
    private JFXComboBox<?> catexp;
    @FXML
    private JFXCheckBox pcarte;
    @FXML
    private Rating raiting;
    @FXML
    private JFXCheckBox wifi;
    @FXML
    private JFXCheckBox clim;
    @FXML
    private JFXCheckBox snckb;
    @FXML
    private JFXCheckBox park;
    @FXML
    private JFXCheckBox picine;
    @FXML
    private JFXCheckBox balcon;
    @FXML
    private JFXCheckBox vguider;
    @FXML
    private JFXCheckBox fumer;
    @FXML
    private JFXCheckBox resrv;
    @FXML
    private JFXCheckBox familial;
    @FXML
    private JFXButton btnajouterexp;
    @FXML
    private ImageView expimages;
    @FXML
    private JFXButton selectimg;
    public static int id;
    FileChooser fileChooser;
    public static offre_experience oe=new offre_experience();
    PartageExperience pexp=new PartageExperience();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             ObservableList RGList = FXCollections.observableList(pexp.ListRegion());
        ObservableList CATList = FXCollections.observableList(pexp.ListCategorie());
        regionexp.setItems(RGList);
        catexp.setItems(CATList);
    }    

    @FXML
    private void BackHomes(MouseEvent event) throws IOException {
        
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/PartageExperience.fxml"));
        Parent root = loader.load();
        descripex.getScene().setRoot(root);
    }

    @FXML
    private void closeApp(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void AjoutExperience(ActionEvent event) {
      oe.setNom(nompx.getText());
         oe.setDescription(descripex.getText());
         oe.setAddrese(adresseexp.getText());
         oe.setRating((int)raiting.getRating());
         oe.setClimatisation(clim.isSelected());
         oe.setWifi(wifi.isSelected());
         oe.setFumer(fumer.isSelected());
         oe.setBalcon(balcon.isSelected());
         oe.setParking(park.isSelected());
         oe.setPaiementparcarte(pcarte.isSelected());
         oe.setPiscine(picine.isSelected());
         oe.setSnackbar(snckb.isSelected());
         oe.setFamiliale(familial.isSelected());
         oe.setReservations(resrv.isSelected());
         oe.setVisites(vguider.isSelected());
         
         pexp.modifierExperience(oe, oe.getId());
         Notifications.create().text("Modifier avec succée").title("Modification").showConfirm();
                  
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

        File file = fileChooser.showOpenDialog(partageexppanes.getScene().getWindow());
        if (file != null) {
            System.out.println("File Was Selected");
            URL url = file.toURI().toURL();
            String urlimg = url.getFile().replaceFirst("/", "");
            //  System.out.println("url = "+urlimg);

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] urlmd5 = md.digest(urlimg.getBytes());
            final String result = new String(Hex.encodeHex(urlmd5)) + ".jpg";
            upload(file, result);
            if(result!="")
           oe.setUrl_image(result);

            //    System.out.println("url md5 = "+result);
            expimages.setImage(new Image(url.toExternalForm()));

        }
    }
     public void initData(offre_experience o) {
         oe.setId(o.getId());
         nompx.setText(o.getNom());
         descripex.setText(o.getDescription());
         adresseexp.setText(o.getAddrese());
         expimages.setImage((new Image( "file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\"+ o.getUrl_image())));
         raiting.setRating(o.getRating());
         clim.selectedProperty().set(o.getClimatisation());
         wifi.selectedProperty().set(o.getWifi());
         fumer.selectedProperty().set(o.getFumer());
         balcon.selectedProperty().set(o.getBalcon());
         park.selectedProperty().set(o.getParking());
         pcarte.selectedProperty().set(o.getPaiementparcarte());
         picine.selectedProperty().set(o.getPiscine());
         snckb.selectedProperty().set(o.getSnackbar());
         familial.selectedProperty().set(o.getFamiliale());
         resrv.selectedProperty().set(o.getReservations());
         vguider.selectedProperty().set(o.getVisites());
          
         
     }
}
