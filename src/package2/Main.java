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
	        RegulatedMotor lm = new EV3LargeRegulatedMotor(MotorPort.A);
	        RegulatedMotor rm = new EV3LargeRegulatedMotor(MotorPort.D);
	        NXTLightSensor lightSensor = new NXTLightSensor(LocalEV3.get().getPort("S2"));
	        SampleProvider light = lightSensor.getRedMode();
	        float[] lightSamples = new float[light.sampleSize()];
	        Behavior b1 = new DriveForward(lm,rm);
	        Behavior b2 = new AvoidEdge(lm,rm,light,lightSamples);
	        Behavior b3 = new AvoidCollision(rm, lm);
	        Behavior [] bArray = {b1, b2,b3};
	        Arbitrator arby = new Arbitrator(bArray);
	        
	        arby.go();
	    }
}


