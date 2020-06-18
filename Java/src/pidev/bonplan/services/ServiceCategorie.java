/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.services;

import java.sql.Connection;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import pidev.sandy.utils.MyConnection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pidev.sandy.entites.Categoris;

/**
 *
 * @author X
 */
public class ServiceCategorie {

    public void ajouterCategorie(Categoris c) {

        try {
            String requete = "INSERT INTO Categorie(libelle,discription,image,idcategoriemere) VALUES('" + c.getLibelle() + "','" + c.getDiscription() + "','" + c.getImage() + "','" + c.getIdcategoriemere() + "')";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Categorie ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajouterCategorie2(Categoris c) {

        try { // 
            if (c.getIdcategoriemere().getId() != 0) {
                String requete = "INSERT INTO categorie(libelle,discription,image,idcategoriemere) VALUES(?,?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setString(1, c.getLibelle());
                pst.setString(2, c.getDiscription());
                pst.setString(3, c.getImage()); //**************************************************************************************
                pst.setInt(4, c.getIdcategoriemere().getId());
                pst.executeUpdate();
            } else {
                String requete = "INSERT INTO categorie(libelle,discription,image) VALUES(?,?,?)";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setString(1, c.getLibelle());
                pst.setString(2, c.getDiscription());
                pst.setString(3, c.getImage()); //**************************************************************************************
                pst.executeUpdate();
            }

            System.out.println("Categorie ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<Categoris> souslist(int id) {
        List<Categoris> myList = new ArrayList<Categoris>();

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "SELECT * from Categorie where idcategoriemere=" + id; //MAJUSCULE NON OBLIGATOIRE 
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Categoris p2 = new Categoris();

                p2.setId(rs.getInt(1));// soit numero , soit nom de la colonne , comme id => numero (index ) =1

                Categoris c = new Categoris(rs.getInt(2));

                String requete2 = "SELECT * from Categorie WHERE id=? ";
                PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2); // import java.sql.Statement
                pst2.setInt(1, rs.getInt(2));
                ResultSet rs2 = pst2.executeQuery();
                while (rs2.next()) {
                    c.setLibelle(rs2.getString("libelle"));
                }

                //c.setLibelle("jj");
                String categ = c.getLibelle();
                p2.setIdcategoriemere(categ);
                p2.setIdcategoriemere(c);

                p2.setLibelle(rs.getString(3));
                p2.setDiscription(rs.getString(4));
//                ImageView imagev = new ImageView(new Image("file:C:\\wamp\\www\\TunisiaBonPlan2\\web\\uploads\\images\\" + rs.getString(5)));
//                imagev.setFitHeight(100);
//                imagev.setFitWidth(100);
                p2.setImage("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\"+ rs.getString(5));

                //p2.setImage(imagev);
                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    //////////////////////
    public void modifierCategorie(Categoris c) {
        System.out.println(c);
        try { // 
            if (c.getIdcategoriemere().getId() != 0) {

                String requete = "UPDATE categorie set libelle=?,discription=?,image=?,idcategoriemere=? WHERE id=?";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setString(1, c.getLibelle());
                pst.setString(2, c.getDiscription());
                pst.setString(3, c.getImage()); //**************************************************************************************
                pst.setInt(4, c.getIdcategoriemere().getId());
                pst.setInt(5, c.getId());
                pst.executeUpdate();
                System.out.println("oumori1");
                System.out.println(c.getLibelle() + " " + c.getDiscription() + " " + c.getImage() + " " + c.getIdcategoriemere().getId() + " " + c.getId());
            } else {
                String requete = "UPDATE categorie set libelle=?,discription=?,image=? WHERE id=?";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setString(1, c.getLibelle());
                pst.setString(2, c.getDiscription());
                pst.setString(3, c.getImage()); //**************************************************************************************

                pst.setInt(4, c.getId());
                pst.executeUpdate();
                System.out.println("oumori2");
            }

            System.out.println("Categorie modifiée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifierCategorie(Categoris c, int id) {

        try {
            String requete = "UPDATE categorie set libelle=?,discription=?,image=?,idcategoriemere=? WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, c.getLibelle());
            pst.setString(2, c.getDiscription());
//            pst.setString(3, c.getImage());//******************************************* hethom bich inrigilhom aprees hani markithom
            pst.setInt(4, c.getIdcategoriemere().getId());
            pst.setInt(5, id);
            pst.executeUpdate();

            System.out.println("Categorie Modifiée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public static List<Categoris> listerCategorie() {
        List<Categoris> myList = new ArrayList<Categoris>();

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "SELECT * from Categorie "; //MAJUSCULE NON OBLIGATOIRE 
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Categoris p2 = new Categoris();

                p2.setId(rs.getInt(1));// soit numero , soit nom de la colonne , comme id => numero (index ) =1

                Categoris c = new Categoris(rs.getInt(2));

                String requete2 = "SELECT * from Categorie WHERE id=? ";
                PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2); // import java.sql.Statement
                pst2.setInt(1, rs.getInt(2));
                ResultSet rs2 = pst2.executeQuery();
                while (rs2.next()) {
                    c.setLibelle(rs2.getString("libelle"));
                }

                //c.setLibelle("jj");
                String categ = c.getLibelle();
                p2.setIdcategoriemere(categ);
                p2.setIdcategoriemere(c);

                p2.setLibelle(rs.getString(3));
                p2.setDiscription(rs.getString(4));
//                ImageView imagev = new ImageView(new Image("file:C:\\wamp\\www\\TunisiaBonPlan2\\web\\uploads\\images\\" + rs.getString(5)));
//                imagev.setFitHeight(100);
//                imagev.setFitWidth(100);
                p2.setImage("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\"+ rs.getString(5));
  //p2.setImage(imagev);
                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }
//public void supprimercategorie(Categorie s)
//{
// try {
//        
//      
//         String req="DELETE FROM `categorie` WHERE id=?";
//        PreparedStatement ste;
//        ste =  MyConnection.getInstance().getCnx().prepareStatement(req);
//        ste.setInt(1, s.getId());
//        
//         ste.executeUpdate();
//         
//     } catch (SQLException ex) {
//         Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
//     }
//}

    public void supprimercategorie(Integer r) {
        try {
            String requete = "delete from `categorie` where id=?";
            PreparedStatement ps;
            ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, r);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

//
//    public void supprimercategorie(String libelle) {
//        String req = "delete from Categorie where libelle=?";
//        PreparedStatement preparedStatement;
//        try {
//            preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
//            preparedStatement.setString(1, libelle);
//            preparedStatement.executeUpdate();
//            System.out.println("Categorie supprimée");
//        } catch (SQLException ex) {
//            System.out.println("ERREUR");
//            System.out.println(ex);
//        }
//    }
//    public Categorie consulterCategorieID(int i) {
//        String sql = "SELECT * FROM `categorie` ";
//        PreparedStatement ps;
//        try {
//
//            ps = MyConnection.getInstance().getCnx().prepareStatement(sql);
//
//            ResultSet result = ps.executeQuery(sql);
//
//            while (result.next()) {
//                int id = result.getInt("id");
//                String libelle = result.getString("libelle");
//                String image = result.getString("image");
//                String description = result.getString("discription");
//                Categorie c = new Categorie();
//                String categ = c.getLibelle();
//                Categorie G = new Categorie(id, categ, libelle, description, image);
//
//                return G;
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Categorie.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public void modifierCategorie(Categorie c) {
//
//        String sql = "UPDATE categorie set libelle=?,discription=?,image=?,idcategoriemere=? WHERE id=? ";
//
//        PreparedStatement statement;
//        try {
//            statement = MyConnection.getInstance().getCnx().prepareStatement(sql);
//
//            statement.setString(1, c.getLibelle());
//            statement.setString(2, c.getDiscription());
////            pst.setString(3, c.getImage());//******************************************* hethom bich inrigilhom aprees hani markithom
//            statement.setInt(4, c.getIdcategoriemere().getId());
//
//            statement.setInt(5, c.getId());
////       
//            System.out.println(sql);
//            int rowsUpdated = statement.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println("Services..()");
//            Logger.getLogger(Categorie.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//    }
    public Categoris ChercherCategorie(String libelle) {
        Categoris c = new Categoris();

        try {
            String requete = "SELECT * from Categorie WHERE libelle=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, libelle);
            pst.executeQuery();

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                System.out.println("rs=" + rs);
                c.setId(rs.getInt("id"));
                c.setLibelle(rs.getString("libelle"));
                c.setDiscription(rs.getString("discription"));
                c.setImage(rs.getString("image"));
                Categoris b = new Categoris(rs.getInt("idcategoriemere"));
                c.setIdcategoriemere(b);
            }

            System.out.println("Categorie Modifiée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return c;
    }

    public Categoris ChercherCategorie(int id) {
        Categoris c = new Categoris();

        try {
            String requete = "SELECT * from Categorie WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeQuery();

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                System.out.println("rs=" + rs);
                c.setId(rs.getInt("id"));
                c.setLibelle(rs.getString("libelle"));
                c.setDiscription(rs.getString("discription"));
                c.setImage(rs.getString("image"));
                Categoris b = new Categoris(rs.getInt("idcategoriemere"));
                c.setIdcategoriemere(b);
            }

            System.out.println("Categorie Modifiée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return c;
    }

    public List<Categoris> recherchelibelleCategorie(String filtre) {
        List<Categoris> myList = new ArrayList<Categoris>();

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "SELECT * from Categorie where `libelle` like'%" + filtre + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Categoris p2 = new Categoris();

                p2.setId(rs.getInt(1));// soit numero , soit nom de la colonne , comme id => numero (index ) =1

                Categoris c = new Categoris(rs.getInt(2));

                String requete2 = "SELECT * from Categorie WHERE id=? ";
                PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2); // import java.sql.Statement
                pst2.setInt(1, rs.getInt(2));
                ResultSet rs2 = pst2.executeQuery();
                while (rs2.next()) {
                    c.setLibelle(rs2.getString("libelle"));
                }

                //c.setLibelle("jj");
                String categ = c.getLibelle();
                p2.setIdcategoriemere(categ);
                p2.setIdcategoriemere(c);

                p2.setLibelle(rs.getString(3));
                p2.setDiscription(rs.getString(4));
//                ImageView imagev = new ImageView(new Image("file:C:\\wamp\\www\\TunisiaBonPlan2\\web\\uploads\\images\\" + rs.getString(5)));
//                imagev.setFitHeight(100);
//                imagev.setFitWidth(100);
                p2.setImage("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + rs.getString(5));
 
                //p2.setImage(imagev);
                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public static List<Categoris> list() {
        List<Categoris> myList = new ArrayList<Categoris>();

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "SELECT * from Categorie where idcategoriemere is null"; //MAJUSCULE NON OBLIGATOIRE 
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Categoris p2 = new Categoris();

                p2.setId(rs.getInt(1));// soit numero , soit nom de la colonne , comme id => numero (index ) =1

                Categoris c = new Categoris(rs.getInt(2));

                String requete2 = "SELECT * from Categorie WHERE id=? ";
                PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2); // import java.sql.Statement
                pst2.setInt(1, rs.getInt(2));
                ResultSet rs2 = pst2.executeQuery();
                while (rs2.next()) {
                    c.setLibelle(rs2.getString("libelle"));
                }

                //c.setLibelle("jj");
                String categ = c.getLibelle();
                p2.setIdcategoriemere(categ);
                p2.setIdcategoriemere(c);

                p2.setLibelle(rs.getString(3));
                p2.setDiscription(rs.getString(4));
//                ImageView imagev = new ImageView(new Image("file:C:\\wamp\\www\\TunisiaBonPlan2\\web\\uploads\\images\\" + rs.getString(5)));
//                imagev.setFitHeight(100);
//                imagev.setFitWidth(100);
                p2.setImage("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + rs.getString(5));

                //p2.setImage(imagev);
                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public List<Categoris> recherchelibelledesciptionCategorie(String filtre, String description) {
        List<Categoris> myList = new ArrayList<Categoris>();
        String requete;
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            if (!filtre.equals("") ) {
                requete = "SELECT * from Categorie where `libelle` like'%" + filtre + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else {
                requete = "SELECT * from Categorie where `discription` like'%" + description + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            }
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Categoris p2 = new Categoris();

                p2.setId(rs.getInt(1));// soit numero , soit nom de la colonne , comme id => numero (index ) =1

                Categoris c = new Categoris(rs.getInt(2));

                String requete2 = "SELECT * from Categorie WHERE id=? ";
                PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2); // import java.sql.Statement
                pst2.setInt(1, rs.getInt(2));
                ResultSet rs2 = pst2.executeQuery();
                while (rs2.next()) {
                    c.setLibelle(rs2.getString("libelle"));
                }

                //c.setLibelle("jj");
                String categ = c.getLibelle();
                p2.setIdcategoriemere(categ);
                p2.setIdcategoriemere(c);

                p2.setLibelle(rs.getString(3));
                p2.setDiscription(rs.getString(4));
//                ImageView imagev = new ImageView(new Image("file:C:\\wamp\\www\\TunisiaBonPlan2\\web\\uploads\\images\\" + rs.getString(5)));
//                imagev.setFitHeight(100);
//                imagev.setFitWidth(100);
                p2.setImage("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\"+ rs.getString(5));

                //p2.setImage(imagev);
                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }
}
