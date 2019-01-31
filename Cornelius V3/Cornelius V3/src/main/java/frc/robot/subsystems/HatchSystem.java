/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.HatchRetreat;
//import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

/**                                                                         //THIS IS A HATCH SYSTEM USING A SOLENOID
 * Add your docs here.
 */
public class HatchSystem extends Subsystem {       // Just attaches a solenoid to the Hatch subsystem.
  Solenoid Solenoid = new Solenoid(1);
  
  
  //Compressor Compressor = new Compressor(3);

 public void Release(boolean Value) {  // Using a boolean value for solenoid is easier. All boolean means is a choice of true or false.
  Solenoid.set(Value);
 }

 public void Retreat(boolean Value) {
   Solenoid.set(Value);
 }
  public void initDefaultCommand() {
    setDefaultCommand(new HatchRetreat());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
