/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;


import java.util.Date;


public class Match {
    
    private int idmatch;
    private int idjoueur1;
    private int idjoueur2;
    private int idscore;
    private String LastNameJoueur2;
    private int idticket;
    private Date datematch;

    public Match() {
    }

    public Match(int idmatch, int idjoueur1, int idjoueur2, int idscore, String LastNameJoueur2, int idticket, Date datematch) {
        this.idmatch = idmatch;
        this.idjoueur1 = idjoueur1;
        this.idjoueur2 = idjoueur2;
        this.idscore = idscore;
        this.LastNameJoueur2 = LastNameJoueur2;
        this.idticket = idticket;
        this.datematch = datematch;
    }

    public Match(int idjoueur1, int idjoueur2, int idscore) {
        this.idjoueur1 = idjoueur1;
        this.idjoueur2 = idjoueur2;
        this.idscore = idscore;
    }

    public Match(int idjoueur1, int idjoueur2, Date datematch) {
        this.idjoueur1 = idjoueur1;
        this.idjoueur2 = idjoueur2;
        this.datematch = datematch;
    }

    public int getIdmatch() {
        return idmatch;
    }

    public void setIdmatch(int idmatch) {
        this.idmatch = idmatch;
    }

    public int getIdjoueur1() {
        return idjoueur1;
    }

    public void setIdjoueur1(int idjoueur1) {
        this.idjoueur1 = idjoueur1;
    }

    public int getIdjoueur2() {
        return idjoueur2;
    }

    public void setIdjoueur2(int idjoueur2) {
        this.idjoueur2 = idjoueur2;
    }

    public int getIdscore() {
        return idscore;
    }

    public void setIdscore(int idscore) {
        this.idscore = idscore;
    }

    public String getLastNameJoueur2() {
        return LastNameJoueur2;
    }

    public void setLastNameJoueur2(String LastNameJoueur2) {
        this.LastNameJoueur2 = LastNameJoueur2;
    }

    public int getIdticket() {
        return idticket;
    }

    public void setIdticket(int idticket) {
        this.idticket = idticket;
    }

    public Date getDatematch() {
        return datematch;
    }

    public void setDatematch(Date datematch) {
        this.datematch = datematch;
    }

    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.idmatch;
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Match other = (Match) obj;
        if (this.idmatch != other.idmatch) {
            return false;
        }
        return true;
    }
    
    
    
    

}