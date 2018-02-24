package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PulseExtake extends Command {

	Timer timer = new Timer();
	double pulse = 250;// in milliseconds
	double milis = 0;

	public PulseExtake() {
		requires(Robot.intake);
	}

	protected void initialize() {
		timer.start();
	}

	protected void execute() {
		milis = timer.get() * 1000;
		if (((int) (milis / pulse)) % 2 == 0) {
			Robot.intake.rightIntake.set(0.0);
			Robot.intake.leftIntake.set(-0.8);
			System.out.println("Left Extake Running");
		} else {
			Robot.intake.leftIntake.set(0.0);
			Robot.intake.rightIntake.set(-0.8);
			System.out.println("Right Extake Running");
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		timer.stop();
		timer.reset();
		Robot.intake.rightIntake.set(0.0);
		Robot.intake.leftIntake.set(0.0);
	}

	protected void interrupted() {
		end();
	}
}
