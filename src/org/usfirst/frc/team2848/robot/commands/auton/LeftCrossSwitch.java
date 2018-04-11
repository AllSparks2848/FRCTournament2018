package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClawDown;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeightAuton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftCrossSwitch extends CommandGroup {

	public LeftCrossSwitch() {
		addSequential(new ShiftHigh());
		addSequential(new ClawDown());
		addSequential(new VelocityDriveToDistance(6, 19));

		addSequential(new VelocityTurnToAngle(2, 80, 1));
		
		addSequential(new VelocityDriveToDistance(6, 13));
		
		addSequential(new VelocityTurnToAngle(2, 130, 1));
		addSequential(new GoToHeightAuton(220));
	}
}