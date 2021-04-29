package en.lib.math;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * A mathematical vector
 * @author SpidurMelon
 */
public class Vector {
	private double direction, size;
	
	/**
	 * Creates an empty vector with direction=0 and size=0
	 * 
	 */
	public Vector() {
		this(0, 0);
	}
	
	/**
	 * Creates a vector with the given values
	 * @param direction
	 * The direction of the vector in degrees, with 0 degrees being right and 90 being straight down
	 * @param size
	 * The size of the vector
	 */
	public Vector(double direction, double size) {
		this.direction = direction;
		this.size = size;
	}
	
	public double getDirection() {
		return direction;
	}
	
	/**
	 * @return
	 * The x component of this vector
	 */
	public double getXComp() {
		return Math.cos(Math.toRadians(direction))*size;
	}
	
	/**
	 * @return
	 * The y component of this vector
	 */
	public double getYComp() {
		return Math.sin(Math.toRadians(direction))*size;
	}
	
	public double getSize() {
		return size;
	}
	
	public void setDirection(double newDirection) {
		this.direction = newDirection;
	}
	
	public void setSize(double newSize) {
		this.size = newSize;
	}
	
	/**
	 * Returns the combined vector of this and another vector
	 * @param v2
	 * The vector to be added to this one
	 * @return 
	 * The combined vector of this and v2
	 */
	public Vector addVector(Vector v2) {
		double xComp1 = this.getXComp();
		double yComp1 = this.getYComp();
		double xComp2 = v2.getXComp();
		double yComp2 = v2.getYComp();
		double totalXComp = MathUtils.roundToDecimals(xComp1+xComp2, 6);
		double totalYComp = MathUtils.roundToDecimals(yComp1+yComp2, 6);
		double newDirectionCoefficient = totalYComp/totalXComp;
		double newDirection = 0.0;
		if (totalXComp >= 0) {
			newDirection = Math.toDegrees(Math.atan(newDirectionCoefficient));
		} else {
			newDirection = Math.toDegrees(Math.atan(newDirectionCoefficient))+180;
		}
		double newSize = Math.sqrt(Math.pow(xComp1+xComp2, 2)+Math.pow(yComp1+yComp2, 2));
		return new Vector(newDirection, newSize);
	}
	/**
	 * A method for getting the direction it would take for a point to get to another
	 * @param p1
	 * Point one
	 * @param p2
	 * Point two
	 * @return
	 * The direction it would take for p1 to get to p2
	 */
	public static double getVectorDirection(Point p1, Point p2) {
		double x1 = p1.getX();
		double x2 = p2.getX();
		double xDif = x2-x1;
		
		double y1 = p1.getY();
		double y2 = p2.getY();
		double yDif = y2-y1;
		
		double directionCoefficient = yDif/xDif;
		double direction = 0;
		if (xDif > 0) {
			direction = Math.toDegrees(Math.atan(directionCoefficient));
		} else if (xDif < 0) {
			direction = Math.toDegrees(Math.atan(directionCoefficient))+180;
		} else {
			if (directionCoefficient > 0) {
				direction = 90.0;
			} else {
				direction = 270.0;
			}
		}
		return direction;
	}
	
	/**
	 * A method for getting the direction it would take for a point to get to another
	 * @param p1
	 * Point one
	 * @param p2
	 * Point two
	 * @return
	 * The direction it would take for p1 to get to p2
	 */
	public static double getVectorDirection(Point2D.Double p1, Point2D.Double p2) {
		double x1 = p1.getX();
		double x2 = p2.getX();
		double xDif = x2-x1;
		
		double y1 = p1.getY();
		double y2 = p2.getY();
		double yDif = y2-y1;
		
		double directionCoefficient = yDif/xDif;
		double direction = 0;
		if (xDif > 0) {
			direction = Math.toDegrees(Math.atan(directionCoefficient));
		} else if (xDif < 0) {
			direction = Math.toDegrees(Math.atan(directionCoefficient))+180;
		} else {
			if (directionCoefficient > 0) {
				direction = 90.0;
			} else {
				direction = 270.0;
			}
		}
		return direction;
	}
	
	public Vector clone() {
		return new Vector(this.direction, this.size);
	}
	
	public void drawVector(double x, double y, Graphics2D g2) {
		g2.drawLine((int)x, (int)y, (int)(x+getXComp()*0.8), (int)(y+getYComp()*0.8));
		g2.setColor(Color.RED);
		g2.drawLine((int)(x+getXComp()*0.8), (int)(y+getYComp()*0.8), (int)(x+getXComp()), (int)(y+getYComp()));
	}
}
