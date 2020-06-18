/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author jha
 */
public class LignePanier {
    
    private String nom;
    private float prix;
    private int qte;
    private Produit p;

    public Produit getP() {
        return p;
    }

    public void setP(Produit p) {
        this.p = p;
    }
    
    
    
    
    
 



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
    
    
    
}
