package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClampIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class NewRightSideScale extends CommandGroup {

    public NewRightSideScale() {
    	addSequential(new ShiftHigh());
		addSequential(new ClampIntakeClaw());
		addSequential(new VelocityDriveToDistance(6, 20));
		addSequential(new VelocityTurnToAngle(4, 90, 1));
		addSequential(new VelocityDriveToDistance(6, 15.75));
		addSequential(new VelocityTurnToAngle(4, 0, 1));
		addSequential(new VelocityDriveToDistance(4, 2));
		addSequential(new GoToHeight(420));
		addSequential(new ExtakeFrontAuton());
		addSequential(new DownToBottom());
    }
}