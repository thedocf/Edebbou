/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import pidev.sandy.entites.Repondre;
import pidev.sandy.entites.demande_experience;
import pidev.sandy.entites.notif_demande;
 
 
 
import pidev.sandy.utils.MyConnection;

/**
 *
 * @author SLIMEN
 */
public class DemandeRecommandation {
    
      public void ajouterDemande(demande_experience p) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "INSERT INTO demande_experience(nom,descripion,addresse,image,datecreation,iduser) VALUES(?,?,?,?,?,?)"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            st.setString(1, p.getNom());
            st.setString(2, p.getDescripion());
            st.setString(3, p.getAddresse());
            st.setString(4, p.getImage());
            st.setDate(5, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
             st.setInt(6, p.getIduser());

            st.executeUpdate();
            System.out.println("demande ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
      
          public void modifierDemande(demande_experience p, int id) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "UPDATE  demande_experience set nom=?,descripion=?,addresse=?,image=? where id="+id; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
   
            st.setString(1, p.getNom());
            st.setString(2, p.getDescripion());
            st.setString(3,p.getAddresse());
            st.setString(4,p.getImage());
            st.executeUpdate();
            System.out.println("modifer");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
          
            public void SupprimerDemande(int id) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "DELETE  FROM demande_experience where id="+id; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
           
  
            st.executeUpdate();
            System.out.println("Demande supprimer");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
            
            public List<demande_experience> listerDemandes() {
        List<demande_experience> myList = new ArrayList<demande_experience>();

        try {
            String requete3 = "SELECT * From demande_experience";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                demande_experience p = new demande_experience();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setDescripion(rs.getString(3));
                p.setImage(rs.getString(5));
                p.setAddresse(rs.getString(4));
                p.setDatecreation(rs.getDate(6));
              
        
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }
        public List<demande_experience> RechDemande(String nom) {
        List<demande_experience> myList = new ArrayList<demande_experience>();

        try {
            String requete3 = "SELECT * From demande_experience where nom='"+nom+"'";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                demande_experience p = new demande_experience();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setDescripion(rs.getString(3));
                p.setImage(rs.getString(5));
                p.setAddresse(rs.getString(4));
                p.setDatecreation(rs.getDate(6));
              
        
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }
        public void AddComm( Repondre r){
           try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "INSERT INTO  repondre(message,datecreation,userid,demandeid) VALUES(?,?,?,?)"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            st.setString(1, r.getMessage());
            st.setInt(3, r.getUserid());
            st.setInt(4, r.getDemandeid()); 
            st.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
           

            st.executeUpdate();
            System.out.println("commantaire ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        }
         public String GetUserAvatar(int id) {
        String myList ="";

        try {
            String requete3 = "SELECT * From fosuser where id="+id;
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                myList=rs.getString(16);
              
        
              

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }
         
         public String GetUsername(int id) {
        String myList ="";

        try {
            String requete3 = "SELECT * From fosuser where id="+id;
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                myList=rs.getString(2);
              
        
              

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }
         
             public int GetUserId(int id) {
        int myList =0;

        try {
            String requete3 = "SELECT * From demande_experience where id="+id;
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                myList=rs.getInt(7);
              
        
              

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }
     public List<Repondre> ListComm(int id) {
        List<Repondre> myList = new ArrayList<Repondre>();

        try {
            String requete3 = "SELECT * From repondre where demandeid="+id;
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                Repondre p = new Repondre();
                p.setId(rs.getInt(1));
                p.setUserid(rs.getInt(2));
                p.setDemandeid(rs.getInt(3));
                p.setMessage(rs.getString(4));
                
                p.setDatecreation(rs.getDate(5));
              
        
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }
        public demande_experience GetDemandeById(int id) {
        demande_experience p = new demande_experience();

        try {
            String requete3 = "SELECT * From demande_experience where id= "+id;
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                 
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setDescripion(rs.getString(3));
                p.setImage(rs.getString(5));
                p.setAddresse(rs.getString(4));
                p.setDatecreation(rs.getDate(6));
              
        
             

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return p;

    }
        
        public void ajoutnotif(int userid,int demandeid,String subject)
        {
            notif_demande nt=new notif_demande();
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "INSERT INTO  notif_demande(userid,demandeid,date,subject,demandeur,message,seen) VALUES(?,?,?,?,?,?,?)"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            st.setInt(1, userid);
            st.setInt(2, demandeid);
            st.setInt(5, GetUserId(demandeid)); 
            st.setString(4, subject); 
            st.setString(6, GetUsername(userid)+" a fait un commantaire sur votre demande"); 
            st.setBoolean(7, false); 
            st.setDate(3, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
           

            st.executeUpdate();
            System.out.println("Notification ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        }
}
