/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import entite.Anonnce;
import entite.Fos_user;
import service.Annoncesservice;
import service.Fosuserservice;
import service.UserSession;
import util.ConnexionBD;

/**
 * FXML Controller class
 *
 * @author Fida
 */
public class BackanController implements Initializable {
   private Connection con;
    @FXML
    private AnchorPane parent1;
    @FXML
    private Button logout;
    @FXML
    private Button deleteAnnonce;
    @FXML
    private TextField tfNomAnnonce;
    @FXML
    private TextField tfIdAnnonce;
    @FXML
    private TextField tfUserId;
    @FXML
    private TextArea tfDescriptionAnnonce;
    @FXML
    private Button btnAjouterAnnonce1;
    @FXML
    private Button photo;
    @FXML
    private ImageView imageview;
    @FXML
    private Button PDF1;
    @FXML
    private Button trier;
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
    public BackanController() {
       con =  ConnexionBD.getInstance().getConnection();
    }
    @FXML
    private PieChart piechart;
    @FXML
    private Label caption;
    @FXML
     private TableView<Anonnce> table;
    @FXML
    private TableColumn<Anonnce, String> textt;
    @FXML
    private TableColumn<Anonnce, String> photoo;
    @FXML
    private TableColumn<Anonnce, String> Userr;
    @FXML
    private TableColumn<Anonnce, String> aimee;
    
            UserSession n = UserSession.getInstance();
 private Anonnce cc=null;
          private Anonnce c1=null;
           String img="";
           String imggg="";
      List<String>type;
    @FXML
    private TableColumn<?, ?> aimee1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        type=new ArrayList<>();
            type.add("*.jpg");
            type.add("*.png");   
       String imgg =n.getEmail();
      
      try {
                 Statement stmt1 = con.createStatement();
        ObservableList<PieChart.Data>pieData = FXCollections.observableArrayList();
                              String SQL1 = "SELECT fos_user.username , COUNT(*) AS \"Announce\" FROM fos_user INNER JOIN annonce ON fos_user.id = annonce.creator GROUP BY annonce.creator";
                               ResultSet rs1 = stmt1.executeQuery(SQL1);
                               while(rs1.next())
                                {
                                   pieData.add(new PieChart.Data("Announce creator : "+rs1.getString(1),rs1.getDouble(2)));
                                           
                                }
       
       piechart.setData(pieData);
                        
caption.setTextFill(Color.DARKORANGE);
caption.setStyle("-fx-font: 12 arial;");

for (final PieChart.Data data : piechart.getData()) {
    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                int i = (int) data.getPieValue();
                caption.setText(String.valueOf("Announce : "+i));
             }
        });
}
      }
      catch (SQLException ex) {
            Logger.getLogger(BackanController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        try {
            
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(BackanController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          
    }    
    private void afficher() throws SQLException { 
        
    Annoncesservice sp = new Annoncesservice (); 
    List Annonce = sp.readAll(); 
    ObservableList et=FXCollections.observableArrayList(Annonce);
       table.setItems(et); 
       textt.setCellValueFactory(new PropertyValueFactory<>("description"));
       photoo.setCellValueFactory(new PropertyValueFactory<>("photo")); 
       Userr.setCellValueFactory(new PropertyValueFactory<>("a"));
       aimee.setCellValueFactory(new PropertyValueFactory<>("title"));
       
       aimee1.setCellValueFactory(new PropertyValueFactory<>("date"));
       
    
}



    @FXML
    private void logout(ActionEvent event) throws IOException {
        n.cleanUserSession(); 
       
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/login.fxml"));
            Parent root= loader.load();  
            logout.getScene().setRoot(root);
    }

    @FXML
    private void deleteAnnonce(ActionEvent event) throws SQLException{
          Annoncesservice cs = new Annoncesservice();
        cc = (Anonnce)table.getSelectionModel().getSelectedItem();
         if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir annonce");
                   
        }else{   
                
            
                 cs.delete(cc.getId());
                afficher();
            
           JOptionPane.showMessageDialog(null, "annonce supprimer");
          
        cc=null;  
            cc=null;
            
        
    }
               }

    @FXML
    private void AjouterAnnonce(ActionEvent event)  throws SQLException, IOException {
          String mail=n.getEmail();
        Fosuserservice  cs = new Fosuserservice ();
        Fos_user uu = new Fos_user(); 
        uu.setEmail(mail);
        Fos_user u =cs.getuser(uu); 
        String title = tfNomAnnonce.getText(); 
        String anoncee = tfDescriptionAnnonce.getText(); 
         String pphoto = img; 
         Annoncesservice an = new Annoncesservice();
         Anonnce a = new Anonnce(u,title,anoncee,pphoto);
         an.ajouter(a);
       
         JOptionPane.showMessageDialog(null, "L'annonce ajoutée");   
                 AnchorPane pane = FXMLLoader.load(getClass().getResource("backan.fxml"));
         parent1.getChildren().setAll(pane);
    }

    @FXML
    private void import_image(ActionEvent event) {
             FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
            img=fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img);
            imageview.setImage(i);
        }
    }



    @FXML
      public void imprimer(ActionEvent event) throws DocumentException {

    System.out.println("Exporting!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.piechart;
//    root.setLayoutX(30);
//    root.setLayoutY(20);
           job.printPage(root);
           job.endJob();
           }
            
       

    
        }

    @FXML
    private void trier(ActionEvent event) throws SQLException {
         Annoncesservice sp = new Annoncesservice (); 
    List Annonce = sp.Tri(); 
    ObservableList et=FXCollections.observableArrayList(Annonce);
       table.setItems(et); 
       textt.setCellValueFactory(new PropertyValueFactory<>("description"));
       photoo.setCellValueFactory(new PropertyValueFactory<>("photo")); 
       Userr.setCellValueFactory(new PropertyValueFactory<>("a"));
       aimee.setCellValueFactory(new PropertyValueFactory<>("title"));
       
       aimee1.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void home(ActionEvent event) throws IOException {
                     FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/homeback.fxml"));
            Parent root= loader.load();
  
            Ghome.getScene().setRoot(root);
    }

    @FXML
    private void Gdepotsss(ActionEvent event) throws IOException {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherdepots.fxml"));
            Parent root= loader.load();  
            Gdepotsss.getScene().setRoot(root);
    }

    @FXML
    private void livreurs(MouseEvent event) {
    }

    @FXML
    private void Glivreur(ActionEvent event) {
    }

    @FXML
    private void products(MouseEvent event) {
    }

    @FXML
    private void GProduit(ActionEvent event) throws IOException {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/ProduitBack.fxml"));
            Parent root= loader.load();  
            GProduit.getScene().setRoot(root);
    }

    @FXML
    private void categoriess(MouseEvent event) {
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

