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
public class offre_experience {
    private int id;
    private int ref_compte;
    private String nom;
    private String description;
    private String url_image;
    private String addrese;
    private int avis_id;
    private int region_id;
    private int rating;
    private int catid;
    private int nbravis;
    private int nbrjaime;
    private Date datecreation;
    private Boolean climatisation;
    private Boolean wifi;
    private Boolean snackbar;
    private Boolean parking;
    private Boolean piscine;
    private Boolean familiale;
    private Boolean paiementparcarte;
    private Boolean balcon;
    private Boolean visites;
    private Boolean fumer;
    private Boolean reservations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRef_compte() {
        return ref_compte;
    }

    public void setRef_compte(int ref_compte) {
        this.ref_compte = ref_compte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getAddrese() {
        return addrese;
    }

    public void setAddrese(String addrese) {
        this.addrese = addrese;
    }

    public int getAvis_id() {
        return avis_id;
    }

    public void setAvis_id(int avis_id) {
        this.avis_id = avis_id;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public int getNbravis() {
        return nbravis;
    }

    public void setNbravis(int nbravis) {
        this.nbravis = nbravis;
    }

    public int getNbrjaime() {
        return nbrjaime;
    }

    public void setNbrjaime(int nbrjaime) {
        this.nbrjaime = nbrjaime;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Boolean getClimatisation() {
        return climatisation;
    }

    public void setClimatisation(Boolean climatisation) {
        this.climatisation = climatisation;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getSnackbar() {
        return snackbar;
    }

    public void setSnackbar(Boolean snackbar) {
        this.snackbar = snackbar;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean getPiscine() {
        return piscine;
    }

    public void setPiscine(Boolean piscine) {
        this.piscine = piscine;
    }

    public Boolean getFamiliale() {
        return familiale;
    }

    public void setFamiliale(Boolean familiale) {
        this.familiale = familiale;
    }

    public Boolean getPaiementparcarte() {
        return paiementparcarte;
    }

    public void setPaiementparcarte(Boolean paiementparcarte) {
        this.paiementparcarte = paiementparcarte;
    }

    public Boolean getBalcon() {
        return balcon;
    }

    public void setBalcon(Boolean balcon) {
        this.balcon = balcon;
    }

    public Boolean getVisites() {
        return visites;
    }

    public void setVisites(Boolean visites) {
        this.visites = visites;
    }

    public Boolean getFumer() {
        return fumer;
    }

    public void setFumer(Boolean fumer) {
        this.fumer = fumer;
    }

    public Boolean getReservations() {
        return reservations;
    }

    public void setReservations(Boolean reservations) {
        this.reservations = reservations;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.id;
        hash = 61 * hash + this.ref_compte;
        hash = 61 * hash + Objects.hashCode(this.nom);
        hash = 61 * hash + Objects.hashCode(this.description);
        hash = 61 * hash + Objects.hashCode(this.addrese);
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
        final offre_experience other = (offre_experience) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.addrese, other.addrese)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "offre_experience{" + "id=" + id + ", ref_compte=" + ref_compte + ", nom=" + nom + ", description=" + description + ", url_image=" + url_image + ", addrese=" + addrese + ", avis_id=" + avis_id + ", region_id=" + region_id + ", rating=" + rating + ", catid=" + catid + ", nbravis=" + nbravis + ", nbrjaime=" + nbrjaime + ", datecreation=" + datecreation + ", climatisation=" + climatisation + ", wifi=" + wifi + ", snackbar=" + snackbar + ", parking=" + parking + ", piscine=" + piscine + ", familiale=" + familiale + ", paiementparcarte=" + paiementparcarte + ", balcon=" + balcon + ", visites=" + visites + ", fumer=" + fumer + ", reservations=" + reservations + '}';
    }

    public offre_experience() {
    }

    public offre_experience(int id, int ref_compte, String nom, String description, String url_image, String addrese, int avis_id, int region_id, int rating, int catid, int nbravis, int nbrjaime, Date datecreation, Boolean climatisation, Boolean wifi, Boolean snackbar, Boolean parking, Boolean piscine, Boolean familiale, Boolean paiementparcarte, Boolean balcon, Boolean visites, Boolean fumer, Boolean reservations) {
        this.id = id;
        this.ref_compte = ref_compte;
        this.nom = nom;
        this.description = description;
        this.url_image = url_image;
        this.addrese = addrese;
        this.avis_id = avis_id;
        this.region_id = region_id;
        this.rating = rating;
        this.catid = catid;
        this.nbravis = nbravis;
        this.nbrjaime = nbrjaime;
        this.datecreation = datecreation;
        this.climatisation = climatisation;
        this.wifi = wifi;
        this.snackbar = snackbar;
        this.parking = parking;
        this.piscine = piscine;
        this.familiale = familiale;
        this.paiementparcarte = paiementparcarte;
        this.balcon = balcon;
        this.visites = visites;
        this.fumer = fumer;
        this.reservations = reservations;
    }
    
    
    
}
