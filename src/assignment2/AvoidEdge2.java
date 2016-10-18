/**
 * 
 */
package assignment2;

import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

/**
 * @author raluca.miclea
 *
 */
public class AvoidEdge2 implements Behavior {

	private boolean suppressed  = false;
	Model m;
	
	public AvoidEdge2(Model m) {
		this.m = m;
	}

	@Override
	public boolean takeControl() {
		m.color.fetchSample(m.colorSamples,0);
		return m.colorSamples[0] == Color.BLACK;
	}

	@Override
	public void action() {
		suppressed = false;
		LCD.drawString("Found Edge! ", 0, 6);
		m.lm.backward();
		m.rm.backward();
		Delay.msDelay(50);
		m.rm.forward();
		Delay.msDelay(75);
		LCD.clear();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
