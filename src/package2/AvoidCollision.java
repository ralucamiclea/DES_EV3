package package2;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class AvoidCollision implements Behavior{
	
	private EV3TouchSensor touchL, touchR;
	private EV3UltrasonicSensor sonar;
	private boolean suppressed  = false;
	private RegulatedMotor lm;
    private RegulatedMotor rm;
    private SampleProvider touchLeft, touchRight;
    private SampleProvider distance;
    private float[] touchLeftSamples, touchRightSamples;
    private float[] distanceSamples;
  
	
	public AvoidCollision(RegulatedMotor rm, RegulatedMotor lm){
		sonar = new EV3UltrasonicSensor(LocalEV3.get().getPort("S3"));
		touchL = new EV3TouchSensor(LocalEV3.get().getPort("S1"));
		touchR = new EV3TouchSensor(LocalEV3.get().getPort("S4"));
		touchLeft = touchL.getTouchMode();
		touchRight = touchR.getTouchMode();
		distance = sonar.getDistanceMode();
		touchLeftSamples = new float[touchLeft.sampleSize()];
		touchRightSamples = new float[touchRight.sampleSize()];
		distanceSamples = new float[distance.sampleSize()];
		this.lm = lm;
    	this.rm = rm;
	}
	
	@Override
	public boolean takeControl() {
		touchLeft.fetchSample(touchLeftSamples, 0);
		touchRight.fetchSample(touchRightSamples, 0);
		distance.fetchSample(distanceSamples, 0);
		LCD.drawString("TL: " + touchLeftSamples[0], 0, 1);
		LCD.drawString("TR: " + touchRightSamples[0], 0, 2);
		LCD.drawString("DS: " + distanceSamples[0], 0, 3);
		return touchLeftSamples[0] > 0  || touchRightSamples[0] > 0 || distanceSamples[0] < 0.25;
	}
	@Override
	public void action() {
		suppressed = false;
		lm.rotate(-180, true);
		rm.rotate(-360,true);
		while(rm.isMoving() && !suppressed){
			Thread.yield();
		}
	}
	@Override
	public void suppress() {
		suppressed = true;
	}
	
}
