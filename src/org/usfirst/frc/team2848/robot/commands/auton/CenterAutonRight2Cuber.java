package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.drive.DriveToDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterAutonRight2Cuber extends CommandGroup {

    public CenterAutonRight2Cuber() {
        //addSequential(new DriveToDistance());
    	
     addSequential(new DriveToDistance(2));
//     addSequential(new GyroTurn(45));
     addSequential(new DriveToDistance(8.6));
//     addSequential(new GyroTurn(-45));
     addSequential(new DriveToDistance(5));
     addSequential(new DriveToDistance(3));
//     addSequential(new GyroTurn(-90));
     addSequential(new DriveToDistance(5));
     addSequential(new DriveToDistance(-5));
    }
}
