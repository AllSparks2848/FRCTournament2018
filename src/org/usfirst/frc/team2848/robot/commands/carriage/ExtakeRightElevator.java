package org.usfirst.frc.team2848.robot.commands.carriage;

import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ExtakeRightElevator extends CommandGroup {

    public ExtakeRightElevator() {
        addSequential(new GoToHeight(250));
        addSequential(new ExtakeRightAuton());
        addSequential(new DownToBottom());
    }
}
