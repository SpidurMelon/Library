package en.lib.shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import en.lib.drawing.Drawable;

public abstract class Transformable implements Drawable {
	private Shape initialShape;
	
	private Point translation = new Point(), rotationAxis = new Point();
	private double rotation = 0, scale = 1;
	
	private AffineTransform transform = new AffineTransform();
	
	public Transformable(Shape shape, Point rotationAxis, Point translation, double rotation, double scale) {
		this.initialShape = shape;
		this.rotationAxis = rotationAxis;
		setTransform(translation, rotation, scale);
	}
	
	public Transformable(Shape shape, Point rotationAxis) {
		this.initialShape = shape;
		this.rotationAxis = rotationAxis;
	}
	
	public Transformable(Shape shape) {
		this.initialShape = shape;
	}
	
	public Transformable() {
		
	}
	
	public void setTranslation(Point translation) {
		setTransform(translation, rotation, scale);
	}
	
	public void setRotation(double rotationDegrees) {
		setTransform(translation, Math.toRadians(rotationDegrees), scale);
	}
	
	public double getRotation() {
		return Math.toDegrees(rotation);
	}
	
	public void setScale(double scale) {
		setTransform(translation, rotation, scale);
	}
	
	public double getScale() {
		return scale;
	}
	
	public void setAxis(Point rotationAxis) {
		this.rotationAxis = rotationAxis;
	}
	
	public Point getAxis() {
		return rotationAxis;
	}
	
	public void setInitialShape(Shape shape) {
		this.initialShape = shape;
	}
	
	public Shape getInitialShape() {
		return initialShape;
	}
	
	public void setTransform(Point translation, double rotation, double scale) {
		this.translation = translation;
		this.rotation = rotation;
		this.scale = scale;
		updateAT();
	}
	
	private void updateAT() {
		transform.setToIdentity();
		transform.rotate(rotation, rotationAxis.x, rotationAxis.y);
		transform.translate(translation.x, translation.y);
		transform.scale(scale, scale);
	}
	
	public AffineTransform getAffineTransform() {
		return transform;
	}
	
	public Shape getTransformedShape() {
		return transform.createTransformedShape(initialShape);
	}

	public void draw(Graphics2D g2) {
		g2.draw(getTransformedShape());
	}

	public Point2D.Double getCenterDouble() {
		return ShapeUtils.getCenter(initialShape, getAffineTransform());
	}
	
	public Point getCenter() {
		Point2D.Double center = ShapeUtils.getCenter(initialShape, getAffineTransform());
		return new Point((int)Math.round(center.x), (int)Math.round(center.y));
	}

	public abstract int getDrawHeight();
}
