/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;



/**
 *
 * @author Th3Doc
 */
public class Product {
    
    private int id;
    private String nom;
    private String image;
     private int category;
     private String cat;
     int like;
     int unlike;

    public Product(int id, String nom, String image, int category, String cat, int like, int unlike, float prix, int qte, String description) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.category = category;
        this.cat = cat;
        this.like = like;
        this.unlike = unlike;
        this.prix = prix;
        this.qte = qte;
        this.description = description;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getUnlike() {
        return unlike;
    }

    public void setUnlike(int unlike) {
        this.unlike = unlike;
    }
     //private LocalDate date_expiration;
    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
    public Product(int id, String nom, String image, int category, float prix, int qte, String description) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.category = category;
        this.prix = prix;
        this.qte = qte;
        this.description = description;
        //this.date_expiration = date_expiration;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
    public Product(String nom, String image, float prix, int qte, String description) {
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.qte = qte;
        this.description = description;
        //this.date_expiration = date_expiration;
    }

    public Product(int id, String nom, String image, float prix, int qte, String description) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.qte = qte;
        this.description = description;
       // this.date_expiration = date_expiration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    private float prix;
    private int qte;
    private String description;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", qte=" + qte + ", description=" + description + '}';
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   /* public LocalDate getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(LocalDate date_expiration) {
        this.date_expiration = date_expiration;
    }*/
    public Product (){
        
    }
    

    public Product(int id, String nom, float prix, int qte, String description) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.qte = qte;
        this.description = description;
        //this.date_expiration = date_expiration;
    }

    public Product(String nom, float prix, int qte, String description) {
        this.nom = nom;
        this.prix = prix;
        this.qte = qte;
        this.description = description;
       // this.date_expiration = date_expiration;
    }
    public Product( float prix, int qte, String description) {
       
        this.prix = prix;
        this.qte = qte;
        this.description = description;
        //this.date_expiration = date_expiration;
    }
    
}
