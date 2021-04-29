package en.lib.input;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import en.lib.drawing.DrawUtils;

public abstract class Button extends Rectangle implements MouseListener {
	protected boolean pressedInside = false;
	public String label;
	
	public Button(int x, int y, int width, int height, String label) {
		super(x, y, width, height);
		this.label = label;
	}
	public Button(String label) {
		this(0,0,0,0,label);
	}
	
	public abstract void onClick();

	public void mousePressed(MouseEvent m) {
		
		if (this.contains(m.getPoint())) {
			pressedInside = true;
		}
	}

	public void mouseReleased(MouseEvent m) {
		if (pressedInside && this.contains(m.getPoint())) {
			onClick();
			pressedInside = false;
		} else {
			pressedInside = false;
		}
	}
	
	public boolean isPressed() {
		return pressedInside;
	}
	
	public void draw(Graphics2D g2) {
		if (pressedInside) {
			g2.setColor(Color.GRAY);
		} else {
			g2.setColor(Color.WHITE);
		}
		g2.fillRect(x, y, width, height);
		g2.setColor(Color.BLACK);
		g2.drawRect(x, y, width, height);
		
		g2.setFont(new Font("Arial", 0, 12));
		DrawUtils.drawStringInBox(label, this, g2);
	}

	public void mouseEntered(MouseEvent m) {}
	public void mouseExited(MouseEvent m) {}
	public void mouseClicked(MouseEvent m) {}
	
	
}
