package frc.robot.commands; 

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class DriveTrainCommands extends Command {

	public DriveTrainCommands() {
		//This class requires the use of the DriveTrain class to call its functions.
		//We have declared an object for the required class in the main Robot class.
		requires(Robot.DriveTrain);
	}

	protected void initialize(){
	
	}

	protected  void execute() {
		/*
	    Robot.DriveTrain.drive(-Robot.oi.driverPad.getLeftY(),
				Robot.oi.driverPad.getLeftX(), 
				Robot.oi.driverPad.getRightX());
		*/
		//This calles the drive function from DriveTrain to set the speeds to the
		//Analog stick inputs from the gamepad (In this case it is an Xbox Controller).
		 Robot.DriveTrain.drive(Robot.oi.controller.leftStick.getY(),
				Robot.oi.controller.leftStick.getX(), 
				Robot.oi.controller.rightStick.getX());

		//Upon every use, the commands cycle back through the init then execute and isFinished.
		//Evertime the command class cycles, just reset the gyro value to zero to correct the direction.		
		Robot.DriveTrain.resetGyro();
	}
		  

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//DriveTrainCommands never stops being called so it returns false.
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
