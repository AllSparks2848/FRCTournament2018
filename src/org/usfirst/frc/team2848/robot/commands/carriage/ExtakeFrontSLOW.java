package org.usfirst.frc.team2848.robot.commands.carriage;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExtakeFrontSLOW extends Command {

	public ExtakeFrontSLOW() {
		requires(Robot.carriage);
		requires(Robot.intake);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.intakeClawMotor.set(0.4);// outputs cube to robot's front
		Robot.carriage.omniPlateMotor.set(.5);
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
