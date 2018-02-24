package org.usfirst.frc.team2848.robot.commands.elevator;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoToHeight extends Command {

	double target;

	public GoToHeight(double x) {// use target height as parameter
		requires(Robot.elevator);
		target = x;
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.elevator.goToPosition(target);
	}

	protected boolean isFinished() {
		return Math.abs(Robot.elevator.elevatorEncoder.get() - target) < 5 ;
	}

	protected void end() {
		Robot.elevator.elevatorMotor.set(0);
	}

	protected void interrupted() {
		end();
	}
}
