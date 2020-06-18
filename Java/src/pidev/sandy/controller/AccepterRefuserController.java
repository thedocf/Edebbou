/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev.sandy.entites.BonPlan;
import pidev.sandy.services.ServiceBonplan;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class AccepterRefuserController implements Initializable {

    @FXML
    private AnchorPane accepterrefuser;
    @FXML
    private TableView<BonPlan> table;
    @FXML
    private TableColumn<BonPlan, String>  libelle;
    @FXML
    private TableColumn<BonPlan, String>  adresse;
    @FXML
    private TableColumn<BonPlan, String> image;
    @FXML
    private TableColumn<BonPlan, Integer>  status;
    static BonPlan bn ,bna;
    @FXML
    private JFXButton bntn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ServiceBonplan bon = new ServiceBonplan();
        ArrayList arrayList = (ArrayList) bon.listerBonPlan();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("addresse"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
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
        image.setCellFactory(cellFactoryImage);
        table.setStyle("-fx-font-weight: bold; -fx-font-size: 1.05em; ");

        // TODO
    }    
    
    public void list(){
       ServiceBonplan bon = new ServiceBonplan();
        ArrayList arrayList = (ArrayList) bon.listerBonPlan();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        table.setItems(observableList);
         
    }


    @FXML
    private void accepter(ActionEvent event) {
        
        if (table.getSelectionModel().isEmpty()) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Choix invalide")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
//        } else {
//        
//            List<BonPlan> bon = table.getSelectionModel().getSelectedItems();
//              bna = table. getSelectionModel().getSelectedItem();
//                  if (bna.getStatus()==1){
//                       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Confirmation Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("Vous voulez vraiment accepter ce bon plan");
//            Optional<ButtonType> action = alert.showAndWait();
//                       new ServiceBonplan().accepterbonplan(bna);
//            }
//                  else if (bna.getStatus()==2)
//                         {
//                              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Confirmation Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("Ce bon plan est déjà accepté");
//                         }
//                         else
//                         {
//                              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//              alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText("Ce bon plan est refusé");
//                            
//           
//                         }
//                      
//                      
//                      
//                  }
//           
//               
              
            
        }
        else
        {
            List<BonPlan> bon = table.getSelectionModel().getSelectedItems();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment accepter ce bon plan");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
               bn = table. getSelectionModel().getSelectedItem();
                new ServiceBonplan().accepterbonplan(bn);
               // System.out.println(bon.get(0).getId());
        }
        }
        list();
    }

    @FXML
    private void refuser(ActionEvent event) {
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
            alert.setContentText("Vous voulez vraiment refuser ce bon plan");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
               bn = table. getSelectionModel().getSelectedItem();
                new ServiceBonplan().refuserbonplan(bn);
               // System.out.println(bon.get(0).getId());
            }
        }
        list();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
                    setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/Adminpartenaire.fxml")));

    }
     private void setNode(Node node) {
        accepterrefuser.getChildren().clear();
        accepterrefuser.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
         }
    
}
