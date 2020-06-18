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
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import pidev.sandy.entites.User;
import pidev.sandy.test.MenuAdmin1;
/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class ListController implements Initializable {

    @FXML
    private ListView<Categoris> list;
public static String image;
 public static String nomProd;
   public static int id;
    @FXML
    private AnchorPane listpane;
    static Categoris categoriemere;
    @FXML
    private VBox vbox;
    @FXML
    private Label partenaire;
    @FXML
    private JFXButton btnclose;
    @FXML
    private Label categorie;
    @FXML
    private JFXButton back;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         User loggedUser = pidev.sandy.controller.LoginController.getInstance().getLoggedUser();
                 
        MenuAdmin1.Id_user_connecte=loggedUser.getId();
        
         ObservableList<Categoris> oblist = FXCollections.observableArrayList();
        ServiceCategorie prod = new ServiceCategorie();
        List<Categoris> listedem = prod.list();
         oblist.addAll(listedem);
        list.getItems().addAll(oblist);
         list.setCellFactory(new Callback<ListView<Categoris>, ListCell<Categoris>>() {

            @Override
            public ListCell<Categoris> call(ListView<Categoris> arg0) {
                return new ListCell<Categoris>() {

                    @Override
                    protected void updateItem(Categoris item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {

                            GridPane gridPane = new GridPane();
                            Label NomProduit = new Label();
                            ImageView imagecategorie = new ImageView();
                            AnchorPane content = new AnchorPane();
                            imagecategorie.setFitWidth(155);
                            imagecategorie.setPreserveRatio(true);
                          GridPane.setConstraints(imagecategorie, 0, 0, 1, 3);
                            GridPane.setValignment(imagecategorie, VPos.TOP);
                            // 
                            NomProduit.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em; ");
                            GridPane.setConstraints(NomProduit, 1, 0);
                            gridPane.getChildren().setAll(imagecategorie, NomProduit);
                            AnchorPane.setTopAnchor(gridPane, 0d);
                            AnchorPane.setLeftAnchor(gridPane, 0d);
                            AnchorPane.setBottomAnchor(gridPane, 0d);
                            AnchorPane.setRightAnchor(gridPane, 0d);
                            content.getChildren().add(gridPane);
                            NomProduit.setText(item.getLibelle());
                            Image i = new Image(item.getImage());
                            imagecategorie.setImage(i);
                            setText(null);
                            setGraphic(content);
                            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

                        }
                    }

                };
            }
        });
         list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Categoris>() {
            @Override
            public void changed(ObservableValue<? extends Categoris> observable, Categoris oldValue, Categoris newValue) {
                id=newValue.getId();
                nomProd=newValue.getLibelle();
                image=newValue.getImage();
	} 
         });
         }

    @FXML
    private void souslist(MouseEvent event) throws IOException {
        categoriemere=list.getSelectionModel().getSelectedItem();
            setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/Souslist.fxml")));
    }
      private void setNode(Node node) {
        listpane.getChildren().clear();
        listpane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
         }

    @FXML
    private void categorie(MouseEvent event) throws IOException {
            setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/List.fxml")));
    }

    @FXML
    private void partenaire(MouseEvent event) throws IOException {
            setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/Espacepartenairee.fxml")));
    }
//
//    @FXML
//    private void back(ActionEvent event) throws IOException {
//        setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/UserI.fxml")));
//    }
        @FXML
    public void closeApp() {

        Stage stage = (Stage) btnclose.getScene().getWindow();
        stage.close();
    }




      
    
}
