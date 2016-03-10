/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobileMidlet;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;
import tftmobile.gui.Authentification;
import tftmobile.gui.SplashTft;


/**
 * @author wissem
 */
public class Midlet extends MIDlet {
 public static Midlet mid;
   public  Display dis = Display.getDisplay(this);
    public void startApp() 
    {
     mid=this;
     dis.setCurrent(new SplashTft(this));
    
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
