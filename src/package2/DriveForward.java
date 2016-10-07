package package2;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.*;

public class DriveForward  implements Behavior {
   private boolean suppressed = false;
   private RegulatedMotor lm;
   private RegulatedMotor rm;
   
   public DriveForward(RegulatedMotor lm, RegulatedMotor rm){
	   this.lm = lm;
	   this.rm = rm;
   }
   
   public boolean takeControl() {
      return true;
   }

   public void suppress() {
      suppressed = true;
   }

   public void action() {
     suppressed = false;
     lm.forward();
     rm.forward();
     while( !suppressed )
        Thread.yield();
     lm.stop(); // clean up
     rm.stop();
   }
}