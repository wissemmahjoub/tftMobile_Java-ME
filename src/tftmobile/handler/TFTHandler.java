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
import tftmobile.entite.Match;

/**
 *
 * @author malox94
 */
public class TFTHandler extends DefaultHandler  {
      // this will hold all the data we read
    private Vector MatchVector;
 
    public TFTHandler() {
        MatchVector = new Vector();
    }
 
    public Match[] getMatch() {
       Match[] MatchTab = new Match[MatchVector.size()];
       MatchVector.copyInto(MatchTab);
        return MatchTab;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
    private Match currentMatch;
   
 
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
        if (qNa.equals("Match")) {
		    // create new Person object
            
            String NameJoueur1 = attr.getValue("NameJoueur1");
            String LastNameJoueur1 = attr.getValue("LastNameJoueur1");
            String NameJoueur2 = attr.getValue("NameJoueur2");
            String LastNameJoueur2 = attr.getValue("LastNameJoueur2");
            String DateMatch = attr.getValue("DateMatch");
            String IdTicket = attr.getValue("IDticket");
            
            



          //  System.out.println("++++" + NameJoueur1 + "+++" + LastNameJoueur2 + "----" + NameJoueur2 + "lll");
//            currentMatch = new Match(NameJoueur1,LastNameJoueur1,NameJoueur2,LastNameJoueur2,Integer.parseInt(IdTicket),DateMatch);
            if (NameJoueur1 == null || LastNameJoueur1 == null) {
                System.out.println("pas ok ok ok ok ok ok");}
            
        } 
    
    } 
    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String ur, String locName, String qNa) throws SAXException {
        if (qNa.equals("Match")) {
            
            // add completed Person object to collection
           MatchVector.addElement(currentMatch);
            
            // we are no longer processing a <person.../> tag
            currentMatch = null;
        }
    }
 
 
    
}
