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
 
  //Here we declare the thee solenoids that will control various systems
  //We also need to declare the compressor that will supply the air to the solenoids.
  private Solenoid dunkerSolenoid;
  private Solenoid hatchSolenoid;
  private Solenoid rampSolenoid;

  private Compressor c;

  //This boolean is to determine if any of the solenoids will start off open or closed.
  //With solenoids, the values passed in aren't integer or double, the have to be booleans.
  private boolean isActivated;

  public DunkyBoy()
  {
    c = new Compressor(0);
		c.setClosedLoopControl(true);

    hatchSolenoid = new Solenoid(0);
    dunkerSolenoid = new Solenoid(1);
    //rampSolenoid = new Solenoid(2);

    //The isActivated variable starts false in order to keep all the pneumatic systems 
    //In their starting positions.
    isActivated = false;
   }
  
  //Instead of toggling, we pass the values directly into these functions.
  //In the parameters of the command constructors, we pass a true or false value in
  //And then these functions are called to set the values manually. These were experimental
  //And were tested before we had the toggle switches working.
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
  //the two hooks forwards. When this section is called, it flips the 
  //Value of the isActivated to true and toggles each solenoid for use when called.
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
