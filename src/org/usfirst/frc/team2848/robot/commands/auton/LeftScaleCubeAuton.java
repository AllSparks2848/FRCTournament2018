package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClampIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeLeft;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToPoint;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCubeAuton;
import org.usfirst.frc.team2848.robot.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleCubeAuton extends CommandGroup {

    public LeftScaleCubeAuton() {
    	addSequential(new ShiftHigh());
		addSequential(new ClampIntakeClaw());
//		addSequential(new VelocityDriveToDistance(-6, -12.5));
//		addSequential(new VelocityDriveToDistance(-7.5, -26.25)); //shop scale is 8 inches back (25.5)
		addSequential(new VelocityDriveToDistance(-6.75, -26.25));
		addSequential(new GoToHeight(420));
    	addSequential(new Wait(.2));
    	addSequential(new ExtakeLeft());
		addSequential(new DownToBottom());
		addSequential(new VelocityTurnToAngle(4, 345, 1));
		addParallel(new IntakeCubeAuton(4.5));
		addSequential(new VelocityDriveToDistance(4, 9.25));
		addSequential(new VelocityDriveToDistance(-6.5, 9));
		addSequential(new VelocityTurnToAngle(4, 0, 1));
		addSequential(new VelocityDriveToDistance(-6, .75));
		addSequential(new GoToHeight(420));
		addSequential(new Wait(.2));
    	addSequential(new ExtakeLeft());
    	addSequential(new DownToBottom());
    }
}

/*
addSequential(new DriveToPoint(0, -26.25));
addSequential(new GoToHeight(420));
addSequential(new Wait(.2));
addSequential(new ExtakeLeft());
addSequential(new DownToBottom());
addParallel(new IntakeCubeAuton());
addSequential(new DriveToPoint(-2, -22.25));
addSequential(new DriveToPoint(0, -26.25));
addSequential(new VelocityTurnToAngle(2, 0, 1));
*/
