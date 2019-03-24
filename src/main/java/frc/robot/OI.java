/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.Gamepad;
import frc.robot.commands.*;

public class OI {

  //We have functionality for two different controllers, a Logitech F310 and Xbox Controllers
  //These are basic object declarations for both of the controllers in OI.
  public Gamepad driverPad;
  public XboxController controller;

  public OI()
  {
   //driverPad = new Gamepad(0);

   //Initialize the controller to a specific port and set a deadzone.
   //The deadzone keeps the controller from reading analog inputs until you push it a certain distance.
   controller = new XboxController(0);
   controller.setDeadZone(0.15);

   //The commands and button declarations are called whenever these buttons are pressed.
   //They go into the commands and toggle the booleans to change for the solenoids.
   controller.a.whenPressed(new DunkyHands());
   controller.lb.whenPressed(new HatchyHands());
    
   //driverPad.getDPadUp().whenPressed(new DunkyHands());     
    
    //driverPad.getBottomButton().whenPressed(new HatchyHands());
    
    /*
    driverPad.getBottomButton().whenPressed(new RampSystem(true));
    driverPad.getLeftButton().whenPressed(new RampSystem(false));
    */

    //driverPad.getBottomButton().whileHeld(new MoveCatapult(1));
    //driverPad.getBottomButton().whenReleased(new MoveCatapult(0));
    
    /*
    if(!rampDown)
    { 
      if(rampButton)
      {
        rampDown = true;
        //rampOp= new RampPosition(1);
        //Set motor values here
      } else if(rampDown == true) {
          if(rampButton)
          {
          rampDown = true;
          //Set motor value here 
          //rampOp= new RampPosition(0);
        } 
      }
    }
    */

    //Leave this code here it works for the ramp

    /*
    driverPad.getLeftButton().whileHeld(new RampPosition(0.5));
    driverPad.getLeftButton().whenReleased(new RampPosition(0));

    driverPad.getTopButton().whileHeld(new RampPosition(-0.5));
    driverPad.getTopButton().whenReleased(new RampPosition(0));

    driverPad.getDPadUp().whileHeld(new WinchSpeed(1));
    driverPad.getDPadUp().whenReleased(new WinchSpeed(0));

    driverPad.getDPadDown().whileHeld(new WinchSpeed(-1));
    driverPad.getDPadDown().whenReleased(new WinchSpeed(0));
    */
  }
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}