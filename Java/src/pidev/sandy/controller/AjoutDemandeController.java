/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import eu.hansolo.enzo.notification.Notification;
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
import javafx.stage.Stage;
 
import org.apache.commons.codec.binary.Hex;
import pidev.sandy.services.DemandeRecommandation;
import eu.hansolo.enzo.notification.Notification.Notifier;
import eu.hansolo.enzo.notification.NotificationBuilder;
import eu.hansolo.enzo.notification.NotifierBuilder;
import pidev.sandy.entites.demande_experience;
/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class AjoutDemandeController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btnclose;
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
   demande_experience p=new demande_experience();
   private String fileName;
    @FXML
    private AnchorPane demandepanes;
    @FXML
    private AnchorPane addDemandePane;
    /**
     * Initializes the controller class.
     */
      private static final Notification[] NOTIFICATIONS = {
        NotificationBuilder.create().title("Info").message("New Information").image(Notification.INFO_ICON).build(),
        NotificationBuilder.create().title("Warning").message("Attention, somethings wrong").image(Notification.WARNING_ICON).build(),
        NotificationBuilder.create().title("Success").message("Ajouter avec succée").image(Notification.SUCCESS_ICON).build(),
        NotificationBuilder.create().title("Error").message("ZOMG").image(Notification.ERROR_ICON).build()
    };
    private Notification.Notifier notifier;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public  String upload(File file,String fileName) throws  IOException {
        BufferedOutputStream stream = null;
        String globalPath="C:\\xampp\\htdocs\\PhpstormProjects\\Pidev3\\web\\uploads\\images";
        String localPath="localhost:80/";
       /*fileName = file.getName();
        fileName=fileName.replace(" ", "_");*/
        try {
            Path p = file.toPath();
            
            byte[] bytes = Files.readAllBytes(p);
    
            File dir = new File(globalPath);
            if (!dir.exists())
                dir.mkdirs();
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath()+File.separator + fileName);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return localPath+"/"+fileName;
        } catch (IOException ex) {
            Logger.getLogger(AjoutDemandeController.class.getName()).log(Level.SEVERE, null, ex);
            return "error2";
        
        }}
    @FXML
    public void addimage(ActionEvent event) throws MalformedURLException, NoSuchAlgorithmException, IOException {

        System.out.println("Load Image Button Pressed");
        fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(demandepanes.getScene().getWindow());
        if (file != null) {
            
            System.out.println("File Was Selected");
            URL url = file.toURI().toURL();
            String urlimg = url.getFile().replaceFirst("/", "");
            //  System.out.println("url = "+urlimg);

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] urlmd5 = md.digest(urlimg.getBytes());
            final String result = new String(Hex.encodeHex(urlmd5))+".jpg";
            upload(file,result);
            //    System.out.println("url md5 = "+result);
            p.setImage(result);
            imgdemande.setImage(new Image(url.toExternalForm()));

        }
        
        
    }
    @FXML
    public void ajouterdemandebtn(){
      
        DemandeRecommandation dem=new DemandeRecommandation();
        p.setNom(titledem.getText());
        p.setDescripion(descripdem.getText());
        p.setAddresse(adressdem.getText());
        p.setIduser(UserInterfaceController.userid);
        dem.ajouterDemande(p);
      notifier = Notification.Notifier.INSTANCE; 
        notifier.notify(NOTIFICATIONS[2]);
      /*  notifier = NotifierBuilder.create()
            //.popupLocation(Pos.TOP_RIGHT)
            //.popupLifeTime(Duration.millis(10000))
            //.styleSheet(getClass().getResource("mynotification.css").toExternalForm())
            .build();*/
        
    }

    @FXML
     public void closeApp(){
        
          // get a handle to the stage
    Stage stage = (Stage) btnclose.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
       @FXML
    private void BackHome(MouseEvent event) throws IOException {
      
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/DemandeRecommandation.fxml"));
    Parent root=loader.load();
        ajouterdemande.getScene().setRoot(root);
      
  
        
        
    }
}
