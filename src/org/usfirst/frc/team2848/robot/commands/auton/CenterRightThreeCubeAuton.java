package org.usfirst.frc.team2848.robot.commands.auton;



import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCubeAuton;
import org.usfirst.frc.team2848.robot.commands.intake.PivotIn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterRightThreeCubeAuton extends CommandGroup {

    public CenterRightThreeCubeAuton() {
    	addSequential(new CenterRightAutonFast());
    	addParallel(new DownToBottom());
    	addSequential(new VelocityDriveToDistance(-20, 4));
    	addSequential(new VelocityTurnToAngle(4, 317.5, 1));
    	
    	addParallel(new IntakeCubeAuton(2.5));
    	addSequential(new VelocityDriveToDistance(10,3.5));
    	addParallel(new WaitThenElevator());
    	addSequential(new VelocityDriveToDistance(-10,3.5));
    	
    	
    	
    	addSequential(new VelocityTurnToAngle(4, 4, 1));
    	addParallel(new PivotIn());
    	addSequential(new VelocityDriveToDistance(8,4));
    	
        addSequential(new ExtakeFrontAuton());
//        addSequential(new VelocityDriveToDistance(-5, 6));
//        
//        addParallel(new ClampIntakeClaw());
//        addSequential(new VelocityTurnToAngle(4, 19, 1));
//        addSequential(new VelocityDriveToDistance(6, 9.75));
//        addParallel(new GoToHeightAuton(200));
//        addParallel(new PivotIn());
//        addSequential(new ExtakeFrontAuton());
    }
}
