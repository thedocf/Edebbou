/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;
import java.sql.Timestamp;

import java.sql.Date;
/**
 *
 * @author Fida
 */
public class Fos_user {
    
     private Integer id;
  
  
    private String username;
 
    private String usernameCanonical;

    private String email;

    private String emailCanonical;

    private boolean enabled;

    private String salt;

    private String password;
 

    private Timestamp lastLogin;
  
    private String confirmationToken;
 
    private Timestamp passwordRequestedAt;
  

    private String roles;
  

    private String nom;
 
    private String prenom;

    
    private String phone;
  
 

    private String datenaiss;
   
    /**
     *
     */
    public Fos_user() {
    }

    public Fos_user(Integer id) {
        this.id = id;
    }

    public Fos_user(Integer id, String username,String email, String phone, String datenaiss) {
        this.id = id;
        this.email = email;
        
        this.username = username;
        this.phone = phone;
        this.datenaiss = datenaiss;
    }

    public Fos_user(Integer id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String salt, String password, Timestamp lastLogin, String confirmationToken, Timestamp passwordRequestedAt, String roles, String nom, String prenom,  String phone) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
       
        this.nom = nom;
        this.prenom = prenom;
     this.phone = phone;
    }

    public Fos_user(Integer id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String password, String roles, String datenaiss) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.password = password;
        this.roles = roles;
        this.datenaiss = datenaiss;
    }

    public Fos_user(Integer id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String salt, String password, Timestamp lastLogin, String confirmationToken, Timestamp passwordRequestedAt, String roles, String nom, String prenom, String phone, String datenaiss) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        
        this.phone = phone;
        this.datenaiss = datenaiss;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public Fos_user(Integer id, String username, String usernameCanonical, String email, String emailCanonical, String nom, String prenom, String phone, String datenaiss) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.datenaiss = datenaiss;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Timestamp getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Timestamp passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    

    public String getFirstname() {
        return nom;
    }

    public void setFirstname(String nom) {
        this.nom = nom;
    }

    public String getLastname() {
        return prenom;
    }

    public void setLastname(String prenom) {
        this.prenom = prenom;
    }

   

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

  

    public String getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(String datenaiss) {
        this.datenaiss = datenaiss;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fos_user)) {
            return false;
        }
        Fos_user other = (Fos_user) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

   public String toString() {
        return "Utilisateur{" + "id=" + id + ", username=" + username + ", username_canonical=" + usernameCanonical + ", email=" + email + ", email_canonical=" + emailCanonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", last_login=" + lastLogin + ", confirmation_token=" + confirmationToken + ", password_requested_at=" + passwordRequestedAt + ", roles=" + roles + ", telephone=" + phone + ", date_naissance=" + datenaiss + ", prenom=" + prenom +  '}';
    }
    
}
