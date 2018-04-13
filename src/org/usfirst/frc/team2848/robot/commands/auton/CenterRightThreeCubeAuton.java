package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClawDown;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToPoint;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCubeAuton;
import org.usfirst.frc.team2848.robot.commands.intake.PivotIn;
import org.usfirst.frc.team2848.robot.commands.intake.PivotOut;

import AutonCommandGroups.ExtakeAndDown;
import AutonCommandGroups.WaitThenElevator;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterRightThreeCubeAuton extends CommandGroup {

	public CenterRightThreeCubeAuton() {
		double[] xp = {2, 3.5};
    	double[] yp = {4, 9.5};
    	double[] speeds = {0.65, 0.65};
    	Command[] actions = {new PivotOut(), new GoToHeight(1000)};
    	
    	addSequential(new ShiftHigh());
		addSequential(new ClawDown());
    	
    	addSequential(new DriveToPoint(xp, yp, speeds, actions, 2));
		
		// go to two
		addParallel(new ExtakeAndDown());
		addSequential(new VelocityDriveToDistance(-15, 3));
		addSequential(new VelocityTurnToAngle(4, 323, 1));

		addParallel(new IntakeCubeAuton(2));
		addSequential(new VelocityDriveToDistance(15, 2));

		addSequential(new VelocityDriveToDistance(-15, 2.5));

		addSequential(new VelocityTurnToAngle(4, 12, 1));
		addParallel(new WaitThenElevator(.5));
		addSequential(new VelocityDriveToDistance(8, 5.25));

		addSequential(new ExtakeFrontAuton());
// go to 3
		addParallel(new DownToBottom());
		addSequential(new VelocityDriveToDistance(-15, 3));
		addSequential(new VelocityTurnToAngle(4, 318, 1));

		addParallel(new IntakeCubeAuton(2.5));
		addSequential(new VelocityDriveToDistance(15, 3));

		addSequential(new VelocityDriveToDistance(-15, 2.5));

		addSequential(new VelocityTurnToAngle(4, 12, 1));
		addParallel(new WaitThenElevator(.3));
		addSequential(new VelocityDriveToDistance(8, 3.5));

		addSequential(new ExtakeFrontAuton());
		// addSequential(new VelocityDriveToDistance(-5, 6));
		//
		// addParallel(new ClampIntakeClaw());
		// addSequential(new VelocityTurnToAngle(4, 19, 1));
		// addSequential(new VelocityDriveToDistance(6, 9.75));
		// addParallel(new GoToHeightAuton(200));
		// addParallel(new PivotIn());
		// addSequential(new ExtakeFrontAuton());
	}
}
