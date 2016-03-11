/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tftmobile.gui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import tftmobileMidlet.Midlet;

/**
 *
 * @author yasmi
 */
public class DescriptionActualiteEventForm extends Form implements CommandListener {

    Command cmdnext  = new Command("Statistiques", Command.SCREEN, 0);
    Command cmdback  = new Command("Back", Command.BACK, 0);
    
    public DescriptionActualiteEventForm(String title) {
        super(title);
        addCommand(cmdnext);
        addCommand(cmdback);
        setCommandListener(this);
        append(SujetActualiteList.actualite);
    }

    public void commandAction(Command c, Displayable d) {
        if(c == cmdback){
            Midlet.mid.dis.setCurrent(new Accueil(Midlet.mid));

        }
    }

}
