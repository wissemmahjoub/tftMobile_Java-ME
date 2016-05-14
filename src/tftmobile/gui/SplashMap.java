/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;

import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import tftmobileMidlet.Midlet;

/**
 *
 * @author yasmi
 */
public class SplashMap extends Canvas implements Runnable{
    private Image mImage;
    private Midlet Midlet;
    Displayable d;
    /**
     * The constructor attempts to load the named image and begins a timeout
     * thread. The splash screen can be dismissed with a key press,
     * a pointer press, or a timeout
     * @param Midlet instance of MIDlet
     */
    public SplashMap(Midlet Midlet){
        this.Midlet = Midlet;
        try{
        mImage = Image.createImage("/Images/icon.png");
        Thread t = new Thread(this);
        t.start();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

   

    protected void paint(Graphics g) {
        int w = getWidth();
        int h = getHeight();
        //set background color to overdraw what ever was previously displayed
        g.setColor(0x668cff);
        g.fillRect(0,0, w, h);
        g.drawImage(mImage, w / 2, h / 2,
        Graphics.HCENTER | Graphics.VCENTER);    
    }
    
    public void dismiss() {
        if (isShown())
                   Midlet.dis.setCurrent(new GoogleMapsMarkerCanvas(Midlet,d));

    }

    public void run() {
    try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException");
            e.printStackTrace();
        }
        dismiss();  
    }
    /**
     * A key release event triggers the dismiss()
     * method to be called.
     */

}

