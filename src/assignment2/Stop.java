package assignment2;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class Stop implements Behavior{
	Goals g;
	Model m;
	
	public Stop(Goals g, Model m){
		this.g=g;
		this.m=m;
	}

	@Override
	public boolean takeControl() {
		return g.red&&g.blue&&g.yellow;
	}

	@Override
	public void action() {
		Sound.buzz();
		m.rm.stop();
		m.lm.stop();
		while(true){
		}
	}

	@Override
	public void suppress() {
		return;
		
	}

}
