/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import pidev.sandy.entites.Categoris;
import pidev.sandy.services.ServiceCategorie;
import pidev.sandy.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class ModifierCategorieController implements Initializable {

    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField libelle;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton image;
    @FXML
    private JFXComboBox<String> categorie;
    @FXML
    private ImageView warning;
    @FXML
    private Label erreur;
    File selectedFile;
    private String path;
    @FXML
    private ImageView imageaff;
    int id;
    @FXML
    private AnchorPane anchorPanceModifierCategorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCategorie ps = new ServiceCategorie();
        warning.setVisible(false);
        erreur.setVisible(false);
     
        path=AfficherCategorieController.d.getImage();
        
        description.setText(AfficherCategorieController.d.getDiscription());
        libelle.setText(AfficherCategorieController.d.getLibelle());
        imageaff.setImage(new Image(AfficherCategorieController.d.getImage()));
     id=    AfficherCategorieController.d.getId();
        
        ///////////////////////////////
        List<String> myList = new ArrayList<String>();
        String sql = "SELECT * FROM categorie WHERE categorie.idcategoriemere is NULL";
        try {
            Statement result = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = result.executeQuery(sql);
            while (rs.next()) {
                myList.add(rs.getString(3));

            }
            ObservableList<String> olist = FXCollections.observableArrayList(myList);
            categorie.setItems(olist);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    @FXML
    private void image(ActionEvent event) {
        System.out.println(categorie.getValue());
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("Image", ".jpg", ".png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
//    
            image.setText(path);

        }

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
    
//         setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AfficherCategorie.fxml")));
          anchorPanceModifierCategorie.toBack();
    }

    @FXML
    private void modifierCategorie(ActionEvent event) throws IOException {
        
        
        
        
         Categoris c = new Categoris(0);
//            List<String> myList = new ArrayList<String>();
            if (categorie.getValue() != null) 
            {
                String sql = "SELECT * FROM categorie WHERE libelle=?";
                try {
                    PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(sql);
                    ps.setString(1, (String) categorie.getValue());
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        System.out.println(rs.getInt(1));
                        c = new Categoris(rs.getInt(1));
                    }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());

                }
            }
                Categoris p = new Categoris();
                   // p.setId(rs.getInt(1));

                p.setLibelle(libelle.getText());
                p.setDiscription(description.getText());
                p.setIdcategoriemere(c);
                p.setImage(path);
                p.setId(id);//**************************************************************************************************
//                    myList.add(rs.getString(3));
                ServiceCategorie ms = new ServiceCategorie();
                ms.modifierCategorie(p);

                if (selectedFile != null) 
                {
                    try {
                        //System.out.println(selectedFile.toString());
                        File source = new File(selectedFile.toString());
                        File dest = new File("C:\\wamp64\\www\\TunisiaBonPlan2\\web\\uploads\\images");

                        FileUtils.copyFileToDirectory(source, dest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (!(libelle.getText().isEmpty())) 
                {
                    String masque = "^[a-zA-Z]+$";
                    Pattern pattern = Pattern.compile(masque);
                    Matcher controler = pattern.matcher(libelle.getText());
                    if (!(controler.matches())) {
                        erreur.setText("Nom catgorie invalide");
                        erreur.setVisible(true);
                        warning.setVisible(true);
                        return;
                    }

                    if ((description.getText().length() < 4)) {
                        erreur.setText("Description Invalide");
                        erreur.setVisible(true);
                        warning.setVisible(true);
                        return;
                    } else {
                        //            ServiceCategorie ms = new ServiceCategorie();
//            ms.ajouterCategorie(new Categorie(categorie, libelle.getText(),
//                description.getText(), 
//                    image.getText()));

                    }
                }
                
                
                    
       
                
                
                
                anchorPanceModifierCategorie.toBack();
//                setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AfficherCategorie.fxml")));
                new Alert(Alert.AlertType.INFORMATION, "Catégorie modifiée").show();
                
                
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("modifier");
//                alert.setHeaderText(null);
//                alert.setContentText("cliquer sur OK si tu veux modifier sinon annuler");
//                Optional<ButtonType> action = alert.showAndWait();
//                if (action.get() == ButtonType.OK) {
//                    bs.modifierCategorie(b1);
                }
    
    
    
    
    
    
     /**************************Node**********************************/
    
         private void setNode(Node node) {
        anchorPanceModifierCategorie.getChildren().clear();
        anchorPanceModifierCategorie.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
           }

    
    
           }
        

    

