package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpitOutFront extends Command {
	Timer timer = new Timer();
	public SpitOutFront() {
		requires(Robot.intake);
		requires(Robot.carriage);
	}

	protected void initialize() {
		timer.start();
	}

	protected void execute() {
//		if(Robot.elevator.elevatorEncoder.get() > 50) {
			Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kForward);
			Robot.carriage.intakeClawMotor.set(-1);
//		} else {
//			Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kForward);
			Robot.intake.leftIntake.set(-0.8);
			Robot.intake.rightIntake.set(0.8);// extakes
//			Robot.carriage.intakeClawMotor.set(-1);
			
			Robot.carriage.omniPlateMotor.set(1.0);
//		}
	}

	protected boolean isFinished() {
		return timer.get() > 2;
	}

	protected void end() {
		Robot.carriage.intakeClawMotor.set(0);
		Robot.intake.leftIntake.set(0.0);
		Robot.intake.rightIntake.set(0.0);// stops motors
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kReverse);
		Robot.carriage.omniPlateMotor.set(0);
	}

	protected void interrupted() {
		end();
	}
}
