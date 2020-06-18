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
public class Boncadeaux {
    
    
    
 
int idBoncadeaux;
private  String type_bonBoncadeaux;
private  String  descriptionBoncadeaux;
private  String date_cadeaux;
private  Cadeaux  Cadeaux;
private Compte MembreConcerne;

    public Boncadeaux() {
    }

    public Boncadeaux(int idBoncadeaux, String type_bonBoncadeaux, String descriptionBoncadeaux, String date_cadeaux, Cadeaux Cadeaux, Compte MembreConcerne) {
        this.idBoncadeaux = idBoncadeaux;
        this.type_bonBoncadeaux = type_bonBoncadeaux;
        this.descriptionBoncadeaux = descriptionBoncadeaux;
        this.date_cadeaux = date_cadeaux;
        this.Cadeaux = Cadeaux;
        this.MembreConcerne = MembreConcerne;
    }
    public Boncadeaux(int idBoncadeaux, String type_bonBoncadeaux, String descriptionBoncadeaux, String date_cadeaux) {
        this.idBoncadeaux = idBoncadeaux;
        this.type_bonBoncadeaux = type_bonBoncadeaux;
        this.descriptionBoncadeaux = descriptionBoncadeaux;
        this.date_cadeaux = date_cadeaux;
    
    }

    public int getIdBoncadeaux() {
        return idBoncadeaux;
    }

    public void setIdBoncadeaux(int idBoncadeaux) {
        this.idBoncadeaux = idBoncadeaux;
    }

    public String getType_bonBoncadeaux() {
        return type_bonBoncadeaux;
    }

    public void setType_bonBoncadeaux(String type_bonBoncadeaux) {
        this.type_bonBoncadeaux = type_bonBoncadeaux;
    }

    public String getDescriptionBoncadeaux() {
        return descriptionBoncadeaux;
    }

    public void setDescriptionBoncadeaux(String descriptionBoncadeaux) {
        this.descriptionBoncadeaux = descriptionBoncadeaux;
    }

    public String getDate_cadeaux() {
        return date_cadeaux;
    }

    public void setDate_cadeaux(String date_cadeaux) {
        this.date_cadeaux = date_cadeaux;
    }

    public Cadeaux getCadeaux() {
        return Cadeaux;
    }
    
        public String getLibelleCadeaux() {
        return Cadeaux.getLibelle();
    }
        public float getValeurCadeaux() {
        return Cadeaux.getValeur_point();
    }
        public float getQuantiteCadeaux() {
        return Cadeaux.getQuantite_actuel();
    }

    public void setCadeaux(Cadeaux Cadeaux) {
        this.Cadeaux = Cadeaux;
    }

    public Compte getMembreConcerne() {
        return MembreConcerne;
    }
    
    public int getIdMembreConcerne() {
        return MembreConcerne.getId_user().getId();
    }
    
    

    public void setMembreConcerne(Compte MembreConcerne) {
        this.MembreConcerne = MembreConcerne;
    }

    @Override
    public String toString() {
        return "Boncadeaux{" + "idBoncadeaux=" + idBoncadeaux + ", type_bonBoncadeaux=" + type_bonBoncadeaux + ", descriptionBoncadeaux=" + descriptionBoncadeaux + ", date_cadeaux=" + date_cadeaux + ", Cadeaux=" + Cadeaux + ", MembreConcerne=" + MembreConcerne + '}';
    }


    
    
}
