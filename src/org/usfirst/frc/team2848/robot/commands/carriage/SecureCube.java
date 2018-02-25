package org.usfirst.frc.team2848.robot.commands.carriage;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SecureCube extends Command {

	public SecureCube() {
		requires(Robot.carriage);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.intakeClawMotor.set(-.8);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.carriage.intakeClawMotor.set(0);
	}

	protected void interrupted() {
		end();
	}
}
