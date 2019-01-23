/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.WinchSpeed;

/**
 * Add your docs here.
 */
public class Winch extends Subsystem {
 
  public static WPI_TalonSRX winchTalon;

  public Winch()
  {
    winchTalon = new WPI_TalonSRX(1);
  }

  public void winchSpeed(double speed)
  {
    winchTalon.set(speed);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new WinchSpeed(0));
  }
}
