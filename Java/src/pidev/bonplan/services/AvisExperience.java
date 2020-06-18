/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import pidev.sandy.entites.avis;
import pidev.sandy.utils.MyConnection;

/**
 *
 * @author SLIMEN
 */
public class AvisExperience {
     public void ajouterAvis(avis p) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "INSERT INTO avis(nom,url_image,datecreation,rating,imageuser,ref_experiencce) VALUES(?,?,?,?,?,?)"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            st.setString(1, p.getNom());
         
            st.setString(2, p.getUrl_image());
           st.setInt(6, p.getRef_experiencce());
            st.setDate(3, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            st.setInt(4, p.getRating());
            st.setString(5, p.getImageuser());
           

            st.executeUpdate();
            System.out.println("experience ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifierAvis(avis p, int id) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "UPDATE  avis set nom=?,url_image=?,datecreation=?,rating=?,imageuser=? where id=?"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
              st.setString(1, p.getNom());
         
            st.setString(2, p.getUrl_image());
           
            st.setDate(3, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            st.setInt(4, p.getRating());
            st.setString(5, p.getImageuser());
            st.setInt(6, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void SupprimerAvis(int id) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "DELETE * FROM avis where id=?"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<avis> listerAvis() {
        List<avis> myList = new ArrayList<avis>();

        try {
            String requete3 = "SELECT * From avis";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                avis p = new avis();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(4));
                p.setUrl_image(rs.getString(5));
                p.setImageuser(rs.getString(8));
                p.setDatecreation(rs.getDate(7));
                p.setRating(rs.getInt(6));
           
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }
       public List<avis> listerAvisParId(int id) {
        List<avis> myList = new ArrayList<avis>();

        try {
            String requete3 = "SELECT * From avis where ref_experiencce="+id;
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                avis p = new avis();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(4));
                p.setUrl_image(rs.getString(5));
                p.setImageuser(rs.getString(8));
                p.setDatecreation(rs.getDate(7));
                p.setRating(rs.getInt(6));
           
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }
}
