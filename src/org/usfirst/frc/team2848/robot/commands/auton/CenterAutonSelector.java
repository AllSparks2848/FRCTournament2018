 package org.usfirst.frc.team2848.robot.commands.auton;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CenterAutonSelector extends Command {
	Command runningAuton;

	String gameData;
	
	Timer t = new Timer();

	public CenterAutonSelector() {

	}

	protected void initialize() {
		
		System.out.println("Inside CenterAuton");

		t.start();
		while(DriverStation.getInstance().getGameSpecificMessage().length() < 2){
			System.out.println("Waiting...");
			if (t.get() > 1.5) {
				break;
			}
		}
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();

		System.out.println("Game Data: " + gameData);

		switch (gameData) {
		case "LLL":
			runningAuton = new CenterLeftAutonFast();
			System.out.println("RUNNING CENTER LEFT AUTON");
			break;
		case "LRL":
			runningAuton = new CenterLeftAutonFast();
			System.out.println("RUNNING CENTER LEFT AUTON");
			break;
		case "RLR":
			runningAuton = new CenterRightAutonFast();
			System.out.println("RUNNING CENTER RIGHT AUTON");
			break;
		case "RRR":
			runningAuton = new CenterRightAutonFast();
			System.out.println("RUNNING CENTER RIGHT AUTON");
			break;
		default:
			runningAuton = new ForwardAuton();
			System.out.println("RUNNING DEFAULT FORWARD AUTON");
			break;
		}
		runningAuton.start();
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