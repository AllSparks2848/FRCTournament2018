package org.usfirst.frc.team2848.robot.commands.auton;

import org.usfirst.frc.team2848.robot.commands.carriage.ClampIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFrontAuton;
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
public class LeftScaleAuton1 extends CommandGroup {

    public LeftScaleAuton1() {
    	addSequential(new ShiftHigh());
		addSequential(new ClampIntakeClaw());
		addSequential(new VelocityDriveToDistance(6.75, 27));
		
		//Turn and spit first cube
		addParallel(new WaitThenElevator(0));
		addSequential(new VelocityTurnToAngle(4, 90, 1));
		addSequential(new Wait(.2));
		addParallel(new DownToBottom());
		
		//Turn and get second cube
		addSequential(new VelocityTurnToAngle(4, 150, 1));
		addParallel(new IntakeCubeAuton(6));
		addSequential(new VelocityDriveToDistance(6.75, 9));
    	addSequential(new ExtakeFrontAuton());
    	addSequential(new VelocityDriveToDistance(-6.75, 9));
    	
    	//Turn and spit seconod cube
    	addParallel(new WaitThenElevator(0));
		addSequential(new VelocityTurnToAngle(4, 90, 1));
		addSequential(new Wait(.2));
		addParallel(new DownToBottom());
		
    }
}
