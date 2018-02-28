package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCubeAuton extends Command {

	public IntakeCubeAuton() {
		requires(Robot.intake);
		requires(Robot.carriage);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kReverse);
		if (Robot.pdp.getCurrent(3) > 15) {
			Robot.intake.leftIntake.set(-0.8);
			Robot.intake.rightIntake.set(0.8);// intakes
			Robot.carriage.intakeClawMotor.set(-0.8);
		} else {
			Robot.intake.leftIntake.set(-0.6);
			Robot.intake.rightIntake.set(0.6);// intakes
			Robot.carriage.intakeClawMotor.set(-0.6);
		}

	}

	protected boolean isFinished() {
		return Robot.intake.sonar.getValue() < 60;
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
