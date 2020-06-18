/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import service.Servicedepots;
import util.ConnexionBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author OrdiOne
 */
public class StatistiqueController implements Initializable {
  
       private Connection con = ConnexionBD.getinstance().getCnx();
      private final Servicedepots serv = new Servicedepots();

    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private Text txtPhotoUser;
    @FXML
    private Button retour;
    @FXML
    private ImageView re;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String req =" SELECT entreprise,surface from depot ";
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        try {
            PreparedStatement ste = (PreparedStatement) con.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
            }
            barChart.getData().add(series);
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void retourafficher(ActionEvent event) {
          try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("afficherdepots.fxml"));
            Parent root= loader.load();
            AfficherdepotsController rc= loader.getController();            
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
        
    }
}
