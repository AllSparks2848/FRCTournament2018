package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClawDown;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.intake.PivotIn;

import AutonCommandGroups.WaitThenElevator;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterRightAutonFast extends CommandGroup {

    public CenterRightAutonFast() {
    	addSequential(new ShiftHigh());
		addParallel(new ClawDown());
		
        addSequential(new VelocityTurnToAngle(4, 25, 1));
        addParallel(new WaitThenElevator(1));
        addSequential(new VelocityDriveToDistance(15, 7));
    }
}
