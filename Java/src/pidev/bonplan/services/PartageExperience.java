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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.sandy.entites.User;
import pidev.sandy.entites.notif_demande;
import pidev.sandy.entites.offre_experience;

import pidev.sandy.utils.MyConnection;

/**
 *
 * @author SLIMEN
 */
public class PartageExperience {

    private String reqmix;

    public void ajouterExperience(offre_experience p) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "INSERT INTO offre_experience(nom,description,url_image,addrese,datecreation,rating,climatisation,wifi,snackbar,parking,piscine,familiale,paiementparcarte,balcon,visites,fumer,reservations,region_id,catid) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            st.setString(1, p.getNom());
            st.setString(2, p.getDescription());
            st.setString(3, p.getUrl_image());
            st.setString(4, p.getAddrese());
            st.setDate(5, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            st.setInt(6, p.getRating());
            st.setBoolean(7, p.getClimatisation());
            st.setBoolean(8, p.getWifi());
            st.setBoolean(9, p.getSnackbar());
            st.setBoolean(10, p.getParking());
            st.setBoolean(11, p.getPiscine());
            st.setBoolean(12, p.getFamiliale());
            st.setBoolean(13, p.getPaiementparcarte());
            st.setBoolean(14, p.getBalcon());
            st.setBoolean(15, p.getVisites());
            st.setBoolean(16, p.getFumer());
            st.setBoolean(17, p.getReservations());
            st.setInt(18, p.getRegion_id());
            st.setInt(19, p.getCatid());

            st.executeUpdate();
            System.out.println("experience ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifierExperience(offre_experience p, int id) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "UPDATE  offre_experience set nom=?,description=?,url_image=?,addrese=?,rating=?,climatisation=?,wifi=?,snackbar=?,parking=?,piscine=?,familiale=?,paiementparcarte=?,balcon=?,visites=?,fumer=?,reservations=? where id="+id; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            st.setString(1, p.getNom());
            st.setString(2, p.getDescription());
            st.setString(3, p.getUrl_image());
            st.setString(4, p.getAddrese());
            st.setInt(5, p.getRating());
            st.setBoolean(6, p.getClimatisation());
            st.setBoolean(7, p.getWifi());
            st.setBoolean(8, p.getSnackbar());
            st.setBoolean(9, p.getParking());
            st.setBoolean(10, p.getPiscine());
            st.setBoolean(11, p.getFamiliale());
            st.setBoolean(12, p.getPaiementparcarte());
            st.setBoolean(13, p.getBalcon());
            st.setBoolean(14, p.getVisites());
            st.setBoolean(15, p.getFumer());
            st.setBoolean(16, p.getReservations());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void SupprimerExperience(int id) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "DELETE FROM offre_experience where id=?"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<offre_experience> listerPartageExp() {
        List<offre_experience> myList = new ArrayList<offre_experience>();

        try {
            String requete3 = "SELECT * From offre_experience";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                offre_experience p = new offre_experience();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setUrl_image(rs.getString(5));
                p.setAddrese(rs.getString(6));
                p.setRegion_id(rs.getInt(8));
                p.setDatecreation(rs.getDate(9));
                p.setRating(rs.getInt(10));
                p.setCatid(rs.getInt(11));
                p.setClimatisation(rs.getBoolean(14));
                p.setWifi(rs.getBoolean(15));
                p.setSnackbar(rs.getBoolean(16));
                p.setParking(rs.getBoolean(17));
                p.setPiscine(rs.getBoolean(18));
                p.setFamiliale(rs.getBoolean(19));
                p.setPaiementparcarte(rs.getBoolean(20));
                p.setBalcon(rs.getBoolean(21));
                p.setVisites(rs.getBoolean(22));
                p.setFumer(rs.getBoolean(23));
                p.setReservations(rs.getBoolean(24));
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }

    public List<offre_experience> RechExp(String nom) {
        List<offre_experience> myList = new ArrayList<offre_experience>();

        try {
            String requete3 = "SELECT * From offre_experience where nom='" + nom + "'";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                offre_experience p = new offre_experience();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setUrl_image(rs.getString(5));
                p.setAddrese(rs.getString(6));
                p.setDatecreation(rs.getDate(9));
                p.setRating(rs.getInt(10));
                p.setRating(rs.getInt(10));
                p.setClimatisation(rs.getBoolean(14));
                p.setWifi(rs.getBoolean(15));
                p.setSnackbar(rs.getBoolean(16));
                p.setParking(rs.getBoolean(17));
                p.setPiscine(rs.getBoolean(18));
                p.setFamiliale(rs.getBoolean(19));
                p.setPaiementparcarte(rs.getBoolean(20));
                p.setBalcon(rs.getBoolean(21));
                p.setVisites(rs.getBoolean(22));
                p.setFumer(rs.getBoolean(23));
                p.setReservations(rs.getBoolean(24));
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }

    public List<String> ListRegion() {

        List<String> list = new ArrayList<String>();
        try {
            String sqlStationName = " select nom from region ";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(sqlStationName);

            while (rs.next()) {

                list.add(rs.getString("nom"));

            }

            rs.close();
            st3.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        }
        return list;
    }

    public List<String> ListCategorie() {

        List<String> list = new ArrayList<String>();
        try {
            String sqlStationName = " select libelle from categorie ";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(sqlStationName);

            while (rs.next()) {

                list.add(rs.getString("libelle"));

            }

            rs.close();
            st3.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        }
        return list;
    }

    public int GetItemId(String nomtable, String comparethis, String comparable) {

        int i = 0;
        try {
            String sqlStationName = " select id from " + nomtable + " where " + comparethis + "='" + comparable + "'";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(sqlStationName);
            while (rs.next()) {

                i = rs.getInt("id");

            }

            rs.close();
            st3.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        }
        return i;
    }

    public String GetRegion(int id) {
        String reg = null;
        try {
            String sqlStationName = " select nom from region where id=" + id;
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(sqlStationName);
            while (rs.next()) {

                reg = rs.getString("nom");

            }

            rs.close();
            st3.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        }
        return reg;
    }

    public User GetUserById(int id) {
        User usr = new User();
        try {
            String sqlStationName = " select * from fosuser where id=" + id;
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(sqlStationName);
            while (rs.next()) {

                usr.setNom(rs.getString("nom"));
                usr.setId(rs.getInt("id"));
                usr.setPrenom(rs.getString("prenom"));
                usr.setUsername(rs.getString("username"));
                usr.setEmail(rs.getString("email"));
                usr.setImage(rs.getString("image"));

            }

            rs.close();
            st3.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        }
        return usr;
    }

    public List<notif_demande> GetNotificationById(int id) {
        List<notif_demande> ld = new ArrayList<>();
        notif_demande notifdem = new notif_demande();
        try {
            String sqlStationName = " select * from notif_demande where demandeur=" + id+" AND seen=0";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(sqlStationName);
            while (rs.next()) {

                notifdem.setSubject(rs.getString("subject"));
                notifdem.setDemandeur(rs.getInt("demandeur"));
                notifdem.setUserid(rs.getInt("userid"));
                notifdem.setId(rs.getInt("id"));
                notifdem.setDemandeid(rs.getInt("demandeid"));
                notifdem.setMessage(rs.getString("message"));
                notifdem.setLink(rs.getString("link"));
                notifdem.setSeen(rs.getBoolean("seen"));
                ld.add(notifdem);

            }

            rs.close();
            st3.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        }
        return ld;
    }

    public List<offre_experience> listerPartageExpParOption(String options) {
        List<offre_experience> myList = new ArrayList<offre_experience>();
        if (options.contains("Wi-Fi")) {
            options = options.replace("Wi-Fi", "wifi=1");
        }
        if (options.contains("Climatisation")) {
            options = options.replace("Climatisation", "climatisation=1");
        }
        if (options.contains("Conviviale et familiale")) {
            options = options.replace("Conviviale et familiale", "familiale=1");
        }
        if (options.contains("Réservations")) {
            options = options.replace("Réservations", "	reservations=1");
        }
        if (options.contains("Parking gratuit")) {
            options = options.replace("Parking gratuit", "parking=1");
        }
        if (options.contains("Autorisation de fumer")) {
            options = options.replace("Autorisation de fumer", "fumer=1");
        }
        if (options.contains("Paiement par carte")) {
            options = options.replace("Paiement par carte", "paiementparcarte=1");
        }
        if (options.contains("Balcon")) {
            options = options.replace("Balcon", "balcon=1");
        }
        if (options.contains("Visites guidées")) {
            options = options.replace("Visites guidées", "visites=1");
        }
        if (options.contains("Snack-bar")) {
            options = options.replace("Snack-bar", "snackbar=1");
        }
        if (options.contains("Piscine")) {
            options = options.replace("Piscine", "piscine=1");
        }
        try {

            if (options.length() > 0) {
                options = options.replaceAll(",", " AND ");
                String requete3 = "SELECT * From offre_experience  WHERE " + options;

                Statement st3 = MyConnection.getInstance().getCnx().createStatement();
                ResultSet rs = st3.executeQuery(requete3);
                while (rs.next()) {
                    offre_experience p = new offre_experience();
                    p.setId(rs.getInt(1));
                    p.setNom(rs.getString(3));
                    p.setDescription(rs.getString(4));
                    p.setUrl_image(rs.getString(5));
                    p.setAddrese(rs.getString(6));
                    p.setRegion_id(rs.getInt(8));
                    p.setDatecreation(rs.getDate(9));
                    p.setRating(rs.getInt(10));
                    p.setCatid(rs.getInt(11));
                    p.setClimatisation(rs.getBoolean(14));
                    p.setWifi(rs.getBoolean(15));
                    p.setSnackbar(rs.getBoolean(16));
                    p.setParking(rs.getBoolean(17));
                    p.setPiscine(rs.getBoolean(18));
                    p.setFamiliale(rs.getBoolean(19));
                    p.setPaiementparcarte(rs.getBoolean(20));
                    p.setBalcon(rs.getBoolean(21));
                    p.setVisites(rs.getBoolean(22));
                    p.setFumer(rs.getBoolean(23));
                    p.setReservations(rs.getBoolean(24));
                    myList.add(p);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }

    public List<offre_experience> listerPartageExpParRegion(String options) {
        List<offre_experience> myList = new ArrayList<offre_experience>();
        if (options.contains("ARIANA")) {
            options = options.replace("ARIANA", "1");
        }
        if (options.contains("SOUSSE")) {
            options = options.replace("SOUSSE", "2");
        }
        if (options.contains("TUNIS")) {
            options = options.replace("TUNIS", "3");
        }
        if (options.contains("NABEUL")) {
            options = options.replace("NABEUL", "4");
        }
        if (options.contains("MONASTIR")) {
            options = options.replace("MONASTIR", "5");
        }
        if (options.contains("BIZERTE")) {
            options = options.replace("BIZERTE", "6");
        }
        if (options.contains("BEN AROUS")) {
            options = options.replace("BEN AROUS", "7");
        }

        if (options.contains("BEJA")) {
            options = options.replace("BEJA", "8");
        }
        if (options.contains("GABES")) {
            options = options.replace("GABES", "9");
        }
        if (options.contains("GAFSA")) {
            options = options.replace("GAFSA", "10");
        }
        if (options.contains("JENDOUBA")) {
            options = options.replace("JENDOUBA", "11");
        }
        if (options.contains("KAIROUAN")) {
            options = options.replace("KAIROUAN", "12");
        }
        if (options.contains("KASSERINE")) {
            options = options.replace("KASSERINE", "13");
        }
        if (options.contains("KEBILI")) {
            options = options.replace("KEBILI", "14");
        }
        if (options.contains("KEF")) {
            options = options.replace("KEF", "15");
        }
        if (options.contains("MAHDIA")) {
            options = options.replace("MAHDIA", "16");
        }
        if (options.contains("MANOUBA")) {
            options = options.replace("MANOUBA", "17");
        }
        if (options.contains("MEDNINE")) {
            options = options.replace("MEDNINE", "18");
        }
        if (options.contains("SFAX")) {
            options = options.replace("SFAX", "19");
        }
        if (options.contains("SIDI BOUZID")) {
            options = options.replace("SIDI BOUZID", "20");
        }
        if (options.contains("SILIANA")) {
            options = options.replace("SILIANA", "21");
        }
        if (options.contains("TATAOUINE")) {
            options = options.replace("TATAOUINE", "22");
        }
        if (options.contains("TOZEUR")) {
            options = options.replace("TOZEUR", "23");
        }
        if (options.contains("ZAGHOUAN")) {
            options = options.replace("ZAGHOUAN", "24");
        }
        try {

            if (options.length() > 0) {
                //  options = options.replaceAll(",", " AND ");
                String requete3 = "SELECT * From offre_experience  WHERE region_id in (" + options + ")";

                Statement st3 = MyConnection.getInstance().getCnx().createStatement();
                ResultSet rs = st3.executeQuery(requete3);
                while (rs.next()) {
                    offre_experience p = new offre_experience();
                    p.setId(rs.getInt(1));
                    p.setNom(rs.getString(3));
                    p.setDescription(rs.getString(4));
                    p.setUrl_image(rs.getString(5));
                    p.setAddrese(rs.getString(6));
                    p.setRegion_id(rs.getInt(8));
                    p.setDatecreation(rs.getDate(9));
                    p.setRating(rs.getInt(10));
                    p.setCatid(rs.getInt(11));
                    p.setClimatisation(rs.getBoolean(14));
                    p.setWifi(rs.getBoolean(15));
                    p.setSnackbar(rs.getBoolean(16));
                    p.setParking(rs.getBoolean(17));
                    p.setPiscine(rs.getBoolean(18));
                    p.setFamiliale(rs.getBoolean(19));
                    p.setPaiementparcarte(rs.getBoolean(20));
                    p.setBalcon(rs.getBoolean(21));
                    p.setVisites(rs.getBoolean(22));
                    p.setFumer(rs.getBoolean(23));
                    p.setReservations(rs.getBoolean(24));
                    myList.add(p);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }

    public List<offre_experience> listerPartageExpParRegionOption(String options, String options2) {
        List<offre_experience> myList = new ArrayList<offre_experience>();

        try {

            if ((options != null) && ((options2 == null) || (options2.length() == 0))) {
                if (options.contains("ARIANA")) {
                    options = options.replace("ARIANA", "1");
                }
                if (options.contains("SOUSSE")) {
                    options = options.replace("SOUSSE", "2");
                }
                if (options.contains("TUNIS")) {
                    options = options.replace("TUNIS", "3");
                }
                if (options.contains("NABEUL")) {
                    options = options.replace("NABEUL", "4");
                }
                if (options.contains("MONASTIR")) {
                    options = options.replace("MONASTIR", "5");
                }
                if (options.contains("BIZERTE")) {
                    options = options.replace("BIZERTE", "6");
                }
                if (options.contains("BEN AROUS")) {
                    options = options.replace("BEN AROUS", "7");
                }

                if (options.contains("BEJA")) {
                    options = options.replace("BEJA", "8");
                }
                if (options.contains("GABES")) {
                    options = options.replace("GABES", "9");
                }
                if (options.contains("GAFSA")) {
                    options = options.replace("GAFSA", "10");
                }
                if (options.contains("JENDOUBA")) {
                    options = options.replace("JENDOUBA", "11");
                }
                if (options.contains("KAIROUAN")) {
                    options = options.replace("KAIROUAN", "12");
                }
                if (options.contains("KASSERINE")) {
                    options = options.replace("KASSERINE", "13");
                }
                if (options.contains("KEBILI")) {
                    options = options.replace("KEBILI", "14");
                }
                if (options.contains("KEF")) {
                    options = options.replace("KEF", "15");
                }
                if (options.contains("MAHDIA")) {
                    options = options.replace("MAHDIA", "16");
                }
                if (options.contains("MANOUBA")) {
                    options = options.replace("MANOUBA", "17");
                }
                if (options.contains("MEDNINE")) {
                    options = options.replace("MEDNINE", "18");
                }
                if (options.contains("SFAX")) {
                    options = options.replace("SFAX", "19");
                }
                if (options.contains("SIDI BOUZID")) {
                    options = options.replace("SIDI BOUZID", "20");
                }
                if (options.contains("SILIANA")) {
                    options = options.replace("SILIANA", "21");
                }
                if (options.contains("TATAOUINE")) {
                    options = options.replace("TATAOUINE", "22");
                }
                if (options.contains("TOZEUR")) {
                    options = options.replace("TOZEUR", "23");
                }
                if (options.contains("ZAGHOUAN")) {
                    options = options.replace("ZAGHOUAN", "24");
                }
                System.out.println("REQ 1 " + options);

                //  options = options.replaceAll(",", " AND ");
                reqmix = "SELECT * From offre_experience  WHERE region_id in (" + options + ")";
            } else if (((options == null) || (options.length() == 0)) && ((options2 != null)||(options2!=""))) {

                if (options2.contains("Wi-Fi")) {
                    options2 = options2.replace("Wi-Fi", "wifi=1");
                }
                if (options2.contains("Climatisation")) {
                    options2 = options2.replace("Climatisation", "climatisation=1");
                }
                if (options2.contains("Conviviale et familiale")) {
                    options2 = options2.replace("Conviviale et familiale", "familiale=1");
                }
                if (options2.contains("Réservations")) {
                    options2 = options2.replace("Réservations", "	reservations=1");
                }
                if (options2.contains("Parking gratuit")) {
                    options2 = options2.replace("Parking gratuit", "parking=1");
                }
                if (options2.contains("Autorisation de fumer")) {
                    options2 = options2.replace("Autorisation de fumer", "fumer=1");
                }
                if (options2.contains("Paiement par carte")) {
                    options2 = options2.replace("Paiement par carte", "paiementparcarte=1");
                }
                if (options2.contains("Balcon")) {
                    options2 = options2.replace("Balcon", "balcon=1");
                }
                if (options2.contains("Visites guidées")) {
                    options2 = options2.replace("Visites guidées", "visites=1");
                }
                if (options2.contains("Snack-bar")) {
                    options2 = options2.replace("Snack-bar", "snackbar=1");
                }
                if (options2.contains("Piscine")) {
                    options2 = options2.replace("Piscine", "piscine=1");
                }
                options2 = options2.replaceAll(",", " AND ");
                System.out.println("REQ 2 " + options2);
                reqmix = "SELECT * From offre_experience  WHERE " + options2;
            } else if ((options != null) && (options2 != null)) {
                if (options.contains("ARIANA")) {
                    options = options.replace("ARIANA", "1");
                }
                if (options.contains("SOUSSE")) {
                    options = options.replace("SOUSSE", "2");
                }
                if (options.contains("TUNIS")) {
                    options = options.replace("TUNIS", "3");
                }
                if (options.contains("NABEUL")) {
                    options = options.replace("NABEUL", "4");
                }
                if (options.contains("MONASTIR")) {
                    options = options.replace("MONASTIR", "5");
                }
                if (options.contains("BIZERTE")) {
                    options = options.replace("BIZERTE", "6");
                }
                if (options.contains("BEN AROUS")) {
                    options = options.replace("BEN AROUS", "7");
                }

                if (options.contains("BEJA")) {
                    options = options.replace("BEJA", "8");
                }
                if (options.contains("GABES")) {
                    options = options.replace("GABES", "9");
                }
                if (options.contains("GAFSA")) {
                    options = options.replace("GAFSA", "10");
                }
                if (options.contains("JENDOUBA")) {
                    options = options.replace("JENDOUBA", "11");
                }
                if (options.contains("KAIROUAN")) {
                    options = options.replace("KAIROUAN", "12");
                }
                if (options.contains("KASSERINE")) {
                    options = options.replace("KASSERINE", "13");
                }
                if (options.contains("KEBILI")) {
                    options = options.replace("KEBILI", "14");
                }
                if (options.contains("KEF")) {
                    options = options.replace("KEF", "15");
                }
                if (options.contains("MAHDIA")) {
                    options = options.replace("MAHDIA", "16");
                }
                if (options.contains("MANOUBA")) {
                    options = options.replace("MANOUBA", "17");
                }
                if (options.contains("MEDNINE")) {
                    options = options.replace("MEDNINE", "18");
                }
                if (options.contains("SFAX")) {
                    options = options.replace("SFAX", "19");
                }
                if (options.contains("SIDI BOUZID")) {
                    options = options.replace("SIDI BOUZID", "20");
                }
                if (options.contains("SILIANA")) {
                    options = options.replace("SILIANA", "21");
                }
                if (options.contains("TATAOUINE")) {
                    options = options.replace("TATAOUINE", "22");
                }
                if (options.contains("TOZEUR")) {
                    options = options.replace("TOZEUR", "23");
                }
                if (options.contains("ZAGHOUAN")) {
                    options = options.replace("ZAGHOUAN", "24");
                }
                if (options2.contains("Wi-Fi")) {
                    options2 = options2.replace("Wi-Fi", "wifi=1");
                }
                if (options2.contains("Climatisation")) {
                    options2 = options2.replace("Climatisation", "climatisation=1");
                }
                if (options2.contains("Conviviale et familiale")) {
                    options2 = options2.replace("Conviviale et familiale", "familiale=1");
                }
                if (options2.contains("Réservations")) {
                    options2 = options2.replace("Réservations", "	reservations=1");
                }
                if (options2.contains("Parking gratuit")) {
                    options2 = options2.replace("Parking gratuit", "parking=1");
                }
                if (options2.contains("Autorisation de fumer")) {
                    options2 = options2.replace("Autorisation de fumer", "fumer=1");
                }
                if (options2.contains("Paiement par carte")) {
                    options2 = options2.replace("Paiement par carte", "paiementparcarte=1");
                }
                if (options2.contains("Balcon")) {
                    options2 = options2.replace("Balcon", "balcon=1");
                }
                if (options2.contains("Visites guidées")) {
                    options2 = options2.replace("Visites guidées", "visites=1");
                }
                if (options2.contains("Snack-bar")) {
                    options2 = options2.replace("Snack-bar", "snackbar=1");
                }
                if (options2.contains("Piscine")) {
                    options2 = options2.replace("Piscine", "piscine=1");
                }
                options2 = options2.replaceAll(",", " AND ");
                System.out.println("REQ 3 " + options2 + " & " + options);
                reqmix = "SELECT * From offre_experience  WHERE " + options2 + " AND region_id in (" + options + ")";
            }
            if (
                    ((options == null) || (options .length()<1)) && ((options2 == null) || (options2.length()<1))
                    ) {
                reqmix = "SELECT * From offre_experience";
            }
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(reqmix);
            while (rs.next()) {
                offre_experience p = new offre_experience();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setUrl_image(rs.getString(5));
                p.setAddrese(rs.getString(6));
                p.setRegion_id(rs.getInt(8));
                p.setDatecreation(rs.getDate(9));
                p.setRating(rs.getInt(10));
                p.setCatid(rs.getInt(11));
                p.setClimatisation(rs.getBoolean(14));
                p.setWifi(rs.getBoolean(15));
                p.setSnackbar(rs.getBoolean(16));
                p.setParking(rs.getBoolean(17));
                p.setPiscine(rs.getBoolean(18));
                p.setFamiliale(rs.getBoolean(19));
                p.setPaiementparcarte(rs.getBoolean(20));
                p.setBalcon(rs.getBoolean(21));
                p.setVisites(rs.getBoolean(22));
                p.setFumer(rs.getBoolean(23));
                p.setReservations(rs.getBoolean(24));
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }

    public String ReplaceRegion(String o) {
        String options = o;

        return options;
    }

    public String ReplaceOptions(String o2) {
        String options2 = o2;

        return options2;
    }
      public void SetNotificationSeen( int id) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "UPDATE  notif_demande set seen=1 where id="+id; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
           
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
      public int GEtMoyRating(int id) {

        int i = 0;
        try {
            String sqlStationName = " select AVG(rating) as moyenne from avis where ref_experiencce="+id;
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(sqlStationName);
            while (rs.next()) {

                i = (int)rs.getDouble("moyenne");
              //  System.out.println("i= "+i);

            }

            rs.close();
            st3.close();

        } catch (SQLException ex) {
            System.err.println("ERR" + ex);
        }
        return i;
    }
}
