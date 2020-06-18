/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entite.Product;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import service.serviceProduct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import service.serviceCategory;
import util.ConnexionBD;
/**
 * FXML Controller class
 *
 * @author Th3Doc
 */
public class ProduitBackController implements Initializable {
File f;
File f1;
    @FXML
    private TextField search;
    private Button home;
    @FXML
    private TableView<Product> table;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Product, Float> price;
    @FXML
    private TableColumn<Product, Integer> qte;
    @FXML
    private TableColumn<Product, Date> date;
    @FXML
    private TableColumn<Product, String> desc;
    @FXML
    private TableColumn<Product, String> image;
    @FXML
    private TableColumn<Product, String> category;
    private ObservableList<Product> recdata = FXCollections.observableArrayList();
         serviceProduct rs = new serviceProduct();
     public ObservableList<Product> list = FXCollections.observableArrayList(rs.afficherPro());
    @FXML
    private TableColumn<Product, Integer> id;
    @FXML
    private Button addButton;
    @FXML
    private Button upButton;
    @FXML
    private Button supButton;
    @FXML
    private Button triButton;
    @FXML
    private Button expButton;
    @FXML
    private Button prix;
    @FXML
    private Button retour;
    @FXML
    private Button dateb;
    @FXML
    private Button category1;
    @FXML
    private Button upload;
    @FXML
    private Label imageLabel;
    @FXML
    private Label catlabel;
    @FXML
    private ChoiceBox<String> category22;
    @FXML
    private Label datelabel;
    @FXML
    private DatePicker date11;
    @FXML
    private ImageView img;
    @FXML
    private Button appli;
    @FXML
    private Button stat;
    @FXML
    private PieChart piechart;
    @FXML
    private Button retour1;
     Connection cnx;
    Statement ste;
       ObservableList < PieChart.Data > piechartdata;
    ArrayList < String > p = new ArrayList <  > ();
    ArrayList < Integer > c = new ArrayList <> ();
    @FXML
    private Label exlab;
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
       afficherProduits();
       table.setEditable(true);
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        
        desc.setCellFactory(TextFieldTableCell.forTableColumn());
        qte.setCellFactory(TextFieldTableCell.<Product, Integer>forTableColumn(new IntegerStringConverter()));
        price.setCellFactory(TextFieldTableCell.<Product, Float>forTableColumn(new FloatStringConverter()));
    
         
        serviceCategory sp = new serviceCategory ();
       
        ObservableList<String> l = null;
    try {
        l = sp.readAll2();
    } catch (SQLException ex) {
        Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        category22.setItems(l);
       
        
    FilteredList<Product> filteredData = new FilteredList<>(list, e -> true);
        search.setOnKeyReleased(e -> {
            search.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Product>) livreur -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lower = newValue.toLowerCase();
                    if (livreur.getNom().toLowerCase().contains(lower)) {
                        return true;
                    }

                    return false;
                });
            });
            SortedList<Product> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        });
        
        retour1.setVisible(false);
        img.setVisible(false);
         retour.setVisible(false);
    addButton.setVisible(true);
    upButton.setVisible(true);
    supButton.setVisible(true);
    triButton.setVisible(true);
    expButton.setVisible(true);
    exlab.setVisible(false);
    dateb.setVisible(false);
    category1.setVisible(false);
    prix.setVisible(false);
    appli.setVisible(false);
    date11.setVisible(false);
    datelabel.setVisible(false);
    category22.setVisible(false);
    catlabel.setVisible(false);
    imageLabel.setVisible(false);
    upload.setVisible(false);
    stat.setVisible(true);
    piechart.setVisible(false);
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

    private void categories(MouseEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/CategorieBack.fxml"));
            Parent root= loader.load();
  
            table.getScene().setRoot(root);
    }

 

    @FXML
    private void Trier(MouseEvent event) {
        
    }

    @FXML
    private void addPro(ActionEvent event) throws IOException {
         Parent root;
        root = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(e -> {
        afficherProduits();
        });
    }

    @FXML
    private void updatePro(ActionEvent event) {
        
        retour.setVisible(true);
        addButton.setVisible(false);
        upButton.setVisible(false);
    supButton.setVisible(false);
    triButton.setVisible(false);
    expButton.setVisible(false);
    dateb.setVisible(false);
    category1.setVisible(false);
    prix.setVisible(false);
    appli.setVisible(true);
    date11.setVisible(true);
    datelabel.setVisible(true);
    category22.setVisible(true);
    catlabel.setVisible(true);
    imageLabel.setVisible(true);
    upload.setVisible(true);
        img.setVisible(true);
        stat.setVisible(false);
        exlab.setVisible(false);
    }

    @FXML
    private void delPro(ActionEvent event) throws SQLException {
         serviceProduct su = new serviceProduct();
        ObservableList<Product> selectedRows, allClients;
        allClients = table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();

        for (Product c : selectedRows) {
            su.deleteProduct(c.getId());
           
        }
       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete");
        alert.setHeaderText(null);
        alert.setContentText("Product's Deleted!");
        alert.showAndWait();
        afficherProduits();
    }

    @FXML
    private void triPro(ActionEvent event) throws SQLException {
        retour.setVisible(true);
        addButton.setVisible(false);
        upButton.setVisible(false);
    supButton.setVisible(false);
    triButton.setVisible(false);
    expButton.setVisible(false);
    dateb.setVisible(true);
    category1.setVisible(true);
    prix.setVisible(true);
    stat.setVisible(false);
    
       
       
    }
private ObservableList<Product> getSortedProduct() throws SQLException {
        serviceProduct su = new serviceProduct();
        return su.triProByDate();
    }
private ObservableList<Product> getSortedProduct1() throws SQLException {
        serviceProduct su = new serviceProduct();
        return su.triProByPrix();
    }
private ObservableList<Product> getSortedProduct2() throws SQLException {
        serviceProduct su = new serviceProduct();
        return su.triProByCat();
    }

    @FXML
    private void exPro(ActionEvent event) throws FileNotFoundException, DocumentException {
        serviceProduct cc = new serviceProduct();
        cc.pdf();
    }
    private void afficherProduits() {
        serviceProduct rs =  new serviceProduct();
        
        recdata.clear();
        name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        category.setCellValueFactory(new PropertyValueFactory<>("cat"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
     
        try {
         
            table.setItems(rs.readAll());
        } catch (SQLException ex) {
            Logger.getLogger(CategorieBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void changeName(TableColumn.CellEditEvent event) throws SQLException {
        Product clientSelected = table.getSelectionModel().getSelectedItem();
        clientSelected.setNom(event.getNewValue().toString());
        serviceProduct su = new serviceProduct();
        su.updateProduct(clientSelected, clientSelected.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update");
        alert.setHeaderText(null);
        alert.setContentText("Product's Updated!");
        alert.showAndWait();
        afficherProduits();
    }

    @FXML
    private void changePrice(TableColumn.CellEditEvent event) throws SQLException {
        Product clientSelected = table.getSelectionModel().getSelectedItem();
        clientSelected.setPrix(Float.parseFloat(event.getNewValue().toString()));
        serviceProduct su = new serviceProduct();
        su.updateProduct(clientSelected, clientSelected.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update");
        alert.setHeaderText(null);
        alert.setContentText("Product's Updated!");
        alert.showAndWait();
        afficherProduits();
    }

    @FXML
    private void changeQte(TableColumn.CellEditEvent event) throws SQLException {
        Product clientSelected = table.getSelectionModel().getSelectedItem();
        clientSelected.setQte(Integer.parseInt(event.getNewValue().toString()));
        serviceProduct su = new serviceProduct();
        su.updateProduct(clientSelected, clientSelected.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update");
        alert.setHeaderText(null);
        alert.setContentText("Product's Updated!");
        alert.showAndWait();
        afficherProduits();
    }

    @FXML
    private void changeDesc(TableColumn.CellEditEvent event) throws SQLException {
        Product clientSelected = table.getSelectionModel().getSelectedItem();
        clientSelected.setDescription(event.getNewValue().toString());
        serviceProduct su = new serviceProduct();
        su.updateProduct(clientSelected, clientSelected.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update");
        alert.setHeaderText(null);
        alert.setContentText("Product's Updated!");
        alert.showAndWait();
        afficherProduits();
    }

    @FXML
    private void date(ActionEvent event) throws SQLException {
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
     
      
            table.setItems(getSortedProduct());
    retour.setVisible(false);
    addButton.setVisible(true);
    upButton.setVisible(true);
    supButton.setVisible(true);
    triButton.setVisible(true);
    expButton.setVisible(true);
    dateb.setVisible(false);
    category1.setVisible(false);
    prix.setVisible(false);
    stat.setVisible(true);
    }

    @FXML
    private void prix(ActionEvent event) throws SQLException {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
     
      
            table.setItems(getSortedProduct1());
    retour.setVisible(false);
    addButton.setVisible(true);
    upButton.setVisible(true);
    supButton.setVisible(true);
    triButton.setVisible(true);
    expButton.setVisible(true);
    dateb.setVisible(false);
    category1.setVisible(false);
    prix.setVisible(false);
    stat.setVisible(true);
    }

    @FXML
    private void category(ActionEvent event) throws SQLException {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
     
      
            table.setItems(getSortedProduct2());
    retour.setVisible(false);
    addButton.setVisible(true);
    upButton.setVisible(true);
    supButton.setVisible(true);
    triButton.setVisible(true);
    expButton.setVisible(true);
    dateb.setVisible(false);
    category1.setVisible(false);
    prix.setVisible(false);
    stat.setVisible(true);
    
    }

    @FXML
    private void retour(ActionEvent event) {
         retour.setVisible(false);
    addButton.setVisible(true);
    upButton.setVisible(true);
    stat.setVisible(true);
    exlab.setVisible(false);
    piechart.setVisible(false);
    supButton.setVisible(true);
    triButton.setVisible(true);
    expButton.setVisible(true);
    dateb.setVisible(false);
    category1.setVisible(false);
    prix.setVisible(false);
    date11.setVisible(false);
    datelabel.setVisible(false);
    category22.setVisible(false);
    catlabel.setVisible(false);
    imageLabel.setVisible(false);
    upload.setVisible(false);
    retour1.setVisible(false);
    appli.setVisible(false);
    img.setVisible(false);
    
    afficherProduits();
    }

    @FXML
    private void changeImage(ActionEvent event) {
        FileChooser fileChooserr = new FileChooser();
        fileChooserr.setTitle("Select Image");
        fileChooserr.setInitialDirectory(new File("C:"));
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooserr.getExtensionFilters().add(imageFilter);
        f1 = fileChooserr.showOpenDialog(img.getScene().getWindow());
        Image i = new Image(f1.getAbsoluteFile().toURI().toString());
        img.setImage(i);
    }

    @FXML
    private void dateFacture1(ActionEvent event) {
    }

    @FXML
    private void appliquer(ActionEvent event) throws SQLException {
        service.serviceProduct sc=new serviceProduct();
        LocalDate myDate =date11.getValue();
    java.sql.Date sqlDate = java.sql.Date.valueOf( myDate );
   
   
   
       ObservableList<Product> selectedRows;
       
        selectedRows = table.getSelectionModel().getSelectedItems();
       
        for (Product cc : selectedRows) {
             Product cl=new Product();
    serviceCategory c=new serviceCategory();
        int idc=c.getIDBy(category22.getValue());
        cl.setCategory(idc);
    
    
    cl.setDate_expiration(sqlDate);
    
    cl.setImage(f1.getAbsoluteFile().toURI().toString());
            cl.setDescription(cc.getDescription());
            cl.setNom(cc.getNom());
            cl.setQte(cc.getQte());
            cl.setPrix(cc.getPrix());
          sc.updateProduct(cl,cc.getId());
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update");
        alert.setHeaderText(null);
        alert.setContentText("Product's Updated!");
        alert.showAndWait();
        retour.setVisible(false);
    addButton.setVisible(true);
    upButton.setVisible(true);
    supButton.setVisible(true);
    triButton.setVisible(true);
    expButton.setVisible(true);
    dateb.setVisible(false);
    category1.setVisible(false);
    prix.setVisible(false);
    appli.setVisible(false);
    date11.setVisible(false);
    datelabel.setVisible(false);
    category22.setVisible(false);
    catlabel.setVisible(false);
    stat.setVisible(true);
    imageLabel.setVisible(false);
    upload.setVisible(false);
    img.setVisible(false);
    retour1.setVisible(false);
        afficherProduits();
    }

    @FXML
    private void stat(ActionEvent event) {
        stat.setVisible(false);
    piechart.setVisible(true);
    retour.setVisible(false);
    retour1.setVisible(true);
    addButton.setVisible(false);
    upButton.setVisible(false);
    supButton.setVisible(false);
    triButton.setVisible(false);
    expButton.setVisible(false);
    dateb.setVisible(false);
    category1.setVisible(false);
    prix.setVisible(false);
    appli.setVisible(false);
    date11.setVisible(false);
    datelabel.setVisible(false);
    category22.setVisible(false);
    catlabel.setVisible(false);
    imageLabel.setVisible(false);
    exlab.setVisible(true);
    upload.setVisible(false);
    img.setVisible(false);
    
    
    LocalDate now=java.time.LocalDate.now();
    
    String query="Select product.cat_id as id ,COUNT(product.id) as nb from product   WHERE product.date_expiration < '"+now+"' Group By product.cat_id";
 
    piechartdata = FXCollections.observableArrayList();

      cnx = ConnexionBD.getinstance().getCnx();
      serviceCategory sc = new serviceCategory();
    try {
      
      ResultSet rs = cnx.createStatement().executeQuery(query);
  
      while (rs.next()) {
       String label = sc.getLabelBy(rs.getInt("id"));
        piechartdata.add(new PieChart.Data(label+"("+Integer.toString(rs.getInt("nb"))+")", rs.getInt("nb")));
        String nombre=Integer.toString(rs.getInt("nb"));
        p.add(label+nombre);
        c.add(rs.getInt("nb"));
        
        
      }
      
    } catch (SQLException e) {
  System.out.println(e.getMessage());
    }
    piechart.setData(piechartdata);
    }

    private void home(ActionEvent event) throws IOException {
        
                                    FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/homeback.fxml"));
            Parent root= loader.load();
  
            home.getScene().setRoot(root);
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
    private void products(MouseEvent event) {
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
