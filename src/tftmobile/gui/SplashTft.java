/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import tftmobileMidlet.Midlet;

/**
 *
 * @author wissem
 */
public class SplashTft extends Canvas implements Runnable{
    private Image mImage;
    private Midlet Midlet;
    /**
     * The constructor attempts to load the named image and begins a timeout
     * thread. The splash screen can be dismissed with a key press,
     * a pointer press, or a timeout
     * @param Midlet instance of MIDlet
     */
    public SplashTft(Midlet Midlet){
        this.Midlet = Midlet;
        try{
        mImage = Image.createImage("/Images/TFTSplash.jpg");
        Thread t = new Thread(this);
        t.start();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

   

    protected void paint(Graphics g) {
    int width = getWidth();
        int height = getHeight();
        //set background color to overdraw what ever was previously displayed
        g.setColor(0x000000);
        g.fillRect(0,0, width, height);
        g.drawImage(mImage, width / 2, height / 2,
        Graphics.HCENTER | Graphics.VCENTER);    }
    
    public void dismiss() {
        if (isShown())
                  Midlet.mid.dis.setCurrent(new Authentification("Authentification",Midlet.mid.dis));

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

