/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tftmobile.connexion.ServerGetter;
import tftmobile.entite.Personne;
import tftmobile.handler.MatchsHandler;
import tftmobileMidlet.Midlet;


/**
 *
 * @author yasmi
 */
public class ListeMatchsJoueurs extends List implements Runnable,CommandListener {
    
    Match[] matchs;
    Personne[] personnes;
    
    Command cmdback  = new Command("Back", Command.BACK, 0);
    Command cmdstat = new Command("Statistiques", Command.SCREEN, 0);
    
    public static int idscore;
    public static String nomjadv,prenomjadv;
    public static String nbtot, nbgain;
    

    public ListeMatchsJoueurs(String title, int listType) {
        super(title, listType);
        addCommand(cmdstat);
        addCommand(cmdback);
        setCommandListener(this);
        Thread th=new Thread(this);
        th.start();
    }
    


    public void run() {
//        System.out.println("yassine");
       
            try {
              
            System.out.println(ListeJoueurs.idpersonne);
            MatchsHandler matchsHandler = new MatchsHandler();
            
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            
            HttpConnection hc2 = (HttpConnection) Connector.open("http://localhost/tftmobile/listematchsjoueurs.php?idpersonne="+ListeJoueurs.idpersonne);
            DataInputStream dis2 = new DataInputStream(hc2.openDataInputStream());
            parser.parse(dis2, matchsHandler);
                
                matchs = matchsHandler.getMatchs();
                personnes = matchsHandler.getJoueurs();
                
                for (int i = 0; i < matchs.length; i++) {
                    System.out.println("matchs["+i+"].getIdjoueur1()" + " " + "matchs["+i+"].getIdjoueur1()");
                   
                    nomjadv = personnes[i].getNom();
                    prenomjadv = personnes[i].getPrenom();
                  
                    append(ListeJoueurs.nomj  +" " + ListeJoueurs.prenomj + " " + "VS" + " " + nomjadv + " " + prenomjadv  + "",null) ;
                    
                    
                    }
                  } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
                System.out.println(ex);
                System.out.println("aaaa");
            } catch (SAXException ex) {
                ex.printStackTrace();
                System.out.println("bbbb");
                 System.out.println(ex);
            } catch (IOException ex) {
                 System.out.println(ex);
                 System.out.println("cccc");
                ex.printStackTrace();
            }
    }

    public void commandAction(Command c, Displayable d) {
        if(c  == cmdback){
                Midlet.mid.dis.setCurrent(new ListeJoueurs("Liste des joueurs avec Clubs", List.IMPLICIT));            
                         }
        if(c == SELECT_COMMAND){
                idscore = matchs[this.getSelectedIndex()].getIdscore();
                Midlet.mid.dis.setCurrent(new  ListScoreJoueurs("Score"));
                      }
        
        if(c == cmdstat){   
            nbtot =  ServerGetter.GetTextServer("http://localhost/tftmobile/getNbTotalMatchJoueur.php?idpersonne="+ListeJoueurs.idpersonne);
            nbgain =  ServerGetter.GetTextServer("http://localhost/tftmobile/getGainJoueur.php?idpersonne="+ListeJoueurs.idpersonne);

            Midlet.mid.dis.setCurrent(new PariStatistique());
                        }
    }
}
