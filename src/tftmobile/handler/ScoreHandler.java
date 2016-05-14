/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tftmobile.handler;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tftmobile.entite.Score;

/**
 *
 * @author yasmi
 */
public class ScoreHandler extends DefaultHandler {
    
   //vector d'idee
    private Vector Scorev;
    private Score score;
    int nbreset1, nbreset2;
    
    
    public  ScoreHandler (){
        Scorev = new Vector();
        
    }
    
    
    //fonction pour inserer le contenu du vecteur dans un tableau
       public Score[] getScores()
    {
        Score[] tableau = new Score[Scorev.size()];
        
        Scorev.copyInto(tableau);
        return tableau;
    }


    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if(qName.equals("score"))
        {
            try {
              
                nbreset1 = Integer.parseInt(attributes.getValue("nbrsetj1"));
                nbreset2 = Integer.parseInt(attributes.getValue("nbrsetj2")); 
            } catch (Exception e) {
                System.out.println("NUM EXC CAUGHT");
            }          
                  score = new Score(nbreset1, nbreset2);
                  System.out.println("NBRE SET FOR PLAYER1"+nbreset1+"NBRE SET FOR PLAYER2"+nbreset2);
          
        }
        
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        
               if(qName.equals("score"))
        {
            Scorev.addElement(score);
            score = null;
        }
        
    }
    
    
       
       
    
    
}