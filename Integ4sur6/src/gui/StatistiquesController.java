/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import util.ConnexionBD;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author tibh
 */
public class StatistiquesController implements Initializable {

    @FXML
    private PieChart pieChart;
    Connection cnx;
    Statement ste;
       ObservableList < PieChart.Data > piechartdata;
    ArrayList < String > p = new ArrayList <  > ();
    ArrayList < Integer > c = new ArrayList <> ();
    @FXML
    private Button home;
    @FXML
    private Button Users;
    @FXML
    private Button annonce;
    @FXML
    private Button login;
    @FXML
    private Button home1;
    @FXML
    private Button annonce1;
    @FXML
    private Button annonce11;
    @FXML
    private AnchorPane parent1;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
                
  pieChart.setData(piechartdata);
   
    }
    
    public void loadData() {
     
   
    String query="Select livreur2.nom ,COUNT(commande.id) as nb from livreur2,commande   WHERE livreur2.id=commande.id_livreur Group By livreur2.nom";
 
    piechartdata = FXCollections.observableArrayList();

      cnx = ConnexionBD.getinstance().getCnx();
 
    try {
      
      ResultSet rs = cnx.createStatement().executeQuery(query);
  
      while (rs.next()) {
       
        piechartdata.add(new PieChart.Data(rs.getString("nom")+"("+Integer.toString(rs.getInt("nb"))+")", rs.getInt("nb")));
        String nombre=Integer.toString(rs.getInt("nb"));
        p.add(rs.getString("nom")+nombre);
        c.add(rs.getInt("nb"));
        
        
      }
      
    } catch (SQLException e) {
  System.out.println(e.getMessage());
    }}

    @FXML
    private void livreurs(MouseEvent event) throws IOException {
         FXMLLoader fxml=new FXMLLoader(getClass().getResource("livreurDash.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
        
    }

    @FXML
    private void annonce(ActionEvent event) {
    }

    @FXML
    private void onmouseexit(MouseEvent event) {
    }

    @FXML
    private void Onmouseenter(MouseEvent event) {
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
    }

    @FXML
    private void Commandes(MouseEvent event) throws IOException {
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("Commandeback.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
    }

    @FXML
    private void newlivreur(MouseEvent event) throws IOException {
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("new livreur.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
    }
    
    
    
}
