package tftmobile.gui;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;
import tftmobileMidlet.Midlet;

abstract public class GoogleMapsTestCanvas_contact extends Canvas implements CommandListener
{
	Command cmdback = new Command("Retour", Command.BACK, 1);
	
	
	MIDlet midlet;
	
	public GoogleMapsTestCanvas_contact(MIDlet m, Displayable testListScreen)
	{
		this.midlet = m;
	
		
		addCommand(cmdback);
		
		setCommandListener(this);
	}
	
	public void commandAction(Command c, Displayable d)
	{
		if(c == cmdback){
		Midlet.mid.dis.setCurrent(new AccueilPublique(Midlet.mid));
		}
	}
	void showError(String message)
	{
		Alert error = new Alert("Error", message, null, AlertType.ERROR);
		
		Display.getDisplay(midlet).setCurrent(error, this);
	}
}
