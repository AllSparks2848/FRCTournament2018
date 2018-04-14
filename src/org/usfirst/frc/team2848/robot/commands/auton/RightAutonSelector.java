package org.usfirst.frc.team2848.robot.commands.auton;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RightAutonSelector extends Command {
	Command runningAuton;
	String gameData;
	Timer t = new Timer();

	public RightAutonSelector() {

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		t.start();
		while (DriverStation.getInstance().getGameSpecificMessage().length() < 2) {
			System.out.println("Waiting...");
			if (t.get() > 1.5) {
				break;
			}
		}

		gameData = DriverStation.getInstance().getGameSpecificMessage();

		System.out.println("Game Data: " + gameData);

		switch (gameData) {
		case "LLL":
			runningAuton = new ForwardAuton();
			break;
		case "LRL":
			runningAuton = new RightScaleCross();
			break;
		case "RLR":
			runningAuton = new ForwardAuton();
			break;
		case "RRR":
			runningAuton = new RightScaleCross();
			break;
		default:
			runningAuton = new ForwardAuton();
			System.out.println("RUNNING DEFAULT FORWARD AUTON");
			break;
		}
		runningAuton.start();
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