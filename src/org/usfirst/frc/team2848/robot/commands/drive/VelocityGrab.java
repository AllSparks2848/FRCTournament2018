package org.usfirst.frc.team2848.robot.commands.drive;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.util.AveragingFilter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VelocityGrab extends Command {
	
	AveragingFilter speeds = new AveragingFilter(10);
	
	public VelocityGrab() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
		Robot.drivetrain.leftEncoder.setDistancePerPulse(-0.00114);
		Robot.drivetrain.rightEncoder.setDistancePerPulse(0.00114);
	}

	protected void execute() {
		Robot.drivetrain.left.set(-1);
		Robot.drivetrain.right.set(1);
		System.out.println("velocity avg: " + speeds.addValueGetAverage((Robot.drivetrain.leftEncoder.getRate() + Robot.drivetrain.rightEncoder.getRate())/2));
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drivetrain.left.set(0);
		Robot.drivetrain.right.set(0);
	}

	protected void interrupted() {
	}
}
