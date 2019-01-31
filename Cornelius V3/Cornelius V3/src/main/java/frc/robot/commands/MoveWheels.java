/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveWheels extends Command {
  

  public MoveWheels() {
    // Use requires() here to declare subsystem dependencies or in simpler words to tell the code to use the shooter subsystem.
    // eg. requires(chassis);
    requires(Robot.shooter);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
      Robot.shooter.setWheelSpeed(Robot.oi.driverPad.getRightY());  // This sets the shooter operation to the Right Joystick on its Y axis
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return (true);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.shooter.setWheelSpeed(0.0);           // When we let go of the joystick, it sets the speed to zero
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
