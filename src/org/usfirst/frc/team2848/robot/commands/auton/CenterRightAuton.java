package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClampIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeightAuton;
import org.usfirst.frc.team2848.robot.commands.intake.SpitOutFront;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterRightAuton extends CommandGroup {

    public CenterRightAuton() {
    	addSequential(new ShiftHigh());
		addSequential(new ClampIntakeClaw());
        addSequential(new VelocityDriveToDistance(5, 3));
        addSequential(new VelocityTurnToAngle(2, 40, 1));
        addSequential(new VelocityDriveToDistance(5, 6.5));
        addSequential(new VelocityTurnToAngle(2, 360, 2));
        addSequential(new GoToHeightAuton(200));
        addSequential(new VelocityDriveToDistance(5, 2.25));
        addSequential(new SpitOutFront());
    }
}