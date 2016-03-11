/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tftmobile.gui;

import tftmobile.entite.Personne;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;
import tftmobileMidlet.Midlet;
import javax.wireless.messaging.*;
/**
 *
 * @author wissem
 */
public class Inscription extends Form implements CommandListener, Runnable {
    private Image image1;
    private Image imagesms;
    Personne user ;
    HttpConnection ht;
    DataInputStream dt;
    int ch;
    Display disp;
    MessageConnection clientConn;
 
    TextField tfprenom= new TextField("prenom", null, 20, TextField.ANY);
    TextField tfNom= new TextField("Nom", null, 20, TextField.ANY);
    TextField tfemail = new TextField("Email", "yourmail@gmail.com", 500, TextField.ANY); 
    TextField tfttel = new TextField("Tel", null, 11, TextField.NUMERIC); 
    TextField tfcin = new TextField("CIN", null, 8, TextField.NUMERIC); 
    TextField tfaddress = new TextField("adresse", null, 500, TextField.ANY); 
    TextField tfLogin = new TextField("Login", null, 20, TextField.ANY);
    TextField tfPassword = new TextField("Mot de passe", null, 50, TextField.ANY);
    DateField date = new DateField("Date",DateField.DATE);
    
    
    
    String [] tabSexe={"Homme","Femme"};
    ChoiceGroup sexe = new  ChoiceGroup("Sexe", ChoiceGroup.POPUP, tabSexe, null);
    Command inscrit= new Command("Submit", Command.SCREEN, 0);
    Command back= new Command("Retour", Command.SCREEN, 1);
    Alert alt = new Alert(null, null, null, AlertType.CONFIRMATION);

  
    //******************* form code confirmation SMS*******************************
    Command Cnfirm= new Command("Confirm", Command.SCREEN, 0);
    Command backInscription= new Command("Retour", Command.SCREEN, 1);
    Form FcodeCOnfirm = new Form("form code");
    TextField tfCodeconfirm= new TextField("CODE DE CONFIRMATION", null, 20, TextField.ANY);
    //*****************************************************************************
     
    public Inscription(String title,Display disp) {
        super(title);
        try {
          image1 = Image.createImage("/Images/inscrip.jpg");
       
         } catch (IOException ex) {
          ex.printStackTrace();
      }
       
        append(image1);
        append(tfprenom);
        append(tfNom);
        append(tfemail);
        append(tfttel);
        append(tfcin);
        append(tfaddress);
        append(tfLogin);
        append(tfPassword);
        append(date);
        append(sexe);
        
        addCommand(inscrit);
        addCommand(back);
        setCommandListener(this);
        
         try {
          imagesms = Image.createImage("/Images/confirm.jpg");
       
         } catch (IOException ex) {
          ex.printStackTrace();
      }
         FcodeCOnfirm.append(imagesms);
         FcodeCOnfirm.append(tfCodeconfirm);
         FcodeCOnfirm.addCommand(backInscription);
         FcodeCOnfirm.addCommand(Cnfirm);
         FcodeCOnfirm.setCommandListener(this);
        
    }

    public Inscription(String title) {
         super(title);

        }

//***********************************************************    
//*********** Generateur  code confirmation *****************
    public String randomCode(String ch1)
    {
        String ch0="";
       ch0="tft8"+ch1+"941";
        return ch0;}
 //*********************************************************
 //*********************************************************  
    
    
    

    
 public void sendSMS(String numtel , String pren)
 {
  try {
      clientConn=(MessageConnection)Connector.open("sms://"+numtel);
      }
 catch(Exception e) {
      System.out.println("Unable to connect to Station because of network problem");
                    }
   try {
        TextMessage textmessage = (TextMessage) clientConn.newMessage(MessageConnection.TEXT_MESSAGE);
        textmessage.setAddress("sms://"+numtel);
        //"876543215"
        textmessage.setPayloadText(randomCode(pren));
        clientConn.send(textmessage);
       }
                        catch(Exception e)
                        {
                            System.out.println("--- Unable to send ---");
                            System.out.println("num tel ="+numtel);
                            
                            System.out.println(randomCode(pren));
                        }
 
 
 }   
    
    
    
    public void commandAction(Command c, Displayable d) {
        if(c==inscrit)
          {
//          Thread th = new Thread(this);
//          th.start();
          String tel=tfttel.getString().trim();
          String prenom=tfprenom.getString().trim();
          sendSMS(tel, prenom);
          Midlet.mid.dis.setCurrent(FcodeCOnfirm);  
          }
          
        if(c==Cnfirm)
        {
            Thread th = new Thread(this);
            th.start();
        }
        
        
        
         
        if(c==back){ 
        Midlet.mid.dis.setCurrent(new Authentification("Authentification",this.disp));
        }
        if(c==backInscription){ 
        Midlet.mid.dis.setCurrent(new Inscription("Inscription",this.disp));
        }
        
     
        
        
    }
    public void run() {
         
       
        try {
         
Calendar cal= Calendar.getInstance();

StringBuffer str = new StringBuffer();
String prenom=tfprenom.getString().trim();
String nom=tfNom.getString().trim();
String email=tfemail.getString().trim();
String tel=tfttel.getString().trim();
                        System.out.println("*************numerrionoo = "+tel);
String cin=tfcin.getString().trim();
String adress=tfaddress.getString().trim();
String login=tfLogin.getString().trim();
String password=tfPassword.getString().trim();
String datee = (cal.get(Calendar.YEAR) + "-" + ( cal.get(Calendar.MONTH) + 1 ) + "-" + cal.get(Calendar.DAY_OF_MONTH)).trim();
String sexee = String.valueOf(sexe.getString(sexe.getSelectedIndex())).trim();


String code=tfCodeconfirm.getString().trim();
if(code.equals(randomCode(prenom))){
  ht =(HttpConnection)Connector.open("http://localhost/tftmobile/insert.php?"
          + "prenom=" + prenom 
          + "&nom=" + nom 
          + "&email=" + email 
          + "&tel=" + tel 
          + "&cin=" + cin 
          + "&adresse=" + adress
          + "&login=" + login 
          + "&password=" + password
          +"&datenaissance="+datee
          +"&sexe="+sexee);

           
              dt = ht.openDataInputStream();
               InputStreamReader r = new InputStreamReader(dt, "UTF-8");
 
            while ((ch = dt.read())!= -1) {
                str.append((char) ch);

            }
            
            System.out.print("Inscription avec Succes");
            Alert alertConfirm = new Alert(prenom ," est ajoute avec succes ", null, AlertType.CONFIRMATION);
            Midlet.mid.dis.setCurrent(alertConfirm, this); 
            Thread.sleep(2000);
            Midlet.mid.dis.setCurrent(new Authentification("Authentification ",Midlet.mid.dis));          
           
}
else
{ System.out.print("VÃ©rifier votre Code de confirmation ");
            Alert AlertSmsCode = new Alert("access denied "," veuillez vérifier votre code de confirmation ", null, AlertType.WARNING);
            Midlet.mid.dis.setCurrent(AlertSmsCode, this); 
            Thread.sleep(2000);
            Midlet.mid.dis.setCurrent(new Authentification("Authentification ",Midlet.mid.dis)); }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) { 
        ex.printStackTrace();
    }
       
    }
    }
    

