/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.entites;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author SLIMEN
 */
public class demande_experience {
    private int id;
    private int iduser;
    private String nom;
    private String descripion;
    private String addresse;
    private String image;
    private Date datecreation;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.nom);
        hash = 23 * hash + Objects.hashCode(this.addresse);
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
        final demande_experience other = (demande_experience) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.iduser != other.iduser) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.descripion, other.descripion)) {
            return false;
        }
        if (!Objects.equals(this.addresse, other.addresse)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.datecreation, other.datecreation)) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    @Override
    public String toString() {
        return "demande_experience{" + "id=" + id + ", iduser=" + iduser + ", nom=" + nom + ", descripion=" + descripion + ", addresse=" + addresse + ", image=" + image + ", datecreation=" + datecreation + '}';
    }

    public demande_experience() {
    }

    public demande_experience(int id, int iduser, String nom, String descripion, String addresse, String image, Date datecreation) {
        this.id = id;
        this.iduser = iduser;
        this.nom = nom;
        this.descripion = descripion;
        this.addresse = addresse;
        this.image = image;
        this.datecreation = datecreation;
    }
    
}
