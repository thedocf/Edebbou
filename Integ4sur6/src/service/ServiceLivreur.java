/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Commande;
import entite.Livreur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ConnexionBD;

/**
 *
 * @author jha
 */
public class ServiceLivreur {
    
    private Connection con = ConnexionBD.getinstance().getCnx();
    
        public void ajouterLivreur (Livreur l){
        try {
            Statement st = con.createStatement();
            String req = "INSERT INTO `livreur2` ( nom) VALUES ( '"+l.getNom()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public List<Livreur> afficherLivreur () {
        List<Livreur> ll = new ArrayList<Livreur>();
        try {
            PreparedStatement pt =con.prepareStatement("select * from livreur2");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                Livreur l = new Livreur();
                l.setId(rs.getInt(1));
                l.setNom(rs.getString(2));
                ll.add(l);
                System.out.println("livreur [id:"+rs.getInt(1)+" ,nom: "+rs.getString(2)+"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }
    public void modifierLivreur (int id,String nom){
        try {
            PreparedStatement pt = con.prepareStatement("update livreur2 set nom= ? where id=?");
            pt.setString(1,nom);
            pt.setInt(2,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerLivreur (int id) {
        try {
            PreparedStatement pt =con.prepareStatement("delete from livreur2 where id = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
