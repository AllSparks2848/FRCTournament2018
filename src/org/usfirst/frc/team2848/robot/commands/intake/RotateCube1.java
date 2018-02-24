package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateCube1 extends Command {
	Timer timer = new Timer();
	public RotateCube1() {
		requires(Robot.intake);

	}

	protected void initialize() {
		timer.start();
	}

	protected void execute() {
		if (timer.get() < .2) {
			Robot.intake.leftIntake.set(0);
			Robot.intake.rightIntake.set(0.3);// intakes
		}
		else if (timer.get() < .4) {
			Robot.intake.leftIntake.set(0);
			Robot.intake.rightIntake.set(0);
		}
		timer.reset();
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {

		Robot.intake.leftIntake.set(0.0);
		Robot.intake.rightIntake.set(0.0);// stops motors

	}

	protected void interrupted() {
		end();
	}
}
