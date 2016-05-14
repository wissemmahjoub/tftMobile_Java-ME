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
public class AccueilPrive extends Canvas implements CommandListener{
    private Image background;
    private Image selecteur;
    Display disp;
    private Midlet Midlet;
    SujetActualiteList lstp ;
    Command cmdback = new Command("Retour", Command.BACK, 1);
 
    
    int width = getWidth();
    int height = getHeight();
    int  x = (width/3)-5;
    int  y = (height/2)-35;

    /**
     * The constructor attempts to load the named image and begins a timeout
     * thread. The splash screen can be dismissed with a key press,
     * a pointer press, or a timeout
     * @param Midlet instance of MIDlet
     */
    public AccueilPrive(Midlet Midlet){
        this.Midlet = Midlet;
        lstp = new SujetActualiteList("Actualites", List.IMPLICIT);
        try{
       
        background = Image.createImage("/Images/private.jpg");
        selecteur = Image.createImage("/Images/select2.png");
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
                    if (y!=125){
                    y=y-90;}
                    break;
                    
                case DOWN:
                    if(y!=215){
                    y=y+90;}
                    break;
                case LEFT:
                    if(x!=75){
                    x=x-100;}
                    break;
                case RIGHT:
                    if(x!=175){
                    x=x+100;}
                    break;
                    
                case FIRE:
                    
                    

         
     //########################## Ticket #####################################               
               //ici on va faire Midlet.mid.dis.setCurrent-->(Ticket)     
               if(x==75 && y==125)
               {
                   System.out.println("-Ticket- Selected");
                 //   Midlet.mid.dis.setCurrent(new Inscription("Inscription",this.disp));
               }
      //######################### Statistique ######################################     
                // Midlet.mid.dis.setCurrent(new Inscription("Inscription",this.disp));
               if(x==175 && y==125)
               {
                    System.out.println("-Statistique- Selected");
                    Midlet.mid.dis.setCurrent(new ListeJoueurs("Liste des joueurs", List.IMPLICIT));
               }
      //####################### Reclamation ########################################     
                  //ici on va faire Midlet.mid.dis.setCurrent-->(Reclamation)   
                 
               if(x==75 && y==215)
               {
                   System.out.println("-Reclamation- Selecteted");
                 Midlet.mid.dis.setCurrent(new Mail("Reclamation", disp));

               }
       //####################### Deconneter ########################################  
                //ici on va faire Midlet.mid.dis.setCurrent-->(Deconneter)     
               if(x==175 && y==215)
               {
                   System.out.println("-Deconneter- Selected");
                  Midlet.mid.dis.setCurrent(new Authentification("Authentification",this.disp));
               }

                    break;
            }
            repaint();
        }

    public void commandAction(Command c, Displayable d) {
   
        if(c==cmdback){
            Midlet.mid.dis.setCurrent(new Accueil(Midlet.mid));
        }
        
    }

    }




