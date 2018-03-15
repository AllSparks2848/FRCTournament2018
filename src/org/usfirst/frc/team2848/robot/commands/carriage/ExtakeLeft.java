package org.usfirst.frc.team2848.robot.commands.carriage;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ExtakeLeft extends Command {

	Timer t = new Timer();

	public ExtakeLeft() {
		requires(Robot.carriage);
		requires(Robot.elevator);
	}

	protected void initialize() {
		t.start();
	}

	protected void execute() {

		Robot.carriage.omniPlateMotor.set(-1);// output cube to robot's left
		while(t.get() < 0.15) {
			
		}
			
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kReverse);

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
