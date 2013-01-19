package robot;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * This class will make it easier to access the digital and analog inputs
 * of the Cypress board. They are accessed through 
 * DriverStation.getDigital/AnalogInput
 * 
 * This class should be used as a helper for a particular robot's magic box.
 * 
 * @author Steven
 */
public class MagicBox extends DriverStation {
    
    //// MAGIC BOX CONSTANTS ---------------------------------------------------
    
    //// MAGIC BOX VARIABLES ---------------------------------------------------
    
    private DriverStation _ds;
    
    //// BODY OF CLASS ---------------------------------------------------------
    
    public MagicBox() {
	_ds = DriverStation.getInstance();
    }
    
    /**
     * This method returns an inverted digital input from a Cypress board.
     * @param channel The Cypress board channel to check.
     * @return Inverted digital input from Cypress channel "channel."
     */
    public boolean getInvertedDigitalIn(int channel) {
	return !_ds.getDigitalIn(channel);
    }
    
    /**
     * Returns a value between 0 and 1 for an analog input.
     * @param channel The channel to read
     * @param maxValue The maximum value of the channel
     * @return The value of the analog channel adjusted between 0 and 1.
     */
    public double getScaledAnalogIn(int channel, double maxValue) {
	return (maxValue - _ds.getAnalogIn(channel)) / maxValue;
    }
}
