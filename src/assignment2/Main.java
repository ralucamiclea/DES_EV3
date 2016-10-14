/**
 * 
 */
package assignment2;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import package2.AvoidCollision;

/**
 * @author raluca.miclea
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	 RegulatedMotor lm = new EV3LargeRegulatedMotor(MotorPort.A);
	 RegulatedMotor rm = new EV3LargeRegulatedMotor(MotorPort.D);
	 EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S2);
	 
	 Behavior driveForward = new DriveForward2(lm, rm);
	 Behavior avoidEdge = new AvoidEdge2(lm,rm, colorSensor);
	 Behavior avoidCollision = new AvoidCollision(rm, lm);
	 Behavior detectColors = new DetectColors(rm, lm, colorSensor);
	 Behavior [] bArray = {driveForward, detectColors, avoidCollision, avoidEdge};
	 Arbitrator arby = new Arbitrator(bArray);
	
	 arby.go();
	}

}
