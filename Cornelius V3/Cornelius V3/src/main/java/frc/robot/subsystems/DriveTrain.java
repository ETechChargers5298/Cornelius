/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.DriveTrainCommands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveTrain extends Subsystem {

	private static WPI_TalonSRX frontRightMotor;
	private static WPI_TalonSRX frontLeftMotor;
	private static WPI_TalonSRX rearRightMotor;
	private static WPI_TalonSRX rearLeftMotor;


	private double frontLeftSpeed;
	private double frontRightSpeed;
	private double rearLeftSpeed;
	private double rearRightSpeed;
	private double norm;

	public DriveTrain() {						//All this code is doing here is just mapping out the motors using the Talons. 
		frontLeftMotor = new WPI_TalonSRX(4);	//They are labeled as well for better organization
		frontLeftMotor.setInverted(false);
		frontLeftMotor.set(0.0);
		
		rearLeftMotor = new WPI_TalonSRX(0);
		rearLeftMotor.setInverted(false);
		rearLeftMotor.set(0.0);

		rearRightMotor = new WPI_TalonSRX(3);
		rearRightMotor.setInverted(true);
		rearRightMotor.set(0.0);

		frontRightMotor = new WPI_TalonSRX(2);
		frontRightMotor.setInverted(true);
		frontRightMotor.set(0.0);

	}

	public void calculateVelocities(double linear, double strafe, double rotate) {				//This is a more complicated way of precision driving for the robot.
		norm = linear + rotate + strafe;														//It takes the joystick inputs and performs calculations on the desired speed
		if (norm < 1.0) {																		//It then stores the calculated values in these variables to be set later in
			norm = 1.0;																			//the moveRobot() function
		}

		frontLeftSpeed = (linear + rotate + strafe) / norm;
		frontRightSpeed = (linear - rotate - strafe) / norm;
		rearLeftSpeed = (linear - rotate + strafe) / norm;
		rearRightSpeed = (linear + rotate - strafe) / norm;

		//System.out.println(frontLeftSpeed + frontRightSpeed + rearLeftSpeed + rearRightSpeed);
	}

	public void moveRobot() {									//This just sets the motors speed after the calculations above have been done. 
		frontLeftMotor.set(Math.pow(frontLeftSpeed, 3));
		rearLeftMotor.set(Math.pow(rearLeftSpeed, 3));
		frontRightMotor.set(Math.pow(frontRightSpeed, 3));
		rearRightMotor.set(Math.pow(rearRightSpeed, 3));
	}

	public void drive(double linearJoystick, double strafeJoystick, double rotateJoystick) {               //This calls or tells the code to execute everything that had been typed
		System.out.println("drive is being called");														//above. 
		calculateVelocities(linearJoystick, strafeJoystick, rotateJoystick); 
		moveRobot();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveTrainCommands());
	}

	public void stop() {       //This just sets everything to zero when the robot stops.
		drive(0, 0, 0); 
	}
}