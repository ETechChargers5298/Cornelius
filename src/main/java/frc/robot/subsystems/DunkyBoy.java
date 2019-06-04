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
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DunkyBoy extends Subsystem {
 
  //Here we declare the thee solenoids that will control various systems
  //We also need to declare the compressor that will supply the air to the solenoids.
  private Solenoid dunkerSolenoid;
  private Solenoid hatchSolenoid;
  private Solenoid wheelIntakeSolenoid;
  private DoubleSolenoid rampSolenoid;

  private static WPI_TalonSRX wheelIntake;

  private Compressor c;

  //This boolean is to determine if any of the solenoids will start off open or closed.
  //With solenoids, the values passed in aren't integer or double, the have to be booleans.
  private boolean hatchIsActivated;
  private boolean dunkIsActivated;
  private boolean rampIsActivated;
  private boolean wheelIntakeIsDown;

  public DunkyBoy()
  {
    c = new Compressor(0);
		c.setClosedLoopControl(true);

    hatchSolenoid = new Solenoid(2);
    dunkerSolenoid = new Solenoid(0);
    //wheelIntakeSolenoid = new Solenoid(7);

    
    //rampSolenoid = new DoubleSolenoid(5,7);

    //wheelIntake = new WPI_TalonSRX(5);

    //The isActivated variable starts false in order to keep all the pneumatic systems 
    //In their starting positions.
    hatchIsActivated = false;
    dunkIsActivated = false;
    wheelIntakeIsDown = false;
   }
  
  //Instead of toggling, we pass the values directly into these functions.
  //In the parameters of the command constructors, we pass a true or false value in
  //And then these functions are called to set the values manually. These were experimental
  //And were tested before we had the toggle switches working.
  public void setRampForward() {
    rampSolenoid.set(Value.kForward); 
  }
  
  public void setRampReverse() {
    rampSolenoid.set(Value.kReverse); 
  }

  public void dunkBall(boolean Value) {
    dunkerSolenoid.set(Value); 
  }

  public void grabHatch(boolean Value) {
    hatchSolenoid.set(Value); 
  }

  public void activateWheels(double speed)
  {
    wheelIntake.set(speed);
  }

  public void stopWheelRotation()
  {
    wheelIntake.set(0);
  }

  public void dropWheelIntake(boolean activate)
  {
    wheelIntakeSolenoid.set(activate);
  }
  
  //THIS IS EXPERIMENTAL TOGGLE SWITCH CODE
  //TODO: TEST!!

  //This activates the hatch solenoid and pushes
  //the two hooks forwards. When this section is called, it flips the 
  //Value of the isActivated to true and toggles each solenoid for use when called.
  public void toggleHatch() 
  {
    hatchIsActivated = !hatchIsActivated;
    hatchSolenoid.set(hatchIsActivated); 
  }

  public void toggleBall() 
  {
    dunkIsActivated = !dunkIsActivated;
    dunkerSolenoid.set(dunkIsActivated); 
  }

  public void toggleDropCargoIntake()
  {
    wheelIntakeIsDown = !wheelIntakeIsDown;
    wheelIntakeSolenoid.set(wheelIntakeIsDown);
  }
  
  /*
  public void toggleRamp() {
    rampIsActivated = !rampIsActivated;
    rampSolenoid.set(rampIsActivated); 
  }
  */
  
  public void initDefaultCommand() {
   //setDefaultCommand(new RampSystem());
  }
}
