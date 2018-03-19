package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FloorHeight extends Command {

	public FloorHeight() {
		requires(Robot.pivotIntake);
	}

	protected void initialize() {
		Robot.pivotIntake.intakePivot.set(DoubleSolenoid.Value.kReverse);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		Robot.pivotIntake.intakePivot.set(DoubleSolenoid.Value.kReverse);
	}

	protected void interrupted() {
	}
}
