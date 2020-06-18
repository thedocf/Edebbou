/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;


import entite.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;




/**
 *
 * @author jha
 */
public class FXMLDocumentController implements Initializable {
  
     private Button Bt_Stock21;
    DropShadow shadow =new DropShadow();
    
    private Label label;
    @FXML
    private Button login;
    @FXML
    private AnchorPane rootpane;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onmouseexit(MouseEvent event) {
           login.setEffect(null);
    }

    @FXML
    private void Onmouseenter(MouseEvent event) {
        Effect shadow = null;
         login.setEffect(shadow);
    }

    @FXML
    private void loginbut(ActionEvent event) throws IOException {
        AnchorPane pane   = FXMLLoader.load(getClass().getResource("/gui/login.fxml"));
       rootpane.getChildren().setAll(pane);
    }

    
}
