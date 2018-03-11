package org.usfirst.frc.team2848.robot.commands.jevois;

import org.usfirst.frc.team2848.robot.Robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GetAngle extends Command {

	public int loopCount;
	
	public double x;
	public double y;
	
    public GetAngle() {
    	requires(Robot.jevois);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	loopCount = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	try {
			System.out.print("Creating JeVois SerialPort...");
			Robot.jevois.visionPort = new SerialPort(Robot.jevois.BAUD_RATE, SerialPort.Port.kUSB);
			System.out.println("Successfully created JeVois SerialPort");
		} catch (Exception e) {
			System.out.println("Failed... Fix and then restart code");
            	e.printStackTrace();
		}
		
		Robot.jevois.inst = NetworkTableInstance.getDefault();
		Robot.jevois.table = Robot.jevois.inst.getTable("datatable");
		Robot.jevois.xEntry = Robot.jevois.table.getEntry("X");
		Robot.jevois.yEntry = Robot.jevois.table.getEntry("Y");
		
		if (Robot.jevois.visionPort == null) return;
        System.out.println("pinging JeVois");
        String cmd = "pingn";
        int bytes = Robot.jevois.visionPort.writeString(cmd);
        System.out.println("wrote " +  bytes + "/" + cmd.length() + " bytes, cmd: " + cmd);
        
        x = 0;
        y = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.jevois.visionPort.getBytesReceived() > 0) {
    		System.out.println("Waited: " + loopCount + " loops, Rcv'd: " + Robot.jevois.visionPort.readString());
    		loopCount = 0;
    	} else {
    		++loopCount;
		}
    	
    	Robot.jevois.xEntry.setDouble(x);
    	Robot.jevois.yEntry.setDouble(y);
    	x += 0.05;
    	y += 1.0;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
