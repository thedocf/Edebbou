/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import util.ConnexionBD;
import entite.Category;
import entite.Livreur;
import entite.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
/**
 *
 * @author Th3Doc
 */
public class serviceCategory implements ICategory{
    Connection cnx;
    public serviceCategory()
    {
        cnx=ConnexionBD.getInstance().getConnection();
    }
    @Override
    public void addCategory(Category cl) throws SQLException {
      Statement stm = cnx.createStatement();
    String query = "INSERT INTO `category` (`id`,`label`) VALUES (NULL,'"+cl.getLabel()+"')";
    stm.executeUpdate(query);
    }
    public List<Category> afficherPro () {
        List<Category> ll = new ArrayList<Category>();
        
            PreparedStatement pt = null;
        try {
            pt = cnx.prepareStatement("select * from category");
        } catch (SQLException ex) {
            Logger.getLogger(serviceCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
            ResultSet rs = null;
        try {
            rs = pt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(serviceCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        try {
            while (rs.next()) {
                Category cl=new Category();
                cl.setId(rs.getInt("id"));
                
                cl.setLabel(rs.getString("label"));
                
                
                ll.add(cl);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
            return ll;
    }
    @Override
    public List<Category> displayCategories() throws SQLException {
    Statement stm=cnx.createStatement();
        String query ="SELECT * FROM `category`"; 
        ResultSet rst = stm.executeQuery(query);
        ArrayList <Category> cats = new ArrayList<>();
        while(rst.next())
        {
         Category cl=new Category();
         cl.setId(rst.getInt("id"));
         cl.setLabel(rst.getString("label"));
        
         
         cats.add(cl);
        }
        return cats;
    }

    @Override
    public void updateCategory(Category cl, int id) throws SQLException {
   Statement stm=cnx.createStatement();
    String query="UPDATE `category` SET `label`='"+cl.getLabel()+"' WHERE `id`='"+id+"'";
    stm.executeUpdate(query);
    }

    @Override
    public void deleteCategory(int id) throws SQLException {
     Statement stm=cnx.createStatement();
        String query="DELETE FROM `category` WHERE `id`='"+id+"'";
        stm.executeUpdate(query); 
    }
    
    public List<Category> afficherCat () {
        List<Category> ll = new ArrayList<Category>();
        try {
            PreparedStatement pt =cnx.prepareStatement("select * from Category");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                Category l = new Category();
                l.setId(rs.getInt(1));
                l.setLabel(rs.getString(2));
                ll.add(l);
                System.out.println("Categ [id:"+rs.getInt(1)+" ,nom: "+rs.getString(2)+"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }

    @Override
    public ObservableList<Category> readAll() throws SQLException {
    ObservableList<Category> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    ResultSet rst=stm.executeQuery("select * from category");
     while (rst.next()) {                
      Category cl=new Category();
         cl.setId(rst.getInt("id"));
        
         cl.setLabel(rst.getString("label"));
         
         arr.add(cl);
     }
    return arr;
    }
    public ObservableList<String> readAll2() throws SQLException {
    ObservableList<String> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    ResultSet rst=stm.executeQuery("select label from category");
     while (rst.next()) {                
         
        
         
         arr.add(rst.getString("label"));
     }
    return arr;
    }
    public ObservableList<Category> triCatsByLabel()throws SQLException
    {
        ObservableList<Category> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    ResultSet rst=stm.executeQuery("select * from category order by label");
     while (rst.next()) {                
      Category cl=new Category();
         cl.setId(rst.getInt("id"));
        
         cl.setLabel(rst.getString("label"));
        
         
         arr.add(cl);
     }
    return arr;
    }
   
   
    @Override
    public int getIDBy(String lab)throws SQLException {
       Statement stm=cnx.createStatement();
        String query="SELECT id FROM `category` WHERE `label`='"+lab+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         int arr=0;
        if(rst.next())
         arr=rst.getInt("id");
    return arr;
    }
    
 
    public String getLabelBy(int ID)throws SQLException {
       Statement stm=cnx.createStatement();
        String query="SELECT label FROM `category` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         String arr="";
        if(rst.next())
         arr=rst.getString("label");
    return arr;
    }

    @Override
    public Category rechercherCategory(int id) throws SQLException {
    Statement stm=cnx.createStatement();
        String query="SELECT * FROM `category` WHERE `id`='"+id+"'";
        ResultSet rst=stm.executeQuery(query);
        Category cl=new Category();
        while(rst.next()){
        cl.setId(rst.getInt("id"));
        cl.setLabel(rst.getString("label"));
      
        }
        return cl;
    }

    @Override
    public ObservableList<Category> rechercherCatFiltre(String k) throws SQLException {
   Statement stm=cnx.createStatement();
        String query="SELECT * FROM `category` WHERE CONCAT(`id`,`label`) like '%"+k+"%' ";
        ResultSet rst=stm.executeQuery(query);
        ObservableList<Category> cl=FXCollections.observableArrayList();
        Category c=new Category();
        while(rst.next()){
        c.setId(rst.getInt("id"));
        c.setLabel(rst.getString("label"));
       
        cl.add(c);
        }
        return cl; 
    }
    
}
