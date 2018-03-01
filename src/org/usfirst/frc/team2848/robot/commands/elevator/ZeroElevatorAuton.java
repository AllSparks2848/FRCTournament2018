package org.usfirst.frc.team2848.robot.commands.elevator;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroElevatorAuton extends Command {

    public ZeroElevatorAuton() {
        requires(Robot.elevator);
    }

    protected void initialize() {
    	Robot.elevator.elevatorController.reset();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Math.abs(Robot.elevator.elevatorEncoder.get()) < 5;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
