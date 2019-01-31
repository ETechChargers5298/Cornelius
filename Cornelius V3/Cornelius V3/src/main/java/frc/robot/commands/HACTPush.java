package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HACTPush extends Command {
    
    public HACTPush() {
        requires (Robot.hactSystem);    //Tells the code that it requires the HACT subsystem.
    }
    
@Override
protected void initialize() {
}

@Override
protected void execute() {
    Robot.hactSystem.HACTPush(0.4);   //Gives it a value to use. This is a speed value.  
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