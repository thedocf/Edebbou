/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Anonnce;
import entite.commentaire;
import entite.Fos_user;
import service.IServise;
import util.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ServiceCommentaire implements IServise<commentaire>{
     private Connection con;
    private Statement ste;

    public ServiceCommentaire() {
        con = ConnexionBD.getInstance().getConnection();

    }
    
    
     @Override
    public void ajouter(commentaire t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("INSERT INTO commentaire ( `id_user`, `id_annonce`, `commentaire`) VALUES ( ?, ?, ?);");
       
        Fosuserservice user_ser= new Fosuserservice();
        pre.setInt(1,user_ser.getuser(t.getUser_id()).getId());
        Annoncesservice annonce_ser = new Annoncesservice();
        pre.setInt(2, annonce_ser.getAnnonce(t.getId_annonce()).getId());
        //System.out.println( t.getU());
        pre.setString(3, t.getComentaire());
        pre.executeUpdate();    
    }
    
      @Override
    public boolean delete(int id) throws SQLException {
//        if(chercher(id)){
        PreparedStatement pre=con.prepareStatement("DELETE FROM `devcrew`.`commentaire`  WHERE id_commentaire = (?) ;");
        pre.setInt(1,id);
        if (pre.executeUpdate() != 0)
            return true;
//        }
        return false;
    }   
    @Override
    public boolean update(commentaire t, int id) throws SQLException {
        
        PreparedStatement pre=con.prepareStatement("UPDATE `devcrew`.`commentaire` SET `commentaire` = ?  WHERE `id_commentaire` = ? ;");
        pre.setString(1, t.getComentaire());
//        pre.setString(2, t.getDescription());
//        pre.setString(3, t.getImage());
//        pre.setInt(4, t.getNote());
//        pre.setString(5, t.getType());
        pre.setInt(2,id);
        if (pre.executeUpdate() != 0)
            return true;
        return false;
        
    }
    @Override
    public List<commentaire> readAll() throws SQLException {
        List<commentaire> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from commentaire ORDER BY id_commentaire DESC");
     while (rs.next()) {                
               int id=rs.getInt(1);
               Fosuserservice user_ser= new Fosuserservice();
               int user_id = rs.getInt(2);
               Fos_user u =user_ser.getuser_id(user_id); 
               String a= u.getUsername();
               int annonce_id = rs.getInt(3);
               String commentaire = rs.getString(4);
               commentaire p=new commentaire(id,commentaire,a);
               
     arr.add(p);
     }
    return arr;
    }
    public List<commentaire> readA(int id) throws SQLException {
        List<commentaire> arr=new ArrayList<>();
    PreparedStatement pre = con.prepareStatement("select * from commentaire ORDER BY id_commentaire DESC where id_annonce= (?);  ");
    pre.setInt(1,id);
   
        ResultSet rs = pre.executeQuery();
  
    while (rs.next()) {                
               Fosuserservice user_ser= new Fosuserservice();
               int user_id = rs.getInt(2);
               Fos_user u =user_ser.getuser_id(user_id); 
               String a= u.getUsername();
               String commentaire = rs.getString(3);
               commentaire p=new commentaire(id,commentaire,a);
               
     arr.add(p);
     }
    return arr;
    }
    
}
