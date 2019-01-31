package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchRetreat extends Command {
    
    public HatchRetreat() {
        requires (Robot.hatchSystem);
    }
    
@Override
protected void initialize() {
}

@Override
protected void execute() {
    Robot.hatchSystem.Retreat(false);
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