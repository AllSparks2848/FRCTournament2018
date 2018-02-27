package org.usfirst.frc.team2848.robot.subsystems;

import org.usfirst.frc.team2848.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LED extends Subsystem {

	public static Spark humanSignalLED = new Spark(RobotMap.p_humanSignalLED);
	public static Spark allianceLED = new Spark(RobotMap.p_allianceLED);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public LED() {
		super("LED");
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static void humanSignal() {
    	humanSignalLED.set(-0.11); //Blink Red
    }
    
    public static void blueAlliance() {
    	allianceLED.set(0.87); //Solid Blue
    }
    
    public static void redAlliance() {
    	allianceLED.set(0.61); //Solid Red
    }
}