/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.RampPosition;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Servo;

public class Shooter extends Subsystem {
  private Solenoid Pusher;
  private Servo Servo;

  private Compressor c;

  public Shooter()
  {
    c = new Compressor(0);
		c.setClosedLoopControl(true);

    Pusher = new Solenoid(1);
    Servo.setAngle(0);
   }

 public void pushBall(boolean Value) {
   Pusher.set(Value); 
 }

 public void setRampPosition(int angle)
 {
    Servo.setAngle(angle);
 }

  public void initDefaultCommand() {
    setDefaultCommand(new RampPosition(0));
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
