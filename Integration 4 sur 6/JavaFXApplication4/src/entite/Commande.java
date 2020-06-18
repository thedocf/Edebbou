/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import javafx.scene.control.Button;

/**
 *
 * @author ikbel
 */
public class Commande {
    private int id;
    private String date;
    private int user;
    private int livreur;
    private String etat;
    private Button b;
    public Commande() {
        b = new Button("delete");
    }

    public Button getB() {
        return b;
    }

    public void setB(Button b) {
        this.b = b;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    

    public int getLivreur() {
        return livreur;
    }

    public void setLivreur(int total) {
        this.livreur = total;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public int getUser() {
        return user;
    }

    public void setUser(int id_user) {
        this.user = id_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Commande(String date, String etat) {
        
        this.date = date;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", date=" + date + ", user=" + user + ", livreur=" + livreur + '}';
    }
    
    

    
    
}
