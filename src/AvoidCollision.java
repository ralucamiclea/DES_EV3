import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class AvoidCollision implements Behavior{
	
	private EV3TouchSensor touch;
	private EV3UltrasonicSensor sonar;
	private boolean suppressed  = false;
	private RegulatedMotor lm;
    private RegulatedMotor rm;
    private SampleProvider touchLeft;
    private SampleProvider distance;
    private float[] touchLeftSamples;
    private float[] distanceSamples;
	
	public AvoidCollision(String portNameSonic, String portNameTouch, RegulatedMotor rm, RegulatedMotor lm){
		sonar = new EV3UltrasonicSensor(LocalEV3.get().getPort("S3"));
		touch = new EV3TouchSensor(LocalEV3.get().getPort("S1"));
		SampleProvider touchLeft = touch.getTouchMode();
		SampleProvider distance = sonar.getDistanceMode();
		touchLeftSamples = new float[touchLeft.sampleSize()];
		distanceSamples = new float[distance.sampleSize()];
		this.lm = lm;
    	this.rm = rm;
	}
	
	@Override
	public boolean takeControl() {
		touchLeft.fetchSample(touchLeftSamples, 0);
		distance.fetchSample(distanceSamples, 0);
		LCD.drawString("TL: " + touchLeftSamples[0], 0, 0);
		LCD.drawString("TL: " + distanceSamples[0], 0, 3);
		return touchLeftSamples[0] > 0  || distanceSamples[0] < 25;
	}
	@Override
	public void action() {
		suppressed = false;
		lm.rotate(-180, true);
		rm.rotate(-360,true);
		if(!rm.isMoving() || suppressed){
			rm.stop();
			lm.stop();
		}
	}
	@Override
	public void suppress() {
		suppressed = true;
	}
	
}
