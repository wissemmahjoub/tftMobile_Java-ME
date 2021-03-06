/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import tftmobileMidlet.Midlet;


/**
 *
 * @author wissem
 */
public class Accueil extends Canvas implements CommandListener{
    private Image background;
    private Image selecteur;
    Display disp;
    private Midlet Midlet;
    SujetActualiteList lstp ;
   
    
    int width = getWidth();
    int height = getHeight();
    int x =(width/2);
    int y =(height/4)+15;

    /**
     * The constructor attempts to load the named image and begins a timeout
     * thread. The splash screen can be dismissed with a key press,
     * a pointer press, or a timeout
     * @param Midlet instance of MIDlet
     */
    public Accueil(Midlet Midlet){
        this.Midlet = Midlet;
        lstp = new SujetActualiteList("Actualites", List.IMPLICIT);
        try{
        background = Image.createImage("/Images/page1.jpg");
        selecteur = Image.createImage("/Images/select1.png");
        //Thread t = new Thread(this);
        //t.start();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

   

    public void paint(Graphics g) 
    {

        //set background color to overdraw what ever was previously displayed
        g.setColor(0x000000);
        g.fillRect(0,0, width, height);
        g.drawImage(background, width / 2, height / 2, Graphics.HCENTER | Graphics.VCENTER);    
        g.drawImage(selecteur, x, y, Graphics.HCENTER | Graphics.VCENTER); 
        System.out.println("x= "+x);
    System.out.println("y= "+y);
    }
    
    protected void keyPressed(int keyCode) {
            switch (getGameAction(keyCode)) {

                case UP:
                    if (y!=110){
                    y=y-140;}
                    break;
                    
                case DOWN:
                    if(y!=250){
                    y=y+140;}
                    break;

                    
                case FIRE:
                    
                    
                            
     //########################## Espace Priv� #####################################               
               
               if( y==235)
               {
                    Midlet.mid.dis.setCurrent(new Authentification("Authentification",this.disp));
               }
      //######################### Espace publique  ######################################     
                  
               if( y==95)
               {
                     Midlet.mid.dis.setCurrent(new AccueilPublique(Midlet));
               }            

                    break;
            }
            repaint();
        }

    public void commandAction(Command c, Displayable d) {
  
    }

    }




