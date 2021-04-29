package en.lib.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import en.lib.math.Formula;
import en.lib.math.MathUtils;

public class ShapeUtils {
	public static ArrayList<Point> getCoords(Shape s, AffineTransform at) {
		ArrayList<Point> result = new ArrayList<Point>();
		PathIterator pi = s.getPathIterator(at);
		double[] coords = new double[6];
		while(!pi.isDone()) {
			pi.currentSegment(coords);
			result.add(new Point((int)coords[0], (int)coords[1]));
			pi.next();
		}
		return result;
	}
	
	public static Point getCenterPoint(Point a, Point b) {
		return new Point((a.x+b.x)/2, (a.y+b.y)/2);
	}
	
	public static Point2D.Double getCenter(Shape s, AffineTransform at) {
		ArrayList<Point> coords = getCoords(s, at);
		int totalX = 0, totalY = 0;
		for (int i = 0; i < coords.size()-2; i++) {
			totalX += coords.get(i).x;
			totalY += coords.get(i).y;
		}
		return new Point2D.Double(totalX/(double)(coords.size()-2), totalY/(double)(coords.size()-2));
	}
	
	public static int getMaxY(Shape s, AffineTransform at) {
		ArrayList<Point> coords = getCoords(s, at);
		int result = coords.get(0).y;
		for (Point p:coords) {
			if (p.y > result) {
				result = p.y;
			}
		}
		return result;
	}
	
	public static Point getClosestPoint(Point goal, ArrayList<Point> points) {
		Point result = new Point();
		if (!points.isEmpty()) {
			result.setLocation(points.get(0));
			for (Point p:points) {
				if (MathUtils.distPoints(p, goal) < MathUtils.distPoints(result, goal)) {
					result.setLocation(p);
				}
			}
		}
		return result;
	}
	public static ArrayList<Point> getIntersections(Formula f1, Rectangle rectangle) {
		ArrayList<Point> result = new ArrayList<Point>();
		if (f1.getY(rectangle.x) > rectangle.y && f1.getY(rectangle.x) < rectangle.getMaxY()) {
			result.add(new Point(rectangle.x, (int)f1.getY(rectangle.x)));
		}
		if (f1.getY(rectangle.getMaxX()) > rectangle.y && f1.getY(rectangle.getMaxX()) < rectangle.getMaxY()) {
			result.add(new Point((int)rectangle.getMaxX(), (int)f1.getY(rectangle.getMaxX())));
		}
		if (f1.getX(rectangle.y) > rectangle.x && f1.getX(rectangle.y) < rectangle.getMaxX()) {
			result.add(new Point((int)f1.getX(rectangle.y), rectangle.y));
		}
		if (f1.getX(rectangle.getMaxY()) > rectangle.x && f1.getX(rectangle.getMaxY()) < rectangle.getMaxX()) {
			result.add(new Point((int)f1.getX(rectangle.getMaxY()), (int)rectangle.getMaxY()));
		}
		return result;
	}
	
	public static ArrayList<Point> getIntersections(Line2D.Double line, Rectangle rectangle) {
		ArrayList<Point> result = new ArrayList<Point>();
		Formula f1 = new Formula(line);
		if (((line.getX1() < rectangle.x && rectangle.x < line.getX2()) || (line.getX2() < rectangle.x && rectangle.x < line.getX1())) && 
			f1.getY(rectangle.x) > rectangle.y && f1.getY(rectangle.x) < rectangle.getMaxY()) {
			result.add(new Point(rectangle.x, (int)f1.getY(rectangle.x)));
		}
		if (((line.getX1() < rectangle.getMaxX() && rectangle.getMaxX() < line.getX2()) || (line.getX2() < rectangle.getMaxX() && rectangle.getMaxX() < line.getX1())) && 
			f1.getY(rectangle.getMaxX()) > rectangle.y && f1.getY(rectangle.getMaxX()) < rectangle.getMaxY()) {
			result.add(new Point((int)rectangle.getMaxX(), (int)f1.getY(rectangle.getMaxX())));
		}
		if (((line.getY1() < rectangle.y && rectangle.y < line.getY2()) || (line.getY2() < rectangle.y && rectangle.y < line.getY1())) && 
			f1.getX(rectangle.y) > rectangle.x && f1.getX(rectangle.y) < rectangle.getMaxX()) {
			result.add(new Point((int)f1.getX(rectangle.y), rectangle.y));
		}
		if (((line.getY1() < rectangle.getMaxY() && rectangle.getMaxY() < line.getY2()) || (line.getY2() < rectangle.getMaxY() && rectangle.getMaxY() < line.getY1())) && 
			f1.getX(rectangle.getMaxY()) > rectangle.x && f1.getX(rectangle.getMaxY()) < rectangle.getMaxX()) {
			result.add(new Point((int)f1.getX(rectangle.getMaxY()), (int)rectangle.getMaxY()));
		}
		return result;
	}
	
	public static ArrayList<Rectangle> cutOut(Rectangle rectangle, Rectangle cutterRectangle) {
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		if (rectangle.intersects(cutterRectangle)) {
			Rectangle cutter = rectangle.intersection(cutterRectangle);
			if (cutter.x > rectangle.x) {
				result.add(new Rectangle(rectangle.x, rectangle.y, cutter.x-rectangle.x, rectangle.height));
			}
			if (cutter.getMaxX() < rectangle.getMaxX()) {
				result.add(new Rectangle((int)cutter.getMaxX(), rectangle.y, (int)(rectangle.getMaxX()-cutter.getMaxX()), rectangle.height));
			}
			if (cutter.y > rectangle.y) {
				result.add(new Rectangle(cutter.x, rectangle.y, cutter.width, cutter.y-rectangle.y));
			}
			if (cutter.getMaxY() < rectangle.getMaxY()) {
				result.add(new Rectangle(cutter.x, (int)cutter.getMaxY(), cutter.width, (int)(rectangle.getMaxY()-cutter.getMaxY())));
			}
		} else {
			result.add(rectangle);
		}
		return result;
	}
	
}
