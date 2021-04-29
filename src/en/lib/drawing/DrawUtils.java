package en.lib.drawing;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class DrawUtils {
	public static void drawStringInBox(String string, Rectangle box, Graphics2D g2) {
		FontMetrics fMetrics = g2.getFontMetrics();
		if (fMetrics.stringWidth(string) < box.getWidth()) {
			g2.drawString(string, (int)(box.getCenterX()-fMetrics.stringWidth(string)/2), (int)(box.getCenterY()+fMetrics.getHeight()/3));
		} else {
			ArrayList<String> lines = new ArrayList<String>();
			int roughLineLength = (int)(string.length()/(fMetrics.stringWidth(string)/box.getWidth()));
			
			int beginIndex = 0, endIndex = roughLineLength;
			while (true) {
				lines.add(string.substring(beginIndex, string.lastIndexOf(" ", endIndex)));
				beginIndex = string.lastIndexOf(" ", endIndex)+1;
				endIndex = beginIndex+roughLineLength;
				if (endIndex > string.length()) {
					lines.add(string.substring(beginIndex, string.length()));
					break;
				}
			}
			
			
			for (int i = 0; i < lines.size(); i++) {
				g2.drawString(lines.get(i), (int)(box.getCenterX()-fMetrics.stringWidth(lines.get(i))/2), 
											(int)(box.getCenterY()+fMetrics.getHeight()/3+((lines.size()+1)%2)*(fMetrics.getHeight()/2)-(lines.size()/2)*fMetrics.getHeight()+i*fMetrics.getHeight()));
			}
		}
	}
	public static void drawPoint(int x, int y, int size, Graphics2D g2) {
		g2.fillOval(x-size/2, y-size/2, size, size);
	}
}
