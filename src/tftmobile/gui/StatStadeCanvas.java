/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import tftmobileMidlet.Midlet;

/**
 *
 * @author yasmi
 */
public class StatStadeCanvas extends Canvas implements CommandListener {
    
  int n1,n2;
  private Command cmdback = new Command("Back", Command.EXIT, 0);
  
  private long result;

    
    
    
    public StatStadeCanvas(){
        this.addCommand(cmdback);
        n1 = Integer.parseInt(DescriptionActualiteStadeForm.num1);
        n2 = Integer.parseInt(DescriptionActualiteStadeForm.num2);
        result = n1 + n2 ;
        
      this.setCommandListener(this);
    }
    protected void paint(Graphics g) {
        
        int w = getWidth();
        int h = getHeight();
        g.setColor(0xffffff);
        g.fillRect( 0,0,w,h );
        h = Math.min( w, h );
        
        long mf = 100000000;
        int ang1 = (int)(( (n1*(36000*mf/result)) +50*mf)/(100*mf)) ;
        int orig=90;
        //cercles
        g.setColor(0xe6e600);
        g.fillArc(0,0,h,h, orig, (int)ang1);
        g.setColor(0xffa64d);
        g.fillArc(0,0,h,h, (int)(orig+ang1), (int)(360-ang1) );
        
        //valeurs
        g.setColor(0x000000);
        g.drawString(n1+"  ", h/2-10, h/2-10,
                Graphics.BASELINE|Graphics.RIGHT);
        g.drawString(n2+" ", h/2+20, h/2-10,
                Graphics.BASELINE|Graphics.LEFT);
        g.drawString("Total "+(n1+n2), h/2, h/2,
                Graphics.TOP|Graphics.HCENTER);

        //rectangles
        g.setColor(0xe6e600);
        g.fillRect(15, h/2+125, w/2-90, 15);
        g.setColor(0xffa64d);
        g.fillRect(15, h/2+155, w/2-90, 15);
        
        //explications
        g.setColor(0x000000);
        g.drawString("Stades disponibles", w/2+55, h/2+142,
                Graphics.BOTTOM|Graphics.RIGHT);
        g.setColor(0x000000);
        g.drawString("Stades non disponibles", w/2+80, h/2+172,
                Graphics.BOTTOM|Graphics.RIGHT);
    }
    public void commandAction( Command c, Displayable d) {

        if(c==cmdback){
            Midlet.mid.dis.setCurrent(new DescriptionActualiteStadeForm("Description"));
        }
    }
  } // end CusCanvas
  