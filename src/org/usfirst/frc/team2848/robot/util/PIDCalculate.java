package org.usfirst.frc.team2848.robot.util;

import java.util.ArrayList;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class PIDCalculate extends Thread {

	private final Object taskRunningLock_ = new Object();

	double velocityL;
	double velocityR;
	double multiplier;
	double offset;

	double basePower = 0.161;

	Timer t = new Timer();

	public double interrupt = 0;

	double target;
	double type;

	double timestamp_;
	double period = 50000;
	double rampdown = 0.3;

	double lastYaw = 0;
	double lastLeftPos = 0;
	double lastRightPos = 0;

	double deltaX, deltaY;

	double leftPos, rightPos, yaw;
	double leftDistance, rightDistance;
	double hypotenuseDistance;

	double targetAngle;

	public double leftSpeed, rightSpeed;

	double change_x, change_y;
	double current_x, current_y;
	double target_x, target_y;

	double headingAngle;
	
	double previousDisplacement, displacement;  

	ArrayList<Double> list = new ArrayList<Double>();

	MiniPID multiplierPID = new MiniPID(0.4, 0.0, 0.5);
	MiniPID diffPID = new MiniPID(0.05, 0.0, 0);

	public PIDCalculate(double velocityL, double velocityR, double target, double type) {
		this.velocityL = velocityL;
		this.velocityR = velocityR;
		this.offset = 0;

		this.multiplier = 1;

		this.target = target;
		this.type = type;

		Robot.drivetrain.leftEncoder.reset();
		Robot.drivetrain.rightEncoder.reset();
		Robot.drivetrain.leftPIDDrive.reset();
		Robot.drivetrain.rightPIDDrive.reset();
	}

	public void setTargetXandY(double x, double y) {
		this.target_x = x;
		this.target_y = y;
	}

	// integrate the position of the robot using the distance from each encoder.
	public void integratePosition() {
		leftPos = Robot.drivetrain.leftEncoder.getDistance();
		rightPos = Robot.drivetrain.rightEncoder.getDistance();

		leftSpeed = ((leftPos - lastLeftPos) / period) * 1000000000;
		rightSpeed = ((rightPos - lastRightPos) / period) * 1000000000;

		yaw = Robot.drivetrain.navX.getFusedHeading();

		yaw = 90 - yaw;
		if (yaw < 0) {
			yaw += 360;
		}

		leftDistance = (leftPos - lastLeftPos);
		rightDistance = (rightPos - lastRightPos);

		hypotenuseDistance = (leftDistance + rightDistance) / 2;

		// System.out.println("DistanceTraveled: " + hypotenuseDistance);

		change_x = ((Math.cos((lastYaw * Math.PI) / 180) * hypotenuseDistance)
				+ (Math.cos((yaw * Math.PI) / 180) * hypotenuseDistance)) / 2;
		change_y = ((Math.sin((lastYaw * Math.PI) / 180) * hypotenuseDistance)
				+ (Math.sin((yaw * Math.PI) / 180) * hypotenuseDistance)) / 2;

		current_x += change_x;
		current_y += change_y;

		if (change_y == 0 && change_x == 0) {
		}

		if (change_y == 0) {
			if (change_x > 0) {
				headingAngle = 0;
			} else {
				headingAngle = 180;
			}
		} else if (change_x == 0) {
			if (change_y > 0) {
				headingAngle = 90;
			} else {
				headingAngle = 270;
			}
			
			
		}

		if (change_x < 0) {
			headingAngle = (Math.atan(change_y / change_x) / Math.PI * 180) + 180;
		} else if (change_y > 0) {
			headingAngle = Math.atan(change_y / change_x) / Math.PI * 180;
		} else {
			headingAngle = (Math.atan(change_y / change_x) / Math.PI * 180) + 360;
		}

		// System.out.println("LeftSpeed: " + this.leftSpeed + " RightSpeed: " +
		// this.rightSpeed);

		// System.out.println("ChX: " + this.change_x + " ChY: " + this.change_y
		// + " Heading: " + this.headingAngle);

		lastYaw = yaw;
		lastLeftPos = leftPos;
		lastRightPos = rightPos;

		this.deltaX = this.target_x - this.current_x;
		this.deltaY = this.target_y - this.current_y;

		if (this.deltaY == 0) {
			if (this.deltaX > 0) {
				this.targetAngle = 0;
			} else {
				this.targetAngle = 180;
			}
		} else if (this.deltaX == 0) {
			if (this.deltaY > 0) {
				this.targetAngle = 90;
			} else {
				this.targetAngle = 270;
			}
		}

		if (this.deltaX < 0) {
			this.targetAngle = (Math.atan(this.deltaY / this.deltaX) / Math.PI * 180) + 180;
		} else if (this.deltaY > 0) {
			this.targetAngle = (Math.atan(this.deltaY / this.deltaX) / Math.PI * 180);
		} else {
			this.targetAngle = (Math.atan(this.deltaY / this.deltaX) / Math.PI * 180) + 360;
		}

		// System.out.println("Diff: " +
		// getDifferenceInAngleDegrees(this.headingAngle, this.targetAngle));

	}

	private double getDifferenceInAngleDegrees(double from, double to) {
		return boundAngleNeg180to180Degrees(from - to);
	}

	private double boundAngleNeg180to180Degrees(double angle) {
		while (angle >= 180.0) {
			angle -= 360.0;
		}
		while (angle < -180.0) {
			angle += 360.0;
		}
		return angle;
	}

	public void run() {

		Robot.drivetrain.leftEncoder.reset();
		Robot.drivetrain.rightEncoder.reset();
		

		if (this.type == 0) {
			MiniPID speedDiffPID = new MiniPID(0.2, 0.00001, 0);
			double constantPower = this.velocityL * 0.063;
			
			if (constantPower < 0) {
				constantPower -= 0.15;
			} else {
				constantPower += 0.15;
			}
			
			double powerDiff = 0;
			
			double leftPower, rightPower;
			
			multiplierPID.reset();
			multiplierPID.setOutputLimits(-1.0, 1.0);
			
			multiplierPID.setP(0.3); // .4
			multiplierPID.setI(0.0);
			multiplierPID.setD(0.0); // .5
			
			AveragingFilter errors = new AveragingFilter(8000);
			errors.initialize(10);
			
			t.reset();
			t.start();
			
			double timerTarget = 0.1;

			while (!interrupted()) {
				synchronized (taskRunningLock_) {
					integratePosition();
					timestamp_ = System.nanoTime();
					
					
					//New speed difference method
					multiplier = multiplierPID.getOutput(Math.abs(Robot.drivetrain.leftEncoder.getDistance()) - Math.abs(this.target), 0);
					
					if(t.get() > timerTarget){
						displacement = Math.abs(Robot.drivetrain.leftEncoder.getDistance() - this.target);
						
						if(Math.abs(displacement - previousDisplacement) < 0.03) {
//							 System.out.println("displacement: " + Math.abs(displacement - previousDisplacement));
							 this.interrupt = 1;
							 System.out.println("Interrupting");
							 this.interrupt();
							 return;
						 }
						
						previousDisplacement = displacement;
						
						timerTarget += 0.1;
					}

					powerDiff = speedDiffPID.getOutput(Robot.drivetrain.leftEncoder.getRate() - Robot.drivetrain.rightEncoder.getRate(), 0);

					Robot.drivetrain.left.set((constantPower + powerDiff) * multiplier);
					Robot.drivetrain.right.set(-(constantPower - powerDiff) * multiplier);

				}
				while (System.nanoTime() < timestamp_ + period) {
				}

			}
		} else if (this.type == 1) {
			multiplierPID.reset();
			multiplierPID.setOutputLimits(-1.0, 1.0);
			multiplierPID.setP(0.04);
			multiplierPID.setI(0.0);
			multiplierPID.setD(0.0);

			AveragingFilter errors = new AveragingFilter(500);
			errors.initialize(100);
			
			t.reset();
			t.start();
			
			double timerTarget = 0.05; //.1

			while (!interrupted()) {
				synchronized (taskRunningLock_) {
					integratePosition();

					timestamp_ = System.nanoTime();
					multiplier = multiplierPID.getOutput(getDifferenceInAngleDegrees(Robot.drivetrain.navX.getFusedHeading(), this.target), 0);

					// double leftPower =
					// Robot.drivetrain.leftPIDDrive.getOutput(this.leftSpeed,
					// this.velocityL * multiplier);
					// double rightPower =
					// Robot.drivetrain.rightPIDDrive.getOutput(this.rightSpeed,
					// -this.velocityR * multiplier);

					Robot.drivetrain.left.set(multiplier);
					Robot.drivetrain.right.set(multiplier);
					

					if(t.get() > timerTarget){
						displacement = getDifferenceInAngleDegrees(Robot.drivetrain.navX.getFusedHeading(), this.target);
						
						if(Math.abs(displacement - previousDisplacement) < 0.2) {
//							 System.out.println("displacement: " + Math.abs(displacement - previousDisplacement));
							 this.interrupt = 1;
							 System.out.println("Interrupting");
							 this.interrupt();
							 return;
						 }
						
						previousDisplacement = displacement;
						
						timerTarget += 0.05; //.1
					}
					
				}
				while (System.nanoTime() < timestamp_ + period) {
				}

			}
		}

		return;
	}
}
