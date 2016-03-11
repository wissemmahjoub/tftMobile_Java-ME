///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package tftmobile.gui;
//
//import java.io.DataInputStream;
//import java.io.IOException;
//import javax.microedition.io.Connector;
//import javax.microedition.io.HttpConnection;
//import javax.microedition.lcdui.Alert;
//import javax.microedition.lcdui.AlertType;
//import javax.microedition.lcdui.Canvas;
//import javax.microedition.lcdui.Command;
//import javax.microedition.lcdui.CommandListener;
//import javax.microedition.lcdui.Displayable;
//import javax.microedition.lcdui.Form;
//import javax.microedition.lcdui.Graphics;
//import javax.microedition.lcdui.Image;
//import javax.microedition.lcdui.TextField;
//
///**
// *
// * @author malox94
// */
//public class Achat extends Form implements CommandListener{
//
//    TextField tfmatch = new TextField("idmatch", "", 10, TextField.NUMERIC);
//    TextField tfticket = new TextField("Idticket", "", 10, TextField.ANY);
//
//    Command cmdNext = new Command("Add", Command.OK, 0);
//    HttpConnection hc;
//    DataInputStream dis;
//    StringBuffer sb;
//
//    String url = "http://localhost/TFTMobile/Achat/insert.php?";
//
//    public Achat(String name) {
//        super("Ajout");
//        append(tfmatch);
//        append(tfticket);
//        addCommand(cmdNext);
//        setCommandListener(this);
//    }
//
//    public void commandAction(Command c, Displayable d) {
//        if (c == cmdNext) {
//            try {
//                String membre, ticket;
//               membre = tfmatch.getString();
//               ticket = tfticket.getString();
//
//                hc = (HttpConnection) Connector.
//                        open(url + "&membre=" + membre + "&ticket=" + ticket);
//                dis = hc.openDataInputStream();
//
//                int ascii;
//                sb = new StringBuffer();
//
//                while ((ascii = dis.read()) != -1) {
//
//                    sb.append((char) ascii);
//
//                }
//             
//                if (sb.toString().equals("successfully added")) {
//                   
//                    Alert a = new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
//                    a.setTimeout(3000);
//                    Midlet.INSTANCE.disp.setCurrent(a);
//                } else {
//                    Alert a = new Alert("Information", sb.toString(), null, AlertType.ERROR);
//                    a.setTimeout(3000);
//                    Midlet.INSTANCE.disp.setCurrent(a);
//                }
//
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//
//        }
//    }
//
//    
//}
