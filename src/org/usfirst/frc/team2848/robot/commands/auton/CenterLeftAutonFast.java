package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClampIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterLeftAutonFast extends CommandGroup {

    public CenterLeftAutonFast() {
    	addSequential(new ShiftHigh());
		addParallel(new ClampIntakeClaw());
        addSequential(new VelocityTurnToAngle(20, 322, 1));
        addParallel(new WaitThenElevator(1));
        addSequential(new VelocityDriveToDistance(10, 9));
        addSequential(new ExtakeFrontAuton());
        
    }
}
