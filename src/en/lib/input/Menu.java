package en.lib.input;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import en.lib.drawing.DrawUtils;
import en.lib.setup.Panel;

public abstract class Menu implements MouseListener {
	public Rectangle menuBounds = new Rectangle();
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private Font buttonFont = new Font("Arial", 0, 30);
	private ButtonStyle buttonStyle = new ButtonStyle() {
		public void draw(Button b, Graphics2D g2) {
			if (b.isPressed()) {
				g2.setColor(Color.GRAY);
			} else {
				g2.setColor(Color.WHITE);
			}
			g2.fillRect(b.x, b.y, b.width, b.height);
			g2.setColor(Color.BLACK);
			g2.drawRect(b.x, b.y, b.width, b.height);
			
			g2.setFont(buttonFont);
			DrawUtils.drawStringInBox(b.label, b, g2);
		}
	};
	
	
	public Menu(int x, int y, int width, int height) {
		menuBounds.setBounds(x, y, width, height);
		buttons = getButtons();
		int totalButtons = buttons.size();
		double gapFactor = 0.3, endGapFactor = 1;
		int buttonWidth = menuBounds.width/2, buttonHeight = (int)((menuBounds.height)/(totalButtons+gapFactor*(totalButtons-1)+endGapFactor*2));
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).setBounds(menuBounds.x+menuBounds.width/2-buttonWidth/2, (int)(menuBounds.y+i*(1+gapFactor)*buttonHeight+endGapFactor*buttonHeight), buttonWidth, buttonHeight);
		}
	}
	
	public Menu(Panel parent, int width, int height) {
		this(parent.getWidth()/2-width/2, parent.getHeight()/2-height/2, width, height);
	}
	
	public void show() {
		
	}
	
	public void hide() {
		
	}
	
	private BasicStroke defaultStroke = new BasicStroke(1);
	private BasicStroke menuBorderStroke = new BasicStroke(10);
	private Color menuColor = Color.ORANGE.darker().darker();
	private Color menuBorderColor = Color.ORANGE.darker().darker().darker();
	public void draw(Graphics2D g2) {
		g2.setColor(menuColor);
		g2.fill(menuBounds);
		
		g2.setColor(menuBorderColor);
		g2.setStroke(menuBorderStroke);
		g2.draw(menuBounds);
		
		g2.setStroke(defaultStroke);
		for (Button b:buttons) {
			buttonStyle.draw(b, g2);
		}
	}
	
	public abstract ArrayList<Button> getButtons();

	public void mousePressed(MouseEvent m) {
		for (Button b:buttons) {
			b.mousePressed(m);
		}
	}
	
	public void mouseReleased(MouseEvent m) {
		for (Button b:buttons) {
			b.mouseReleased(m);
		}
	}
	
	public void mouseClicked(MouseEvent m) {}
	public void mouseEntered(MouseEvent m) {}
	public void mouseExited(MouseEvent m) {}

}
