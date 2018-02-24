package org.usfirst.frc.team2848.robot.commands.carriage;

import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ExtakeLeftElevator extends CommandGroup {

    public ExtakeLeftElevator() {
        addSequential(new GoToHeight(250));
        addSequential(new ExtakeLeftAuton());
        addSequential(new DownToBottom());
    }
}
