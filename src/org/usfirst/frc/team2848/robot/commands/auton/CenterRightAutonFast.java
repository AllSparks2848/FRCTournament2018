package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClampIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeightAuton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterRightAutonFast extends CommandGroup {

    public CenterRightAutonFast() {
    	addSequential(new ShiftHigh());
		addParallel(new ClampIntakeClaw());
        addSequential(new VelocityTurnToAngle(2, 26, 1));
        addSequential(new VelocityDriveToDistance(5, 10));
        addSequential(new GoToHeightAuton(200));
        addSequential(new ExtakeFrontAuton());
    }
}
