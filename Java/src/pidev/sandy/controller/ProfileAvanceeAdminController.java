/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import pidev.sandy.services.AdminService;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class ProfileAvanceeAdminController implements Initializable {

    @FXML
    private AnchorPane Homepane;
    @FXML
    private VBox vboxx;
    @FXML
    private Label reclamamtion;
    @FXML
    private JFXButton Partenaire;
    @FXML
    private JFXButton Membre;
    @FXML
    private Label Utlisateurs;
    @FXML
    private Label BonPlan;
    @FXML
    private Label Comment;
    @FXML
    private Pane BTGestionUtilisateur;
    @FXML
    private Pane btGestionCadeaux;
    AdminService adminService=new AdminService();
    @FXML
    private HBox groupHolder;
    @FXML
    private Group groupRegistered;
    @FXML
    private Group groupTarget;
    @FXML
    private Group groupGents;
    @FXML
    private Group groupLadies;
    @FXML
    private HBox groupHolder1;
    @FXML
    private Group groupRegistered1;
    @FXML
    private Group groupLadies1;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               setRipples();
 

 
        Utlisateurs.setText(String.valueOf(adminService.get_Number_User()));
        Comment.setText(String.valueOf(adminService.get_Number_Comment()));
        BonPlan.setText(String.valueOf(adminService.get_Number_User()));
        reclamamtion.setText(String.valueOf(adminService.get_Number_Reclamation()));
    }  
    
    
    private void setRipples() {
        JFXRippler rippler1 = new JFXRippler(groupRegistered);
        JFXRippler rippler2 = new JFXRippler(groupGents);
        JFXRippler rippler3 = new JFXRippler(groupLadies);
        JFXRippler rippler4 = new JFXRippler(groupTarget);
        rippler1.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler2.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler3.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler4.setMaskType(JFXRippler.RipplerMask.RECT);

        rippler1.setRipplerFill(Paint.valueOf("#1564C0"));
        rippler2.setRipplerFill(Paint.valueOf("#00AACF"));
        rippler3.setRipplerFill(Paint.valueOf("#00B3A0"));
        rippler4.setRipplerFill(Paint.valueOf("#F87951"));

        groupHolder.getChildren().addAll(rippler1, rippler2, rippler3, rippler4);
    }

//    private void setUpDepartments() {
//        departmentList.getItems().clear();
//        try {
//            connection = handler.getConnection();
//
//            String query = "SELECT department.`name` FROM department";
//            try (PreparedStatement pst = connection.prepareStatement(query)) {
//                rs = pst.executeQuery();
//
//                while (rs.next()) {
//                    String name = rs.getString("name");
//                    departments = FXCollections.observableArrayList(name);
//                    departmentList.getItems().addAll(departments);
//                }
//            }
//            rs.close();
//
//        } catch (Exception ex) {
//            Logger.getLogger(OverviewController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
//        }
//
//    }
//    
    
    
    
    
      private void setNode(Node node) {
        Homepane.getChildren().clear();
        Homepane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }


   
    
    
    @FXML
    private void GestionUtilisateur(MouseEvent event) throws IOException {
        
        
        
setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ListeUtlisateurFXML.fxml")));
        
        
        
    }

    @FXML
    private void GestionCadeaux(MouseEvent event) throws IOException {
        setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ListeCadeauxAdmin.fxml")));
    }

    @FXML
    private void AfficherNbrPartenaire(ActionEvent event) {
        
        Utlisateurs.setText(String.valueOf(adminService.get_Number_partenaire()));
    }

    @FXML
    private void AfficherNbrMembre(ActionEvent event) {
        
        
        Utlisateurs.setText(String.valueOf(adminService.get_Number_Membre()));
    }
 

    
}
