/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.ressources;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;

/**
 * @author wissem
 */
public class Midlet extends MIDlet implements CommandListener{
    
 private final Command cmdNextVersPage2 = new Command("Suivant", Command.SCREEN, 0);
 private final Command cmdNextVersPage1 = new Command("back", Command.BACK, 0);
  private final Command cm_exit = new Command("exit", Command.EXIT, 0);
    
    Display disp =  Display.getDisplay(this);
    Form f1= new Form("page_chams");
    Form f2= new Form("page_2");
    TextField zonetext1 =    new TextField("remplire_champ : ", "", 50, TextField.ANY);
    public void startApp() 
    {    
        disp.setCurrent(f1);
        f1.addCommand(cmdNextVersPage2);
        f1.addCommand(cm_exit);
        f1.append(zonetext1);
        f1.setCommandListener(this);
       // TextField z2 = new TextField("label", zonetext1.getString(), 50, TextField.ANY);

      
        f2.addCommand(cmdNextVersPage1);
        f2.setCommandListener(this);
 //       f2.append(s1);
//        String c= zonetext1.getString();
//        t1.setString(c);
//       
//       f2.append(c);
    //   f2.append(t1.getString());        
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
      if (c==cm_exit)
      {notifyDestroyed();}
       if (c==cmdNextVersPage1)
      {
                   
      }
       
       if (c==cmdNextVersPage2)
      {  StringItem s1 = new StringItem("", zonetext1.getString());
            f2.append(s1);
      disp.setCurrent(f1);
          disp.setCurrent(f2);}
       
       
       
    }
}
