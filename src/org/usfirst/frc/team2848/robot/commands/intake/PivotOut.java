package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PivotOut extends Command {

	public PivotOut() {
		requires(Robot.pivotIntake);
	}

	protected void initialize() {
		Robot.pivotIntake.intakePivot.set(DoubleSolenoid.Value.kForward);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
