import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Behavior;



/**
 * @author raluca.miclea
 *
 */

public class DriveForward implements Behavior {

	private boolean suppressed;
	RegulatedMotor lm;
    RegulatedMotor rm;
    
    public DriveForward(RegulatedMotor rm, RegulatedMotor lm){
    	this.lm = lm;
    	this.rm = rm;
    }
    
	public void action(){
		suppressed = false;
		rm.forward();
		lm.forward();
		if(suppressed){
			rm.stop();
			lm.stop();
		}
	}
	
	public void suppress(){
		suppressed = true;
	}
	
	public boolean takeControl(){
		return true;
	}
}
