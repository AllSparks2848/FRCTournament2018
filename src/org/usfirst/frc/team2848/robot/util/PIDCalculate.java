package org.usfirst.frc.team2848.robot.util;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class PIDCalculate extends Thread {

	double velocityL;
	double velocityR;
	double multiplier;
	double offset;
	
	Timer t = new Timer();
	
	public double interrupt = 0;

	double target;
	double type;
	
	MiniPID multiplierPID = new MiniPID(0.45, 0.0, 0.0);

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

	public void run() {
		
		boolean notBeginning = false;

		Robot.drivetrain.leftEncoder.reset();
		Robot.drivetrain.rightEncoder.reset();

		if (this.type == 0) {
			multiplierPID.setOutputLimits(0, 1.0);
			
			while (!interrupted()) {
				multiplier = multiplierPID.getOutput(Math.abs(Robot.drivetrain.leftEncoder.getDistance()), Math.abs(this.target));
				
//				System.out.println("Mult: " + multiplier);
				
				double leftSpeed = Robot.drivetrain.leftPIDDrive.getOutput(Robot.drivetrain.leftEncoder.getRate(),
						this.velocityL * multiplier);
				double rightSpeed = Robot.drivetrain.rightPIDDrive.getOutput(Robot.drivetrain.rightEncoder.getRate(),
						this.velocityR * multiplier);

				Robot.drivetrain.left.set(leftSpeed);
				Robot.drivetrain.right.set(-rightSpeed);
				
				if(multiplier < 0.05){
					this.interrupt = 1;
					System.out.println("Interrupting");
					this.interrupt();
					return;
				}
				
//				if (multiplier == 1){
//					notBeginning = true;
//				}
//				
//				if (multiplier != 1 && notBeginning){
//					t.start();
//				}
//				
//				if (t.get() > 1) {
//					this.interrupt = 1;
//					System.out.println("Interrupting");
//					this.interrupt();
//					return;
//				}
			}
		} else if (this.type == 1) {
			
			while (!interrupted()) {
				double leftSpeed = Robot.drivetrain.leftPIDDrive.getOutput(Robot.drivetrain.leftEncoder.getRate(),
						this.velocityL);
				double rightSpeed = Robot.drivetrain.rightPIDDrive.getOutput(Robot.drivetrain.rightEncoder.getRate(),
						-this.velocityR);

				Robot.drivetrain.left.set(leftSpeed);
				Robot.drivetrain.right.set(-rightSpeed);
				
				if(Math.abs(Robot.drivetrain.navX.getFusedHeading() - this.target) < 10){
					this.interrupt = 1;
					System.out.println("Interrupting");
					this.interrupt();
					return;
				}
			}
		} else if (this.type == 2) {			
			while (!interrupted()) {				
				double leftSpeed = Robot.drivetrain.leftPIDDrive.getOutput(Robot.drivetrain.leftEncoder.getRate(),
						-this.velocityL);
				double rightSpeed = Robot.drivetrain.rightPIDDrive.getOutput(Robot.drivetrain.rightEncoder.getRate(),
						this.velocityR);

				Robot.drivetrain.left.set(leftSpeed);
				Robot.drivetrain.right.set(-rightSpeed);
				
				if(Math.abs(Robot.drivetrain.navX.getFusedHeading() - this.target) < 10){
					this.interrupt = 1;
					System.out.println("Interrupting");
					this.interrupt();
					return;
				}
			}
		}

		return;
	}
}
