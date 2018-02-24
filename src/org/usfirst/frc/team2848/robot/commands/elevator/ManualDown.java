package org.usfirst.frc.team2848.robot.commands.elevator;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualDown extends Command {

    public ManualDown() {
    	requires(Robot.elevator);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(Robot.elevator.limitSwitchElevatorBottom.get())
    	Robot.elevator.elevatorMotor.set(0.25); //makes elevator go down when held
    	else {
    		Robot.elevator.elevatorMotor.set(0);
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.elevator.elevatorMotor.set(0.0); //makes elevator stop
    }

    protected void interrupted() {
    	end();
    }
}
