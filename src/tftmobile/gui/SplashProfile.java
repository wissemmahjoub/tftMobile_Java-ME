/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tftmobile.entite.Personne;
import tftmobile.handler.ProfileHandler;
import midlet.Midlet;

/**
 *
 * @author wissem
 */
public class SplashProfile extends Canvas implements Runnable{
    private Image mImage;
    private Midlet Midlet;
    /**
     * The constructor attempts to load the named image and begins a timeout
     * thread. The splash screen can be dismissed with a key press,
     * a pointer press, or a timeout
     * @param Midlet instance of MIDlet
     */
    public SplashProfile(Midlet Midlet){
        this.Midlet = Midlet;
        try{
        mImage = Image.createImage("/Images/TFTSplashh.jpg");
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
         Midlet.INSTANCE.disp.setCurrent(new ProfilePortail());
    }

    public void run() {
   
        //Affichage des matchs
            ProfileHandler profile = new ProfileHandler();
            try{
                
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/tftmobile/Membre/selectAvance.php?id=1");//people.xml est un exemple
            System.out.println(hc.getFile());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, profile);
               // display the result
            
            Personne person[] = profile.getPersonne();
         ProfilePortail.nomComplet = person[0].getNom() + " " + person[0].getPrenom();
          
        
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        
        
        
        
        
      
        dismiss();  
    }
    /**
     * A key release event triggers the dismiss()
     * method to be called.
     */

}

