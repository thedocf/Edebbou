/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author jha
 */
public class Produit {

    public static void setCellValueFactory(PropertyValueFactory<Object, Object> propertyValueFactory) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private int id;
    private String nom;
    private float prix;
    
    private Button b;

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
    public boolean equals(Object obj) {
        Produit p = (Produit)obj;
        return p.getId() == id; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
