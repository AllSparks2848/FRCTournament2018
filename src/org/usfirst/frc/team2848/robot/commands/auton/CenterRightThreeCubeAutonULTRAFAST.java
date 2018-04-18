package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCubeAuton;

import AutonCommandGroups.WaitThenDown;
import AutonCommandGroups.WaitThenElevator;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterRightThreeCubeAutonULTRAFAST extends CommandGroup {

	public CenterRightThreeCubeAutonULTRAFAST() {
		addSequential(new CenterRightAutonFast());
		
		// go to two
		addParallel(new WaitThenDown(0));
		addSequential(new VelocityDriveToDistance(-20, 3));
		addSequential(new VelocityTurnToAngle(8, 323, 1));

		addParallel(new IntakeCubeAuton(2));
		addSequential(new VelocityDriveToDistance(17, 2.2));

		addSequential(new VelocityDriveToDistance(-20, 2.3));

		addSequential(new VelocityTurnToAngle(8, 0, 1));
		addParallel(new WaitThenElevator(.1, 1000, 0.7));
		addSequential(new VelocityDriveToDistance(20, 3.5));
		
// go to 3
		addParallel(new WaitThenDown(0));
		addSequential(new VelocityDriveToDistance(-20, 1));
		addSequential(new VelocityTurnToAngle(8, 305, 1));

		addParallel(new IntakeCubeAuton(2.5));
		addSequential(new VelocityDriveToDistance(20, 2.75));

		addSequential(new VelocityDriveToDistance(-20, 2.7));

		addSequential(new VelocityTurnToAngle(8, 5, 1));	
		addParallel(new WaitThenElevator(.2, 1000, 0.7));
		addSequential(new VelocityDriveToDistance(20, 2.5));

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
