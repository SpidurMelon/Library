package en.lib.math;

import java.awt.Point;

public class MathUtils {
	public static long toMegaBytes(long bytes) {
		return (long)(bytes/Math.pow(1024, 2));
	}
	
	public static int randInt(int low, int high) {
		return (int)(low+Math.random()*((high-low)+1));
	}
	
	public static double roundToDecimals(double number, int decimals) {
		return Math.round(number*Math.pow(10, decimals))/Math.pow(10, decimals);
	}
	/**
	 * Returns the distance between 2 points
	 * @param p1
	 * Point 1
	 * @param p2
	 * Point 2
	 * @return
	 * The distance between point 1 and point 2, or -1 if p1 or p2 are null
	 */
	public static double distPoints(Point p1, Point p2) {
		if (p1 == null || p2 == null) {
			return -1;
		}
		double dx = p1.getX()-p2.getX();
		double dy = p1.getY()-p2.getY();
		return Math.sqrt(dx*dx+dy*dy);
	}
}
