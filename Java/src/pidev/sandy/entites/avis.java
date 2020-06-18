/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.entites;


import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author SLIMEN
 */
public class avis {
    private int id;
    private int ref_experiencce;
    private int ref_compte;
    private int rating;
    private String nom;
    private String url_image;
    private String imageuser;
    private Date datecreation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRef_experiencce() {
        return ref_experiencce;
    }

    public void setRef_experiencce(int ref_experiencce) {
        this.ref_experiencce = ref_experiencce;
    }

    public int getRef_compte() {
        return ref_compte;
    }

    public void setRef_compte(int ref_compte) {
        this.ref_compte = ref_compte;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getImageuser() {
        return imageuser;
    }

    public void setImageuser(String imageuser) {
        this.imageuser = imageuser;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.nom);
        hash = 53 * hash + Objects.hashCode(this.imageuser);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final avis other = (avis) obj;
        if (this.rating != other.rating) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.datecreation, other.datecreation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "avis{" + "id=" + id + ", ref_experiencce=" + ref_experiencce + ", ref_compte=" + ref_compte + ", rating=" + rating + ", nom=" + nom + ", url_image=" + url_image + ", imageuser=" + imageuser + ", datecreation=" + datecreation + '}';
    }

    public avis() {
    }

    public avis(int id, int ref_experiencce, int ref_compte, int rating, String nom, String url_image, String imageuser, Date datecreation) {
        this.id = id;
        this.ref_experiencce = ref_experiencce;
        this.ref_compte = ref_compte;
        this.rating = rating;
        this.nom = nom;
        this.url_image = url_image;
        this.imageuser = imageuser;
        this.datecreation = datecreation;
    }
    
    
    
    
}
