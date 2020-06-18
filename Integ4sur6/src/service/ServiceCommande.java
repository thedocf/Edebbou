/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

//import com.itextpdf.io.font.constants.StandardFonts;
//import com.itextpdf.kernel.colors.DeviceRgb;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entite.Commande;
import entite.LignePanier;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.ConnexionBD;

/**
 *
 * @author 
 */
public class ServiceCommande {
      private Connection con=ConnexionBD.getinstance().getCnx();
    
    public void ajouterComande (Commande l){
        try {
            Statement st = con.createStatement();
            String req = "INSERT INTO `commande` ( id_u, date) VALUES ( "+l.getUser()+", '2020-02-18')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//7ell l base
    
    public int derniereCommande(){
            int c = 0;
        try {
            PreparedStatement pt =con.prepareStatement("select * from commande order by id desc limit 1");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                c = rs.getInt(1);
//System.out.println("commande [id:"+rs.getInt(1)+" ,date: "+rs.getString(2)+",id_user: "+rs.getInt(2)+"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }        
            return c;
    }
    
    public List<Commande> afficherCommande (int id_user) {
        List<Commande> array=new ArrayList<Commande>() ;
            try {
            PreparedStatement pt =con.prepareStatement("SELECT `id`,`id_u`,`id_livreur`,`date`,`etatLivraison` FROM `commande`  where id_u = "+id_user);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                Commande c  = new Commande();
                c.setId(rs.getInt(1));
                c.setDate(rs.getString(4));
                c.setEtat(rs.getString(5));
                c.setLivreur(rs.getInt(3));
                array.add(c);
                
              
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
     public List<Commande> afficherAllCommande () {
        List<Commande> array=new ArrayList<Commande>() ;
            try {
            PreparedStatement pt =con.prepareStatement("SELECT `id`,`id_u`,`id_livreur`,`date`,`etatLivraison` FROM `commande`  ");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                Commande c  = new Commande();
                c.setId(rs.getInt(1));
                c.setDate(rs.getString(4));
                c.setEtat(rs.getString(5));
                c.setLivreur(rs.getInt(3));
                array.add(c);
                
              
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    public List<Commande> afficherCommandePdf () {
        List<Commande> array=new ArrayList<Commande>() ;
            try {
            PreparedStatement pt =con.prepareStatement("SELECT `id`,`id_u`,`id_livreur`,`date`,`etatLivraison` FROM `commande` ");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                Commande c  = new Commande();
                c.setId(rs.getInt(1));
                c.setDate(rs.getString(4));
                c.setEtat(rs.getString(5));
                c.setLivreur(rs.getInt(3));
                array.add(c);
                
              
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    public void modifierCommande (int id,int idl, String etat){
        try {
            PreparedStatement pt = con.prepareStatement("update commande set id_livreur = ?, etatLivraison = ? where id=?");
            pt.setInt(1,idl);
            pt.setString(2,etat);
            pt.setInt(3,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerCommande (Commande c) {
        try {
            PreparedStatement pt =con.prepareStatement("delete from commande where id = ?");
            pt.setInt(1, c.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public ArrayList<Commande> getCommande (int user) {
         ArrayList<Commande> array=new ArrayList();
        try {
            PreparedStatement pt =con.prepareStatement("select * from commande where id_u="+user);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
              //  System.out.println("commande [id:"+rs.getInt(1)+" ,date: "+rs.getString(2)+",id_user: "+rs.getInt(2)+"]");
              Commande c=new Commande();
              c.setDate(rs.getString(3));
            c.setLivreur(rs.getInt(4));
            c.setId(rs.getInt(1));
            array.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
        public Table generateFournisseurPdfTable() {
            Table table = new Table(new float[3]).useAllAvailableWidth();
         table.setMarginTop(5);
        table.setMarginBottom(5);
        table.setMarginRight(5);
        table.setMarginLeft(5);
        
            Cell cell = new Cell(1, 7).add(new Paragraph("Facture").setFontSize(14).setDestination("LinkCommande"));
        cell.setTextAlignment(TextAlignment.CENTER);
        cell.setPadding(5);
        
        table.addCell(cell);
        table.setTextAlignment(TextAlignment.CENTER);
        
        table.addCell(new Cell(1, 1).add(new Paragraph("Id Commande ")));
        table.addCell(new Cell(1, 1).add(new Paragraph("Date")));
        table.addCell(new Cell(1, 1).add(new Paragraph("etat Livraison")));
       
          ArrayList<Commande> ListCommande=null ;
        
            ListCommande = new ArrayList(this.afficherCommandePdf());
       
      
       for (Commande cmd : ListCommande) {
            table.addCell(Integer.toString(cmd.getId()));
            table.addCell(cmd.getDate());
            table.addCell(cmd.getEtat());
            
           
           
           
       }
      
      
      
      
      
      return table;
      
      }

        
        
            private static String FILE = "C:/Users/Th3Doc/Desktop/facture.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
        
        public void getFacture(int idc){
            
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addContent(document,idc);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
        
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/Commande.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
        public static void main(String[] args) {
		launch(args);
	}
        
        

        
        private void addContent(Document subCatPart,int idc)
            throws BadElementException, DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.addCell("Produit");
        table.addCell("Prix");
        table.addCell("Quantité");
            try {
            PreparedStatement pt =con.prepareStatement("SELECT nom,prix,ligne_commande.qte FROM ligne_commande join product on productId = product.id where id_commande =  "+idc);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                table.addCell(rs.getString(1));
                table.addCell(rs.getString(2));
                table.addCell(rs.getString(3));
                
              
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        subCatPart.add(table);

    }
          public List<Commande> getTrierEtat() throws SQLException {
    List<Commande> arr=new ArrayList<>();
    Statement ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from commande ORDER BY etatLivraison DESC");
     while (rs.next()) {                
                
               String d=rs.getString(4);
               String etat=rs.getString(5);
              
               Commande p=new Commande(d, etat);
     arr.add(p);
     }
     
    System.out.println("trié avec succés ");
    return arr;
    }
          
          public List<Commande> getTrierDate() throws SQLException {
    List<Commande> arr=new ArrayList<>();
    Statement ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from commande ORDER BY date ASC");
     while (rs.next()) {                
                
               String d=rs.getString(4);
               String etat=rs.getString(5);
              
               Commande p=new Commande(d, etat);
     arr.add(p);
     }
     
    System.out.println("trié avec succés ");
    return arr;
    }


}