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
import lejos.hardware.sensor.EV3ColorSensor;
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
	protected static EV3ColorSensor colorSensor;
	public SampleProvider color;
	public float[] colorSamples;
	protected static EV3TouchSensor touchL, touchR;
	protected static EV3UltrasonicSensor sonar;
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
        lm.setSpeed(290);
        rm.setSpeed(300);
        colorSensor = new EV3ColorSensor(SensorPort.S2);
        color = colorSensor.getColorIDMode();
		colorSamples = new float[color.sampleSize()];
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
	 	if(name.equals("Rover1")){
	 		connection = connector.connect("Rover2", NXTConnection.RAW);
	 	}else if(name.equals("Rover3")){
	 		connection = connector.connect("Rover4", NXTConnection.RAW);
	 	}else{ //name==rover2 or rover4
	 		connection = connector.waitForConnection(60000,NXTConnection.RAW);
	 	}
	 	writer = new PrintWriter(connection.openOutputStream());
 		reader = connection.openDataInputStream();
 		LCD.clearDisplay();/*
 		if(name.equals("Rover1")){
 			writer.print('x');
 			writer.flush();
 		}else{
 			try{
				LCD.clear();
				LCD.drawString("decoding message", 5, 0);
				boolean bll = ((b=reader.readByte())!='\n');
				LCD.clear();
				if(bll){
					LCD.drawChar((char) b,5,0);
				}
				else
					LCD.drawString("nah", 5, 0);
			}catch (IOException ex){
	 			LCD.drawString("error:", 0, 3);
	 			LCD.drawString(ex.getMessage(),0,4);
	 		}
 		}
 		if(!name.equals("Rover1")){
 			writer.print('y');
 			writer.flush();
 		}else{
 			try{
				LCD.drawString("decoding message", 6, 0);
				boolean bll = ((b=reader.readByte())!='\n');
				LCD.clear();
				if(bll){
					LCD.drawChar((char) b,6,0);
				}
				else
					LCD.drawString("nah", 6, 0);
			}catch (IOException ex){
	 			LCD.drawString("error:", 0, 3);
	 			LCD.drawString(ex.getMessage(),0,4);
	 		}
 		}
 		while(true){}*/
	}
}
