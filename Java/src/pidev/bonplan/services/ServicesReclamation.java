/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.services;

import java.sql.Date;
import pidev.sandy.entites.Reclamation;
import pidev.sandy.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author X
 */
public class ServicesReclamation {

    public void ajouterReclamation(Reclamation R) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "INSERT INTO reclamation(ref_compte,ref_bonplan,ref_deal,ref_mise,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite)  VALUES('" + R.getRef_compte() + "','" + R.getRef_bonplan() + "','" + R.getRef_deal() + "','" + R.getRef_mise() + "','" + R.getDescription() + "','" + R.getObjet() + "','" + R.getDate_creation() + "','" + R.getDate_traitement() + "','" + R.getStatus() + "','" + R.getPriorite() + "','" + R.getScreenshot() + "','" + R.getNom() + "','" + R.getPrenom() + "','" + R.getEmail() + "','" + R.getNumero_mobile() + "','" + R.getDate_disponibilite() + "')"; //MAJUSCULE NON OBLIGATOIRE 
            Statement st = MyConnection.getInstance().getCnx().createStatement(); // ici comme methode static donc pas de new et on appelle la methode par le nom de la classe
            st.executeUpdate(requete);
            System.out.println("Reclamation ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajouterReclamation2(Reclamation R) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try

            String requete = "INSERT INTO reclamation(ref_compte,ref_bonplan,ref_deal,ref_mise,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            pst.setInt(1, R.getRef_compte());
            pst.setInt(2, R.getRef_bonplan());
            pst.setInt(3, R.getRef_deal());
            pst.setInt(4, R.getRef_mise());

            pst.setString(5, R.getDescription());
            pst.setString(6, R.getObjet());
            pst.setDate(7, R.getDate_creation());
            pst.setDate(8, R.getDate_traitement());
            pst.setString(9, R.getStatus());
            pst.setInt(10, R.getPriorite());
            // pst.setString(11, R.getScreenshot());
            pst.setString(12, R.getNom());
            pst.setString(13, R.getPrenom());//prenom
            pst.setString(14, R.getEmail());//email
            pst.setInt(15, R.getNumero_mobile());
            pst.setDate(16, R.getDate_disponibilite());
            pst.executeUpdate();

            System.out.println("Reclamation Modifiée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajouterReclamation3Backup(Reclamation R, java.sql.Timestamp dateDispo) {
        // java.util.Date date_util = new java.util.Date();
        // java.sql.Date date_sql = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            if (R.getRef_compte() != 0) {
                String requete = "INSERT INTO reclamation(ref_compte,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite,id_author)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_compte());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setDate(5, R.getDate_traitement());
                pst.setString(6, R.getStatus());
                pst.setInt(7, R.getPriorite());
                pst.setString(8, R.getScreenshot());
                pst.setString(9, R.getNom());
                pst.setString(10, R.getPrenom());//prenom
                pst.setString(11, R.getEmail());//email
                pst.setInt(12, R.getNumero_mobile());
                pst.setObject(13, dateDispo);
//            pst.setDate(13, R.getDate_disponibilite());
                pst.setInt(14, R.getId_author());

                pst.executeUpdate();
            } else if (R.getRef_bonplan() != 0) {
                String requete = "INSERT INTO reclamation(ref_bonplan,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite,id_author)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_bonplan());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setDate(5, R.getDate_traitement());
                pst.setString(6, R.getStatus());
                pst.setInt(7, R.getPriorite());
                pst.setString(8, R.getScreenshot());
                pst.setString(9, R.getNom());
                pst.setString(10, R.getPrenom());//prenom
                pst.setString(11, R.getEmail());//email
                pst.setInt(12, R.getNumero_mobile());
                pst.setObject(13, dateDispo);
                pst.setInt(14, R.getId_author());
                pst.executeUpdate();
            } else if (R.getRef_deal() != 0) {
                String requete = "INSERT INTO reclamation(ref_deal,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite,id_author)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_deal());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setDate(5, R.getDate_traitement());
                pst.setString(6, R.getStatus());
                pst.setInt(7, R.getPriorite());
                pst.setString(8, R.getScreenshot());
                pst.setString(9, R.getNom());
                pst.setString(10, R.getPrenom());//prenom
                pst.setString(11, R.getEmail());//email
                pst.setInt(12, R.getNumero_mobile());
                pst.setObject(13, dateDispo);
                pst.setInt(14, R.getId_author());
                pst.executeUpdate();
            } else if (R.getRef_mise() != 0) {
                String requete = "INSERT INTO reclamation(ref_mise,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite,id_author)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_mise());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setDate(5, R.getDate_traitement());
                pst.setString(6, R.getStatus());
                pst.setInt(7, R.getPriorite());
                pst.setString(8, R.getScreenshot());
                pst.setString(9, R.getNom());
                pst.setString(10, R.getPrenom());//prenom
                pst.setString(11, R.getEmail());//email
                pst.setInt(12, R.getNumero_mobile());
                pst.setObject(13, dateDispo);
                pst.setInt(14, R.getId_author());
                pst.executeUpdate();
            } else {
                String requete = "INSERT INTO reclamation(description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite,id_author)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setString(1, R.getDescription());
                pst.setString(2, R.getObjet());
                pst.setObject(3, date_sql);//pst.setDate(3, R.getDate_creation());
                pst.setDate(4, R.getDate_traitement());
                pst.setString(5, R.getStatus());
                pst.setInt(6, R.getPriorite());
                pst.setString(7, R.getScreenshot());
                pst.setString(8, R.getNom());
                pst.setString(9, R.getPrenom());//prenom
                pst.setString(10, R.getEmail());//email
                pst.setInt(11, R.getNumero_mobile());
                pst.setObject(12, dateDispo);//pst.setDate(12, R.getDate_disponibilite());
                pst.setInt(13, R.getId_author());
                pst.executeUpdate();

            }

            System.out.println("Reclamation Ajouté");

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());

        }

    }

    public void ajouterReclamation3(Reclamation R, java.sql.Timestamp dateDispo) {
        // java.util.Date date_util = new java.util.Date();
        // java.sql.Date date_sql = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            if (R.getRef_compte() != 0 && R.getId_author() != 0) {
                String requete = "INSERT INTO reclamation(ref_compte,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite,id_author)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_compte());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setDate(5, R.getDate_traitement());
                pst.setString(6, R.getStatus());
                pst.setInt(7, R.getPriorite());
                pst.setString(8, R.getScreenshot());
                pst.setString(9, R.getNom());
                pst.setString(10, R.getPrenom());//prenom
                pst.setString(11, R.getEmail());//email
                pst.setInt(12, R.getNumero_mobile());
                pst.setObject(13, dateDispo);
//            pst.setDate(13, R.getDate_disponibilite());
                pst.setInt(14, R.getId_author());
                pst.executeUpdate();
            } else if (R.getRef_compte() != 0 && R.getId_author() == 0) {
                String requete = "INSERT INTO reclamation(ref_compte,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_compte());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setDate(5, R.getDate_traitement());
                pst.setString(6, R.getStatus());
                pst.setInt(7, R.getPriorite());
                pst.setString(8, R.getScreenshot());
                pst.setString(9, R.getNom());
                pst.setString(10, R.getPrenom());//prenom
                pst.setString(11, R.getEmail());//email
                pst.setInt(12, R.getNumero_mobile());
                pst.setObject(13, dateDispo);
//            pst.setDate(13, R.getDate_disponibilite());
                pst.executeUpdate();
            } else if (R.getRef_bonplan() != 0 && R.getId_author() != 0) {
                String requete = "INSERT INTO reclamation(ref_bonplan,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite,id_author)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_bonplan());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setDate(5, R.getDate_traitement());
                pst.setString(6, R.getStatus());
                pst.setInt(7, R.getPriorite());
                pst.setString(8, R.getScreenshot());
                pst.setString(9, R.getNom());
                pst.setString(10, R.getPrenom());//prenom
                pst.setString(11, R.getEmail());//email
                pst.setInt(12, R.getNumero_mobile());
                pst.setObject(13, dateDispo);
                pst.setInt(14, R.getId_author());
                pst.executeUpdate();
            } else if (R.getRef_bonplan() != 0 && R.getId_author() == 0) {
                String requete = "INSERT INTO reclamation(ref_bonplan,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_bonplan());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setDate(5, R.getDate_traitement());
                pst.setString(6, R.getStatus());
                pst.setInt(7, R.getPriorite());
                pst.setString(8, R.getScreenshot());
                pst.setString(9, R.getNom());
                pst.setString(10, R.getPrenom());//prenom
                pst.setString(11, R.getEmail());//email
                pst.setInt(12, R.getNumero_mobile());
                pst.setObject(13, dateDispo);
                pst.executeUpdate();
            } else if (R.getRef_deal() != 0 && R.getId_author() != 0) {
                String requete = "INSERT INTO reclamation(ref_deal,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite,id_author)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_deal());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setDate(5, R.getDate_traitement());
                pst.setString(6, R.getStatus());
                pst.setInt(7, R.getPriorite());
                pst.setString(8, R.getScreenshot());
                pst.setString(9, R.getNom());
                pst.setString(10, R.getPrenom());//prenom
                pst.setString(11, R.getEmail());//email
                pst.setInt(12, R.getNumero_mobile());
                pst.setObject(13, dateDispo);
                pst.setInt(14, R.getId_author());
                pst.executeUpdate();
            } else if (R.getRef_deal() != 0 && R.getId_author() == 0) {
                String requete = "INSERT INTO reclamation(ref_deal,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_deal());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setDate(5, R.getDate_traitement());
                pst.setString(6, R.getStatus());
                pst.setInt(7, R.getPriorite());
                pst.setString(8, R.getScreenshot());
                pst.setString(9, R.getNom());
                pst.setString(10, R.getPrenom());//prenom
                pst.setString(11, R.getEmail());//email
                pst.setInt(12, R.getNumero_mobile());
                pst.setObject(13, dateDispo);
                pst.executeUpdate();
            } else if (R.getRef_mise() != 0 && R.getId_author() != 0) {
                String requete = "INSERT INTO reclamation(ref_mise,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite,id_author)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_mise());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setDate(5, R.getDate_traitement());
                pst.setString(6, R.getStatus());
                pst.setInt(7, R.getPriorite());
                pst.setString(8, R.getScreenshot());
                pst.setString(9, R.getNom());
                pst.setString(10, R.getPrenom());//prenom
                pst.setString(11, R.getEmail());//email
                pst.setInt(12, R.getNumero_mobile());
                pst.setObject(13, dateDispo);
                pst.setInt(14, R.getId_author());
                pst.executeUpdate();
            } else if (R.getRef_mise() != 0 && R.getId_author() == 0) {
                String requete = "INSERT INTO reclamation(ref_mise,description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_mise());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setDate(5, R.getDate_traitement());
                pst.setString(6, R.getStatus());
                pst.setInt(7, R.getPriorite());
                pst.setString(8, R.getScreenshot());
                pst.setString(9, R.getNom());
                pst.setString(10, R.getPrenom());//prenom
                pst.setString(11, R.getEmail());//email
                pst.setInt(12, R.getNumero_mobile());
                pst.setObject(13, dateDispo);
                pst.executeUpdate();
            } else if (R.getId_author() != 0) {
                String requete = "INSERT INTO reclamation(description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite,id_author)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setString(1, R.getDescription());
                pst.setString(2, R.getObjet());
                pst.setObject(3, date_sql);//pst.setDate(3, R.getDate_creation());
                pst.setDate(4, R.getDate_traitement());
                pst.setString(5, R.getStatus());
                pst.setInt(6, R.getPriorite());
                pst.setString(7, R.getScreenshot());
                pst.setString(8, R.getNom());
                pst.setString(9, R.getPrenom());//prenom
                pst.setString(10, R.getEmail());//email
                pst.setInt(11, R.getNumero_mobile());
                pst.setObject(12, dateDispo);//pst.setDate(12, R.getDate_disponibilite());
                pst.setInt(13, R.getId_author());
                pst.executeUpdate();

            } else if (R.getId_author() == 0) {
                String requete = "INSERT INTO reclamation(description,objet,date_creation,date_traitement,status,priorite,screenshot,nom,prenom,email,numero_mobile,date_disponibilite)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setString(1, R.getDescription());
                pst.setString(2, R.getObjet());
                pst.setObject(3, date_sql);//pst.setDate(3, R.getDate_creation());
                pst.setDate(4, R.getDate_traitement());
                pst.setString(5, R.getStatus());
                pst.setInt(6, R.getPriorite());
                pst.setString(7, R.getScreenshot());
                pst.setString(8, R.getNom());
                pst.setString(9, R.getPrenom());//prenom
                pst.setString(10, R.getEmail());//email
                pst.setInt(11, R.getNumero_mobile());
                pst.setObject(12, dateDispo);//pst.setDate(12, R.getDate_disponibilite());
                pst.executeUpdate();

            }

            System.out.println("Reclamation Ajouté");

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());

        }

    }

    public void modifierReclamation(Reclamation R, int id, java.sql.Timestamp dateDispo) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            // String requete = "UPDATE reclamation set ref_compte=?,ref_bonplan=?,ref_deal=?,ref_mise=?,description=?,objet=?,date_creation=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 
            String requete = "UPDATE reclamation set ref_compte=?,ref_bonplan=?,ref_deal=?,ref_mise=?,description=?,objet=?,date_creation=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement

            pst.setInt(1, R.getRef_compte());
            pst.setInt(2, R.getRef_bonplan());
            pst.setInt(3, R.getRef_deal());
            pst.setInt(4, R.getRef_mise());
            pst.setString(5, R.getDescription());
            pst.setString(6, R.getObjet());
            pst.setDate(7, R.getDate_creation());
            pst.setDate(8, R.getDate_traitement());
            pst.setString(9, R.getStatus());
            pst.setInt(10, R.getPriorite());
            // pst.setString(11, R.getScreenshot());
            pst.setString(12, R.getNom());
            pst.setString(13, R.getPrenom());//prenom
            pst.setString(14, R.getEmail());//email
            pst.setInt(15, R.getNumero_mobile());
            pst.setDate(16, R.getDate_disponibilite());
            pst.executeUpdate();

            System.out.println("Reclamation Modifiée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifierReclamation2(Reclamation R, int id, java.sql.Timestamp dateDispo) {
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            // String requete = "UPDATE reclamation set ref_compte=?,ref_bonplan=?,ref_deal=?,ref_mise=?,description=?,objet=?,date_creation=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 
//            String requete = "UPDATE reclamation set ref_compte=?,ref_bonplan=?,ref_deal=?,ref_mise=?,description=?,objet=?,date_creation=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 

            if (R.getRef_compte() != 0) {
                String requete = "UPDATE reclamation set ref_compte=?,description=?,objet=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_compte());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setDate(4, R.getDate_traitement());
                pst.setString(5, R.getStatus());
                pst.setInt(6, R.getPriorite());
                pst.setString(7, R.getScreenshot());
                pst.setString(8, R.getNom());
                pst.setString(9, R.getPrenom());//prenom
                pst.setString(10, R.getEmail());//email
                pst.setInt(11, R.getNumero_mobile());
                pst.setObject(12, dateDispo);
                pst.setInt(13, id);

//            pst.setDate(12, R.getDate_disponibilite());
                pst.executeUpdate();
            } else if (R.getRef_bonplan() != 0) {
                String requete = "UPDATE reclamation set ref_bonplan=?,description=?,objet=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_bonplan());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setDate(4, R.getDate_traitement());
                pst.setString(5, R.getStatus());
                pst.setInt(6, R.getPriorite());
                pst.setString(7, R.getScreenshot());
                pst.setString(8, R.getNom());
                pst.setString(9, R.getPrenom());//prenom
                pst.setString(10, R.getEmail());//email
                pst.setInt(11, R.getNumero_mobile());
                pst.setObject(12, dateDispo);
                pst.setInt(13, id);
                pst.executeUpdate();
            } else if (R.getRef_deal() != 0) {
                String requete = "UPDATE reclamation set ref_deal=?,description=?,objet=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_deal());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setDate(4, R.getDate_traitement());
                pst.setString(5, R.getStatus());
                pst.setInt(6, R.getPriorite());
                pst.setString(7, R.getScreenshot());
                pst.setString(8, R.getNom());
                pst.setString(9, R.getPrenom());//prenom
                pst.setString(10, R.getEmail());//email
                pst.setInt(11, R.getNumero_mobile());
                pst.setObject(12, dateDispo);
                pst.setInt(13, id);
                pst.executeUpdate();
            } else if (R.getRef_mise() != 0) {
                String requete = "UPDATE reclamation set ref_mise=?,description=?,objet=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_mise());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setDate(4, R.getDate_traitement());
                pst.setString(5, R.getStatus());
                pst.setInt(6, R.getPriorite());
                pst.setString(7, R.getScreenshot());
                pst.setString(8, R.getNom());
                pst.setString(9, R.getPrenom());//prenom
                pst.setString(10, R.getEmail());//email
                pst.setInt(11, R.getNumero_mobile());
                pst.setObject(12, dateDispo);
                pst.setInt(13, id);
                pst.executeUpdate();
            } else {
                String requete = "UPDATE reclamation set description=?,objet=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setString(1, R.getDescription());
                pst.setString(2, R.getObjet());
                pst.setDate(3, R.getDate_traitement());
                pst.setString(4, R.getStatus());
                pst.setInt(5, R.getPriorite());
                pst.setString(6, R.getScreenshot());
                pst.setString(7, R.getNom());
                pst.setString(8, R.getPrenom());//prenom
                pst.setString(9, R.getEmail());//email
                pst.setInt(10, R.getNumero_mobile());
                pst.setObject(11, dateDispo);
                pst.setInt(12, id);
                pst.executeUpdate();

            }

            System.out.println("Reclamation Modifiée");

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());

        }

    }

    public void modifierReclamationStatus(Reclamation R, int id, java.sql.Timestamp dateDispo) {
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            // String requete = "UPDATE reclamation set ref_compte=?,ref_bonplan=?,ref_deal=?,ref_mise=?,description=?,objet=?,date_creation=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 
//            String requete = "UPDATE reclamation set ref_compte=?,ref_bonplan=?,ref_deal=?,ref_mise=?,description=?,objet=?,date_creation=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 
            java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
            if (R.getRef_compte() != 0) {
                String requete = "UPDATE reclamation set ref_compte=?,description=?,objet=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_compte());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setString(5, R.getStatus());
                pst.setInt(6, R.getPriorite());
                pst.setString(7, R.getScreenshot());
                pst.setString(8, R.getNom());
                pst.setString(9, R.getPrenom());//prenom
                pst.setString(10, R.getEmail());//email
                pst.setInt(11, R.getNumero_mobile());
                pst.setObject(12, dateDispo);
                pst.setInt(13, id);

//            pst.setDate(12, R.getDate_disponibilite());
                pst.executeUpdate();
            } else if (R.getRef_bonplan() != 0) {
                String requete = "UPDATE reclamation set ref_bonplan=?,description=?,objet=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_bonplan());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setString(5, R.getStatus());
                pst.setInt(6, R.getPriorite());
                pst.setString(7, R.getScreenshot());
                pst.setString(8, R.getNom());
                pst.setString(9, R.getPrenom());//prenom
                pst.setString(10, R.getEmail());//email
                pst.setInt(11, R.getNumero_mobile());
                pst.setObject(12, dateDispo);
                pst.setInt(13, id);
                pst.executeUpdate();
            } else if (R.getRef_deal() != 0) {
                String requete = "UPDATE reclamation set ref_deal=?,description=?,objet=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_deal());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setString(5, R.getStatus());
                pst.setInt(6, R.getPriorite());
                pst.setString(7, R.getScreenshot());
                pst.setString(8, R.getNom());
                pst.setString(9, R.getPrenom());//prenom
                pst.setString(10, R.getEmail());//email
                pst.setInt(11, R.getNumero_mobile());
                pst.setObject(12, dateDispo);
                pst.setInt(13, id);
                pst.executeUpdate();
            } else if (R.getRef_mise() != 0) {
                String requete = "UPDATE reclamation set ref_mise=?,description=?,objet=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
                pst.setInt(1, R.getRef_mise());
                pst.setString(2, R.getDescription());
                pst.setString(3, R.getObjet());
                pst.setObject(4, date_sql);
                pst.setString(5, R.getStatus());
                pst.setInt(6, R.getPriorite());
                pst.setString(7, R.getScreenshot());
                pst.setString(8, R.getNom());
                pst.setString(9, R.getPrenom());//prenom
                pst.setString(10, R.getEmail());//email
                pst.setInt(11, R.getNumero_mobile());
                pst.setObject(12, dateDispo);
                pst.setInt(13, id);
                pst.executeUpdate();
            } else {
                String requete = "UPDATE reclamation set description=?,objet=?,date_traitement=?,status=?,priorite=?,screenshot=?,nom=?,prenom=?,email=?,numero_mobile=?,date_disponibilite=? WHERE id=?"; //MAJUSCULE NON OBLIGATOIRE 
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setString(1, R.getDescription());
                pst.setString(2, R.getObjet());
                pst.setObject(3, date_sql);
                pst.setString(4, R.getStatus());
                pst.setInt(5, R.getPriorite());
                pst.setString(6, R.getScreenshot());
                pst.setString(7, R.getNom());
                pst.setString(8, R.getPrenom());//prenom
                pst.setString(9, R.getEmail());//email
                pst.setInt(10, R.getNumero_mobile());
                pst.setObject(11, dateDispo);
                pst.setInt(12, id);
                pst.executeUpdate();

            }

            System.out.println("Reclamation Modifiée");

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());

        }

    }

    public List<Reclamation> listerReclamations() {
        List<Reclamation> myList = new ArrayList<Reclamation>();

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "SELECT * from Reclamation"; //MAJUSCULE NON OBLIGATOIRE 
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Reclamation R = new Reclamation();
                R.setId(rs.getInt(1));
                R.setId_author(rs.getInt(2));
                R.setRef_compte(rs.getInt(3));
                R.setRef_bonplan(rs.getInt(4));
                R.setRef_deal(rs.getInt(5));
                R.setRef_mise(rs.getInt(6));
                R.setDescription(rs.getString(7));
                R.setObjet(rs.getString(8));
//              R.setDate_creation(rs.getDate(8));
                if (rs.getDate(9) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(9)).getTime());
                    R.setDate_creation(a);
                } else {
                    R.setDate_creation(rs.getDate(9));
                }
//              R.setDate_traitement(rs.getDate(9));
                if (rs.getDate(10) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(10)).getTime());
                    R.setDate_traitement(a);
                } else {
                    R.setDate_traitement(rs.getDate(10));
                }
                R.setStatus(rs.getString(11));
                R.setPriorite(rs.getInt(12));
//                        ImageView imagev =new ImageView(new Image("file:C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\"+rs.getString(12)));
//                        imagev.setFitHeight(100);
//                        imagev.setFitWidth(100);
                R.setScreenshot("file:C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\" + rs.getString(13));
                R.setNom(rs.getString(14));
                R.setPrenom(rs.getString(15));//prenom
                R.setEmail(rs.getString(16));//email
                R.setNumero_mobile(rs.getInt(17));
                if (rs.getDate(18) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(18)).getTime());
                    R.setDate_disponibilite(a);
                } else {
                    R.setDate_disponibilite(rs.getDate(18));
                }

//                if(rs.getDate(17)!=null) // test temps dans date
//                {System.out.println(new Date(((Timestamp)rs.getObject(17)).getTime()));
//                System.out.println((Timestamp)rs.getObject(17));
//      SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//      System.out.println("Current Date: " + ft.format(new Date(((Timestamp)rs.getObject(17)).getTime())));
//      System.out.println("Current Date: " + ft.format(new Date(((Timestamp)rs.getObject(17)).getTime())));
//                }
//                R.setDate_disponibilite(rs.getDate(17));
                myList.add(R);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public List<Reclamation> rechercheReclamations(String type, String valeur) {
        List<Reclamation> myList = new ArrayList<Reclamation>();
        String requete = null;
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            if (type.equals("Nom")) {
                requete = "SELECT * from Reclamation where nom like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Prenom")) {
                requete = "SELECT * from Reclamation where prenom like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Email")) {
                requete = "SELECT * from Reclamation where email like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Objet")) {
                requete = "SELECT * from Reclamation where objet like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Message")) {
                requete = "SELECT * from Reclamation where description like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Status")) {
                requete = "SELECT * from Reclamation where status ='" + valeur + "'";; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Tout")) {
                requete = "SELECT * from Reclamation where status like '%" + valeur + "%' or nom like '%" + valeur + "%' or prenom like '%" + valeur + "%' or email like '%" + valeur + "%' or objet like '%" + valeur + "%' or description like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            }

            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Reclamation R = new Reclamation();
                R.setId(rs.getInt(1));
                R.setId_author(rs.getInt(2));
                R.setRef_compte(rs.getInt(3));
                R.setRef_bonplan(rs.getInt(4));
                R.setRef_deal(rs.getInt(5));
                R.setRef_mise(rs.getInt(6));
                R.setDescription(rs.getString(7));
                R.setObjet(rs.getString(8));
//              R.setDate_creation(rs.getDate(8));
                if (rs.getDate(9) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(9)).getTime());
                    R.setDate_creation(a);
                } else {
                    R.setDate_creation(rs.getDate(9));
                }
//              R.setDate_traitement(rs.getDate(9));
                if (rs.getDate(10) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(10)).getTime());
                    R.setDate_traitement(a);
                } else {
                    R.setDate_traitement(rs.getDate(10));
                }
                R.setStatus(rs.getString(11));
                R.setPriorite(rs.getInt(12));
//                        ImageView imagev =new ImageView(new Image("file:C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\"+rs.getString(12)));
//                        imagev.setFitHeight(100);
//                        imagev.setFitWidth(100);
                R.setScreenshot("file:C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\" + rs.getString(13));
                R.setNom(rs.getString(14));
                R.setPrenom(rs.getString(15));//prenom
                R.setEmail(rs.getString(16));//email
                R.setNumero_mobile(rs.getInt(17));
                if (rs.getDate(18) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(18)).getTime());
                    R.setDate_disponibilite(a);
                } else {
                    R.setDate_disponibilite(rs.getDate(18));
                }

//                if(rs.getDate(17)!=null) // test temps dans date
//                {System.out.println(new Date(((Timestamp)rs.getObject(17)).getTime()));
//                System.out.println((Timestamp)rs.getObject(17));
//      SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//      System.out.println("Current Date: " + ft.format(new Date(((Timestamp)rs.getObject(17)).getTime())));
//      System.out.println("Current Date: " + ft.format(new Date(((Timestamp)rs.getObject(17)).getTime())));
//                }
//                R.setDate_disponibilite(rs.getDate(17));
                myList.add(R);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }
    public List<Reclamation> rechercheReclamationsAuthorId(String type, String valeur,int id_author) {
        List<Reclamation> myList = new ArrayList<Reclamation>();
        String requete = null;
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            if (type.equals("Nom")) {
                requete = "SELECT * from Reclamation where id_author="+id_author+" and nom like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Prenom")) {
                requete = "SELECT * from Reclamation where id_author="+id_author+" and prenom like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Email")) {
                requete = "SELECT * from Reclamation where id_author="+id_author+" and email like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Objet")) {
                requete = "SELECT * from Reclamation where id_author="+id_author+" and objet like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Message")) {
                requete = "SELECT * from Reclamation where id_author="+id_author+" and description like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Status")) {
                requete = "SELECT * from Reclamation where id_author="+id_author+" and status ='" + valeur + "'";; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Tout")) {
                requete = "SELECT * from Reclamation where id_author="+id_author+" and status like '%" + valeur + "%' or nom like '%" + valeur + "%' or prenom like '%" + valeur + "%' or email like '%" + valeur + "%' or objet like '%" + valeur + "%' or description like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            }

            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Reclamation R = new Reclamation();
                R.setId(rs.getInt(1));
                R.setId_author(rs.getInt(2));
                R.setRef_compte(rs.getInt(3));
                R.setRef_bonplan(rs.getInt(4));
                R.setRef_deal(rs.getInt(5));
                R.setRef_mise(rs.getInt(6));
                R.setDescription(rs.getString(7));
                R.setObjet(rs.getString(8));
//              R.setDate_creation(rs.getDate(8));
                if (rs.getDate(9) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(9)).getTime());
                    R.setDate_creation(a);
                } else {
                    R.setDate_creation(rs.getDate(9));
                }
//              R.setDate_traitement(rs.getDate(9));
                if (rs.getDate(10) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(10)).getTime());
                    R.setDate_traitement(a);
                } else {
                    R.setDate_traitement(rs.getDate(10));
                }
                R.setStatus(rs.getString(11));
                R.setPriorite(rs.getInt(12));
//                        ImageView imagev =new ImageView(new Image("file:C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\"+rs.getString(12)));
//                        imagev.setFitHeight(100);
//                        imagev.setFitWidth(100);
                R.setScreenshot("file:C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\" + rs.getString(13));
                R.setNom(rs.getString(14));
                R.setPrenom(rs.getString(15));//prenom
                R.setEmail(rs.getString(16));//email
                R.setNumero_mobile(rs.getInt(17));
                if (rs.getDate(18) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(18)).getTime());
                    R.setDate_disponibilite(a);
                } else {
                    R.setDate_disponibilite(rs.getDate(18));
                }

//                if(rs.getDate(17)!=null) // test temps dans date
//                {System.out.println(new Date(((Timestamp)rs.getObject(17)).getTime()));
//                System.out.println((Timestamp)rs.getObject(17));
//      SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//      System.out.println("Current Date: " + ft.format(new Date(((Timestamp)rs.getObject(17)).getTime())));
//      System.out.println("Current Date: " + ft.format(new Date(((Timestamp)rs.getObject(17)).getTime())));
//                }
//                R.setDate_disponibilite(rs.getDate(17));
                myList.add(R);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public List<Reclamation> rechercheListeReclamationsByAuthorId(int id) {
        String requete = null;
        Reclamation R = null;
        List<Reclamation> myList = new ArrayList<Reclamation>();

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            requete = "SELECT * from Reclamation where id_author=" + id; //MAJUSCULE NON OBLIGATOIRE 
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                R = new Reclamation();
                R.setId(rs.getInt(1));
                R.setId_author(rs.getInt(2));
                R.setRef_compte(rs.getInt(3));
                R.setRef_bonplan(rs.getInt(4));
                R.setRef_deal(rs.getInt(5));
                R.setRef_mise(rs.getInt(6));
                R.setDescription(rs.getString(7));
                R.setObjet(rs.getString(8));
//              R.setDate_creation(rs.getDate(8));
                if (rs.getDate(9) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(9)).getTime());
                    R.setDate_creation(a);
                } else {
                    R.setDate_creation(rs.getDate(9));
                }
//              R.setDate_traitement(rs.getDate(9));
                if (rs.getDate(10) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(10)).getTime());
                    R.setDate_traitement(a);
                } else {
                    R.setDate_traitement(rs.getDate(10));
                }
                R.setStatus(rs.getString(11));
                R.setPriorite(rs.getInt(12));
//                        ImageView imagev =new ImageView(new Image("file:C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\"+rs.getString(12)));
//                        imagev.setFitHeight(100);
//                        imagev.setFitWidth(100);
                R.setScreenshot("file:C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\" + rs.getString(13));
                R.setNom(rs.getString(14));
                R.setPrenom(rs.getString(15));//prenom
                R.setEmail(rs.getString(16));//email
                R.setNumero_mobile(rs.getInt(17));
                if (rs.getDate(18) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(18)).getTime());
                    R.setDate_disponibilite(a);
                } else {
                    R.setDate_disponibilite(rs.getDate(18));
                }

                myList.add(R);

            }
            return myList;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public Reclamation rechercheReclamationById(int id) {
        String requete = null;
        Reclamation R = null;
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            requete = "SELECT * from Reclamation where id=" + id; //MAJUSCULE NON OBLIGATOIRE 
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                R = new Reclamation();
                R.setId(rs.getInt(1));
                R.setId_author(rs.getInt(2));
                R.setRef_compte(rs.getInt(3));
                R.setRef_bonplan(rs.getInt(4));
                R.setRef_deal(rs.getInt(5));
                R.setRef_mise(rs.getInt(6));
                R.setDescription(rs.getString(7));
                R.setObjet(rs.getString(8));
//              R.setDate_creation(rs.getDate(8));
                if (rs.getDate(9) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(9)).getTime());
                    R.setDate_creation(a);
                } else {
                    R.setDate_creation(rs.getDate(9));
                }
//              R.setDate_traitement(rs.getDate(9));
                if (rs.getDate(10) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(10)).getTime());
                    R.setDate_traitement(a);
                } else {
                    R.setDate_traitement(rs.getDate(10));
                }
                R.setStatus(rs.getString(11));
                R.setPriorite(rs.getInt(12));
//                        ImageView imagev =new ImageView(new Image("file:C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\"+rs.getString(12)));
//                        imagev.setFitHeight(100);
//                        imagev.setFitWidth(100);
                R.setScreenshot("file:C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\" + rs.getString(13));
                R.setNom(rs.getString(14));
                R.setPrenom(rs.getString(15));//prenom
                R.setEmail(rs.getString(16));//email
                R.setNumero_mobile(rs.getInt(17));
                if (rs.getDate(18) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(18)).getTime());
                    R.setDate_disponibilite(a);
                } else {
                    R.setDate_disponibilite(rs.getDate(18));
                }

            }
            return R;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return R;

    }

    public void supprimerReclamation(Integer r) {
        try {
            String requete = "delete from `reclamation` where id=?";
            PreparedStatement ps;
            ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, r);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ObservableList buildDataBonPlan() {
//     public  ObservableList<PieChart.Data> buildData() {
        List<PieChart.Data> myList = new ArrayList<PieChart.Data>();
        ResultSet rs = null;
        PieChart.Data d;
        ObservableList observableList = null;

        try {
// String requete1 = "SELECT * from Reclamation"; //MAJUSCULE NON OBLIGATOIRE 
// String requete = "SELECT ref_bonplan,COUNT(id) as nbr FROM `Reclamation` group by ref_bonplan";
// String requete = "SELECT r.ref_bonplan,COUNT(r.id) as nbr FROM Reclamation r,bon_plan b where r.ref_bonplan=b.id group by r.ref_bonplan";
            String requete = "SELECT bon_plan.libelle,COUNT(Reclamation.id) as nbr FROM Reclamation left join bon_plan on Reclamation.ref_bonplan=bon_plan.id group by Reclamation.ref_bonplan";

            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            rs = pst.executeQuery(requete);
            while (rs.next()) {

                if (rs.getObject(1) == null) {
                    System.out.println(rs.getString(1));
                    d = new PieChart.Data("Autre ", rs.getInt(2));
                } else {
                    d = new PieChart.Data(rs.getString(1), rs.getInt(2));
                }
//                System.out.println(rs.getString(1));
//                System.out.println(rs.getInt(2));
                myList.add(d);

            }
            observableList = FXCollections.observableArrayList(myList);

            return observableList;

        } catch (Exception e) {

            System.out.println("Error on DB connection BuildDataBonPlan");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
        return observableList;
    }

}
