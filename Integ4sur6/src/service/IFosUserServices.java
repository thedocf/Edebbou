/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Fos_user;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Fida
 */
public interface IFosUserServices {



      public void  ajouterClient(Fos_user c);
      
  public Boolean CheckIfUsernameExist(String username) ;
  Boolean CheckIfUserExist(String email) ;
 
     public ObservableList afficher();
  
    }
