package org.usfirst.frc.team2848.robot.commands.drive;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroTurn extends Command {

	private double setpoint;

	// private double time;
	// Timer timer = new Timer();
	public GyroTurn(double setpoint) {
		requires(Robot.drivetrain);
//		this.setpoint = setpoint;
//		if (Math.abs(setpoint) > 4) {
//			time = 2.5;
//		} else if (Math.abs(setpoint) > 6)
//			time = 3;
//		else {
//			time = 1;
//		}
	}

	protected void initialize() {
		Robot.drivetrain.navX.reset();
		Robot.drivetrain.navX.zeroYaw();
		Robot.drivetrain.gyroController.setOutputRange(-.5, .5);
		Robot.drivetrain.gyroController.setSetpoint(setpoint);
		Robot.drivetrain.gyroController.enable();
		// timer.reset();
	}

	protected void execute() {
		Robot.drivetrain.left.pidWrite(Robot.drivetrain.gyroController.get());
		Robot.drivetrain.right.pidWrite(Robot.drivetrain.gyroController.get());
	}

	protected boolean isFinished() {
		return (Math.abs(setpoint - Robot.drivetrain.navX.getYaw()) < 5);
	}

	protected void end() {
		Robot.drivetrain.gyroController.disable();
		Robot.drivetrain.left.set(0);
		Robot.drivetrain.right.set(0);
	}

	protected void interrupted() {
		end();
	}
}
