// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//Karol was here(and jude)

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C.Port;
import frc.robot.subsystems.swerve.swerveTranslator;
import frc.team88.swerve.SwerveController;
import frc.team88.swerve.gyro.NavX;
import frc.team88.swerve.gyro.SwerveGyro;

/**
 * Robot using Team 88's SwerveLibrary, using the TimedRobot framework without command-based
 * programming. The controller in use is an XBox controller, with the following mapping:
 *
 * <p>Left stick - Steer the direction of translation. Does not set speed. Right trigger - Sets the
 * translation speed of the robot. Right stick X - Spin the robot about it's center. Right bumper -
 * Hold for robot-centric steering. Otherwise, steering isn field-centric. Y button - Zeros the gyro
 * so that the robot is facing forwards. Only works while disabled.
 */
public class Robot extends TimedRobot {

  private swerveTranslator sc;


  // The xBox gamepad.
  private Joystick gamepad = new Joystick(0);




  @Override
  public void robotInit() {


    gamepad = new Joystick(k.GAMEPAD_PORT);

    AHRS navx = new AHRS(Port.kMXP);

    SwerveGyro swerveGyro = new NavX();

    SwerveController swerveController = new SwerveController("swerve.toml", swerveGyro);


  }

  @Override
  public void robotPeriodic() {

    

  }

  @Override
  public void disabledPeriodic() {
    // Y button zeros the gyro, which tells the robot that it is currently facing forwards.
    if (gamepad.getRawButton(4)) {
      sc.zeroGyro();
    }

    sc.scDisablePeriodic();
  }

  @Override
  public void autonomousPeriodic() {


    sc.scAutoPeriodic();

  }




  @Override
  public void teleopPeriodic() {
    // Get the translation speed from the right trigger, scaled linearly so that fully pressed
    // commands our max speed in feet per second. Because the triggers on the XBox controller
    // actually go to zero when released, no deadband is needed.
    sc.translationSpeedCmd = deadband(this.gamepad.getRawAxis(3));

    // Get the rotation velocity from the right stick X axis, scaled linearly so that fully pushed
    // commands our max rotation speed in rotations per second. Uses a deadband since XBox
    // controller joysticks don't get exactly to zero when released.
    sc.rotationVelocityCmd = deadband(this.gamepad.getRawAxis(4));


    // The translation direction is field-centric if the right bumper is not pressed.
    sc.isFieldCentric = !this.gamepad.getRawButton(6);


    

    

  }

  @Override
  public void testPeriodic() {
    // Set the swerve drive to just hold the current position of the modules without driving the
    // wheels.
  }


  public double deadband (double in) {
    if (Math.abs(in) < k.JOYSTICK_DEADBAND) {
      return 0;
    }
    return in;
  }
}