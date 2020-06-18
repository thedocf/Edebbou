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
public class Compte {
    
    
    
int idCompte;
private Badge id_badge;
private Cadeaux id_cadeau;
private User id_user;
private int point_merci;

    public Compte() {
    }

    public Compte(int idCompte, Badge id_badge, Cadeaux id_cadeau, User id_user, int point_merci) {
        this.idCompte = idCompte;
        this.id_badge = id_badge;
        this.id_cadeau = id_cadeau;
        this.id_user = id_user;
        this.point_merci = point_merci;
    }

    @Override
    public String toString() {
        return "Compte{" + "idCompte=" + idCompte + ", id_badge=" + id_badge + ", id_cadeau=" + id_cadeau + ", id_user=" + id_user + ", point_merci=" + point_merci + '}';
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public Badge getId_badge() {
        return id_badge;
    }

    public void setId_badge(Badge id_badge) {
        this.id_badge = id_badge;
    }

    public Cadeaux getId_cadeau() {
        return id_cadeau;
    }

    public void setId_cadeau(Cadeaux id_cadeau) {
        this.id_cadeau = id_cadeau;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public int getPoint_merci() {
        return point_merci;
    }

    public void setPoint_merci(int point_merci) {
        this.point_merci = point_merci;
    }


    
}
