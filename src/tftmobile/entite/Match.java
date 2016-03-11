/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.entite;


import java.util.Date;

/**
 *
 * @author malox94
 */
public class Match {

    public Match(String NameJoueur1, String LastNameJoueur1, String NameJoueur2, String LastNameJoueur2, int idticket, String DateMatch) {
        this.NameJoueur1 = NameJoueur1;
        this.LastNameJoueur1 = LastNameJoueur1;
        this.NameJoueur2 = NameJoueur2;
        this.LastNameJoueur2 = LastNameJoueur2;
        this.idticket = idticket;
        this.DateMatch = DateMatch;
    }

    public int getIdticket() {
        return idticket;
    }

    public void setIdticket(int idticket) {
        this.idticket = idticket;
    }

  

    public String getNameJoueur1() {
        return NameJoueur1;
    }

    public void setNameJoueur1(String NameJoueur1) {
        this.NameJoueur1 = NameJoueur1;
    }

    public String getLastNameJoueur1() {
        return LastNameJoueur1;
    }

    public void setLastNameJoueur1(String LastNameJoueur1) {
        this.LastNameJoueur1 = LastNameJoueur1;
    }

    public String getNameJoueur2() {
        return NameJoueur2;
    }

    public void setNameJoueur2(String NameJoueur2) {
        this.NameJoueur2 = NameJoueur2;
    }

    public String getLastNameJoueur2() {
        return LastNameJoueur2;
    }

    public void setLastNameJoueur2(String LastNameJoueur2) {
        this.LastNameJoueur2 = LastNameJoueur2;
    }

    public String getDateMatch() {
        return DateMatch;
    }

    public void setDateMatch(String DateMatch) {
        this.DateMatch = DateMatch;
    }
    
  
    private String NameJoueur1;
    private String LastNameJoueur1;
    private String NameJoueur2;
    private String LastNameJoueur2;
    int idticket;
    private String DateMatch;


     public Match(String NameJoueur1, String LastNameJoueur1, String NameJoueur2, String LastNameJoueur2, String DateMatch) {
        this.NameJoueur1 = NameJoueur1;
        this.LastNameJoueur1 = LastNameJoueur1;
        this.NameJoueur2 = NameJoueur2;
        this.LastNameJoueur2 = LastNameJoueur2;
        this.DateMatch = DateMatch;
    }

    public String toString() {
        return "Match{" + "NameJoueur1=" + NameJoueur1 + ", LastNameJoueur1=" + LastNameJoueur1 + ", NameJoueur2=" + NameJoueur2 + ", LastNameJoueur2=" + LastNameJoueur2 + ", DateMatch=" + DateMatch + '}';
    }

    
    
     public String getFullMatch() {
        return NameJoueur1 + " " + LastNameJoueur1 + " VS " + NameJoueur2 + " " + LastNameJoueur2 + " le " + DateMatch;
    }

  

   
    

   

  

    
}
