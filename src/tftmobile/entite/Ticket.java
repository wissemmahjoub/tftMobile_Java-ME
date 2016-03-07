/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.entite;

/**
 *
 * @author yasmi
 */
public class Ticket {
    
    private int idticket;
    private int nbrticket;
    private int idmatch;
    private float prix;

    public Ticket() {
    }

    public Ticket(int idticket, int nbrticket, int idmatch, float prix) {
        this.idticket = idticket;
        this.nbrticket = nbrticket;
        this.idmatch = idmatch;
        this.prix = prix;
    }

    public int getIdticket() {
        return idticket;
    }

    public void setIdticket(int idticket) {
        this.idticket = idticket;
    }

    public int getNbrticket() {
        return nbrticket;
    }

    public void setNbrticket(int nbrticket) {
        this.nbrticket = nbrticket;
    }

    public int getIdmatch() {
        return idmatch;
    }

    public void setIdmatch(int idmatch) {
        this.idmatch = idmatch;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    
    
}
