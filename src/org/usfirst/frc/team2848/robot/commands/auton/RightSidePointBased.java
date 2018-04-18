package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClawDown;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFront;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToPoint;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCubeAuton;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSidePointBased extends CommandGroup {

    public RightSidePointBased() {
    	
    	double[] xp = {0, 0};
    	double[] yp = {10, 24.5};
    	double[] speeds = {0.7, 0.75};
    	Command[] actions = {new DoNothing(), new DoNothing()};
    	
    	addSequential(new ShiftHigh());
		addParallel(new ClawDown());
    	addSequential(new DriveToPoint(xp, yp, speeds, actions, 2));
    	addSequential(new VelocityTurnToAngle(4, 270, 1));
    	addSequential(new GoToHeight(2300));
		addSequential(new ExtakeFront(0.5, 1.0));
		
		addSequential(new DownToBottom());
		addSequential(new VelocityTurnToAngle(4, 200, 1));
		addParallel(new IntakeCubeAuton(6));
		
		addSequential(new VelocityDriveToDistance(6, 9));
		
		addSequential(new VelocityDriveToDistance(-10, 7.9));
		addParallel(new ClawDown());
		addSequential(new VelocityTurnToAngle(4, 270, 1));
    	addSequential(new GoToHeight(2300));
		addSequential(new ExtakeFront(0.5, 1.0));
    }
}