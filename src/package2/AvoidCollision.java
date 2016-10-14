package package2;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class AvoidCollision implements Behavior{
	
	private static EV3TouchSensor touchL, touchR;
	private static EV3UltrasonicSensor sonar;
	private boolean suppressed  = false;
	private RegulatedMotor lm;
    private RegulatedMotor rm;
    private SampleProvider touchLeft, touchRight;
    private SampleProvider distance;
    private float[] touchLeftSamples, touchRightSamples;
    private float[] distanceSamples;
	
	public AvoidCollision(RegulatedMotor rm, RegulatedMotor lm){
		this.lm = lm;
    	this.rm = rm;
		sonar = new EV3UltrasonicSensor(SensorPort.S3);
		touchL = new EV3TouchSensor(SensorPort.S1);
		touchR = new EV3TouchSensor(SensorPort.S4);
		touchLeft = touchL.getTouchMode();
		touchRight = touchR.getTouchMode();
		distance = sonar.getDistanceMode();
		touchLeftSamples = new float[touchLeft.sampleSize()];
		touchRightSamples = new float[touchRight.sampleSize()];
		distanceSamples = new float[distance.sampleSize()];
	}
	
	@Override
	public boolean takeControl() {
		touchLeft.fetchSample(touchLeftSamples, 0);
		touchRight.fetchSample(touchRightSamples, 0);
		distance.fetchSample(distanceSamples, 0);
		LCD.drawString("TL: " + touchLeftSamples[0], 0, 2);
		LCD.drawString("TR: " + touchRightSamples[0], 0, 3);
		LCD.drawString("DS: " + distanceSamples[0], 0, 4);
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
