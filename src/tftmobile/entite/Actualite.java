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
public class Actualite {
    
    private int idactualite;
    private String sujet;
    private Date datepublication;
    private Date datedestruction;

    public Actualite() {
    }

    public Actualite(int idactualite, String sujet, Date datepublication, Date datedestruction) {
        this.idactualite = idactualite;
        this.sujet = sujet;
        this.datepublication = datepublication;
        this.datedestruction = datedestruction;
    }

    public int getIdactualite() {
        return idactualite;
    }

    public void setIdactualite(int idactualite) {
        this.idactualite = idactualite;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Date getDatepublication() {
        return datepublication;
    }

    public void setDatepublication(Date datepublication) {
        this.datepublication = datepublication;
    }

    public Date getDatedestruction() {
        return datedestruction;
    }

    public void setDatedestruction(Date datedestruction) {
        this.datedestruction = datedestruction;
    }

   
    
    
    
}
