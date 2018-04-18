package org.usfirst.frc.team2848.robot.commands.auton;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LeftAutonSelector extends Command {
	Command runningAuton;
	String gameData;
	Timer t = new Timer();

	public LeftAutonSelector() {
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
		System.out.println("Inside LeftAuton");

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
//			runningAuton = new LeftScaleAuton();
//			runningAuton = new LeftScaleAuton();
			runningAuton = new ForwardAuton();
			System.out.println("RUNNING LEFT SCALE AUTON");
			break;
		case "LRL":
//			runningAuton = new LeftSwitchAuton();
//			runningAuton = new ForwardAndCross();
//			runningAuton = new LeftSwitchRightScaleAuton();
			runningAuton = new ForwardAuton();
			System.out.println("RUNNING LEFT SWITCH AUTON AUTON");
			break;
		case "RLR":
//			runningAuton = new LeftScaleAuton();
//			runningAuton = new LeftScaleAuton();
			runningAuton = new ForwardAuton();
			System.out.println("RUNNING LEFT SCALE AUTON");
			break;
		case "RRR":
			runningAuton = new ForwardAuton();
			System.out.println("RUNNING FORWARD AUTON");
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