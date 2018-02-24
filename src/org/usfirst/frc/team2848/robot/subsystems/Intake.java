package org.usfirst.frc.team2848.robot.subsystems;

import org.usfirst.frc.team2848.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	//Intake motors
	public Spark leftIntake = new Spark(RobotMap.p_leftIntake);
	public Spark rightIntake = new Spark(RobotMap.p_rightIntake);

	public Intake() {
		super("Intake");
	}

	public void initDefaultCommand() {
	}
	
	//Take the cube in
	public void intake() {
		leftIntake.set(0.8);
		rightIntake.set(-0.8);
	}
	
	//Spit the cube out
	public void extake() {
		leftIntake.set(-0.8);
		rightIntake.set(0.8);
	}
	
	//Set all motors to 0
	public void intakeSetPowerZero() {
		leftIntake.set(0.0);
		rightIntake.set(0.0);
	}
}