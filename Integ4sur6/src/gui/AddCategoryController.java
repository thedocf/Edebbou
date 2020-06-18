/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Category;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.serviceCategory;

/**
 * FXML Controller class
 *
 * @author Th3Doc
 */
public class AddCategoryController implements Initializable {

    @FXML
    private Label lblAdd;
    @FXML
    private Label amountLabel;
    @FXML
    private TextField labeltxt;
    @FXML
    private Button addPro;
    @FXML
    private Button addPro1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addPCategory(ActionEvent event) throws SQLException {
          service.serviceCategory sc = new serviceCategory();
        Category cl=new Category(labeltxt.getText());
        sc.addCategory(cl);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add");
        alert.setHeaderText(null);
        alert.setContentText("Category's Added!");
        alert.showAndWait();
        Stage stage = (Stage) addPro1.getScene().getWindow();
   
    stage.close();
      
    }

    @FXML
    private void exitaction(ActionEvent event) {
            // get a handle to the stage
    Stage stage = (Stage) addPro1.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
    
}
