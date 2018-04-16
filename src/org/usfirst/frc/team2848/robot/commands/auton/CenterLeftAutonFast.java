package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClawDown;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
import org.usfirst.frc.team2848.robot.commands.drive.DriveToPoint;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;

import AutonCommandGroups.WaitThenElevator;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterLeftAutonFast extends CommandGroup {

    public CenterLeftAutonFast() {
    	double[] xp = {0, -8.0};
    	double[] yp = {1, 8.0};
    	double[] speeds = {0.7, 0.75};
    	Command[] actions = {new DoNothing(), new DoNothing()};
    	
    	
    	addSequential(new ShiftHigh());
		addParallel(new ClawDown());
		addParallel(new WaitThenElevator(1.5));
		addSequential(new DriveToPoint(xp, yp, speeds, actions, 2));
        
    }
}
