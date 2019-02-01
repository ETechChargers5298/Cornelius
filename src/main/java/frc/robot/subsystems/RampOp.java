/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Servo;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class RampOp extends Subsystem {

  private Servo Servo;

  private static WPI_TalonSRX testMotor;

  public RampOp()
  {
   
    testMotor = new WPI_TalonSRX(1);

    Servo = new Servo(2);
    Servo.setAngle(0);
  }

  public void setServoAngle(double angle)
  {
    Servo.setAngle(angle);
  }

  public void setSpeed(double speed)
  {
    testMotor.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new MySpecialCommand());
  }
}
