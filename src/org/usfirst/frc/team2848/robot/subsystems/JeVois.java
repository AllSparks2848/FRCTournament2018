package org.usfirst.frc.team2848.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class JeVois extends Subsystem {

	public static final int BAUD_RATE = 115200;
	
	public static SerialPort visionPort = null;
	
	public static NetworkTableInstance inst;
	public static NetworkTable table;
	public static NetworkTableEntry xEntry;
	public static NetworkTableEntry yEntry;
	
	public JeVois() {
		super("JeVois");
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

