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
	
	double x, y;
	
	PointNav LoLa;

    public DriveToPoint(double[] X, double[] Y, double[] speeds, Command[] actions, int points) {
    	LoLa = new PointNav(X, Y, speeds, points, actions, 100000);
    }

    protected void initialize() {
    	LoLa.start();
    } 

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return LoLa.interrupt == 1;
    }

    protected void end() {
    	Robot.drivetrain.drivetrainSetPowerZero();
    	LoLa.interrupt();
    	try {
			LoLa.join();
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
    }

    protected void interrupted() {
    	end();
    }
}