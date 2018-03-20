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
public class ForwardAndCross extends CommandGroup {

    public ForwardAndCross() {
    	addSequential(new ShiftHigh());
		addSequential(new ClampIntakeClaw());
		addSequential(new VelocityDriveToDistance(-6.5, -19));
		addSequential(new VelocityTurnToAngle(4, 270, 1));
		addSequential(new VelocityDriveToDistance(6, 15.75));
		addSequential(new VelocityTurnToAngle(3, 180, 1));
		addSequential(new VelocityDriveToDistance(4, 2));
		addSequential(new GoToHeight(420));
		addSequential(new ExtakeFrontAuton());
		addSequential(new DownToBottom());
		
		
		//scale score
//		addSequential(new GoToHeightAuton(420));
//		addSequential(new VelocityDriveToDistance(3, 3));
//		addSequential(new ExtakeFront());
    }
}