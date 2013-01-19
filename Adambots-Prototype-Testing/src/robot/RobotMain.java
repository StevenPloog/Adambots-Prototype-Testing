package robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotMain extends IterativeRobot {
    
    final int MOTOR_1 = 5;
    final int MOTOR_2 = 7;
    
    MagicBox box;
    FancyJoystick joy;
    
    Victor motor1;
    Victor motor2;
    
    DriverStation ds;
    
    double currentTrigger;
    double holdTriggerValue;
    double setMotor1;
    double setMotor2;
    
    boolean holdReleased = true;
    boolean useHoldValue = false;
    
    public void robotInit() {
	box = new MagicBox();
	joy = new FancyJoystick(1);
	ds = DriverStation.getInstance();
	
	motor1 = new Victor(MOTOR_1);
	motor2 = new Victor(MOTOR_2);
	currentTrigger = 0;
    }
    
    public void teleopPeriodic() {
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
    
    public void autonomousPeriodic() {}
    
    public void testPeriodic() {}
    
}
