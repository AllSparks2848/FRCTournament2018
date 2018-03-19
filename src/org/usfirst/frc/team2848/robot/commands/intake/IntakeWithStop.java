package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeWithStop extends Command {

	public IntakeWithStop() {
		requires(Robot.intake);
		requires(Robot.carriage);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kReverse);
		
		if( Robot.intake.breakBeam.get() == true) {
			Robot.intake.leftIntake.set(0.7);
			Robot.intake.rightIntake.set(-0.7);// intakes
			Robot.carriage.intakeClawMotor.set(0.7);
		} else {
			Robot.intake.leftIntake.set(0);
			Robot.intake.rightIntake.set(0);// intakes
			Robot.carriage.intakeClawMotor.set(0);
		}
		
	}

	protected boolean isFinished() {
		return Robot.intake.breakBeam.get() == false;
	}

	protected void end() {
		Robot.carriage.intakeClawMotor.set(0);
		Robot.intake.leftIntake.set(0.0);
		Robot.intake.rightIntake.set(0.0);// stops motors
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kForward);
	}

	protected void interrupted() {
		end();
	}
}
