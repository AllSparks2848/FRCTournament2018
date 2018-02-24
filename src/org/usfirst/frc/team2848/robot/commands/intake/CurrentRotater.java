//package org.usfirst.frc.team2848.robot.commands.intake;
//
//import org.usfirst.frc.team2848.robot.Robot;
//
//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class CurrentRotater extends Command {
//	Timer timer = new Timer();
//	double pulse = 150;// in milliseconds
//	double milis = 0;
//
//	public CurrentRotater() {
//		requires(Robot.intake);
//		requires(Robot.carriage);
//
//		// Use requires() here to declare subsystem dependencies
//		// eg. requires(chassis);
//	}
//
//	// Called just before this Command runs the first time
//	protected void initialize() {
//		// Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kReverse);
//		timer.start();
//	}
//
//	// Called repeatedly when this Command is scheduled to run
//	protected void execute() {
//		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kReverse);
//		// System.out.println("Current 6: "+ Robot.pdp.getCurrent(6));
//		// System.out.println("Current 7: "+Robot.pdp.getCurrent(7));
//		if (Robot.pdp.getCurrent(7) > 58 && Robot.pdp.getCurrent(7) < 70) {
//			timer.reset();
//			if (timer.get() < 2000) {
//				Robot.carriage.intakeClawMotor.set(-.8);
//				Robot.intake.leftIntake.set(.0);
//				Robot.intake.rightIntake.set(.8);
//			}
//
//			//
//			// if (((int) (milis / pulse)) % 2 == 0) {
//			// Robot.intake.rightIntake.set(0.0);
//			// Robot.intake.leftIntake.set(-.5);
//			// // System.out.println("Left Intake Running");
//			// } else {
//			// Robot.intake.leftIntake.set(0.0);
//			// Robot.intake.rightIntake.set(.5);
//			// // System.out.println("Right Intake Running");
//			// }
//
//		} else {
//			Robot.intake.leftIntake.set(-.8);
//			Robot.intake.rightIntake.set(.8);
//			Robot.carriage.intakeClawMotor.set(-.8);
//
//		}
//	}
//
//	// Make this return true when this Command no longer needs to run execute()
//	protected boolean isFinished() {
//		return false;
//	}
//
//	// Called once after isFinished returns true
//	protected void end() {
//		Robot.intake.leftIntake.set(-0.);
//		Robot.intake.rightIntake.set(0.);
//		Robot.carriage.intakeClawMotor.set(0);
//		Robot.carriage.intakeClaw.set(DoubleSolenoid.Value.kForward);
//	}
//
//	// Called when another command which requires one or more of the same
//	// subsystems is scheduled to run
//	protected void interrupted() {
//		end();
//	}
//}
