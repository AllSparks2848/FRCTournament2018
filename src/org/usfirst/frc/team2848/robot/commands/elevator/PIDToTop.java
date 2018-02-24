package org.usfirst.frc.team2848.robot.commands.elevator;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDToTop extends Command {

	double target = 840;

	public PIDToTop() {
		requires(Robot.elevator);
	}

	protected void initialize() {
	}

	protected void execute() {
		if (Robot.elevator.elevatorEncoder.get() < 800 && Robot.elevator.limitSwitchElevatorTop.get())
			Robot.elevator.goToPosition(target);
		else if (Robot.elevator.elevatorEncoder.get() > 800 && Robot.elevator.limitSwitchElevatorTop.get()) {
			Robot.elevator.elevatorMotor.set(-.2);
		} else {
			Robot.elevator.goToPosition(Robot.elevator.elevatorEncoder.get());
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
