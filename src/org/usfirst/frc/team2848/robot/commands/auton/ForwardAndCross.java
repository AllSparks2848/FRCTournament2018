package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClampIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFront;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeRightAuton;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeightAuton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ForwardAndCross extends CommandGroup {

    public ForwardAndCross() {
    	addSequential(new ShiftHigh());
		addSequential(new ClampIntakeClaw());
		addSequential(new VelocityDriveToDistance(6, 19));
		addSequential(new VelocityTurnToAngle(2, 80, 1));
		addSequential(new VelocityDriveToDistance(6, 13));
		addSequential(new VelocityTurnToAngle(2, 345, 2));
		
		
		//scale score
		addSequential(new GoToHeightAuton(420));
		addSequential(new VelocityDriveToDistance(3, 3));
		addSequential(new ExtakeFront());
    }
}