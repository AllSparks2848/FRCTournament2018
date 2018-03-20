package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClampIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeLeft;
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
public class LeftSwitchRightScaleAuton extends CommandGroup {

    public LeftSwitchRightScaleAuton() {
    	addSequential(new ShiftHigh());
		addSequential(new ClampIntakeClaw());
		addSequential(new VelocityTurnToAngle(4, 5, 1));
		addSequential(new VelocityDriveToDistance(-6.75, -13));
		addParallel(new GoToHeight(250));
    	addSequential(new Wait(.4));
    	addSequential(new ExtakeLeft());
    	addSequential(new DownToBottom());
    	addSequential(new VelocityDriveToDistance(-6.75, -5));
    	addSequential(new VelocityTurnToAngle(4, 345, 1));
    	addParallel(new IntakeCubeAuton(2.75));
    	addSequential(new VelocityDriveToDistance(4, 2));
    	addSequential(new VelocityDriveToDistance(-4, -2));
    	addSequential(new VelocityTurnToAngle(4, 270, 1));
    	
    	addSequential(new VelocityDriveToDistance(6, 15.5));
		addSequential(new VelocityTurnToAngle(4, 180, 1));
		addSequential(new VelocityDriveToDistance(5, 2));
		addParallel(new IntakeCubeAuton(.2));
		addSequential(new GoToHeight(420));
		addSequential(new Wait(.4));
		addSequential(new ExtakeFrontAuton());
		addSequential(new DownToBottom());
    }
}
