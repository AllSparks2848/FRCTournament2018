package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClosePivot extends Command {

	public ClosePivot() {
		requires(Robot.pivotIntake);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.pivotIntake.intakePivot.set(DoubleSolenoid.Value.kReverse);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
