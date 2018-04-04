package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ReleaseIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottomAuton;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeightAuton;
import org.usfirst.frc.team2848.robot.commands.intake.PivotIn;
import org.usfirst.frc.team2848.robot.commands.intake.PivotOut;
import org.usfirst.frc.team2848.robot.util.Wait;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonReset extends CommandGroup {
	Timer timer = new Timer();

	public AutonReset() {
		addSequential(new PivotOut());
		addSequential(new Wait(1));
		addSequential(new DownToBottomAuton());
		addSequential(new GoToHeightAuton(570));
		addSequential(new Wait(1));
		addSequential(new PivotIn());
		addSequential(new ReleaseIntakeClaw());
	}
}
