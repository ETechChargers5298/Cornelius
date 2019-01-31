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

	public DriveTrain() {
		frontLeftMotor = new WPI_TalonSRX(4);
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

	public void moveRobot() {
		frontLeftMotor.set(Math.pow(frontLeftSpeed, 3));
		rearLeftMotor.set(Math.pow(rearLeftSpeed, 3));
		frontRightMotor.set(Math.pow(frontRightSpeed, 3));
		rearRightMotor.set(Math.pow(rearRightSpeed, 3));
	}

	public void drive(double linearJoystick, double strafeJoystick, double rotateJoystick) {
		System.out.println("drive is being called");
		calculateVelocities(linearJoystick, strafeJoystick, rotateJoystick);
		moveRobot();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveTrainCommands());
	}

	public void stop() {
		drive(0, 0, 0);
	}
}