/**
 * 
 */
package assignment2;

/**
 * @author raluca.miclea
 *
 */
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.*;

public class DriveForward2  implements Behavior {
   private boolean suppressed = false;
   private RegulatedMotor lm;
   private RegulatedMotor rm;
   
   public DriveForward2(RegulatedMotor lm, RegulatedMotor rm){
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
   }
}