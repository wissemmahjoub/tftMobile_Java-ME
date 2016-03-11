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
import tftmobile.entite.Personne;
import tftmobile.handler.MembreHandler;
import tftmobile.handler.TFTHandler;
import tftmobileMidlet.Midlet;

/**
 *
 * @author malox94
 */
public class ListMatchs extends List implements CommandListener, Runnable {
    
      Command cmdBack = new Command("Back", Command.SCREEN, 0);
    Match[] match;
    StringBuffer sb;
       private Vector Demandev;
         Personne[] person;
   
    Display disp;

   

    public ListMatchs(String title, int listType) {
        super(title, listType);
      
        Thread th = new Thread(this);
        th.start();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            //disp.setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
        }
           if (c == List.SELECT_COMMAND){
               
           int idperson = person[this.getSelectedIndex()].getIdpersonne();
                String log = person[this.getSelectedIndex()].getLogin();
                 String Name = person[this.getSelectedIndex()].getNom();
                  String LastName  = person[this.getSelectedIndex()].getPrenom();
                   String jeton = person[this.getSelectedIndex()].getNbrjeton();
               
               Personne p = new Personne(idperson, log, Name, LastName, jeton);
               
               String NameJ1 = match[this.getSelectedIndex()].getNameJoueur1() ;
                String LastNameJ1 = match[this.getSelectedIndex()].getLastNameJoueur1();
                 String NameJ2 = match[this.getSelectedIndex()].getNameJoueur2() ;
                  String LastNameJ2  = match[this.getSelectedIndex()].getLastNameJoueur2();
                   String DateMatchLo = match[this.getSelectedIndex()].getDateMatch();
                    int nbrjeto = match[this.getSelectedIndex()].getIdticket() ;
               
               Match m = new Match(NameJ1, LastNameJ1, NameJ2, LastNameJ2,nbrjeto,DateMatchLo);
                Midlet.mid.dis.setCurrent(new Reservation("Reservation", m , p));
                 
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
            HttpConnection hc2 = (HttpConnection) Connector.open("http://localhost/TFTMobile/membre/people.xml");//people.xml est un exemple
            System.out.println(hc2.getFile());
            DataInputStream dis2 = new DataInputStream(hc2.openDataInputStream());
            parser.parse(dis2, membreHandler);
            // display the result
            person = membreHandler.getPersonne() ;
          
         
            
          

            if (match.length > 0) {
                for (int i = 0; i < match.length; i++) {
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
