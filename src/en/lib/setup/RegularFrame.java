package en.lib.setup;

import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * A standard frame for any purpose
 * @author SpidurMelon
 */
public class RegularFrame extends JFrame {
	public RegularFrame(int width, int height, boolean borderless) {
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(borderless);
		setLayout(null);
		setBounds(0, 0, width, height);
		setVisible(true);
		Tick.start();
	}
	
	/**
	 * Adds a panel to the frame and to the tickList, then packs the frame
	 * @param p
	 * Panel to add
	 */
	public void addPanel(Panel p) {
		synchronized(Tick.tickList) {
			Tick.tickList.add(p);
		}
		add(p);
		//pack();
	}
	
	public void removePanel(Panel p) {
		synchronized(Tick.tickList) {
			Tick.tickList.remove(p);
		}
		remove(p);
		//pack();
	}
}
