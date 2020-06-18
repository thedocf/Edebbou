/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;
import pidev.sandy.entites.demande_experience;
import pidev.sandy.entites.offre_experience;
import pidev.sandy.services.DemandeRecommandation;

/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class DemandeRecommandationController implements Initializable {

    @FXML
    private AnchorPane UserPane;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vboxavis;
    @FXML
    private JFXButton btnclose;
    @FXML
    private Label nomPExp;
    @FXML
    private FontAwesomeIcon notif;
    @FXML
    private AnchorPane listpane;
    @FXML
    private ImageView imgdetail;
    @FXML
    private ImageView imageaviss;
    @FXML
    private TableView<demande_experience> lview;
    @FXML
    private TableColumn<demande_experience, String> imgcell;
    @FXML
    private TableColumn<?, ?> nomcell;
    @FXML
    private TableColumn<?, ?> desccell;
    @FXML
    private TableColumn<?, ?> addcell;
    DemandeRecommandation pexp=new DemandeRecommandation();
    @FXML
    private JFXButton adddmd;
    @FXML
    private HBox backdmdh;
    @FXML
    private Label backdmd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      List<demande_experience> listo=pexp.listerDemandes();
       
       ObservableList<demande_experience> list= FXCollections.observableArrayList();
      //  lview.setItems(list);
      for (demande_experience o:listo)
      { list.add(o);
      nomcell.setCellValueFactory(new PropertyValueFactory<>("nom"));
      desccell.setCellValueFactory(new PropertyValueFactory<>("descripion"));
      addcell.setCellValueFactory(new PropertyValueFactory<>("addresse"));
      imgcell.setCellValueFactory(new PropertyValueFactory<>("image"));
      imgcell.setCellFactory(tc -> {
       TableCell<demande_experience, String> cell = new TableCell<demande_experience, String>() {
             @Override
             protected void updateItem(String item, boolean empty) {
                  super.updateItem(item, empty);
                  if (item == null || empty) {
                        setText(null);
                  } else {
                        ImageView imageview = new ImageView();
                        imageview.setImage(new Image( "file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\"+ item));
                        imageview.setFitWidth(300);
                        imageview.setFitHeight(150);
                        imageview.setPreserveRatio(false);
                        StackPane stackPane = new StackPane(imageview);
                        setGraphic(stackPane);
                  }
             }
       };
       return cell;
});
     
      
      }  
      lview.setItems(list);
      lview.setRowFactory( tv -> {
    TableRow<demande_experience> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
            demande_experience rowData = row.getItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/DetailDemande.fxml"));
            Parent root;
            String nomPE=rowData.getNom().toString();
            try {
                root = loader.load();
                DetailDemandeController detail=loader.getController();
                 
              detail.initData(rowData);
                
                        lview.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(PartageExperienceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
    return row ;
});  // TODO
    
    }    


    @FXML
    private void closeApp(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void getnotif(MouseEvent event) {
    }




    @FXML
    private void BackListDmd(MouseEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/UserInterface.fxml"));
        Parent root = loader.load();
        vbox.getScene().setRoot(root);
    }

    @FXML
    private void showAddDemande(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/AjoutDemande.fxml"));
        Parent root = loader.load();
        vbox.getScene().setRoot(root);   
    }

}
