package AutonCommandGroups;

import org.usfirst.frc.team2848.robot.commands.carriage.ExtakeFront;
import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.intake.PivotIn;
import org.usfirst.frc.team2848.robot.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class WaitThenElevator extends CommandGroup {

    public WaitThenElevator(double seconds, double height, double power) {
    	addSequential(new Wait(seconds));
    	addSequential(new GoToHeight(height));
    	addSequential(new PivotIn());
    	addSequential(new ExtakeFront(0.5, power));
    }
}
