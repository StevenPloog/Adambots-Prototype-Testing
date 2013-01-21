/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends SimpleRobot {
    
    FancyJoystick joy;
    
    Victor motor1;
    Victor motor2;
    
    DriverStation ds;
    
    double currentTrigger;
    double holdTriggerValue;
    double setMotor1;
    double setMotor2;
    
    final int MOTOR_1 = 5;
    final int MOTOR_2 = 7;
    
    boolean holdReleased = true;
    boolean useHoldValue = false;
    
    public void robotInit() {
	System.out.println("Initiailized");
	joy = new FancyJoystick(1);
	motor1 = new Victor(MOTOR_1);
	motor2 = new Victor(MOTOR_2);
	ds = DriverStation.getInstance();
	currentTrigger = 0;
    }
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {}

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
	while (true) {
	    currentTrigger = joy.getDeadAxis(FancyJoystick.AXIS_TRIGGERS);

	    if (holdReleased && joy.getRawButton(FancyJoystick.BUTTON_A)) {
		holdTriggerValue = currentTrigger;
		useHoldValue = !useHoldValue;
		holdReleased = false;
	    } else if (!joy.getRawButton(FancyJoystick.BUTTON_A)) {
		holdReleased = true;
	    }
	    
	    if (!ds.getDigitalIn(8)) {
		setMotor1 = 0;
		setMotor2 = 0;
	    } else if (!ds.getDigitalIn(7)) {
		setMotor1 = (2.05 - ds.getAnalogIn(1)) / 2.05;
                setMotor2 = (2.05 - ds.getAnalogIn(2)) / 2.05;
		
		if (-.05 < setMotor1 && setMotor1 < .05)
		    setMotor1 = 0;
		if (-.05 < setMotor2 && setMotor2 < .05)
		    setMotor2 = 0;
	    } else {
		if (useHoldValue) {
		    setMotor1 = holdTriggerValue;
		    setMotor2 = holdTriggerValue;
		} else {
		    setMotor1 = currentTrigger;
		    setMotor2 = currentTrigger;
		}
	    }
	    
	    if (!ds.getDigitalIn(5)) {
		setMotor1 = -setMotor1;
		setMotor2 = -setMotor2;
	    }
	    
	    motor1.set(setMotor1);
	    motor2.set(setMotor2);
	    
	    SmartDashboard.putNumber("HoldValue", holdTriggerValue);
	    SmartDashboard.putNumber("CurrentTrigger", currentTrigger);
	    SmartDashboard.putNumber(("Motor1Value"), setMotor1);
	    SmartDashboard.putNumber(("Motor2Value"), setMotor2);
	    DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser1, 1, "Hello World");
	}
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
