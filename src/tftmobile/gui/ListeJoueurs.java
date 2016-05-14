/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;

import tftmobile.handler.JoueursHandler;
import tftmobile.entite.Club;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Spacer;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tftmobile.entite.Personne;
import tftmobileMidlet.Midlet;

/**
 *
 * @author yasmi
 */
public class ListeJoueurs extends List implements Runnable,CommandListener {
    
    Personne[] personnes;
    Club[] clubs;
    Command cmdback  = new Command("Retour", Command.BACK, 0);
    String libelle;
    static String nomj,prenomj;
    static int idpersonne;
    static String res;

            
    public ListeJoueurs(String title, int listType) {
        super(title, listType);
        addCommand(cmdback);
        setCommandListener(this);
        Thread th=new Thread(this);
        th.start();
    }
    


    public void run() {
  
            try {
         
                
            JoueursHandler jdh = new JoueursHandler();                
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/tftmobile/listejoueurs.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, jdh);
                
                personnes = jdh.getJoueurs();
                clubs = jdh.getClubs();
                
                for (int i = 0; i < personnes.length; i++) {
                    System.out.println("personnes["+i+"].getNom()" + " " + "personnes["+i+"].getPrenom()");
                    
                    String nom = personnes[i].getNom();
                    String prenom = personnes[i].getPrenom();
                    libelle = clubs[i].getLibelleclub();
                    
                    append("Joueur: "+nom + " " + prenom + "\n" +"Son Club: " +libelle ,null) ;
                    
                    }

                
                  } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            } catch (SAXException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }

    public void commandAction(Command c, Displayable d) {
        if(c == SELECT_COMMAND){
                idpersonne = personnes[this.getSelectedIndex()].getIdpersonne();
                nomj = personnes[this.getSelectedIndex()].getNom();
                prenomj = personnes[this.getSelectedIndex()].getPrenom();
                Midlet.mid.dis.setCurrent(new ListeMatchsJoueurs("Liste matchs par joueurs", List.IMPLICIT));
                      }
        if(c==cmdback){
            Midlet.mid.dis.setCurrent(new AccueilPrive(Midlet.mid));
        }
        

    }
}
