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

public class GetMessage extends Thread {
	Model m;
	Goals g;
	
	public GetMessage(Model m, Goals g){
		this.m=m;
		this.g=g;
	}

	public void run(){
		while(!(g.red&&g.blue&&g.yellow)){
			try{
				LCD.clear();
				LCD.drawString("decoding message", 0, 6);
				boolean bll = ((m.b=m.reader.readByte())!='\n');
				LCD.clear();
				if(bll){
					LCD.drawString("yes",0,6);
					action();
				}
				else
					LCD.drawString("nah", 0,6);
			}catch (IOException ex){
	 			LCD.drawString("error:", 0, 3);
	 			LCD.drawString(ex.getMessage(),0,4);
	 		}
		}
	}

	public void action() {
		Sound.beepSequenceUp();
		if(m.b=='r')
			g.red=true;
		else if(m.b=='y')
			g.yellow=true;
		else
			g.blue=true;
	}
		
}