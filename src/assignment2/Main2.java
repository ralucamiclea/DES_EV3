/**
 * 
 */
package assignment2;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

/**
 * @author raluca.miclea
 *
 */
public class Main2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	 
	 Model m = new Model();
	 Goals g = new Goals();
	 Behavior driveForward = new DriveForward2(m);
	 Behavior avoidEdge = new AvoidEdge2(m);
	 Behavior avoidCollision = new AvoidCollision2(m);
	 Behavior detectColors = new DetectColors(m,g);
	 Behavior stop = new Stop(g,m);
	 GetMessage t = new GetMessage(m,g);
	 t.start();
	 //Behavior [] bArray = {detectColors}
	 Behavior [] bArray = {driveForward, detectColors, avoidCollision, stop, avoidEdge};
	 Arbitrator arby = new Arbitrator(bArray);
	
	 arby.go();
	}

}
