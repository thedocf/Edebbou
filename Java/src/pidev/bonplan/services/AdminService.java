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
import java.util.List;
import pidev.sandy.entites.Cadeaux;
import pidev.sandy.entites.Categoris;
import pidev.sandy.entites.User;
import pidev.sandy.utils.MyConnection;

/**
 *
 * @author Alaa
 */
public class AdminService {

    public int get_Number_Reclamation() {
        ResultSet rs = null;
        int Message_Number = 989898;
        try {
            String req = "SELECT COUNT(*) FROM `reclamation` ";
            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message_Number = rs.getInt(1);
            }
            return Message_Number;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Message_Number;
    }

    public int get_Number_User() {
        ResultSet rs = null;
        int Message_Number = 989898;
        try {
            String req = "SELECT COUNT(roles) FROM `fosuser` ";
            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message_Number = rs.getInt(1);
            }
            return Message_Number;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Message_Number;
    }

    public int get_Number_Membre() {
        ResultSet rs = null;
        int Message_Number = 989898;
        try {
            String req = "SELECT COUNT(roles) FROM `fosuser` where roles= 'a:1:{i:0;s:11:\"ROLE_MEMBRE\";}'";

            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message_Number = rs.getInt(1);
            }
            return Message_Number;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Message_Number;
    }

    public int get_Number_partenaire() {
        ResultSet rs = null;
        int Message_Number = 989898;
        try {
            String req = "SELECT COUNT(roles) FROM `fosuser` where roles= 'a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}'";

            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message_Number = rs.getInt(1);
            }
            return Message_Number;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Message_Number;
    }

    public int get_Number_Comment() {
        ResultSet rs = null;
        int Number_CommentUser = 0;
        try {
            String req = "SELECT COUNT(*) FROM `comment`";

            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Number_CommentUser = rs.getInt(1);
            }
            return Number_CommentUser;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Number_CommentUser;
    }

    public int get_Number_BonPlan() {
        ResultSet rs = null;
        int Number_BonPlanUser = 0;
        try {
            String req = "SELECT COUNT(*) FROM `bon_plan`";

            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Number_BonPlanUser = rs.getInt(1);
            }
            return Number_BonPlanUser;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Number_BonPlanUser;
    }

    public int get_Number_Cadeaux() {
        ResultSet rs = null;
        int Product_Number = 989898;
        try {
            String req = "SELECT COUNT(*) FROM `cadeaux`";

            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product_Number = rs.getInt(1);
            }
            return Product_Number;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Product_Number;
    }

    public int get_Number_CommentUser(int user, Date DateDebut1, Date DateFin1) {
        ResultSet rs = null;
        int Number_CommentUser = 0;
        try {
            String req = "SELECT COUNT(*) FROM `comment` WHERE `author_id`=? and created_at BETWEEN ? AND ? ";

            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setInt(1, user);
            preparedStatement.setDate(2, DateDebut1);
            preparedStatement.setDate(3, DateFin1);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Number_CommentUser = rs.getInt(1);
            }
            return Number_CommentUser;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Number_CommentUser;
    }

    public int get_Number_BonPlanUser(int user) {
        ResultSet rs = null;
        int Number_BonPlanUser = 0;
        try {
            String req = "SELECT COUNT(*) FROM `bon_plan` WHERE `ref_compte`=?";

            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setInt(1, user);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Number_BonPlanUser = rs.getInt(1);
            }
            return Number_BonPlanUser;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Number_BonPlanUser;
    }

    public int get_Number_OffreExperience(int user, Date DateDebut1, Date DateFin1) {
        ResultSet rs = null;
        int Number_OffreExperience = 0;
        try {
            String req = "SELECT COUNT(*) FROM `offre_experience` WHERE `ref_compte`=? and datecreation BETWEEN ? AND ? ";

            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setInt(1, user);
            preparedStatement.setDate(2, DateDebut1);
            preparedStatement.setDate(3, DateFin1);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Number_OffreExperience = rs.getInt(1);
            }
            return Number_OffreExperience;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Number_OffreExperience;
    }

    /**
     * ***********************Rechercher***********************
     */
    public List<Cadeaux> rechercheAvanceeCadeaux(String type, String valeur) {
        MyServices myServices = new MyServices();
        /**
         * ********************En Cas de Libelle cate**************************
         */

        List<Categoris> categorisLibelle = new ArrayList<Categoris>();
        categorisLibelle = myServices.chercherCategorieBylibelle(valeur);

        for (int i = 0; i < categorisLibelle.size(); i++) {
            System.out.println(i + " ====>" + categorisLibelle.get(i).getId());

        }

        /**
         * ****************************************************
         */
        List<Cadeaux> myListCadeaux = new ArrayList<Cadeaux>();
        String requete = null;
 
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            if (type.equals("Libelle")) {
                requete = "SELECT * from cadeaux where libelle like '%" + valeur + "%'";
            } else if (type.equals("Prix Reel")) {

                 
                
                requete = "SELECT * from cadeaux where prix_reel like '%" + 11 + "%'";
            } else if (type.equals("Quantite")) {
                requete = "SELECT * from cadeaux where quantite_actuel like '%" + valeur + "%'";
            } else if (type.equals("Categoris")) {
                List<String> categorisLibelleString = new ArrayList<String>();
                for (int i = 0; i < categorisLibelle.size(); i++) {
                    requete = "SELECT * from cadeaux c,categorie cat where cat.libelle like '%" + categorisLibelle.get(i).getLibelle() + "%'";
                    

                    categorisLibelleString.add(requete);
                }

            } else if (type.equals("Valeur")) {
                requete = "SELECT * from cadeaux where valeur_point like '%" + valeur + "%'";
            } else if (type.equals("Tout")) {
                requete = "SELECT * from cadeaux where libelle like '%" + valeur + "%' or prix_reel like '%" + valeur + "%' or quantite_actuel like '%" + valeur + "%' or categorisCadeaux like '%" + valeur + "%' or valeur_point like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (valeur.equals("")) {
                requete = "SELECT * from cadeaux ";
            }

            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Cadeaux cadeaux = new Cadeaux();

                cadeaux.setId(rs.getInt(1));
                cadeaux.setLibelle(rs.getString(2));
                cadeaux.setDescription(rs.getString(3));
                cadeaux.setPrix_reel(rs.getFloat(4));
                cadeaux.setValeur_point(rs.getFloat(5));
                cadeaux.setQuantite_initial(rs.getInt(6));
                cadeaux.setQuantite_actuel(rs.getInt(7));

                cadeaux.setImage(rs.getString(8));
                Categoris categoris = new Categoris();

                categoris.setId(rs.getInt(9));
                categoris = myServices.chercherCategorieById(categoris);

                cadeaux.setCategorisCadeaux(categoris);

                myListCadeaux.add(cadeaux);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myListCadeaux;

    }

}
