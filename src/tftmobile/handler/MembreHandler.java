/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.handler;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tftmobile.entite.Match;

import tftmobile.entite.Personne;

/**
 *
 * @author malox94
 */
public class MembreHandler extends DefaultHandler {
        // this will hold all the data we read
    private Vector MembreVector;
 
    public MembreHandler() {
        MembreVector = new Vector();
    }
 
    public Personne[] getPersonne() {
        Personne[] PersonneTab = new Personne[MembreVector.size()];
       MembreVector.copyInto(PersonneTab);
        return PersonneTab;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
    static Personne currentMembre;
   
 
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
        if (qNa.equals("connected")) {
		    // create new Person object
            
            String idpersonne = attr.getValue("ID_Personne");
            String nom = attr.getValue("Name");
            String prenom = attr.getValue("Last_Name");
            String nbrjeton = attr.getValue("Nbr_Jeton");
            
            


            
          //  System.out.println("++++" + NameJoueur1 + "+++" + LastNameJoueur2 + "----" + NameJoueur2 + "lll");
            currentMembre = new Personne(Integer.parseInt(idpersonne),nom,prenom,Integer.parseInt(nbrjeton));
             if (prenom == null || nom == null) {
                System.out.println("pas ok ok ok ok ok ok");}
            
        } 
    
    } 
    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String ur, String locName, String qNa) throws SAXException {
        if (qNa.equals("connected")) {
            
            // add completed Person object to collection
           MembreVector.addElement(currentMembre);
            
            // we are no longer processing a <person.../> tag
            currentMembre = null;
        }
    }
 
 
    
}
