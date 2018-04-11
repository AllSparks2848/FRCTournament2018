package org.usfirst.frc.team2848.robot.commands.carriage;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawDown extends Command {
	boolean clamped;
    public ClawDown() {
    	requires(Robot.carriage);
    }

    protected void initialize() {
    	Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kForward);
    	clamped = true;
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	if (clamped == true) {
    		return true;
    	}
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
