/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.handler;

import java.util.Date;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tftmobile.entite.Personne;


/**
 *
 * @author Mustapha
 */
public class ProfileHandler extends DefaultHandler{
    
        // this will hold all the data we read
    private Vector MembreVector;
 
    public ProfileHandler() {
        MembreVector = new Vector();
    }
 
    public Personne[] getPersonne() {
        Personne[] PersonneTab = new Personne[MembreVector.size()];
       MembreVector.copyInto(PersonneTab);
        return PersonneTab;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
    private Personne currentMembre;
   
 
    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
 
    // startElement is the opening part of the tag "<tagname...>"

    /**
     *
     * @param ur
     * @param locName
     * @param qNa
     * @param attr
     */
    public void startElement(String ur, String locName, String qNa, Attributes attr) {
        if (qNa.equals("membre")) {
		    // create new Person object
            
            String idpersonne = attr.getValue("idpersonne");
            String login = attr.getValue("login");
            String nom = attr.getValue("nom");
            String prenom = attr.getValue("prenom");
            String nbrjeton = attr.getValue("nbrjeton");
            String sexe = attr.getValue("sexe");
            String cin = attr.getValue("cin");
             String avatar = attr.getValue("avatar");
             String email = attr.getValue("email");


            // Personne (int idpersonne , String cin, String nom, String prenom, String sexe, String email, String avatar,int nbrjeton)
    // Personne (int idpersonne , String cin, String nom, String prenom, String sexe, String email, String avatar,int nbrjeton, String login)
     
          //  System.out.println("++++" + NameJoueur1 + "+++" + LastNameJoueur2 + "----" + NameJoueur2 + "lll");
            currentMembre = new Personne();
            currentMembre.setAvatar(avatar);
            currentMembre.setCin(cin);
            currentMembre.setEmail(email);
            currentMembre.setNom(nom);
            currentMembre.setPrenom(prenom);
            currentMembre.setLogin(login);
            currentMembre.setNbrjeton(Integer.parseInt(nbrjeton));
            currentMembre.setIdpersonne(Integer.parseInt(idpersonne));
             if (login == null || nom == null) {
                System.out.println("Oh lala");}
            
        } 
    
    } 
    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String ur, String locName, String qNa) throws SAXException {
        if (qNa.equals("membre")) {
            
            // add completed Person object to collection
           MembreVector.addElement(currentMembre);
            
            // we are no longer processing a <person.../> tag
            currentMembre = null;
        }
    }
 
 
 
}
