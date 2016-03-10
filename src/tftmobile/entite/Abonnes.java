/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.entite;

/**
 *
 * @author wissem
 */
public class Abonnes {
    int idabonnes;
    private String email;

    public Abonnes(int idabonnes, String email) {
        this.idabonnes = idabonnes;
        this.email = email;
    }

    public int getIdabonnes() {
        return idabonnes;
    }

    public void setIdabonnes(int idabonnes) {
        this.idabonnes = idabonnes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Abonnes{" + "idabonnes=" + idabonnes + ", email=" + email + '}';
    }
    
}
