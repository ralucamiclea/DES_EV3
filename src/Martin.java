import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Martin {

	public static void main(String[] args) {
		  RegulatedMotor lm = new EV3LargeRegulatedMotor(MotorPort.A);
	        RegulatedMotor rm = new EV3LargeRegulatedMotor(MotorPort.D);
	        lm.forward();

	        NXTLightSensor lightSensor = new NXTLightSensor(LocalEV3.get().getPort("S2"));
	        SampleProvider light = lightSensor.getRedMode();
	        float[] lightSamples = new float[light.sampleSize()];
	        while(!Button.ESCAPE.isDown()){
	        	light.fetchSample(lightSamples,0);
	        	LCD.drawString("LI: " + lightSamples[0],0,2);
	        	if(lightSamples[0]>0.5){
	        		rm.forward();
	        		Delay.msDelay(50);
	        	}else{
	        		rm.backward();
	        		Delay.msDelay(50);
	        	}
	        }
	    }

	}

