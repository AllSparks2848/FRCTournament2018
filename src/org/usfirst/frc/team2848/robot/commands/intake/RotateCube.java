package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateCube extends Command {

	public RotateCube() {
		requires(Robot.intake);

	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.intake.leftIntake.set(-0.6);
		Robot.intake.rightIntake.set(0.3);// intakes
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {

		Robot.intake.leftIntake.set(0.0);
		Robot.intake.rightIntake.set(0.0);// stops motors

	}

	protected void interrupted() {
		end();
	}
}
