
package package2;


import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

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
	        NXTLightSensor lightSensor = new NXTLightSensor(LocalEV3.get().getPort("S2"));
	        SampleProvider light = lightSensor.getRedMode();
	        float[] lightSamples = new float[light.sampleSize()];
	        Behavior b1 = new DriveForward(lm,rm);
	        Behavior b2 = new AvoidEdge(rm,light,lightSamples);
	        Behavior [] bArray = {b1, b2};
	        Arbitrator arby = new Arbitrator(bArray,false);
	        
	        arby.start();
	        	/*
		 RegulatedMotor lm = new EV3LargeRegulatedMotor(MotorPort.A);
		 RegulatedMotor rm = new EV3LargeRegulatedMotor(MotorPort.D);
		 Behavior B1 = new DriveForward(rm,lm);
		 Behavior B2 = new AvoidCollision("S3", "S1", rm, lm);
		 
		 Behavior [] bArray = {B1, B2};
		 Arbitrator arby = new Arbitrator(bArray);
	     arby.start();
	     */
	    }
}



