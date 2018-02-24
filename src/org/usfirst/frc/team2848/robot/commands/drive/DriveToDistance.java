package org.usfirst.frc.team2848.robot.commands.drive;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveToDistance extends Command {
	private double setpoint;
	private double time;
	Timer timer = new Timer();

	public DriveToDistance(double setpoint) {
		requires(Robot.drivetrain);
		this.setpoint = setpoint;
		if (Math.abs(setpoint) > 4) {
			time = 2;
		} else if (Math.abs(setpoint) > 6) {
			time = 3;
		}
		else if (Math.abs(setpoint) > 2) {
			time = 1;
		}
		else if (Math.abs(setpoint) > 10) {
			time = 4;
		}
		else {
			time = .5;
		}
	}

	protected void initialize() {
		Robot.drivetrain.leftEncoder.reset();
		Robot.drivetrain.rightEncoder.reset();
		Robot.drivetrain.leftEncoder.setReverseDirection(true);
		Robot.drivetrain.navX.reset();

		Robot.drivetrain.setOutputRange(-.6, .6);
		Robot.drivetrain.setSetpoint(setpoint);
		
		Robot.drivetrain.enable();
		timer.start();
	}

	protected void execute() {

	}

	protected boolean isFinished() {
//		System.out.println("left Error: " + Robot.drivetrain.getPIDController().getError() + " right Error: " + Robot.drivetrain.getPIDController().getError());
//		System.out.println("PID Out: " + Robot.drivetrain.getPIDController().get());

		if (timer.get() > time) {
			return true;
		}
		return false;
//				return (Math.abs(Robot.drivetrain.rightEncoder.get() - setpoint) < 0.01 /*&& Math.abs(Robot.drivetrain.rightEncoder.get() - setpoint) < 0*/);
	}

	protected void end() {
		Robot.drivetrain.disable();
		Robot.drivetrain.navX.zeroYaw();
	}

	protected void interrupted() {
		Robot.drivetrain.disable();
		end();
	}
}
