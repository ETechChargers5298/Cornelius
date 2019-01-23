/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalOutput;
import java.io.ByteArrayOutputStream;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

import java.io.IOException;
import java.util.ArrayList;


public class Lidar extends Subsystem {

  private static Counter lidar;
  private static DigitalOutput lidarTrigger;

  private static SerialPort rangeFinder;
  private static ByteArrayOutputStream rangeFinderBuffer;

  public Lidar()
  {
    lidar = new Counter(1);
    lidarTrigger = new DigitalOutput(0);

    rangeFinder = new SerialPort(115200, Port.kMXP);
    rangeFinderBuffer = new ByteArrayOutputStream();

    lidar.setSemiPeriodMode(true);
  }

  public void enableLidar() {
    lidarTrigger.set(false);
}

public void disableLidar() {
    lidarTrigger.set(true);
    lidar.reset();
}

public double getDisplacement() {
  return lidar.getPeriod() * 25400;   // TODO: Convert from m to ft.
}

 
    // Poll this method in Command.execute() or Command.getPIDInput().
    public Boolean rangeFinderReady() throws IOException {
      if(rangeFinderBuffer.size() < 16) {
          rangeFinderBuffer.write(rangeFinder.read(8));
          return false;
      }

      return true;
  }

  public Integer getHeight() {
      // Java is retarded.
      ArrayList<Integer> input = new ArrayList<Integer>();
      int height;

      // Convert byte array to int arrayList.
      for(byte b: rangeFinderBuffer.toByteArray())
          input.add(b & 0xFF);

      for(int i = 1; i < input.size(); i++) {
          // Look for header bytes and check for data bytes beyond.
          if((input.get(i - 1) == 0x59) && (input.get(i) == 0x59) && (input.size() - i > 2)) {
              rangeFinderBuffer.reset();
              return (input.get(i + 1) << 8) + input.get(i + 2);
          }
      }

      // Should not return null if you called rangeFinderReady() before.
      return null;
  }

  public void initDefaultCommand() {
  }
}