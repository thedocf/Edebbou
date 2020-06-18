/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.fournisseurs;
import util.ConnexionBD;
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
import javafx.event.Event;
import javafx.scene.control.Alert;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 *
 * @author user
 */
public class Servicefournisseurs {

      private Connection con = ConnexionBD.getinstance().getCnx();
    public void ajouterfournisseurs(fournisseurs p) {

        try {
            Statement st = con.createStatement();
            String req = "insert into fournisseur values(" + p.getIdFour() + ",'" + p.getDepot_id()+ "','" + p.getNom() + "','" + p.getPrenom() + "','" + p.getNumTel() + "','" + p.getDisponible() + "')";

            st.executeUpdate(req);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout d'un Fournisseur!");
            alert.setHeaderText("Information");
            alert.setContentText("Fournisseur ajouté ");
            alert.showAndWait();
            TrayNotification tray = new TrayNotification("succès", "Fournisseur ajouté", SUCCESS);
            tray.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(Servicefournisseurs.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout d'un Fournisseur!");
            alert.setHeaderText("Information");
            alert.setContentText("Fournisseur non ajouté ");

            alert.showAndWait();
        }

    }
 
    
    
    
    
    
    
    
    
    public void modifierfournisseurs(int id,int depot_id, String nom, String prenom, int numTel, String disponible) {
        String requete = "UPDATE fournisseur SET depot_id=?, nom=? , prenom=? , numTel=? , disponible=? WHERE id=?";
        try {
            PreparedStatement pst = con.prepareStatement(requete);

            pst.setInt(1, depot_id);
            pst.setString(2, nom);
            pst.setString(3, prenom);
            pst.setInt(4, numTel);
            pst.setString(5, disponible);
            pst.setInt(6, id);
            pst.executeUpdate();
            TrayNotification tray = new TrayNotification("succès", "Fournisseur modifié", SUCCESS);
            tray.showAndWait();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    
    public List<fournisseurs> afficherfournisseurs() {

        ArrayList<fournisseurs> myList = new ArrayList();
        try {
            PreparedStatement pt = con.prepareStatement("select * from fournisseur ");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                fournisseurs a = new fournisseurs();
                a.setIdFour(rs.getInt(1));
                a.setDepot_id(rs.getInt(2));
                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setNumTel(rs.getInt(5));
                a.setDisponible(rs.getString(6));

                myList.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    

    public void supprimerfournisseurs(int id) {
        String requete = "DELETE FROM fournisseur WHERE id =?";

        try {
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            TrayNotification tray = new TrayNotification("succès", "Fournisseur Suprimé", SUCCESS);
            tray.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void afficherusertriernumtel() {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `fournisseur` ORDER BY numTel Desc ");
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

    public List<fournisseurs> rechercherEvent(String auteur) {

        String requete = "select * FROM fournisseur where (numTel LIKE ? )";

        String ch = "%" + auteur + "%";
        ArrayList<fournisseurs> myList = new ArrayList();
        try {

            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, ch);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                fournisseurs a = new fournisseurs();
                a.setIdFour(rs.getInt(1));
                a.setDepot_id(rs.getInt(2));

                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setNumTel(rs.getInt(5));
                a.setDisponible(rs.getString(6));

                myList.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }
    
    
    
    public List<fournisseurs> afficherPro () {
        List<fournisseurs> ll = new ArrayList<fournisseurs>();
        try {
            PreparedStatement pt;
            pt = con.prepareStatement("select * from product");
            ResultSet rs = pt.executeQuery();
            
            while (rs.next()) {                
          fournisseurs a = new fournisseurs();
                a.setIdFour(rs.getInt(1));
                a.setDepot_id(rs.getInt(2));

                a.setNom(rs.getString(3));
                a.setPrenom(rs.getString(4));
                a.setNumTel(rs.getInt(5));
                a.setDisponible(rs.getString(6));
         
         ll.add(a);
     }
        } catch (SQLException ex) {
            Logger.getLogger(Servicefournisseurs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }
    
    
    
    
    
      public void pdf() throws FileNotFoundException
    {
        try {
            String file_name ="C:\\Users\\M-YAHYAOUI\\Desktop\\pdf\\Yahyaoui.pdf";
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(file_name));
            } catch (DocumentException ex) {
                Logger.getLogger(Servicefournisseurs.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            document.open();
            PreparedStatement pt = con.prepareStatement("select * from fournisseur");
            ResultSet rs = pt.executeQuery();
            
            while (rs.next()) { 
                Paragraph para=new Paragraph("Fournisseurs [ depot_id : " + rs.getInt(2)+ " nom: " +rs.getString(3) + " prenom : " + rs.getString(4) + " numTel : " + rs.getInt(5)+" disponible : " + rs.getString(6)+"]");
                document.add(para);
                document.add(new Paragraph(" "));
             // Alert alert = new Alert(Alert.AlertType.INFORMATION);
            // alert.setTitle("PDF");
            // alert.setHeaderText("Information");
            // alert.setContentText("Voir votre dossier pdf sur le bureau  ");
             
           // alert.showAndWait();

            }
            TrayNotification tray = new TrayNotification("succès", "Voir votre dossier pdf sur le bureau", SUCCESS);
            tray.showAndWait();  
            document.close();
        } catch (SQLException ex) {
            Logger.getLogger(fournisseurs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(fournisseurs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(fournisseurs.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
