/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Category;
import entite.Product;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.serviceCategory;
import service.serviceProduct;

/**
 * FXML Controller class
 *
 * @author Th3Doc
 */
public class CategorieBackController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private TableView<Category> table;

    @FXML
    private Button annonce111111;
    @FXML
    private Button annonce1111112;
    @FXML
    private Button annonce1111113;
     private ObservableList<Category> recdata = FXCollections.observableArrayList();
      serviceCategory rss = new serviceCategory();
     public ObservableList<Category> list = FXCollections.observableArrayList(rss.afficherPro());
    @FXML
    private TableColumn<Category,Integer> id;
    @FXML
    private TableColumn<Category, String> label;
    @FXML
    private Button retour;
    @FXML
    private Button logout;
    @FXML
    private Button Gdepotsss;
    @FXML
    private Button Glivreur;
    @FXML
    private Button GProduit;
    @FXML
    private Button GCategorie;
    @FXML
    private Button Gcommande;
    @FXML
    private Button Ghome;
    @FXML
    private Button GF;
    @FXML
    private Button GCategorie1;
    @FXML
    private Button GCategorie11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherCategories();
       table.setEditable(true);
        label.setCellFactory(TextFieldTableCell.forTableColumn());
       retour.setVisible(false);
       
       FilteredList<Category> filteredData = new FilteredList<>(list, e -> true);
        search.setOnKeyReleased(e -> {
            search.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Category>) livreur -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lower = newValue.toLowerCase();
                    if (livreur.getLabel().toLowerCase().contains(lower)) {
                        return true;
                    }

                    return false;
                });
            });
            SortedList<Category> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        });
       
    }    

    @FXML
    private void onmouseexit(MouseEvent event) {
    }

    @FXML
    private void Onmouseenter(MouseEvent event) {
    }




    @FXML
    private void livreurs(MouseEvent event) {
    }


    @FXML
    private void Trier(MouseEvent event) throws SQLException {
        
       
        
    }

    @FXML
    private void products(MouseEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/ProduitBack.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
    }
    /* private ObservableList<Category> getCategory() throws SQLException {
        serviceCategory su = new serviceCategory();
        return su.readAll();
    }*/
    private ObservableList<Category> getSortedCategory() throws SQLException {
        serviceCategory su = new serviceCategory();
        return su.triCatsByLabel();
    }
    public void afficherCategories() {
       serviceCategory rs =  new serviceCategory();
       
        recdata.clear();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        label.setCellValueFactory(new PropertyValueFactory<>("label"));
        try {
            
            table.setItems(rs.readAll());
        } catch (SQLException ex) {
            Logger.getLogger(CategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addCateg(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("addCategory.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(e -> {
        afficherCategories();
        });
    }


    @FXML
    private void delCateg(ActionEvent event) throws SQLException {
         serviceCategory su = new serviceCategory();
        ObservableList<Category> selectedRows, allClients;
        allClients = table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();

        for (Category c : selectedRows) {
            su.deleteCategory(c.getId());
           
        }
       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete");
        alert.setHeaderText(null);
        alert.setContentText("Category's Deleted!");
        alert.showAndWait();
        afficherCategories();
    }

    @FXML
    private void triCateg(ActionEvent event) throws SQLException {
        retour.setVisible(true);
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
        label.setCellValueFactory(new PropertyValueFactory<>("label"));
     
        
            table.setItems(getSortedCategory());
    }

    @FXML
    private void changelabel(TableColumn.CellEditEvent event) throws SQLException {
        Category clientSelected = table.getSelectionModel().getSelectedItem();
        clientSelected.setLabel(event.getNewValue().toString());
        serviceCategory su = new serviceCategory();
        su.updateCategory(clientSelected, clientSelected.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update");
        alert.setHeaderText(null);
        alert.setContentText("Category Updated!");
        alert.showAndWait();
        afficherCategories();
    }

    @FXML
    private void retour(ActionEvent event) {
        afficherCategories();
        retour.setVisible(false);
    }

    
    @FXML
    private void logout(ActionEvent event) throws IOException {
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/login.fxml"));
            Parent root= loader.load();
  
            logout.getScene().setRoot(root);
    }

    @FXML
    private void Gdepotsss(ActionEvent event) throws IOException {
        
                                    FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherdepots.fxml"));
            Parent root= loader.load();  
            Gdepotsss.getScene().setRoot(root);
    }

    @FXML
    private void Glivreur(ActionEvent event) throws IOException {
        
                                    FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/livreurDash.fxml"));
            Parent root= loader.load();
  
            Glivreur.getScene().setRoot(root);
    }
    @FXML
    private void GProduit(ActionEvent event) throws IOException {
        
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/ProduitBack.fxml"));
            Parent root= loader.load();  
            GProduit.getScene().setRoot(root);
    }

    @FXML
    private void categoriess(MouseEvent event) {
    }

    @FXML
    private void GCategorie(ActionEvent event) throws IOException {
        
                                            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/CategorieBack.fxml"));
            Parent root= loader.load();
  
            GCategorie.getScene().setRoot(root);
    }

    @FXML
    private void Gcommande(ActionEvent event) throws IOException {
        
                         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Commandeback.fxml"));
            Parent root= loader.load();  
            Gcommande.getScene().setRoot(root);
    }

    @FXML
    private void Ghome(ActionEvent event) throws IOException {
        
                            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/homeback.fxml"));
            Parent root= loader.load();
  
            Ghome.getScene().setRoot(root);
    }

    @FXML
    private void GF(ActionEvent event) throws IOException {
           
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/afficherfournisseurs.fxml"));
            Parent root= loader.load();  
            GF.getScene().setRoot(root);
    }

    @FXML
    private void logout(MouseEvent event) {
    }

      @FXML
    private void buser(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/dash.fxml"));
            Parent root= loader.load();  
            GF.getScene().setRoot(root);
        
    }

    @FXML
    private void bannonce(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/backan.fxml"));
            Parent root= loader.load();  
            GF.getScene().setRoot(root);
        
    }

    
}
