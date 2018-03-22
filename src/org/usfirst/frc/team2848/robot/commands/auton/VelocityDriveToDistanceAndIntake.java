package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCubeAuton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VelocityDriveToDistanceAndIntake extends CommandGroup {

    public VelocityDriveToDistanceAndIntake() {
    	addSequential(new IntakeCubeAuton(3));
        addParallel(new VelocityDriveToDistance(4, 4));
        
    }
}
