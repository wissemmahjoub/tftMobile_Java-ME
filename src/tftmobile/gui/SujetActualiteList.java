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
import tftmobile.handler.ActualiteHandler;
import tftmobileMidlet.Midlet;

/**
 *
 * @author yasmi
 */
public class SujetActualiteList extends List implements Runnable,CommandListener {
    
    private Actualite[] actualites;
    private Display disp;
    private StringBuffer sb;
    static String sujetact;
    private Command cmdback  = new Command("Retour", Command.SCREEN, 0);

    
       public SujetActualiteList(String title, int listType) {
        super(title, listType);
        addCommand(cmdback);
        setCommandListener(this);
        Thread th=new Thread(this);
        th.start();
    }

    public void run() {
  
            try {
                    ActualiteHandler acthandler =new ActualiteHandler();
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
        
  
        if(c==cmdback){
            Midlet.mid.dis.setCurrent(new AccueilPublique(Midlet.mid));
        }
        
        if(c==SELECT_COMMAND){
                sujetact = actualites[this.getSelectedIndex()].getSujet();
                System.out.println("sujet 1 " + sujetact);
                Midlet.mid.dis.setCurrent(new DescriptionActualite("D�tails", List.IMPLICIT));
        }
        

    }
}
