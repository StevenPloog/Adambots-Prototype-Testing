package robot;

import edu.wpi.first.wpilibj.IterativeRobot;

public class RobotMain extends IterativeRobot {
    
    MagicBox box;
    FancyJoystick joy;
    
    public void robotInit() {
	box = new MagicBox();
	joy = new FancyJoystick(1);
    }
    
    public void autonomousPeriodic() {

    }
    
    public void teleopPeriodic() {
        
    }
    
    public void testPeriodic() {
    
    }
    
}
