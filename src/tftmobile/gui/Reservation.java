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
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import tftmobile.entite.Match;
import tftmobile.entite.Personne;
import tftmobileMidlet.Midlet;

/**
 *
 * @author malox94
 */
public class Reservation extends Form implements CommandListener{
     HttpConnection hc;
    DataInputStream dis;
    StringBuffer sb;
 String url = "http://localhost/TFTMobile/Membre/insert.php?";
    Match matchs ;
    Personne personne;
     Command cmdBack = new Command("Back", Command.SCREEN, 0);
      Command cmdAcheter = new Command("Acheter", Command.SCREEN, 0);
      Command cmdReserver = new Command("Reserver", Command.SCREEN, 0);
//    ListMatchs ls = new ListMatchs("Reservation", 0);
    public Reservation(String title, Personne personne) {
        super(title);
        // this.matchs = matchs;
         this.personne = personne;
       // System.out.println(matchs.getNameJoueur1());
        //append(matchs.getNameJoueur1()+ " " + matchs.getLastNameJoueur1() + " VS " + matchs.getNameJoueur2() + " " + matchs.getLastNameJoueur2());
       // append("Le " + matchs.getDateMatch());
        append("Vous avez actuellement " + personne.getNbrjeton());
        addCommand(cmdBack);
        setCommandListener(this);
        
    }
     public Reservation(String title, Match matchs , Personne personnes) {
        super(title);
        // this.matchs = matchs;
         this.matchs = matchs;
         this.personne = personnes;
        System.out.println(matchs.getNameJoueur1());
        append(matchs.getNameJoueur1()+ " " + matchs.getLastNameJoueur1() + " VS " + matchs.getNameJoueur2() + " " + matchs.getLastNameJoueur2());
        append("Le " + matchs.getDateMatch());
        append("Vous avez actuellement " + personne.getNbrjeton());
        addCommand(cmdBack);
        setCommandListener(this);
          addCommand(cmdAcheter);
        setCommandListener(this);
        addCommand(cmdReserver);
        setCommandListener(this);
        
    }
    
    
    public void commandAction(Command c, Displayable d) {
        if (c==cmdReserver){
           
            try {
              
              

                hc = (HttpConnection) Connector.
                        open(url + "membre=" + personne.getIdpersonne() + "&ticket=" + matchs.getIdticket() );
                dis = hc.openDataInputStream();

                int ascii;
                sb = new StringBuffer();

                while ((ascii = dis.read()) != -1) {

                    sb.append((char) ascii);

                }

//                if (sb.toString().equals("successfully added")) {
//                    Alert a = new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
//                    a.setTimeout(3000);
//                    Midlet.mMidlet.disp.setCurrent(a);
//                } else {
//                    Alert a = new Alert("Information", sb.toString(), null, AlertType.ERROR);
//                    a.setTimeout(3000);
//                    Midlet.mMidlet.disp.setCurrent(a);
//                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
   if (c==cmdBack){
      Midlet.mid.dis.setCurrent(new ListMatchs("Reservation",List.IMPLICIT));
   }
    }
}
