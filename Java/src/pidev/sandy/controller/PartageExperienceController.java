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
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.CheckTreeView;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.Rating;
import static pidev.sandy.controller.DetailExperienceController.of;
import pidev.sandy.entites.notif_demande;
import pidev.sandy.entites.offre_experience;
import pidev.sandy.services.DemandeRecommandation;
import pidev.sandy.services.PartageExperience;

/**
 * FXML Controller class
 *
 * @author SLIMEN
 */
public class PartageExperienceController implements Initializable {

    @FXML
    private AnchorPane UserPane;
    @FXML
    private VBox vbox;
    @FXML
    private JFXButton btnclose;
    @FXML
    private TableView<offre_experience> lview;
    PartageExperience pexp=new PartageExperience();
    @FXML
    private AnchorPane listpane;
    @FXML
    private JFXButton addexp;
    @FXML
    private TableColumn<offre_experience, String> imgcell;
    @FXML
    private TableColumn<offre_experience, String> nomcell;
    @FXML
    private TableColumn<offre_experience, String> desccell;
    @FXML
    private TableColumn<offre_experience, String> addcell;
    @FXML
    private TableColumn<String, Integer> regcell;
    @FXML
    private TableColumn<String, Integer> ratcell;
    @FXML
    private FontAwesomeIcon notif;
    @FXML
    private VBox vboxcx;
    @FXML
    private Label numbernotif;
    private String str=null;
    private String sto=null;
    private String selectedItemsLabel;
    
      List<offre_experience> filtredlst=new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       List<notif_demande> ntflist=pexp.GetNotificationById(UserInterfaceController.userid);
        numbernotif.setText(""+ntflist.size());
        List<offre_experience> listo=pexp.listerPartageExp();
       
       ObservableList<offre_experience> list= FXCollections.observableArrayList();
      //  lview.setItems(list);
      for (offre_experience o:listo)
      { list.add(o);
      nomcell.setCellValueFactory(new PropertyValueFactory<>("nom"));
      desccell.setCellValueFactory(new PropertyValueFactory<>("description"));
      addcell.setCellValueFactory(new PropertyValueFactory<>("addrese"));
      imgcell.setCellValueFactory(new PropertyValueFactory<>("url_image"));
      imgcell.setCellFactory(tc -> {
       TableCell<offre_experience, String> cell = new TableCell<offre_experience, String>() {
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
                        imageview.setPreserveRatio(true);
                        StackPane stackPane = new StackPane(imageview);
                        setGraphic(stackPane);
                  }
             }
       };
       return cell;
});
      ratcell.setCellValueFactory(new PropertyValueFactory<>("rating"));
      ratcell.setCellFactory(param -> new TableCell<String, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    Rating rtt=new Rating(pexp.GEtMoyRating(18));
                    System.out.println("item= "+pexp.GEtMoyRating(18));
                    rtt.setOrientation(Orientation.VERTICAL);
                   
                  //  setText(pexp.GetRegion(item));
                    setGraphic(rtt);
                    
                     
                }
            }
        }); 
      regcell.setCellValueFactory(new PropertyValueFactory<>("region_id"));
      regcell.setCellFactory(param -> new TableCell<String, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                  //  System.out.println(item);
                    setText(pexp.GetRegion(item));
                    
                    
                     
                }
            }
        });
 
      
      }  
      lview.setItems(list);
      lview.setRowFactory( tv -> {
    TableRow<offre_experience> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
            offre_experience rowData = row.getItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/DetailExperience.fxml"));
            Parent root;
            String nomPE=rowData.getNom().toString();
            try {
                root = loader.load();
                DetailExperienceController detail=loader.getController();
                             
                 detail.setNomPExp(nomPE);
                 detail.initData(rowData);
                 DetailExperienceController.id=rowData.getId();
                        lview.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(PartageExperienceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
    return row ;
});
      
      
      CheckBoxTreeItem<String> region = new CheckBoxTreeItem<String>("Region");
 region.setExpanded(true);
 region.getChildren().addAll(
               new CheckBoxTreeItem<String>("ARIANA"),
               new CheckBoxTreeItem<String>("SOUSSE"),
               new CheckBoxTreeItem<String>("TUNIS"),
               new CheckBoxTreeItem<String>("NABEUL"),
               new CheckBoxTreeItem<String>("MONASTIR"),
               new CheckBoxTreeItem<String>("BIZERTE"),
               new CheckBoxTreeItem<String>("BEJA"),
               new CheckBoxTreeItem<String>("GABES"),
               new CheckBoxTreeItem<String>("GAFSA"),
               new CheckBoxTreeItem<String>("JENDOUBA"),
               new CheckBoxTreeItem<String>("KAIROUAN"),
               new CheckBoxTreeItem<String>("KASSERINE"),
               new CheckBoxTreeItem<String>("KEBILI"),
               new CheckBoxTreeItem<String>("KEF"),
               new CheckBoxTreeItem<String>("MAHDIA"),
               new CheckBoxTreeItem<String>("MANOUBA"),
               new CheckBoxTreeItem<String>("MEDNINE"),
               new CheckBoxTreeItem<String>("SFAX"),
               new CheckBoxTreeItem<String>("SIDI BOUZID"),
               new CheckBoxTreeItem<String>("SILIANA"),
               new CheckBoxTreeItem<String>("TATAOUINE"),
               new CheckBoxTreeItem<String>("TOZEUR"),
               new CheckBoxTreeItem<String>("ZAGHOUAN"));
  final CheckTreeView<String> checkTreeView = new CheckTreeView<>(region);
  checkTreeView.setPrefHeight(400);
   checkTreeView.getCheckModel().getCheckedItems().addListener(new ListChangeListener<TreeItem<String>>() {
      public void onChanged(ListChangeListener.Change<? extends TreeItem<String>> c) {
     //     System.out.println("xxxx "+filtredlst );
      }
 });
 CheckBoxTreeItem<String> option = new CheckBoxTreeItem<String>("Option");
 option.setExpanded(true);
 option.getChildren().addAll(
               new CheckBoxTreeItem<String>("Wi-Fi"),
               new CheckBoxTreeItem<String>("Piscine"));
        final CheckTreeView<String> checkTreeView2 = new CheckTreeView<>(option);
        checkTreeView2.setPrefHeight(400);
       
         checkTreeView2.getCheckModel().getCheckedItems().addListener(new ListChangeListener<TreeItem<String>>() {
      public void onChanged(ListChangeListener.Change<? extends TreeItem<String>> c) {
     //     System.out.println(checkTreeView2.getCheckModel().getCheckedItems());
      }
 });
        
         checkTreeView2.getCheckModel().getCheckedItems().addListener(new ListChangeListener<TreeItem<String>>() {
            @Override public void onChanged(ListChangeListener.Change<? extends TreeItem<String>> c) {
                updateText2(selectedItemsLabel, c.getList());
                //System.out.println("str="+str);
                // System.out.println(option);
                        /*if(sto.isEmpty())
                        {sto=null;}
                         if(str=="")
                        {str=null;}*/
                      // System.out.println("fel1 2  : STR = "+str+" sto= "+sto);
                  
                     filtredlst=pexp.listerPartageExpParRegionOption(str, sto); 
                      
                 
                     ObservableList<offre_experience> listf= FXCollections.observableArrayList();

                 for (offre_experience o:filtredlst)
      { 

          listf.add(o);
      nomcell.setCellValueFactory(new PropertyValueFactory<>("nom"));
      desccell.setCellValueFactory(new PropertyValueFactory<>("description"));
      addcell.setCellValueFactory(new PropertyValueFactory<>("addrese"));
      imgcell.setCellValueFactory(new PropertyValueFactory<>("url_image"));
      imgcell.setCellFactory(tc -> {
       TableCell<offre_experience, String> cell = new TableCell<offre_experience, String>() {
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
                        imageview.setPreserveRatio(true);
                        StackPane stackPane = new StackPane(imageview);
                        setGraphic(stackPane);
                  }
             }
       };
       return cell;
});
      ratcell.setCellValueFactory(new PropertyValueFactory<>("rating"));
      ratcell.setCellFactory(param -> new TableCell<String, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    Rating rtt=new Rating(item);
                    rtt.setOrientation(Orientation.VERTICAL);
                   
                  //  setText(pexp.GetRegion(item));
                    setGraphic(rtt);
                    
                     
                }
            }
        }); 
      regcell.setCellValueFactory(new PropertyValueFactory<>("region_id"));
      regcell.setCellFactory(param -> new TableCell<String, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                  //  System.out.println(item);
                    setText(pexp.GetRegion(item));
                    
                    
                     
                }
            }
        });
 
      
      }  
               
      lview.setItems(listf);
                
            }
        });
       
         checkTreeView.getCheckModel().getCheckedItems().addListener(new ListChangeListener<TreeItem<String>>() {
            @Override public void onChanged(ListChangeListener.Change<? extends TreeItem<String>> c) {
                updateText(selectedItemsLabel, c.getList());
                //System.out.println("str="+str);
                // System.out.println(region);
                  if(sto=="")
                        {sto=null;}
                         if(str=="")
                        {str=null;}
                  //   System.out.println("fel1 0 : STR = "+str+" sto= "+sto);
                     filtredlst=pexp.listerPartageExpParRegionOption(str, sto); 
               
              
                //System.out.println(filtredlst);
                     ObservableList<offre_experience> listd= FXCollections.observableArrayList();

                 for (offre_experience o:filtredlst)
      { 

          listd.add(o);
      nomcell.setCellValueFactory(new PropertyValueFactory<>("nom"));
      desccell.setCellValueFactory(new PropertyValueFactory<>("description"));
      addcell.setCellValueFactory(new PropertyValueFactory<>("addrese"));
      imgcell.setCellValueFactory(new PropertyValueFactory<>("url_image"));
      imgcell.setCellFactory(tc -> {
       TableCell<offre_experience, String> cell = new TableCell<offre_experience, String>() {
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
                        imageview.setPreserveRatio(true);
                        StackPane stackPane = new StackPane(imageview);
                        setGraphic(stackPane);
                  }
             }
       };
       return cell;
});
      ratcell.setCellValueFactory(new PropertyValueFactory<>("rating"));

      ratcell.setCellFactory(param -> new TableCell<String, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    System.out.println("rating "+pexp.GEtMoyRating(o.getId())+" item "+item);

                    Rating rtt=new Rating(pexp.GEtMoyRating(o.getId()));
                    rtt.setOrientation(Orientation.VERTICAL);
                   
                  //  setText(pexp.GetRegion(item));
                  setGraphic(rtt);
                    
                     
                }
            }
        }); 
      regcell.setCellValueFactory(new PropertyValueFactory<>("region_id"));
      regcell.setCellFactory(param -> new TableCell<String, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                  //  System.out.println(item);
                    setText(pexp.GetRegion(item));
                    
                    
                     
                }
            }
        });
 
      
      }  
               
      lview.setItems(listd);
                
            }
        });
          
        vboxcx.getChildren().addAll(checkTreeView,checkTreeView2);
       // vboxcx.setPrefWidth(50);
    }
   protected void updateText(String label, ObservableList<? extends TreeItem<String>> list) {
        final StringBuilder sb = new StringBuilder();
        
        if (list != null) {
            for (int i = 0, max = list.size(); i < max; i++) {
                sb.append(list.get(i).getValue());
                if (i < max - 1) {
                    sb.append(", ");
                }
            }
        }
        
            str = sb.toString();
         
    }
     protected void updateText2(String label, ObservableList<? extends TreeItem<String>> list) {
        final StringBuilder sb = new StringBuilder();
        
        if (list != null) {
            for (int i = 0, max = list.size(); i < max; i++) {
                sb.append(list.get(i).getValue());
                if (i < max - 1) {
                    sb.append(", ");
                }
            }
        }
        
            sto = sb.toString();
         
    }
    @FXML
    public void BackHomes(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/UserInterface.fxml"));
        Parent root = loader.load();
        vbox.getScene().setRoot(root);
    }

    @FXML
    public void closeApp(ActionEvent event) {

        // get a handle to the stage
        Stage stage = (Stage) btnclose.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    @FXML
    public void showAddPartageExp() throws IOException
    {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/AjoutPartageExperience.fxml"));
        Parent root = loader.load();
        listpane.getScene().setRoot(root);
    }
    @FXML
    public void getnotif()
    {
        PopOver ntf=new PopOver();
        
        ntf.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
   //     PartageExperience pexp=new PartageExperience();
        List<notif_demande> ntflist=pexp.GetNotificationById(UserInterfaceController.userid);
         
        List<String> ll=new ArrayList<>();
        List<Integer> lll=new ArrayList<>();
        List<Integer> llll=new ArrayList<>();
        
        for(notif_demande n:ntflist)
        {
            ll.add(n.getMessage());
            lll.add(n.getDemandeid());
            llll.add(n.getId());
            
            
        }
        ListView<String> l=new ListView<String>(); 
        ObservableList<String> items =FXCollections.observableArrayList(ll);
         l.setItems(items);
        l.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
           DemandeRecommandation drec=new DemandeRecommandation();
            @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
         pexp.SetNotificationSeen(llll.get(l.getSelectionModel().getSelectedIndex()));
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/sandy/GUI/DetailDemande.fxml"));
            Parent root;
           
            try {
                root = loader.load();
                   DetailDemandeController detail=loader.getController();
                             
                 detail.initData(drec.GetDemandeById(
                         lll.get(l.getSelectionModel().getSelectedIndex())
                 ));
              
              
                        UserPane.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(PartageExperienceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
});
        
        
       
        l.setPrefSize(400, 200);
   //  ntf.setHeight(200);
        //Label lb=new Label("tesst");
     ntf.setContentNode(l);
     ntf.setDetachable(false);
         ntf.show(notif);
    }
    
}
