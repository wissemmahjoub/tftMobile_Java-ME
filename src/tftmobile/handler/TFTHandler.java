/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.handler;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.microedition.lcdui.Ticker;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import tftmobile.entite.Matchs;
import tftmobile.entite.Ticket;

/**
 *
 * @author malox94
 */
public class TFTHandler extends DefaultHandler  {
      // this will hold all the data we read
    private Vector MatchVector;
     private Vector TicketVector;
 
    public TFTHandler() {
        MatchVector = new Vector();
        TicketVector = new Vector();
    }
 
    public Matchs[] getMatch() {
       Matchs[] MatchTab = new Matchs[MatchVector.size()];
       MatchVector.copyInto(MatchTab);
        return MatchTab;
    }
    
    public Ticket[] getTicket(){
        Ticket[] TicketTab = new Ticket[TicketVector.size()];
        TicketVector.copyInto(TicketTab);
        return TicketTab;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
    private Matchs currentMatch;
      private Ticket currentTicket;
   
 
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
            String IDticket = attr.getValue("IDticket");
            String PrixTicket = attr.getValue("PrixTicket");
            String NbrTicket = attr.getValue("Nbrticket");
            
           
            System.out.println(IDticket + " ------ TFThandler");

              
          //  System.out.println("++++" + NameJoueur1 + "+++" + LastNameJoueur2 + "----" + NameJoueur2 + "lll");
            currentMatch = new Matchs(NameJoueur1,LastNameJoueur1,NameJoueur2,LastNameJoueur2 ,Integer.parseInt(IDticket), DateMatch);
            currentTicket = new Ticket(Integer.parseInt(IDticket), Integer.parseInt(PrixTicket), Integer.parseInt(NbrTicket));
            System.out.println(currentTicket.getNbrticket() + " aaaa " + currentTicket.getPrix());
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
           TicketVector.addElement(currentTicket);
            // we are no longer processing a <person.../> tag
            currentMatch = null;    
            currentTicket = null;
        }
    }
 
    public Date stringToDate(String s){
  Calendar c = Calendar.getInstance();

  c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(s.substring(0, 2)));
  c.set(Calendar.MONTH, Integer.parseInt(s.substring(2, 4))-1 );
  c.set(Calendar.YEAR, Integer.parseInt(s.substring(4, 8)));

  return c.getTime();
}
 
    
}
