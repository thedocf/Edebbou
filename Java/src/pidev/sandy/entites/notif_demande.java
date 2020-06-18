/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.entites;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author SLIMEN
 */
public class notif_demande {
    private int id;
    private int userid;
    private int demandeid;
    private Date date;
    private String subject;
    private String message;
    private String link;
    private int demandeur;
    private Boolean seen;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(int demandeur) {
        this.demandeur = demandeur;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.subject);
        hash = 59 * hash + Objects.hashCode(this.message);
        hash = 59 * hash + Objects.hashCode(this.link);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final notif_demande other = (notif_demande) obj;
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "notif_demande{" + "id=" + id + ", userid=" + userid + ", demandeid=" + demandeid + ", date=" + date + ", subject=" + subject + ", message=" + message + ", link=" + link + ", demandeur=" + demandeur + ", seen=" + seen + '}';
    }

    public notif_demande() {
    }

    public notif_demande(int id, int userid, int demandeid, Date date, String subject, String message, String link, int demandeur, Boolean seen) {
        this.id = id;
        this.userid = userid;
        this.demandeid = demandeid;
        this.date = date;
        this.subject = subject;
        this.message = message;
        this.link = link;
        this.demandeur = demandeur;
        this.seen = seen;
    }
    
    
    
}
