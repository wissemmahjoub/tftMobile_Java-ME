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
import tftmobile.entite.Matchs;
import tftmobile.entite.Personne;
import tftmobile.entite.Ticket;
import tftmobileMidlet.Midlet;
import tftmobile.handler.MembreHandler;

/**
 *
 * @author malox94
 */
public class Achat extends Form implements CommandListener{
     HttpConnection hc;
    DataInputStream dis;
    StringBuffer sb;
 String url = "http://localhost/TFTMobile/Achat/insert.php?";
 String url2 = "http://localhost/TFTMobile/Achat/UpdateJeton.php?";
    Matchs matchs ;
    Personne personne;
    Ticket ticket;
     Command cmdBack = new Command("Back", Command.SCREEN, 0);
      Command cmdAchat = new Command("Acheter", Command.SCREEN, 0);
//    ListMatchs ls = new ListMatchs("Reservation", 0);
    public Achat(String title) {
        super(title);
        // this.matchs = matchs;
      
       // System.out.println(matchs.getNameJoueur1());
        //append(matchs.getNameJoueur1()+ " " + matchs.getLastNameJoueur1() + " VS " + matchs.getNameJoueur2() + " " + matchs.getLastNameJoueur2());
       // append("Le " + matchs.getDateMatch());
        append("Vous avez actuellement " + ListMatchs.p.getNbrjeton());
        addCommand(cmdBack);
        setCommandListener(this);
        
    }
     public Achat(String title, Matchs matchs  , Ticket tickets) {
        super(title);
        // this.matchs = matchs;
         this.matchs = matchs;
        
         this.ticket = tickets;
        System.out.println(matchs.getNameJoueur1());
        append(matchs.getNameJoueur1()+ " " + matchs.getLastNameJoueur1() + " VS " + matchs.getNameJoueur2() + " " + matchs.getLastNameJoueur2());
        append("Le " + matchs.getDateMatch());
        append("Vous avez actuellement " + ListMatchs.p.getNbrjeton());
        append("Le prix du ticket est de " + ticket.getPrix());
        addCommand(cmdBack);
        setCommandListener(this);
        addCommand(cmdAchat);
        setCommandListener(this);
        
    }
    
    
    public void commandAction(Command c, Displayable d) {
        if (c==cmdAchat){
            
            if (ListMatchs.p.getNbrjeton() >= ticket.getNbrticket()){
            
            try {
                System.out.println("aaaaaaaaaabbbbbbbbb");
                hc = (HttpConnection) Connector.
                open(url + "membre=" + ListMatchs.p.getIdpersonne() + "&ticket=" + matchs.getIdticket() );
                System.out.println(ListMatchs.p.getIdpersonne() + " test " + matchs.getIdticket());
                dis = hc.openDataInputStream();

                  hc = (HttpConnection) Connector.
                open(url2 + "membre=" + ListMatchs.p.getIdpersonne()  + "&jeton=" + (ListMatchs.p.getNbrjeton() - ticket.getNbrticket()) );
                System.out.println(ListMatchs.p.getIdpersonne() + " test " + matchs.getIdticket());
                dis = hc.openDataInputStream();
                
                // Alert succ�s de l'achat
                Alert a = new Alert("Succes", "Votre achat a �t� effectu� avec succ�s, Votre solde est maintenant de  " + (personne.getNbrjeton() - ticket.getNbrticket()), null, AlertType.CONFIRMATION);
                a.setTimeout(5000);
                Midlet.mid.dis.setCurrent(a);
                
                
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
        
        }else {
              Alert a = new Alert("Echec", "Vous n'avez pas suffisemment de solde pour faire cet achat ", null, AlertType.CONFIRMATION);
                a.setTimeout(5000);
                Midlet.mid.dis.setCurrent(a);
        }}
   if (c==cmdBack){
      Midlet.mid.dis.setCurrent(new ListMatchs("Achat",List.IMPLICIT));
   }
    }
}
