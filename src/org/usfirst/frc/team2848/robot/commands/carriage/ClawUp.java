package org.usfirst.frc.team2848.robot.commands.carriage;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawUp extends Command {
	boolean unclamped;
    public ClawUp() {
    	requires(Robot.carriage);
    }

    protected void initialize() {
    	Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kReverse);
    	unclamped = true;
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	if (unclamped == true) {
    		return true;
    	}
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
