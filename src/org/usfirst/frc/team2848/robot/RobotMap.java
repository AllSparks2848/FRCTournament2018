package org.usfirst.frc.team2848.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Controllers
	public static int p_xbox1 = 0;
	public static int p_buttonBox = 3;
	public static int p_newButtonBoxA = 1;
	public static int p_newButtonBoxB = 2;

	// DriveTrain Motors
	public static int p_leftDrive1 = 5;
	public static int p_leftDrive2 = 6;
	public static int p_leftDrive3 = 7;
	public static int p_rightDrive1 = 0;
	public static int p_rightDrive2 = 1;
	public static int p_rightDrive3 = 2;

	// Intake motors

	public static int p_leftIntake = 8;
	public static int p_rightIntake = 3;

	// Hanger motor
	public static int p_hangerMotor1 = 4;

	// Elevator motors
	public static int p_elevatorMotor = 12;

	// Elevator DigitalInputs
	public static int p_elevatorLimitBottom = 6;
	public static int p_elevatorLimitTop = 7;

	// Encoders

	// DriveTrain Encoders
	public static int p_leftEncoderA = 0;
	public static int p_leftEncoderB = 1;
	public static int p_rightEncoderA = 2;
	public static int p_rightEncoderB = 3;

	// Elevator Encoders
	public static int p_elevatorEncoderA = 4;
	public static int p_elevatorEncoderB = 5;

	// Solenoids

	// Drivetrain Solenoids
	public static int p_driveShifter1 = 0;
	public static int p_driveShifter2 = 1;

	// Hanger Solenoids
	public static int p_unfoldPneumatic = 2;

	// intake solenoids
	public static int p_intakeClawA = 4;
	public static int p_intakeClawB = 5;
	public static int p_intakePivotA = 6;
	public static int p_intakePivotB = 7;

	// pivotintake Solenoids

	// carriage motors
	public static int p_omniPlateMotor = 11;
	public static int p_intakeClawMotor = 10;
	
//	analog input
	public static int p_sonar = 3;
}