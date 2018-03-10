package org.usfirst.frc.team2848.robot.util;

import java.util.ArrayList;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class PIDCalculate extends Thread {

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
	
	ArrayList<Double> list = new ArrayList<Double>(); 
	
	MiniPID multiplierPID = new MiniPID(0.4, 0.0, 0.5);

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
			multiplierPID.setOutputLimits(-1.0, 1.0);
			
			while (!interrupted()) {
				timestamp_ = System.nanoTime();
				multiplier = multiplierPID.getOutput(Math.abs(Robot.drivetrain.leftEncoder.getDistance()), Math.abs(this.target));
				
//				if (Math.abs(Robot.drivetrain.leftEncoder.getDistance()) < Math.abs(this.target)*rampdown){
//					multiplier = (Math.abs(this.target)*rampdown - Math.abs(Robot.drivetrain.leftEncoder.getDistance()))/Math.abs(this.target)*rampdown;
//				} else {
//					multiplier = 1.0;
//				}
				
				//System.out.println("Ratio: " + Robot.drivetrain.leftEncoder.getRate()/Robot.drivetrain.rightEncoder.getRate());
				list.add(Robot.drivetrain.leftEncoder.getRate()/Robot.drivetrain.rightEncoder.getRate());
				
				double leftSpeed = Robot.drivetrain.leftPIDDrive.getOutput(Robot.drivetrain.leftEncoder.getRate(),
						this.velocityL * multiplier);
				double rightSpeed = Robot.drivetrain.rightPIDDrive.getOutput(Robot.drivetrain.rightEncoder.getRate(),
						this.velocityR * multiplier);

				Robot.drivetrain.left.set((leftSpeed + basePower));
				Robot.drivetrain.right.set(-(rightSpeed + basePower));
				
				if(multiplier < 0.05){
					this.interrupt = 1;
					System.out.println("Interrupting");
					this.interrupt();
//					for(double i : list) {
//						System.out.println(i);
//					}
					return;
				}
				
				while(System.nanoTime() < timestamp_ + period){}
				
			}
		} else if (this.type == 1) {
			multiplierPID.setOutputLimits(-1.0, 1.0);
			multiplierPID.setP(0.02);
			multiplierPID.setD(0.9);
			
			while (!interrupted()) {
				timestamp_ = System.nanoTime();
				multiplier = multiplierPID.getOutput(Math.abs(Robot.drivetrain.navX.getYaw()), Math.abs(this.target));
				
				double leftSpeed = Robot.drivetrain.leftPIDDrive.getOutput(Robot.drivetrain.leftEncoder.getRate(),
						this.velocityL * multiplier);
				double rightSpeed = Robot.drivetrain.rightPIDDrive.getOutput(Robot.drivetrain.rightEncoder.getRate(),
						-this.velocityR * multiplier);

				Robot.drivetrain.left.set(leftSpeed);
				Robot.drivetrain.right.set(-rightSpeed);
				
				if(Math.abs(multiplier) < 0.0005){
					this.interrupt = 1;
					System.out.println("Interrupting");
					this.interrupt();
					return;
				}
				
				while(System.nanoTime() < timestamp_ + period){}
				
			}
		} else if (this.type == 2) {
			multiplierPID.setOutputLimits(-1.0, 1.0);
			multiplierPID.setP(0.02);
			multiplierPID.setD(0.9);
			
			while (!interrupted()) {
				timestamp_ = System.nanoTime();
				multiplier = multiplierPID.getOutput(Math.abs(Robot.drivetrain.navX.getYaw()), Math.abs(this.target));
				
				double leftSpeed = Robot.drivetrain.leftPIDDrive.getOutput(Robot.drivetrain.leftEncoder.getRate(),
						-this.velocityL * multiplier);
				double rightSpeed = Robot.drivetrain.rightPIDDrive.getOutput(Robot.drivetrain.rightEncoder.getRate(),
						this.velocityR * multiplier);

				Robot.drivetrain.left.set(leftSpeed);
				Robot.drivetrain.right.set(-rightSpeed);
				
				if(Math.abs(multiplier) < 0.0005){
					this.interrupt = 1;
					System.out.println("Interrupting");
					this.interrupt();
					return;
				}
				
				while(System.nanoTime() < timestamp_ + period){}
				
			}
		}

		return;
	}
}
