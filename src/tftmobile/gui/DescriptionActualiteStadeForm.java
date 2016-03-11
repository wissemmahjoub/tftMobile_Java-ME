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
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tftmobile.connexion.ServerGetter;
import tftmobileMidlet.Midlet;



/**
 *
 * @author yasmi
 */
public class DescriptionActualiteStadeForm extends Form implements CommandListener {
    
    Command cmdnext  = new Command("Statistiques", Command.SCREEN, 0);
    Command cmdback  = new Command("Back", Command.BACK, 0);
    public static String num1, num2;
    
    
    public DescriptionActualiteStadeForm(String title) {
        super(title);
        addCommand(cmdnext);
        addCommand(cmdback);
        append(SujetActualiteList.actualite);
        setCommandListener(this);
       
    }

    public void commandAction(Command c, Displayable d) {
        
            if(c==cmdnext){
                //champs statistiques stades
                num1 =  ServerGetter.GetTextServer("http://localhost/tftmobile/statstade1.php");
                num2 =  ServerGetter.GetTextServer("http://localhost/tftmobile/statstade2.php");
                Midlet.mid.dis.setCurrent(new StatStadeCanvas());
            }
            
            if(c==cmdback){
                 Midlet.mid.dis.setCurrent(new SujetActualiteList("Actualites", List.IMPLICIT));
            }
        }
    }
    

