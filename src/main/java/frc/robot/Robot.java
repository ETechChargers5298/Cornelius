/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

import edu.wpi.first.networktables.*;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DunkyBoy;
import frc.robot.subsystems.Lidar;
import frc.robot.subsystems.RampOp;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {

  //In this section, we declare basic objects thast will later be initialized in the robotInit function
  //It's important these remain static because these objects will last the whole runtime of the program.
  public static OI oi;
  public static Gamepad driverPad;
  public static DriveTrain DriveTrain;
  public static Lidar lidar;
  public static DunkyBoy slamDunk;
  public static RampOp ramp;

   //Here we declare the camera object and some basic parameters for the camera to function properly.
  //These include framerate and resolution for the camera.
  private UsbCamera camera;
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
  public static final int FPS = 20;
  

  //Although not useed, these can be used to declare autonomous commands that will be 
  //Initialized in the autonomousInit function. The sendable chooser grabs the specific
  //Command you want to run for the autonomous period.
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    //In order for the robot to know what subsystems exist for the runtime of the robot,
    //We initialize the objects in here and this allows the robot to see the subsystem
    //And everything we declared inside of them such as Talon SRXs.

    //ramp = new RampOp();
    //winch = new Winch();
    slamDunk = new DunkyBoy();
    DriveTrain = new DriveTrain();

    oi = new OI();

       //In this section, we initialize the camera to a USB port and set the parameters declared before 
    //Such as resolution and framerate.
    camera = CameraServer.getInstance().startAutomaticCapture(0);
		camera.setResolution(WIDTH, HEIGHT);
		camera.setFPS(FPS);
    //lidar = new Lidar();

    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
 
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    
    }


    //lidar.enableLidar();

  }

  //This function was declared to put numbers into the Smartdashboard and constantly update these values.
  public void updateSmartDashboard()
  {
    SmartDashboard.putNumber("Lidar Value", lidar.getDisplacement());

  }
  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    boolean trigger = oi.xboxDrive.b.get();

    if(trigger)
    {
      
    }

  }

  /** 
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public void updateLimelight()
  {
      final double STEER_K = 0.03;                  
      final double DRIVE_K = 0.26;                   
      final double DESIRED_TARGET_AREA = 13.0;        
      final double MAX_DRIVE = 0.7;                  

      double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
      double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
      double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
      double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

      if (tv < 1.0)
      {
        m_LimelightHasValidTarget = false;
        m_LimelightDriveCommand = 0.0;
        m_LimelightSteerCommand = 0.0;
        return;
      }

      m_LimelightHasValidTarget = true;

      // Start with proportional steering
      double steer_cmd = tx * STEER_K;
      m_LimelightSteerCommand = steer_cmd;

      // try to drive forward until the target area reaches our desired area
      double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

      // don't let the robot drive too fast into the goal
      if (drive_cmd > MAX_DRIVE)
      {
        drive_cmd = MAX_DRIVE;
      }
      m_LimelightDriveCommand = drive_cmd;
  }
}
