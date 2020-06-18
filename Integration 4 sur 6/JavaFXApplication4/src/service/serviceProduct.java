/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entite.Livreur;
import entite.Product;
import gui.ProduitBackController;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import service.IProduct;
import util.ConnexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Th3Doc
 */
public class serviceProduct implements IProduct {
    Connection cnx;
    public serviceProduct()
    {
        cnx=ConnexionBD.getInstance().getConnection();
    }
    public void addProduct(Product cl) throws SQLException {
      Statement stm = cnx.createStatement();
    String query = "INSERT INTO `product` (`id`,`nom`,`prix`,`qte`,`description`,`date_expiration`,`image`,`cat_id`) VALUES (NULL,'"+cl.getNom()+"','"+cl.getPrix()+"','"+cl.getQte()+"','"+cl.getDescription()+"','"+cl.getDate_expiration()+"','"+cl.getImage()+"','"+cl.getCategory()+"')";
    stm.executeUpdate(query);
    }

    public List<Product> afficherPro () {
        List<Product> ll = new ArrayList<Product>();
        try {
            PreparedStatement pt =cnx.prepareStatement("select * from product");
            ResultSet rs = pt.executeQuery();
            
            while (rs.next()) {                
      Product cl=new Product();
         cl.setId(rs.getInt("id"));
         serviceCategory sc=new serviceCategory();
         cl.setNom(rs.getString("nom"));
         cl.setPrix(rs.getFloat("prix"));
         cl.setDescription(rs.getString("description"));
         cl.setQte(rs.getInt("qte"));
         cl.setDate_expiration(rs.getDate("date_expiration"));
         cl.setImage(rs.getString("image"));
         cl.setCategory(rs.getInt("cat_id"));
         String ch=sc.getLabelBy(rs.getInt("cat_id"));
         cl.setCat(ch);
         
         ll.add(cl);
     }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }

    public void updateProduct(Product cl, int id) throws SQLException {
   Statement stm=cnx.createStatement();
    String query="UPDATE `product` SET `nom`='"+cl.getNom()+"',`prix`='"+cl.getPrix()+"',`qte`='"+cl.getQte()+"',`description`='"+cl.getDescription()+"',`date_expiration`='"+cl.getDate_expiration()+"',`cat_id`='"+cl.getCategory()+"' WHERE `id`='"+id+"'";
    stm.executeUpdate(query);
    }
    public void pdf()
    {
        serviceCategory ck = new serviceCategory();
        try {
            String file_name ="C:/Users/Th3doc/Desktop/Produits.pdf";
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(file_name));
            } catch (DocumentException ex) {
                Logger.getLogger(ProduitBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            document.open();
            PreparedStatement pt = cnx.prepareStatement("select * from product");
            ResultSet rs = pt.executeQuery();
            Paragraph para1=new Paragraph("                                                                                    Liste des Produits :");
            document.add(para1);
            Paragraph ppp=new Paragraph("   ");
            document.add(ppp);
            LocalDate now=java.time.LocalDate.now();
            while (rs.next()) { 
               String k = ck.getLabelBy(rs.getInt(2));
                Paragraph para=new Paragraph("Produits [ ID : " + rs.getInt(1)+ ", nom: " +rs.getString(7) + ", prix : " + rs.getFloat(3) + ", quantite : " + rs.getInt(4)+ ", Description : " + rs.getString(5)+", Date expiration : " + rs.getString(6)+", Categorie : " + k +"]");
                document.add(para);
                document.add(new Paragraph(" "));
             

            }
            Paragraph para12=new Paragraph("Imprime le "+ now+".");
            document.add(para12);
            document.close();
        } catch (SQLException ex) {
            Logger.getLogger(serviceProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(serviceProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(serviceProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("PDF");
            alert.setHeaderText(null);
             alert.setContentText("Fichier PDF exporté avec succeés");
             alert.showAndWait();
    }
    @Override
    public void deleteProduct(int id) throws SQLException {
     Statement stm=cnx.createStatement();
        String query="DELETE FROM `product` WHERE `id`='"+id+"'";
        stm.executeUpdate(query); 
    }

    @Override
    public ObservableList<Product> readAll() throws SQLException {
    ObservableList<Product> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    ResultSet rst=stm.executeQuery("select id,nom,prix,description,qte,date_expiration,image,cat_id from product");
     while (rst.next()) {                
      Product cl=new Product();
         cl.setId(rst.getInt("id"));
         serviceCategory sc=new serviceCategory();
         cl.setNom(rst.getString("nom"));
         cl.setPrix(rst.getFloat("prix"));
         cl.setDescription(rst.getString("description"));
         cl.setQte(rst.getInt("qte"));
         cl.setDate_expiration(rst.getDate("date_expiration"));
         cl.setImage(rst.getString("image"));
         cl.setCategory(rst.getInt("cat_id"));
         String ch=sc.getLabelBy(rst.getInt("cat_id"));
         cl.setCat(ch);
         
         arr.add(cl);
     }
    return arr;
    }
    @Override
    public ObservableList<Product> triProByDate()throws SQLException {
          ObservableList<Product> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    ResultSet rst=stm.executeQuery("select id,nom,prix,description,qte,date_expiration,image,cat_id from product order by date_expiration");
     while (rst.next()) {                
      Product cl=new Product();
         cl.setId(rst.getInt("id"));
         serviceCategory sc=new serviceCategory();
         cl.setNom(rst.getString("nom"));
         cl.setPrix(rst.getFloat("prix"));
         cl.setDescription(rst.getString("description"));
         cl.setQte(rst.getInt("qte"));
         cl.setDate_expiration(rst.getDate("date_expiration"));
         cl.setImage(rst.getString("image"));
         cl.setCategory(rst.getInt("cat_id"));
         String ch=sc.getLabelBy(rst.getInt("cat_id"));
         cl.setCat(ch);
         
         arr.add(cl);
     }
 
    return arr;
    }
    public ObservableList<Product> triProByPrix()throws SQLException {
      ObservableList<Product> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    ResultSet rst=stm.executeQuery("select id,nom,prix,description,qte,date_expiration,image,cat_id from product order by prix");
     while (rst.next()) {                
      Product cl=new Product();
         cl.setId(rst.getInt("id"));
         serviceCategory sc=new serviceCategory();
         cl.setNom(rst.getString("nom"));
         cl.setPrix(rst.getFloat("prix"));
         cl.setDescription(rst.getString("description"));
         cl.setQte(rst.getInt("qte"));
         cl.setDate_expiration(rst.getDate("date_expiration"));
         cl.setImage(rst.getString("image"));
         cl.setCategory(rst.getInt("cat_id"));
         String ch=sc.getLabelBy(rst.getInt("cat_id"));
         cl.setCat(ch);
         
         arr.add(cl);
     }
    return arr;
    }
    public ObservableList<Product> triProByCat()throws SQLException {
          ObservableList<Product> arr;
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    ResultSet rst=stm.executeQuery("select id,nom,prix,description,qte,date_expiration,image,cat_id from product order by cat_id");
     while (rst.next()) {                
      Product cl=new Product();
         cl.setId(rst.getInt("id"));
         serviceCategory sc=new serviceCategory();
         cl.setNom(rst.getString("nom"));
         cl.setPrix(rst.getFloat("prix"));
         cl.setDescription(rst.getString("description"));
         cl.setQte(rst.getInt("qte"));
         cl.setDate_expiration(rst.getDate("date_expiration"));
         cl.setImage(rst.getString("image"));
         cl.setCategory(rst.getInt("cat_id"));
         String ch=sc.getLabelBy(rst.getInt("cat_id"));
         cl.setCat(ch);
         
         arr.add(cl);
     }
    return arr;
    }

    @Override
    public Product rechercherProduct(int id) throws SQLException {
    Statement stm=cnx.createStatement();
        String query="SELECT * FROM `product` WHERE `id`='"+id+"'";
        ResultSet rst=stm.executeQuery(query);
        Product cl=new Product();
        while(rst.next()){
        cl.setId(rst.getInt("id"));
        cl.setNom(rst.getString("nom"));
         cl.setPrix(rst.getFloat("prix"));
         cl.setDescription(rst.getString("description"));
         cl.setQte(rst.getInt("qte"));
         cl.setDate_expiration(rst.getDate("date_expiration"));
         cl.setImage(rst.getString("image"));
      
        }
        return cl;
    }

    public ObservableList<Product> rechercheProFilter(String k) throws SQLException {
   Statement stm=cnx.createStatement();
        String query="SELECT * FROM `product` WHERE CONCAT(`id`,`nom`) like '%"+k+"%' ";
        ResultSet rst=stm.executeQuery(query);
        ObservableList<Product> cl=FXCollections.observableArrayList();
        Product c=new Product();
        while(rst.next()){
        c.setId(rst.getInt("id"));
        c.setNom(rst.getString("nom"));
        c.setPrix(rst.getFloat("prix"));
         c.setDescription(rst.getString("description"));
         c.setQte(rst.getInt("qte"));
         c.setDate_expiration(rst.getDate("date_expiration"));
         c.setImage(rst.getString("image"));
        cl.add(c);
        }
        return cl; 
    }
     public ObservableList<Product> rechercheParPMN(Double k) throws SQLException {
   Statement stm=cnx.createStatement();
        String query="SELECT * FROM `product` WHERE `prix` > '"+k+"' ";
        ResultSet rst=stm.executeQuery(query);
        ObservableList<Product> cl=FXCollections.observableArrayList();
        Product c=new Product();
        while(rst.next()){
        c.setId(rst.getInt("id"));
        c.setNom(rst.getString("nom"));
        c.setPrix(rst.getFloat("prix"));
         c.setDescription(rst.getString("description"));
         c.setQte(rst.getInt("qte"));
         c.setDate_expiration(rst.getDate("date_expiration"));
         c.setImage(rst.getString("image"));
        cl.add(c);
        }
        return cl; 
    }

    @Override
    public String getNameBy(int ID) throws SQLException {
        Statement stm=cnx.createStatement();
        String query="SELECT nom FROM `product` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         String arr="";
        if(rst.next())
         arr=rst.getString("nom");
    return arr;
    }

    @Override
    public Float getPriceBy(int ID) throws SQLException {
        Statement stm=cnx.createStatement();
        String query="SELECT prix FROM `product` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         float arr=0;
        if(rst.next())
         arr=rst.getFloat("prix");
    return arr;
    }

    @Override
    public Date getDateBy(int ID) throws SQLException {
Statement stm=cnx.createStatement();
        String query="SELECT date_expiration FROM `product` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         java.sql.Date arr = null;
        if(rst.next())
         arr=rst.getDate("date_expiration");
    return arr;    }

    @Override
    public Integer getQteBy(int ID) throws SQLException {
Statement stm=cnx.createStatement();
        String query="SELECT qte FROM `product` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         int arr=0;
        if(rst.next())
         arr=rst.getInt("qte");
    return arr;    }

    @Override
    public String getDescBy(int ID) throws SQLException {
Statement stm=cnx.createStatement();
        String query="SELECT description FROM `product` WHERE `id`='"+ID+"'";
        ResultSet rst=stm.executeQuery(query);
     
      
         String arr="";
        if(rst.next())
         arr=rst.getString("description");
    return arr;    }

    @Override
    public ObservableList<Product> rechercheParPMX(Double k) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Product> readAllBC(String Cat) throws SQLException {
        ObservableList<Product> arr;
        serviceCategory ss=new serviceCategory();
        arr = FXCollections.observableArrayList();
    Statement stm=cnx.createStatement();
    int ID=ss.getIDBy(Cat);
    ResultSet rst=stm.executeQuery("select id,nom,prix,description,qte,date_expiration,image,cat_id from product where cat_id='"+ID+"' ");
     while (rst.next()) {                
      Product cl=new Product();
         cl.setId(rst.getInt("id"));
         serviceCategory sc=new serviceCategory();
         cl.setNom(rst.getString("nom"));
         cl.setPrix(rst.getFloat("prix"));
         cl.setDescription(rst.getString("description"));
         cl.setQte(rst.getInt("qte"));
         cl.setDate_expiration(rst.getDate("date_expiration"));
         cl.setImage(rst.getString("image"));
         cl.setCategory(rst.getInt("cat_id"));
         String ch=sc.getLabelBy(rst.getInt("cat_id"));
         cl.setCat(ch);
         
         arr.add(cl);
     }
    return arr;
    }

   
    
}
