//package org.usfirst.frc.team2848.robot.commands.auton;
//
//import org.usfirst.frc.team2848.robot.Robot;
//import org.usfirst.frc.team2848.robot.util.PIDCalculate;
//
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.command.Command;
//
//public class FollowPath extends Command {
//
//	Timer timer = new Timer();
//
//	private int setpointIndex = 0;
//	private double leftV, rightV;
//
//	public double[] distances, radii, angles;
//
//	public double constVelocity = 4;
//	public double wheelbaseSeperation = 2;
//
//	public FollowPath(double[] x, double[] y, double[] directions) {
//		requires(Robot.drivetrain);
//
//		System.out.println("Finishing FollowPath init");
//
//		Robot.drivetrain.arcPIDs = new PIDCalculate(0, 0);
//
//		Robot.pathplanning.x = x;
//		Robot.pathplanning.y = y;
//		Robot.pathplanning.directions = directions;
//
//		Robot.pathplanning.calculatePoints();
//
//		this.distances = Robot.pathplanning.distances;
//		this.angles = Robot.pathplanning.angles;
//		this.radii = Robot.pathplanning.radii;
//
//		timer.start();
//	}
//
//	// Called just before this Command runs the first time
//	protected void initialize() {
//		Robot.drivetrain.leftEncoder.reset();
//		Robot.drivetrain.rightEncoder.reset();
//
//	}
//	
//	protected void endPIDThread(){
//    	Robot.drivetrain.arcPIDs.interrupt();
//    	try {
//			Robot.drivetrain.arcPIDs.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// Called repeatedly when this Command is scheduled to run
//	protected void execute() {
//
//		if (this.distances[setpointIndex] == 0) {
//			System.out.println("angle: " + Robot.drivetrain.navX.getFusedHeading());
//
//			if (this.radii[setpointIndex] > 0) {
//				rightV = constVelocity;
//				leftV = Math.pow(((this.radii[setpointIndex] - (wheelbaseSeperation / 2)) * Math.pow(constVelocity, 2))
//						/ (this.radii[setpointIndex] + (wheelbaseSeperation / 2)), 0.5);
//			} else {
//				leftV = constVelocity;
//				rightV = Math.pow(((this.radii[setpointIndex] - (wheelbaseSeperation / 2)) * Math.pow(constVelocity, 2))
//						/ (this.radii[setpointIndex] + (wheelbaseSeperation / 2)), 0.5);
//			}
//			
//			endPIDThread();
//			Robot.drivetrain.arcPIDs = new PIDCalculate(leftV, rightV);
//			Robot.drivetrain.arcPIDs.start();
//
//			if (Math.abs(Robot.drivetrain.navX.getFusedHeading() - this.angles[setpointIndex]) < 10) {
//				setpointIndex++;
//
//				if (setpointIndex == radii.length) {
//					return;
//				}
//			}
//		} else {
//			System.out.println("lef: " + Robot.drivetrain.leftEncoder.getDistance() + " target: "
//					+ (this.distances[setpointIndex] - 0.1));
//			
//			endPIDThread();
//			Robot.drivetrain.arcPIDs = new PIDCalculate(constVelocity, constVelocity);
//			Robot.drivetrain.arcPIDs.start();
//			
//			if (Robot.drivetrain.leftEncoder.getDistance() > (this.distances[setpointIndex] - 0.1)) {
//				setpointIndex++;
//
//				if (setpointIndex == radii.length) {
//					return;
//				}
//			}
//		}
//		return;
//	}
//
//	// Make this return true when this Command no longer needs to run execute()
//	protected boolean isFinished() {
//		if (setpointIndex == radii.length) {
//			Robot.drivetrain.left.stopMotor();
//			Robot.drivetrain.right.stopMotor();
//			return true;
//		}
//
//		return false;
//	}
//
//	// Called once after isFinished returns true
//	protected void end() {
//		setpointIndex = 0;
//
//		Robot.drivetrain.drivetrainSetPowerZero();
//
//		Robot.drivetrain.arcPIDs.interrupt();
//		try {
//			Robot.drivetrain.arcPIDs.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// Called when another command which requires one or more of the same
//	// subsystems is scheduled to run
//	protected void interrupted() {
//	}
//}
