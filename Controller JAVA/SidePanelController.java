/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import IServices.ChangeCallback;
import Entities.Session;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SidePanelController implements Initializable {

    @FXML
    private JFXButton b1;
    private JFXButton b2;
    @FXML
    private JFXButton exit;

    @FXML
    private Label user;

    private ChangeCallback callback;
    @FXML
    private JFXButton b3131;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String nomUser = Session.getCurrentSession().getNom();

        user.setText("Utilisateur : " + nomUser);
        int id_u = Session.getCurrentSession().getId();
        System.out.println("id : " + id_u);

    }

    public void setCallback(ChangeCallback callback) {
        this.callback = callback;
    }

    @FXML
    // a mettre les SRC
    private void navigate(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch (btn.getText()) {
            case "Acceuil":
                callback.update("");
                break;
            case "Gestion Des Fournisseurs":
                callback.update("/GUI/afficherfournisseurs.fxml");
                break;
            case "Gestion Des Depots":
                callback.update("/GUI/afficherdepots.fxml");
                break;  

        }
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    // a mettre les SRC
    private void deco(ActionEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("/GUI/Login.fxml"));
            Stage s = (Stage) b2.getScene().getWindow();
            s.close();

            try {
                Loader.load();
            } catch (IOException e) {
                System.out.println(e);
            }
            LoginController c = Loader.getController();
            Parent p = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.show();
            event.consume();
            Session.setCurrentSessionToNull();
        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }
}
