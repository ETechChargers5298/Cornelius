/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class DunkyBoy extends Subsystem {
 
  private Solenoid dunkerSolenoid;
  private Solenoid hatchSolenoid;
  private Solenoid rampSolenoid;

  private boolean isActivated;

  private Compressor c;

  public DunkyBoy()
  {
    c = new Compressor(0);
		c.setClosedLoopControl(true);

    hatchSolenoid = new Solenoid(0);
    dunkerSolenoid = new Solenoid(1);
    //rampSolenoid = new Solenoid(2);

    isActivated = false;
   }
  
  public void setRamp(boolean Value) {
    rampSolenoid.set(Value); 
  }

  public void dunkBall(boolean Value) {
    dunkerSolenoid.set(Value); 
  }

  public void grabHatch(boolean Value) {
    hatchSolenoid.set(Value); 
  }
  
  //THIS IS EXPERIMENTAL TOGGLE SWITCH CODE
  //TODO: TEST!!

  //This activates the hatch solenoid and pushes
  //the two hooks forwards

  public void toggleHatch() {
    isActivated = !isActivated;
    hatchSolenoid.set(isActivated); 
  }

  public void toggleRamp() {
    isActivated = !isActivated;
    rampSolenoid.set(isActivated); 
  }

  public void toggleBall() {
    isActivated = !isActivated;
    dunkerSolenoid.set(isActivated); 
  }
  
  public void initDefaultCommand() {
   // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
