package org.usfirst.frc.team2848.robot;

import org.usfirst.frc.team2848.robot.commands.auton.AutonReset;
import org.usfirst.frc.team2848.robot.commands.auton.CenterLeftThreeCubeAutonULTRAFAST;
import org.usfirst.frc.team2848.robot.commands.auton.LeftScaleAuton1;
import org.usfirst.frc.team2848.robot.commands.auton.LeftScaleRightSwitchAuton;
import org.usfirst.frc.team2848.robot.commands.carriage.ClampIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFront;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeLeft;
import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeRight;
import org.usfirst.frc.team2848.robot.commands.carriage.ReleaseIntakeClaw;
import org.usfirst.frc.team2848.robot.commands.carriage.SecureCube;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team2848.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityDriveToDistance;
import org.usfirst.frc.team2848.robot.commands.drive.VelocityTurnToAngle;
import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeightPortal;
import org.usfirst.frc.team2848.robot.commands.elevator.ManualDown;
import org.usfirst.frc.team2848.robot.commands.elevator.ManualUp;
import org.usfirst.frc.team2848.robot.commands.hanger.DeployHanger;
import org.usfirst.frc.team2848.robot.commands.hanger.PullUp;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeCube;
import org.usfirst.frc.team2848.robot.commands.intake.IntakeWithStop;
import org.usfirst.frc.team2848.robot.commands.intake.Pivot;
import org.usfirst.frc.team2848.robot.commands.intake.PivotIn;
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

	// Controllers and button boxes
	Joystick xbox1 = new Joystick(RobotMap.p_xbox1);
	Joystick newButtonBoxA = new Joystick(RobotMap.p_newButtonBoxA);
	Joystick newButtonBoxB = new Joystick(RobotMap.p_newButtonBoxB);
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

	// newButtonBoxA

	JoystickButton nbba1 = new JoystickButton(newButtonBoxA, 1);
	JoystickButton nbba2 = new JoystickButton(newButtonBoxA, 2);
	JoystickButton nbba3 = new JoystickButton(newButtonBoxA, 3);
	JoystickButton nbba4 = new JoystickButton(newButtonBoxA, 4);
	JoystickButton nbba5 = new JoystickButton(newButtonBoxA, 5);
	JoystickButton nbba6 = new JoystickButton(newButtonBoxA, 12);
	JoystickButton nbba7 = new JoystickButton(newButtonBoxA, 11);
	JoystickButton nbba8 = new JoystickButton(newButtonBoxA, 6);
	JoystickButton nbba9 = new JoystickButton(newButtonBoxA, 7);
	JoystickButton nbba10 = new JoystickButton(newButtonBoxA, 8);
	JoystickButton nbba11 = new JoystickButton(newButtonBoxA, 9);
	JoystickButton nbba12 = new JoystickButton(newButtonBoxA, 10);

	// newButtonBoxB
	JoystickButton nbbb1 = new JoystickButton(newButtonBoxB, 1);
	// JoystickButton nbbb2 = new JoystickButton(newButtonBoxB, 2);
	JoystickButton nbbb3 = new JoystickButton(newButtonBoxB, 3);
	JoystickButton nbbb4 = new JoystickButton(newButtonBoxB, 11);
	JoystickButton nbbb5 = new JoystickButton(newButtonBoxB, 10);
	JoystickButton nbbb6 = new JoystickButton(newButtonBoxB, 9);
	JoystickButton nbbb7 = new JoystickButton(newButtonBoxB, 8);
	JoystickButton nbbb8 = new JoystickButton(newButtonBoxB, 7);
	JoystickButton nbbb9 = new JoystickButton(newButtonBoxB, 6);
	JoystickButton nbbb10 = new JoystickButton(newButtonBoxB, 5);
	JoystickButton nbbb11 = new JoystickButton(newButtonBoxB, 4);
	JoystickButton nbbb12 = new JoystickButton(newButtonBoxB, 3);
	JoystickButton nbbb13 = new JoystickButton(newButtonBoxB, 2);
	JoystickButton nbbb14 = new JoystickButton(newButtonBoxB, 1);

	public OI() {

//		 back.whenPressed(new LeftScaleCubeAuton());
//		 back.whenPressed(new LeftScaleSwitchAuton());
//		 back.whenPressed(new ForwardAndCross());
//		back.whenPressed(new LeftSwitchRightScaleAuton());
		back.whenPressed(new LeftScaleRightSwitchAuton());
//		back.whenPressed(new RightScaleRightSwitchAuton());
		// back.whenPressed(new GyroTurn(-60));
		// back.whenPressed(new AutonSetup());
		// back.whenPressed(new ForwardAndCross());
		start.whenPressed(new AutonReset());
		lb.whenPressed(new ShiftHigh());
		// b.whenPressed(new DeployHanger());
		rb.whenPressed(new ShiftLow());
		// a.whenPressed(new ExtakeFrontAuton());
		// a.whenPressed(new LeftScaleAuton());
		// b.whenPressed(new RightScaleSetupAuton());
		// a.whenPressed(new CenterLeftAutonFast());
		// b.whenPressed(new CenterRightAutonFast());
//		x.whenPressed(new VelocityTurnToAngle(4, 315, 1));
//		x.whenPressed(new CenterLeftAutonFast());
//		y.whenPressed(new CenterRightAutonFast());
//		y.whenPressed(new CenterRightTwoCubeAuton());
		y.whenPressed(new VelocityDriveToDistance(10, 9));
		b.whenPressed(new CenterLeftThreeCubeAutonULTRAFAST());
		x.whenPressed(new VelocityTurnToAngle(8, 330, 1));
//		a.whenPressed(new GoToHeight(800));
//		b.whenPressed(new GoToHeight(1400));
//		x.whileHeld(new GoToHeight(2000));
//		y.whenPressed(new GoToHeight(2500));
		a.whenPressed(new LeftScaleAuton1());
		back.whileHeld(new IntakeCube());
//		a.whenPressed(new NewLeftSideScale());

		// teleop button box commands
		bb11.whenPressed(new GoToHeight(100)); // scale
		bb12.whenPressed(new DownToBottom());
		bb13.whenPressed(new GoToHeight(50)); // switch
		// bb14.whileHeld(new PullUp());
		bb15.whileHeld(new DeployHanger());
		// bb16.whenPressed(new DeployHanger());
		bb16.whileHeld(new SpitOutFront());// pulse intake
		bb18.whileHeld(new ManualUp());
		bb19.whileHeld(new ManualDown());
		bb110.whileHeld(new IntakeCube());
		bb111.whileHeld(new ExtakeLeft()); // left
		bb112.whileHeld(new ExtakeRight()); // right
		bb113.whileHeld(new ExtakeFront());
		bb17.whileHeld(new Pivot());

		// teleop new Button Boc
		nbba1.whenPressed(new GoToHeight(2900)); // scale high 450
		nbba2.whenPressed(new GoToHeight(2000)); // scale low 400
		nbba3.whenPressed(new GoToHeight(1400)); // switch high 250
		nbba4.whenPressed(new GoToHeight(800)); // switch low 150
		nbba5.whenPressed(new DownToBottom()); // down and zero
		// nbba6.whileHeld(new ); //not wanted yet
		nbba7.whenPressed(new GoToHeightPortal(93)); // portal (20.5in)
		nbba8.whileHeld(new ManualUp()); // manual up
		nbba9.whileHeld(new ManualDown()); // manual down
		nbba10.whileHeld(new PullUp()); // pull up
		nbba11.whenPressed(new DeployHanger()); // deploy hanger
		// nbba12.whenPressed(new ); // buddy

		// nbbb1.whenPressed(new ); //warn
		// nbbb2.whenPressed(new AutonReset()); //not wanted
		nbbb3.whenPressed(new IntakeWithStop()); // not wanted
		nbbb4.whileHeld(new RotateCube()); // rotate
		nbbb5.whileHeld(new IntakeWithStop()); // SwitchSpitIntake
		nbbb6.whenPressed(new Pivot()); // Pivot
		nbbb7.whenPressed(new PivotIn()); // PivotIn
		nbbb8.whenPressed(new ReleaseIntakeClaw());
		nbbb9.whenPressed(new ClampIntakeClaw());
		nbbb10.whileHeld(new SecureCube());
		nbbb11.whileHeld(new SpitOutFront());
		nbbb12.whileHeld(new ExtakeLeft());
		nbbb13.whileHeld(new ExtakeRight());
		nbbb14.whileHeld(new IntakeCube()); // ian changed from when pressed
											// autonintake cube becuase no
											// ultrasocin

	}

	public double getLeftJoystick() {
		return xbox1.getRawAxis(1);
	}

	public double getRightJoystick() {
		return xbox1.getRawAxis(4);
	}
}