package org.usfirst.frc.team2848.robot.commands.drive;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.util.PIDCalculate;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Odometer extends Command {

    public Odometer() {
    }

    protected void initialize() {
    	Robot.drivetrain.arcPIDs = new PIDCalculate(-4, -4, 0, 2);
    	Robot.drivetrain.arcPIDs.setPriority(10);
    	Robot.drivetrain.arcPIDs.setTargetXandY(-4, -4);
        Robot.drivetrain.arcPIDs.start();
    } 

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return Robot.drivetrain.arcPIDs.interrupt == 1;
    }

    protected void end() {
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