package org.usfirst.frc.team2848.robot.commands.auton;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StraightAutonSelector extends Command {
	Command runningAuton;

	String gameData;

	Timer t = new Timer();

	public StraightAutonSelector() {

	}

	protected void initialize() {

		System.out.println("Inside CenterAuton");

		t.start();
		while (DriverStation.getInstance().getGameSpecificMessage().length() < 2) {
			System.out.println("Waiting...");
			if (t.get() > 1.5) {
				break;
			}
		}

		gameData = DriverStation.getInstance().getGameSpecificMessage();

		System.out.println("Game Data: " + gameData);
		runningAuton = new ForwardAuton();
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
