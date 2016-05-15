/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tftmobile.entite.Match;
import tftmobile.entite.Matchs;
import tftmobile.entite.Personne;
import tftmobile.entite.Ticket;
import tftmobile.entite.Reservation;
import tftmobile.handler.MembreHandler;
import tftmobile.handler.ReservationHandler;
import tftmobile.handler.TFTHandler;
import tftmobileMidlet.Midlet;

/**
 *
 * @author malox94
 */
public class ConsultationReservation extends List implements CommandListener, Runnable {

    
    Command cmdBack = new Command("Back", Command.SCREEN, 0);
    //  Personne p = new Personne(1, "yassine", "boukthir", "yassine", 1500);
    Matchs[] match;
    Ticket[] tickettab;
    Reservation[] reservationtableau;
    Personne[] person;
    StringBuffer sb;
   
    
    private Vector Demandev;
    
   
    Display disp;
    
    
    public ConsultationReservation(String title, int listType) {
         super(title, listType);
      
        Thread th = new Thread(this);
        th.start();
    }

    public void commandAction(Command c, Displayable d) {
    if (c == cmdBack) {
            Midlet.mid.dis.setCurrent(new ListMatchs("Liste Des matchs",List.IMPLICIT));
            Thread th = new Thread(this);
            th.start();
        }
    
      if (c == List.SELECT_COMMAND){
          
              
             
               //Remplissage du match selectionné de la liste
                String NameJ1 = match[this.getSelectedIndex()].getNameJoueur1() ;
                String LastNameJ1 = match[this.getSelectedIndex()].getLastNameJoueur1();
                String NameJ2 = match[this.getSelectedIndex()].getNameJoueur2() ;
                String LastNameJ2  = match[this.getSelectedIndex()].getLastNameJoueur2();
                String DateMatchLo = match[this.getSelectedIndex()].getDateMatch();
                int IDticket = match[this.getSelectedIndex()].getIdticket();
                Matchs m = new Matchs(NameJ1, LastNameJ1, NameJ2, LastNameJ2,IDticket, DateMatchLo);
             
               
               
               // Remplissage des tickets selectionné de la lsite
                float prixticket = tickettab[this.getSelectedIndex()].getPrix();
                int nbrticket = tickettab[this.getSelectedIndex()].getNbrticket();
                Ticket t = new Ticket(nbrticket,prixticket);
                   
            
                Midlet.mid.dis.setCurrent(new AfficherMesReservations("Mes Reservations", m,t));
              
               
      }
    
        
        
    }

    public void run() {
          try {
            setCommandListener(this);
            addCommand(cmdBack);
            
            //Affichage des matchs
            TFTHandler matchHandler = new TFTHandler();
            
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            
            ReservationHandler reservationhandler = new ReservationHandler();
              System.out.println("mahmoud");
                HttpConnection hc3 = (HttpConnection) Connector.open("http://localhost/TFTMobile/Reservation/select.php?idpersonne=" + ListMatchs.p.getIdpersonne() );//people.xml est un exemple
            System.out.println(hc3.getFile());
            DataInputStream dis3 = new DataInputStream(hc3.openDataInputStream());
            parser.parse(dis3, reservationhandler);
               // display the result
         
            
            reservationtableau = reservationhandler.getReservation();
            match = reservationhandler.getMatchs();
            tickettab = reservationhandler.getTicket();
            
            
            if (match.length > 0) {
                for (int i = 0; i < match.length; i++) {
                    append(match[i].getFullMatch(), null);
                }
            }
             
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
           
        } catch (SAXException ex) {
            
      
             Midlet.mid.dis.setCurrent(new ListMatchs("Liste Des matchs",List.IMPLICIT));
             Alert a = new Alert("Alert", " Vous n'avez aucune reservation  " , null, AlertType.WARNING);
             a.setTimeout(5000);
             Midlet.mid.dis.setCurrent(a);
             
             
        } catch (IOException ex) {
             
            ex.printStackTrace();
        }

    }
    
 

    
}
