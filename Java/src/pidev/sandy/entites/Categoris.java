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
public class Categoris {
    int id;
    private  Categoris idcategoriemere;
    private  String libelle;
    private  String discription;
    private  String image;
  //public static Categoris currCategorie=new Categoris();
    public Categoris() {
    }
    
 
    public Categoris(Categoris idcategoriemere, String libelle, String discription, String image) {
        this.idcategoriemere = idcategoriemere;
        this.libelle = libelle;
        this.discription = discription;
        this.image = image;
    }

    public Categoris(int id, Categoris idcategoriemere, String libelle, String discription, String image) {
        this.id = id;
        this.idcategoriemere = idcategoriemere;
        this.libelle = libelle;
        this.discription = discription;
        this.image = image;
    }
   public void setIdcategoriemere(String libelle) {
    this.libelle =libelle;
    }
    
    public Categoris(int id, String categ, String libelle, String description, String image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      public Categoris(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  libelle ;
    }

    public Categoris getIdcategoriemere() {
        return idcategoriemere;
    }

    public void setIdcategoriemere(Categoris idcategoriemere) {
        this.idcategoriemere = idcategoriemere;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    
 
 

  

  

  

   

     
   
  
  
    
    
    
    
    
}
