package org.usfirst.frc.team2848.robot.commands.intake;

import org.usfirst.frc.team2848.robot.commands.carriage.ReleaseIntakeClaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeSideCube extends CommandGroup {

    public IntakeSideCube() {
        addSequential(new ReleaseIntakeClaw());
        
        
    }
}
