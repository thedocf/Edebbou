/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;
import javafx.scene.image.ImageView;
import entite.Fos_user;
/**
 *
 * @author Fida
 */
public class Anonnce {
    private int id;

    private Integer nbrrating;
  
    private Fos_user user;
    private String description;
    private String title;
     private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    private Integer rating;
    private String image;
    private ImageView photo;
    
    private String a;

    public Anonnce(int id, String description, String title, String image, ImageView photo, String a, Date Date) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.image = image;
        this.photo = photo;
        this.a = a;
        this.Date = Date;
    }

    public Anonnce(int id,  String description, String title, Integer rating, String image, ImageView photo, String a, Date Date) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.rating = rating;
        this.image = image;
        this.photo = photo;
        this.a = a;
        this.Date = Date;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
    private Date Date;

    public Anonnce(Fos_user id_user, String description, String title, String image) {
        this.user = id_user;
        this.description = description;
        this.title = title;
        this.image = image;
      
    }

    public Anonnce(Fos_user user, String description, String title, String type, String image) {
        this.user = user;
        this.description = description;
        this.title = title;
        this.type = type;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Anonnce(int id, Fos_user id_user, String description, String title, String image, ImageView photo, Date Date) {
        this.id = id;
        this.user = id_user;
        this.description = description;
        this.title = title;
        this.image = image;
        this.photo = photo;
        this.Date = Date;
    }

    public Anonnce() {
    }
    

    public Anonnce(int id,Fos_user id_user, String description, String title, ImageView photo, Date Date) {
        this.id = id;
        this.user = id_user;
        this.description = description;
        this.title = title;
        this.photo = photo;
        this.Date = Date;
    }
 public Anonnce(Fos_user id_user, String description, String title, ImageView photo) {
 
        this.user = id_user;
        this.description = description;
        this.title = title;
        this.photo = photo;
        
    }
   public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fos_user getId_user() {
        return user;
    }

    public void setId_user(Fos_user id_user) {
        this.user = id_user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return Date;
    }

    public Anonnce(String description, String title, String image) {
        this.description = description;
        this.title = title;
        this.image = image;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public Integer getNbrrating() {
        return nbrrating;
    }

    public void setNbrrating(Integer nbrrating) {
        this.nbrrating = nbrrating;
    }

    public Object getDateDebut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
