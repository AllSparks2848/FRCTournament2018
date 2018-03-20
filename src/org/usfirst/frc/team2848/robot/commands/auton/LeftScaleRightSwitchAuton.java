package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClampIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeLeft;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.intake.ClosePivot;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeWithStop;
import org.usfirst.frc.team2848.robot.commands.intake.SpitOutFront;
import org.usfirst.frc.team2848.robot.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleRightSwitchAuton extends CommandGroup {

	public LeftScaleRightSwitchAuton() {
		addSequential(new ShiftHigh());
		addSequential(new ClampIntakeClaw());
		addSequential(new VelocityDriveToDistance(-6.75, -26.25));
		addSequential(new GoToHeight(420));
		addSequential(new Wait(.2));
		addSequential(new ExtakeLeft());
		addSequential(new DownToBottom());
		addSequential(new VelocityDriveToDistance(6.75, 9.5));
		addSequential(new VelocityTurnToAngle(4, 280, 1));
		addSequential(new VelocityDriveToDistance(6, 16));
		addSequential(new VelocityTurnToAngle(4, 350, 1));
		
		addParallel(new IntakeWithStop());
		addSequential(new VelocityDriveToDistance(4, 2.1));
		
		
		addSequential(new GoToHeight(250));
		addSequential(new ClosePivot());
		addSequential(new VelocityDriveToDistance(4, 1.5));
		addSequential(new Wait(.2));
		addSequential(new SpitOutFront());
	}
}
