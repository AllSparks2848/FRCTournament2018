package org.usfirst.frc.team2848.robot.commands.carriage;

import org.usfirst.frc.team2848.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ExtakeRight extends Command {
	
	Timer t = new Timer();
	
	public ExtakeRight() {
		requires(Robot.carriage);
	}

	protected void initialize() {
		t.start();
	}

	protected void execute() {
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kReverse);
		if(t.get() < 0.3) {
			
		} else {
			Robot.carriage.omniPlateMotor.set(1.0);
		}
	}

	protected boolean isFinished() {
		return t.get() > 1.5;
	}

	protected void end() {
		t.stop();
		Robot.carriage.omniPlateMotor.set(0.0);// stops motor
	}

	protected void interrupted() {
		end();
	}
}
