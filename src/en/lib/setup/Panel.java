package en.lib.setup;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * A JPanel with an added tick and draw method
 * @author SpidurMelon
 */
public abstract class Panel extends JPanel {
	public static boolean trackTPS = true;
	public long currentTPSSecond = System.currentTimeMillis()/1000;
	public int ticksThisSecond = 0, TPS = 0;
	
	public static boolean trackFPS = true;
	public long currentFPSSecond = System.currentTimeMillis()/1000;
	public int framesThisSecond = 0, FPS = 0;
	
	public final void executeTick(double delta) {
		tick(delta);
		
		if (trackTPS) {
			if (System.currentTimeMillis()/1000 != currentTPSSecond) {
				TPS = ticksThisSecond;
				ticksThisSecond = 0;
				currentTPSSecond = System.currentTimeMillis()/1000;
			} 
			ticksThisSecond++;
		}
	}
	
	public final void paintComponent(Graphics g) {
		draw((Graphics2D)g);
		
		if (trackFPS) {
			if (System.currentTimeMillis()/1000 != currentFPSSecond) {
				FPS = framesThisSecond;
				//System.out.println("FPS:" + FPS);
				framesThisSecond = 0;
				currentFPSSecond = System.currentTimeMillis()/1000;
			} 
			framesThisSecond++;
		}
	}
	
	public abstract void tick(double delta);
	public abstract void draw(Graphics2D g2);
	
}
