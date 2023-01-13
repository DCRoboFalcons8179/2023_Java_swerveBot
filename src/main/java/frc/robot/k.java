package frc.robot;

public final class k {

    // The max translation and rotation speeds to be commanded to the robot.
     public static final double MAX_SPEED = 14.7; // feet per second
     public static final double MAX_ROTATION = 90.; // degrees per second


    // port of the XBox gamepad.
    public static final int GAMEPAD_PORT = 0;

    // deadband to use for XBox gamepad joysticks.
    public static final double JOYSTICK_DEADBAND = 0.1;

    // threshold for left stick magnitude to change translation direction.
    public  static final double CHANGE_DIRECTION_THRESHOLD = 0.25;

    // speed thresholds for hold modules in their current direction.
    public  static final double HOLD_DIRECTION_TRANSLATION_THRESHOLD = 0.1;
    public  static final double HOLD_DIRECTION_ROTATION_THRESHOLD = 1;
    
}
