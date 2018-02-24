package org.usfirst.frc.team2848.robot.commands.carriage;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OmniPlateOff extends Command {

    public OmniPlateOff() {
        requires(Robot.carriage);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.carriage.omniPlateMotor.set(0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
