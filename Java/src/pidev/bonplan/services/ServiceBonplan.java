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
import java.util.List;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import pidev.sandy.utils.MyConnection;
import pidev.sandy.entites.BonPlan;
import pidev.sandy.entites.Categoris;
import pidev.sandy.entites.User;
import pidev.sandy.entites.aimer;
import pidev.sandy.entites.avisbonplan;

/**
 *
 * @author Alaa
 */
public class ServiceBonplan {

    public void ajouterBonPlan(BonPlan b) {

        try {
            String requete = "INSERT INTO bon_plan(status,dislike,longitude,latitude,ref_compte,refcategorie,num_tel_local,libelle ,desciption,image,couverture,addresse,email,overture,fermeture)"
                    + " VALUES('" + b.getStatus() + "','" + b.getDislike() + "','" + b.getLongitude() + "','" + b.getLatitude() + "','" + b.getRef_compte() + "','" + b.getNum_tel_local() + "','" + b.getLibelle() + "','" + b.getDescription() + "','" + b.getImage() + "','" + b.getCouverture() + "','" + b.getAddresse() + "','" + b.getEmail() + "','" + b.getOverture() + "','" + b.getFermeture() + "')";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Bonplan ajouté");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajouter2(BonPlan c) {

        try { // 
            String requete = "INSERT INTO bon_plan(longitude,latitude,refcategorie,num_tel_local,libelle ,description,image,couverture,addresse,email,overture,fermeture,status,ref_compte) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            String requete = "INSERT INTO bon_plan(longitude,latitude,refcategorie,num_tel_local,libelle ,desciption,image,couverture,addresse,email,overture,fermeture)(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
//            pst.setInt(1, 10);
//            pst.setInt(2, 10);
            pst.setDouble(1, c.getLongitude());
            pst.setDouble(2, c.getLatitude());

            pst.setInt(3, c.getRefcategorie().getId());
            pst.setInt(4, c.getNum_tel_local()); //**************************************************************************************
//            pst.setInt(4, 202020); //**************************************************************************************
            pst.setString(5, c.getLibelle());
            pst.setString(6, c.getDescription());
            pst.setString(7, c.getImage());
            pst.setString(8, c.getCouverture());
            pst.setString(9, c.getAddresse());
            pst.setString(10, c.getEmail());
            pst.setObject(11, c.getOverture());
            pst.setObject(12, c.getFermeture());
            pst.setInt(13, 1);

            User loggedUser = pidev.sandy.controller.LoginController.getInstance().getLoggedUser();
            pst.setInt(14, loggedUser.getId());

            pst.executeUpdate();

            System.out.println("BonPlan ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

//    public void ajouterBonPlan2(BonPlan b) {
//
//        try { // 
//            String requete = "INSERT INTO bon_plan(status,dislike,longitude,latitude,ref_compte,refcategorie,num_tel_local,libelle ,desciption,image,couverture,addresse,email,overture,fermeture)(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
//            pst.setInt(1, b.getStatus());
//            pst.setInt(2, b.getDislike());
//            pst.setDouble(3, b.getLongitude());
//            pst.setDouble(4, b.getLatitude());
//            pst.setInt(5, b.getRef_compte().getId());
//            pst.setInt(6, b.getRefcategorie().getId());
//            pst.setInt(7, b.getNum_tel_local());
//            pst.setString(8, b.getLibelle());
//            pst.setString(9, b.getDescription());
//            pst.setString(10, b.getImage());
//            pst.setString(11, b.getCouverture());
//            pst.setString(12, b.getAddresse());
//            pst.setString(13, b.getAddresse());
//            pst.setTime(14, b.getOverture());
//            pst.setTime(15, b.getFermeture());
//
//            pst.executeUpdate();
//
//            System.out.println("Bon plan  ajouté");
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//
//    }
//
//    public void ajouter2(BonPlan c) {
//
//        try { // 
//            String requete = "INSERT INTO bon_plan(longitude,latitude,refcategorie,num_tel_local,libelle ,description,image,couverture,addresse,email,overture,fermeture,status,ref_compte) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
////            String requete = "INSERT INTO bon_plan(longitude,latitude,refcategorie,num_tel_local,libelle ,desciption,image,couverture,addresse,email,overture,fermeture)(?,?,?,?,?,?,?,?,?,?,?,?)";
//            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
////            pst.setInt(1, 10);
////            pst.setInt(2, 10);
//            pst.setDouble(1, c.getLongitude());
//            pst.setDouble(2, c.getLatitude());
//
//            pst.setInt(3, c.getRefcategorie().getId());
//            pst.setInt(4, c.getNum_tel_local()); //**************************************************************************************
////            pst.setInt(4, 202020); //**************************************************************************************
//            pst.setString(5, c.getLibelle());
//            pst.setString(6, c.getDescription());
//            pst.setString(7, c.getImage());
//            pst.setString(8, c.getCouverture());
//            pst.setString(9, c.getAddresse());
//            pst.setString(10, c.getEmail());
//            pst.setObject(11, c.getOverture());
//            pst.setObject(12, c.getFermeture());
//            pst.setInt(13, 1);
//             User loggedUser = LoginController.getInstance().getLoggedUser();
//             pst.setInt(14, loggedUser.getId());
//             
//             
//
//            pst.executeUpdate();
//
//            System.out.println("BonPlan ajoutée");
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//
//    }
////    public void modifierBonplan(BonPlan b, int id) {
////
////        try {
////            String requete = "UPDATE bonp set status=? ,dislike =?,longitude =?,latitude =?,ref_compte=? ,refcategorie =? ,num_tel_local=? , libelle =? ,desciption =?,image=?,couverture=?,addresse=?,email =? overture=?,fermeture =? WHERE id=?";
////            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
////            pst.setInt(1, b.getStatus());
////            pst.setInt(2, b.getDislike());
////            pst.setDouble(3, b.getLongitude());
////            pst.setDouble(4, b.getLatitude());
////            pst.setInt(5, b.getRef_compte().getId());
////            pst.setInt(6, b.getRefcategorie().getId());
////            pst.setInt(7, b.getNum_tel_local());
////            pst.setString(8, b.getLibelle());
////            pst.setString(9, b.getDescription());
////            pst.setString(10, b.getImage());
////            pst.setString(11, b.getCouverture());
////            pst.setString(12, b.getAddresse());
////            pst.setString(13, b.getAddresse());
////            pst.setTime(14, b.getOverture());
////            pst.setTime(15, b.getFermeture());
////            pst.setInt(16, id);
////            pst.executeUpdate();
////
////            System.out.println("Bon plan  Modifié");
////
////        } catch (SQLException ex) {
////            System.err.println(ex.getMessage());
////        }
////
////    }
//    public static List<BonPlan> listerBonplan() {
//        List<BonPlan> myList = new ArrayList<BonPlan>();
//
//        try {
//            String requete = "SELECT * from bon_plan";
//            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
//            ResultSet rs = pst.executeQuery(requete);
//            while (rs.next()) {
//                BonPlan p2 = new BonPlan();
//                p2.setId(rs.getInt(1));
//                p2.setLibelle(rs.getString(2));
//                p2.setStatus(rs.getInt(3));
//                p2.setDescription(rs.getString(4));
//                p2.setImage(rs.getString(5));
//                p2.setCouverture(rs.getString(6));
//                p2.setAddresse(rs.getString(7));
//                p2.setOverture(rs.getTime(8));
//                p2.setFermeture(rs.getTime(9));
//                p2.setNum_tel_local(rs.getInt(10));
//                p2.setEmail(rs.getString(11));
//                Categorie c = new Categorie(rs.getInt(12));
//                p2.setRefcategorie(c);
//                User u = new User(rs.getInt(13));
//                p2.setRef_compte(u);
//
//                p2.setDislike(rs.getInt(14));
//                p2.setLongitude(rs.getInt(15));
//                p2.setLatitude(rs.getInt(16));
//
//                myList.add(p2);
//
//            }
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        return myList;
//
//    }
    //hedhi shiha
    public List<BonPlan> listerBonPlan() {
        List<BonPlan> myList = new ArrayList<BonPlan>();

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "SELECT * from bon_plan"; //MAJUSCULE NON OBLIGATOIRE 
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                BonPlan p2 = new BonPlan();
                p2.setId(rs.getInt(1));
                p2.setLibelle(rs.getString(2));
                p2.setStatus(rs.getInt(3));
                p2.setDescription(rs.getString(4));
                p2.setImage("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + rs.getString(5));
                p2.setCouverture("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + rs.getString(6));
                p2.setAddresse(rs.getString(7));
                p2.setOverture(rs.getTime(8));
                p2.setFermeture(rs.getTime(9));
                p2.setNum_tel_local(rs.getInt(10));
                p2.setEmail(rs.getString(11));
                Categoris c = new ServiceCategorie().ChercherCategorie(rs.getInt(12));
                p2.setRefcategorie(c);
//                User u = new User(rs.getInt(13));
//                p2.setRef_compte(u);
                p2.setDislike(rs.getInt(14));
                p2.setLongitude(rs.getInt(15));
                p2.setLatitude(rs.getInt(16));
                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public void modifier2(BonPlan c) {

        try { // 

            String requete = "UPDATE bon_plan set longitude =? ,latitude =?,refcategorie =?,num_tel_local =?,libelle =?,description =?,image =?,couverture =?,addresse =?,email =?,overture =?,fermeture =?,status=? WHERE id=?";
//            String requete = "INSERT INTO bon_plan(longitude,latitude,refcategorie,num_tel_local,libelle ,desciption,image,couverture,addresse,email,overture,fermeture)(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
//            pst.setInt(1, 10);
//            pst.setInt(2, 10);
            pst.setDouble(1, c.getLongitude());
            pst.setDouble(2, c.getLatitude());

            pst.setInt(3, c.getRefcategorie().getId());
            pst.setInt(4, c.getNum_tel_local()); //**************************************************************************************
//            pst.setInt(4, 202020); //**************************************************************************************
            pst.setString(5, c.getLibelle());
            pst.setString(6, c.getDescription());
            pst.setString(7, c.getImage());
            pst.setString(8, c.getCouverture());
            pst.setString(9, c.getAddresse());
            pst.setString(10, c.getEmail());
            pst.setObject(11, c.getOverture());
            pst.setObject(12, c.getFermeture());
            pst.setInt(13, 1);
            pst.setInt(14, c.getId());
            pst.executeUpdate();

            System.out.println("BonPlan modifie");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<BonPlan> Touslesbonsplans(int id) {
        List<BonPlan> myList = new ArrayList<BonPlan>();

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "SELECT * from bon_plan where refcategorie=" + id; //MAJUSCULE NON OBLIGATOIRE 
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                BonPlan p2 = new BonPlan();
                p2.setId(rs.getInt(1));
                p2.setLibelle(rs.getString(2));
                p2.setStatus(rs.getInt(3));
                p2.setDescription(rs.getString(4));
                p2.setImage("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + rs.getString(5));
                p2.setCouverture("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + rs.getString(6));
                p2.setAddresse(rs.getString(7));
                p2.setOverture(rs.getTime(8));
                p2.setFermeture(rs.getTime(9));
                p2.setNum_tel_local(rs.getInt(10));
                p2.setEmail(rs.getString(11));
                Categoris c = new ServiceCategorie().ChercherCategorie(rs.getInt(12));
                p2.setRefcategorie(c);
//                User u = new User(rs.getInt(13));
//                p2.setRef_compte(u);
                p2.setDislike(rs.getInt(14));
                p2.setLongitude(rs.getInt(15));
                p2.setLatitude(rs.getInt(16));
                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public void supprimerbonplan(Integer r) {
        try {
            String requete = "delete from `bon_plan` where id=?";
            PreparedStatement ps;
            ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, r);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void rating(int rat, int bn) {

        try { // 
            String requete = "INSERT INTO avisbonplan(rating) VALUES(?) where bn=" + bn;
//            String requete = "INSERT INTO bon_plan(longitude,latitude,refcategorie,num_tel_local,libelle ,desciption,image,couverture,addresse,email,overture,fermeture)(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, rat);

            pst.executeUpdate();

            System.out.println("BonPlan ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajoutrating(avisbonplan a) {

        try { // 
            String requete = "INSERT INTO avisbonplan(refavis,titre,commentaire,rating,bn) VALUES(?,?,?,?,?) ";
//            String requete = "INSERT INTO bon_plan(longitude,latitude,refcategorie,num_tel_local,libelle ,desciption,image,couverture,addresse,email,overture,fermeture)(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, a.getRefavis().getId());
            pst.setString(2, a.getTitre());
            pst.setString(3, a.getCommentaire());
            pst.setDouble(4, a.getRating());
            pst.setInt(5, a.getBn().getId());

            pst.executeUpdate();

            System.out.println("Rating ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<avisbonplan> ListeAvis(int id) {
        List<avisbonplan> myList = new ArrayList<avisbonplan>();

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "SELECT * from avisbonplan where bn=" + id; //MAJUSCULE NON OBLIGATOIRE 
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                avisbonplan p2 = new avisbonplan();
                p2.setId(rs.getInt(1));
                User u = new User(rs.getInt(2));
                p2.setRefavis(u);

//        String nom = c.getNom();
//                p2.setRefavis(nom);
//                p2.setRefavis(c);
//               User c = new User(rs.getInt(2));
//
//                String requete2 = "SELECT * from avisbonplan WHERE id=? ";
//                PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2); // import java.sql.Statement
//                pst2.setInt(1, rs.getInt(2));
//                ResultSet rs2 = pst2.executeQuery();
//                while (rs2.next()) {
//                    c.setNom(rs2.getString("nom"));
//                }
//
//                String nom = c.getNom();
//                p2.setRefavis(nom);
//                p2.setRefavis(c);
                p2.setTitre(rs.getString(3));
                p2.setCommentaire(rs.getString(4));
                p2.setRating(rs.getDouble(5));

//                  BonPlan b = new BonPlan(rs.getInt(6));
//       p2.setBn(b);
//      
//                
                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public void supprimeravis(Integer r) {
        User loggedUser = pidev.sandy.controller.LoginController.getInstance().getLoggedUser();
        if (loggedUser.getId() == r) {
            try {

                String requete = "delete from `avisbonplan` where id=?";
                PreparedStatement ps;
                ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
                ps.setInt(1, r);
                ps.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Ce n'est pas votre commentaire")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
        }

    }

    public void accepterbonplan(BonPlan c) {
        try { // 

            String requete = "UPDATE bon_plan set status =? WHERE id=?";
//            String requete = "INSERT INTO bon_plan(longitude,latitude,refcategorie,num_tel_local,libelle ,desciption,image,couverture,addresse,email,overture,fermeture)(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
//            pst.setInt(1, 10);
//            pst.setInt(2, 10);

            pst.setInt(1, 2);
            pst.setInt(2, c.getId());
            pst.executeUpdate();

            System.out.println("BonPlan modifie");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void refuserbonplan(BonPlan c) {

        try { // 

            String requete = "UPDATE bon_plan set status =? WHERE id=?";
//            String requete = "INSERT INTO bon_plan(longitude,latitude,refcategorie,num_tel_local,libelle ,desciption,image,couverture,addresse,email,overture,fermeture)(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
//            pst.setInt(1, 10);
//            pst.setInt(2, 10);

            pst.setInt(1, 3);
            pst.setInt(2, c.getId());
            pst.executeUpdate();

            System.out.println("BonPlan modifie");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

//    public ObservableList<BonPlan> searchProduct(String motcle) {
//        ObservableList<BonPlan> myList = FXCollections.observableArrayList();
//        try {
//            String requete = "select * from bon_plan where libelle  like '%" + motcle + "%' or "
//                    + "description like '%" + motcle + "%'";
//            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
//            ResultSet rs = pst.executeQuery(requete);
//            while (rs.next()) {
//                BonPlan p2 = new BonPlan();
//                p2.setId(rs.getInt(1));
//                p2.setLibelle(rs.getString(2));
//                p2.setStatus(rs.getInt(3));
//                p2.setDescription(rs.getString(4));
//                p2.setImage("file:C:\\wamp\\www\\TunisiaBonPlan2\\web\\uploads\\images\\" + rs.getString(5));
//                p2.setCouverture("file:C:\\wamp\\www\\TunisiaBonPlan2\\web\\uploads\\images\\" + rs.getString(6));
//                p2.setAddresse(rs.getString(7));
//                p2.setOverture(rs.getTime(8));
//                p2.setFermeture(rs.getTime(9));
//                p2.setNum_tel_local(rs.getInt(10));
//                p2.setEmail(rs.getString(11));
//                Categoris c = new ServiceCategorie().ChercherCategorie(rs.getInt(12));
//                p2.setRefcategorie(c);
////                User u = new User(rs.getInt(13));
////                p2.setRef_compte(u);
//                p2.setDislike(rs.getInt(14));
//                p2.setLongitude(rs.getInt(15));
//                p2.setLatitude(rs.getInt(16));
//                myList.add(p2);
//
//            }
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        return myList;
//    }
    public List<BonPlan> recherchebonplan(int id, String filtre, String filtree) {
        List<BonPlan> myList = new ArrayList<BonPlan>();
        String requete;
        try {

            if (!filtre.equals("")) {
                requete = "select * from bon_plan where libelle  like '%" + filtre + "%'  and "
                        + "refcategorie=" + id + "";
            } else {
                requete = "select * from bon_plan where addresse  like '%" + filtree + "%'  and "
                        + "refcategorie=" + id + "";
            }

            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                BonPlan p2 = new BonPlan();
                p2.setId(rs.getInt(1));
                p2.setLibelle(rs.getString(2));
                p2.setStatus(rs.getInt(3));
                p2.setDescription(rs.getString(4));
                p2.setImage("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + rs.getString(5));
                p2.setCouverture("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + rs.getString(6));
                p2.setAddresse(rs.getString(7));
                p2.setOverture(rs.getTime(8));
                p2.setFermeture(rs.getTime(9));
                p2.setNum_tel_local(rs.getInt(10));
                p2.setEmail(rs.getString(11));
                Categoris c = new ServiceCategorie().ChercherCategorie(rs.getInt(12));
                p2.setRefcategorie(c);
//                User u = new User(rs.getInt(13));
//                p2.setRef_compte(u);
                p2.setDislike(rs.getInt(14));
                p2.setLongitude(rs.getInt(15));
                p2.setLatitude(rs.getInt(16));
                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public List<aimer> listejaime(int id) {
        List<aimer> myList = new ArrayList<aimer>();

        try { // LES var declaré dans le try aimer sont vue que dans le try, et inversement pour en dhors du try
            String requete = "SELECT * from aimer where idb=" + id; //MAJUSCULE NON OBLIGATOIRE 
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                aimer p2 = new aimer();
                p2.setId(rs.getInt(1));
                p2.setIdb(rs.getInt(2));
//               User u = new User(rs.getString(3));
//              p2.setUsername(u);
                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public int countJaime(int id) {
        int a = 0;
        try { // LES var declaré dans le try aimer sont vue que dans le try, et inversement pour en dhors du try
            String requete = "SELECT count(*) from aimer where idb=" + id; //MAJUSCULE NON OBLIGATOIRE 
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {

                a = rs.getInt(1);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return a;

    }

    public void jaime(aimer a) {
       
            try {
                String requete = "INSERT INTO aimer(idb,uname) VALUES(?,?) ";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setString(2, a.getUsername().getUsername());
                pst.setInt(1, a.getIdb());
                pst.executeUpdate();
                System.out.println("j'aime");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
           
        }
    }

    public List<BonPlan> recherchebonplanespacepartenaire(String type, String valeur, int id_author) {
        List<BonPlan> myList = new ArrayList<BonPlan>();
        String requete = null;
        try { 
            if (type.equals("Nom")) {
                requete = "SELECT * from bon_plan where ref_compte=" + id_author + " and libelle like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Description")) {
                requete = "SELECT * from bon_plan where ref_compte=" + id_author + " and description like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Addresse")) {
                requete = "SELECT * from bon_plan where ref_compte=" + id_author + " and addresse like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Status")) {
                requete = "SELECT * from bon_plan where ref_compte=" + id_author + " and status like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            }

            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                BonPlan p2 = new BonPlan();
                p2.setId(rs.getInt(1));
                p2.setLibelle(rs.getString(2));
                p2.setStatus(rs.getInt(3));
                p2.setDescription(rs.getString(4));
                p2.setImage("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + rs.getString(5));
                p2.setCouverture("file:C:\\wamp64\\www\\SandyPi\\web\\uploads\\images\\" + rs.getString(6));
       
                p2.setAddresse(rs.getString(7));
 
                p2.setOverture(rs.getTime(8));
                p2.setFermeture(rs.getTime(9));
                p2.setNum_tel_local(rs.getInt(10));
                p2.setEmail(rs.getString(11));
                Categoris c = new ServiceCategorie().ChercherCategorie(rs.getInt(12));
                p2.setRefcategorie(c);
//                User u = new User(rs.getInt(13));
//                p2.setRef_compte(u);
                p2.setDislike(rs.getInt(14));
                p2.setLongitude(rs.getInt(15));
                p2.setLatitude(rs.getInt(16));
                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

}
