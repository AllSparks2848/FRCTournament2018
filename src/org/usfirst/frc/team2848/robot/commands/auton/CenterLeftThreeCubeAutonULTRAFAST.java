package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCubeAuton;

import AutonCommandGroups.WaitThenDown;
import AutonCommandGroups.WaitThenElevator;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterLeftThreeCubeAutonULTRAFAST extends CommandGroup {

	public CenterLeftThreeCubeAutonULTRAFAST() {
		addSequential(new CenterLeftAutonFast());
		
		// go to two
		addParallel(new WaitThenDown(0));
		addSequential(new VelocityDriveToDistance(-20, 3.1));
		addSequential(new VelocityTurnToAngle(8, 33, 1));

		addParallel(new IntakeCubeAuton(2));
		addSequential(new VelocityDriveToDistance(20, 1.9));

		addSequential(new VelocityDriveToDistance(-20, 2.5));

		addSequential(new VelocityTurnToAngle(8, 343, 1));
		addParallel(new WaitThenElevator(.1, 1000, 0.7));
		addSequential(new VelocityDriveToDistance(20, 4.25));
// go to 3
		addParallel(new WaitThenDown(0));
		addSequential(new VelocityDriveToDistance(-20, 2.5));
		addSequential(new VelocityTurnToAngle(8, 33, 1));

		addParallel(new IntakeCubeAuton(2.5));
		addSequential(new VelocityDriveToDistance(20, 3.1));

		addSequential(new VelocityDriveToDistance(-20, 2));

		addSequential(new VelocityTurnToAngle(8, 348, 1));
		addParallel(new WaitThenElevator(.05, 1000, 0.7));
		addSequential(new VelocityDriveToDistance(20, 3));
		
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
