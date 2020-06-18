/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.serviceCategory;
import service.serviceProduct;

/**
 * FXML Controller class
 *
 * @author Th3Doc
 */
public class UpdateProductController implements Initializable {

    /**
     * Initializes the controller class.
     */
     public static int x;
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
    private TextField desc;
    @FXML
    private Label amountLabel2;
    @FXML
    private TextField idP;
    @FXML
    private DatePicker date;
    @FXML
    private Label amountLabel11111;
    @FXML
    private Button UpPro1;
    @FXML
    private ImageView img;
    @FXML
    private Label amountLabel12;
    @FXML
    private ChoiceBox<String> category;
    @FXML
    private Button UpPro;
    @FXML
    private Button exit;

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x=x;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceCategory sp = new serviceCategory ();
        serviceProduct ss = new serviceProduct();
        if (x!=0)
        {
            try {
                idP.setText(Integer.toString(x));
                idP.setDisable(true);
                name.setText(ss.getNameBy(x));
                desc.setText(ss.getDescBy(x));
                price.setText(ss.getPriceBy(x).toString());
                qte.setText(ss.getQteBy(x).toString());
                date.setValue(ss.getDateBy(x).toLocalDate());
            } catch (SQLException ex) {
                Logger.getLogger(UpdateProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ObservableList<String> l = null;
    try {
        l = sp.readAll2();
    } catch (SQLException ex) {
        Logger.getLogger(gui.AddProductController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        category.setItems(l);
            
    }    

    @FXML
    private void dateFacture(ActionEvent event) {
    }

    @FXML
    private void changeImage(ActionEvent event) {
    }

    @FXML
    private void livreurs(MouseEvent event) {
    }

    @FXML
    private void UpdateProduct(ActionEvent event) {
    }

    @FXML
    private void exitaction(ActionEvent event) {
    }
    
}
