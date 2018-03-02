package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFront;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeightAuton;
import org.usfirst.frc.team2848.robot.commands.intake.Pivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleScoreAndBack extends CommandGroup {

    public LeftScaleScoreAndBack() {
    	addSequential(new GoToHeightAuton(420));
		addSequential(new VelocityTurnToAngle(2, 10, 1));
		addSequential(new VelocityDriveToDistance(3, 3));
		addSequential(new ExtakeFront());
		addSequential(new VelocityDriveToDistance(1, 3));
		addSequential(new Pivot());
		addSequential(new DownToBottom());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
