package org.usfirst.frc.team2848.robot;

import org.usfirst.frc.team2848.robot.commands.auton.CenterAutonSelector;
import org.usfirst.frc.team2848.robot.commands.auton.LeftAutonSelector;
import org.usfirst.frc.team2848.robot.commands.auton.RightAutonSelector;
import org.usfirst.frc.team2848.robot.commands.auton.StraightAutonSelector;
import org.usfirst.frc.team2848.robot.subsystems.Carriage;
import org.usfirst.frc.team2848.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2848.robot.subsystems.Elevator;
import org.usfirst.frc.team2848.robot.subsystems.Hanger;
import org.usfirst.frc.team2848.robot.subsystems.Intake;
import org.usfirst.frc.team2848.robot.subsystems.PivotIntake;
//import org.usfirst.frc.team2848.robot.util.ControlLooper;
import org.usfirst.frc.team2848.robot.util.PathPlanning;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveTrain drivetrain = new DriveTrain();
	public static final Intake intake = new Intake();
	public static final Hanger hanger = new Hanger();
	public static final Carriage carriage = new Carriage();
	public static final Elevator elevator = new Elevator();
	public static final PivotIntake pivotIntake = new PivotIntake();
	public static PathPlanning pathplanning = new PathPlanning();
	
//	public ControlLooper TestLoop;

	// public static PowerDistributionPanel pdp = new PowerDistributionPanel();

	public static OI oi;

	public static Command autonomousCommand;
	SendableChooser autoChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		Robot.drivetrain.leftEncoder.reset();
		Robot.drivetrain.rightEncoder.reset();
		Robot.drivetrain.navX.reset();
		Robot.drivetrain.leftEncoder.setReverseDirection(true);

		Robot.drivetrain.leftEncoder.setDistancePerPulse(-0.00116);
		Robot.drivetrain.rightEncoder.setDistancePerPulse(0.00115);

		CameraServer.getInstance().startAutomaticCapture();

		autoChooser = new SendableChooser();
		autoChooser.addDefault("Left", new LeftAutonSelector()); // Picks one of
																	// the left
																	// options
		autoChooser.addObject("Center", new CenterAutonSelector()); // Picks one
																	// of the
																	// center
																	// options
		autoChooser.addObject("Right", new RightAutonSelector()); // Picks one
																	// of the
																	// right
																	// options
		autoChooser.addObject("Straight", new StraightAutonSelector());
		SmartDashboard.putData("Autonomous Mode Selector", autoChooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {		
		if (Robot.drivetrain.arcPIDs != null) {
			System.out.println("LeftSpeed: " + Robot.drivetrain.arcPIDs.leftSpeed + " RightSpeed: " + Robot.drivetrain.arcPIDs.rightSpeed);
			System.out.println("ending thread");
			Robot.drivetrain.arcPIDs.interrupt();
			try {
				Robot.drivetrain.arcPIDs.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Robot.hanger.lockHangerDown();
		Robot.drivetrain.navX.reset();
		Robot.drivetrain.leftEncoder.reset();
		Robot.drivetrain.rightEncoder.reset();
		Robot.drivetrain.disable();
		Robot.drivetrain.drivetrainSetPowerZero();
		Scheduler.getInstance().removeAll();
		
//		if(TestLoop != null){
//			TestLoop.stop();
//		}
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		
		Robot.drivetrain.navX.zeroYaw();
		
		autonomousCommand = (Command) autoChooser.getSelected();

		if (autonomousCommand != null) {
			System.out.println("Starting AutonChooser Command");
			autonomousCommand.start();
		} else {
			System.out.println("autonomousCommand == null");
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		Robot.drivetrain.navX.zeroYaw();
		
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		Robot.hanger.lockHangerDown();
		
//		Robot.drivetrain.arcPIDs = new PIDCalculate(0, 0, 0, 0);
//		Robot.drivetrain.arcPIDs.setTargetXandY(-4, -4);
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		// Arcade Drive
		if (Math.abs(oi.getLeftJoystick()) > .05 || Math.abs(oi.getRightJoystick()) > .05)
			drivetrain.arcadeDrive(oi.getLeftJoystick(), -oi.getRightJoystick());
		
//		Robot.drivetrain.arcPIDs.integratePosition();
		
//		System.out.println("Beambreak: " + intake.haveCube.get());

//		System.out.println("left encoder: " + Robot.drivetrain.leftEncoder.get() + " right encoder: "
//				+ Robot.drivetrain.rightEncoder.get());
//		System.out.println("gyro angle: " + Robot.drivetrain.navX.getFusedHeading());
		// System.out.println("sonar: " + Robot.intake.sonar.getValue());
//		System.out.println("Elev: " + Robot.elevator.elevatorEncoder.get() + " Bottom Lim: "
//				+ Robot.elevator.limitSwitchElevatorBottom.get() + "Top Lim: "
//				+ Robot.elevator.limitSwitchElevatorTop.get());
	}

	/** 
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	// public int getAutoNum() {
	// int autoNum = 0;
	// if(autoChooser.addDefault("Left", new LeftAutonSelector())) {}
	//
	// return autoNum;
	// }
}