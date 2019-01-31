/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {    //THIS IS A SUBSYSTEM FOR A SHOOTER USING WHEELS.

  private WPI_TalonSRX Shooter;

  public Shooter()
  {
    Shooter = new WPI_TalonSRX(5);  //Attaches a Talon for the code to reference. Here its Talon 5.
  }

  public void setWheelSpeed(double speed)
  {
    Shooter.set(speed);        //Setting a double for a speed precedent value. 
  }

  //Return the current status of the limit switch (If its been hit while by cascade)
  

  @Override
  public void initDefaultCommand() {

  }
}
