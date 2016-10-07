import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
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
		 Behavior B1 = new DriveForward(rm,lm);
		 Behavior B2 = new AvoidCollision("S3", "S1", rm, lm);
		 
		 Behavior [] bArray = {B1, B2};
		 Arbitrator arby = new Arbitrator(bArray);
	     arby.start();
	    }
}



