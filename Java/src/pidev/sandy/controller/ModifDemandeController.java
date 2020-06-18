/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.apache.commons.codec.binary.Hex;
import org.controlsfx.control.Notifications;
import static pidev.sandy.controller.DetailDemandeController.id;
import static pidev.sandy.controller.ModiffPartageExperienceController.oe;
import pidev.sandy.entites.demande_experience;
import pidev.sandy.entites.offre_experience;
import pidev.sandy.services.DemandeRecommandation;

/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class ModifDemandeController implements Initializable {

    @FXML
    private AnchorPane addDemandePane;
    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btnclose;
    @FXML
    private AnchorPane demandepanes;
    @FXML
    private JFXTextField titledem;
    @FXML
    private JFXTextArea descripdem;
    @FXML
    private JFXTextField adressdem;
    @FXML
    private JFXButton btnajoutdemande;
    @FXML
    private JFXButton ajouterdemande;
    @FXML
    private ImageView imgdemande;
    FileChooser fileChooser;
    private static int idd;
    public static demande_experience oe=new demande_experience();
    DemandeRecommandation pexp=new DemandeRecommandation();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BackHome(MouseEvent event) {
         
    
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/DemandeRecommandation.fxml"));
            Parent root;
           
            try {
                root = loader.load();
            
                        addDemandePane.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(PartageExperienceController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
    
    }

    @FXML
    private void closeApp(ActionEvent event) {
        System.exit(0);
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

        File file = fileChooser.showOpenDialog(addDemandePane.getScene().getWindow());
        if (file != null) {
            System.out.println("File Was Selected");
            URL url = file.toURI().toURL();
            String urlimg = url.getFile().replaceFirst("/", "");
            //  System.out.println("url = "+urlimg);

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] urlmd5 = md.digest(urlimg.getBytes());
            final String result = new String(Hex.encodeHex(urlmd5)) + ".jpg";
            upload(file, result);
            System.out.println("result "+result);
            if(result!="")
           oe.setImage(result);

            //    System.out.println("url md5 = "+result);
            imgdemande.setImage(new Image(url.toExternalForm()));

        }
    }
     public void initData(demande_experience o) {
         oe.setId(o.getId());
         titledem.setText(o.getNom());
         descripdem.setText(o.getDescripion());
         adressdem.setText(o.getAddresse());
         imgdemande.setImage(new Image("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\"+ o.getImage()));
         idd=o.getId();
      
          
         
     }

    @FXML
    private void ajouterdemandebtn(ActionEvent event) {
               
          //System.out.println(id);
           oe.setAddresse(adressdem.getText());
         oe.setDescripion(descripdem.getText());
         oe.setNom(titledem.getText());
           pexp.modifierDemande(oe, idd);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/DemandeRecommandation.fxml"));
            Parent root;
           
            try {
                root = loader.load();
              
                      
          
        //System.out.println("id ="+id);
                        demandepanes.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(PartageExperienceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Notifications.create()
                .title("Modification d'un demande de recommandation")
                .darkStyle()
                .text("Votre demande  a été modifier avec succès")
                .showConfirm(); 
    }
    
}
