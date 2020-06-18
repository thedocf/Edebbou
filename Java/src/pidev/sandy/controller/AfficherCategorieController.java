/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import pidev.sandy.entites.Categoris;
import pidev.sandy.services.ServiceCategorie;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import static pidev.sandy.controller.AfficherReclamationController.selectionedReclamation;
import static pidev.sandy.controller.AfficherReclamationController.stageModifier;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class AfficherCategorieController implements Initializable {
    static Stage stageModifier;
    @FXML
    private TableView<Categoris> listcategorie;
    @FXML
    private TableColumn<Categoris, String> libelle;
    @FXML
    private TableColumn<Categoris, String> description;
    @FXML
    private TableColumn<Categoris, String> categorie;
    @FXML
    private TableColumn<Categoris, String> image;
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton modifier;
    static Categoris d;
    @FXML
    private AnchorPane anchorpaneAfficherCategorie;
    @FXML
    private TextField filtre;
    @FXML
    private TextField nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affiche();
    }

    public void affiche() {
        ServiceCategorie produitService = new ServiceCategorie();
        ArrayList arrayList = (ArrayList) produitService.listerCategorie();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listcategorie.setItems(observableList);
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        description.setCellValueFactory(new PropertyValueFactory<>("discription"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("idcategoriemere"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));

        Callback<TableColumn<Categoris, String>, TableCell<Categoris, String>> cellFactoryImage
                = //
                new Callback<TableColumn<Categoris, String>, TableCell<Categoris, String>>() {
            @Override
            public TableCell call(final TableColumn<Categoris, String> param) {
                final TableCell<Categoris, String> cell = new TableCell<Categoris, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText("Aucune image n'existe dans cette liste");
                        } else {
                            ImageView imagev = new ImageView(new Image(item));
                            imagev.setFitHeight(120);
                            imagev.setFitWidth(200);
                            setGraphic(imagev);
                            setText(null);
                            //System.out.println(item);
                        }
                    }
                };
                return cell;
            }
        };

        image.setCellFactory(cellFactoryImage);
        listcategorie.setStyle("-fx-font-weight: bold; -fx-font-size: 1.05em; ");

    }

    public void list() {
        ServiceCategorie produitService = new ServiceCategorie();
        ArrayList arrayList = (ArrayList) produitService.listerCategorie();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listcategorie.setItems(observableList);

    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {

        
            Parent root = FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AjouterCategorie.fxml"));
        Scene scene = new Scene(root);
        stageModifier = new Stage();
        stageModifier.setScene(scene);
        stageModifier.show();
        
        
        
       

    }

    private void setNode(Node node) {
        anchorpaneAfficherCategorie.getChildren().clear();
        anchorpaneAfficherCategorie.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        if (listcategorie.getSelectionModel().isEmpty()) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Choix invalide")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
        } else {
            List<Categoris> categorie = listcategorie.getSelectionModel().getSelectedItems();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment supprimer cette categorie");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new ServiceCategorie().supprimercategorie(categorie.get(0).getId());
                System.out.println(categorie.get(0).getId());
            }
        }
        list();
    }

//    private void modifier(ActionEvent event) throws IOException {
//        Club.currClub.setId(TableClub.getSelectionModel().getSelectedItem().getId());
//        Stage stage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("ModifierClub.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//
//    }
//
//    private void retour(ActionEvent event) {
//        ((Node) event.getSource()).getScene().getWindow().hide();
//    }
    @FXML
    void modifier(ActionEvent event) throws IOException {

        if (listcategorie.getSelectionModel().isEmpty()) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Choix invalide")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
        } else {
            d = listcategorie.getSelectionModel().getSelectedItem();

            
            
          
//        Stage stageModifier = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ModifierCategorie.fxml"));
        Scene scene = new Scene(root);
        stageModifier = new Stage();
        stageModifier.setScene(scene);
        stageModifier.show();
            
            
           // setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ModifierCategorie.fxml")));
        }
    }

    @FXML
    private void search(KeyEvent event) {
        ServiceCategorie produitService = new ServiceCategorie();
        ArrayList arrayList = (ArrayList) produitService.recherchelibelledesciptionCategorie(filtre.getText(), nom.getText());
//              ArrayList arrayList= (ArrayList) produitService.recherchelibelleCategorie(nom.getText());

        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listcategorie.setItems(observableList);
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        description.setCellValueFactory(new PropertyValueFactory<>("discription"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("idcategoriemere"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));

        Callback<TableColumn<Categoris, String>, TableCell<Categoris, String>> cellFactoryImage
                = //
                new Callback<TableColumn<Categoris, String>, TableCell<Categoris, String>>() {
            @Override
            public TableCell call(final TableColumn<Categoris, String> param) {
                final TableCell<Categoris, String> cell = new TableCell<Categoris, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            ImageView imagev = new ImageView(new Image(item));
                            imagev.setFitHeight(100);
                            imagev.setFitWidth(100);
                            setGraphic(imagev);
                            setText(null);
                            //System.out.println(item);
                        }
                    }
                };
                return cell;
            }
        };

        image.setCellFactory(cellFactoryImage);

    }

    private void searchK(KeyEvent event) {
        ServiceCategorie produitService = new ServiceCategorie();
        ArrayList arrayList = (ArrayList) produitService.listerCategorie();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listcategorie.setItems(observableList);
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        description.setCellValueFactory(new PropertyValueFactory<>("discription"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("idcategoriemere"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));

        Callback<TableColumn<Categoris, String>, TableCell<Categoris, String>> cellFactoryImage
                = //
                new Callback<TableColumn<Categoris, String>, TableCell<Categoris, String>>() {
            @Override
            public TableCell call(final TableColumn<Categoris, String> param) {
                final TableCell<Categoris, String> cell = new TableCell<Categoris, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            ImageView imagev = new ImageView(new Image(item));
                            imagev.setFitHeight(100);
                            imagev.setFitWidth(100);
                            setGraphic(imagev);
                            setText(null);
                            //System.out.println(item);
                        }
                    }
                };
                return cell;
            }
        };

        image.setCellFactory(cellFactoryImage);
    }


    @FXML
    private void back(ActionEvent event) throws IOException {
                    setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/Adminpartenaire.fxml")));

    }
    
    
}
