package frc.robot.commands; 

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.networktables.*;


public class DriveTrainCommands extends Command {
	
	private boolean m_LimelightHasValidTarget = false;
	private double m_LimelightDriveCommand = 0.0;
	private double m_LimelightSteerCommand = 0.0;
  
	private double tv;
	private double tx;
	private double ty;
	private double ta;

	public DriveTrainCommands() {
		//This class requires the use of the DriveTrain class to call its functions.
		//We have declared an object for the required class in the main Robot class.
		requires(Robot.DriveTrain);
	}

	public void updateLimelight()
	{
		final double STEER_K = 0.03;                  
		final double DRIVE_K = 0.26;                   
		final double DESIRED_TARGET_AREA = 13.0;        
		final double MAX_DRIVE = 0.5;                  
  
		tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
		tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
		ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
		ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
  
		if (tv < 1.0)
		{
		  m_LimelightHasValidTarget = false;
		  m_LimelightDriveCommand = 0.0;
		  m_LimelightSteerCommand = 0.0;
		  return;
		}
  
		m_LimelightHasValidTarget = true;
  
		// Start with proportional steering
		double steer_cmd = tx * STEER_K;
		m_LimelightSteerCommand = steer_cmd;
  
		// try to drive forward until the target area reaches our desired area
		double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;
  
		// limits the speed of the robot going forward
		if (drive_cmd > MAX_DRIVE)
		{
		  drive_cmd = MAX_DRIVE;
		}
		m_LimelightDriveCommand = drive_cmd;
	}

	protected void initialize(){
		
	}

	protected  void execute() {
		/*
	    Robot.DriveTrain.drive(-Robot.oi.driverPad.getLeftY(),
				Robot.oi.driverPad.getLeftX(), 
				Robot.oi.driverPad.getRightX());
		*/

		//updateLimelight();
		

		//This calles the drive function from DriveTrain to set the speeds to the
		//Analog stick inputs from the gamepad (In this case it is an Xbox Controller).
		 Robot.DriveTrain.drive(Robot.oi.xboxDrive.leftStick.getY(),
				Robot.oi.xboxDrive.rightStick.getX(), 
				Robot.oi.xboxDrive.leftStick.getX());

				
		/*
				boolean trigger = Robot.oi.xboxDrive.b.get();
			
				if(trigger)
				{
				  if(m_LimelightHasValidTarget)
				  {
					Robot.DriveTrain.steerInPlace(m_LimelightSteerCommand);
					System.out.println("Valid target has been acquired");
				  } else {
					Robot.DriveTrain.stop();
				  }
				} else {
					Robot.DriveTrain.drive(Robot.oi.xboxDrive.leftStick.getY(),
										   Robot.oi.xboxDrive.rightStick.getX(), 
										   Robot.oi.xboxDrive.leftStick.getX());
				}
			*/
		//Upon every use, the commands cycle back through the init then execute and isFinished.
		//Evertime the command class cycles, just reset the gyro value to zero to correct the direction.		
		//Robot.DriveTrain.resetGyro();
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
