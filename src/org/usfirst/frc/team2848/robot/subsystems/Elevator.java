package org.usfirst.frc.team2848.robot.subsystems;

import org.usfirst.frc.team2848.robot.RobotMap;
import org.usfirst.frc.team2848.robot.util.MiniPID;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

	public Spark elevatorMotor = new Spark(RobotMap.p_elevatorMotor);

	public DigitalInput limitSwitchElevatorBottom = new DigitalInput(6);// false if carriage at bottom
	public DigitalInput limitSwitchElevatorTop = new DigitalInput(7); // false if at top

	public Encoder elevatorEncoder = new Encoder(RobotMap.p_elevatorEncoderA, RobotMap.p_elevatorEncoderB, true,
			EncodingType.k4X);// measures carriage height

	double kP = 0.04;
	double kI = 0.00;
	double kD = 0.16;

	public MiniPID elevatorController = new MiniPID(kP, kI, kD);
	
	public double scalePos = 400;
	public double switchPos = 250;
	public double sideSpitMinPos = 100;

	public Elevator() {
		elevatorController.setOutputLimits(-.4, 1);
		elevatorEncoder.reset(); 
		elevatorEncoder.setReverseDirection(false);
		// pid.set
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void goToPosition(double height) {
		double pidOutput = elevatorController.getOutput(elevatorEncoder.get(), height);
		elevatorMotor.set(-pidOutput);
		System.out.println("PID Output: " + pidOutput);
	}

	public void elevatorUp() {
		elevatorMotor.set(0.8);
	}

	public void elevatorDown() {
		elevatorMotor.set(-0.4);
	}

	public void elevatorSetPowerZero() {
		elevatorMotor.set(0);
	}
}
