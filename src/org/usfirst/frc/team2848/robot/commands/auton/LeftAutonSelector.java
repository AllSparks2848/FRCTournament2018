package org.usfirst.frc.team2848.robot.commands.auton;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LeftAutonSelector extends Command {

	String gameData;
	
    public LeftAutonSelector() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	switch (gameData) {
		case "LLL":
//			new LeftAutonLLL();
			break;
		case "LRL":
//			new LeftAutonLRL();
			break;
		case "RLR":
//			new LeftAutonRLR();
			break;
		case "RRR":
//			new LeftAutonRRR();
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
