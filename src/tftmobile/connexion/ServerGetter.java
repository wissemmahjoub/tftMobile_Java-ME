package tftmobile.connexion;


import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Image;


/**
 *
 * @author HP
 */
public class ServerGetter {
    
     public static String GetTextServer(String url) {
        
        HttpConnection hconx;
        DataInputStream allData;
        StringBuffer resultat = new StringBuffer("");
        int ascii;
        
        String ResultatString=null;
        try {
            hconx = (HttpConnection) Connector.open(url);
            allData = hconx.openDataInputStream();

            while ((ascii = allData.read()) != -1) {
                resultat.append((char) ascii);
            }
            
            ResultatString = resultat.toString().trim();
            

        } catch (IOException ex) {
            System.out.println("Text From Server problem : "+ex);
        }
        
        return ResultatString;
    }
    
    
}
