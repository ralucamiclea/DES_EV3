/**
 * 
 */
package assignment2;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

/**
 * @author raluca.miclea
 *
 */
public class DetectColors implements Behavior{

	private boolean suppressed  = false;
	private SampleProvider colorSample;
	private float[] colorSamples;
	private RegulatedMotor lm;
    private RegulatedMotor rm;
    private boolean red = false, blue = false, yellow = false, all = false; 
	
	public DetectColors(RegulatedMotor rm, RegulatedMotor lm, EV3ColorSensor colorSensor){
		this.lm = lm;
		this.rm = rm;
		colorSample = colorSensor.getColorIDMode();
		colorSamples = new float[colorSample.sampleSize()];
	}
	
	@Override
	public boolean takeControl() {
		colorSample.fetchSample(colorSamples, 0);
		LCD.drawString("Color ID: " + colorSamples[0], 0, 5);
		return colorSamples[0] == Color.BLUE || colorSamples[0] == Color.YELLOW || colorSamples[0] == Color.RED;
	}

	@Override
	public void action() {
		suppressed = false;

		if(colorSamples[0] == Color.BLUE && blue == false){ 
			blue = true;
			Sound.beep();
		}
		else
			if(colorSamples[0] == Color.YELLOW && yellow == false){ 
				yellow = true;
				Sound.beep();
			}
			else
				if(colorSamples[0] == Color.RED && red == false){ 
					red = true;
					Sound.beep();
				}
		
		if(red == true && yellow == true && blue == true)
			all = true;
		
		if(all == true){
			Sound.buzz();
			rm.stop();
			lm.stop();
			while(all){
			}
		}
		
		while(rm.isMoving() && !all && !suppressed)
			Thread.yield();	
		rm.stop();
		lm.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
