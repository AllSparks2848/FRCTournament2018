package org.usfirst.frc.team2848.robot.commands.drive;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.util.PIDCalculate;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VelocityTurnToAngle extends Command {
	
	double velocity, angle, direction;
	Timer timer = new Timer();
	
    public VelocityTurnToAngle(double velocity, double angle, double direction) {
        this.velocity = velocity;
        this.angle = angle;
        this.direction = direction;
    }

    protected void initialize() {
    	Robot.drivetrain.arcPIDs = new PIDCalculate(velocity, velocity, angle, direction);
        Robot.drivetrain.arcPIDs.start();
        timer.start();
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
    	return Robot.drivetrain.arcPIDs.interrupt == 1;
    }

    protected void end() {
    	
    	
    	Robot.drivetrain.drivetrainSetPowerZero();
    	
    	Robot.drivetrain.arcPIDs.interrupt();
    	try {
			Robot.drivetrain.arcPIDs.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    protected void interrupted() {
    	end();
    }
}
