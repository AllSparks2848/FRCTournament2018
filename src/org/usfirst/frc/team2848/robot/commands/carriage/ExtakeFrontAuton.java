package org.usfirst.frc.team2848.robot.commands.carriage;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExtakeFrontAuton extends Command {
	Timer timer = new Timer();
	public ExtakeFrontAuton() {
		requires(Robot.carriage);
		requires(Robot.intake);
	}

	protected void initialize() {
		timer.start();
	}

	protected void execute() {
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.intakeClawMotor.set(-1);// outputs cube to robot's front
		Robot.carriage.omniPlateMotor.set(1.0);
	}

	protected boolean isFinished() {
		return timer.get() > 1;
	}

	protected void end() {
		Robot.carriage.intakeClawMotor.set(0);
		Robot.carriage.omniPlateMotor.set(0);
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kReverse);
	}

	protected void interrupted() {
		end();
	}
}
