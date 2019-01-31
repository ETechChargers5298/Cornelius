package frc.robot.commands; 

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class DriveTrainCommands extends Command {

	public DriveTrainCommands() {
		requires(Robot.driveTrain);
	}

	protected void initialize(){
	}

	protected  void execute() {
	    Robot.driveTrain.drive(-Robot.oi.driverPad.getLeftY(),
	    		Robot.oi.driverPad.getLeftX(), 
	    		Robot.oi.driverPad.getRightX());
	  	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
  
  }

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	  	end();
	}
}
