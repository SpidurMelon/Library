package en.lib.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import en.lib.math.Formula;
import en.lib.setup.Panel;
import en.lib.shapes.ShapeUtils;

public class TestPanel extends Panel implements MouseListener {
	private Rectangle rectangle1 = new Rectangle(0, 0, 50, 50);
	private Rectangle rectangle2 = new Rectangle(400, 400, 100, 100);
	
	public TestPanel() {
		setPreferredSize(new Dimension(800, 800));
		addMouseListener(this);
	}

	public void tick(double delta) {
		
	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		g2.setColor(Color.BLACK);
		g2.draw(rectangle1);
		g2.draw(rectangle2);
		
		g2.setColor(Color.RED);
		for (Rectangle r:ShapeUtils.cutOut(rectangle2, rectangle1)) {
			g2.fill(r);
		}
		
	}

	public void mousePressed(MouseEvent m) {
		if (SwingUtilities.isLeftMouseButton(m)) {
			rectangle1.setLocation(m.getPoint());
		} else {
			rectangle2.setLocation(m.getPoint());
		}
	}

	public void mouseClicked(MouseEvent m) {}
	public void mouseReleased(MouseEvent m) {}
	public void mouseEntered(MouseEvent m) {}
	public void mouseExited(MouseEvent m) {}
}
