package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClawDown;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleSetupAuton extends CommandGroup {

    public RightScaleSetupAuton() {
    	addSequential(new ShiftHigh());
		addSequential(new ClawDown());
		addSequential(new VelocityDriveToDistance(6, 20));
//		addSequential(new VelocityTurnToAngle(2, 90, 1));
//		addSequential(new VelocityDriveToDistance(4, 12));
    }
}
