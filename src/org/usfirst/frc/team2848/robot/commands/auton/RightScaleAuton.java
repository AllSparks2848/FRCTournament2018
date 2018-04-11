package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClawDown;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleAuton extends CommandGroup {

    public RightScaleAuton() {
    	addSequential(new ShiftHigh());
		addSequential(new ClawDown());
		addSequential(new VelocityDriveToDistance(-6.75, 27));
		addSequential(new VelocityTurnToAngle(4, 90, 1));
		addSequential(new GoToHeight(2600));
		addSequential(new VelocityDriveToDistance(2.5, 2));
    	addSequential(new Wait(.2));
    	addSequential(new ExtakeFrontAuton());
    	addSequential(new VelocityDriveToDistance(-2.5, 2));
		addSequential(new DownToBottom());
    }
}
