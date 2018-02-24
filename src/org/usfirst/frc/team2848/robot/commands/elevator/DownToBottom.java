package org.usfirst.frc.team2848.robot.commands.elevator;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DownToBottom extends Command {

	public DownToBottom() {
		requires(Robot.elevator);
	}

	protected void initialize() {
	}

	protected void execute() {
		if (Robot.elevator.elevatorEncoder.get() < 100) {
			Robot.elevator.elevatorMotor.set(.3);// sends carriage down
		} else {
			Robot.elevator.elevatorMotor.set(.5);// sends carriage down
		}

	}

	protected boolean isFinished() {
		if (!Robot.elevator.limitSwitchElevatorBottom.get())
			return true;
		return false;
	}

	protected void end() {
		Robot.elevator.elevatorEncoder.reset();
		Robot.elevator.elevatorMotor.set(0.0);
	}

	protected void interrupted() {
		end();
	}
}
