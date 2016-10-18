package assignment2;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import assignment2.Model;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class AvoidCollision2 implements Behavior{
	Model m;
	private boolean suppressed;
	
	public AvoidCollision2(Model m){
		this.m=m;
	}
	
	@Override
	public boolean takeControl() {
		m.touchLeft.fetchSample(m.touchLeftSamples, 0);
		m.touchRight.fetchSample(m.touchRightSamples, 0);
		m.distance.fetchSample(m.distanceSamples, 0);
		return m.touchLeftSamples[0] > 0  || m.touchRightSamples[0] > 0 || m.distanceSamples[0] < 0.25;
	}
	@Override
	public void action() {
		suppressed = false;
		m.lm.rotate(-180, true);
		m.rm.rotate(-360,true);
		while(m.rm.isMoving() && !suppressed){
			Thread.yield();
		}
	}
	@Override
	public void suppress() {
		suppressed = true;
	}
	
}