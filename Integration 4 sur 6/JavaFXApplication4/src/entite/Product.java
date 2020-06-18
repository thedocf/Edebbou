/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Th3Doc
 */
@Entity
public class Product {
    @Id
    private int id;
    private String nom;
    private String image;
     private int category;
     private String cat;

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
    public Product(int id, String nom, String image, int category, float prix, int qte, String description, Date date_expiration) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.category = category;
        this.prix = prix;
        this.qte = qte;
        this.description = description;
        this.date_expiration = date_expiration;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
    public Product(String nom, String image, float prix, int qte, String description, Date date_expiration) {
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.qte = qte;
        this.description = description;
        this.date_expiration = date_expiration;
    }

    public Product(int id, String nom, String image, float prix, int qte, String description, Date date_expiration) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.prix = prix;
        this.qte = qte;
        this.description = description;
        this.date_expiration = date_expiration;
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
    private java.sql.Date date_expiration; 

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
        return "Product{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", qte=" + qte + ", description=" + description + ", date_expiration=" + date_expiration + '}';
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

    public Date getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }
    public Product (){
        
    }
    

    public Product(int id, String nom, float prix, int qte, String description, Date date_expiration) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.qte = qte;
        this.description = description;
        this.date_expiration = date_expiration;
    }

    public Product(String nom, float prix, int qte, String description, Date date_expiration) {
        this.nom = nom;
        this.prix = prix;
        this.qte = qte;
        this.description = description;
        this.date_expiration = date_expiration;
    }
    public Product( float prix, int qte, String description, Date date_expiration) {
       
        this.prix = prix;
        this.qte = qte;
        this.description = description;
        this.date_expiration = date_expiration;
    }
    
}
