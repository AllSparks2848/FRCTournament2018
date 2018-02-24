package org.usfirst.frc.team2848.robot.util;

import org.usfirst.frc.team2848.robot.Robot;

public class PIDCalculate extends Thread {

	double velocityL;
	double velocityR;
	double interrupt = 0;
	double multiplier;

	double target;
	double type;

	public PIDCalculate(double velocityL, double velocityR, double target, double type) {
		this.velocityL = velocityL;
		this.velocityR = velocityR;

		this.multiplier = 1;

		this.target = target;
		this.type = type;
	}

	public void run() {

		Robot.drivetrain.leftEncoder.reset();
		Robot.drivetrain.rightEncoder.reset();

		if (this.type == 0) {
			while (!interrupted()) {
				double leftSpeed = Robot.drivetrain.leftPIDDrive.getOutput(Robot.drivetrain.leftEncoder.getRate(),
						this.velocityL * multiplier);
				double rightSpeed = Robot.drivetrain.rightPIDDrive.getOutput(Robot.drivetrain.rightEncoder.getRate(),
						this.velocityR * multiplier);

				Robot.drivetrain.left.set(leftSpeed);
				Robot.drivetrain.right.set(-rightSpeed);

				System.out.println("Mult: "
						+ (Math.abs(Robot.drivetrain.leftEncoder.getDistance() - this.target)) / (this.target * 0.1));

				if (Math.abs(Robot.drivetrain.leftEncoder.getDistance() - this.target) < this.target * 0.1) {
					System.out.println("Mult: " + this.multiplier);
					this.multiplier = (Math
							.abs(Robot.drivetrain.leftEncoder.getDistance() - (this.target) / this.target * 0.1));
				}

				if (Math.abs(Robot.drivetrain.leftEncoder.getDistance() - this.target) < 0.5) {
					int i = 6000;
					while (i > 0) {
						if (velocityL > 0) {
							Robot.drivetrain.left.set(0.1);
						} else {
							Robot.drivetrain.left.set(-0.1);
						}

						if (velocityR > 0) {
							Robot.drivetrain.right.set(0.1);
						} else {
							Robot.drivetrain.right.set(-0.1);
						}
						i--;
					}
					this.interrupt();
					return;
				}
			}
		} else if (this.type == 1) {
			while (!interrupted()) {
				double leftSpeed = Robot.drivetrain.leftPIDDrive.getOutput(Robot.drivetrain.leftEncoder.getRate(),
						this.velocityL * multiplier);
				double rightSpeed = Robot.drivetrain.rightPIDDrive.getOutput(Robot.drivetrain.rightEncoder.getRate(),
						-this.velocityR * multiplier);

				Robot.drivetrain.left.set(leftSpeed);
				Robot.drivetrain.right.set(-rightSpeed);
				
				System.out.println("Yaw: " + Robot.drivetrain.navX.getYaw());

				if (Math.abs(Robot.drivetrain.navX.getYaw() - this.target) < 20) {
					this.interrupt();
					return;
				}
			}
		} else if (this.type == 2) {
			while (!interrupted()) {
				double leftSpeed = Robot.drivetrain.leftPIDDrive.getOutput(Robot.drivetrain.leftEncoder.getRate(),
						-this.velocityL * multiplier);
				double rightSpeed = Robot.drivetrain.rightPIDDrive.getOutput(Robot.drivetrain.rightEncoder.getRate(),
						this.velocityR * multiplier);

				Robot.drivetrain.left.set(leftSpeed);
				Robot.drivetrain.right.set(-rightSpeed);
				
				System.out.println("Yaw: " + Robot.drivetrain.navX.getYaw());

				if (Math.abs(Robot.drivetrain.navX.getYaw() - this.target) < 20) {
					this.interrupt();
					return;
				}
			}
		}

		return;
	}
}
