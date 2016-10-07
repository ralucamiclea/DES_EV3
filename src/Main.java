

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;



/**
 * @author raluca.miclea
 *
 */
public class Main {

	/**
	 * @param args
	 */
	 public static void main(String[] args)
	    {
	       /* //System.out.println("Running...");
	        GraphicsLCD g = BrickFinder.getDefault().getGraphicsLCD();
	        final int SW = g.getWidth();
	        final int SH = g.getHeight();
	        Button.LEDPattern(4);
	        Sound.beepSequenceUp();
	        
	        g.setFont(Font.getLargeFont());
	        g.drawString("leJOS/EV3", SW/2, SH/2, GraphicsLCD.BASELINE|GraphicsLCD.HCENTER);
	        Button.LEDPattern(3);
	        Delay.msDelay(4000);
	        Button.LEDPattern(5);
	        g.clear();
	        g.refresh();
	        Sound.beepSequence();
	        Delay.msDelay(500);
	        Button.LEDPattern(0);*/
	        
	        RegulatedMotor lm = new EV3LargeRegulatedMotor(MotorPort.A);
	        RegulatedMotor rm = new EV3LargeRegulatedMotor(MotorPort.D);
	        lm.forward();

	        NXTLightSensor lightSensor = new NXTLightSensor(LocalEV3.get().getPort("S2"));
	        SampleProvider light = lightSensor.getRedMode();
	        float[] lightSamples = new float[light.sampleSize()];
	        while(!Button.ESCAPE.isDown()){
	        	light.fetchSample(lightSamples,0);
	        	LCD.drawString("LI: " + lightSamples[0],0,2);
	        	if(lightSamples[0]>0.5){
	        		rm.forward();
	        		Delay.msDelay(50);
	        	}else{
	        		rm.backward();
	        		Delay.msDelay(50);
	        	}
	        }
	    }
}



