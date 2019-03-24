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
import edu.wpi.first.wpilibj.ADXRS450_Gyro;


public class DriveTrain extends Subsystem {

	//Here we declare a basic call to the motors so the program initializes 
	//The four Talon SRXs and Gyro from the beginning of program runtime. We will 
	//Declare the ports and objects in the constructor but these declarations will 
	//Allow us to declare that a static object exists and will be delcared later.
	private static WPI_TalonSRX frontRightMotor;
	private static WPI_TalonSRX frontLeftMotor;
	private static WPI_TalonSRX rearRightMotor;
	private static WPI_TalonSRX rearLeftMotor;

	private static ADXRS450_Gyro gyro;

	//These are global variables that will store the speed and normalisation value.
	//Normalisation adds the values passed in for linear, strafe and rotate. 
	//together and later divides them
	private double frontLeftSpeed;
	private double frontRightSpeed;
	private double rearLeftSpeed;
	private double rearRightSpeed;
	private double norm;

	public DriveTrain() {
		//This constructor declares the ports for the Talon SRXs and the gyro.
		//It also declares which motors are inverted and what the speeds at the beginning
		//Of runtime are supposed to be (START AT 0).
		frontLeftMotor = new WPI_TalonSRX(3);
		frontLeftMotor.setInverted(false);
		frontLeftMotor.set(0.0);
		
		rearLeftMotor = new WPI_TalonSRX(2);
		rearLeftMotor.setInverted(false);
		rearLeftMotor.set(0.0);

		rearRightMotor = new WPI_TalonSRX(1);
		rearRightMotor.setInverted(true);
		rearRightMotor.set(0.0);

		frontRightMotor = new WPI_TalonSRX(0);
		frontRightMotor.setInverted(true);
		frontRightMotor.set(0.0);

		gyro = new ADXRS450_Gyro();
		
		//Whenever the robot starts up, just calibrate the gyro and reset the value to 0.
		gyro.calibrate();
		resetGyro();

	}

	//This function calculates the speed of all four motors based on input from the gamepad
	//Generally in Drive Train Commands, we pass onputs from the left and right analog sticks.
	//We call functions like getLeftY() or getRightX() to get these values.
	public void calculateVelocities(double linear, double strafe, double rotate) {
		norm = linear + rotate + strafe;

		if (norm < 1.0) {
			norm = 1.0;
		}

		frontLeftSpeed = (linear + rotate + strafe) / norm;
		frontRightSpeed = (linear - rotate - strafe) / norm;
		rearLeftSpeed = (linear - rotate + strafe) / norm;
		rearRightSpeed = (linear + rotate - strafe) / norm;

		//System.out.println(frontLeftSpeed + frontRightSpeed + rearLeftSpeed + rearRightSpeed);
	}

	//Take the calculated speeds from the previous function and
	//Set the speeds to the Talon SRXs.
	public void moveRobot() {
		frontLeftMotor.set(frontLeftSpeed);
		rearLeftMotor.set(rearLeftSpeed);
		frontRightMotor.set(frontRightSpeed);
		rearRightMotor.set(rearRightSpeed);
	}

	//This function combines the previous two to constant calculate and set the speeds in the command class.
	//It passes in values from the joystick that will allow the robot to move in all directions.
	public void drive(double linearJoystick, double strafeJoystick, double rotateJoystick) {
		System.out.println("drive is being called");
		calculateVelocities(linearJoystick, strafeJoystick, rotateJoystick);
		moveRobot();
	}

	//Whenever called, the gyro will be calibrated to the direction the robot is facing.
	//All functions below this that pertain to the gyro are called with commands or in other classes
	//To update gyro values during teleop through button pressed. Some however are automatically called
	//At the beginning of certain commands like resetGyro everytime DriveTrainCommands runs.
	public void calibrateGyro()
	{
		gyro.calibrate();
	}

    public void resetGyro() {
        gyro.reset();
    }

    public double getAngle() {
        return gyro.getAngle();
    }

	//The default command for this class is DriveTrainCommands and we need to declare this so
	//The subsystem knows where to look for its basic command to run on.
	public void initDefaultCommand() {
		setDefaultCommand(new DriveTrainCommands());
	}
	
	//When this is called, set all speeds to zero!
	public void stop() {
		drive(0, 0, 0);
	}
}