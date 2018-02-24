package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class PulseIntake extends Command {

	Timer timer = new Timer();
	double pulse = 100;// in milliseconds
	double milis = 0;

	public PulseIntake() {
		requires(Robot.intake);
		requires(Robot.carriage);
	}

	protected void initialize() {
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kReverse);
		timer.start();
	}

	protected void execute() {
		milis = timer.get() * 1000;
		if (((int) (milis / pulse)) % 2 == 0) {
			Robot.intake.rightIntake.set(0.0);
			Robot.intake.leftIntake.set(0.8);
			System.out.println("Left Intake Running");
		} else {
			Robot.intake.leftIntake.set(0.0);
			Robot.intake.rightIntake.set(-0.8);
			System.out.println("Right Intake Running");
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
