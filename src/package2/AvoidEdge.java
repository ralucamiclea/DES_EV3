package package2;
import assignment2.Model;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class AvoidEdge implements Behavior {
	Model m;
	
	public AvoidEdge(Model m) {
		this.m=m;
	}

	@Override
	public boolean takeControl() {
		m.light.fetchSample(m.lightSamples,0);
		return m.lightSamples[0]<0.5;
	}

	@Override
	public void action() {
		m.lm.forward();
		m.rm.backward();
		Delay.msDelay(50);
		m.lm.backward();
		Delay.msDelay(50);
	}

	@Override
	public void suppress() {
		return;

	}

}
