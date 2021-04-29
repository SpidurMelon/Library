package en.lib.math;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Formula {
	private Line2D.Double line;
	public Formula(Line2D.Double line) {
		this.line = line;
	}
	
	private double getCoefficient() {
		double dY = line.getY2()-line.getY1();
		double dX = line.getX2()-line.getX1();
		return dY/dX;
	}
	
	private double getTranslation() {
		Point2D a = line.getP1();
		double x = a.getX();
		
		double yWithoutTranslation = x*getCoefficient();
		double supposedY = a.getY();
		
		double translation = supposedY - yWithoutTranslation;
		return translation;
	}
	
	public double getY(double x) {
		return x*getCoefficient()+getTranslation();
	}
	
	public double getX(double y) {
		return (y-getTranslation())/getCoefficient();
	}
	
	public void print() {
		System.out.println("y = " + getCoefficient() + "x + " + getTranslation());
	}
}
