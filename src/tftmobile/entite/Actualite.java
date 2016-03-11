/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.entite;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author yasmi
 */
public class Actualite {
    
    private int idactualite;
    private String sujet;
    private Vector description;
    private Date datepublication;
    private Date datedestruction;

    public Actualite() {
    }

    public Actualite(int idactualite, String sujet, Date datepublication, Date datedestruction) {
        this.idactualite = idactualite;
        this.sujet = sujet;
        description = new Vector();
        this.datepublication = datepublication;
        this.datedestruction = datedestruction;
    }
    
    public Actualite(String sujet) {
        this.sujet = sujet;
    }
    
    public Actualite(Vector description) {
        this.description = description;
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

    public Description[] getDescription() {
        Description[] descriptions = new Description[description.size()];
        description.copyInto(descriptions);
        return descriptions;
    }

    public void setDescription(Vector description) {
        this.description = description;
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

    public String toString() {
        return "Actualite{" + "idactualite=" + idactualite + ", sujet=" + sujet + ", description=" + description + ", datepublication=" + datepublication + ", datedestruction=" + datedestruction + '}';
    }

    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.idactualite;
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Actualite other = (Actualite) obj;
        if (this.idactualite != other.idactualite) {
            return false;
        }
        return true;
    }

    
    public void addDescription(Description desc) {
        description.addElement(desc);
    }
    
}
