/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.entities;

//import java.sql.Timestamp;

/**
 *
 * @author asus
 */
public class Evenement {
     int id ;
    String nomEvent;
    String adresse;
    String Type;
    float prix;
    int nbPlaces;
    String description;
    String Image;
    //Timestamp DateD;
    //Timestamp DateF;  

    public Evenement() {
    }

    public Evenement(int id, String nomEvent, String adresse, String Type, float prix, int nbPlaces, String description, String Image) {
        this.id = id;
        this.nomEvent = nomEvent;
        this.adresse = adresse;
        this.Type = Type;
        this.prix = prix;
        this.nbPlaces = nbPlaces;
        this.description = description;
        this.Image = Image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    
}
