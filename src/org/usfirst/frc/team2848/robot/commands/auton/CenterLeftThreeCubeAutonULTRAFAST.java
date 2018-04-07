package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCubeAuton;
import org.usfirst.frc.team2848.robot.commands.intake.PivotIn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterLeftThreeCubeAutonULTRAFAST extends CommandGroup {

	public CenterLeftThreeCubeAutonULTRAFAST() {
		addSequential(new CenterLeftAutonFast());
		
		// go to two
		addParallel(new DownToBottom());
		addSequential(new VelocityDriveToDistance(-20, 3.25));
		addSequential(new VelocityTurnToAngle(8, 38, 1));

		addParallel(new IntakeCubeAuton(2));
		addSequential(new VelocityDriveToDistance(20, 2));

		addSequential(new VelocityDriveToDistance(-20, 2.5));

		addSequential(new VelocityTurnToAngle(8, 350, 1));
		addParallel(new WaitThenElevator(.1));
		addSequential(new VelocityDriveToDistance(20, 4.25));

		addSequential(new ExtakeFrontAuton());
// go to 3
		addParallel(new DownToBottom());
		addSequential(new VelocityDriveToDistance(-20, 2.75));
		addSequential(new VelocityTurnToAngle(8, 36, 1));

		addParallel(new IntakeCubeAuton(2.5));
		addSequential(new VelocityDriveToDistance(20, 3.3));

		addSequential(new VelocityDriveToDistance(-20, 2));

		addSequential(new VelocityTurnToAngle(8, 348, 1));
		addParallel(new WaitThenElevator(.05));
		addSequential(new VelocityDriveToDistance(20, 3));

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