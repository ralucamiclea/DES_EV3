package package2;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class AvoidEdge implements Behavior {
	private RegulatedMotor lm, rm;
	NXTLightSensor lightSensor;
    SampleProvider light = lightSensor.getRedMode();
    float[] lightSamples;
	
	public AvoidEdge(RegulatedMotor lm, RegulatedMotor rm) {
		this.lm = lm;
		this.rm = rm;
		lightSensor = new NXTLightSensor(LocalEV3.get().getPort("S2"));
		lightSamples = new float[light.sampleSize()];
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
