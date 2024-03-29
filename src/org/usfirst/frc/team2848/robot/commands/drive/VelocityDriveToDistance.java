package org.usfirst.frc.team2848.robot.commands.drive;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.util.PIDCalculate;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VelocityDriveToDistance extends Command {
	double velocity, distance;

    public VelocityDriveToDistance(double velocity, double distance) {
        this.velocity = velocity;
        this.distance = distance;
    }

    protected void initialize() {
    	Robot.drivetrain.arcPIDs = new PIDCalculate(velocity, velocity, distance, 0);
    	Robot.drivetrain.arcPIDs.setPriority(10);
        Robot.drivetrain.arcPIDs.start();
        
//        System.out.println("StartingVelocity");
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