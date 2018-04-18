package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClawDown;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToPoint;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;

import AutonCommandGroups.WaitThenElevator;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterLeftAutonFast extends CommandGroup {

    public CenterLeftAutonFast() {
    	double[] xp = {0, -2, -2.75};
    	double[] yp = {0.25, 5, 8.5};
    	double[] speeds = {0.75, 0.75, 0.75};
    	Command[] actions = {new DoNothing(), new DoNothing(), new DoNothing()};
    	
    	
    	addSequential(new ShiftHigh());
		addSequential(new ClawDown());
		addParallel(new WaitThenElevator(1, 1000, 0.7));
		addSequential(new DriveToPoint(xp, yp, speeds, actions, 3));
        
    }
}
