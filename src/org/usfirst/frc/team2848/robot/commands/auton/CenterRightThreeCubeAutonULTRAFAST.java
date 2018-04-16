package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCubeAuton;
import org.usfirst.frc.team2848.robot.commands.intake.PivotIn;

import AutonCommandGroups.WaitThenElevator;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterRightThreeCubeAutonULTRAFAST extends CommandGroup {

	public CenterRightThreeCubeAutonULTRAFAST() {
		addSequential(new CenterRightAutonFast());
		
		double[] xp1 = {0, 3};
    	double[] yp1 = {1, 8.0};
    	double[] speeds1 = {0.7, 0.75};
    	Command[] actions1 = {new DoNothing(), new DoNothing()};
		
		// go to two
		addParallel(new DownToBottom());
		addSequential(new VelocityDriveToDistance(-20, 3.25));
		addSequential(new VelocityTurnToAngle(8, 323, 1));

		addParallel(new IntakeCubeAuton(2));
		addSequential(new VelocityDriveToDistance(17, 2.2));

		addSequential(new VelocityDriveToDistance(-20, 2.3));

		addSequential(new VelocityTurnToAngle(8, 0, 1));
		addParallel(new WaitThenElevator(.1));
		addSequential(new VelocityDriveToDistance(20, 4.5));
		
		double[] xp2 = {0, 3};
    	double[] yp2 = {1, 8.0};
    	double[] speeds2 = {0.7, 0.75};
    	Command[] actions2 = {new DoNothing(), new DoNothing()};
		
// go to 3
		addParallel(new DownToBottom());
		addSequential(new VelocityDriveToDistance(-20, 2.75));
		addSequential(new VelocityTurnToAngle(8, 305, 1));

		addParallel(new IntakeCubeAuton(2.5));
		addSequential(new VelocityDriveToDistance(20, 3.3));

		addSequential(new VelocityDriveToDistance(-20, 2));

		addSequential(new VelocityTurnToAngle(8, 12, 1));	
		addParallel(new WaitThenElevator(.2));
		addSequential(new VelocityDriveToDistance(20, 2));

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
