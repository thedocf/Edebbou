/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Product;
import java.sql.Date;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author Th3Doc
 */
public interface IProduct {
     public void addProduct(Product cl) throws SQLException;
  
    public void updateProduct(Product cl,int id) throws SQLException;
    public void deleteProduct(int id) throws SQLException; 
    public ObservableList<Product> readAll () throws SQLException;
    public ObservableList<Product> readAllBC (String Cat) throws SQLException;
    public Product rechercherProduct(int id) throws SQLException;
    public ObservableList<Product> rechercheProFilter(String k) throws SQLException;
   public ObservableList<Product> triProByDate()throws SQLException;
   public String getNameBy(int ID)throws SQLException;
   public Float getPriceBy(int ID)throws SQLException;
      public Date getDateBy(int ID)throws SQLException;
     public Integer getQteBy(int ID)throws SQLException;
     public String getDescBy(int ID)throws SQLException;
     public ObservableList<Product> rechercheParPMN(Double k) throws SQLException;
     public ObservableList<Product> rechercheParPMX(Double k) throws SQLException;
             



}
