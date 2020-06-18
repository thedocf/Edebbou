/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author user
 */
public class fournisseurs {
    private int id;
    private int depot_id;
    private String nom;
    private String prenom;
    private int numTel;
    private String disponible;
    
    

    public fournisseurs(int id,int depot_id, String nom, String prenom, int numTel, String disponible) {
        this.id = id;
        this.depot_id = depot_id ;
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.disponible = disponible;
    }

    public int getDepot_id() {
        return depot_id;
    }

    public void setDepot_id(int depot_id) {
        this.depot_id = depot_id;
    }

   
    public fournisseurs() {      
    }

    public int getIdFour() {
        return id;
    }

    public void setIdFour(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "refugies{" + "idFour=" + id + "depot_id="+ depot_id +", nom=" + nom + ", prenom=" + prenom + ", numtel=" + numTel + ", disponible=" + disponible + '}';
    }


    
}
