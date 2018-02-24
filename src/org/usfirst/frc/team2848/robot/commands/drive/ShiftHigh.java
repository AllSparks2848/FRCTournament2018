package org.usfirst.frc.team2848.robot.commands.drive;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftHigh extends Command {

    public ShiftHigh() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {
    	Robot.drivetrain.shiftHigh(); //Shift to high gear
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}