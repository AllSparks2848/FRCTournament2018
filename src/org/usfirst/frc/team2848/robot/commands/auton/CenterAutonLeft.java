package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.drive.DriveToDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterAutonLeft extends CommandGroup {

    public CenterAutonLeft() {
        //addSequential(new DriveToDistance());
    	
     addSequential(new DriveToDistance(2));
//     addSequential(new GyroTurn(-45));
     addSequential(new DriveToDistance(8.2));
//     addSequential(new GyroTurn(45));
     addSequential(new DriveToDistance(5));
    }
}
