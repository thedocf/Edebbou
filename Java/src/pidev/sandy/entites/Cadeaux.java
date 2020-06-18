/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.entites;

/**
 *
 * @author Alaa
 */
public class Cadeaux {
    
    
    
    
int id;
private String libelle;
private String description;
private float prix_reel;
private float valeur_point;
private int quantite_initial;
private int  quantite_actuel;
private String image;
private Categoris categorisCadeaux;

    public Cadeaux(int id, String libelle, String description, float prix_reel, float valeur_point, int quantite_initial, int quantite_actuel, String image, Categoris categorisCadeaux) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.prix_reel = prix_reel;
        this.valeur_point = valeur_point;
        this.quantite_initial = quantite_initial;
        this.quantite_actuel = quantite_actuel;
        this.image = image;
        this.categorisCadeaux = categorisCadeaux;
    }

    public Cadeaux(int id, String libelle, String description, float prix_reel, float valeur_point, int quantite_initial, int quantite_actuel, String image) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.prix_reel = prix_reel;
        this.valeur_point = valeur_point;
        this.quantite_initial = quantite_initial;
        this.quantite_actuel = quantite_actuel;
        this.image = image;
    }
    
    
    
    



    public Cadeaux() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix_reel() {
        return prix_reel;
    }

    public void setPrix_reel(float prix_reel) {
        this.prix_reel = prix_reel;
    }

    public float getValeur_point() {
        return valeur_point;
    }

    public void setValeur_point(float valeur_point) {
        this.valeur_point = valeur_point;
    }

    public int getQuantite_initial() {
        return quantite_initial;
    }

    public void setQuantite_initial(int quantite_initial) {
        this.quantite_initial = quantite_initial;
    }

    public int getQuantite_actuel() {
        return quantite_actuel;
    }

    public void setQuantite_actuel(int quantite_actuel) {
        this.quantite_actuel = quantite_actuel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Categoris getCategorisCadeaux() {
        return categorisCadeaux;
    }

    public void setCategorisCadeaux(Categoris categorisCadeaux) {
        this.categorisCadeaux = categorisCadeaux;
    }

    @Override
    public String toString() {
        return "Cadeaux{" + "id=" + id + ", libelle=" + libelle + ", description=" + description + ", prix_reel=" + prix_reel + ", valeur_point=" + valeur_point + ", quantite_initial=" + quantite_initial + ", quantite_actuel=" + quantite_actuel + ", image=" + image + ", categorisCadeaux=" + categorisCadeaux + '}';
    }
    

    
}
