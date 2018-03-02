package org.usfirst.frc.team2848.robot.commands.auton;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LeftAutonSelector extends Command {

	String gameData;
	
    public LeftAutonSelector() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	switch (gameData) {
		case "LLL":
			new LeftSwitchAuton();
			break;
		case "LRL":
			new LeftSwitchAuton();
			break;
		case "RLR":
			new LeftScaleAuton();
			break;
		case "RRR":
			new ForwardAuton();
			break;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}