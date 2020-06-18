/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.services;

import java.sql.Date;
import pidev.sandy.entites.BonplanAdnene;
import pidev.sandy.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import pidev.sandy.entites.Reclamation;

/**
 *
 * @author Alaa
 */
public class ServiceBonplanAdnene {

    public void ajouterBonPlan(BonplanAdnene b) {

        try {
            String requete = "INSERT INTO bon_plan(status,dislike,longitude,latitude,ref_compte,refcategorie,num_tel_local,libelle ,desciption,image,couverture,addresse,email,overture,fermeture)"
                    + " VALUES('" + b.getStatus() + "','" + b.getDislike() + "','" + b.getLongitude() + "','" + b.getLatitude() + "','" + b.getRef_compte() + "','" + b.getNum_tel_local() + "','" + b.getLibelle() + "','" + b.getDesciption() + "','" + b.getImage() + "','" + b.getCouverture() + "','" + b.getAddresse() + "','" + b.getEmail() + "','" + b.getOverture() + "','" + b.getFermeture() + "')";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Bonplan ajouté");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajouterBonPlan2(BonplanAdnene b) {

        try { // 
            String requete = "INSERT INTO bon_plan(status,dislike,longitude,latitude,ref_compte,refcategorie,num_tel_local,libelle ,desciption,image,couverture,addresse,email,overture,fermeture)(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, b.getStatus());
            pst.setInt(2, b.getDislike());
            pst.setInt(3, b.getLongitude());
            pst.setInt(4, b.getLatitude());
            pst.setInt(5, b.getRef_compte());
            pst.setInt(6, b.getRefcategorie());
            pst.setInt(7, b.getNum_tel_local());
            pst.setString(8, b.getLibelle());
            pst.setString(9, b.getDesciption());
            pst.setString(10, b.getImage());
            pst.setString(11, b.getCouverture());
            pst.setString(12, b.getAddresse());
            pst.setString(13, b.getAddresse());
            pst.setTime(14, b.getOverture());
            pst.setTime(15, b.getFermeture());

            pst.executeUpdate();

            System.out.println("Bon plan  ajouté");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifierBonplan(BonplanAdnene b, int id) {

        try {
            String requete = "UPDATE categorie set status=? ,dislike =?,longitude =?,latitude =?,ref_compte=? ,refcategorie =? ,num_tel_local=? , libelle =? ,desciption =?,image=?,couverture=?,addresse=?,email =? overture=?,fermeture =? WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
              pst.setInt(1, b.getStatus());
            pst.setInt(2, b.getDislike());
            pst.setInt(3, b.getLongitude());
            pst.setInt(4, b.getLatitude());
            pst.setInt(5, b.getRef_compte());
            pst.setInt(6, b.getRefcategorie());
            pst.setInt(7, b.getNum_tel_local());
            pst.setString(8, b.getLibelle());
            pst.setString(9, b.getDesciption());
            pst.setString(10, b.getImage());
            pst.setString(11, b.getCouverture());
            pst.setString(12, b.getAddresse());
            pst.setString(13, b.getAddresse());
            pst.setTime(14, b.getOverture());
            pst.setTime(15, b.getFermeture());
            pst.setInt(16, id);
            pst.executeUpdate();

            System.out.println("Bon plan  Modifié");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public static List<BonplanAdnene> listerBonplan() {
        List<BonplanAdnene> myList = new ArrayList<BonplanAdnene>();

        try {
            String requete = "SELECT * from bon_plan"; 
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                BonplanAdnene p2 = new BonplanAdnene();
                p2.setId(rs.getInt(1));
                p2.setLibelle(rs.getString(5));
                p2.setStatus(rs.getInt(6));
                p2.setDesciption(rs.getString(7));
                p2.setImage(rs.getString(8));
                p2.setCouverture(rs.getString(20));
                p2.setAddresse(rs.getString(9));
                p2.setOverture(rs.getTime(11));
                p2.setFermeture(rs.getTime(12));
                p2.setNum_tel_local(rs.getInt(13));
                p2.setEmail(rs.getString(14));
                p2.setRefcategorie(rs.getInt(3));
                p2.setRef_compte(rs.getInt(2));
                p2.setDislike(rs.getInt(16));
                p2.setLongitude(rs.getInt(18));
                p2.setLatitude(rs.getInt(17));
               
                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }
    
    
    public BonplanAdnene rechercheBonPlanById(int id) {
        String requete = null;
         BonplanAdnene p2=null;
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            requete = "SELECT * from bon_plan where id=" + id; //MAJUSCULE NON OBLIGATOIRE 
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                p2 = new BonplanAdnene();
                p2.setId(rs.getInt(1));
                p2.setLibelle(rs.getString(5));
                p2.setStatus(rs.getInt(6));
                p2.setDesciption(rs.getString(7));
                p2.setImage(rs.getString(8));
                p2.setCouverture(rs.getString(20));
                p2.setAddresse(rs.getString(9));
                p2.setOverture(rs.getTime(11));
                p2.setFermeture(rs.getTime(12));
                p2.setNum_tel_local(rs.getInt(13));
                p2.setEmail(rs.getString(14));
                p2.setRefcategorie(rs.getInt(3));
                p2.setRef_compte(rs.getInt(2));
                p2.setDislike(rs.getInt(16));
                p2.setLongitude(rs.getInt(18));
                p2.setLatitude(rs.getInt(17));
            }
            return p2;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return p2;

    }

}
