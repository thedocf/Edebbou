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
public  class DemandeCadeau {
    
    int idDemandeCadeaux;
private  String statusDemandeCadeaux;
private  String  descriptionDemandeCadeaux;
private  String datedemandeDemandeCadeaux;
private  String  messageDemandeCadeaux;
private  String  datedexpirationDemandeCadeaux;
private  Compte  MembreDemande;
private  Cadeaux  Cadeaux;

    public DemandeCadeau(int idDemandeCadeaux, String statusDemandeCadeaux, String descriptionDemandeCadeaux, String datedemandeDemandeCadeaux, String messageDemandeCadeaux, String datedexpirationDemandeCadeaux, Compte MembreDemande, Cadeaux Cadeaux) {
        this.idDemandeCadeaux = idDemandeCadeaux;
        this.statusDemandeCadeaux = statusDemandeCadeaux;
        this.descriptionDemandeCadeaux = descriptionDemandeCadeaux;
        this.datedemandeDemandeCadeaux = datedemandeDemandeCadeaux;
        this.messageDemandeCadeaux = messageDemandeCadeaux;
        this.datedexpirationDemandeCadeaux = datedexpirationDemandeCadeaux;
        this.MembreDemande = MembreDemande;
        this.Cadeaux = Cadeaux;
    }

    public DemandeCadeau(int idDemandeCadeaux, String statusDemandeCadeaux, String descriptionDemandeCadeaux, String datedemandeDemandeCadeaux, String messageDemandeCadeaux, String datedexpirationDemandeCadeaux) {
        this.idDemandeCadeaux = idDemandeCadeaux;
        this.statusDemandeCadeaux = statusDemandeCadeaux;
        this.descriptionDemandeCadeaux = descriptionDemandeCadeaux;
        this.datedemandeDemandeCadeaux = datedemandeDemandeCadeaux;
        this.messageDemandeCadeaux = messageDemandeCadeaux;
        this.datedexpirationDemandeCadeaux = datedexpirationDemandeCadeaux;
    }

  
    
    

    public DemandeCadeau() {
    }

    public int getIdDemandeCadeaux() {
        return idDemandeCadeaux;
    }

    public void setIdDemandeCadeaux(int idDemandeCadeaux) {
        this.idDemandeCadeaux = idDemandeCadeaux;
    }

    public String getStatusDemandeCadeaux() {
        return statusDemandeCadeaux;
    }

    public void setStatusDemandeCadeaux(String statusDemandeCadeaux) {
        this.statusDemandeCadeaux = statusDemandeCadeaux;
    }

    public String getDescriptionDemandeCadeaux() {
        return descriptionDemandeCadeaux;
    }

    public void setDescriptionDemandeCadeaux(String descriptionDemandeCadeaux) {
        this.descriptionDemandeCadeaux = descriptionDemandeCadeaux;
    }

    public String getDatedemandeDemandeCadeaux() {
        return datedemandeDemandeCadeaux;
    }

    public void setDatedemandeDemandeCadeaux(String datedemandeDemandeCadeaux) {
        this.datedemandeDemandeCadeaux = datedemandeDemandeCadeaux;
    }

    public String getMessageDemandeCadeaux() {
        return messageDemandeCadeaux;
    }

    public void setMessageDemandeCadeaux(String messageDemandeCadeaux) {
        this.messageDemandeCadeaux = messageDemandeCadeaux;
    }

    public String getDatedexpirationDemandeCadeaux() {
        return datedexpirationDemandeCadeaux;
    }

    public void setDatedexpirationDemandeCadeaux(String datedexpirationDemandeCadeaux) {
        this.datedexpirationDemandeCadeaux = datedexpirationDemandeCadeaux;
    }

    public Compte getMembreDemande() {
        return MembreDemande;
    }
    public String getUsernameMembreDemande() {
        return MembreDemande.getId_user().getUsername();
    }

    public void setMembreDemande(Compte MembreDemande) {
        this.MembreDemande = MembreDemande;
    }
   public int getIdMembreDemande() {
        return MembreDemande.getId_user().getId();
    }
    public int getPointMembreDemande() {
        return MembreDemande.getPoint_merci();
    }
//    public String getImageMembreDemande() {
//        
//        if (MembreDemande.getId_user().getImage().equals("")) {
//            return null;
//            
//        } else {MembreDemande.getId_user().getImage();
//        }
//        
//      return null;
//    }
    public Cadeaux getCadeaux() {
        return Cadeaux;
    }
    public String getLibelleCadeaux() {
        return Cadeaux.getLibelle();
    }

    public void setCadeaux(Cadeaux Cadeaux) {
        this.Cadeaux = Cadeaux;
    }

    @Override
    public String toString() {
        return "DemandeCadeaux{" + "idDemandeCadeaux=" + idDemandeCadeaux + ", statusDemandeCadeaux=" + statusDemandeCadeaux + ", descriptionDemandeCadeaux=" + descriptionDemandeCadeaux + ", datedemandeDemandeCadeaux=" + datedemandeDemandeCadeaux + ", messageDemandeCadeaux=" + messageDemandeCadeaux + ", datedexpirationDemandeCadeaux=" + datedexpirationDemandeCadeaux + ", MembreDemande=" + MembreDemande + ", Cadeaux=" + Cadeaux + '}';
    }
   
    


    
 }
