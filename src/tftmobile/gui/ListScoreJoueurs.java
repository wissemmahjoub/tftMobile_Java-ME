/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tftmobile.gui;

import tftmobile.handler.ScoreHandler;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.Ticker;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tftmobile.entite.Score;
import tftmobileMidlet.Midlet;

/**
 *
 * @author yasmi
 */
public class ListScoreJoueurs extends Form implements Runnable,CommandListener {
    
    Score[] scores;
    Command cmdback  = new Command("Back", Command.BACK, 0);
    StringItem nbresets = new StringItem(" Sets   ", "");
    StringItem nbreaces = new StringItem(" Aces   ", "");
    StringItem nbrefautes = new StringItem(" Fautes", "");
    

    public ListScoreJoueurs(String title) {
        super(title);
        addCommand(cmdback);
        append(nbresets);
        append(nbreaces);
        append(nbrefautes);
        setCommandListener(this);
        Thread th=new Thread(this);
        th.start();
    }
    


    public void run() {
//        System.out.println("yassine");
       
            try {
              
            System.out.println(ListeJoueurs.idpersonne);
            ScoreHandler scoreHandler = new ScoreHandler();
            
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            
            HttpConnection hc2 = (HttpConnection) Connector.open("http://localhost/tftmobile/listescoresmatchs.php?idscore="+ListeMatchsJoueurs.idscore);
            DataInputStream dis2 = new DataInputStream(hc2.openDataInputStream());
            parser.parse(dis2, scoreHandler);
                
                scores = scoreHandler.getScores();
                
                for (int i = 0; i < scores.length; i++) {
                    System.out.println("scores["+i+"].getNbrsetj1()" + " " + "scores["+i+"].getNbrsetj2()");
                    Ticker tic =  tic = new Ticker(ListeJoueurs.nomj  +" " + ListeJoueurs.prenomj + " " + "VS" + " " + ListeMatchsJoueurs.nomjadv + " " + ListeMatchsJoueurs.prenomjadv  + "");
                    setTicker(tic);
                    nbresets.setText(scores[i].getNbrsetj1() + " " + "VS" + " " + scores[i].getNbrsetj2()) ;
                    nbreaces.setText(scores[i].getNbracej1()+ " " + "VS" + " " + scores[i].getNbracej2()) ;
                    nbrefautes.setText(scores[i].getDblfautej1()+ " " + "VS" + " " + scores[i].getDblfautej2());
                
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
                Midlet.mid.dis.setCurrent(new ListeMatchsJoueurs("test", List.IMPLICIT));            
                         }
//        if(c == SELECT_COMMAND){
//               
////                Midlet.instance.disp.setCurrent(new ListeMatchsJoueurs("test", List.IMPLICIT));
//                      }
    }
}
