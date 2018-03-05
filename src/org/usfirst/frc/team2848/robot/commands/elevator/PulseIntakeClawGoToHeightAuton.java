package org.usfirst.frc.team2848.robot.commands.elevator;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PulseIntakeClawGoToHeightAuton extends Command {
	double lastTick = 0;
	double target = 0;
	Timer t = new Timer();
	double pulse = 250;// in milliseconds
	double milis = 0;

	public PulseIntakeClawGoToHeightAuton(double x) {// use target height as
														// parameter
		requires(Robot.elevator);
		requires(Robot.carriage);

		target = x;
	}

	protected void initialize() {
		lastTick = Robot.elevator.elevatorEncoder.get();
		t.start();
	}

	protected void execute() {
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kForward);
		milis = t.get() * 1000;
		if (((int) (milis / pulse)) % 2 == 0) {
			Robot.carriage.intakeClawMotor.set(.8);
		} else {
			Robot.carriage.intakeClawMotor.set(0);
		}

		Robot.elevator.goToPosition(target);
	}

	protected boolean isFinished() {
		if (!Robot.elevator.limitSwitchElevatorTop.get()) {
			return true;
		}
		return (Robot.elevator.elevatorEncoder.get() > target + 5)
				|| Math.abs(Robot.elevator.elevatorEncoder.get() - target) < 10;
	}

	protected void end() {
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kReverse);
		Robot.elevator.elevatorMotor.set(0);
		Robot.carriage.intakeClawMotor.set(0);
	}

	protected void interrupted() {
		end();
	}
}
