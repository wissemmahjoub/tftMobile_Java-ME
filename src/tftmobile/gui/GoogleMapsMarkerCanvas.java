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

public class GoogleMapsMarkerCanvas extends GoogleMapsTestCanvas implements GoogleStaticMapHandler
{
        GoogleMaps gMaps = null;
        GoogleStaticMap map = null;
        Command zoomInCommand, zoomOutCommand;
	
	public GoogleMapsMarkerCanvas(MIDlet m, Displayable testListScreen)
	{
		super(m, testListScreen);
                  addCommand(zoomInCommand = new Command("Zoom in", Command.OK, 1));
		addCommand(zoomOutCommand = new Command("Zoom out", Command.OK, 2));
		
		gMaps = new GoogleMaps();
                  map = gMaps.createMap(getWidth(), getHeight(), GoogleStaticMap.FORMAT_PNG);
		map.setHandler(this);
                  map.setCenter(new GoogleMapsCoordinates(36.843920, 10.183620));
		
                  //federation Marker
		GoogleMapsMarker redMarker = new GoogleMapsMarker(new GoogleMapsCoordinates(36.843920, 10.183620));
		redMarker.setColor(GoogleStaticMap.COLOR_RED);
		redMarker.setSize(GoogleMapsMarker.SIZE_MID);
		redMarker.setLabel('T');
                  
                  map.addMarker(redMarker);
            
                  
                  //Club de Tennis de Tunis Marker Marker
		GoogleMapsMarker redMarker2 = new GoogleMapsMarker(new GoogleMapsCoordinates(36.833605, 10.178278));
		redMarker2.setColor(GoogleStaticMap.COLOR_GREEN);
		redMarker2.setSize(GoogleMapsMarker.SIZE_MID);
		redMarker2.setLabel('A');
               
                  map.addMarker(redMarker2);             
		
                  
                  //ASCNS, Association Sprotive Cité Nationale Sprotive , Tennis
		GoogleMapsMarker redMarker3 = new GoogleMapsMarker(new GoogleMapsCoordinates(37.108291, 10.185616));
		redMarker3.setColor(GoogleStaticMap.COLOR_ORANGE);
		redMarker3.setSize(GoogleMapsMarker.SIZE_MID);
		redMarker3.setLabel('B');
              
                  map.addMarker(redMarker3);
		
                  //Terrain tennis parc farhat hached, rades 
		GoogleMapsMarker redMarker4 = new GoogleMapsMarker(new GoogleMapsCoordinates(37.038166, 10.174630));
		redMarker4.setColor(GoogleStaticMap.COLOR_YELLOW);
		redMarker4.setSize(GoogleMapsMarker.SIZE_MID);
		redMarker4.setLabel('C');
              
                  map.addMarker(redMarker4);
                  
		map.setZoom(15);
		map.update();
	}
	
	protected void paint(Graphics g)
	{
		map.draw(g, 0, 0, Graphics.TOP | Graphics.LEFT);
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