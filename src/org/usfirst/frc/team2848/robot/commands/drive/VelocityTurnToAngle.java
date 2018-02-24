//package org.usfirst.frc.team2848.robot.commands.drive;
//
//import org.usfirst.frc.team2848.robot.Robot;
//import org.usfirst.frc.team2848.robot.util.PIDCalculate;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class VelocityTurnToAngle extends Command {
//	
//	double velocity, angle, direction;
//	
//    public VelocityTurnToAngle(double velocity, double angle, double direction) {
//        this.velocity = velocity;
//        this.angle = angle;
//        this.direction = direction;
//    }
//
//    protected void initialize() {
//    	Robot.drivetrain.arcPIDs = new PIDCalculate(velocity, velocity, angle, direction);
//        Robot.drivetrain.arcPIDs.start();
//        
//        Robot.drivetrain.navX.reset();
//    }
//
//    protected void execute() {
//    }
//
//    protected boolean isFinished() {
//    	return Robot.drivetrain.arcPIDs.isInterrupted() || (Math.abs(Robot.drivetrain.navX.getYaw() - angle) < 10);
//    }
//
//    protected void end() {
//    	
//    	
//    	Robot.drivetrain.drivetrainSetPowerZero();
//    	
//    	System.out.println("DONE");
//
//    	try {
//			Robot.drivetrain.arcPIDs.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//    }
//
//    protected void interrupted() {
//    	end();
//    }
//}
