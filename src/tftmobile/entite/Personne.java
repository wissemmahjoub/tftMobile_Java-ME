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
public class Personne {
    
    int idpersonne;
    String cin;
    String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String sexe;
    private String login;
    private String password;
    private Date datenaissance;
    private String role;
    private String avatar;
    private Date datedestruction;

    public Personne() {
    }

    
    
        public Personne(int idpersonne, String cin, String nom, String prenom, String adresse, String email, String sexe, String login, String password, Date datenaissance ) {
        this.idpersonne = idpersonne;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.sexe = sexe;
        this.login = login;
        this.password = password;
        this.datenaissance = datenaissance;
    
    }
    
    public Personne(int idpersonne, String cin, String nom, String prenom, String adresse, String email, String sexe, String login, String password, Date datenaissance ,String avatar) {
        this.idpersonne = idpersonne;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.sexe = sexe;
        this.login = login;
        this.password = password;
        this.datenaissance = datenaissance;
        this.avatar = avatar;
    }
    
    
    public Personne(int idpersonne, String cin, String nom, String prenom, String adresse, String email, String sexe, String login, String password, Date datenaissance, String role, String avatar, Date datedestruction) {
        this.idpersonne = idpersonne;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.sexe = sexe;
        this.login = login;
        this.password = password;
        this.datenaissance = datenaissance;
        this.role = role;
        this.avatar = avatar;
        this.datedestruction = datedestruction;
    }
    public Personne (int idpersonne , String cin, String nom, String prenom)
    {
    this.idpersonne = idpersonne;
    this.cin = cin;
    this.nom = nom;
    this.prenom = prenom;
    }

    public int getIdpersonne() {
        return idpersonne;
    }

    public void setIdpersonne(int idpersonne) {
        this.idpersonne = idpersonne;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getDatedestruction() {
        return datedestruction;
    }

    public void setDatedestruction(Date datedestruction) {
        this.datedestruction = datedestruction;
    }

  
    
    
}
