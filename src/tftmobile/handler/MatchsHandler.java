/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.handler;

import tftmobile.gui.*;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tftmobile.entite.Personne;

/**
 *
 * @author yasmi
 */
public class MatchsHandler extends DefaultHandler {
    
   //vector d'idee
    private Vector Matchv;
    private Vector Personnev;
    
    int id1,id2,idscore;
    String nom,prenom;
    
    public  MatchsHandler (){
        Matchv = new Vector();
        Personnev = new Vector();
        
    }
    
    
    //fonction pour inserer le contenu du vecteur dans un tableau
       public Match[] getMatchs(){
        Match[] tableau = new Match[Matchv.size()];
        
        Matchv.copyInto(tableau);
        return tableau;
    }
       
        public Personne[] getJoueurs() {
        Personne[] tableau=new Personne[Personnev.size()];
        
        Personnev.copyInto(tableau);
        return tableau;
    }
       
       //
       private Match match;
       private Personne personne;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if(qName.equals("match"))
        {
            try {
              
                id1 = Integer.parseInt(attributes.getValue("idjoueur1"));
                id2 = Integer.parseInt(attributes.getValue("idjoueur2")); 
                idscore = Integer.parseInt(attributes.getValue("idscore"));
                nom = attributes.getValue("nom");
                prenom = attributes.getValue("prenom");
                
            } catch (Exception e) {
                System.out.println("NUM EXC CAUGHT");
            }          
                  match = new Match(id1, id2, idscore);
                  personne = new Personne(nom, prenom);
                  System.out.println("IDS ARE"+id1+" "+id2);
          
        }
        
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        
               if(qName.equals("match"))
        {
            Personnev.addElement(personne);
            Matchv.addElement(match);
            match = null;
            personne = null;
        }
        
    }
    
    
       
       
    
    
}