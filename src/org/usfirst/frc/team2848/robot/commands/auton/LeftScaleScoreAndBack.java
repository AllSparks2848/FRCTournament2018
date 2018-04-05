package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.PulseIntakeClawGoToHeightAuton;
import org.usfirst.frc.team2848.robot.commands.intake.Pivot;
import org.usfirst.frc.team2848.robot.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleScoreAndBack extends CommandGroup {

    public LeftScaleScoreAndBack() {
    	addSequential(new PulseIntakeClawGoToHeightAuton(420));
    	addSequential(new Wait(.2));
//		addSequential(new VelocityTurnToAngle(2, 10, 1));
//		addSequential(new VelocityDriveToDistance(3, 3));
//		addSequential(new ExtakeFront());
//		addSequential(new VelocityDriveToDistance(1, 3));
		addSequential(new Pivot());
		addSequential(new DownToBottom());
    }
}