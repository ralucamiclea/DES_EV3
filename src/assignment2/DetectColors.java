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
	Model m;
	Goals g;
	
	public DetectColors(Model m,Goals g){
		this.m=m;
		this.g=g;
	}
	
	@Override
	public boolean takeControl() {
		m.color.fetchSample(m.colorSamples, 0);
		if(g.blue)
			LCD.drawString("BLUE", 0, 2); 
		if(g.red)
			LCD.drawString("RED", 0, 4); 
		if(g.yellow)
			LCD.drawString("YELLOW", 0, 3); 
		return m.colorSamples[0] == Color.BLUE || m.colorSamples[0] == Color.YELLOW || m.colorSamples[0] == Color.RED;
	}

	@Override
	public void action() {
		suppressed = false;

		if(m.colorSamples[0] == Color.BLUE && g.blue == false){ 
			LCD.drawString("BLUE", 0, 2); 
			g.blue = true;
			Sound.beepSequence();
			m.writer.print('b');
			m.writer.flush();
		}
		else
			if(m.colorSamples[0] == Color.YELLOW && g.yellow == false){
				LCD.drawString("YELLOW", 0, 3); 
				g.yellow = true;
				Sound.beepSequence();
				m.writer.print('y');
				m.writer.flush();
			}
			else
				if(m.colorSamples[0] == Color.RED && g.red == false){ 
					LCD.drawString("RED", 0, 4); 
					g.red = true;
					Sound.beepSequence();
					m.writer.print('r');
					m.writer.flush();
				}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
