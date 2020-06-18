/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import pidev.sandy.entites.BonPlan;
import pidev.sandy.entites.Boncadeaux;
import pidev.sandy.entites.Cadeaux;
import pidev.sandy.entites.Categoris;
import pidev.sandy.entites.Compte;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
import pidev.sandy.services.ServiceSysdate;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class AjouterCadeauxController implements Initializable {

    private static Font taille15B = new Font(Font.FontFamily.TIMES_ROMAN, 15,
            Font.BOLD);
    private static Font taille12NORMALRed = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font taille12NORMA = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);
 
    
       public static int numeroCommande = 0;
    Document doc = new Document();
    
    
    
    @FXML
    private ComboBox<String> comboBoxSelectionnerPartenaire;
    @FXML
    private TableView<BonPlan> tableViewBonPlan;
    @FXML
    private TableColumn<BonPlan, String> tableColumnLibelle;
    @FXML
    private TableColumn<BonPlan, String> tableColumnCategoris;
    @FXML
    private TableColumn<BonPlan, String> tableColumnDescription;
 
    @FXML
    private ComboBox<String> comboBoxSelectionnerBonPlan;
 
    
    private List<String> listPartnaire;
    private List<BonPlan> listPlan;
    private ObservableList<String> ObservableListPartnaire;
    private ObservableList<String> ObservableListBonPlan;
    private ObservableList<BonPlan> ObservableListBonPlanTableView;
    
    
      private Stage stage;//controle de classe(annuler ou fermer) stage(serie)
    private boolean buttonConfimClicked = false;//nous informer est ce que on clicker sur confimer ou annuler
    
    private Cadeaux cadeaux;
    private Boncadeaux boncadeaux;
         MyServices services = new MyServices();
         private boolean  Ajout = false;
    @FXML
    private ImageView PartenaireCheck;
    @FXML
    private ImageView DateCheck;
    @FXML
    private ImageView PrixCheck;
    @FXML
    private ImageView TypeBonCheck;
    @FXML
    private ImageView quantiteCheck;
    @FXML
    private ImageView ValeurCheck;
    @FXML
    private ImageView BonPlanCheck;

    public boolean getAjout() {
        return Ajout;
    }

    public void setAjout(boolean Ajout) {
        this.Ajout = Ajout;
    }

  
         
         
    
    @FXML
    private DatePicker datePickerAjoutCadeaux;
    @FXML
    private TextField TXValeurPointCadeaux;
    @FXML
    private TextField TXValeurPrixReel;
    @FXML
    private TextField TXTypeBonCadeaux;
    @FXML
    private Button buttonConfirmer;
    @FXML
    private Button BtAnuller;
    @FXML
    private JFXTextField TXQuantite;
 
         
         
 
  
      public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isButtonConfimClicked() {
        return buttonConfimClicked;
    }

    public void setButtonConfimClicked(boolean buttonConfimClicked) {
        this.buttonConfimClicked = buttonConfimClicked;
    }

    public Cadeaux getCadeaux() {
        return cadeaux;
    }

    public void setCadeaux(Cadeaux cadeaux) {

        this.cadeaux = cadeaux;
 
       
    }

    public Boncadeaux getBoncadeaux() {
        return boncadeaux;
    }

    public void setBoncadeaux(Boncadeaux boncadeaux) {
        this.boncadeaux = boncadeaux;
    }
    
    
    
        public static boolean isInteger(String s) {
    try { 
        Integer.parseInt(s); 
    } catch(NumberFormatException e) { 
        return false; 
    } catch(NullPointerException e) {
        return false;
    }
    return true;
}
    
    public void ChargerComboBoxListeDesPartenaire()
            {            
               
 
                    String ROLE="a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}";
                     listPartnaire=services.afficherlistePartenaire(ROLE);
                    if (listPartnaire.equals("")) {
                    
                     Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text(" Tu ne peut pas demander des cadeaux il y n'est des partenaire");
                n.showError();
                } else {
                               
                     Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("Choisir un Bon Plan pour Ajouter un cadeaux");
                n.showError(); 
                       ObservableListPartnaire=FXCollections.observableArrayList(listPartnaire);
                     comboBoxSelectionnerPartenaire.setItems(ObservableListPartnaire); 
                }
                    
            
            }
    
   

    /**
     * Initializes the controller class.
     */
     private  static MyServices myServices=new MyServices();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        ChargerComboBoxListeDesPartenaire();
        TXTypeBonCadeaux.setText("demande");
        TXTypeBonCadeaux.setEditable(false);
        TXTypeBonCadeaux.isDisable();
 
            MyServices services = new MyServices();
    }    


       ServiceSysdate sys = new ServiceSysdate();
    @FXML
    private void handleButtonConfirmer(ActionEvent event) throws IOException {
  
        
          Alert alert = new Alert(Alert.AlertType.ERROR);
        
       
        
        if (comboBoxSelectionnerPartenaire.getValue()==null)
        { PartenaireCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
      
                            
                            
                                                  
                                    Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("  Veillez choisir le partenaire ");
                n.showError();
        }
        
           else  if (comboBoxSelectionnerBonPlan.getValue()==null)
        {BonPlanCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
          
                            
                            
                                                  
                                    Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("  Veillez choisir le bon plan ");
                n.showError();
        }
     
        else if (datePickerAjoutCadeaux.getEditor().getText().equals(""))
        {DateCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
  
                            
                            
                                                  
                                    Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("  Veillez enter la date de votre demande");
                n.showError();
        }
        
        
        
  
          else
        { 
            Date dateo = Date.valueOf(datePickerAjoutCadeaux.getValue());
            if (dateo.equals(sys.selectDate()))
            {
                DateCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
                
              System.out.println(datePickerAjoutCadeaux.getEditor().getText());
            System.out.println(sys.selectDate());
  
                            
                                             
                                                  
                                    Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("verifier que  la date de votre Demande est egale à la date actuelle");
                n.showError();
                            
        }
  
        
//          
// 
//       else if (TXTypeBonCadeaux.getText().equals(""))
//        {TypeBonCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
// 
//                            
//                            
//                            
//                                                  
//                                    Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
//                      .darkStyle()
//                      .text(" veillez enter la  Type BonCadeauxux ");
//                n.showError();
//        }
               
               
               
       else if (TXValeurPrixReel.getText().equals(""))
        {PrixCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
                
                                    Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text(" veillez enter la Valeur Prix Reel ");
                n.showError();
        }
       
              else if (!isInteger(TXValeurPointCadeaux.getText()))
        {    PrixCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
                Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("  Le prix reel du cadeaux doit être un entier ");
                n.showError();          
          
        }

       
       else if (TXValeurPointCadeaux.getText().equals(""))
        {ValeurCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
    
                            
                            
                                                  
                                    Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("  Veillez enter la point du cadeaux ");
                n.showError();
        }
       
 
       else if (TXTypeBonCadeaux.getText().equals(""))
        {TypeBonCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
 
                            
                            
                                                  
                                    Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("  Veillez enter la Type BonCadeaux");
                n.showError();
        }
       else if (TXQuantite.getText().equals(""))
        {quantiteCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
      
                            
                            
                            
                                    Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("  Veillez enter la quantite demander du cadeaux ");
                n.showError(); 
        }
       
       else if (!isInteger(TXQuantite.getText()))
        {    quantiteCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
        
                Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("  Le Quantite doit être un entier ");
                n.showError();          
          
        }
       else if (!isInteger(TXValeurPointCadeaux.getText()))
        {    ValeurCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
                Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("  Le Valeur du point du cadeaux doit être un entier ");
                n.showError();          
          
        }

        
        
                         else if (!isInteger(TXValeurPointCadeaux.getText()))
        {    ValeurCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
                Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("  Le valeur du point du cadeaux doit être un entier ");
                n.showError();          
          
        }
          
   
            
            
            
            else
            {
                String username = comboBoxSelectionnerPartenaire.getSelectionModel().getSelectedItem();
                String  bonPlanlLibelle=  comboBoxSelectionnerBonPlan.getSelectionModel().getSelectedItem();
                User user = services.chercherUtilisateurByUsername(username);
               
               
                
                /************************Compte******************************/
                Compte compte = services.chercherUtilisateurByUsernameDansLecompte(username);
                /************************Creation de la cadeau******************************/
                
          
                
          //     `libelle`, `description`, `prix_reel`, `valeur_point`,
         //   `quantite_initial`, `quantite_actuel`, `image`, `categorisCadeaux`)

 
                 BonPlan bonPlan=services.chercherBonPlanBylibelle(bonPlanlLibelle);
    
                
                
                Cadeaux cadeauxAjout=services.chercherCadeauxByLibelle(bonPlan.getLibelle());
                  
                 if (cadeauxAjout.getId()==0) {
                      System.out.println("Ajout====>"+cadeauxAjout.toString());
                       cadeaux.setLibelle(bonPlan.getLibelle());
                 cadeaux.setDescription(bonPlan.getDescription());
                 cadeaux.setPrix_reel(Float.valueOf(TXValeurPrixReel.getText()) );
                cadeaux.setValeur_point(Float.valueOf(TXValeurPointCadeaux.getText()) ); 
                 cadeaux.setQuantite_actuel(Integer.valueOf(TXQuantite.getText()) ); 
                  cadeaux.setQuantite_initial(Integer.valueOf(TXQuantite.getText())); 
                 cadeaux.setImage(bonPlan.getImage()) ; 
                 Categoris categoris=new Categoris();
                 categoris.setId(bonPlan.getRefcategorie().getId());
                 cadeaux.setCategorisCadeaux(categoris); 
                     Ajout=true;
                  
                }
                 
                else
                    
                 
                     
                 {  
   
                     
                     
                     System.out.println("Modification====>"+cadeauxAjout.toString());
                 cadeaux.setId(cadeauxAjout.getId());
                             cadeaux.setLibelle(bonPlan.getLibelle());
                 cadeaux.setDescription(bonPlan.getDescription());
                 cadeaux.setPrix_reel(Float.valueOf(TXValeurPrixReel.getText()) );
                cadeaux.setValeur_point(Float.valueOf(TXValeurPointCadeaux.getText()) ); 
                     System.out.println("TXQuantite.getText()"+(Integer.valueOf(TXQuantite.getText())));
                     System.out.println("cadeauxAjout.getQuantite_actuel())"+cadeauxAjout.getQuantite_actuel());
                 cadeaux.setQuantite_actuel((Integer.valueOf(TXQuantite.getText()))+cadeauxAjout.getQuantite_actuel() ); 
                 cadeaux.setQuantite_initial(cadeauxAjout.getQuantite_initial()); 
                 cadeaux.setImage(bonPlan.getImage()) ; 
                 Categoris categoris=new Categoris();
                 categoris.setId(bonPlan.getRefcategorie().getId());
                 cadeaux.setCategorisCadeaux(categoris);
                     
                      Ajout=false;
                 
                 }
                 
              
            System.out.println("Ajouttttt====>"+Ajout);

            if (Ajout==true) {
                services.ajouterCadeaux(cadeaux);
            } else {
                
                 services.modifierCadeaux(cadeaux);
            }
                   
      
         cadeaux=services.chercherCadeauxByLibelle(cadeaux.getLibelle());
            
                
                
                
                
                
                       
                   boncadeaux.setCadeaux(cadeaux);
                 boncadeaux.setMembreConcerne(compte);         
                 boncadeaux.setDate_cadeaux(datePickerAjoutCadeaux.getValue().toString());
                 boncadeaux.setType_bonBoncadeaux(TXTypeBonCadeaux.getText());
               boncadeaux.setDescriptionBoncadeaux("Demande du cadeaux");
           
           
//                              cadeaux.setQuantite_actuel(cadeaux.getQuantite_actuel() - 1);
//                //compte.setPoint_merci((int) (compte.getPoint_merci()- cadeaux.getValeur_point()));  
//                 
//                 boncadeaux.setCadeaux(cadeaux);
//                 boncadeaux.setMembreConcerne(compte);
//                 boncadeaux.setDescriptionBoncadeaux("Demande du cadeaux");
//                 boncadeaux.setType_bonBoncadeaux(TXYpeCadeaux.getText());
//                 boncadeaux.setDate_cadeaux(DateAffectationCadeaux.getValue().toString());
//                 
//                 System.out.println("Bon ====>"+boncadeaux.toString());
                   numeroCommande = numeroCommande + 1;
            String nom = "BonCadeaux" + numeroCommande + ".pdf";
            try {
                
                


                 

              SimpleDateFormat  date = new SimpleDateFormat("dd/MM/yyyy");
             SimpleDateFormat Heure = new SimpleDateFormat("hh:mm:ss");

                
                //Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
                PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("C:\\wamp64\\www\\TunisiaBonPlan2\\web\\uploads\\BonCadeaux\\"+ nom));
                doc.open();
                       Image img = Image.getInstance("C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\User.png");
             doc.add(img);
                doc.add(new Paragraph("                                                           "
                        + "                                                                                           "
                        + "    " + date.format(new java.util.Date()), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        taille12NORMALRed));

                doc.add(new Paragraph("                                                           "
                        + "                                                                                                 "
                        + "  " + Heure.format(new java.util.Date()), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        taille12NORMALRed));
                doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                doc.add(new Paragraph("                                    Bon de demande N°" + numeroCommande + " Ajouter un cadeaux dans le stock                                                         "));
                doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                doc.add(new Paragraph("Bon Plan",taille12NORMA));
                

         
         
  /*********************************Tableaux**********************************************/
              
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(60);
                table.setSpacingBefore(11f);
          
                Font f = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
     
                PdfPCell c1 = new PdfPCell(new Paragraph("Libelle", taille15B));
                 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                  PdfPCell c2 = new PdfPCell(new Paragraph("Quantité", taille15B));
                  c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell c3 = new PdfPCell(new Paragraph(boncadeaux.getCadeaux().getLibelle(), taille12NORMA));
              c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell c4 = new PdfPCell(new Paragraph(TXQuantite.getText(), taille12NORMA));
          c4.setHorizontalAlignment(Element.ALIGN_CENTER);
    
                table.addCell(c1);
                table.addCell(c3);
                table.addCell(c2);
                table.addCell(c4);
 
  /*********************************Tableaux Admin**********************************************/
     
                 User loggedUser = pidev.sandy.controller.LoginController.getInstance().getLoggedUser();
                 
        
  
 User UserConneter=myServices.chercherUtilisateurByid(loggedUser.getId());            
  
  
                PdfPTable tableAdmin = new PdfPTable(2);
                tableAdmin.setWidthPercentage(60);
                tableAdmin.setSpacingBefore(11f);
               
        
 
                PdfPCell cAdmin1 = new PdfPCell(new Paragraph("Admin"));
                 cAdmin1.setHorizontalAlignment(Element.ALIGN_CENTER);
                  PdfPCell cAdmin2 = new PdfPCell(new Paragraph(UserConneter.getUsername()));
                  cAdmin2.setHorizontalAlignment(Element.ALIGN_CENTER);
           
    
                tableAdmin.addCell(cAdmin1);
                tableAdmin.addCell(cAdmin2);
 
  
   /*********************************Tableaux User**********************************************/
                
   
   
                          PdfPTable tableUser= new PdfPTable(2);
                tableUser.setWidthPercentage(60);
                tableUser.setSpacingBefore(11f);
       
               
 
                PdfPCell cUser1 = new PdfPCell(new Paragraph("Partenaire"));
                 cUser1.setHorizontalAlignment(Element.ALIGN_CENTER);
            
                  PdfPCell cUser2 = new PdfPCell(new Paragraph(comboBoxSelectionnerPartenaire.getSelectionModel().getSelectedItem()));
                  cUser2.setHorizontalAlignment(Element.ALIGN_CENTER);
           
    
                tableUser.addCell(cUser1);
                tableUser.addCell(cUser2);
                
                
                  
                doc.add(table);
                doc.add(tableAdmin);
              doc.add(tableUser);

                doc.close();
                Desktop.getDesktop().open(new File("C:\\wamp64\\www\\TunisiaBonPlan2\\web\\uploads\\BonCadeaux\\" + nom));
                writer.close();

            } catch (DocumentException e) {
                e.printStackTrace();
            }
//            
          
                buttonConfimClicked = true;
 
            stage.close();

 
               
          
                
                  
                     
                
            }}
        }

    @FXML
    private void handleButtonAnnuler(ActionEvent event) {
            stage.close();
    }
 
 

    @FXML
    private void AfficherBonPlan(ActionEvent event) {
                      PartenaireCheck.setImage(new javafx.scene.image.Image("/pidev/sandy/ressources/img/checkMark.jpg"));

                String Username=comboBoxSelectionnerPartenaire.getSelectionModel().getSelectedItem();
                    User Partenaire=new User();
                    System.out.println(Username);
                    Partenaire=services.chercherUtilisateurByUsername(Username);
                     listPlan=services.afficherlisteBonPlanParUser22(Partenaire);
                     List<String> listeNameBonPlan=new ArrayList<>();
                     
      for (BonPlan bonPlan : listPlan) {
            listeNameBonPlan.add(bonPlan.getLibelle());
            System.out.println(bonPlan.toString());
            
        }
        if (listeNameBonPlan.isEmpty()) {
             Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text(" Tu ne peut pas demander des cadeaux ce partenaire n'a pas des bon plan");
                n.showError();
        } else {
    
                 Notifications n = Notifications.create().title("erreur").graphic(null).position(Pos.TOP_CENTER)
                      .darkStyle()
                      .text("Choisir un Bon Plan");
                n.showError();
            
            System.out.println(listeNameBonPlan.toString());
             ObservableListBonPlan=FXCollections.observableArrayList(listeNameBonPlan);
                          comboBoxSelectionnerBonPlan.setItems(ObservableListBonPlan);
        }
            
     
                     
                    
    }

    @FXML
    private void createtableviewBonPlan(ActionEvent event) {
        
        
                      BonPlanCheck.setImage(new javafx.scene.image.Image("/pidev/sandy/ressources/img/checkMark.jpg"));

        MyServices services = new MyServices();
        tableColumnLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));//nom reference pour la colonne
        tableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));//nom reference pour la colonne
        //reference pour la colonne
        tableColumnCategoris.setCellValueFactory(new PropertyValueFactory<>("refcategorie"));//reference pour la colonne
    
         
         
          
       String Username=comboBoxSelectionnerPartenaire.getSelectionModel().getSelectedItem();
                    User Partenaire=new User();
                    System.out.println(Username);
                    Partenaire=services.chercherUtilisateurByUsername(Username);
                     listPlan=services.afficherlisteBonPlanParUser22(Partenaire);
                     
                                        List<BonPlan> listeNameBonPlan=new ArrayList<>();
      
                     
                     ObservableListBonPlanTableView=FXCollections.observableArrayList(listPlan);
           
        tableViewBonPlan.setItems(ObservableListBonPlanTableView);
    }

    @FXML
    private boolean verfieprixreel(KeyEvent event) {
        
        if (TXValeurPrixReel.getText().equals(""))
        {PrixCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
     return false;
        }
       
              else if (!isInteger(TXValeurPointCadeaux.getText()))
        {    PrixCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
             return false;       
          
        }
           
          PrixCheck.setImage(new javafx.scene.image.Image("/pidev/sandy/ressources/img/checkMark.jpg"));
             return true;
    }

    @FXML
    private boolean verfiequantite(KeyEvent event) {
       if (TXQuantite.getText().equals(""))
        {quantiteCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
    return false;
        }
       
       else if (!isInteger(TXQuantite.getText()))
        {    quantiteCheck.setImage(new javafx.scene.image.Image("/pidev/sandy/ressources/img/checkMark.jpg"));
 return false;
          
        }
       
  
                 quantiteCheck.setImage(new javafx.scene.image.Image("/pidev/sandy/ressources/img/checkMark.jpg"));
   
         return true;
    }

    @FXML
    private boolean verfieValeurPoint(KeyEvent event) {
        
        if (TXValeurPrixReel.getText().equals(""))
        {ValeurCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
               return false;
        }
         
              else if (!isInteger(TXValeurPointCadeaux.getText()))
        {    ValeurCheck.setImage(new javafx.scene.image.Image("/pidev/sandy/ressources/img/checkMark.jpgverfieprixreel"));
          
          return false;
        }
        
                 ValeurCheck.setImage(new javafx.scene.image.Image("/pidev/sandy/ressources/img/checkMark.jpg"));

        return true;
    }

    @FXML
    private boolean verfieDate(ActionEvent event) {
        
        
         if (datePickerAjoutCadeaux.getEditor().getText().equals(""))
        {DateCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
   
        }
  
          else
        { 
            Date dateo = Date.valueOf(datePickerAjoutCadeaux.getValue());
            if (dateo.equals(sys.selectDate()))
            {
                DateCheck.setImage(new javafx.scene.image.Image("Images/erreurcheckMark.png"));
      
                    return true;             
        }
  
    }
         
         
         
                            
                     
              DateCheck.setImage(new javafx.scene.image.Image("/pidev/sandy/ressources/img/checkMark.jpg"));
     
     return true;
    }

    }
    
 
