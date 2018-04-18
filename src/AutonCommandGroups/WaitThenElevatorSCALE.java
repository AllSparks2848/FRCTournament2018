package AutonCommandGroups;

import org.usfirst.frc.team2848.robot.commands.elevator.GoToHeight;
import org.usfirst.frc.team2848.robot.commands.intake.PivotIn;
import org.usfirst.frc.team2848.robot.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class WaitThenElevatorSCALE extends CommandGroup {

    public WaitThenElevatorSCALE(double seconds) {
    	addSequential(new Wait(seconds));
    	addSequential(new GoToHeight(2300));
    	addSequential(new PivotIn());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
