package assignment2;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;

public class Model {
	public RegulatedMotor lm;
	public RegulatedMotor rm;
	public NXTLightSensor lightSensor;
	public SampleProvider light;
	public float[] lightSamples;
	public EV3TouchSensor touchL, touchR;
	public EV3UltrasonicSensor sonar;
	public SampleProvider touchLeft, touchRight;
	public SampleProvider distance;
	public float[] touchLeftSamples, touchRightSamples;
	public float[] distanceSamples;
	public Brick brick;
	public String name;
	public NXTConnection connection;
	public BTConnector connector;
	public PrintWriter writer;
	public DataInputStream reader;
	public byte[] buffer = new byte[32];
	public byte b;
	public int bufferi = 0;
    
	public Model(){
		lm = new EV3LargeRegulatedMotor(MotorPort.A);
        rm = new EV3LargeRegulatedMotor(MotorPort.D);
        lightSensor = new NXTLightSensor(SensorPort.S2);
        light = lightSensor.getRedMode();
        lightSamples = new float[light.sampleSize()];
        sonar = new EV3UltrasonicSensor(SensorPort.S3);
		touchL = new EV3TouchSensor(SensorPort.S1);
		touchR = new EV3TouchSensor(SensorPort.S4);
		touchLeft = touchL.getTouchMode();
		touchRight = touchR.getTouchMode();
		distance = sonar.getDistanceMode();
		touchLeftSamples = new float[touchLeft.sampleSize()];
		touchRightSamples = new float[touchRight.sampleSize()];
		distanceSamples = new float[distance.sampleSize()];
		connector = new BTConnector();
	 	brick = BrickFinder.getDefault();
	 	name = brick.getName();
	 	LCD.drawString(name, 0, 3);
	 	if(name.equals("Rover1") || name.equals("Rover3")){
	 		connection = connector.connect("Rover2", NXTConnection.RAW);
	 	}else{ //name==rover2 or rover4
	 		connection = connector.waitForConnection(60000,NXTConnection.RAW);
	 	}
	 	writer = new PrintWriter(connection.openOutputStream());
 		reader = connection.openDataInputStream();
	}
}
