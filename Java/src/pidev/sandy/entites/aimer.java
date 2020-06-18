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
public class aimer {
    int id,idb;
    User username;
    // String username;

    public aimer(int id) {
        this.id = id;
    }

    public aimer() {
   
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdb() {
        return idb;
    }

    public void setIdb(int idb) {
        this.idb = idb;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }
   public aimer( int idb, User username) {
        
        this.idb = idb;
        this.username = username;
    }
    public aimer(int id, int idb, User username) {
        this.id = id;
        this.idb = idb;
        this.username = username;
    }
   
    
    
}
