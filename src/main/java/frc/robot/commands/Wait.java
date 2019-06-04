/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command {
	
	public double startTime;
	public double maxTime;
	private boolean finished;
	
	public Wait(double time) {
		requires(Robot.DriveTrain);
		maxTime = time;
	}
	
	public void initialize() {
		startTime = Timer.getFPGATimestamp();
	}
	
	public void execute() {
    Robot.DriveTrain.drive(0.0, 0.0, 0.0);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if (Timer.getFPGATimestamp() - startTime >= maxTime) {
			finished = true;
		}
		return finished;
	}

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }	
}