package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClawDown;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeightAuton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitchAuton extends CommandGroup {

	public LeftSwitchAuton() {
		addSequential(new ShiftHigh());
		addSequential(new ClawDown());
		addSequential(new VelocityDriveToDistance(6, 12.5));
		addSequential(new GoToHeightAuton(220));
	}
}
