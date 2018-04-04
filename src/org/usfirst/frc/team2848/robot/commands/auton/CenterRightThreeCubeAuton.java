package org.usfirst.frc.team2848.robot.commands.auton;



import org.usfirst.frc.team2848.robot.commands.carriage.ClampIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeLeftAuton;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeightAuton;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCubeAuton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterRightTwoCubeAuton extends CommandGroup {

    public CenterRightTwoCubeAuton() {
    	addSequential(new ShiftHigh());
		addParallel(new ClampIntakeClaw());
        addSequential(new VelocityTurnToAngle(4, 19, 1));
        addSequential(new VelocityDriveToDistance(6, 9.75));
        addSequential(new GoToHeightAuton(200));
        addSequential(new ExtakeFrontAuton());
        addSequential(new VelocityTurnToAngle(4, 19, 1));
        addParallel(new DownToBottom());
        addSequential(new VelocityDriveToDistance(-6, 9.75));
        
        addSequential(new VelocityTurnToAngle(4, 360, 1));
        
        addParallel(new IntakeCubeAuton(3));
        addSequential(new VelocityDriveToDistance(4, 6));
        addSequential(new VelocityDriveToDistance(-5, 1.5));
        
        addSequential(new VelocityTurnToAngle(4, 40, 1));
        addSequential(new VelocityDriveToDistance(5, 7));
        addSequential(new GoToHeightAuton(200));
        addSequential(new ExtakeLeftAuton());
        
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
