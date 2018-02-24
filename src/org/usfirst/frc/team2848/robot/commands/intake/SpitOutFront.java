package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpitOutFront extends Command {

	public SpitOutFront() {
		requires(Robot.intake);
		requires(Robot.carriage);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kForward);
		Robot.intake.leftIntake.set(0.8);
		Robot.intake.rightIntake.set(-0.8);// extakes
		Robot.carriage.intakeClawMotor.set(1);
	}

	protected boolean isFinished() {
		
		
		return false;
	}

	protected void end() {
		Robot.carriage.intakeClawMotor.set(0);
		Robot.intake.leftIntake.set(0.0);
		Robot.intake.rightIntake.set(0.0);// stops motors
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kReverse);
	}

	protected void interrupted() {
		end();
	}
}
