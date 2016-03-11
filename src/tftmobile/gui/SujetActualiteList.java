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
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tftmobile.connexion.ServerGetter;
import tftmobile.entite.Actualite;
import tftmobile.entite.Description;
import tftmobile.handler.SujetActualiteHandler;
import tftmobileMidlet.Midlet;

/**
 *
 * @author yasmi
 */
public class SujetActualiteList extends List implements Runnable,CommandListener {
    
    private Actualite[] actualites;
    private Command cmdnext  = new Command("Next", Command.SCREEN, 0);
    private Command cmdback  = new Command("Back", Command.BACK, 0);
    private Display disp;
    private StringBuffer sb;
    private Description[] descriptions;
    public static String actualite;
    
       public SujetActualiteList(String title, int listType) {
        super(title, listType);
 
        addCommand(cmdnext);
        addCommand(cmdback);
        setCommandListener(this);
        Thread th=new Thread(this);
        th.start();
    }

    public void run() {
  
            try {
                    SujetActualiteHandler acthandler =new SujetActualiteHandler();
                    SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                    HttpConnection hc = (HttpConnection) Connector.open("http://localhost/tftmobile/sujetactualite.php");
                    DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                    parser.parse(dis, acthandler);
                
                        actualites = acthandler.getActualite();
                        
                        for (int i = 0; i < actualites.length; i++) {
                          System.out.println("actualites["+i+"].getSujet()");
                          append(actualites[i].getSujet(), null);
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
        
        if(c == cmdnext){
             for (int i = 0 ; i <this.size();i++){
            if(this.isSelected(i)){
                if(this.getString(i).equals("Stades")){
                         actualite = ServerGetter.GetTextServer("http://localhost/tftmobile/staedeactualite.txt");
                         Midlet.mid.dis.setCurrent(new DescriptionActualiteStadeForm("Description"));
                                                      }
                
                if(this.getString(i).equals("Evenements")){
                         actualite = ServerGetter.GetTextServer("http://localhost/tftmobile/eventact.txt");
                         Midlet.mid.dis.setCurrent(new DescriptionActualiteEventForm("Description"));
                                                          }
                                  }
                                                 }
                        }
        if(c == cmdback){
            Midlet.mid.dis.setCurrent(new Accueil(Midlet.mid));

        }

    }
}
