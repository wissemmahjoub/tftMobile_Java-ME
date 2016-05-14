/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;

import tftmobile.entite.Personne;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;
import tftmobileMidlet.Midlet;

/**
 *
 * @author wissem
 */
public class Authentification extends Form implements CommandListener, Runnable{
    private Image image1;
     StringBuffer sb;
     Personne pers;
     Display disp ;
  
      String url = "http://localhost/tftmobile/fichier.php?";
    
    Command cmNewsLetter = new Command("NewsLetter", Command.BACK, 1);
    Command cmlogin = new Command("Login", Command.SCREEN, 0);
    Command cmdback = new Command("Retour", Command.BACK, 1);
    
    TextField login= new TextField("Login", null, 500, TextField.ANY);
    TextField password = new TextField("Mot de passe ", null, 500, TextField.PASSWORD);
    public Authentification(String title,Display disp) {
        super(title);
        try {
          image1 = Image.createImage("/Images/authentif.jpg");
       
         } catch (IOException ex) {
          ex.printStackTrace();
      }
     
        append(image1); 
        append(login);
        append(password);
        addCommand(cmlogin);
        addCommand(cmdback);
        addCommand(cmNewsLetter);
        setCommandListener(this);
        
    }

    public void commandAction(Command c, Displayable d) {
      

       if(c==cmNewsLetter){
           Midlet.mid.dis.setCurrent(new AbonneNewsLetter("NewsLetter",Midlet.mid.dis));
       }
       if (c == cmlogin) {
            Thread th = new Thread(this);
            th.start();
        }
       
       if(c==cmdback){
            Midlet.mid.dis.setCurrent(new Accueil(Midlet.mid));
        }

   }

    public void run() {
        
         url += "&login=" + login.getString().trim() + "&password=" + password.getString().trim();
        try {
            HttpConnection hc = (HttpConnection) Connector.open(url);
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());

            int ch;
            sb = new StringBuffer();
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
             String[] tab = Split(sb.toString().trim(), "+");
            System.out.println(tab[0]);
              
        
         if(sb.toString().trim().equals("no")/*||(password.getString()=="")*/) {    
             Alert erreur = new Alert("Attention!","Veuillez verifier vos informations", null, AlertType.ERROR);
             
               Midlet.mid.dis.setCurrent(erreur, this); 
              login.setString("");
              password.setString("");
         } 
         
         else if (login.getString().equals("")||(password.getString().equals("")))
         {     Alert logiinn = new Alert("ATTENTION ","veuillez remplir tous les champs ", null, AlertType.CONFIRMATION);
               Midlet.mid.dis.setCurrent(logiinn, this); 
         }
         else {
                
               Alert a = new Alert("Bienvenue ",login.getString(), null, AlertType.CONFIRMATION);
               Midlet.mid.dis.setCurrent(a, this); 
              
              Midlet.mid.dis.setCurrent(new AccueilPrive(Midlet.mid));
   }
          
         
        }  catch (IOException ex) {

                }
    

    }
    
    public static String[] Split(String splitStr, String delimiter) {
     StringBuffer token = new StringBuffer();
     Vector tokens = new Vector();
     // split
     char[] chars = splitStr.toCharArray();
     for (int i=0; i < chars.length; i++) {
         if (delimiter.indexOf(chars[i]) != -1) {
             // we bumbed into a delimiter
             if (token.length() > 0) {
                 tokens.addElement(token.toString());
                 token.setLength(0);
             }
         } else {
             token.append(chars[i]);
         }
     }
     // don't forget the "tail"...
     if (token.length() > 0) {
         tokens.addElement(token.toString());
     }
     // convert the vector into an array
     String[] splitArray = new String[tokens.size()];
     for (int i=0; i < splitArray.length; i++) {
         splitArray[i] = (String)tokens.elementAt(i);
     }
     return splitArray;
 }}
