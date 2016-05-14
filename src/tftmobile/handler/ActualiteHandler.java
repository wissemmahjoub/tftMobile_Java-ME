/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.handler;

import java.util.Date;
import java.util.Vector;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tftmobile.entite.Actualite;

/**
 *
 * @author yasmi
 */
public class ActualiteHandler extends DefaultHandler {
    
    // this will hold all the data we read
    private Vector actVector;
    private Actualite act;
 
    public ActualiteHandler() {
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
         if(qName.equals("actualite"))
        {
              String sujet = attributes.getValue("sujet");
              String description = attributes.getValue("description");
              String datepublication = attributes.getValue("datepublication");
              
              
              act = new Actualite(sujet, description);
              
          
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("actualite")) {
            actVector.addElement(act);
            act = null;
                                       } 
    }
 

}