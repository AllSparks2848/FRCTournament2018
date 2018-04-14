package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClawDown;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToPoint;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.intake.PivotOut;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSidePointBased extends CommandGroup {

    public LeftSidePointBased() {
    	
    	double[] xp = {0};
    	double[] yp = {25};
    	double[] speeds = {0.75};
    	Command[] actions = {new PivotOut()};
    	
    	addSequential(new ShiftHigh());
		addParallel(new ClawDown());
    	addSequential(new DriveToPoint(xp, yp, speeds, actions, 1));
    	addSequential(new VelocityTurnToAngle(4, 90, 1));
    	addSequential(new GoToHeight(2400));
		addSequential(new ExtakeFrontAuton());
//    	
//		addSequential(new ClawDown());
//		addSequential(new VelocityDriveToDistance(6, 20));
//		addSequential(new VelocityTurnToAngle(4, 90, 1));
//		addSequential(new VelocityDriveToDistance(6, 15.75));
//		addSequential(new VelocityTurnToAngle(4, 0, 1));
//		addSequential(new VelocityDriveToDistance(4, 2));
//		addSequential(new GoToHeight(420));
//		addSequential(new ExtakeFrontAuton());
//		addSequential(new DownToBottom());
    }
}