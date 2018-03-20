package org.usfirst.frc.team2848.robot.subsystems;

import org.usfirst.frc.team2848.robot.Robot;
import org.usfirst.frc.team2848.robot.RobotMap;
import org.usfirst.frc.team2848.robot.util.MiniPID;
import org.usfirst.frc.team2848.robot.util.PIDCalculate;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends PIDSubsystem {

	// Motors for DriveTrain
	public Spark leftDrive1 = new Spark(RobotMap.p_leftDrive1);
	public Spark leftDrive2 = new Spark(RobotMap.p_leftDrive2);
	public Spark leftDrive3 = new Spark(RobotMap.p_leftDrive3);
	public Spark rightDrive1 = new Spark(RobotMap.p_rightDrive1);
	public Spark rightDrive2 = new Spark(RobotMap.p_rightDrive2);
	public Spark rightDrive3 = new Spark(RobotMap.p_rightDrive3);

	// Left and Right sides for DriveTrain
	public SpeedControllerGroup left = new SpeedControllerGroup(leftDrive1, leftDrive2, leftDrive3);
	public SpeedControllerGroup right = new SpeedControllerGroup(rightDrive1, rightDrive2, rightDrive3);

	// The DriveTrain drive
	public DifferentialDrive drive1 = new DifferentialDrive(left, right);

	// Encoders for the motors
	public Encoder leftEncoder = new Encoder(RobotMap.p_leftEncoderA, RobotMap.p_leftEncoderB, true, EncodingType.k4X);
	public Encoder rightEncoder = new Encoder(RobotMap.p_rightEncoderA, RobotMap.p_rightEncoderB, true,
			EncodingType.k4X);

	// Pneumatic for the gearbox
	DoubleSolenoid driveShifter = new DoubleSolenoid(RobotMap.p_driveShifter1, RobotMap.p_driveShifter2);

	// The NavX gyro
	public AHRS navX = new AHRS(SPI.Port.kMXP);

	// DriveTrain PIDs
	public static double driveKp = 0.45;
	public static double driveKi = 0.0;
	public static double driveKd = 0.055;

	// Gyro PIDs
	public static double gyroKp = 0.6;
	public static double gyroKi = 0;
	public static double gyroKd = 0.1;
	
//	private static double kP = 0.06;
//	private static double kI = 0.0001;
//	private static double kD = 0.02;
	
	private static double kP = 0.05;
	private static double kI = 0.0001;
	private static double kD = 0.03;
	
	public PIDCalculate arcPIDs;
	
	public MiniPID leftPIDDrive = new MiniPID(kP, kI, kD);
	public MiniPID rightPIDDrive = new MiniPID(kP, kI, kD);

	// DriveTrain and Gyro PIDControllers
	public PIDController driveController = new PIDController(driveKp, driveKi, driveKd, leftEncoder, leftDrive1);
	public PIDController gyroController = new PIDController(gyroKp, gyroKi, gyroKd, navX, rightDrive1);

	// Initialize your subsystem here
	public DriveTrain() {
		super("DriveTrain", driveKp, driveKi, driveKd);
	}
	
	public void initDefaultCommand() {
	}

	// Shifts to high gear
	public void shiftHigh() {
		driveShifter.set(DoubleSolenoid.Value.kForward); // fwd
	}

	// Shifts to low gear
	public void shiftLow() {
		driveShifter.set(DoubleSolenoid.Value.kReverse); // rev
	}

	// General arcade drive
	public void arcadeDrive(double left, double right) {
		drive1.arcadeDrive(-left, -right);
	}

	// Sets all motors to 0
	public void drivetrainSetPowerZero() {
		left.set(0);
		right.set(0);
	}

	@Override
	protected double returnPIDInput() {
		return Robot.drivetrain.leftEncoder.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		left.set(output);
		right.set(-output);
	}
}