/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import util.ConnexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import entite.Anonnce;
import entite.Fos_user;
/**
 *
 * @author Fida
 */
public class Annoncesservice {
    private Connection cnx;   
    private Statement st; 
    private PreparedStatement pst;
    private ResultSet rs;
    public Annoncesservice(){
    cnx= ConnexionBD.getInstance().getConnection();
    }   
    public void ajouter(Anonnce t) throws SQLException {
  
    PreparedStatement pst=cnx.prepareStatement("insert into annonce(creator,title,description,photo) VALUES ( ?, ?, ?, ?);");    
       
        Fosuserservice user_ser= new Fosuserservice();
        pst.setInt(1,user_ser.getuser(t.getId_user()).getId()); 
        pst.setString(2, t.getTitle());
        pst.setString(3, t.getDescription());
        pst.setString(4, t.getImage());
       
        pst.executeUpdate(); }
          public List<Anonnce> readAll() throws SQLException {
 String req="select*from annonce "; 
     List<Anonnce> list=new ArrayList<>(); 
        
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            while(rs.next()) {
                ImageView l=new  ImageView();
                l.setImage(new Image(rs.getString(5)));
                l.setFitHeight(50);
                l.setFitWidth(50);
            int id = rs.getInt(1); 
             String text=rs.getString(3);
             
             String title=rs.getString(4);
               String image=rs.getString(5);
               
               int user_id = rs.getInt(2);
               
        Fosuserservice user_ser= new Fosuserservice();
                Fos_user u =user_ser.getuser_id(user_id); 
               
                String a = u.getUsername();
               java.sql.Date d2 = new java.sql.Date(rs.getDate(6).getTime());
               ;
      
        
                    Anonnce p=new Anonnce(id,text, title, image, null, a, d2) ;
     p.setPhoto(l);
     list.add(p);
            } 
        return list;    
    }
       public List<Anonnce> readAll2() throws SQLException {
 String req="select*from annonce "; 
     List<Anonnce> list=new ArrayList<>(); 
        
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            while(rs.next()) {
                ImageView l=new  ImageView();
                l.setImage(new Image(rs.getString(5)));
                l.setFitHeight(50);
                l.setFitWidth(50);
            int id = rs.getInt(1); 
             String text=rs.getString(3);
             
             String title=rs.getString(4);
               String image=rs.getString(5);
               
               int user_id = rs.getInt(2);
               
        Fosuserservice user_ser= new Fosuserservice();
                Fos_user u =user_ser.getuser_id(user_id); 
               
                String a = u.getUsername();
               java.sql.Date d2 = new java.sql.Date(rs.getDate(6).getTime());
               Integer rating=rs.getInt(7);
      
        
                    Anonnce p=new Anonnce(id,text, title,rating, image, null, a, d2) ;
     p.setPhoto(l);
     list.add(p);
            } 
        return list;    
    }
       public List<Anonnce> Tri() throws SQLException {
 String req="select*from annonce ORDER BY postdate DESC"; 
     List<Anonnce> list=new ArrayList<>(); 
        
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            while(rs.next()) {
                ImageView l=new  ImageView();
                l.setImage(new Image(rs.getString(5)));
                l.setFitHeight(50);
                l.setFitWidth(50);
            int id = rs.getInt(1); 
             String text=rs.getString(3);
             
             String title=rs.getString(4);
               String image=rs.getString(5);
               
               int user_id = rs.getInt(2);
               
        Fosuserservice user_ser= new Fosuserservice();
                Fos_user u =user_ser.getuser_id(user_id); 
               
                String a = u.getUsername();
               java.sql.Date d2 = new java.sql.Date(rs.getDate(6).getTime());
      
        
                    Anonnce p=new Anonnce(id,text, title, image, null, a, d2) ;
     p.setPhoto(l);
     list.add(p);
            } 
        return list;    
    }
          public Anonnce getannonce_id(int id) throws SQLException {  
        Anonnce us = new Anonnce();
        PreparedStatement pre = cnx.prepareStatement("SELECT * FROM annonce WHERE id LIKE ? ;");

        pre.setInt(1,id); 
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id_annonce = rs.getInt(1);
            String title = rs.getString(3);
            String image = rs.getString(5);
            int id_user = rs.getInt(2);
            String desc = rs.getString(4);
            Date date =rs.getDate(6);
             
        Fosuserservice user_ser= new Fosuserservice();
            Fos_user u =user_ser.getuser_id(id_user);
            us.setId(id_annonce);
            us.setImage(image);
            us.setDescription(desc);
            us.setId_user(u);
            us.setTitle(title);
            us.setDate(date);
        }
        return us;

    }
         public boolean chercher(int id) throws SQLException {
        String req="select * from annonce  ";
        List<Integer> list = new ArrayList<>();
        
        try {
            st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Annoncesservice.class.getName()).log(Level.SEVERE, null, ex);
        }
       // list.forEach(System.out::println);
        return list.contains(id);
    }
          public boolean delete(int id) throws SQLException  {
        if (chercher(id)) {
            PreparedStatement pstmt = cnx.prepareStatement("DELETE FROM annonce WHERE id = ?");

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            if (pstmt.executeUpdate() != 0) {
                return true;
            }
        }
        return false;
    }
             public boolean update(Anonnce t, int id) throws SQLException {
PreparedStatement pre;

        pre = cnx.prepareStatement("UPDATE annonce SET title = ? ,description = ? , photo = ? WHERE id = ? ;");
        pre.setString(1, t.getTitle());
                pre.setString(3, t.getImage());
                
                pre.setString(2, t.getDescription());
                
                pre.setInt(4,id);
                pre.executeUpdate();

        if (pre.executeUpdate() != 0) {
            return true;
        }
        return false;
        
     }
              public Anonnce getAnnonce(Anonnce a) throws SQLException {   
        Anonnce an = new Anonnce();
        PreparedStatement pre = cnx.prepareStatement("SELECT * FROM annonce WHERE  photo LIKE ? AND title LIKE ? AND creator LIKE ? ;");

        
        pre.setString(1, a.getImage());
        pre.setString(2, a.getTitle());
        pre.setInt(3, a.getId_user().getId());
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id_annonce = rs.getInt(1);
            String image= rs.getString(5);
            String text = rs.getString(3);
            int id_user = rs.getInt(2);
            Fosuserservice user_ser= new Fosuserservice();
            Fos_user u =user_ser.getuser_id(id_user);
            
            an.setId(id_annonce);
            an.setImage(image);
            an.setTitle(text);
            an.setId_user(u);
        }
        return an;

    }
               public List<Anonnce> afficherev() {

        List<Anonnce> le = new ArrayList<>();
        try {
            String select = "SELECT * from annonce ";
            Statement statement1 = cnx.createStatement();
            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
               ImageView l=new  ImageView();
                l.setImage(new Image(rs.getString(5)));
                l.setFitHeight(50);
                l.setFitWidth(50);
            int id = rs.getInt(1); 
             String text=rs.getString(3);
             
             String title=rs.getString(4);
               String image=rs.getString(5);
               
               int user_id = rs.getInt(2);
               
        Fosuserservice user_ser= new Fosuserservice();
                Fos_user u =user_ser.getuser_id(user_id); 
               
                String a = u.getUsername();
               java.sql.Date d2 = new java.sql.Date(rs.getDate(6).getTime());
      
        
                    Anonnce p=new Anonnce(id,text, title, image, null, a, d2) ;
     p.setPhoto(l);
    
                le.add(p);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return le;

    }
               public float getRateAnnonces(Anonnce e) {

        float moy = 0.0f;

        try {
            PreparedStatement st = cnx.prepareStatement("SELECT AVG(rating) FROM `annonce` where id = '" + e.getId() + "'");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                moy = rs.getFloat(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Annoncesservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return moy;

    }
               public void addRarting(Anonnce e) {
        try {
//            String req=("INSERT INTO `annonce`(`id`, `id_user`, `rating`, `nbrrating`, `nbruser`) VALUES (?,?,?,?,?)");
            String req = ("update `annonce` SET `rating` =? WHERE id = '" + e.getId() + "' ");

            PreparedStatement st =ConnexionBD.getInstance().getConnection().prepareStatement(req);
            st.setInt(1, e.getRating());

//                      
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Annoncesservice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
