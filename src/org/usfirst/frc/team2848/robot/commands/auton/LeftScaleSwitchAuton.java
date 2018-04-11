package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClawDown;
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
public class LeftScaleSwitchAuton extends CommandGroup {

    public LeftScaleSwitchAuton() {
    	addSequential(new ShiftHigh());
		addSequential(new ClawDown());
		addSequential(new VelocityDriveToDistance(-6.75, -26.25));
		addSequential(new GoToHeight(420));
    	addSequential(new Wait(.2));
    	addSequential(new ExtakeLeft());
    	addSequential(new DownToBottom());
		addSequential(new VelocityTurnToAngle(4, 355, 1));
//		addParallel(new IntakeWithStop());
		addParallel(new IntakeCubeAuton(2.4));
		addSequential(new VelocityDriveToDistance(4, 9.25));
		addSequential(new GoToHeight(200));
//		addSequential(new ClosePivot());
		addSequential(new VelocityDriveToDistance(4, 1.25));
		addSequential(new Wait(.2));
		addSequential(new ExtakeFrontAuton());
    }
}
