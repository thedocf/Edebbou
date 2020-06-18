/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.entites;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;

/**
 *
 * @author Asus
 */
public class User {
 
   private static final Map<Integer, User> USERS = new HashMap<>();
    private int id;
    private String username;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private String password;
     
    private int enabled;
    //salt
    private Date last_login;
        private String roles;

    private String nom;
    private String prenom;
    private String phone;
    private String image;
    private String date_naissance;
    private String date_inscription;
    private String genre;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }
    
    
      public static User of(int id) {
        User user = USERS.get(id);
        if (user == null) {
            user = new User(id);
            USERS.put(id, user);
        }
        return user;
    }

    public User(int id, String username, String username_canonical, String email, String email_canonical, String password, int enabled, Date last_login, String roles, String nom, String prenom, String phone, String image, String date_naissance, String date_inscription, String genre) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.enabled = enabled;
         
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.image = image;
        this.date_naissance = date_naissance;
        this.date_inscription = date_inscription;
        this.genre = genre;
    }
    
    

    public User(int id, String username, String username_canonical, String email, String email_canonical, String password, String roles, String nom, String prenom, String phone, String image, String date_naissance, String date_inscription, String genre) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.image = image;
        this.date_naissance = date_naissance;
        this.date_inscription = date_inscription;
        this.genre = genre;
    }

    public User(String username, String username_canonical, 
            String nom, String prenom, String roles, String phone, String genre) {
         
        this.username = username;
        this.username_canonical = username_canonical;
    
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
     
        this.genre = genre;
    }

    public User(int aInt, String string, String string0, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     

    
 
    
    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(String date_inscription) {
        this.date_inscription = date_inscription;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", password=" + password + ", enabled=" + enabled + ", last_login=" + last_login + ", roles=" + roles + ", nom=" + nom + ", prenom=" + prenom + ", phone=" + phone + ", image=" + image + ", date_naissance=" + date_naissance + ", date_inscription=" + date_inscription + ", genre=" + genre + '}'+'\n';
    }
     

    
   
  

}
