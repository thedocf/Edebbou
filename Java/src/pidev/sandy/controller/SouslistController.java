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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class SouslistController implements Initializable {
public static String image;
 public static String nomProd;
   public static int id;
    @FXML
    private AnchorPane souslistpane;
    @FXML
    private ListView<Categoris> souslist;
    
      static Categoris categoriefille;
    @FXML
    private VBox vbox;
    @FXML
    private Label categorie;
    @FXML
    private Label partenaire;
    @FXML
    private JFXButton btnclose;
    @FXML
    private JFXButton back;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList<Categoris> oblist = FXCollections.observableArrayList();
        ServiceCategorie prod = new ServiceCategorie();
        List<Categoris> listedem = prod.souslist( ListController.categoriemere.getId());
         oblist.addAll(listedem);
        souslist.getItems().addAll(oblist);
         souslist.setCellFactory(new Callback<ListView<Categoris>, ListCell<Categoris>>() {

            @Override
            public ListCell<Categoris> call(ListView<Categoris> arg0) {
                return new ListCell<Categoris>() {

                    @Override
                    protected void updateItem(Categoris item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {

                            GridPane gridPane = new GridPane();
                            Label NomProduit = new Label();
//                            Label Prix = new Label();
                            ImageView ImageProd = new ImageView();
                            AnchorPane content = new AnchorPane();
                            ImageProd.setFitWidth(155);
                            ImageProd.setPreserveRatio(true);
                            GridPane.setConstraints(ImageProd, 0, 0, 1, 3);
                            GridPane.setValignment(ImageProd, VPos.TOP);
                            // 
                            NomProduit.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");
                            GridPane.setConstraints(NomProduit, 1, 0);
                            //GridPane.setConstraints(AjoutAuPanier, 3, 0);
//                            Prix.setStyle("-fx-opacity: 0.75;");
//                            GridPane.setConstraints(Prix, 1, 1);
//                            GridPane.setColumnSpan(Prix, Integer.MAX_VALUE);
                            gridPane.getChildren().setAll(ImageProd, NomProduit);
                            AnchorPane.setTopAnchor(gridPane, 0d);
                            AnchorPane.setLeftAnchor(gridPane, 0d);
                            AnchorPane.setBottomAnchor(gridPane, 0d);
                            AnchorPane.setRightAnchor(gridPane, 0d);
                            content.getChildren().add(gridPane);
                            NomProduit.setText(item.getLibelle());
//                            String prix = "" + item.getPrix() + "DT";
//                            Prix.setText(prix);
                            //AjoutAuPanier.setText("Ajouter au panier"); 
                            Image i = new Image(item.getImage());
                            ImageProd.setImage(i);
                            //descriptionLabel.setText(String.format(", %d places, %d portes", item.getSeats(), item.getDoors())); 
                            //colorRect.setFill(item.getColor()); 
                            setText(null);
                            setGraphic(content);
                            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

                        }
                    }

                };
            }
       
            

        });
         souslist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Categoris>() {
            @Override
            public void changed(ObservableValue<? extends Categoris> observable, Categoris oldValue, Categoris newValue) {
                
                      id=newValue.getId();
               nomProd=newValue.getLibelle();
              //  Prix=(int) newValue.getPrix();
              //  quantite=newValue.getQuantite();
              //  cat=newValue.getCategorie();
              //  etat=newValue.getEtat();
                image=newValue.getImage();
               // dispo=newValue.isDisponible();
                //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Panier.fxml"));
                  //  Parent root;
              
                   // root = (Parent) fxmlLoader.load();
               
                   // Stage stage = new Stage();
                  //  stage.setScene(new Scene(root));
                  //  stage.show();
                     // catch (IOException ex) {
                        // System.out.println(ex.getMessage());
               
                
	}

           
                
            
         });
       
    }    

    @FXML
    private void touslesbonplan(MouseEvent event)  throws IOException {
        categoriefille=souslist.getSelectionModel().getSelectedItem();
            setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/TousLesBonsPlans.fxml")));
    }
      private void setNode(Node node) {
        souslistpane.getChildren().clear();
        souslistpane.getChildren().add((Node) node);
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

    @FXML
    private void back(ActionEvent event) throws IOException {
        setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/List.fxml")));
    }
        @FXML
    public void closeApp() {

        Stage stage = (Stage) btnclose.getScene().getWindow();
        stage.close();
    }

}
