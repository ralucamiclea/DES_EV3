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
	private SampleProvider lightSample;
	private float[] lightSamples;
	private RegulatedMotor lm;
    private RegulatedMotor rm;
	
	public AvoidEdge2(RegulatedMotor lm, RegulatedMotor rm, EV3ColorSensor lightSensor) {
		this.lm = lm;
		this.rm = rm;
		
		lightSample = lightSensor.getColorIDMode();
		lightSamples = new float[lightSample.sampleSize()];
	}

	@Override
	public boolean takeControl() {
		lightSample.fetchSample(lightSamples,0);
		return lightSamples[0] == Color.BLACK;
	}

	@Override
	public void action() {
		suppressed = false;
		LCD.drawString("Found Edge! ", 0, 6);
		lm.forward();
		rm.backward();
		Delay.msDelay(50);
		lm.backward();
		Delay.msDelay(50);
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
