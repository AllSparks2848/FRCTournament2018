package org.usfirst.frc.team2848.robot.commands.carriage;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
public class ExtakeRight extends Command {

	public ExtakeRight() {
		requires(Robot.carriage);
	}

	protected void initialize() {
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kReverse);
	}

	protected void execute() {
//		if(Robot.elevator.elevatorEncoder.get() > Robot.elevator.sideSpitMinPos) {
			Robot.carriage.omniPlateMotor.set(1);// output cube to robot's right
//		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.carriage.omniPlateMotor.set(0.0);// stops motor
	}

	protected void interrupted() {
		end();
	}
}
