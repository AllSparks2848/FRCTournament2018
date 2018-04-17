package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClawDown;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToPoint;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;

import AutonCommandGroups.WaitThenElevator;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterRightAutonFast extends CommandGroup {

    public CenterRightAutonFast() {
    	double[] xp = {2.5};
    	double[] yp = {8};
    	double[] speeds = {0.7};
    	Command[] actions = {new DoNothing()};
    	
    	
    	addSequential(new ShiftHigh());
		addParallel(new ClawDown());
		addParallel(new WaitThenElevator(1));
		addSequential(new DriveToPoint(xp, yp, speeds, actions, 1));
    }
}
