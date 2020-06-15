/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Services.Servicefournisseurs;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ModifierFournisseursController implements Initializable {

    @FXML
    private ImageView re;
    @FXML
    private ImageView va;
    @FXML
    private TextField modifdepot_id;
    @FXML
    private TextField modifnom;
    @FXML
    private TextField modifprenom;
    @FXML
    private TextField modifnumtel;
    @FXML
    private TextField modifdisponible;
    @FXML
    private Button retour;
    @FXML
    private Button valider;


    public TextField getModifnom() {
        return modifnom;
    }

    public void setModifnom(TextField modifnom) {
        this.modifnom = modifnom;
    }

    public TextField getModifprenom() {
        return modifprenom;
    }

    public void setModifprenom(TextField modifprenom) {
        this.modifprenom = modifprenom;
    }

    public TextField getModifnumtel() {
        return modifnumtel;
    }

    public void setModifnumtel(TextField modifnumtel) {
        this.modifnumtel = modifnumtel;
    }

    public TextField getModifdisponible() {
        return modifdisponible;
    }

    public void setModifdisponible(TextField modifdisponible) {
        this.modifdisponible = modifdisponible;
    }
    
    public TextField getModifdepot_id() {
        return modifdepot_id;
    }

    public void setModifdepot_id(TextField modifdepot_id) {
        this.modifdepot_id = modifdepot_id;
    }

    public Button getRetour() {
        return retour;
    }

    public void setRetour(Button retour) {
        this.retour = retour;
    }

    public Button getValider() {
        return valider;
    }

    public void setValider(Button valider) {
        this.valider = valider;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modifdepot_id.setText(String.valueOf(AfficherFournisseursController.Recup.getDepot_id()));
        modifnom.setText(AfficherFournisseursController.Recup.getNom());
        modifprenom.setText(AfficherFournisseursController.Recup.getPrenom());
        modifnumtel.setText(String.valueOf(AfficherFournisseursController.Recup.getNumTel()));
        modifdisponible.setText(AfficherFournisseursController.Recup.getDisponible());

    }

    @FXML
    private void validerModiffournisseurs(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification Depot ");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous vraiment modifier cet fournisseurs ? ");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Servicefournisseurs ac = new Servicefournisseurs();

            ac.modifierfournisseurs(AfficherFournisseursController.Recup.getIdFour(), Integer.parseInt(modifdepot_id.getText()), modifnom.getText(), modifprenom.getText(), Integer.parseInt(modifnumtel.getText()), modifdisponible.getText()); // insertion dans la base de données
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherfournisseurs.fxml"));
                Parent root = loader.load();
                AfficherFournisseursController rc = loader.getController();
                modifnom.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());

            }

        }

    }

    @FXML
    private void retourModif(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherfournisseurs.fxml"));
            Parent root = loader.load();
            AfficherFournisseursController rc = loader.getController();

            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }

    }

}
