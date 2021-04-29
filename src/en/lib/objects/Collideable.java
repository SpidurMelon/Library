package en.lib.objects;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import en.lib.math.Vector;

public class Collideable extends Rectangle2D.Double {
	public Collideable() {
		this(0,0,0,0);
	}
	
	public Collideable(int width, int height) {
		this(0,0,width,height);
	}
	
	public Collideable(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public int getXInt() {
		return (int)x;
	}
	
	public int getYInt() {
		return (int)y;
	}
	
	public int getWidthInt() {
		return (int)width;
	}
	
	public int getHeightInt() {
		return (int)height;
	}
	
	private Point getCenter() {
		return new Point((int)(x+width/2),(int)(y+height/2));
	}
	
	public Vector pathOfLeastResistance(Collideable staticObj) {
		if (intersects(staticObj)) {
			double maxAllowedDistanceX = this.width/2.0+staticObj.width/2.0;
			double currentDistanceX = Math.abs(staticObj.getCenter().getX()-this.getCenter().getX());
			double separationDX = maxAllowedDistanceX-currentDistanceX;
			
			double maxAllowedDistanceY = this.height/2.0+staticObj.height/2.0;
			double currentDistanceY = Math.abs(staticObj.getCenter().getY()-this.getCenter().getY());
			double separationDY = maxAllowedDistanceY-currentDistanceY;
			
			if (separationDX < separationDY) {
				if (getCenter().getX() > staticObj.getCenter().getX()) {
					return new Vector(0, separationDX);
				} else {
					return new Vector(180, separationDX);
				}
			} else {
				if (getCenter().getY() > staticObj.getCenter().getY()) {
					return new Vector(90, separationDY);
				} else {
					return new Vector(270, separationDY);
				}
			}
		}
		return new Vector(0, 0);
	}
	
	public Vector pathOfNthResistance(Collideable staticObj, int N) {
		return pathOfResistances(staticObj)[N];
	}
	
	public Vector[] pathOfResistances(Collideable staticObj) {
		if (intersects(staticObj)) {
			double maxAllowedDistanceX = this.width/2.0+staticObj.width/2.0;
			double currentDistanceX = Math.abs(staticObj.getCenter().getX()-this.getCenter().getX());
			double separationDX = maxAllowedDistanceX-currentDistanceX;
			
			double maxAllowedDistanceY = this.height/2.0+staticObj.height/2.0;
			double currentDistanceY = Math.abs(staticObj.getCenter().getY()-this.getCenter().getY());
			double separationDY = maxAllowedDistanceY-currentDistanceY;
			
			Vector[] result = new Vector[4];
			boolean upDisabled = false, rightDisabled = false, downDisabled = false, leftDisabled = false;
			for (int i = 0; i < 4; i++) {
				if (separationDX < separationDY) {
					if (getCenter().getX() > staticObj.getCenter().getX() && !rightDisabled || leftDisabled) {
						result[i] = new Vector(0, separationDX);
						if (!leftDisabled) {
							separationDX = staticObj.width+this.width-separationDX;
						} else {
							separationDX = 1000000;
						}
						rightDisabled = true;
					} else if (!leftDisabled) {
						result[i] = new Vector(180, separationDX);
						if (!rightDisabled) {
							separationDX = staticObj.width+this.width-separationDX;
						} else {
							separationDX = 1000000;
						}
						leftDisabled = true;
					}
				} else {
					if (getCenter().getY() > staticObj.getCenter().getY() && !downDisabled || upDisabled) {
						result[i] = new Vector(90, separationDY);
						if (!upDisabled) {
							separationDY = staticObj.height+this.height-separationDY;
						} else {
							separationDY = 1000000;
						}
						downDisabled = true;
					} else if (!upDisabled) {
						result[i] = new Vector(270, separationDY);
						if (!downDisabled) {
							separationDY = staticObj.height+this.height-separationDY;
						} else {
							separationDY = 1000000;
						}
						upDisabled = true;
					}
				}
			}
			return result;
		}
		return new Vector[]{new Vector(), new Vector(), new Vector(), new Vector()};
	}
	
	public boolean equals(Object o) {
		return this == o;
	}
}
