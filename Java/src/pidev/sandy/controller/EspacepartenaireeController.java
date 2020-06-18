/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import pidev.sandy.entites.BonPlan;
import pidev.sandy.services.ServiceBonplan;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev.sandy.test.MenuAdmin1;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class EspacepartenaireeController implements Initializable {

    @FXML
    private TableView<BonPlan> table;
    @FXML
    private TableColumn<BonPlan, String> libelle;
    @FXML
    private TableColumn<BonPlan, String> description;
    @FXML
    private Label categorie;
    @FXML
    private TableColumn<BonPlan, String> adresse;
    @FXML
    private TableColumn<BonPlan, String> ouverture;
    @FXML
    private TableColumn<BonPlan, String> fermeture;
    @FXML
    private TableColumn<BonPlan, String> profile;
    @FXML
    private TableColumn<BonPlan, String> couverture;
    @FXML
    private TableColumn<BonPlan, Integer> status;
    @FXML
    private AnchorPane espacepartenaire;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton supprimer;
      public static BonPlan d;
    @FXML
    private TableColumn<BonPlan, Double> longtitude;
    @FXML
    private TableColumn<BonPlan, Double> laltitude;
    @FXML
    private VBox vbox;
    @FXML
    private Label partenaire;
    @FXML
    private JFXButton btnclose;
    @FXML
    private JFXButton affcherBtn;
    ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Nom", "Description", "Addresse", "Status");
    @FXML
    private JFXComboBox<String> comboBox;
    @FXML
    private JFXTextField rechercheTFLD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBox.setItems(listeTypeRecherche);
comboBox.setValue("Nom");
        
//          ServiceBonplan bon = new ServiceBonplan();
//        ArrayList arrayList = (ArrayList) bon.listerBonPlan();
        ObservableList observableList = FXCollections.observableArrayList(list());
        //bch nhotouha tooul f table view
        table.setItems((observableList));
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
       
        adresse.setCellValueFactory(new PropertyValueFactory<>("addresse"));
        profile.setCellValueFactory(new PropertyValueFactory<>("image"));
        couverture.setCellValueFactory(new PropertyValueFactory<>("couverture"));
        ouverture.setCellValueFactory(new PropertyValueFactory<>("overture"));
        fermeture.setCellValueFactory(new PropertyValueFactory<>("fermeture"));
  //longtitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
   // laltitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
    
        Callback<TableColumn<BonPlan, String>, TableCell<BonPlan, String>> cellFactoryImage
                = 
                new Callback<TableColumn<BonPlan, String>, TableCell<BonPlan, String>>() {
            @Override
            public TableCell call(final TableColumn<BonPlan, String> param) {
                final TableCell<BonPlan, String> cell = new TableCell<BonPlan, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            ImageView imagev = new ImageView(new Image(item));
                            imagev.setFitHeight(90);
                            imagev.setFitWidth(150);
                            setGraphic(imagev);
                            setText(null);
                            //System.out.println(item);
                        }
                    }
                };
                return cell;
            }
        };

         
         
         
          Callback<TableColumn<BonPlan, Integer>, TableCell<BonPlan, Integer>> cellFactoryStatus
                = //
                new Callback<TableColumn<BonPlan, Integer>, TableCell<BonPlan, Integer>>() {
            @Override
            public TableCell call(final TableColumn<BonPlan, Integer> param) {
                final TableCell<BonPlan, Integer> cell = new TableCell<BonPlan, Integer>() {

                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                           String message="";
                           ImageView imagev ;
                           if (item==1)
                           {imagev = new ImageView(new Image ("file:C:\\Users\\Chahnez Sardouk\\Documents\\NetBeansProjects\\crud\\src\\pidev\\bonplan\\ressources\\img\\attente.PNG"));
                           setGraphic(imagev);    
                         //  message="En attente";
                           }
                           else if (item==2)
                           { {imagev = new ImageView(new Image ("file:C:\\Users\\Chahnez Sardouk\\Documents\\NetBeansProjects\\crud\\src\\pidev\\bonplan\\ressources\\img\\accepte.PNG"));
                           setGraphic(imagev);    
                         // message="Accepte";
                           }
                              //message="Accepte";
                           }
                           else 
                                imagev = new ImageView(new Image ("file:C:\\Users\\Chahnez Sardouk\\Documents\\NetBeansProjects\\crud\\src\\pidev\\bonplan\\ressources\\img\\refuse.PNG"));
                           setGraphic(imagev);    
                          
                           //    message="Refuse";
                            //setText(message);
                            System.out.println(item);
                        }
                    }
                };
                return cell;
            }
        };
          status.setCellFactory(cellFactoryStatus);
        profile.setCellFactory(cellFactoryImage);
      couverture.setCellFactory(cellFactoryImage);
      table.setStyle("-fx-font-weight: bold; -fx-font-size: 1.05em; ");

      
  }
    @FXML
        public  List<BonPlan>  list(){
            System.out.println(MenuAdmin1.Id_user_connecte);
            ArrayList arrayList=null;
       ServiceBonplan bon = new ServiceBonplan();
               if (rechercheTFLD.getText().equals(""))
//         arrayList = (ArrayList) bon.listerBonPlan();
//         arrayList = (ArrayList) bon.recherchebonplanespacepartenaire();
//               else
                   
                                   System.out.println(rechercheTFLD.getText());
                System.out.println(comboBox.getValue().toString());
                   
                arrayList = (ArrayList)  bon.recherchebonplanespacepartenaire(comboBox.getValue().toString(), rechercheTFLD.getText(), MenuAdmin1.Id_user_connecte);
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        System.out.println(arrayList);
        table.setItems(observableList);


        
        
        return arrayList;
  
        

}
    private void setNode(Node node) {
        espacepartenaire.getChildren().clear();
        espacepartenaire.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void ajouterBonplan(ActionEvent event) throws IOException {
          setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AjouterBonPlan.fxml")));
    }

    @FXML
    private void modifierBonPlan(ActionEvent event) throws IOException {
              if (table.getSelectionModel().isEmpty()) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Choix invalide")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
        } else {
         d=table.getSelectionModel().getSelectedItem();
       
      setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ModifierBonPlan.fxml")));
               }
    }

    @FXML
    private void supprimerBonPlan(ActionEvent event) {
        
             if (table.getSelectionModel().isEmpty()) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Choix invalide")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
        } else {
            List<BonPlan> bon = table.getSelectionModel().getSelectedItems();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment supprimer cette categorie");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new ServiceBonplan().supprimerbonplan(bon.get(0).getId());
                //System.out.println(bon.get(0).getId());
            }
        }
        list();
    }

//    private void affiche(MouseEvent event) throws IOException {
//        
//       
//          
//    }


    

    @FXML
    private void categorie(MouseEvent event) throws IOException {
            setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/List.fxml")));
    }

    @FXML
    private void partenaire(MouseEvent event) throws IOException {
            setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/Espacepartenairee.fxml")));
    }

     @FXML
    public void closeApp() {

        Stage stage = (Stage) btnclose.getScene().getWindow();
        stage.close();
    }

//    private void afficherbonplan(ActionEvent event) throws IOException {
//       if (table.getSelectionModel().isEmpty()) {
//            Notifications n = Notifications.create()
//                    .title("Erreur")
//                    .text("Choix invalide")
//                    .graphic(null)
//                    .position(Pos.TOP_CENTER)
//                    .hideAfter(Duration.seconds(5));
//            n.showWarning();
//        } else {
//         d=table.getSelectionModel().getSelectedItem();
//       
//      setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ModifierBonPlan.fxml")));
//               }
//    
//    }

//    private void modifierBonPlan1(ActionEvent event) throws IOException {
//           if (table.getSelectionModel().isEmpty()) {
//            Notifications n = Notifications.create()
//                    .title("Erreur")
//                    .text("Choix invalide")
//                    .graphic(null)
//                    .position(Pos.TOP_CENTER)
//                    .hideAfter(Duration.seconds(5));
//            n.showWarning();
//        } else {
//         d=table.getSelectionModel().getSelectedItem();
//       
//      setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AfficherBonPlan.fxml")));
//               }
//    }

    @FXML
    private void afficherBonPlan(ActionEvent event) throws IOException {
         if (table.getSelectionModel().isEmpty()) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Choix invalide")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
        } else {
         d=table.getSelectionModel().getSelectedItem();
       
      setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AfficherBonPlan.fxml")));
               }
    }




    

    
}
