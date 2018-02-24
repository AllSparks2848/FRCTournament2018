package org.usfirst.frc.team2848.robot.subsystems;

import org.usfirst.frc.team2848.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Carriage extends Subsystem {

	public Spark omniPlateMotor = new Spark(RobotMap.p_omniPlateMotor);
	public Spark intakeClawMotor = new Spark(RobotMap.p_intakeClawMotor);

	public DoubleSolenoid intakeClaw = new DoubleSolenoid(RobotMap.p_intakeClawA, RobotMap.p_intakeClawB);

	public Carriage() {

	}

	public void initDefaultCommand() {
	}
}