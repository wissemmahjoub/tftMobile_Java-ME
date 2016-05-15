/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;
import tftmobileMidlet.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tftmobile.entite.Personne;
import tftmobile.gui.Authentification;
import tftmobile.gui.ProfileGUI;
import tftmobile.handler.ProfileHandler;

/**
 *
 * @author Mustapha
 */
public class ProfilePortail extends Canvas implements CommandListener, Runnable{
    
    Image photo;
    Image bgProfile,bgProfile_parier,bgProfile_prof,bgProfile_rslt;
    int w = getWidth();
    int h = getHeight();
    Graphics grph;
    TextField lol;
         public static String nomComplet ="Rien";
      private boolean aboutSelected= false;
      private boolean gameSelected = false;
      private boolean exitSelected = false;
      private int cursor = -1;
      private boolean [] tabSelection = {false,false,false};
      
    Command exitCmd = new Command ("Retour",Command.BACK,1);
    protected void paint(Graphics g) {
       
        grph = g;
       
        // Background
        if (tabSelection[0])
        {
            g.drawImage(bgProfile_prof, 0, 0, Graphics.TOP | Graphics.LEFT);
        }else if (tabSelection[1])
        {
            g.drawImage(bgProfile_parier, 0, 0, Graphics.TOP | Graphics.LEFT);
        }else if (tabSelection[2])
        {
            g.drawImage(bgProfile_rslt, 0, 0, Graphics.TOP | Graphics.LEFT);
        }else
        {
            g.drawImage(bgProfile, 0, 0, Graphics.TOP | Graphics.LEFT);
        }
        
        
    //Contour & Image de profile
        g.setColor(0xffffff);
        g.drawString(nomComplet, (w/2) - 50,40,Graphics.LEFT|Graphics.BASELINE);
        g.fillRect((w/2) - 52, 48, 104, 104);
        g.drawImage(resizeImage(photo,100,100), (w/2) - 50, 50, Graphics.TOP | Graphics.LEFT);
       
    }
    
    
private Image resizeImage(Image src,int screenWidth, int screenHeight) {
      int srcWidth = src.getWidth();
      int srcHeight = src.getHeight();
      Image tmp = Image.createImage(screenWidth, srcHeight);
      Graphics g = tmp.getGraphics();
      int ratio = (srcWidth << 16) / screenWidth;
      int pos = ratio/2;

      //Horizontal Resize        

      for (int x = 0; x < screenWidth; x++) {
          g.setClip(x, 0, 1, srcHeight);
          g.drawImage(src, x - (pos >> 16), 0, Graphics.LEFT | Graphics.TOP);
          pos += ratio;
      }

      Image resizedImage = Image.createImage(screenWidth, screenHeight);
      g = resizedImage.getGraphics();
      ratio = (srcHeight << 16) / screenHeight;
      pos = ratio/2;        

      //Vertical resize

      for (int y = 0; y < screenHeight; y++) {
          g.setClip(0, y, screenWidth, 1);
          g.drawImage(tmp, 0, y - (pos >> 16), Graphics.LEFT | Graphics.TOP);
          pos += ratio;
      }
      return resizedImage;

  }//resize image  


    public ProfilePortail() {
       Thread th = new Thread(this);
        th.start();
        
        try {
            bgProfile = Image.createImage("/tftmobile/ressources/bgProfile.png");
            bgProfile_parier = Image.createImage("/tftmobile/ressources/bgProfile_parier.png");
            bgProfile_prof = Image.createImage("/tftmobile/ressources/bgProfile_prof.png");
            bgProfile_rslt = Image.createImage("/tftmobile/ressources/bgProfile_rslt.png");
            
            //depuis la base
            photo = Image.createImage("/tftmobile/ressources/4.jpg");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        setCommandListener(this);
        addCommand(exitCmd);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == exitCmd)
        {
          Midlet.mid.dis.setCurrent(new AccueilPrive(tftmobileMidlet.Midlet.mid));
        }
    
    }
    
   
    protected void keyPressed(int keyCode)
        {
            
            if (keyCode == -1)
            {
                   if (cursor == -1)
                {tabSelection[0] = true; 
                cursor =0;
                }
                  
                  if (cursor == 2)
                  {
                       for (int i = 0 ; i < tabSelection.length;i++)
                    {
                        tabSelection[i] = false;
                    }
                      cursor = 1;
                      tabSelection[cursor] = true;   
                 
                  }
                   repaint();
            }else if (keyCode == -2)
            {
                  if (cursor == -1)
                {tabSelection[0] = true; 
                cursor =0;
                }
                  
                 if (cursor == 1)
                  {
                      for (int i = 0 ; i < tabSelection.length;i++)
                    {
                        tabSelection[i] = false;
                    }
                      cursor = 2;
                      tabSelection[cursor] = true;  
                        
                  }
                 repaint();
            }else if (keyCode == -3)
            {
             if (cursor == -1)
                {tabSelection[0] = true; 
                cursor =0;
                }
             
                  if (cursor == 1)
                  {
                         for (int i = 0 ; i < tabSelection.length;i++)
                    {
                        tabSelection[i] = false;
                    }
                      cursor = 0;
                      tabSelection[cursor] = true;  
                        
                  }
                  repaint();
            }else if ( keyCode == -4)
            {
                if (cursor == -1)
                {tabSelection[0] = true; 
                cursor =0;
                }
                
                  if (cursor == 0)
                  {
                      for (int i = 0 ; i < tabSelection.length;i++)
                    {
                        tabSelection[i] = false;
                    }
                      cursor = 1;
                      tabSelection[cursor] = true;  
                        
                  }
                  repaint();
            }else if (keyCode == -5)
            {
                if (cursor == 1)
                {
                }else if ( cursor == 0)
                {
                    Midlet.mid.dis.setCurrent(new ProfileGUI("Profile"));
                }
            }
        }

    public void run() {
     //Affichage des matchs
            ProfileHandler profile = new ProfileHandler();
            try{
                
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/tftmobile/Membre/selectAvance.php?id="+Authentification.sessionIdMembre);//people.xml est un exemple
            System.out.println(hc.getFile());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, profile);
               // display the result
            
            Personne person[] = profile.getPersonne();
         nomComplet = person[0].getNom() + " " + person[0].getPrenom();
          repaint();
        
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    
    }
    
    
}
