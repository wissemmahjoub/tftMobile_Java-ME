/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import tftmobileMidlet.Midlet;

/**
 *
 * @author wissem
 */
public class AbonneNewsLetter extends Form implements CommandListener, Runnable {
    
    HttpConnection ht;
    DataInputStream dt;
    int ch;
    Display disp;
    
    TextField Mail= new TextField("Email","yourmail@gmail.com" ,500, TextField.EMAILADDR);

    Command abonner= new Command("S'abonner", Command.SCREEN, 0);
    Command back= new Command("Retour", Command.SCREEN, 1);
    Alert alt = new Alert(null, null, null, AlertType.CONFIRMATION);

  
     
    public AbonneNewsLetter(String title,Display disp) {
        super(title);
        append(Mail);
        addCommand(abonner);
        addCommand(back);
        setCommandListener(this);
    }

    public AbonneNewsLetter(String title) {
         super(title);

        }

    public void commandAction(Command c, Displayable d) {
        if(c==abonner){
            
            Thread th = new Thread(this);
            th.start();
            
    }
         
        if(c==back){
           
        Midlet.mid.dis.setCurrent(new Authentification("Authentification",this.disp));
  
                }
    }
    public void run() {
         
       
        try {
         
Calendar cal= Calendar.getInstance();

StringBuffer str = new StringBuffer();
String Mail1=Mail.getString().trim();


  ht =(HttpConnection)Connector.open("http://localhost/tftmobile/abonne.php?" + "abonne=" + Mail1 );

           
              dt = ht.openDataInputStream();
               InputStreamReader r = new InputStreamReader(dt, "UTF-8");
 
            while ((ch = dt.read())!= -1) {
                str.append((char) ch);

            }
            
            System.out.print("abonnement avec Succes");
            Alert a = new Alert(Mail1 ," est abonné(e) avec succÃ©s ", null, AlertType.CONFIRMATION);
            Midlet.mid.dis.setCurrent(a, this); 
            Thread.sleep(1000);
            Midlet.mid.dis.setCurrent(new Authentification("Authentification ",Midlet.mid.dis));          
    
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) { 
        ex.printStackTrace();
    } 
    }
    }
    

