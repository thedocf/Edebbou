/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author jha
 */
public class Commande {
    private int id;
    private String date;
    private String etatLivraison;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    

    public String getEtatLivraison() {
        return etatLivraison;
    }

    public void setEtatLivraison(String etatLivraison) {
        this.etatLivraison = etatLivraison;
    }
    
    
    
}
