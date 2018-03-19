package org.usfirst.frc.team2848.robot.commands.carriage;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ExtakeLeftAuton extends Command {
	Timer timer = new Timer();
	public ExtakeLeftAuton() {
		requires(Robot.carriage);
	}

	protected void initialize() {
		timer.start();
	}

	protected void execute() { 
		Robot.carriage.omniPlateMotor.set(-1);// output cube to robot's left
		while(timer.get() < 0.5) {
			
		}
		
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kReverse);
		
		if(Robot.elevator.elevatorEncoder.get() > Robot.elevator.sideSpitMinPos) {
			Robot.carriage.omniPlateMotor.set(-1);// output cube to robot's left
		}
	}

	protected boolean isFinished() {
		return timer.get() > 1.5;
	}

	protected void end() {
		Robot.carriage.omniPlateMotor.set(0.0);// stops motor
	}

	protected void interrupted() {
		end();
	}
}
