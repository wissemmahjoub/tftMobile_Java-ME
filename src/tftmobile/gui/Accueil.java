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
    Command deconnection = new Command("Deconnexion ", Command.SCREEN, 0);
    
    int width = getWidth();
    int height = getHeight();
    int x =(width/3)+9;
    int y =(height/3)+4;

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
         addCommand(deconnection);
         setCommandListener(this);
        background = Image.createImage("/Images/Accueuilfinal.jpg");
        selecteur = Image.createImage("/Images/selecteur.png");
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
                    y=y-70;}
                    break;
                    
                case DOWN:
                    if(y!=250){
                    y=y+70;}
                    break;
                case LEFT:
                    if(x!=89){
                    x=x-70;}
                    break;
                case RIGHT:
                    if(x!=159){
                    x=x+70;}
                    break;
                    
                case FIRE:
     //########################## Profil #####################################               
               //ici on va faire setCurrent-->(profil)     
               if(x==89 && y==110)
               {
                    Midlet.mid.dis.setCurrent(new Inscription("Inscription",this.disp));
               }
      //######################### Mail ######################################     
                //ici on va faire setCurrnt-->(Mail)     
               if(x==89 && y==180)
               {
                    Midlet.mid.dis.setCurrent(new Inscription("Inscription",this.disp));
               }
      //####################### News ########################################     
                    
                 
               if(x==89 && y==250)
               {
                 Midlet.mid.dis.setCurrent(lstp);  
               }
       //####################### Video ########################################  
                //ici on va faire setCurrent-->(Video)     
               if(x==159 && y==110)
               {
                    Midlet.mid.dis.setCurrent(new Inscription("Inscription",this.disp));
               }
       //######################## Achat & reservation #########################  
                //ici on va faire setCurrent-->(Achat)     
               if(x==159 && y==180)
               {
                    Midlet.mid.dis.setCurrent(new Inscription("Inscription",this.disp));
               }
        //######################CONTACTER FEDERATION ##########################  
                //ici on va faire setCurrent-->(CONTACTER FEDERATION)     
               if(x==159 && y==250)
               {
                    Midlet.mid.dis.setCurrent(new Inscription("Inscription",this.disp));
               }
                    
               
                    
                    break;
            }
            repaint();
        }

    public void commandAction(Command c, Displayable d) {
    if (c == deconnection){ 
        Midlet.mid.dis.setCurrent(new Authentification("Authentification",this.disp));
                        }
    }

    }




