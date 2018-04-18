package AutonCommandGroups;

import org.usfirst.frc.team2848.robot.commands.elevator.DownToBottom;
import org.usfirst.frc.team2848.robot.commands.intake.PivotOut;
import org.usfirst.frc.team2848.robot.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class WaitThenDown extends CommandGroup {

    public WaitThenDown(double seconds) {
    	addSequential(new Wait(seconds));
    	addSequential(new PivotOut());
    	addSequential(new DownToBottom());
    }
}
