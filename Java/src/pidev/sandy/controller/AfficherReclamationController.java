/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import pidev.sandy.entites.Reclamation;
import pidev.sandy.services.ServicesReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.joda.time.Days;
import pidev.sandy.entites.BonplanAdnene;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
import pidev.sandy.services.ServiceBonplanAdnene;
import pidev.sandy.test.MenuAdmin1;

/**
 * FXML Controller class
 *
 * @author X
 */
public class AfficherReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> TableViewReclamation;
    @FXML
    private Button supprimerBTN;
    @FXML
    private Button modifierBTN;
    static Reclamation selectionedReclamation;
    @FXML
    private ComboBox<String> typeRecherche;
    ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Tout", "Nom", "Prenom", "Email", "Objet", "Message", "Status");
    ObservableList<String> listeTypeRechercheMembre = FXCollections.observableArrayList("Objet", "Message", "Status");
    @FXML
    private TextField RechercheTextField;
    @FXML
    private HBox hbox;
    private static ComboBox<String> typeRechercheStatus;
    static Stage stageModifier;
    static Stage stageAffichageUnique;
    @FXML
    private AnchorPane btnGestionAffichage;
    User UserConneter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        btnGestionAffichage.set
//btnGestionAffichage.setBottomAnchor(<anchorpane>,0.0);
//btnGestionAffichage.setLeftAnchor(<anchorpane>,0.0);
//btnGestionAffichage.setRightAnchor(<anchorpane>,0.0);
        MyServices myServices = new MyServices();
        UserConneter = myServices.chercherUtilisateurByid(MenuAdmin1.Id_user_connecte);
//        System.out.println("ID du user connecté interface ajouter reclamation =" + MenuAdmin1.Id_user_connecte);
        stageModifier = new Stage();
        stageAffichageUnique = new Stage();

        typeRecherche.setItems(listeTypeRecherche);
typeRecherche.setValue("Tout");
        if (UserConneter != null) {
            if (!UserConneter.getRoles().contains("ROLE_ADMIN")) {
                typeRecherche.setItems(listeTypeRechercheMembre);
                typeRecherche.setValue("Objet");
            }
        }
        

//        //System.out.println(typeRecherche.getValue().toString());
        TableColumn<Reclamation, Integer> idColumn = new TableColumn<>("ID");
//        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, String> nomColumn = new TableColumn<>("Nom");
//        nomColumn.setMinWidth(100);
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, String> prenomColumn = new TableColumn<>("Prenom");
//        prenomColumn.setMinWidth(100);
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        prenomColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, String> emailColumn = new TableColumn<>("Email");
//        emailColumn.setMinWidth(100);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, String> telColumn = new TableColumn<>("Tél");
//        telColumn.setMinWidth(100);
        telColumn.setCellValueFactory(new PropertyValueFactory<>("numero_mobile"));
        telColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, String> statusColumn = new TableColumn<>("Status");
//        statusColumn.setMinWidth(110);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, String> objetColumn = new TableColumn<>("Objet");
//        objetColumn.setMinWidth(100);
        objetColumn.setCellValueFactory(new PropertyValueFactory<>("objet"));
        objetColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, String> messageColumn = new TableColumn<>("Message");
//        messageColumn.setMinWidth(100);
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        messageColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, Date> dateCreationColumn = new TableColumn<>("Date de creation");
//        dateCreationColumn.setMinWidth(100);
        dateCreationColumn.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        dateCreationColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, Date> dateDispoColumn = new TableColumn<>("Date de disponibilité");
//        dateDispoColumn.setMinWidth(100);
        dateDispoColumn.setCellValueFactory(new PropertyValueFactory<>("date_disponibilite"));
        dateDispoColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, Date> dateTraitementColumn = new TableColumn<>("Date de Traitement");
//        dateTraitementColumn.setMinWidth(100);
        dateTraitementColumn.setCellValueFactory(new PropertyValueFactory<>("date_traitement"));
        dateTraitementColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, Integer> bonPlanColumn = new TableColumn<>("Type");
//        dateTraitementColumn.setMinWidth(100);
        bonPlanColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bonPlanColumn.setStyle("-fx-alignment: CENTER;");

        // TableColumn<Reclamation, ImageView> screenshotColumn = new TableColumn<>("Image");
        TableColumn<Reclamation, String> screenshotColumn = new TableColumn<>("Image");
//        screenshotColumn.setMinWidth(100);
        screenshotColumn.setCellValueFactory(new PropertyValueFactory<>("screenshot"));
        screenshotColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFactory
                = //
                new Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>>() {
            @Override
            public TableCell call(final TableColumn<Reclamation, String> param) {
                final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {

                    final Button btn = new Button("Supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {

//                                Reclamation person = getTableView().getItems().get(getIndex());
////                                System.out.println(person.getId()
//                                        + "   " + person.getNom());
                                if (getTableView().getItems().get(getIndex()) == null) {
                                    Notifications n = Notifications.create()
                                            .title("Erreur")
                                            .text("Choix invalide")
                                            .graphic(null)
                                            .position(Pos.TOP_CENTER)
                                            .hideAfter(Duration.seconds(5));
                                    n.showWarning();
                                } else {
                                    //List<Reclamation> listReclamation = TableViewReclamation.getSelectionModel().getSelectedItems();

                                    Reclamation reclamationSelectione = getTableView().getItems().get(getIndex());
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Vous voulez vraiment supprimer cette categorie");
                                    Optional<ButtonType> action = alert.showAndWait();
                                    if (action.get() == ButtonType.OK) {
                                        new ServicesReclamation().supprimerReclamation(reclamationSelectione.getId());
//                                        System.out.println("Reclamation supprimée" + reclamationSelectione.getId());
                                    }
                                }
                                list();

                            });
//                            btn.getStyleClass().removeAll("addBobOk, focus"); 
//                            btn.getStyleClass().add("addBobOk");
//                             btn.setStyle("-fx-background-color: #00ff00");
                            setGraphic(btn);
                            setText(null);
//                            //  System.out.println(item);
                        }
                    }
                };
                return cell;
            }
        };

        actionCol.setCellFactory(cellFactory);

        ServicesReclamation produitService = new ServicesReclamation();
        ArrayList arrayList;

        if (UserConneter != null && !UserConneter.getRoles().contains("ROLE_ADMIN")) {
            arrayList = (ArrayList) produitService.rechercheListeReclamationsByAuthorId(MenuAdmin1.Id_user_connecte);
        } else {
            arrayList = (ArrayList) produitService.listerReclamations();
        }

        ObservableList observableList = FXCollections.observableArrayList(arrayList);

        TableViewReclamation.setItems(observableList);
        TableViewReclamation.getColumns().addAll(idColumn, bonPlanColumn, objetColumn, messageColumn, screenshotColumn, statusColumn, nomColumn, prenomColumn, emailColumn, telColumn, dateCreationColumn, dateDispoColumn, dateTraitementColumn, actionCol);
//        TableViewReclamation.getColumns().addAll(idColumn, screenshotColumn, actionCol, nomColumn, prenomColumn, emailColumn,telColumn);

        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFactoryImage
                = //
                new Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>>() {
            String path;

            @Override
            public TableCell call(final TableColumn<Reclamation, String> param) {
                final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            path = item;

                            ImageView imagev = new ImageView(new Image(item));
                            imagev.setFitHeight(100);
                            imagev.setFitWidth(100);
                            setGraphic(imagev);
                            setText(null);
//                            System.out.println(item);
                        }
                    }
                };

                cell.setOnMouseClicked((MouseEvent event2)
                        -> {
                    if (event2.getClickCount() == 1) {
                        if (TableViewReclamation.getSelectionModel().getSelectedItem() != null && !TableViewReclamation.getSelectionModel().getSelectedItem().getScreenshot().contains("null")) {
                            Stage window = new Stage();
//
                            window.setMinWidth(250);
                            ImageView imagevPOPUP = new ImageView(new Image(TableViewReclamation.getSelectionModel().getSelectedItem().getScreenshot()));
                            imagevPOPUP.setFitHeight(576);
                            imagevPOPUP.setFitWidth(1024);

                            VBox layout = new VBox(10);
                            layout.getChildren().addAll(imagevPOPUP);
                            layout.setAlignment(Pos.CENTER);

                            //Display window and wait for it to be closed before returning
                            Scene scene = new Scene(layout);
                            window.setScene(scene);
                            window.show();

                        }
                    }

//                    @Override
//                    public void handle(MouseEvent t) {
//                        Stage window = new Stage();
//
//                        window.setMinWidth(250);
//
//                        ImageView imagevPOPUP=new ImageView(new Image("file:C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\"+TableViewReclamation.getSelectionModel().getSelectedItem().getScreenshot()));
//                        imagevPOPUP.setFitHeight(576);
//                        imagevPOPUP.setFitWidth(1024);
//
//                        VBox layout = new VBox(10);
//                        layout.getChildren().addAll(imagevPOPUP);
//                        layout.setAlignment(Pos.CENTER);
//
//                        //Display window and wait for it to be closed before returning
//                        Scene scene = new Scene(layout);
//                        window.setScene(scene);
//                        window.show();
//                        cell.setOnMouseExited(new EventHandler<MouseEvent>() {
//
//                            @Override
//                            public void handle(MouseEvent t) {
//                                window.close();
//                            }
//                        });
////                        System.out.println(path);
//                    }
                });

                return cell;
            }
        };

        screenshotColumn.setCellFactory(cellFactoryImage);
//***************************************************************************************************************************
        Callback<TableColumn<Reclamation, Date>, TableCell<Reclamation, Date>> cellFactoryTime
                = //
                new Callback<TableColumn<Reclamation, Date>, TableCell<Reclamation, Date>>() {
            @Override
            public TableCell call(final TableColumn<Reclamation, Date> param) {
                final TableCell<Reclamation, Date> cell = new TableCell<Reclamation, Date>() {

                    @Override
                    public void updateItem(Date item, boolean empty) {
                        super.updateItem(item, empty);
                        if ((empty) || (item == null)) {
                            setGraphic(null);
                            setText(null);
                        } else {
//                            SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
                            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy \n 'a' hh:mm:ss");
//                            setText("Current Date: " + ft.format(item));
                            setText(ft.format(item));
//                            //System.out.println(item);
                        }
                    }
                };
                return cell;
            }
        };

        dateDispoColumn.setCellFactory(cellFactoryTime);
        dateCreationColumn.setCellFactory(cellFactoryTime);
        dateTraitementColumn.setCellFactory(cellFactoryTime);

        //**************************************************************************************************************************************************************** 
        TableViewReclamation.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 2) {
                if (TableViewReclamation.getSelectionModel().getSelectedItem() != null) {
                    selectionedReclamation = TableViewReclamation.getSelectionModel().getSelectedItem();

                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AfficherReclamationUniqueFXML.fxml"));
                        Scene scene = new Scene(root);
                        stageAffichageUnique.setScene(scene);
                        stageAffichageUnique.show();

                    } catch (IOException ex) {
                        Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });

        //***********************************************************************************************************************************************
        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFactoryStatus
                = //
                new Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>>() {
            @Override
            public TableCell call(final TableColumn<Reclamation, String> param) {
                final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {

                            ImageView imagev;
                            if (item.equals("En attente")) {
                                imagev = new ImageView(new Image("file:C:\\Users\\X\\Documents\\PIDEV\\src\\Images\\enAttente.png"));
                                imagev.setFitHeight(30);
                                imagev.setFitWidth(100);
                                setGraphic(imagev);

                            }
                            if (item.equals("En Traitement")) {
                                imagev = new ImageView(new Image("file:C:\\Users\\X\\Documents\\PIDEV\\src\\Images\\enTraitement.png"));
                                imagev.setFitHeight(30);
                                imagev.setFitWidth(100);
                                setGraphic(imagev);
                            } else if (item.equals("Traité")) {
                                imagev = new ImageView(new Image("file:C:\\Users\\X\\Documents\\PIDEV\\src\\Images\\traite.png"));
                                imagev.setFitHeight(30);
                                imagev.setFitWidth(100);
                                setGraphic(imagev);
                            }

                        }
                    }
                };
                return cell;
            }
        };

        statusColumn.setCellFactory(cellFactoryStatus);

        //*******************************************************************************************************************************************************************************
        Callback<TableColumn<Reclamation, Integer>, TableCell<Reclamation, Integer>> cellFactoryRef
                = //
                new Callback<TableColumn<Reclamation, Integer>, TableCell<Reclamation, Integer>>() {
            @Override
            public TableCell call(final TableColumn<Reclamation, Integer> param) {
                final TableCell<Reclamation, Integer> cell = new TableCell<Reclamation, Integer>() {

                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                             MyServices su= new MyServices();
                            ServicesReclamation produitService = new ServicesReclamation();
                                       ServiceBonplanAdnene sbn= new ServiceBonplanAdnene();
                                       Reclamation r = produitService.rechercheReclamationById(item); 
                                       
        BonplanAdnene b =sbn.rechercheBonPlanById(r.getRef_bonplan());
                      
        User u =su.chercherUtilisateurByid(r.getRef_compte());
        
                           

                            if (r.getRef_bonplan() != 0) {
                                setText("   BonPlan:\nNom="+b.getLibelle());

                            } else if (r.getRef_compte() != 0) {
                                setText("    Compte:\nUsername="+u.getUsername());

                            } else if (r.getRef_deal() != 0) {
                                setText("Deal");

                            } else if (r.getRef_mise() != 0) {
                                setText("Mise");

                            } else {
                                setText("Autre");
                            }

                        }
                    }
                };
                return cell;
            }
        };

        bonPlanColumn.setCellFactory(cellFactoryRef);

        //*******************************************************************************************************************************************************
        stageModifier.setOnCloseRequest(e -> {
//   final ArrayList arrayList1 = (ArrayList) produitService.listerReclamations();
//       final ObservableList observableList1 = FXCollections.observableArrayList(arrayList1);
//    TableViewReclamation.refresh();
//    TableViewReclamation.setItems(observableList1);
//    TableViewReclamation.refresh();
            list();
        });

        stageAffichageUnique.setOnCloseRequest(e -> {
            list();
        });

    }

//    public void list() {
//
//        ServicesReclamation produitService = new ServicesReclamation();
//        ArrayList arrayList = (ArrayList) produitService.listerReclamations();
//        ObservableList observableList = FXCollections.observableArrayList(arrayList);
//        TableViewReclamation.setItems(observableList);
//
//    }
    @FXML
    public void list() {
        ArrayList arrayList = null;
        ServicesReclamation produitService = new ServicesReclamation();
        if (typeRechercheStatus == null) {
            typeRechercheStatus = new ComboBox<String>();
        }
        typeRechercheStatus.setOnAction(e -> list());

        if (typeRecherche.getValue().toString().equals("Status") && hbox.getChildren().contains(RechercheTextField)) {
            typeRechercheStatus.getItems().setAll("En attente", "En Traitement", "Traité");
            typeRechercheStatus.setValue("En attente");
            hbox.getChildren().remove(RechercheTextField);
            hbox.getChildren().add(typeRechercheStatus);
        } else if (!typeRecherche.getValue().toString().equals("Status") && !hbox.getChildren().contains(RechercheTextField)) {
            hbox.getChildren().remove(typeRechercheStatus);
            hbox.getChildren().add(RechercheTextField);
        }

        if ((UserConneter == null && typeRecherche.getValue().toString().equals("Status")) || (typeRecherche.getValue().toString().equals("Status") && UserConneter.getRoles().contains("ROLE_ADMIN"))) {
            arrayList = (ArrayList) produitService.rechercheReclamations(typeRecherche.getValue().toString(), typeRechercheStatus.getValue().toString());
        } else if (UserConneter != null && typeRecherche.getValue().toString().equals("Status") && !UserConneter.getRoles().contains("ROLE_ADMIN")) {
            arrayList = (ArrayList) produitService.rechercheReclamationsAuthorId(typeRecherche.getValue().toString(), typeRechercheStatus.getValue().toString(), MenuAdmin1.Id_user_connecte);
        } //*********
        else if (((UserConneter == null) && RechercheTextField.getText().equals("")) || (RechercheTextField.getText().equals("") && UserConneter.getRoles().contains("ROLE_ADMIN"))) {
            arrayList = (ArrayList) produitService.listerReclamations();
        } else if (UserConneter != null && RechercheTextField.getText().equals("") && !UserConneter.getRoles().contains("ROLE_ADMIN")) {
            arrayList = (ArrayList) produitService.rechercheListeReclamationsByAuthorId(MenuAdmin1.Id_user_connecte);
        } //********
        else if ((UserConneter == null) || (UserConneter.getRoles().contains("ROLE_ADMIN"))) {
            arrayList = (ArrayList) produitService.rechercheReclamations(typeRecherche.getValue().toString(), RechercheTextField.getText());
        } else {
            arrayList = (ArrayList) produitService.rechercheReclamationsAuthorId(typeRecherche.getValue().toString(), RechercheTextField.getText(), MenuAdmin1.Id_user_connecte);
        }

        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        TableViewReclamation.setItems(observableList);

    }

    @FXML
    private void supprimerReclamation(ActionEvent event) {

        if (TableViewReclamation.getSelectionModel().isEmpty()) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Choix invalide")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
        } else {
            List<Reclamation> listReclamation = TableViewReclamation.getSelectionModel().getSelectedItems();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment supprimer cette categorie");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new ServicesReclamation().supprimerReclamation(listReclamation.get(0).getId());
//                System.out.println(listReclamation.get(0).getId());
            }
        }
        list();
    }

    @FXML
    private void ModifierReclamation(ActionEvent event) throws IOException {
        selectionedReclamation = TableViewReclamation.getSelectionModel().getSelectedItem();
        java.util.Date current_date = new java.util.Date();
        java.util.Date creation_date = selectionedReclamation.getDate_creation();
        long diffInMillies = Math.abs(current_date.getTime() - creation_date.getTime());
        long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        
//        Stage stageModifier = new Stage();
        if (!selectionedReclamation.getStatus().equals("En attente") )
        {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Modification impossible: Reclamation en traitement ou traité")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
        }
        else if (diff >= 24) 
        {
            Notifications b = Notifications.create()
                    .title("Erreur")
                    .text("Modification impossible: Reclamation a depasée 24h depuis sa creation")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            b.showWarning();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ModifierReclamationFXML.fxml"));
            Scene scene = new Scene(root);
            stageModifier.setScene(scene);
            stageModifier.show();
        }

    }

    private void AfficherReclamation(ActionEvent event) throws IOException {
        TableViewReclamation.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 2) {
                if (TableViewReclamation.getSelectionModel().getSelectedItem() != null) {
                    selectionedReclamation = TableViewReclamation.getSelectionModel().getSelectedItem();
                    Stage stage = new Stage();
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/AfficherReclamationUniqueFXML.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
    }
}
