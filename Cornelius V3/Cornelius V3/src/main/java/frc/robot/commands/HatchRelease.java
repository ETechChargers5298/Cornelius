package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchRelease extends Command {
    
    public HatchRelease() {
        requires (Robot.hatchSystem);  //Tells the code that it requires the Hatch subsystem
    }
    
@Override
protected void initialize() {
}

@Override
protected void execute() {
    Robot.hatchSystem.Release(true); // This means that when true, the hatch will release.
}
    

// Make this return true when this Command no longer needs to run execute()
protected boolean isFinished() {
  return (true);
}

// Called once after isFinished returns true
@Override
protected void end() {
}
 

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
@Override
protected void interrupted() {
  end();
    }
}