
package tftmobile.entite;

import java.util.Date;

/**
 *
 * @author yasmi
 */
public class Club {
    
    
    private int idclub;
    private String libelleclub;
    private String avatar;
    private Date datecreation;
    private Date datedestruction;

    public Club() {
    }

    public Club(int idclub, String libelleclub, String avatar, Date datecreation, Date datedestruction) {
        this.idclub = idclub;
        this.libelleclub = libelleclub;
        this.avatar = avatar;
        this.datecreation = datecreation;
        this.datedestruction = datedestruction;
    }
    
    public Club(String libelleclub) {
        this.libelleclub = libelleclub;
    }
   

    public int getIdclub() {
        return idclub;
    }

    public void setIdclub(int idclub) {
        this.idclub = idclub;
    }

    public String getLibelleclub() {
        return libelleclub;
    }

    public void setLibelleclub(String libelleclub) {
        this.libelleclub = libelleclub;
    }

    

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Date getDatedestruction() {
        return datedestruction;
    }

    public void setDatedestruction(Date datedestruction) {
        this.datedestruction = datedestruction;
    }

    public String toString() {
        return "Club{" + "idclub=" + idclub + ", libellecode=" + libelleclub + ", avatar=" + avatar + ", datecreation=" + datecreation + ", datedestruction=" + datedestruction + '}';
    }

    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.idclub;
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Club other = (Club) obj;
        if (this.idclub != other.idclub) {
            return false;
        }
        return true;
    }

    
    

}
