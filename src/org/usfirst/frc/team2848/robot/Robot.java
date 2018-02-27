package org.usfirst.frc.team2848.robot;

import org.usfirst.frc.team2848.robot.commands.auton.CenterAutonSelector;
import org.usfirst.frc.team2848.robot.commands.auton.LeftAutonSelector;
import org.usfirst.frc.team2848.robot.commands.auton.RightAutonSelector;
import org.usfirst.frc.team2848.robot.commands.led.BlueAlliance;
import org.usfirst.frc.team2848.robot.commands.led.RedAlliance;
import org.usfirst.frc.team2848.robot.subsystems.Carriage;
import org.usfirst.frc.team2848.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2848.robot.subsystems.Elevator;
import org.usfirst.frc.team2848.robot.subsystems.Hanger;
import org.usfirst.frc.team2848.robot.subsystems.Intake;
import org.usfirst.frc.team2848.robot.subsystems.LED;
import org.usfirst.frc.team2848.robot.subsystems.PivotIntake;
import org.usfirst.frc.team2848.robot.util.PathPlanning;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
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
	public static final LED led = new LED();
	public static final PivotIntake pivotIntake = new PivotIntake();
	public static PathPlanning pathplanning = new PathPlanning();

	// public static PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static OI oi;

	Command autonomousCommand;
	SendableChooser allianceChooser;
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

		Robot.drivetrain.leftEncoder.setDistancePerPulse(0.00114);
		Robot.drivetrain.rightEncoder.setDistancePerPulse(-0.00114);
		
		allianceChooser = new SendableChooser();
		
		allianceChooser.addDefault("Red Alliance", new RedAlliance());
		allianceChooser.addObject("Blue Alliance", new BlueAlliance());

		autoChooser = new SendableChooser();
		
		autoChooser.addDefault("Left", new LeftAutonSelector()); //Picks one of the left options
		autoChooser.addObject("Center", new CenterAutonSelector()); //Picks one of the center options
		autoChooser.addObject("Right", new RightAutonSelector()); //Picks one of the right options
		SmartDashboard.putData("Autonomous Mode Selector", autoChooser);
		
		CameraServer.getInstance().startAutomaticCapture();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		if (Robot.drivetrain.arcPIDs != null) {
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
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		Timer.delay(SmartDashboard.getNumber("Auton Init Delay", 0));
		
		autonomousCommand = (Command) autoChooser.getSelected();

		if (autonomousCommand != null)
			autonomousCommand.start();
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
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		Robot.hanger.lockHangerDown();

		drivetrain.navX.zeroYaw();
		drivetrain.navX.reset();
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
//
//		System.out.println("left encoder: " + Robot.drivetrain.leftEncoder.getDistance() + " right encoder: "
//				+ Robot.drivetrain.rightEncoder.getDistance());
//		System.out.println("gyro angle: " + Robot.drivetrain.navX.getAngle());
		
		System.out.println(Robot.elevator.elevatorEncoder.get());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}