package org.usfirst.frc.team2848.robot.commands.elevator;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoToHeight extends Command {
	double lastTick = 0;
	double target = 0;
//	Timer t = new Timer();
	
	
	public GoToHeight(double x) {// use target height as parameter
		requires(Robot.elevator);
		
		target = x;
	}

	protected void initialize() {
		lastTick = Robot.elevator.elevatorEncoder.get();
//		t.start();
	}

	protected void execute() {
//		if(t.get() ==.1 && Robot.elevator.elevatorEncoder.get()<lastTick +5) {
//			end();
//		}
//		if(t.get()>.2)
//			Robot.pivotIntake.intakePivot.set(DoubleSolenoid.Value.kForward);
		Robot.elevator.goToPosition(target);
	}

	protected boolean isFinished() {
		if(!Robot.elevator.limitSwitchElevatorTop.get()) {
			end();
		}
		return Math.abs(Robot.elevator.elevatorEncoder.get() - target) < 5 ;
	}

	protected void end() {
		Robot.elevator.elevatorMotor.set(0);
	}

	protected void interrupted() {
		end();
	}
}
