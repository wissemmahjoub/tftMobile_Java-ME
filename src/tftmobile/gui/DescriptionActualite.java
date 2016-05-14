/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tftmobile.entite.Actualite;
import tftmobile.handler.ActualiteHandler;
import tftmobileMidlet.Midlet;



/**
 *
 * @author yasmi
 */
public class DescriptionActualite extends List implements Runnable,CommandListener {
    
    private Actualite[] actualites;
    private Command cmdback  = new Command("Retour", Command.SCREEN, 0);
    private Display disp;
    private StringBuffer sb;
    
       public DescriptionActualite(String title, int listType) {
        super(title, listType);
 
        addCommand(cmdback);
        setCommandListener(this);
        Thread th=new Thread(this);
        th.start();
    }

    public void run() {
  
            try {  
                    System.out.println(SujetActualiteList.sujetact);
                    ActualiteHandler acthandler =new ActualiteHandler();
                    SAXParser parser = SAXParserFactory.newInstance().newSAXParser();

                    HttpConnection hc = (HttpConnection) Connector.open("http://localhost/tftmobile/descriptionActualite.php?sujet="+SujetActualiteList.sujetact);
               
                    
                    DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                    parser.parse(dis, acthandler);
                
                        actualites = acthandler.getActualite();
                        
                        for (int i = 0; i < actualites.length; i++) {
                          System.out.println("actualites["+i+"].getDescription()");
                          
 
                          append(actualites[i].getDescription() + " ", null);
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
        
//        if(c==cmdnext){
//             for (int i = 0 ; i <this.size();i++){
//            if(this.isSelected(i)){
//                if(this.getString(i).equals("Stades")){
//                         actualite = ServerGetter.GetTextServer("http://localhost/javame/staedeactualite.txt");
//                         Midlet.instance.disp.setCurrent(new DescriptionActualiteStadeForm("Description"));
//                                                      }
//                
//                if(this.getString(i).equals("Evenements")){
//                         actualite = ServerGetter.GetTextServer("http://localhost/javame/eventact.txt");
//                         Midlet.instance.disp.setCurrent(new DescriptionActualiteEventForm("Description"));
//                                                      }
//                                  }
//                                                 }
//}
        if(c==cmdback){
            Midlet.mid.dis.setCurrent(new SujetActualiteList("Sujets Actualités", List.IMPLICIT));
        }
        

    }
}
