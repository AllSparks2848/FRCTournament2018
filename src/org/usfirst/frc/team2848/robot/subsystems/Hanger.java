package org.usfirst.frc.team2848.robot.subsystems;

import org.usfirst.frc.team2848.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hanger extends Subsystem {
	public Solenoid unfoldPneumatic = new Solenoid(RobotMap.p_unfoldPneumatic);
	public Spark hangerMotor1 = new Spark(RobotMap.p_hangerMotor1);

	public Hanger() {
		super("Hanger");
	}

	public void initDefaultCommand() {
	}

	public void lockHangerDown() {
		unfoldPneumatic.set(false);
	}
}