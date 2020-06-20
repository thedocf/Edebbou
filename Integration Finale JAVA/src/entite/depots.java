/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;



/**
 *
 * @author user
 */
public class depots {
    private int id;
    private String entreprise;
    private int surface;
    private String ville;
    private int capacite;
    private String description;
    private static String photo ;
    
    
    
    
    

    public depots(int id, String entreprise, int surface, String ville, String description) {
        this.id = id;
        this.entreprise = entreprise;
        this.surface = surface;
        this.ville = ville;
        this.capacite = capacite;
        this.description = description;

    }
    
    
    public depots(int id, String entreprise, int surface, String ville, String description, String photo, Image image) {
        this.id = id;
        this.entreprise = entreprise;
        this.surface = surface;
        this.ville = ville;
        this.capacite = capacite;
        this.description = description;
        this.photo = photo;
        this.image = image;
        
    }
    
     public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
        try {
            BufferedImage bf = ImageIO.read(new File("C:\\Users\\M-YAHYAOUI\\Desktop\\photo\\" + photo));
            BufferedImage bf1 = Scalr.resize(bf, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
                    250, 100, Scalr.OP_ANTIALIAS);
            image = SwingFXUtils.toFXImage(bf1, null);
        } catch (IOException ex) {
            // NO PHOTO A AJOUTER
            //System.out.println("entities.AnnounceRep.setUrlPhoto()");;
        }
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
        Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    
   
    public depots() {      
    }

    public int getId() {
        return id;
    }

    public void setIdRef(int id) {
        this.id = id;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


    @Override
    public String toString() {
        return "depots{" + "id=" + id + ", entreprise=" + entreprise + ", surface=" + surface + ", ville=" + ville + ", capacite=" + capacite + ", description=" + description + '}';
    }



    
}
