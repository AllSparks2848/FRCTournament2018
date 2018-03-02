package org.usfirst.frc.team2848.robot.commands.elevator;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DownToBottomAuton extends Command {

	Timer t = new  Timer();
	public DownToBottomAuton() {
		requires(Robot.elevator);
	}

	protected void initialize() {
		Robot.pivotIntake.intakePivot.set(DoubleSolenoid.Value.kReverse);
		t.start();
	}

	protected void execute() {
		Robot.pivotIntake.intakePivot.set(DoubleSolenoid.Value.kReverse);
		if(t.get()>.2) {
		if (Robot.elevator.elevatorEncoder.get() < 100) {
			Robot.elevator.elevatorMotor.set(.5);// sends carriage down
		} else {
			Robot.elevator.elevatorMotor.set(.7);// sends carriage down
		}
		}
	}

	protected boolean isFinished() {
		if (!Robot.elevator.limitSwitchElevatorBottom.get())
			return true;
		return false;
	}

	protected void end() {
		Robot.elevator.elevatorEncoder.reset();
		Robot.elevator.elevatorMotor.set(0.0);
	}

	protected void interrupted() {
		end();
	}
}
