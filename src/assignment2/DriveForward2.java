package assignment2;
import assignment2.Model;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.*;

public class DriveForward2  implements Behavior {
   private boolean suppressed = false;
   Model m;
   
   public DriveForward2(Model m){
	   this.m=m;
   }
   
   public boolean takeControl() {
      return true;
   }

   public void suppress() {
      suppressed = true;
   }

   public void action() {
     suppressed = false;
     m.lm.forward();
     m.rm.forward();
     while( !suppressed )
        Thread.yield();
   }
}