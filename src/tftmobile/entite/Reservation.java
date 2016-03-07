/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.entite;

import java.util.Date;

/**
 *
 * @author yasmi
 */
public class Reservation {
    
    private int idmembre;
    private int idticket;
    private Date datereservation;

    public Reservation() {
    }

    public Reservation(int idmembre, int idticket, Date datereservation) {
        this.idmembre = idmembre;
        this.idticket = idticket;
        this.datereservation = datereservation;
    }

    public int getIdmembre() {
        return idmembre;
    }

    public void setIdmembre(int idmembre) {
        this.idmembre = idmembre;
    }

    public int getIdticket() {
        return idticket;
    }

    public void setIdticket(int idticket) {
        this.idticket = idticket;
    }

    public Date getDatereservation() {
        return datereservation;
    }

    public void setDatereservation(Date datereservation) {
        this.datereservation = datereservation;
    }

    
}
