package org.usfirst.frc.team2848.robot.util;

import java.util.ArrayList;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class PointNav extends Thread {

	private final Object taskRunningLock_ = new Object();

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

	public double targetAngle;

	double change_x, change_y;
	public double current_x, current_y;
	double[] target_x, target_y;
	Command[] actions;
	double[] constPowers;

	int currentIndex = 0;
	int pointNumber;
	
	int direction;

	public double headingAngle;

	ArrayList<Double> list = new ArrayList<Double>();

	MiniPID multiplierPID = new MiniPID(0.4, 0.0, 0.5);

	public PointNav(double[] targetX, double[] targetY, double[] constPowers, int pointNumber, Command[] actions,
			double periodNS) {
		this.target_x = targetX;
		this.target_y = targetY;
		this.period = periodNS;
		this.actions = actions;
		this.constPowers = constPowers;
		this.pointNumber = pointNumber;

		Robot.drivetrain.leftEncoder.reset();
		Robot.drivetrain.rightEncoder.reset();
	}

	// integrate the position of the robot using the distance from each encoder.
	public void integratePosition() {
		leftPos = Robot.drivetrain.leftEncoder.getDistance();
		rightPos = Robot.drivetrain.rightEncoder.getDistance();

		yaw = Robot.drivetrain.navX.getFusedHeading();

		yaw = 90 - yaw;
		if (yaw < 0) {
			yaw += 360;
		}

		leftDistance = (leftPos - lastLeftPos);
		rightDistance = (rightPos - lastRightPos);

		hypotenuseDistance = (leftDistance + rightDistance) / 2;

		change_x = (Math.cos(((yaw + lastYaw) * 0.5 * Math.PI) / 180) * hypotenuseDistance);
		change_y = (Math.sin(((yaw + lastYaw) * 0.5 * Math.PI) / 180) * hypotenuseDistance);

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

		lastYaw = yaw;
		lastLeftPos = leftPos;
		lastRightPos = rightPos;

		this.deltaX = this.target_x[currentIndex] - this.current_x;
		this.deltaY = this.target_y[currentIndex] - this.current_y;
		
//		System.out.println("TargetX: " + this.target_x[currentIndex] + " TargetY: " + this.target_y[currentIndex]);

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

		multiplierPID.reset();
		multiplierPID.setOutputLimits(-1.0, 1.0);
		multiplierPID.setP(0.25);
		multiplierPID.setI(0.0);
		multiplierPID.setD(0.0);
		
		boolean goingRightDirection = false;

		double multiplier = 1;

		double constantPower = this.constPowers[0];
		double powerDiff = 0;

		MiniPID turnOffsetPID = new MiniPID(0.01, 0, 0.02);
		turnOffsetPID.setOutputLimits(-0.4, 0.4);

		timestamp_ = System.nanoTime();

		Robot.drivetrain.left.set(constantPower);
		Robot.drivetrain.right.set(-constantPower);

		t.reset();
		t.start();

		double timerTarget = 0.05; // .1

		double previousDisplacement, displacement;
		previousDisplacement = 0;
		
		double targetYawAngle = 0;

		this.actions[0].start();

		while (!interrupted()) {
			synchronized (taskRunningLock_) {
				
				timestamp_ = System.nanoTime();
				integratePosition();

				if (constantPower == 0) {
					
					multiplierPID.setOutputLimits(-1.0, 1.0);
					multiplierPID.setP(0.04);
					multiplierPID.setI(0.0);
					multiplierPID.setD(0.0);
					
					targetYawAngle = 90 - this.targetAngle;
					if(targetYawAngle < 0) {
						targetYawAngle += 360;
					}

					
					multiplier = multiplierPID.getOutput(getDifferenceInAngleDegrees(Robot.drivetrain.navX.getFusedHeading(), targetYawAngle), 0);

					Robot.drivetrain.left.set(multiplier);
					Robot.drivetrain.right.set(multiplier);

					if (t.get() > timerTarget) {
						
						displacement = getDifferenceInAngleDegrees(Robot.drivetrain.navX.getFusedHeading(), targetYawAngle);

						if (this.currentIndex == this.pointNumber - 1 && Math.abs(displacement - previousDisplacement) < 0.7) {
							this.interrupt = 1;
							System.out.println("Interrupting");
							this.interrupt();
							return;
						} else if(Math.abs(displacement - previousDisplacement) < 0.7) {
							multiplierPID.reset();
							turnOffsetPID.reset();
							this.currentIndex += 1;
							constantPower = this.constPowers[this.currentIndex];
							
							goingRightDirection = false;
							
							if(constantPower == 0) {
								this.t.reset();
								this.t.start();
							}
							this.actions[this.currentIndex].start();
							return;
						}

						previousDisplacement = displacement;

						timerTarget += 0.05; // .1
					}


				} else {
					
					multiplierPID.setOutputLimits(-1.0, 1.0);
					
					multiplierPID.setP(0.25);
					multiplierPID.setI(0.0);
					multiplierPID.setD(0.0);
					
					if (this.currentIndex == this.pointNumber - 1) {
						multiplier = multiplierPID.getOutput(-(Math.cos(getDifferenceInAngleDegrees(this.targetAngle, this.headingAngle) / 180 * Math.PI) * Math.pow(Math.pow(this.deltaX, 2) + Math.pow(this.deltaY, 2), 0.5)), 0);
					}

					powerDiff = turnOffsetPID.getOutput(getDifferenceInAngleDegrees(this.targetAngle, this.headingAngle), 0);

					Robot.drivetrain.left.set((constantPower + powerDiff) * multiplier);
					Robot.drivetrain.right.set(-(constantPower - powerDiff) * multiplier);
					
					System.out.println("Target: " + this.targetAngle + " Adj: " + Math.cos(getDifferenceInAngleDegrees(this.targetAngle, this.headingAngle) / 180 * Math.PI) * Math.abs(Math.pow(Math.pow(this.deltaX, 2) + Math.pow(this.deltaY, 2), 0.5)));

					if (!goingRightDirection) {
						goingRightDirection = (Math.cos(getDifferenceInAngleDegrees(this.targetAngle, this.headingAngle) / 180 * Math.PI) * Math.abs(Math.pow(Math.pow(this.deltaX, 2) + Math.pow(this.deltaY, 2), 0.5)) > 0);
						
					} else if (this.currentIndex == this.pointNumber - 1 && 
							(Math.cos(getDifferenceInAngleDegrees(this.targetAngle, this.headingAngle) / 180 * Math.PI) * Math.abs(Math.pow(Math.pow(this.deltaX, 2) + Math.pow(this.deltaY, 2), 0.5)) < 0) && 
							(Math.abs(Math.sin(getDifferenceInAngleDegrees(this.targetAngle, this.headingAngle) / 180 * Math.PI) * Math.pow(Math.pow(this.deltaX, 2) + Math.pow(this.deltaY, 2), 0.5)) < 4.0)) {
						this.interrupt = 1;
						System.out.println("Interrupting");
						this.interrupt();
						return;
					} else if ((Math.cos(getDifferenceInAngleDegrees(this.targetAngle, this.headingAngle) / 180 * Math.PI) * Math.abs(Math.pow(Math.pow(this.deltaX, 2) + Math.pow(this.deltaY, 2), 0.5)) < 0) && 
							(Math.abs(Math.sin(getDifferenceInAngleDegrees(this.targetAngle, this.headingAngle) / 180 * Math.PI) * Math.pow(Math.pow(this.deltaX, 2) + Math.pow(this.deltaY, 2), 0.5)) < 4.0)) {
						turnOffsetPID.reset();
						multiplierPID.reset();
						this.currentIndex += 1;
						constantPower = this.constPowers[this.currentIndex];
						
						goingRightDirection = false;
						
						if(constantPower == 0) {
							this.t.reset();
							this.t.start();
						}
						this.actions[this.currentIndex].start();
					}
				}
			}
			while (System.nanoTime() < timestamp_ + period) {
				
			}
		}
		return;
	}
}
