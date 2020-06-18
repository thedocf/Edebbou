/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.DocumentException;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import entite.Anonnce;
import entite.Fos_user;
import entite.commentaire;
import service.Annoncesservice;
import service.Fosuserservice;
import service.ServiceCommentaire;
import service.UserSession;
/**
 * FXML Controller class
 *
 * @author Fida
 */
public class CommentController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private Label annonce;
    @FXML
    private Label userr;
    @FXML
    private ImageView image;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> comentaireee;
    @FXML
    private TableColumn<?, ?> writer;
    @FXML
    private Button ajou;
    @FXML
    private Button suppp;
    @FXML
    private Button upda;
    @FXML
    private TextField commmmmm;
    @FXML
    private Text writerrrr;
    @FXML
    private Text iddddddddddddddd;
    @FXML
    private Text kop;
    
     private Anonnce selectedAnnonce; 
    private commentaire cc=null;
    UserSession n = UserSession.getInstance();
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
    private Button search;
    @FXML
    private AnchorPane parent1;
    @FXML
    private Button PDF1;
    @FXML
    private GridPane Grid;
    @FXML
    private Button login;
    @FXML
    private Button profile1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table.setOnMouseClicked(new EventHandler<MouseEvent>()
                
        { 
            @Override
            public void handle(MouseEvent event) { 
                 
                cc = (commentaire)table.getSelectionModel().getSelectedItem(); 
                commmmmm.setText(cc.getComentaire());
                writerrrr.setText(cc.getA());
                int od=cc.getId_commentaire();   
                String odd=String.valueOf(od);
                iddddddddddddddd.setText(odd);   
                
                
                
                
            }});
        try {
            afficher();
            
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
           

  

     private void afficher() throws SQLException { 
        
    ServiceCommentaire sp = new ServiceCommentaire(); 
    List commentaire = sp.readAll(); 
    ObservableList et=FXCollections.observableArrayList(commentaire);
       table.setItems(et); 
       comentaireee.setCellValueFactory(new PropertyValueFactory<>("comentaire"));
       writer.setCellValueFactory(new PropertyValueFactory<>("a")); 
     
       
    } 
       public void initData(Anonnce aa) { 
    selectedAnnonce = aa; 
    annonce.setText(selectedAnnonce.getDescription());
    
    Image i = new Image(selectedAnnonce.getImage());
    image.setImage(i);  
    String n= selectedAnnonce.getId_user().getUsername();
    userr.setText(n);  
    int od=selectedAnnonce.getId();
                String odd=String.valueOf(od);
                kop.setText(odd);   
                
    
    }
    @FXML
    private void ajou(ActionEvent event)  throws SQLException, IOException {
        
        Fosuserservice cs = new Fosuserservice();
        Fos_user uu = new Fos_user(); 
            uu.setEmail(n.getEmail());
            Fos_user u =cs.getuser(uu); 
        String comment = commmmmm.getText(); 
         ServiceCommentaire an = new ServiceCommentaire();  
         int i= Integer.parseInt(kop.getText());
         Annoncesservice l = new Annoncesservice(); 
         
         commentaire a = new commentaire(u,l.getannonce_id(i),comment); 
         an.ajouter(a);    
       Image img = new Image("https://img.icons8.com/cotton/2x/appointment-reminders.png");
      Notifications NotificationBuilder =  Notifications.create()
               .title("Comment")
              .text("New Comment!")
              .graphic(new ImageView(img))
              .hideAfter(Duration.seconds(5));
      NotificationBuilder.show(); 
        FXMLLoader loader = new FXMLLoader();  
        loader.setLocation(getClass().getResource("comment.fxml"));
        AnchorPane parent = loader.load(); 
        Scene tableviewscene = new Scene(parent);  
        
         int oddd = Integer.parseInt(kop.getText()); 
                  Anonnce aaa = new Anonnce();
        aaa.setId(oddd); 
       Annoncesservice saa = new Annoncesservice();  
       Anonnce kkk= new Anonnce(); 
                try {
                    kkk =saa.getannonce_id(aaa.getId());
                     cc=null; 
        commmmmm.clear();
                    
            afficher();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
       
    }

    @FXML
    private void suppp(ActionEvent event)throws SQLException {
        ServiceCommentaire cs = new ServiceCommentaire(); 
         cc = (commentaire)table.getSelectionModel().getSelectedItem();
         if(writerrrr.getText().equals(n.getUserName())) {
        commentaire f = new commentaire(cc.getId_commentaire());
        int pm = f.getId_commentaire();
        cs.delete(pm);
            afficher();
        JOptionPane.showMessageDialog(null, "commentaire supprimer");
        cc=null; 
        commmmmm.clear();
        writerrrr.setText(null); } else {JOptionPane.showMessageDialog(null, "Le commentaire ne vous appartient pas, vou ne pouvez pas le supprimer"); 
            cc=null; 
         commmmmm.clear();
        writerrrr.setText(null);
         }
    }

    @FXML
    private void updat(ActionEvent event)  throws SQLException {
        ServiceCommentaire cs = new ServiceCommentaire(); 
         if(writerrrr.getText().equals(n.getUserName())) {
        String commme = commmmmm.getText();    
         cs.update(new commentaire(commme),cc.getId_commentaire());
            afficher();
        JOptionPane.showMessageDialog(null, "commentaire modifier");
        cc=null; 
        commmmmm.clear();
        writerrrr.setText(null); } 
         
        else {
             JOptionPane.showMessageDialog(null, "Le commentaire ne vous appartient pas, vou ne pouvez pas le modifier"); 
            cc=null; 
         commmmmm.clear();
        writerrrr.setText(null);
         }
    }

      @FXML
    private void profile(ActionEvent event) throws IOException {
                
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/gui/profile_1.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
      
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
         n.cleanUserSession(); 
       
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/gui/login.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
    }

   

    @FXML
    public void imprimer(ActionEvent event) throws DocumentException {

    System.out.println("Exporting!");
         PrinterJob job = PrinterJob.createPrinterJob();
        
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.parent1;
 
           job.printPage(root);
           job.endJob();
           }
            
       

    
        }

   

        private void home(ActionEvent event)  throws IOException{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/javafxapplication4/FXMLDocument.fxml"));
            Parent root= loader.load();
  
            parent1.getScene().setRoot(root);
    }

    @FXML
    private void tocart(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/panier.fxml"));
            Parent root= loader.load();
  
            parent1.getScene().setRoot(root);
    }

   

    @FXML
    private void onmouseexit(MouseEvent event) {
    }

    @FXML
    private void Onmouseenter(MouseEvent event) {
    }

    @FXML
    private void annonce(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/listFXML.fxml"));
            Parent root= loader.load();
  
            parent1.getScene().setRoot(root);
    }

    @FXML
private void product(ActionEvent event) throws IOException {
       FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/ShoppingP.fxml"));
        Parent root= loader.load();

        parent1.getScene().setRoot(root);
}

    @FXML
    private void checkout(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/commande.fxml"));
            Parent root= loader.load();
            parent1.getScene().setRoot(root);
    }
  
  
    
}
