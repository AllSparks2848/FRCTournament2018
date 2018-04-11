package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClawDown;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCubeAuton;
import org.usfirst.frc.team2848.robot.util.Wait;

import AutonCommandGroups.WaitThenElevatorSCALE;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleAuton1 extends CommandGroup {

    public LeftScaleAuton1() {
    	addSequential(new ShiftHigh());
		addSequential(new ClawDown());
		addSequential(new VelocityDriveToDistance(6.75, 27));
		
		//Turn and spit first cube
		addParallel(new WaitThenElevatorSCALE(0));
		addSequential(new VelocityTurnToAngle(4, 90, 1));
		addSequential(new Wait(.4));
		addSequential(new ExtakeFrontAuton());
		addParallel(new DownToBottom());
		
		//Turn and get second cube
		addSequential(new VelocityTurnToAngle(4, 152, 1));
		addParallel(new IntakeCubeAuton(4));
		addSequential(new VelocityDriveToDistance(6.75, 11));
    	addSequential(new VelocityDriveToDistance(-6.75, 10.5));
    	
    	//Turn and spit seconod cube
    	addParallel(new WaitThenElevatorSCALE(0));
		addSequential(new VelocityTurnToAngle(4, 100, 1));
		addSequential(new Wait(.8));
		addSequential(new ExtakeFrontAuton());
		addParallel(new DownToBottom());
		
    }
}
