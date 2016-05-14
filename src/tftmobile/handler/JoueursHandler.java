/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.handler;

import tftmobile.entite.Club;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tftmobile.entite.Personne;

/**
 *
 * @author yasmi
 */
public class JoueursHandler extends DefaultHandler {
    
   //vector d'idee
    private Vector Personnev;
    private Vector Clubv;
    
    public  JoueursHandler (){
        Personnev = new Vector();
        Clubv = new Vector();
        
    }
    
    
    //fonction pour inserer le contenu du vecteur dans un tableau
    public Personne[] getJoueurs() {
        Personne[] tableau=new Personne[Personnev.size()];
        
        Personnev.copyInto(tableau);
        return tableau;
    }
      
    public Club[] getClubs() {
        Club[] tableau=new Club[Clubv.size()];
        Clubv.copyInto(tableau);
        return tableau;
    }
      
       //
       private Personne personne;
       private Club club;
       
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if(qName.equals("personne"))
        {
              int id = Integer.parseInt(attributes.getValue("idpersonne"));
              String nom = attributes.getValue("nom");
              String prenom = attributes.getValue("prenom");
              String libelleclub = attributes.getValue("libelleclub");
              
              
              personne = new Personne(id, nom, prenom);
              club = new Club(libelleclub);
              
          
        }
        
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        if(qName.equals("personne")){
            Personnev.addElement(personne);
            Clubv.addElement(club);
            club = null;
            personne = null;
        }
        
    }
    
    
       
       
    
    
}