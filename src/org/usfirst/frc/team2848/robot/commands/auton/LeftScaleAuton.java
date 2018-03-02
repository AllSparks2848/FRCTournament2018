package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClampIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFront;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeightAuton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleAuton extends CommandGroup {

	public LeftScaleAuton() {
		addSequential(new ShiftHigh());
		addSequential(new ClampIntakeClaw());
		addSequential(new VelocityDriveToDistance(6, 21));
		for (int i = 0; i <5000; i++) {
			System.out.println("ENDING");
		}
		addSequential(new GoToHeightAuton(420));
		addSequential(new VelocityTurnToAngle(2, 10, 1));
		addSequential(new VelocityDriveToDistance(3, 3));
		addSequential(new ExtakeFront());
	}
}
