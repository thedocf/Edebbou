/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.depots;
import Utils.ConnexionBD;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Alert;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 *
 * @author user
 */
public class Servicedepots 
{
    Connection c=ConnexionBD.getinstance().getcnx();
    public void ajouterdepots (depots p)
    {
       
        try 
        {
            Statement st=c.createStatement();
            String req="insert into depot values("+p.getId()+",'"+p.getEntreprise()+"','"+p.getSurface()+"','"+p.getVille()+"','"+p.getCapacite()+"','"+p.getDescription()+"','"+p.getPhoto()+"')";
            
            st.executeUpdate(req);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Ajout d'un depot!");
             alert.setHeaderText("Information");
             alert.setContentText("Depot ajouté ");
             
             alert.showAndWait();
        } catch (SQLException ex)
        {
            Logger.getLogger(Servicedepots.class.getName()).log(Level.SEVERE, null, ex);
                        Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Ajout d'un depots!");
             alert.setHeaderText("Information");
             alert.setContentText("Depot non ajouté ");
             
             alert.showAndWait();
        }
  
    }
        
   /* public void modifierdepots (depots p,String nom)
    {
        try {
            PreparedStatement pt= c.prepareStatement("update depots set nom= ? where id=?");
            pt.setString(1,nom);
            pt.setInt(2,p.getIdRef());
            
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Servicedepots.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
    public void modifierdepots(int id,String entreprise,int surface,String ville,int capacite,String description){
         String requete="UPDATE depot SET entreprise=? , surface=? , ville=? , capacite=? , description=?  WHERE id=?";
         
         try {
            PreparedStatement pst = c.prepareStatement(requete);
//myCNX.getCnx().prepareStatement(requete);
           
            pst.setString(1,entreprise);
            pst.setInt(2, surface );
            pst.setString(3, ville );
            pst.setInt(4, capacite );
            pst.setString(5, description );
            pst.setInt(6, id);
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Modification d'un depots!");
             alert.setContentText("Depot modifié ");
             
             alert.showAndWait();
        
            
            pst.executeUpdate();
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Modification d'un depots!");
             alert.setHeaderText("Information");
             alert.setContentText("Depot non modifié ");
             
             alert.showAndWait();
            
            
        }
    
    }
    

        public List<depots> afficherdepots (){
        
        ArrayList<depots> myList = new ArrayList();
        try {
            PreparedStatement pt =c.prepareStatement("select * from depot ");
            ResultSet rs= pt.executeQuery();
            while(rs.next()){
                depots a = new depots();
                a.setIdRef(rs.getInt(1));
                a.setEntreprise(rs.getString(2));
                a.setSurface(rs.getInt(3));
                a.setVille(rs.getString(4));
                a.setCapacite(rs.getInt(5));
                a.setDescription(rs.getString(6));
               
           
                myList.add(a);
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return myList;
    }

      
    
    public void supprimerdepots(int id) {
        String requete = "DELETE FROM depot WHERE id =?";

        try {
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            
            TrayNotification tray = new TrayNotification("succès", "Depot Suprimé ", SUCCESS);
            tray.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
public void afficherusertriercapacite() {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `depot` ORDER BY capacite Desc ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getObject(i).toString() + " ");
                }
                System.out.println("\n");

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
      public List<depots> rechercherEvent (String auteur){
        
        String requete;
        requete = "select * FROM depot where entrprise LIKE ? ";
      
        String ch="%"+auteur+"%";
        ArrayList<depots> myList = new ArrayList();
        try {
            
             PreparedStatement pst = c.prepareStatement(requete);
             pst.setString(1,ch);
              
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                depots a = new depots();
                
                a.setIdRef(rs.getInt(1));
                a.setEntreprise(rs.getString(2));
                a.setSurface(rs.getInt(3));
                a.setVille(rs.getString(4));
                a.setCapacite(rs.getInt(5));
                a.setDescription(rs.getString(6));
               
                
           
                myList.add(a);
                

                
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return myList;
    }

 
      
     public ObservableList<depots> triProBySurface()throws SQLException {
      ObservableList<depots> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=c.createStatement();
    ResultSet rs=stm.executeQuery("select * FROM depot order by surface");
     while (rs.next()) {                
      depots a = new depots();
                
                a.setIdRef(rs.getInt(1));
                a.setEntreprise(rs.getString(2));
                a.setSurface(rs.getInt(3));
                a.setVille(rs.getString(4));
                a.setCapacite(rs.getInt(5));
                a.setDescription(rs.getString(6));
         
         arr.add(a);
     }
    return arr;
    }
     
     
    public ObservableList<depots> triProByCapacite()throws SQLException {
      ObservableList<depots> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=c.createStatement();
    ResultSet rs=stm.executeQuery("select * FROM depot order by capacite");
     while (rs.next()) {                
      depots a = new depots();
                
                a.setIdRef(rs.getInt(1));
                a.setEntreprise(rs.getString(2));
                a.setSurface(rs.getInt(3));
                a.setVille(rs.getString(4));
                a.setCapacite(rs.getInt(5));
                a.setDescription(rs.getString(6));
         
         arr.add(a);
     }
    return arr;
    }
      
      
}
