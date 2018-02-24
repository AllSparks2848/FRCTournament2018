package org.usfirst.frc.team2848.robot.subsystems;

import org.usfirst.frc.team2848.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PivotIntake extends Subsystem {

	public DoubleSolenoid intakePivot = new DoubleSolenoid(RobotMap.p_intakePivotA, RobotMap.p_intakePivotB);

	public void initDefaultCommand() {
	}
}
