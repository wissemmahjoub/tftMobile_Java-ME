/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;

import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Ticker;
import tftmobileMidlet.Midlet;

/**
 *
 * @author yasmi
 */
public class PariStatistique extends Canvas implements CommandListener {
    
  int nbtot,nbgain;
  private Command cmdback = new Command("Back", Command.EXIT, 0);
  private int nbperte;

    
    
    
    public PariStatistique(){
      
        this.addCommand(cmdback);
        
             nbtot = Integer.parseInt(ListeMatchsJoueurs.nbtot);
             nbgain = Integer.parseInt(ListeMatchsJoueurs.nbgain);
             System.out.println(nbtot+"  ************   "+nbgain);
             nbperte = nbtot - nbgain ;
             y= nbperte;
        Timer timer = new Timer();
        timer.schedule(timerTask, 5, 5);
        
      this.setCommandListener(this);
    }
    
    TimerTask timerTask = new TimerTask() {

    public void run() {
      animations();
    }

   
};
    int x = 0 ;


  int y = nbperte;
     private void animations() {
         System.out.println("dsgfsdgs  "+nbtot);
            System.out.println("dsgfsdgs  "+nbperte);
                        System.out.println("dsgfsdgs  "+nbgain);
         System.out.println("dsgfsdgs"+(float)y);
         System.out.println((((float)y*(((float)360)/((float)nbtot)))));
         
      x++;
      if(x>=((float)(y*(((float)360)/((float)nbtot))))){
          System.out.println(x+" *****  "+(y*(((float)360)/((float)nbtot))));
      timerTask.cancel();
      }
     
      repaint();
    }
    protected void paint(Graphics g) {
         
        int w = getWidth();
        int h = getHeight();
        g.setColor(0xCEECF5);
        g.fillRect( 0,0,w,h );
    
       g.setColor(0x58D3F7);
       g.fillArc(0, getHeight()/2-getWidth()/2, getWidth() , getWidth(), 0, 360);
       
       g.setColor(0xBDBDBD);
       g.fillArc(0, getHeight()/2-getWidth()/2, getWidth(), getWidth(), 0   , x);
       
        //valeurs
        g.setColor(0x610B21);
        g.drawString(nbgain+"  ",h/2-50, h/2-10,
                Graphics.BASELINE|Graphics.RIGHT);
        g.drawString(nbperte+" ", h/2+20, h/2-10,
                Graphics.BASELINE|Graphics.LEFT);
        g.drawString(ListeJoueurs.nomj+ " "+ ListeJoueurs.prenomj + " " + "Total:"+(nbgain+nbperte), h/2-15, h/2-148,
                Graphics.TOP|Graphics.HCENTER);
       
       //rectangles
        g.setColor(0x58D3F7);
        g.fillRect(15, h/2+125, w/2-90, 15);
        g.setColor(0xBDBDBD);
       g.fillRect(150, h/2+125, w/2-90, 15);
        
        
        //explications
        g.setColor(0x610B21);
        g.drawString("Gain", w/2-43, h/2+140,
                Graphics.BOTTOM|Graphics.RIGHT);
        g.setColor(0x610B21);
        g.drawString("Perte", w/2+100, h/2+140,
                Graphics.BOTTOM|Graphics.RIGHT);
         
//        int w = getWidth();
//        int h = getHeight();
//        g.setColor(0xD8D8D8);
//        g.fillRect( 0,0,w,h );
//        h = Math.min( w, h );
//        
//        long mf = 100000000;
//        int ang1 = (int)(( (nbgain*(36000*mf/nbtot)) +50*mf)/(100*mf)) ;
//        int orig=90;
//        //cercles
//        g.setColor(0x33adff);
//        g.fillArc(0,0,h,h, orig, (int)ang1);
////        g.setColor(0X66c2ff);
//        g.setColor(0x007399);
//        g.fillArc(0,0,h,h, (int)(orig+ang1), (int)(360-ang1) );
//        
//        //valeurs
//        g.setColor(0x610B21);
//        g.drawString(nbgain+"  ",h/2-10, h/2-10,
//                Graphics.BASELINE|Graphics.RIGHT);
//        g.drawString(nbperte+" ", h/2+20, h/2-10,
//                Graphics.BASELINE|Graphics.LEFT);
//        g.drawString(ListeJoueurs.nomj+ " "+ ListeJoueurs.prenomj + "\n"+"Total:"+(nbgain+nbperte), h/2, h/2,
//                Graphics.TOP|Graphics.HCENTER);
//
//        //rectangles
//        g.setColor(0x33adff);
//        g.fillRect(15, h/2+125, w/2-90, 15);
//        g.setColor(0x007399);
//        g.fillRect(15, h/2+155, w/2-90, 15);
//        
//        //explications
//        g.setColor(0x610B21);
//        g.drawString("Gain", w/2-41, h/2+142,
//                Graphics.BOTTOM|Graphics.RIGHT);
//        g.setColor(0x610B21);
//        g.drawString("Perte", w/2-36, h/2+169,
//                Graphics.BOTTOM|Graphics.RIGHT);
        
        
        
        
        
      
        
        
    }
    public void commandAction( Command c, Displayable d) {

        if(c==cmdback){
            Midlet.mid.dis.setCurrent(new ListeMatchsJoueurs("Liste matchs par joueurs", List.IMPLICIT));
        }
    }
  } // end CusCanvas
  