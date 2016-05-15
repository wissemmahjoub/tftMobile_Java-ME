/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.handler;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tftmobile.entite.Match;
import tftmobile.entite.Matchs;
import tftmobile.entite.Reservation;
import tftmobile.entite.Ticket;

/**
 *
 * @author malox94
 */
public class ReservationHandler extends DefaultHandler {
         // this will hold all the data we read
    private Vector MatchVector;
     private Vector TicketVector;
 private Vector ReservationVector;
 
    public ReservationHandler() {
        MatchVector = new Vector();
        TicketVector = new Vector();
        ReservationVector = new Vector();
    }
 
    public Matchs[] getMatchs() {
       Matchs[] MatchTab = new Matchs[MatchVector.size()];
       MatchVector.copyInto(MatchTab);
        return MatchTab;
    }
    
    public Ticket[] getTicket(){
        Ticket[] TicketTab = new Ticket[TicketVector.size()];
        TicketVector.copyInto(TicketTab);
        return TicketTab;
    }
    
    public Reservation[] getReservation(){
        Reservation[] ReservationTab = new Reservation[ReservationVector.size()];
        ReservationVector.copyInto(ReservationTab);
        return ReservationTab;
        
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
   private Matchs currentMatch;
   private Ticket currentTicket;
   private Reservation currentReservation;
 
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
        if (qNa.equals("reservation")) {
		    // create new Person object
                    
                    
            String idpersonne = attr.getValue("idmembre");
            String NameJoueur1 = attr.getValue("NomJoueur1");
            String LastNameJoueur1 = attr.getValue("PrenomJoueur1");
            String NameJoueur2 = attr.getValue("NomJoueur2");
            String LastNameJoueur2 = attr.getValue("PrenomJoueur2");
            String DateMatch = attr.getValue("DateMatch");
            String IDticket = attr.getValue("IDticket");
            String PrixTicket = attr.getValue("Prix");
            String NbrTicket = attr.getValue("NbrTicket");
            
           
            

              
          //  System.out.println("++++" + NameJoueur1 + "+++" + LastNameJoueur2 + "----" + NameJoueur2 + "lll");
          currentMatch = new Matchs(NameJoueur1,LastNameJoueur1,NameJoueur2,LastNameJoueur2 ,Integer.parseInt(IDticket), DateMatch);
          currentReservation = new Reservation(Integer.parseInt(idpersonne));
          currentTicket = new Ticket(Integer.parseInt(IDticket), Integer.parseInt(NbrTicket),Float.parseFloat(PrixTicket));
          
            if (NameJoueur1 == null || LastNameJoueur1 == null) {
                System.out.println("pas ok ok ok ok ok ok");}
            
        } 
    
    } 
    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String ur, String locName, String qNa) throws SAXException {
        if (qNa.equals("reservation")) {
            
            // add completed Person object to collection
           MatchVector.addElement(currentMatch);
           TicketVector.addElement(currentTicket);
           ReservationVector.addElement(currentReservation);
            // we are no longer processing a <person.../> tag
            currentReservation = null;
            currentMatch = null;
            currentTicket = null;
        }
    }
 
    
}
