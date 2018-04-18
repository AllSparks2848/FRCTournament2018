package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClawDown;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFront;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToPoint;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.intake.PivotOut;
import org.usfirst.frc.team2848.robot.util.Wait;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleCross extends CommandGroup {
	
    public LeftScaleCross() {
    	double[] xp = {0, 4, 15.5, 20.5};
    	double[] yp = {14, 19, 19, 26.5};
    	double[] speeds = {0.7, 0.7, 0.7, 0.65};
    	Command[] actions = {new PivotOut(),  new DoNothing(),  new DoNothing(),  new DoNothing()};
    	
    	addSequential(new ShiftHigh());
		addParallel(new ClawDown());
    	addSequential(new DriveToPoint(xp, yp, speeds, actions, 4));
    	addSequential(new VelocityTurnToAngle(4, 270, 1));
    	addSequential(new GoToHeight(2400));
    	addSequential(new Wait(0.5));
		addSequential(new ExtakeFront(0.5, 1.0));
    }
}
