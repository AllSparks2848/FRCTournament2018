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
import org.usfirst.frc.team2848.robot.commands.intake.SpitOutFront;
import org.usfirst.frc.team2848.robot.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class NewLeftSideScale extends CommandGroup {

    public NewLeftSideScale() {
    	addSequential(new ShiftHigh());
		addSequential(new ClampIntakeClaw());
		addSequential(new VelocityDriveToDistance(6, 28)); //23
		addSequential(new VelocityTurnToAngle(4, 90, 1));
//		addSequential(new VelocityDriveToDistance(6, 2));
		addSequential(new GoToHeight(420));
		addSequential(new VelocityDriveToDistance(3, 2));
		addSequential(new Wait(0.2));
		addSequential(new SpitOutFront());
		addSequential(new VelocityDriveToDistance(-3, 2));
		addSequential(new DownToBottom());
		addSequential(new VelocityTurnToAngle(4, 0, 1));
//		addSequential(new VelocityDriveToDistance(6.5, 9));
    }
}
