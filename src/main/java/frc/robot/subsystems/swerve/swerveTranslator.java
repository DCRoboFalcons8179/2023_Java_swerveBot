package frc.robot.subsystems.swerve;

import frc.team88.swerve.SwerveController;
import frc.robot.k;

public class swerveTranslator {

    public SwerveController swerve;
    
    // Controls
    public double translationSpeedCmd = 0;
    public double rotationVelocityCmd = 0;
    public boolean isFieldCentric = false;
    
    private double translationSpeed = 0;
    private double rotationVelocity = 0;

    private void updateSpeeds(double translation, double rotation) {
        translationSpeed = translation * k.MAX_SPEED;
        rotationVelocity = rotation * k.MAX_ROTATION;
    }



    // Maintain

    public void scRoboInitial() {

        this.swerve = new SwerveController("swerve.toml");

        // Zero the gyro upon initialization.
        this.swerve.setGyroYaw(0);


        

    }

    public void scRoboPeriodic() {
        
        
        updateSpeeds(translationSpeedCmd, rotationVelocityCmd);
        this.swerve.update();
    }

    public void scAutoPeriodic() {

    // Set the swerve drive to just hold the current position of the modules without driving the
    // wheels.
    swerve.holdDirection();
    
}

    public void scTeleopInitial() {
        
    }


    public void scTeleopPeriodic() {

        // Set the translation speed and rotation velocities.
        swerve.setVelocity(translationSpeed, rotationVelocity);

        // Determine if the left stick is pressed enough to merit changing the direction.
        if (shouldChangeDirection()) {

    
          // Set the translation direction from the left stick.
          this.swerve.setTranslationDirection(this.calculateTranslationDirection(), isFieldCentric);
    
          // If we aren't changing translation direction, and we aren't commanding a significant speed
          // in
          // either translation or rotation, just hold the modules in their current position.
        } else if (translationSpeed < k.HOLD_DIRECTION_TRANSLATION_THRESHOLD
            && Math.abs(rotationVelocity) < k.HOLD_DIRECTION_ROTATION_THRESHOLD) {
          this.swerve.holdDirection();
        }

    }

    public void setManualSpeed() {

    }


    public void scDisableInitial() {

    }


    public void scDisablePeriodic() {

    }


    public void zeroGyro() {

        this.swerve.setGyroYaw(0);
    
    }

/**
   * Determines if the left stick is pressed out far enough to merit changing the translation
   * direction. If the joystick is close to the center, it is too difficult to control the
   * direction.
   *
   * @return True if the current translation direction should be changed, false if it should stay
   *     the same.
   */
  private boolean shouldChangeDirection() {

    double x = translationSpeedCmd;
    double y = rotationVelocityCmd;

    // Calculate the magnitude of the joystick position and use it as the threshold.
    return Math.sqrt(x * x + y * y) >= k.CHANGE_DIRECTION_THRESHOLD;
  }


  /**
   * Calculates the angle of translation set by the left stick.
   *
   * @return The angle of translation, in degrees. 0 corresponds to forwards, and positive
   *     corresponds to counterclockwise.
   */
  private double calculateTranslationDirection() {

    double x = translationSpeedCmd;
    double y = rotationVelocityCmd;

    // Calculate the angle. The variables are switched up because 0 degrees needs to be forwards.
    
    return Math.toDegrees(Math.atan2(x, y));
  }



}


