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
import tftmobile.entite.Actualite;
import tftmobile.entite.Description;

/**
 *
 * @author yasmi
 */
public class SujetActualiteHandler extends DefaultHandler {
    
    // this will hold all the data we read
    private Vector actVector;
    private Actualite act;
    private Description desc;
 
    public SujetActualiteHandler() {
        actVector = new Vector();
    }
 
    public Actualite[] getActualite() {
        Actualite[] actTab = new Actualite[actVector.size()];
        actVector.copyInto(actTab);
        return actTab;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING

 
    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
 
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("actualite")) {
            String sujet = attributes.getValue("sujet");
            act = new Actualite(sujet);
            if (sujet == null) {
                throw new IllegalArgumentException("Person requires both givenname and familyname");
                               }
                                        } 
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("actualite")) {
            actVector.addElement(act);
            act = null;
                                       } 
    }
 

}