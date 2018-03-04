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
public class CenterLeftAutonFast extends CommandGroup {

    public CenterLeftAutonFast() {
    	addSequential(new ShiftHigh());
		addParallel(new ClampIntakeClaw());
        addSequential(new VelocityTurnToAngle(2, 329, 2));
        addSequential(new VelocityDriveToDistance(5, 10));
        addSequential(new GoToHeightAuton(200));
        addSequential(new SpitOutFront());
        
    }
}
