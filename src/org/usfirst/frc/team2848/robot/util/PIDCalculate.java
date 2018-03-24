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

		yaw = Robot.drivetrain.navX.getYaw();

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
			double constantPower = this.velocityL * 0.063;
			
			if (constantPower < 0) {
				constantPower -= 0.15;
			} else {
				constantPower += 0.15;
			}
			
			double powerDiff = 0;
			
			multiplierPID.reset();
			multiplierPID.setOutputLimits(-1.0, 1.0);
//			multiplierPID.setP(0.7); // .4
//			multiplierPID.setI(0.0);
//			multiplierPID.setD(0.7); // .5
			
			multiplierPID.setP(0.1); // .4
			multiplierPID.setI(0.0);
			multiplierPID.setD(0.0); // .5
			
			AveragingFilter errors = new AveragingFilter(50);
			errors.initialize(10);

			while (!interrupted()) {
				synchronized (taskRunningLock_) {
					integratePosition();
					timestamp_ = System.nanoTime();
					
					//Old auton... meh
					/*
					multiplier = multiplierPID.getOutput(Math.abs(Robot.drivetrain.leftEncoder.getDistance()),
							Math.abs(this.target));

					leftPower = (Robot.drivetrain.leftPIDDrive.getOutput(Robot.drivetrain.leftEncoder.getRate(),
							this.velocityL * multiplier));
					rightPower = (Robot.drivetrain.rightPIDDrive.getOutput(Robot.drivetrain.rightEncoder.getRate(),
							this.velocityR * multiplier));
					
//					double powerDiff = Robot.drivetrain.leftPIDDrive.getOutput(Robot.drivetrain.leftEncoder.getRate() - Robot.drivetrain.rightEncoder.getRate(), 0);

					 if(multiplier < 0.005){
						 System.out.println("CurrentX: " + this.current_x + " CurrentY: " + this.current_y);
						 this.interrupt = 1;
						 System.out.println("Interrupting");
						 this.interrupt();
						 return;
					 }

					// System.out.println("leftSpeed: " + this.leftSpeed);
					 
					

					Robot.drivetrain.left.set(leftPower);
					Robot.drivetrain.right.set(-(rightPower));
					
					*/
					
					//New speed difference method
					multiplier = multiplierPID.getOutput(this.target - Robot.drivetrain.leftEncoder.getDistance(), 0);

					powerDiff = diffPID.getOutput(this.leftSpeed - this.rightSpeed, 0);
					//powerDiff = Robot.drivetrain.leftPIDDrive.getOutput(Robot.drivetrain.leftEncoder.getRate() - Robot.drivetrain.rightEncoder.getRate(), 0);

					 if(errors.addValueGetAverage(Math.abs(Robot.drivetrain.leftEncoder.getDistance() - this.target)) < 0.05){
						 System.out.println("CurrentX: " + this.current_x + " CurrentY: " + this.current_y);
						 this.interrupt = 1;
						 System.out.println("Interrupting");
						 this.interrupt();
						 return;
					 }

					Robot.drivetrain.left.set((constantPower - powerDiff) * multiplier);
					Robot.drivetrain.right.set(-(constantPower + powerDiff) * multiplier);

				}
				while (System.nanoTime() < timestamp_ + period) {
				}

			}
		} else if (this.type == 1) {
			multiplierPID.reset();
			multiplierPID.setOutputLimits(-1.0, 1.0);
			multiplierPID.setP(0.02);
			multiplierPID.setI(0.0);
			multiplierPID.setD(0.0);

			AveragingFilter errors = new AveragingFilter(50);
			errors.initialize(10);

			while (!interrupted()) {
				synchronized (taskRunningLock_) {
					integratePosition();

					timestamp_ = System.nanoTime();
					multiplier = multiplierPID.getOutput(getDifferenceInAngleDegrees(Robot.drivetrain.navX.getYaw(), this.target), 0);

					// double leftPower =
					// Robot.drivetrain.leftPIDDrive.getOutput(this.leftSpeed,
					// this.velocityL * multiplier);
					// double rightPower =
					// Robot.drivetrain.rightPIDDrive.getOutput(this.rightSpeed,
					// -this.velocityR * multiplier);

					Robot.drivetrain.left.set(multiplier);
					Robot.drivetrain.right.set(multiplier);

					if (errors.addValueGetAverage(Math.abs(getDifferenceInAngleDegrees(Robot.drivetrain.navX.getYaw(), this.target))) < 0.05) {
						this.interrupt = 1;
						System.out.println("Interrupting, mult");
						this.interrupt();
						return;
					}

				}
				while (System.nanoTime() < timestamp_ + period) {
				}

			}
		} else if (this.type == 2) {
			multiplierPID.reset();
			multiplierPID.setOutputLimits(-1.0, 1.0);
			multiplierPID.setP(0.4);
			multiplierPID.setI(0.0);
			multiplierPID.setD(0.5);

			double turnOffset = 0;
			double leftPower, rightPower;

			MiniPID turnOffsetPID = new MiniPID(0.01, 0, 0.0);
			turnOffsetPID.setOutputLimits(-3, 3);

			timestamp_ = System.nanoTime();

			while (System.nanoTime() < timestamp_ + period) {
			}

			while (!interrupted()) {
				synchronized (taskRunningLock_) {
					timestamp_ = System.nanoTime();

					integratePosition();
					/*
					 * if(Math.abs(Math.pow(Math.pow(this.deltaX, 2) +
					 * Math.pow(this.deltaY, 2), 0.5)) < 1.0) {
					 * 
					 * multiplier =
					 * multiplierPID.getOutput(Math.abs(Robot.drivetrain.
					 * leftEncoder.getDistance()), Math.abs(this.target));
					 * 
					 * if(velocityL > 0) { leftPower = (velocityL *
					 * multiplier)*0.063 + basePower; } else { leftPower =
					 * (velocityL * multiplier)*0.063 - basePower; }
					 * 
					 * if(velocityR > 0) { rightPower = (velocityR *
					 * multiplier)*0.063 + basePower; } else { rightPower =
					 * (velocityR * multiplier)*0.063 - basePower; }
					 * 
					 * if(multiplier < 0.05){ System.out.println("CurrentX: " +
					 * this.current_x + " CurrentY: " + this.current_y);
					 * this.interrupt = 1; System.out.println("Interrupting");
					 * this.interrupt(); return; }
					 * 
					 * } else
					 */ if (Double.isNaN(getDifferenceInAngleDegrees(this.targetAngle, this.headingAngle))) {

						leftPower = Robot.drivetrain.leftPIDDrive.getOutput(this.leftSpeed, this.velocityL);
						rightPower = Robot.drivetrain.rightPIDDrive.getOutput(this.rightSpeed, this.velocityR);
						//
						// leftPower = (velocityL) * 0.063;
						// rightPower = (velocityR) * 0.063;

						if (velocityL > 0) {
							leftPower += basePower;
							rightPower += basePower;
						} else {
							leftPower -= basePower;
							rightPower -= basePower;
						}

					} else {
						turnOffset = turnOffsetPID
								.getOutput(getDifferenceInAngleDegrees(this.targetAngle, this.headingAngle), 0);

						System.out.println("Target: " + this.targetAngle + " Heading: " + this.headingAngle + " TO: "
								+ turnOffset);

						leftPower = (Robot.drivetrain.leftPIDDrive.getOutput(this.leftSpeed,
								this.velocityL/* + turnOffset */));
						rightPower = (Robot.drivetrain.rightPIDDrive.getOutput(this.rightSpeed,
								this.velocityR/* - turnOffset */));

						// leftPower = (velocityL + turnOffset) * 0.063;
						// rightPower = (velocityR + turnOffset) * 0.063;

						if (velocityL > 0) {
							leftPower += basePower;
							rightPower += basePower;
						} else {
							leftPower -= basePower;
							rightPower -= basePower;
						}

					}

					Robot.drivetrain.left.set(leftPower);
					Robot.drivetrain.right.set(-rightPower);

				}
				while (System.nanoTime() < timestamp_ + period) {
				}

			}
		}

		return;
	}
}
