package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClawDown;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;

import AutonCommandGroups.WaitThenElevator;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterLeftAutonFast extends CommandGroup {

    public CenterLeftAutonFast() {
    	addSequential(new ShiftHigh());
		addParallel(new ClawDown());
        addSequential(new VelocityTurnToAngle(20, 323, 1));
        addParallel(new WaitThenElevator(1));
        addSequential(new VelocityDriveToDistance(10, 8.125));
        addSequential(new ExtakeFrontAuton());
        
    }
}
