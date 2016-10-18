package package2;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class AvoidEdge implements Behavior {
	private RegulatedMotor lm, rm;
	private SampleProvider light;
	private float[] lightSamples;
	
	public AvoidEdge(RegulatedMotor lm, RegulatedMotor rm, SampleProvider light, float[] lightSamples) {
		this.lm = lm;
		this.rm = rm;
		this.light = light;
		this.lightSamples = lightSamples;
	}

	@Override
	public boolean takeControl() {
		light.fetchSample(lightSamples,0);
		return lightSamples[0]<0.5;
	}

	@Override
	public void action() {
		lm.forward();
		rm.backward();
		Delay.msDelay(50);
		lm.backward();
		Delay.msDelay(50);
	}

	@Override
	public void suppress() {
		return;

	}

}