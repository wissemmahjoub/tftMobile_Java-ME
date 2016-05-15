package tftmobile.gui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;


import com.jappit.midmaps.googlemaps.GoogleMaps;
import com.jappit.midmaps.googlemaps.GoogleMapsCoordinates;
import com.jappit.midmaps.googlemaps.GoogleMapsGeocoderHandler;
import com.jappit.midmaps.googlemaps.GoogleMapsMarker;
import com.jappit.midmaps.googlemaps.GoogleMapsPath;
import com.jappit.midmaps.googlemaps.GoogleStaticMap;
import com.jappit.midmaps.googlemaps.GoogleStaticMapHandler;
import java.io.IOException;
import javax.microedition.lcdui.Image;

public class GoogleMapsMarkerCanvas_contact extends GoogleMapsTestCanvas implements GoogleStaticMapHandler
{
     int width = getWidth();
    int height = getHeight();
    int  xx = width/2;
    int  yy = (height -height/8)-10;
        GoogleMaps gMaps = null;
        GoogleStaticMap map = null;
        Command zoomInCommand, zoomOutCommand;
	 private Image info;
	public GoogleMapsMarkerCanvas_contact(MIDlet m, Displayable testListScreen) throws IOException
	{
             
		super(m, testListScreen);
                  addCommand(zoomInCommand = new Command("Zoom in", Command.OK, 1));
		addCommand(zoomOutCommand = new Command("Zoom out", Command.OK, 2));
		    info = Image.createImage("/Images/co.jpg");
		gMaps = new GoogleMaps();
                  map = gMaps.createMap(getWidth(), getHeight(), GoogleStaticMap.FORMAT_PNG);
		map.setHandler(this);
                  map.setCenter(new GoogleMapsCoordinates(36.843694, 10.183560));
		
                  //federation Marker
		GoogleMapsMarker redMarker = new GoogleMapsMarker(new GoogleMapsCoordinates(36.843694, 10.183560));
		redMarker.setColor(GoogleStaticMap.COLOR_RED);
		redMarker.setSize(GoogleMapsMarker.SIZE_MID);
		redMarker.setLabel('T');
                  
                  map.addMarker(redMarker);
            
                  
                          
		
		map.setZoom(18);
		map.update();
	}
	
	protected void paint(Graphics g)
	{
            
                    //set background color to overdraw what ever was previously displayed
        
	map.draw(g, 0, 0, Graphics.TOP | Graphics.LEFT);
        
        g.drawImage(info, xx, yy, Graphics.HCENTER | Graphics.VCENTER);
	}
	
	public void GoogleStaticMapUpdateError(GoogleStaticMap map, int errorCode, String errorMessage){
		showError("map error: " + errorCode + ", " + errorMessage);
	}
	public void GoogleStaticMapUpdated(GoogleStaticMap map){
		repaint();
	}
        
        protected void keyPressed(int key){
		int gameAction = getGameAction(key);
		
		if(gameAction == Canvas.UP || gameAction == Canvas.RIGHT || gameAction == Canvas.DOWN || gameAction == Canvas.LEFT)
		{
			map.move(gameAction);
		}
	}
        
        public void commandAction(Command c, Displayable d){
		super.commandAction(c, d);
		
		if(c == zoomInCommand)
			map.zoomIn();
		else if(c == zoomOutCommand)
			map.zoomOut();
	}
	
}