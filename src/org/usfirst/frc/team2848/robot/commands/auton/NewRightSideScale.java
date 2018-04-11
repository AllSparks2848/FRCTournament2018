package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.drive.DriveToPoint;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCubeAuton;
import org.usfirst.frc.team2848.robot.commands.intake.Pivot;
import org.usfirst.frc.team2848.robot.commands.intake.PivotOut;

import AutonCommandGroups.ExtakeAndDown;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class NewRightSideScale extends CommandGroup {

    public NewRightSideScale() {
    	
    	double[] xp = {2, 3.5, 3.5, -0.5, 3};
    	double[] yp = {4, 9.5, 2.5, 4, 3};
    	double[] speeds = {0.65, 0.65, -0.65, 0.65, -0.65};
    	Command[] actions = {new PivotOut(), new GoToHeight(1000), new ExtakeAndDown(), new IntakeCubeAuton(3), new GoToHeight(1000)};
    	
    	addSequential(new DriveToPoint(xp, yp, speeds, actions, 5));
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