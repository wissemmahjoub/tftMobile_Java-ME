/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tftmobile.entite.Match;
import tftmobile.entite.Personne;
import  tftmobile.gui.Authentification;
import tftmobile.handler.ProfileHandler;

/**
 *
 * @author Mustapha
 */
public class ProfileGUI extends Form implements CommandListener, Runnable {

    private Image image;
    Command cmdBack = new Command("Retour", Command.SCREEN, 1);
    Command cmdUpdate = new Command("Enregistrer", Command.SCREEN, 0);
    Match[] match;
    StringBuffer sb;
    int ch;
    HttpConnection ht;
    DataInputStream dt;
    private Vector Demandev;
    Personne[] person;

    Display disp;

    TextField nom = new TextField("Nom", null, 500, TextField.ANY);
    TextField prenom = new TextField("Prenom", null, 500, TextField.ANY);

    TextField email = new TextField("Email", null, 500, TextField.ANY);
    TextField login = new TextField("Login", null, 500, TextField.ANY);
    TextField password = new TextField("Password", null, 500, TextField.PASSWORD);

    public ProfileGUI(String title) {
        super(title);

        addCommand(cmdBack);
        addCommand(cmdUpdate);
        setCommandListener(this);
        append(nom);
        append(prenom);
        append(email);
        try {
            image = Image.createImage("/Images/speratorProfile.jpg");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        append(image);
        append(login);
        append(password);
        Thread th = new Thread(this);
        th.start();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            tftmobileMidlet.Midlet.mid.dis.setCurrent(new ProfilePortail());
        }
        if (c == cmdUpdate) {

//         Personne p = new Personne(idperson, log, Name, LastName, jeton);
           
            try {

                Calendar cal = Calendar.getInstance();

                StringBuffer str = new StringBuffer();

                ht = (HttpConnection) Connector.open("http://localhost/tftmobile/Membre/updateProfile.php?"
                        + "prenom=" + prenom.getString()
                        + "&nom=" + nom.getString()
                        + "&email=" + email.getString()
                        + "&login=" + login.getString()
                        + "&olduser=" + person[0].getLogin()
                        + "&password=" + password.getString());

                dt = ht.openDataInputStream();
                InputStreamReader r = new InputStreamReader(dt, "UTF-8");

                while ((ch = dt.read()) != -1) {
                    str.append((char) ch);

                }
                int serverMessage = Integer.parseInt(str.toString().trim());
                System.out.println(str.toString().trim());
                if (serverMessage == 1) {
                    Alert succes = new Alert("Ok ", "Votre profile a été mise à jour !", null, AlertType.ERROR);
                      tftmobileMidlet.Midlet.mid.dis.setCurrent(succes, this);

                } else {
                    Alert erreur = new Alert("Attention!", "Veuillez verifier vos informations", null, AlertType.ERROR);

                      tftmobileMidlet.Midlet.mid.dis.setCurrent(erreur, this);
                }

            } catch (IOException ex) {
                ex.printStackTrace();

            }
        }else
            {
                  Alert erreur = new Alert("Attention!", "Veuillez verifier vos informations", null, AlertType.ERROR);

            }

        

    }

    public void run() {
        try {
            setCommandListener(this);
            addCommand(cmdBack);

            //Affichage des matchs
            ProfileHandler profile = new ProfileHandler();

            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/tftmobile/Membre/selectAvance.php?id="+Authentification.sessionIdMembre);//people.xml est un exemple
            System.out.println(hc.getFile());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, profile);
            // display the result
            person = profile.getPersonne();

            login.setString(person[0].getLogin());
            email.setString(person[0].getEmail());
            nom.setString(person[0].getNom());
            prenom.setString(person[0].getPrenom());
            password.setString(person[0].getPassword());

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
