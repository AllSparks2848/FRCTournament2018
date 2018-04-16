package org.usfirst.frc.team2848.robot.commands.drive;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.util.PIDCalculate;
import org.usfirst.frc.team2848.robot.util.PointNav;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToPoint extends Command {
	
	double[] X;
	double[] Y;
	double[] speeds;
	Command[] actions;
	int points;

    public DriveToPoint(double[] X, double[] Y, double[] speeds, Command[] actions, int points) {
    	this.X = X;
    	this.Y = Y;
    	this.speeds = speeds;
    	this.actions = actions;
    	this.points = points;
    }

    protected void initialize() {
    	Robot.drivetrain.LoLa = new PointNav(this.X, this.Y, this.speeds, this.points, this.actions, 50000);
    	Robot.drivetrain.LoLa.start();
    } 

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return Robot.drivetrain.LoLa.interrupt == 1;
    }

    protected void end() {
    	
    	System.out.println("Ending");
    	Robot.drivetrain.X_Point = Robot.drivetrain.LoLa.current_x;
    	Robot.drivetrain.Y_Point = Robot.drivetrain.LoLa.current_y;
    	
    	Robot.drivetrain.drivetrainSetPowerZero();
    	Robot.drivetrain.LoLa.interrupt();
    	try {
			Robot.drivetrain.LoLa.join();
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
    	
    	Robot.drivetrain.LoLa = null;
    }

    protected void interrupted() {
    	end();
    }
}