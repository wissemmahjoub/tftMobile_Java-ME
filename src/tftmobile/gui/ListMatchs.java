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
import tftmobile.handler.MembreHandler;
import tftmobile.handler.TFTHandler;
import tftmobileMidlet.Midlet;

/**
 *
 * @author malox94
 */
public class ListMatchs extends List implements CommandListener, Runnable {
    
      Command cmdBack = new Command("Back", Command.SCREEN, 0);
      Command cmdConsulter = new Command("Consulter Reservation", Command.SCREEN, 0);
    Matchs[] match;
    Ticket[] tickettab;
    StringBuffer sb;
       private Vector Demandev;
         Personne[] person;
   static Personne p;
    Display disp;

   

    public ListMatchs(String title, int listType) {
        super(title, listType);
      
        Thread th = new Thread(this);
        th.start();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            //disp.setCurrent(loadingDialog);
            Midlet.mid.dis.setCurrent(new MenuAchReser(Midlet.mid));
//            Thread th = new Thread(this);
//            th.start();
        }
        if (c == cmdConsulter) {
            //disp.setCurrent(loadingDialog);
            Midlet.mid.dis.setCurrent(new ConsultationReservation("Consulter Mes Reservations",List.IMPLICIT));
//            Thread th = new Thread(this);
//            th.start();
        }
           if (c == List.SELECT_COMMAND){
              
               //Récupérer la personne connectée.
           int idperson = person[this.getSelectedIndex()].getIdpersonne();
                 String Name = person[this.getSelectedIndex()].getNom();
                  String LastName  = person[this.getSelectedIndex()].getPrenom();
                   int jeton = person[this.getSelectedIndex()].getNbrjeton();
               p = new Personne(idperson, Name, LastName, jeton);
               System.out.println(p.toString() + "ETAPE1");
               String NameJ1 = match[this.getSelectedIndex()].getNameJoueur1() ;
                String LastNameJ1 = match[this.getSelectedIndex()].getLastNameJoueur1();
                 String NameJ2 = match[this.getSelectedIndex()].getNameJoueur2() ;
                  String LastNameJ2  = match[this.getSelectedIndex()].getLastNameJoueur2();
                   String DateMatchLo = match[this.getSelectedIndex()].getDateMatch();
                    int nbrjeto = match[this.getSelectedIndex()].getIdticket() ;
               
               Matchs m = new Matchs(NameJ1, LastNameJ1, NameJ2, LastNameJ2,nbrjeto,DateMatchLo);
               
               
               if ("achat".equals(MenuAchReser.choix)){
                   
                    float prixticket = tickettab[this.getSelectedIndex()].getPrix();
                   int nbrticket = tickettab[this.getSelectedIndex()].getNbrticket();
                   Ticket t = new Ticket(nbrticket,prixticket);
                   System.out.println(t.toString());
                   Midlet.mid.dis.setCurrent(new Achat("Achat", m,t));
               }else{
                   Midlet.mid.dis.setCurrent(new Reservation("Reservation", m));
               }
               
                
                 
           }

    }

    public void run() {
        try {
            setCommandListener(this);
            addCommand(cmdBack);
            setCommandListener(this);
            addCommand(cmdConsulter);
            
            //Affichage des matchs
            TFTHandler matchHandler = new TFTHandler();
            
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/TFTMobile/Match/select.php");//people.xml est un exemple
            System.out.println(hc.getFile());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, matchHandler);
               // display the result
            match = matchHandler.getMatch();
            
            //Affichage des points de crédits
             MembreHandler membreHandler = new MembreHandler();
           
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc2 = (HttpConnection) Connector.open("http://localhost/TFTMobile/fichier2.php?login="+ Authentification.sessionlog + "&password=" + Authentification.sessionpass);//people.xml est un exemple
            System.out.println(hc2.getFile());
            
            DataInputStream dis2 = new DataInputStream(hc2.openDataInputStream());
            
            parser.parse(dis2, membreHandler);
            // display the result
            person = membreHandler.getPersonne() ;
            System.out.println(person);
            
         
            
          

            if (match.length > 0) {
              
                for (int i = 0; i < match.length; i++) {
                    
                    System.out.println(match[i].getFullMatch());
                    append(match[i].getFullMatch(), null);
                    
                }
            }
             
                

        
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

  
}
