package org.usfirst.frc.team2848.robot;

import org.usfirst.frc.team2848.robot.commands.carriage.ClampIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFront;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeLeftElevator;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeRightElevator;
import org.usfirst.frc.team2848.robot.commands.carriage.ReleaseIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.elevator.ManualDown;
import org.usfirst.frc.team2848.robot.commands.elevator.ManualUp;
import org.usfirst.frc.team2848.robot.commands.hanger.DeployHanger;
import org.usfirst.frc.team2848.robot.commands.hanger.PullUp;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCube;
import org.usfirst.frc.team2848.robot.commands.intake.Pivot;
import org.usfirst.frc.team2848.robot.commands.intake.RotateCube;
import org.usfirst.frc.team2848.robot.commands.intake.SpitOutFront;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public double[] xC = { 0, 0, -10, -10 };
	public double[] yC = { 0, 3, 4, 18 };
	public double[] direction = { -1, -1, 1, 1 };

	// Controllers and button boxes
	Joystick xbox1 = new Joystick(RobotMap.p_xbox1);
	 Joystick buttonBoxA = new Joystick(RobotMap.p_buttonBox);

	// xbox1 buttons
	Button a = new JoystickButton(xbox1, 1);
	Button b = new JoystickButton(xbox1, 2);
	Button x = new JoystickButton(xbox1, 3);
	Button y = new JoystickButton(xbox1, 4);
	Button lb = new JoystickButton(xbox1, 5);
	Button rb = new JoystickButton(xbox1, 6);
	Button back = new JoystickButton(xbox1, 7);
	Button start = new JoystickButton(xbox1, 8);
	Button l = new JoystickButton(xbox1, 9);
	Button r = new JoystickButton(xbox1, 10);

	// buttonBoxA buttons
	 JoystickButton bb11 = new JoystickButton(buttonBoxA, 16);
	 JoystickButton bb12 = new JoystickButton(buttonBoxA, 10);
	 JoystickButton bb13 = new JoystickButton(buttonBoxA, 15);
	 JoystickButton bb14 = new JoystickButton(buttonBoxA, 13);
	 JoystickButton bb15 = new JoystickButton(buttonBoxA, 14);
	 JoystickButton bb16 = new JoystickButton(buttonBoxA, 5);
	 JoystickButton bb17 = new JoystickButton(buttonBoxA, 11);
	 JoystickButton bb18 = new JoystickButton(buttonBoxA, 12);
	 JoystickButton bb19 = new JoystickButton(buttonBoxA, 3);
	 JoystickButton bb110 = new JoystickButton(buttonBoxA, 4);
	 JoystickButton bb111 = new JoystickButton(buttonBoxA, 8);
	 JoystickButton bb112 = new JoystickButton(buttonBoxA, 7);
	 JoystickButton bb113 = new JoystickButton(buttonBoxA, 9);

	public OI() {

//		back.whenPressed(new LeftAuton1());
//		back.whenPressed(new AutonSetup());
		start.whenPressed(new ReleaseIntakeClaw());
		back.whenPressed(new ClampIntakeClaw());
//		start.whenPressed(new DriveToDistance(3));
		lb.whenPressed(new ShiftHigh());
		b.whileHeld(new DeployHanger());
		a.whileHeld(new PullUp());
//		b.whenPressed(new GyroTurn(90));
//		a.whenPressed(new VelocityDriveToDistance(6, 3));
//		y.whenPressed(new VelocityDriveToDistance(6, 6));
//		x.whenPressed(new VelocityDriveToDistance(6, 9));
		rb.whenPressed(new ShiftLow());
//		a.whileHeld(new CurrentRotater());
//		y.whenPressed(new AutonReset());
		//b.whenPressed(new VelocityTurnToAngle(3, 90, 1));
		//a.whenPressed(new VelocityTurnToAngle(3, -90, 2));

		// teleop button box commands
		 bb11.whenPressed(new GoToHeight(450)); // scale
		 bb12.whenPressed(new DownToBottom());
		 bb13.whenPressed(new GoToHeight(250)); // switch
		 bb14.whileHeld(new PullUp());
		 bb15.whileHeld(new RotateCube());
//		 bb16.whenPressed(new DeployHanger());
		 bb16.whileHeld(new SpitOutFront());// pulse intake
		 bb18.whileHeld(new ManualUp());
		 bb19.whileHeld(new ManualDown());
		 bb110.whileHeld(new IntakeCube());
		 bb111.whenPressed(new ExtakeLeftElevator()); // left
		 bb112.whenPressed(new ExtakeRightElevator()); // right
		 bb113.whileHeld(new ExtakeFront());
		 bb17.whileHeld(new Pivot());
	}

	public double getLeftJoystick() {
		return xbox1.getRawAxis(1);
	}

	public double getRightJoystick() {
		return xbox1.getRawAxis(4);
	}
}