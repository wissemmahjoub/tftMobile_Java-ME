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
 * @author malox94
 */
public class MenuAchReser extends Canvas implements CommandListener {
    
    static String choix;
     private Image background;
    private Image selecteur;
    Display disp;
    private Midlet Midlet;
    
    
    int width = getWidth();
    int height = getHeight();
    int x =(width/2);
    int y =(height/4)+15;

   public MenuAchReser(Midlet Midlet){
       this.Midlet = Midlet;
       try{
        background = Image.createImage("/Images/Achat_reservation.jpg");
        selecteur = Image.createImage("/Images/select1.png");
        //Thread t = new Thread(this);
        //t.start();
        }
        catch(IOException e){
            e.printStackTrace();
        }
   }
    
    
    protected void paint(Graphics g) {
          g.setColor(0x000000);
        g.fillRect(0,0, width, height);
        g.drawImage(background, width / 2, height / 2, Graphics.HCENTER | Graphics.VCENTER);    
        g.drawImage(selecteur, x, y-22, Graphics.HCENTER | Graphics.VCENTER); 
        System.out.println("x= "+x);
    System.out.println("y= "+y);
    
    }

     protected void keyPressed(int keyCode) {
            switch (getGameAction(keyCode)) {

                case UP:
                    if (y!=110){
                    y=y-173;}
                    break;
                    
                case DOWN:
                    if(y!=250){
                    y=y+173;}
                    break;

                    
                case FIRE:
                    
                    
                            
     //########################## Espace Privé #####################################               
               
//               if( y==200)
//               {
//                    Midlet.mid.dis.setCurrent(new Authentification("Authentification",this.disp));
//               }
      //######################### Espace publique  ######################################     
                  
               if( y==95)
               {
                     choix = "achat";
                     Midlet.mid.dis.setCurrent(new ListMatchs("Acheter Ticket",List.IMPLICIT));
                     
               }else{
                    choix = "reservation";
                    Midlet.mid.dis.setCurrent(new ListMatchs("Reserver Ticket",List.IMPLICIT));
                  
               }            

                    break;
            }
            repaint();
        }

    public void commandAction(Command c, Displayable d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
