/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;
import entite.Category;
import entite.Category;

/**
 *
 * @author Th3Doc
 */
public interface ICategory {
    public void addCategory(Category cl) throws SQLException;
    public List <Category> displayCategories() throws SQLException;
    public void updateCategory(Category cl,int id) throws SQLException;
    public void deleteCategory(int id) throws SQLException; 
    public ObservableList<Category> readAll () throws SQLException;
     public ObservableList<String> readAll2 () throws SQLException;
    public Category rechercherCategory(int id) throws SQLException;
    public ObservableList<Category> rechercherCatFiltre(String k) throws SQLException;
   public ObservableList<Category> triCatsByLabel()throws SQLException;
   public int getIDBy(String lab)throws SQLException;
   public String getLabelBy(int ID)throws SQLException;
}
