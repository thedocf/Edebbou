/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author M-YAHYAOUI
 */
public class depot {
    
    private int id;
    private String entreprise;
    private int surface;
    private String ville;
    private int capacite;
    private String description;
    private  String photo ;

    public depot(int id, String entreprise, int surface, String ville, int capacite, String description, String photo) {
        this.id = id;
        this.entreprise = entreprise;
        this.surface = surface;
        this.ville = ville;
        this.capacite = capacite;
        this.description = description;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "depot{" + "id=" + id + ", entreprise=" + entreprise + ", surface=" + surface + ", ville=" + ville + ", capacite=" + capacite + ", description=" + description + ", photo=" + photo + '}';
    }

 

    public depot() {
       
    }

    public int getIdRef() {
        return id;
    }

    public void setIdRef(int id) {
        this.id = id;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

   
    
}
