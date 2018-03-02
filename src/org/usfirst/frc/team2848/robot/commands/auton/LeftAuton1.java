package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClampIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeLeftAuton;
import org.usfirst.frc.team2848.robot.commands.carriage.ReleaseIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCubeAuton;
import org.usfirst.frc.team2848.robot.commands.intake.PivotOut;
import org.usfirst.frc.team2848.robot.util.Wait;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftAuton1 extends CommandGroup {
	Timer timer = new Timer();

	public LeftAuton1() {
		addSequential(new ShiftHigh());
		addSequential(new ClampIntakeClaw());
		addSequential(new VelocityDriveToDistance(-6, -13.5));
		addParallel(new PivotOut());
		addParallel(new Wait(.3));
		addParallel(new DownToBottom());
		
//		addSequential(new GoToHeight(250));
//		addSequential(new Wait(.5));
//		addSequential(new ReleaseIntakeClaw());
//		addSequential(new Wait(.5));
//		addSequential(new ExtakeLeftAuton());
		
//		addSequential(new DownToBottom());
		addSequential(new VelocityDriveToDistance(-6, -4.5)); //was 2.75
		addSequential(new VelocityTurnToAngle(2.5, 310, 2)); //was 65
		addParallel(new IntakeCubeAuton());
		addSequential(new VelocityDriveToDistance(3, 2));
		addSequential(new VelocityDriveToDistance(-2, -2));
		addSequential(new VelocityTurnToAngle(3, 180, 2));
		
//		addSequential(new VelocityTurnToAngle(3, -60, 2));
//		addSequential(new Wait(.5));
//		addParallel(new IntakeCube());
//		addSequential(new VelocityDriveToDistance(3, 1.5));
	}
}
