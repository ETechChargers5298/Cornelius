package frc.robot.commands; 

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class DriveTrainCommands extends Command {

	public DriveTrainCommands() {
		requires(Robot.DriveTrain);
	}

	protected void initialize(){
	
	}

	protected  void execute() {
	    Robot.DriveTrain.drive(Robot.oi.driverPad.getLeftY(),
				Robot.oi.driverPad.getRightX(), 
				Robot.oi.driverPad.getLeftX());
		
		Robot.DriveTrain.resetGyro();

	
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
