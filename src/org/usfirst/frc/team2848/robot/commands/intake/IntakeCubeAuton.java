package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCubeAuton extends Command {
	Timer timer = new Timer();
	double time;
	public IntakeCubeAuton(double time) {
		requires(Robot.intake);
		requires(Robot.carriage);
		this.time = time;
	}

	protected void initialize() {
		timer.start();
	}

	protected void execute() {
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kReverse);
			Robot.intake.leftIntake.set(0.8);
			Robot.intake.rightIntake.set(-0.8);// intakes
			Robot.carriage.intakeClawMotor.set(-0.8);
			Robot.carriage.omniPlateMotor.set(1.0);// output cube to robot's left
	}

	protected boolean isFinished() {
//		return Robot.intake.sonar.getValue() < 60;
		return timer.get() > time;
	}

	protected void end() {
		Robot.carriage.intakeClawMotor.set(0);
		Robot.carriage.omniPlateMotor.set(0);
		Robot.intake.leftIntake.set(0.0);
		Robot.intake.rightIntake.set(0.0);// stops motors
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kForward);
		
	}

	protected void interrupted() {
		end();
	}
}
