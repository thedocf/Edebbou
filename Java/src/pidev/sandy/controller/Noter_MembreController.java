/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
 
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.mail.search.DateTerm;
import jdk.nashorn.internal.parser.TokenType;
import org.controlsfx.control.Notifications;
import static pidev.sandy.controller.ModifierCadeauxController.isInteger;
import pidev.sandy.entites.Compte;
import pidev.sandy.entites.DemandeCadeau;
import pidev.sandy.entites.User;
import pidev.sandy.services.AdminService;
import pidev.sandy.services.MyServices;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class Noter_MembreController implements Initializable {

    @FXML
    private AnchorPane PanePrincipale;
    @FXML
    private AnchorPane PaneNoter;
    @FXML
    private JFXTextField NombreCommentaire;
    @FXML
    private JFXTextField NombreExperience;
    @FXML
    private JFXButton Confirmer;
    @FXML
    private JFXTextField NombreBonPlan;
    @FXML
    private JFXButton Retour;
    @FXML
    private JFXTextField Username;
    @FXML
    private JFXTextField point;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXTextField Noter;
    
    boolean verificationNombreType=false;
    
    

        MyServices services = new MyServices();
        AdminService adminService = new AdminService();
              User useruseraffecter = ListeUtlisateurFXMLController.useraffecter;
                    Compte compte = services.chercherUtilisateurByUsernameDansLecompte(useruseraffecter.getUsername());
    @FXML
    private JFXDatePicker DateFin;
    @FXML
    private JFXDatePicker DateDebut;
    @FXML
    private JFXButton choiBtsirDeuxDates;
    
    
    
        List<String> listType=new ArrayList<>();
         private ObservableList<String> ObservablelistType;
    @FXML
    private JFXComboBox<String> ComboBoxType;
    @FXML
    private JFXTextField NombreType;
    @FXML
    private JFXTextField NoteFinale;
    @FXML
    private Label labelNombreType;

    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


  

  

        Username.setText(useruseraffecter.getUsername());
        Email.setText(useruseraffecter.getEmail());
        point.setText(String.valueOf(compte.getPoint_merci()));
        Noter.setText("0");
        Username.setEditable(false);
        Email.setEditable(false);
        point.setEditable(false);
        NombreBonPlan.setEditable(false);
        NombreExperience.setEditable(false);
        NombreCommentaire.setEditable(false);
        point.setEditable(false);
        
        
        
        
     
//          Date dateo = Date.valueOf(DateCreation.getValue());
   
Date DateDebut1=new Date(2000, 04, 22);
Date DateFin1=new Date(2022, 04, 22);
        NombreBonPlan.setText(String.valueOf(adminService.get_Number_BonPlanUser(useruseraffecter.getId())));
        NombreExperience.setText(String.valueOf(adminService.get_Number_OffreExperience(useruseraffecter.getId(),DateDebut1,DateFin1)));
        NombreCommentaire.setText(String.valueOf(adminService.get_Number_CommentUser(useruseraffecter.getId(),DateDebut1,DateFin1)));

    }
 
    @FXML
    private void choisirDeuxDate(ActionEvent event) {
        
        
             Alert alert = new Alert(Alert.AlertType.ERROR);
           
        
          
Date DateDebut1 = Date.valueOf(DateDebut.getValue());
Date DateFin1 = Date.valueOf(DateFin.getValue());

        if (DateDebut1.before(DateFin1)) {
            
            System.out.println("DateDebut1<DateFin1");
               NombreBonPlan.setText(String.valueOf(adminService.get_Number_BonPlanUser(useruseraffecter.getId())));
        NombreExperience.setText(String.valueOf(adminService.get_Number_OffreExperience(useruseraffecter.getId(),DateDebut1,DateFin1)));
        NombreCommentaire.setText(String.valueOf(adminService.get_Number_CommentUser(useruseraffecter.getId(),DateDebut1,DateFin1)));
        } else {
            
            
            
             alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez choisir Date Debut < Date Fin");
                            alert.show();
                            DateDebut1.toString();  
            
        }






       
    }

    @FXML
    private void ChargerComboBoxListeDesType(MouseEvent event) {
            
//                     Cadeaux cadeaux= (Cadeaux)comboBoxSelectionneCadeau.getSelectionModel().getSelectedItem();
                     
                      listType.add("Commentaire");
                      listType.add("Bon Plan");
                      listType.add("Partge experience");
                     ObservablelistType=FXCollections.observableArrayList(listType);
                      ComboBoxType.setItems(ObservablelistType);
        
    }

    
    
    
    
    
    
 
  
     
    
    
  
    private boolean controlabelNombreType() {
 
            int nbChar = 0;
            for (int i = 0; i <  NombreType.getText().trim().length(); i++) {
                char ch = NombreType.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                System.out.println(nbChar);
            }

            if (nbChar == 0) {
                  
        
                labelNombreType.setText("number valide");
        
                return true;
            } else {  
                labelNombreType.setText("invalide number \n"
                        + " Il exist des char");
                     return false;

            }
    }

        
      
 

   

    @FXML
    private void CalculerPointParRapportType(KeyEvent event) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
        
        
             
             
             
             
        
        if (ComboBoxType.getValue()==null)
        {
            alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez choisir le Type pour estimer le note");
                            alert.show();
                            ComboBoxType.requestFocus();
        }
        
            else  if (NombreType.getText()==null)
        {
            alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez choisir le Nombre de"+ComboBoxType.getSelectionModel().getSelectedItem());
                            alert.show();
                            NombreType.requestFocus();
        }
            
                
            
               
                  else if (controlabelNombreType()==false)
        
            { alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText(" nombre invalide");
                            alert.show();
                            NombreType.requestFocus();
       }
        
            else 
            {
                
                
        
        
        String Type = ComboBoxType.getSelectionModel().getSelectedItem();
        Integer Nombre =Integer.parseInt(NombreType.getText().trim());
        Integer Point;
        
        if (Type.equals("Commentaire")) {
            
            Point=Nombre*3;
            
        } else if (Type.equals("Bon Plan")) {
            
            Point=Nombre*10;
        }
        else
        {
        Point=Nombre*5;
        }
        
        NoteFinale.setText(String.valueOf(Point));
        
        
       
        }
        
    }

   
    @FXML
    private void handleButtonConfirmer(ActionEvent event) throws IOException {
  
        
          Alert alert = new Alert(Alert.AlertType.ERROR);
        
        
        
        if (Integer.parseInt(Noter.getText())==0)
        {
            alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez entre la Note ");
                            alert.show();
                            Noter.requestFocus();
        }
        
       
     
 
            else
            {
                
                  System.out.println("avant affecter"+compte.toString());
       
              compte.setPoint_merci(compte.getPoint_merci()+(Integer.valueOf(Noter.getText().trim())));
     
      
              
                services.NoterMembre(compte, compte.getIdCompte());

 
                System.out.println("Apre affecter"+compte.toString());
         setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ListeUtlisateurFXML.fxml")));
                
                  
                     
                
            }}

    @FXML
    private void handleButtonAnnuler(ActionEvent event) throws IOException {
     setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ListeUtlisateurFXML.fxml")));
    }

    
 
    
    
    
    
    
    
  
    
    
      
   
    private void setNode(Node node) {

        PanePrincipale.getChildren().clear();
        PanePrincipale.getChildren().add((Node) node);
 
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    
    
    
    

  

}
}