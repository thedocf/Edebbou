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
public class LigneCommande {
    private int id ;
    private int quantite;
    private int id_commande;
    private int id_produit;

    public LigneCommande() {
    }

    public LigneCommande(int quantite, int id_commande, int id_produit) {
        this.quantite = quantite;
        this.id_commande = id_commande;
        this.id_produit = id_produit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    @Override
    public String toString() {
        return "LigneCommmande{" + "id=" + id + ", quantite=" + quantite + ", id_commande=" + id_commande + ", id_produit=" + id_produit + '}';
    }
    
    
}
