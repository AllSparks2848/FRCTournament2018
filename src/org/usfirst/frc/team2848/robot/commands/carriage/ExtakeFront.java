package org.usfirst.frc.team2848.robot.commands.carriage;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExtakeFront extends Command {

	public ExtakeFront() {
		requires(Robot.carriage);
		requires(Robot.intake);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.intakeClawMotor.set(-0.8);// outputs cube to robot's front
		Robot.carriage.omniPlateMotor.set(1.0);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.carriage.intakeClawMotor.set(0.0);
		Robot.carriage.omniPlateMotor.set(0);
	}

	protected void interrupted() {
		end();
	}
}
