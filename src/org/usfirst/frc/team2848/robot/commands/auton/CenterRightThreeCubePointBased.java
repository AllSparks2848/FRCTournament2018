package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.drive.DriveToPoint;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterRightThreeCubePointBased extends CommandGroup {

	public CenterRightThreeCubePointBased() {
		addSequential(new CenterRightAutonFast());
		
		double[] xp1 = {1, -0.5, -1.5, 3};
    	double[] yp1 = {3, 8.0, 9.0, 3};
    	double[] speeds1 = {-0.7, 0, 0.5, -0.7};
    	Command[] actions1 = {new DoNothing(), new DoNothing(), new DoNothing(), new DoNothing()};
		
		// go to two
		addParallel(new DownToBottom());
		addSequential(new DriveToPoint(xp1, yp1, speeds1, actions1, 4));
		
		
//		double[] xp2 = {0, 3};
//    	double[] yp2 = {1, 8.0};
//    	double[] speeds2 = {0.7, 0.75};
//    	Command[] actions2 = {new DoNothing(), new DoNothing()};
//		
//// go to 3
//		addParallel(new DownToBottom());
//		addSequential(new DriveToPoint(xp, yp, speeds, actions, 1));
		

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
