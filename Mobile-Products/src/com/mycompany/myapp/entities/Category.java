/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author M-YAHYAOUI
 */
public class Category {
    private int id;
    
    private String label;

        
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public Category(int idCategory,String label)
    {
        this.id=idCategory;
        this.label=label;
    }
    public Category(String label)
    {
        this.label=label;
    }
    public Category()
    {
        
    }
    @Override
    public String toString() {
        return "Product Category{" + "idCategory=" + id + ", Label=" + label + '}';
    }
    
}


