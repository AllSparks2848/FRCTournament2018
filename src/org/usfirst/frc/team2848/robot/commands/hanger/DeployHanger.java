package org.usfirst.frc.team2848.robot.commands.hanger;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DeployHanger extends Command {

	public DeployHanger() {
		requires(Robot.hanger);
	}

	protected void initialize() {
		Robot.hanger.unfoldPneumatic.set(true);
	}

	protected void execute() {
		Robot.hanger.unfoldPneumatic.set(true);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
		end();
	}
}
