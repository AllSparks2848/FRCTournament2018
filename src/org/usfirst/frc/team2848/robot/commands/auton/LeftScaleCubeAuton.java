package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClampIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeLeftAuton;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.PulseIntakeClawGoToHeightAuton;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCubeAuton;
import org.usfirst.frc.team2848.robot.commands.intake.Pivot;
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
		addSequential(new VelocityDriveToDistance(-7.5, -26.25)); //shop scale is 8 inches back (25.5)
		addSequential(new PulseIntakeClawGoToHeightAuton(420));
    	addSequential(new Wait(.2));
    	addSequential(new ExtakeLeftAuton());
		addSequential(new DownToBottom());
		addSequential(new VelocityTurnToAngle(2, 339, 2));
		addParallel(new IntakeCubeAuton());
		addSequential(new VelocityDriveToDistance(4, 9.5));
		
    }
}
