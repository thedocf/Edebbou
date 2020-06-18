/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Commande;
import entite.LigneCommande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ConnexionBD;

/**
 *
 * @author 
 */
public class ServiceLigneCommande {
       private Connection con=ConnexionBD.getinstance().getCnx();
    
    public void ajouterLigneComande (LigneCommande l){
        try {
            Statement st = con.createStatement();
            String req = "INSERT INTO ligne_commande ( `qte`, `Id_commande`, `productid`) VALUES ("+l.getQuantite()+", "+l.getId_commande()+", "+l.getId_produit()+");";
            
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void afficherCommande (int id_user) {
        try {
            PreparedStatement pt =con.prepareStatement("select * from commande where id_u="+id_user);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                System.out.println("commande [id:"+rs.getInt(1)+" ,total: "+rs.getFloat(2)+",id_user: "+rs.getInt(3)+"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierCommande (int id,int livreur){
        try {
            PreparedStatement pt = con.prepareStatement("update commande set id_livreur= ? where id=?");
            pt.setInt(1,livreur);
            pt.setInt(2,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerCommande (LigneCommande lc) {
        try {
            PreparedStatement pt =con.prepareStatement("delete from commande where id = ?");
            pt.setInt(1, lc.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
