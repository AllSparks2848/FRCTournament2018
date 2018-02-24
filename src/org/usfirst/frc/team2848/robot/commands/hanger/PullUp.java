package org.usfirst.frc.team2848.robot.commands.hanger;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PullUp extends Command {

	public PullUp() {
		requires(Robot.hanger);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.hanger.hangerMotor1.set(1);

	}

	protected boolean isFinished() {
		return false; // ?
	}

	protected void end() {
		Robot.hanger.hangerMotor1.set(0.0);
	}

	protected void interrupted() {
		end();
	}
}
