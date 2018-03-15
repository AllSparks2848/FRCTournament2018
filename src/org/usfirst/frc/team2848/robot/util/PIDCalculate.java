package org.usfirst.frc.team2848.robot.util;

import java.util.ArrayList;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class PIDCalculate extends Thread {

	private final Object taskRunningLock_ = new Object();
	
	double velocityL;
	double velocityR;
	double multiplier, turnOffset;
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
	double lastLeftSpeed = 0;
	double lastRightSpeed = 0;
	
	double leftSpeed, rightSpeed, yaw;
	double leftDistance, rightDistance;
	double hypotenuseDistance;
	
	double change_x, change_y;
	double current_x, current_y;
	double target_x, target_y;
	
	double headingAngle;
	
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
	
	public void setTargetXandY(double x, double y) {
		this.target_x = x;
		this.target_y = y;
	}
	
	//Use a trapezoidal integration technique to integrate the position of the robot using the distance from each encoder.
	private void trapIntegratePosition() {
		leftSpeed = Robot.drivetrain.leftEncoder.getRate();
		rightSpeed = Robot.drivetrain.rightEncoder.getRate();
		yaw = Robot.drivetrain.navX.getYaw();
		
		yaw = 90 - yaw;
		if (yaw < 0) {
			yaw += 360;
		}		
		
		leftDistance = 0.5 * (period / 1000000000) * (leftSpeed + lastLeftSpeed);
		rightDistance = 0.5 * (period / 1000000000) * (rightSpeed + lastRightSpeed);
		
		hypotenuseDistance = (leftDistance + rightDistance) / 2;
		
//		System.out.println("DistanceTraveled: " + hypotenuseDistance);
		
		change_x = ((Math.cos((lastYaw * Math.PI)/180) * hypotenuseDistance) + (Math.cos((yaw * Math.PI)/180) * hypotenuseDistance)) / 2;
		change_y = ((Math.sin((lastYaw * Math.PI)/180) * hypotenuseDistance) + (Math.sin((yaw * Math.PI)/180) * hypotenuseDistance)) / 2;
		
		headingAngle = Math.atan((change_x) / (change_y));
		
//		System.out.println("ChangeY: " + change_y);
		
		current_x += change_x;
		current_y += change_y;
		
		if(change_y == 0 && change_x == 0) {}
		
		if(change_y == 0) {
			if(change_x > 0) {
				headingAngle = 0;
			} else {
				headingAngle = 180;
			}
		} else if(change_x == 0) {
			if(change_y > 0) {			
				headingAngle = 90;
			} else {
				headingAngle = 270;
			}
		}
		
		if(change_x < 0) {
			headingAngle = Math.atan(change_y/change_x)*Math.PI*180 + 180;
		} else if(change_y > 0) {
			headingAngle = Math.atan(change_y/change_x)*Math.PI*180;
		} else {
			headingAngle = Math.atan(change_y/change_x)*Math.PI*180 + 360;
		}
		
//		System.out.println("Curr X: " + current_x + " Curr Y: " + current_y);
		
		lastYaw = yaw;
		lastLeftSpeed = leftSpeed;
		lastRightSpeed = rightSpeed;
	}
	
    private double getDifferenceInAngleDegrees(double from, double to) {
        return boundAngleNeg180to180Degrees(to - from);
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
			multiplierPID.setOutputLimits(-1.0, 1.0);
			multiplierPID.setP(0.4);
			multiplierPID.setI(0.0);
			multiplierPID.setD(0.5);
			
			while (!interrupted()) {
				synchronized (taskRunningLock_) {
					timestamp_ = System.nanoTime();
					multiplier = multiplierPID.getOutput(Math.abs(Robot.drivetrain.leftEncoder.getDistance()), Math.abs(this.target));
					
//					list.add(Robot.drivetrain.leftEncoder.getRate()/Robot.drivetrain.rightEncoder.getRate());
					
					double leftSpeed = Robot.drivetrain.leftPIDDrive.getOutput(Robot.drivetrain.leftEncoder.getRate(),
							this.velocityL * multiplier);
					double rightSpeed = Robot.drivetrain.rightPIDDrive.getOutput(Robot.drivetrain.rightEncoder.getRate(),
							this.velocityR * multiplier);
					
//					System.out.println("Lef: " + Robot.drivetrain.leftEncoder.getRate() + " Rig: " + Robot.drivetrain.rightEncoder.getRate());

					Robot.drivetrain.left.set((leftSpeed + basePower));
					Robot.drivetrain.right.set(-(rightSpeed + basePower));
					
					if(multiplier < 0.05){
						this.interrupt = 1;
						System.out.println("Interrupting");
						this.interrupt();
						return;
					}
				}				
				while(System.nanoTime() < timestamp_ + period){}
				
			}
		} else if (this.type == 1) {
			multiplierPID.setOutputLimits(-1.0, 1.0);
			multiplierPID.setP(0.015);
			multiplierPID.setI(0.0);
			multiplierPID.setD(0.1);
			
			
			AveragingFilter errors = new AveragingFilter(20);
			errors.initialize(10);
			
			while (!interrupted()) {
				synchronized (taskRunningLock_) {
					timestamp_ = System.nanoTime();
					multiplier = multiplierPID.getOutput(getDifferenceInAngleDegrees(this.target, Robot.drivetrain.navX.getYaw()), 0);
					
					double leftSpeed = Robot.drivetrain.leftPIDDrive.getOutput(Robot.drivetrain.leftEncoder.getRate(),
							this.velocityL * multiplier);
					double rightSpeed = Robot.drivetrain.rightPIDDrive.getOutput(Robot.drivetrain.rightEncoder.getRate(),
							-this.velocityR * multiplier);

					Robot.drivetrain.left.set(leftSpeed);
					Robot.drivetrain.right.set(-rightSpeed);
					
					if(Math.abs(errors.addValueGetAverage(multiplier)) < 0.00005){
						this.interrupt = 1;
						System.out.println("Interrupting");
						this.interrupt();
						return;
					}
					
				}				
				while(System.nanoTime() < timestamp_ + period){}
				
			}
		} else if (this.type == 2) {
			multiplierPID.setOutputLimits(-1.0, 1.0);
			multiplierPID.setP(0.1);
			multiplierPID.setI(0.0);
			multiplierPID.setD(0.0);
			MiniPID turnOffsetPID = new MiniPID(0.05,0,0);
			turnOffsetPID.setOutputLimits(-5, 5);
			
			while (!interrupted()) {
				synchronized (taskRunningLock_) {
					timestamp_ = System.nanoTime();

					double deltaX, deltaY, targetAngle;
					
					deltaX = this.target_x - this.current_x;
					deltaY = this.target_y - this.current_y;
					
					if(deltaY == 0) {
						if(deltaX > 0) {
							targetAngle = 0;
						} else {
							targetAngle = 180;
						}
					} else if(deltaX == 0) {
						if(deltaY > 0) {			
							targetAngle = 90;
						} else {
							targetAngle = 270;
						}
					}
					
					if(deltaX < 0) {
						targetAngle = Math.atan(deltaY/deltaX)*Math.PI*180 + 180;
					} else if(deltaY > 0) {
						targetAngle = Math.atan(deltaY/deltaX)*Math.PI*180;
					} else {
						targetAngle = Math.atan(deltaY/deltaX)*Math.PI*180 + 360;
					}
					
					System.out.println("CurrentHeading: " + getDifferenceInAngleDegrees(this.headingAngle, this.headingAngle));
					
					double leftSpeed = Robot.drivetrain.leftPIDDrive.getOutput(Robot.drivetrain.leftEncoder.getRate(),
							(this.velocityL + turnOffset));
					double rightSpeed = Robot.drivetrain.rightPIDDrive.getOutput(Robot.drivetrain.rightEncoder.getRate(),
							(this.velocityR - turnOffset));

					Robot.drivetrain.left.set((leftSpeed + basePower));
					Robot.drivetrain.right.set(-(rightSpeed + basePower));
					
//					if(multiplier < 0.05){
//						this.interrupt = 1;
//						System.out.println("Interrupting");
//						this.interrupt();
//						return;
//					}
				}				
				while(System.nanoTime() < timestamp_ + period){}
				
			}
		}

		return;
	}
}
