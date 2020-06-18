/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.entites;

import java.sql.Date;

/**
 *
 * @author SLIMEN
 */
public class Repondre {
     int id,userid,demandeid; // pas necessaire car dans base autoincrement
    String message;
    Date datecreation;

    public Repondre() {
    }

    @Override
    public String toString() {
        return "Repondre{" + "id=" + id + ", userid=" + userid + ", demandeid=" + demandeid + ", message=" + message + ", datecreation=" + datecreation + '}';
    }

    public Repondre(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getDemandeid() {
        return demandeid;
    }

    public void setDemandeid(int demandeid) {
        this.demandeid = demandeid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }
}
