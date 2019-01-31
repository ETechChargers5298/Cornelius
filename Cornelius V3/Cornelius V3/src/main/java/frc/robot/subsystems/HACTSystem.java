/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.HatchRetreat;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//import edu.wpi.first.wpilibj.Compressor;


/**
 * Add your docs here.
 */
public class HACTSystem extends Subsystem {       //THIS IS FOR AN HATCH SYSTEM USING A LINEAR ACTUATOR
  private WPI_TalonSRX HACTSystem;

  public HACTSystem() {  
    HACTSystem = new WPI_TalonSRX(1);    //This just attaches the subsystem to a Talon. In this case Talon 1
  }
  
 public void HACTPush(double speed) {   // This sets a precedent value for the command classes to use. 
    HACTSystem.set(speed);              //They are doubles meaning a number with a certain amount of decimal places.
 }
 public void HACTPull(double speed) { //^^^
    HACTSystem.set(speed);
 }
  public void initDefaultCommand() {
    setDefaultCommand(new HatchRetreat());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
