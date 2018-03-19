package org.usfirst.frc.team2848.robot.subsystems;

import org.usfirst.frc.team2848.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	//Intake motors
	public Spark leftIntake = new Spark(RobotMap.p_leftIntake);
	public Spark rightIntake = new Spark(RobotMap.p_rightIntake);
	
	public AnalogInput sonar = new AnalogInput(RobotMap.p_sonar);
	public DigitalInput breakBeam = new DigitalInput(RobotMap.p_breakBeam);

	public Intake() {
		super("Intake");
	}

	public void initDefaultCommand() {
	}
	
	//Take the cube in
	public void intake() {
		leftIntake.set(1.0);
		rightIntake.set(-1.0);
	}
	
	//Spit the cube out
	public void extake() {
		leftIntake.set(-1.0);
		rightIntake.set(1.0);
	}
	
	//Set all motors to 0
	public void intakeSetPowerZero() {
		leftIntake.set(0.0);
		rightIntake.set(0.0);
	}
}