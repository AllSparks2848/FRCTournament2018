package org.usfirst.frc.team2848.robot.commands.auton;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CenterAutonSelector extends Command {

	String gameData;
	
    public CenterAutonSelector() {
    }

    protected void initialize() {
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	switch (gameData) {
		case "LLL":
			new CenterLeftAuton();
			break;
		case "LRL":
			new CenterLeftAuton();
			break;
		case "RLR":
			new CenterRightAuton();
			break;
		case "RRR":
			new CenterRightAuton();
			break;
    	}
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}