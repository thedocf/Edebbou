/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Product;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.serviceCategory;
import service.serviceProduct;
/**
 * FXML Controller class
 *
 * @author Th3Doc
 */
public class AddProductController implements Initializable {
File f;
    @FXML
    private Label lblAdd;
    @FXML
    private Label amountLabel;
    @FXML
    private TextField name;
    @FXML
    private Label amountLabel1;
    @FXML
    private Label amountLabel11;
    @FXML
    private Label amountLabel111;
    @FXML
    private Label amountLabel1111;
    @FXML
    private TextField price;
    @FXML
    private TextField qte;
    @FXML
    private DatePicker date;
    @FXML
    private TextField desc;
    @FXML
    private Label amountLabel1112;
    @FXML
    private Button image;
    @FXML
    private ImageView img;
    @FXML
    private Label amountLabel12;
    @FXML
    private ChoiceBox<String> category;
    @FXML
    private Button addPro;
    @FXML
    private Button exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    serviceCategory sp = new serviceCategory ();
  
        ObservableList<String> l = null;
    try {
        l = sp.readAll2();
    } catch (SQLException ex) {
        Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        category.setItems(l);}    

    @FXML
    private void dateFacture(ActionEvent event) {
    }

    @FXML
    private void uploadImage(ActionEvent event) {
          FileChooser fileChooserr = new FileChooser();
        fileChooserr.setTitle("Select Image");
        fileChooserr.setInitialDirectory(new File("C:"));
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooserr.getExtensionFilters().add(imageFilter);
        f = fileChooserr.showOpenDialog(img.getScene().getWindow());
        Image i = new Image(f.getAbsoluteFile().toURI().toString());
        img.setImage(i);
    }

    @FXML
    private void addProduct(ActionEvent event) throws SQLException {
        service.serviceProduct sc = new serviceProduct();
         LocalDate myDate =date.getValue();
    java.sql.Date sqlDate = java.sql.Date.valueOf( myDate );
  
   
        Product cl=new Product(name.getText(),Float.parseFloat(price.getText()),Integer.parseInt(qte.getText()),desc.getText(),sqlDate);
        cl.setImage(f.getAbsoluteFile().toURI().toString());
        serviceCategory c=new serviceCategory();
        int idc=c.getIDBy(category.getValue());
        cl.setCategory(idc);
        
        sc.addProduct(cl);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add");
        alert.setHeaderText(null);
        alert.setContentText("Product's Added!");
        alert.showAndWait();
        Stage stage = (Stage) exit.getScene().getWindow();
   
    stage.close();
    }

    @FXML
    private void exitaction(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
    
}
