package assignment2;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.subsumption.Behavior;

public class GetMessage implements Behavior {
	Model m;
	private boolean suppressed  = false;
	
	public GetMessage(Model m){
		this.m=m;
	}

	@Override
	public boolean takeControl() {
		try{
			return ((m.b=m.reader.readByte())!='\n');
		}catch (IOException ex){
 			LCD.drawString("error:", 0, 3);
 			LCD.drawString(ex.getMessage(),0,4);
 			return false;
 		}
	}

	@Override
	public void action() {
		suppressed=false;
		Sound.beep();
		
	}

	@Override
	public void suppress() {
		suppressed = true;
		
	}
}
