package org.usfirst.frc.team2848.robot.commands.carriage;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExtakeFront extends Command {
	
	Timer timer = new Timer();
	double power;
	double time;
	

	public ExtakeFront(double time, double power) {
		requires(Robot.carriage);
		requires(Robot.intake);
		
		this.power = power;
		this.time = time;
	}

	protected void initialize() {
		timer.reset();
		timer.start();
	}

	protected void execute() {
		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.intakeClawMotor.set(-power);// outputs cube to robot's front
		Robot.carriage.omniPlateMotor.set(-power);
	}

	protected boolean isFinished() {
		return timer.get() > this.time;
	}

	protected void end() {
		Robot.carriage.intakeClawMotor.set(0.0);
		Robot.carriage.omniPlateMotor.set(0);
	}

	protected void interrupted() {
		end();
	}
}
