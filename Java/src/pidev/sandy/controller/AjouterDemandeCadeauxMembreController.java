/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import pidev.sandy.entites.Cadeaux;
import pidev.sandy.entites.Compte;
import pidev.sandy.entites.DemandeCadeau;
import pidev.sandy.entites.User;
import pidev.sandy.services.AdminService;
import pidev.sandy.services.MyServices;
import pidev.sandy.services.ServiceSysdate;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class AjouterDemandeCadeauxMembreController implements Initializable {

    /**
     * Initializes the controller class.
     */
     MyServices services = new MyServices();
    
       ServiceSysdate sys = new ServiceSysdate();
    
   
    @FXML
    private JFXTextField TXCadeaux;
    @FXML
    private JFXTextField TXStatus;
    @FXML
    private JFXTextField TXMessage;
    @FXML
    private JFXTextField TXDescription;
    @FXML
    private JFXTextField TXMembredemande;
    @FXML
    private JFXDatePicker TXDatedexpiration;
    @FXML
    private JFXDatePicker TXDatedemande;
    @FXML
    private JFXButton buttonConfirmer;
    @FXML
    private JFXButton BtAnuller;
    @FXML
    private AnchorPane AnchorePanAjoutDemande;
 
    public void setTXCadeaux(String TXCadeaux) {
  
        this.TXCadeaux.setText(TXCadeaux);
    }

    public void setTXStatus(String TXStatus) {
 
        this.TXStatus.setText(TXStatus);
    }

    public void setTXMessage(String TXMessage) {
 
        this.TXMessage.setText(TXMessage);
    }

    public void setTXDescription(String TXDescription) {
 
        this.TXDescription.setText(TXDescription);
    }

    public void setTXMembredemande(String TXMembredemande) {
     
        this.TXMembredemande.setText(TXMembredemande);
    }

 

  
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        TXMembredemande.setEditable(false);
        TXCadeaux.setEditable(false);
        TXStatus.setEditable(false);
         
        
        
 
    }    

 
    @FXML
    private void handleButtonConfirmer(ActionEvent event) throws SQLException, IOException {
  
        
          Alert alert = new Alert(Alert.AlertType.ERROR);
        
        
        
        if (TXDatedemande.getEditor().getText().equals(""))
        {
            alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez choisir le date demande");
                            alert.show();
                            TXDatedemande.requestFocus();
        }
        
          else if (TXDatedexpiration.getEditor().getText().equals(""))
        {
            alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez choisir le date  date d'expiration");
                            alert.show();
                            TXDatedexpiration.requestFocus();
        }
        
       
             
          else    if ((Date.valueOf(TXDatedemande.getValue())).before(sys.selectDate()))
            {
                
              System.out.println(TXDatedemande.getEditor().getText());
            System.out.println(sys.selectDate());
             alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez verifier que  la date de votre demmande est superieur à la date actuelle");
                            alert.show();
                            TXDatedemande.requestFocus();
                            
        }
            
            
        
                
                 else if ((Date.valueOf(TXDatedexpiration.getValue())).before((Date.valueOf(TXDatedemande.getValue()))))
            {
                
              System.out.println(TXDatedemande.getEditor().getText());
            System.out.println(sys.selectDate());
             alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez verifier que  la date de votre expiration est superieur à la date demande");
                            alert.show();
                            TXDatedemande.requestFocus();
                            
        }
      
      
      
     
     
        
    
            else
            {DemandeCadeau demandeCadeau=new DemandeCadeau();
            
            demandeCadeau.setStatusDemandeCadeaux(TXStatus.getText());
            demandeCadeau.setDescriptionDemandeCadeaux(TXDescription.getText());
            demandeCadeau.setDatedemandeDemandeCadeaux(TXDatedemande.getEditor().getText());
            demandeCadeau.setDatedexpirationDemandeCadeaux(TXDatedexpiration.getEditor().getText());
            
            Compte compte=new Compte();
            compte=services.chercherUtilisateurByUsernameDansLecompte(TXMembredemande.getText());
            Cadeaux cadeaux=new Cadeaux();
            cadeaux=services.chercherCadeauxByLibelle(TXCadeaux.getText());
            demandeCadeau.setMembreDemande(compte);
            demandeCadeau.setCadeaux(cadeaux);
            demandeCadeau.setStatusDemandeCadeaux(TXStatus.getText());
           
      
            
                 
                 System.out.println("Bon ====>"+demandeCadeau.toString());
                 
                 
                services.AjouteDemandecadeauxMembre(demandeCadeau);
            
          
               
 
       
 setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ListeDesDemandeCadeauxMembre.fxml")));
 
               
          
                
                  
                     
                
            }}
     

    @FXML
    private void handleButtonAnnuler(ActionEvent event) throws IOException {
     setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ListeDesDemandeCadeauxMembre.fxml")));
    }

    
 
    
    
    
    
    
    
  
    
    
      
   
    private void setNode(Node node) {

        AnchorePanAjoutDemande.getChildren().clear();
        AnchorePanAjoutDemande.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    
    
    
    

  

}
    
}
