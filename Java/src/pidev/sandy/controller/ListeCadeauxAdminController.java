/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.controller;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXCheckBox;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import static pidev.sandy.controller.AfficherReclamationController.selectionedReclamation;
import pidev.sandy.entites.Boncadeaux;
import pidev.sandy.entites.Cadeaux;
import pidev.sandy.entites.Categoris;
import pidev.sandy.entites.User;
import pidev.sandy.services.AdminService;
import pidev.sandy.services.MyServices;
import pidev.sandy.services.ServiceNotification;
import static sun.security.krb5.KrbException.errorMessage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class ListeCadeauxAdminController implements Initializable {
    Cadeaux cadeaux1=new Cadeaux();
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    @FXML
    private ImageView imgpro;
    @FXML
    private Label labeleLibelle;
    @FXML
    private Label labelDescription;
    @FXML
    private Button buttonModifierCadeaux;
    @FXML
    private Button buttonSupprimerCadeaux;
    @FXML
    private Button buttonAffecterCadeaux;
    @FXML
    private Button buttonAfficherAffectation;
    @FXML
    private Button buttonAjouterCadeaux;
    @FXML
    private TableView<Cadeaux> tableviewCadeaux;
    @FXML
    private TableColumn<Cadeaux, String> tablecolonneCadeauId;

    @FXML
    private TableColumn<Cadeaux, String> tablecolonneCadeauLibelle;
    @FXML
    private TableColumn<Cadeaux, String> tablecolonneCadeauDescription;
    @FXML
    private TableColumn<Cadeaux, String> tablecolonneCadeauPrix;
    @FXML
    private TableColumn<Cadeaux, String> tablecolonneCadeauQuantite;
    @FXML
    private TableColumn<Cadeaux, String> tablecolonneCadeauValeur;
    @FXML
    private TableColumn<Cadeaux, String> tablecolonneCadeauCategeoris;

    public static int numeroCommande = 0;
    Document doc = new Document();
    private List<Cadeaux> listCadeaux;//retourner liste de la select
    private ObservableList<Cadeaux> ObservablelisteCadeaux;//pour la table view
    @FXML
    private Button buttonAfficgherListeCadeaux;
    @FXML
    private BorderPane ListeCadeauxBorderPane;
    @FXML
    private TextField ChercherCadeaux;
    @FXML
    private JFXCheckBox Libelle;
    @FXML
    private JFXCheckBox PrixReel;
    @FXML
    private JFXCheckBox Quantite;
    @FXML
    private JFXCheckBox Valeur;
    @FXML
    private JFXCheckBox Categoris;
    MyServices services = new MyServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        createtableviewCadeaux();
        
    /**********************************************/
  tableviewCadeaux.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 2) {
                if (tableviewCadeaux.getSelectionModel().getSelectedItem() != null) {
                    Cadeaux selectionedCado = tableviewCadeaux.getSelectionModel().getSelectedItem();

                     Stage window = new Stage();
                    System.out.println("file:C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\"+selectionedCado.getImage());
                            window.setMinWidth(250);
                            ImageView imagevPOPUP = new ImageView(new javafx.scene.image.Image("file:C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\"+selectionedCado.getImage()));
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
        });


        
        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Cadeaux, String>, TableCell<Cadeaux, String>> cellFactory
                = //
                new Callback<TableColumn<Cadeaux, String>, TableCell<Cadeaux, String>>() {
            @Override
            public TableCell call(final TableColumn<Cadeaux, String> param) {
                final TableCell<Cadeaux, String> cell = new TableCell<Cadeaux, String>() {

                    final Button btn = new Button("Supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
 
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

                                    Cadeaux CadeauxSelectione = getTableView().getItems().get(getIndex());
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Vous voulez vraiment supprimer ce cadeau");
                                    Optional<ButtonType> action = alert.showAndWait();
                                    if (action.get() == ButtonType.OK) {
                                        
                                                if (CadeauxSelectione != null) {
                                        services.supprimerCadeaux(CadeauxSelectione);
                                                }
                         
                                    }
                                }
                                createtableviewCadeaux();

                            });
//                            btn.getStyleClass().removeAll("addBobOk, focus"); 
//                            btn.getStyleClass().add("addBobOk");
//                             btn.setStyle("-fx-background-color: #00ff00");
                            setGraphic(btn);
                            setText(null);
                            //  System.out.println(item);
                        }
                    }
                };
                return cell;
            }
        };

        actionCol.setCellFactory(cellFactory);

 
        ArrayList arrayList = (ArrayList) services.afficherlisteCadeaux();
       ObservablelisteCadeaux= FXCollections.observableArrayList(arrayList);
    
    
    
    
    
       tableviewCadeaux.setItems(ObservablelisteCadeaux);
        tableviewCadeaux.getColumns().addAll( 
        
                actionCol);
    
 
        
        
        
        tableviewCadeaux.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        selectionnerUnCadeaux(newValue);
                    } catch (BadElementException ex) {
                        Logger.getLogger(ListeCadeauxAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ListeCadeauxAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

    }

    public void selectionnerUnCadeaux(Cadeaux cadeaux) throws BadElementException, IOException//quand on selectionner un user il declanche  
    {

        labelDescription.setText(cadeaux.getDescription());
        labeleLibelle.setText(cadeaux.getLibelle());

        File file = new File("C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + cadeaux.getImage());
        javafx.scene.image.Image img = new javafx.scene.image.Image(file.toURI().toURL().toString());

        imgpro.setImage(img);
    }

    @FXML
    private void modifierCadeau(ActionEvent event) throws IOException {

        Cadeaux cadeaux = tableviewCadeaux.getSelectionModel().getSelectedItem();

        if (cadeaux != null) {

            boolean buttonConfimClicked = showModifierCadeauxFXML(cadeaux);//cette qui va ouvrir cette page (dmender cete methode) 
            if (buttonConfimClicked) {

                services.modifierCadeaux(cadeaux);
                createtableviewCadeaux();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner un cadeaux");
            alert.show();
        }

    }

    @FXML
    private void supprimerCadeau(ActionEvent event) {

        Cadeaux cadeaux = tableviewCadeaux.getSelectionModel().getSelectedItem();

        if (cadeaux != null) {

            services.supprimerCadeaux(cadeaux);
            createtableviewCadeaux();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner un Cadeaux");
            alert.show();
        }

    }

    @FXML
    private void AffecterCadeaux(ActionEvent event) throws IOException, SQLException {

        Cadeaux cadeaux = tableviewCadeaux.getSelectionModel().getSelectedItem();
        Boncadeaux boncadeaux = new Boncadeaux();

        if (cadeaux != null) {

            boolean buttonConfimClicked = showAjouterBonCadeauxFXML(cadeaux, boncadeaux);//cette qui va ouvrir cette page (dmender cete methode) 
            if (buttonConfimClicked) {
                System.out.println("eeeeeee====>" + boncadeaux.toString());

                services.AjouteBonCadeaux(boncadeaux);

                ServiceNotification.showNotif("Succe", "Membre affecter avec succée");

                createtableviewCadeaux();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner uu cadeaux");
            alert.show();
        }

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    @FXML
    private void AfficherAffectation(ActionEvent event) throws IOException {

        setNode(FXMLLoader.load(getClass().getResource("/pidev/sandy/GUI/ListAffectationDemandeCadeaux.fxml")));

    }

    private void setNode(Node node) {
        ListeCadeauxBorderPane.getChildren().clear();
        ListeCadeauxBorderPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void ajouterCadeaux(ActionEvent event) throws IOException, SQLException {

        Cadeaux cadeaux = new Cadeaux();
        Boncadeaux boncadeaux = new Boncadeaux();
        boolean Ajout = true;

        //connecter au fb bech nimchi lil set client fil controlleur AjouterPersonne2Controller
        boolean buttonConfimClicked = showAjouterCadeauxFXML(cadeaux, boncadeaux, Ajout);//cette qui va ouvrir cette page (dmender cete methode) 

        if (buttonConfimClicked) {

            services.AjouteBonCadeaux(boncadeaux);

            createtableviewCadeaux();

        }

    }

    private boolean showAjouterCadeauxFXML(Cadeaux cadeaux, Boncadeaux boncadeaux, boolean Ajout) throws IOException {
        /**
         * ***********************1ere etape*******************************
         */

        FXMLLoader loader = new FXMLLoader();//qui va charger la fichier de type fxml
        loader.setLocation(AjouterUilisateurController.class.getResource("/pidev/sandy/GUI/AjouterCadeaux.fxml"));//ouvrire ce fenetre
        AnchorPane page = (AnchorPane) loader.load();//d'ou et cree un attribut page qui va enregister mon loder deja telechagere
        //==> mon fichier et deje tawa en memoire
        /**
         * ***********************2eme etape(configuration pour new
         * fenter*******************************
         */

        //TAWA POUR en voie cette page il faut cree un stage
        Stage dialogeStage = new Stage();
        //a partir de ce stage j'ai cree un titre pour le client
        dialogeStage.setTitle("Ajouter des cadeaux");

        //craetion de la scene et passe l'attrbut page qui le petite ecran de la l'affichage qui on a telechager
        Scene scene = new Scene(page);

        //charger la scene
        dialogeStage.setScene(scene);
        /**
         * ***********************3eme etape*******************************
         */
//cree un attribut unique et nous avons  utliser cete endroit pour appele le controller de cette (AjouterPersonneFXML)
//on va tawa lil AjouterPersonne2Controller donner lui 2 clics(pour voire les attribut stage,personne,confiramation bbuton)
        AjouterCadeauxController controller = loader.getController();

//tawa on va vers les methode ili 3amlou applele lil methode hethi lekbira
        controller.setStage(dialogeStage);
        controller.setCadeaux(cadeaux);//ce client passer en parametre passer pa le controlleur
        controller.setBoncadeaux(boncadeaux);//ce client passer en parametre passer pa le controlleur
        controller.setAjout(Ajout);//ce client passer en parametre passer pa le controlleur

        /**
         * ***********************4eme etape enfin montre l'ecran a l'user
         * attend l'insertion des donne*******************************
         */
//je ne encore envoyoer cet ecran il va aparaitre a partir
        dialogeStage.showAndWait();//je veut lui montre et attender que l'user fermer cette petite ecran plus tard

        /**
         * **********************5eme etape attendre l'user fermer le
         * fentre***************************
         */
//on va modifier setClient pour //on va modifier setClient pour  
        return controller.isButtonConfimClicked();
    }

    @FXML
    private void createtableviewCadeaux() {

        tablecolonneCadeauId.setCellValueFactory(new PropertyValueFactory<>("Id"));//nom reference pour la colonne
        tablecolonneCadeauLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));//nom reference pour la colonne
        //reference pour la colonne
        tablecolonneCadeauDescription.setCellValueFactory(new PropertyValueFactory<>("description"));//reference pour la colonne
        tablecolonneCadeauPrix.setCellValueFactory(new PropertyValueFactory<>("prix_reel"));//reference pour la colonne
        tablecolonneCadeauQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite_actuel"));//reference pour la colonne
        tablecolonneCadeauValeur.setCellValueFactory(new PropertyValueFactory<>("valeur_point"));//reference pour la colonne
        tablecolonneCadeauCategeoris.setCellValueFactory(new PropertyValueFactory<>("categorisCadeaux"));//reference pour la colonne
        listCadeaux = services.afficherlisteCadeaux();

        ObservablelisteCadeaux = FXCollections.observableArrayList(listCadeaux);//convertir la liste des client en observable liste
        tableviewCadeaux.setItems(ObservablelisteCadeaux);

    }

    public void supprimerCadeaux() throws IOException {

        Cadeaux cadeaux = tableviewCadeaux.getSelectionModel().getSelectedItem();

        if (cadeaux != null) {

            services.supprimerCadeaux(cadeaux);
            createtableviewCadeaux();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner un cadeaux");
            alert.show();
        }

    }

    public void modifierCadeaux() throws IOException {

        Cadeaux cadeaux = tableviewCadeaux.getSelectionModel().getSelectedItem();

        if (cadeaux != null) {

            boolean buttonConfimClicked = showModifierCadeauxFXML(cadeaux);//cette qui va ouvrir cette page (dmender cete methode) 
            if (buttonConfimClicked) {

                services.modifierCadeaux(cadeaux);

                createtableviewCadeaux();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner un cadeaux");
            alert.show();
        }

    }

    private boolean showAjouterBonCadeauxFXML(Cadeaux cadeaux, Boncadeaux boncadeaux) throws IOException {
        /**
         * ***********************1ere etape*******************************
         */

        FXMLLoader loader = new FXMLLoader();//qui va charger la fichier de type fxml
        loader.setLocation(AffecterCadeauxController.class.getResource("/pidev/sandy/GUI/AffecterCadeaux.fxml"));//ouvrire ce fenetre
        AnchorPane page = (AnchorPane) loader.load();//d'ou et cree un attribut page qui va enregister mon loder deja telechagere
        //==> mon fichier et deje tawa en memoire
        /**
         * ***********************2eme etape(configuration pour new
         * fenter*******************************
         */

        //TAWA POUR en voie cette page il faut cree un stage
        Stage dialogeStage = new Stage();
        //a partir de ce stage j'ai cree un titre pour le client
        dialogeStage.setTitle("Gestion des cadeaux");

        //craetion de la scene et passe l'attrbut page qui le petite ecran de la l'affichage qui on a telechager
        Scene scene = new Scene(page);

        //charger la scene
        dialogeStage.setScene(scene);
        /**
         * ***********************3eme etape*******************************
         */
//cree un attribut unique et nous avons  utliser cete endroit pour appele le controller de cette (AjouterPersonneFXML)
//on va tawa lil AjouterPersonne2Controller donner lui 2 clics(pour voire les attribut stage,personne,confiramation bbuton)
        AffecterCadeauxController controller = loader.getController();

//tawa on va vers les methode ili 3amlou applele lil methode hethi lekbira
        controller.setStage(dialogeStage);
        controller.setCadeaux(cadeaux);
        controller.setBoncadeaux(boncadeaux);//ce client passer en parametre passer pa le controlleur

        /**
         * ***********************4eme etape enfin montre l'ecran a l'user
         * attend l'insertion des donne*******************************
         */
//je ne encore envoyoer cet ecran il va aparaitre a partir
        dialogeStage.showAndWait();//je veut lui montre et attender que l'user fermer cette petite ecran plus tard

        /**
         * **********************5eme etape attendre l'user fermer le
         * fentre***************************
         */
//on va modifier setClient pour //on va modifier setClient pour  
        return controller.isButtonConfimClicked();
    }

    private boolean showModifierCadeauxFXML(Cadeaux cadeaux) throws IOException {
        /**
         * ***********************1ere etape*******************************
         */

        FXMLLoader loader = new FXMLLoader();//qui va charger la fichier de type fxml
        loader.setLocation(ModifierCadeauxController.class.getResource("/pidev/sandy/GUI/ModifierCadeaux.fxml"));//ouvrire ce fenetre
        AnchorPane page = (AnchorPane) loader.load();//d'ou et cree un attribut page qui va enregister mon loder deja telechagere
        //==> mon fichier et deje tawa en memoire
        /**
         * ***********************2eme etape(configuration pour new
         * fenter*******************************
         */

        //TAWA POUR en voie cette page il faut cree un stage
        Stage dialogeStage = new Stage();
        //a partir de ce stage j'ai cree un titre pour le client
        dialogeStage.setTitle("Modifer cadeaux");

        //craetion de la scene et passe l'attrbut page qui le petite ecran de la l'affichage qui on a telechager
        Scene scene = new Scene(page);

        //charger la scene
        dialogeStage.setScene(scene);
        /**
         * ***********************3eme etape*******************************
         */
//cree un attribut unique et nous avons  utliser cete endroit pour appele le controller de cette (AjouterPersonneFXML)
//on va tawa lil AjouterPersonne2Controller donner lui 2 clics(pour voire les attribut stage,personne,confiramation bbuton)
        ModifierCadeauxController controller = loader.getController();

//tawa on va vers les methode ili 3amlou applele lil methode hethi lekbira
        controller.setStage(dialogeStage);
        controller.setCadeaux(cadeaux);//ce client passer en parametre passer pa le controlleur

        /**
         * ***********************4eme etape enfin montre l'ecran a l'user
         * attend l'insertion des donne*******************************
         */
//je ne encore envoyoer cet ecran il va aparaitre a partir
        dialogeStage.showAndWait();//je veut lui montre et attender que l'user fermer cette petite ecran plus tard

        /**
         * **********************5eme etape attendre l'user fermer le
         * fentre***************************
         */
//on va modifier setClient pour //on va modifier setClient pour  
        return controller.isButtonConfimClicked();
    }

    @FXML
    private void SelectedValue(ActionEvent event) {

        if (Libelle.isSelected()) {

            PrixReel.setSelected(false);
            Quantite.setSelected(false);
            Valeur.setSelected(false);
            Categoris.setSelected(false);

        } else if (PrixReel.isSelected()) {

            Libelle.setSelected(false);
            Quantite.setSelected(false);
            Valeur.setSelected(false);
            Categoris.setSelected(false);
        } else if (Quantite.isSelected()) {

            PrixReel.setSelected(false);
            Libelle.setSelected(false);
            Valeur.setSelected(false);
            Categoris.setSelected(false);
        } else if (Valeur.isSelected()) {

            PrixReel.setSelected(false);
            Quantite.setSelected(false);
            Libelle.setSelected(false);
            Categoris.setSelected(false);
        } else if (Categoris.isSelected()) {

            PrixReel.setSelected(false);
            Quantite.setSelected(false);
            Valeur.setSelected(false);
            Libelle.setSelected(false);
        } else {
            PrixReel.setSelected(false);
            Quantite.setSelected(false);
            Valeur.setSelected(false);
            Libelle.setSelected(false);
            Categoris.setSelected(false);

        }

    }

    @FXML
    private void ChercherCadeauxFonction(KeyEvent event) {
        AdminService adminService = new AdminService();
        List<Cadeaux> cadeauxs = new ArrayList<>();

        if (ChercherCadeaux.getText().trim().equals("")) {
            listCadeaux = adminService.rechercheAvanceeCadeaux("", "");

        } else {

            if (Libelle.isSelected() || PrixReel.isSelected() || Quantite.isSelected() || Valeur.isSelected() || Categoris.isSelected()) {

                if (Libelle.isSelected()) {
       
                    listCadeaux = adminService.rechercheAvanceeCadeaux(Libelle.getText(), ChercherCadeaux.getText());

                } else if (PrixReel.isSelected()) {
                    
                    
                    listCadeaux = adminService.rechercheAvanceeCadeaux(PrixReel.getText(), ChercherCadeaux.getText());

                } else if (Quantite.isSelected()) {
                    listCadeaux = adminService.rechercheAvanceeCadeaux(Quantite.getText(), ChercherCadeaux.getText());

                } else if (Valeur.isSelected()) {
                    listCadeaux = adminService.rechercheAvanceeCadeaux(Valeur.getText(), ChercherCadeaux.getText());

                } else if (Categoris.isSelected()) {

//           
                    listCadeaux = adminService.rechercheAvanceeCadeaux(Categoris.getText(), ChercherCadeaux.getText());

                } else {
                    listCadeaux = adminService.rechercheAvanceeCadeaux("Tout", ChercherCadeaux.getText());

                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error valeur");
                alert.setHeaderText("???");
                alert.setContentText("Il Faut cocher une valeur");
                alert.show();
                createtableviewCadeaux();
                listCadeaux = services.afficherlisteCadeaux();

            }
        }

        ObservablelisteCadeaux = FXCollections.observableArrayList(listCadeaux);//convertir la liste des client en observable liste
        tableviewCadeaux.setItems(ObservablelisteCadeaux);
        System.out.println(cadeauxs.toString() + "V" + Valeur.getText());

    }
}
