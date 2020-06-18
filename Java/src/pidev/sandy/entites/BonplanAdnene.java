/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.entites;

import java.sql.Time;

/**
 *
 * @author Alaa
 */
public class BonplanAdnene {
     int id , status,dislike,longitude,latitude,ref_compte,refcategorie,num_tel_local;
    String libelle ,desciption,image,couverture,addresse,email;
    Time overture,fermeture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getRef_compte() {
        return ref_compte;
    }

    public void setRef_compte(int ref_compte) {
        this.ref_compte = ref_compte;
    }

    public int getRefcategorie() {
        return refcategorie;
    }

    public void setRefcategorie(int refcategorie) {
        this.refcategorie = refcategorie;
    }

    public int getNum_tel_local() {
        return num_tel_local;
    }

    public void setNum_tel_local(int num_tel_local) {
        this.num_tel_local = num_tel_local;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCouverture() {
        return couverture;
    }

    public void setCouverture(String couverture) {
        this.couverture = couverture;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Time getOverture() {
        return overture;
    }

    public void setOverture(Time overture) {
        this.overture = overture;
    }

    public Time getFermeture() {
        return fermeture;
    }

    public void setFermeture(Time fermeture) {
        this.fermeture = fermeture;
    }

    @Override
    public String toString() {
        return "Bonplan{" + "id=" + id + ", status=" + status + ", dislike=" + dislike + ", longitude=" + longitude + ", latitude=" + latitude + ", ref_compte=" + ref_compte + ", refcategorie=" + refcategorie + ", num_tel_local=" + num_tel_local + ", libelle=" + libelle + ", desciption=" + desciption + ", image=" + image + ", couverture=" + couverture + ", addresse=" + addresse + ", email=" + email + ", overture=" + overture + ", fermeture=" + fermeture + '}';
    }
    
}
