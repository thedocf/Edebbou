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
public class reclamation {
    
    private int id;
    private String title;
    private String description;
    private  String photo ;

    public reclamation(int id, String title, String description, String photo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", title=" + title + ", description=" + description + ", photo=" + photo + '}';
    }

 

    public reclamation() {
       
    }

    public int getIdRef() {
        return id;
    }

    public void setIdRef(int id) {
        this.id = id;
    }


 
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
