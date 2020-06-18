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
public class avisbonplan {
    int id; Double rating;
   User refavis;
   String titre, commentaire ;
   BonPlan bn;

    public avisbonplan() {
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public User getRefavis() {
        return refavis;
    }

    public void setRefavis(User refavis) {
        this.refavis = refavis;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public BonPlan getBn() {
        return bn;
    }

    public void setBn(BonPlan bn) {
        this.bn = bn;
    }

    public avisbonplan(int id, Double rating, User refavis, String titre, String commentaire, BonPlan bn) {
        this.id = id;
        this.rating = rating;
        this.refavis = refavis;
        this.titre = titre;
        this.commentaire = commentaire;
        this.bn = bn;
    }
    public avisbonplan( Double rating, User refavis, String titre, String commentaire, BonPlan bn) {
        this.id = id;
        this.rating = rating;
        this.refavis = refavis;
        this.titre = titre;
        this.commentaire = commentaire;
        this.bn = bn;
    }

    public void setRefavis(BonPlan b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setRefavis(String categ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "avisbonplan{" + "refavis=" + refavis + '}';
    }

   
    
}
