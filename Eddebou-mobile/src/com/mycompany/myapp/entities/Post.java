/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Fida
 */
public class Post {
   private String titre;
private Integer id;

    
   int createur;
    String photo;
    String description;
public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Post() {
    }

    public Post(int createur, String description,String photo, String titre) {
        this.createur = createur;
         this.description=description;
          this.photo=photo;
        this.titre = titre;
       
         
       
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

  
  

    public int getCreateur() {
        return createur;
    }

    public void setCreateur(int createur) {
        this.createur = createur;
    }
   

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

   

    @Override
    public String toString() {
        return "Task{" + "auteur=" + createur + ", nom=" + titre + '}';
    }
           
}
