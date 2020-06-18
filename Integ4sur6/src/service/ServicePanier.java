/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Commande;
import entite.LigneCommande;
import entite.LignePanier;
import entite.Produit;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.awt.AWTException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import util.SMTP;

/**
 *
 * @author 
 */
public class ServicePanier {
    public static Map<Produit,Integer> panier;
    public void ajouterProduit(Produit idproduit, int qte) {
        if (panier.containsKey(idproduit)) {
            panier.put(idproduit, panier.get(idproduit) + qte);
        } else {
            panier.put(idproduit, qte);
        }
    }

    public void supprimerProduit(Produit produit, int qte) {
        if (panier.containsKey(produit)) {
            if (panier.get(produit) > qte) {
                panier.put(produit, panier.get(produit) - qte);
            } else {
                panier.remove(produit);
            }
        }
    }
    
    public List<LignePanier> getPanier(){
        List<LignePanier> lp = new ArrayList<LignePanier>();
        for (Map.Entry<Produit, Integer> entry : panier.entrySet()) {
            Produit p = entry.getKey();
            LignePanier llp = new LignePanier();
            llp.setNom(p.getNom());
            llp.setPrix(p.getPrix());
            llp.setQte(entry.getValue());
            llp.setP(p);
            
            
            lp.add(llp);
        }
        return lp;
    }
    
    public String total(){
    float total = 0;
    
    
        for (Map.Entry<Produit, Integer> entry : panier.entrySet()) {
            total += entry.getValue() * entry.getKey().getPrix();
        }
    
    return String.valueOf(total);
    }

    public void checkout( int livreur, boolean livraison,int login) throws AWTException, Exception {

        Commande c = new Commande();
        ServiceCommande sc = new ServiceCommande();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        c.setDate(sdf.format(cal.getTime()));
        
        c.setUser(login);
        c.setLivreur(livreur);
        sc.ajouterComande(c);
        int idc = sc.derniereCommande();
        
        ServiceLigneCommande slc = new ServiceLigneCommande();

        for (Map.Entry<Produit, Integer> entry : panier.entrySet()) {

            LigneCommande lc = new LigneCommande();
            lc.setId_commande(idc);
            Produit p = entry.getKey();
            lc.setId_produit(p.getId());
            lc.setQuantite(entry.getValue());
            slc.ajouterLigneComande(lc);
            panier = new HashMap<Produit,Integer>();
            
            //System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
        }
        
        SMTP.sendMail("ikbel.khemiri@esprit.tn","Livraison","Vous avez une nouvelle commande");

    }
    public void setProduit(Produit idproduit, int qte) {
        if (panier.containsKey(idproduit)) {
            panier.put(idproduit,qte);
        } else {
            panier.put(idproduit, qte);
        }
    }

 


}
